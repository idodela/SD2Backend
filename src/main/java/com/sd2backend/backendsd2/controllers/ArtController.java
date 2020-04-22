package com.sd2backend.backendsd2.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sd2backend.backendsd2.models.Art;
import com.sd2backend.backendsd2.models.User;
import com.sd2backend.backendsd2.models.UserType;
import com.sd2backend.backendsd2.models.helper.AppconfigJ;
import com.sd2backend.backendsd2.repositories.ArtRepository;
import com.sd2backend.backendsd2.security.JWToken;
import com.sd2backend.backendsd2.security.JWTokenInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.parser.Entity;
import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import static org.aspectj.bridge.MessageUtil.fail;

@RestController
@RequestMapping("/arts")
public class ArtController {

    @Autowired
    private ArtRepository artRepository;

    @Autowired
    AppconfigJ appconfigJ;

    private String getUserIdFromToken(String token) {
        String s = token.replace("Bearer ", "");
        JWTokenInfo jwTokenInfo = JWToken.decode(s, appconfigJ.passphrase);
        return jwTokenInfo.getId();
    }

    @GetMapping("")
    @ResponseBody
    public List<Art>findAllArts(){
        List<Art> arts =  artRepository.findAll();

        return arts;

    }



    @PostMapping("")
    public ResponseEntity createArt(@RequestParam(value = "name") String name,
                                    @RequestParam(value = "price") Double price,
                                    @RequestParam(value = "available") String availablep,
                                    @RequestParam(value = "description") String description,
                                    @RequestParam(value = "img") MultipartFile img,
                                    @RequestHeader(name = "Authorization") String token) throws IOException {
        Art art = new Art();


        boolean available = Boolean.parseBoolean(availablep);

        art.setName(name);
        art.setAvailable(available);
        art.setPrice(price);
        art.setDescription(description);
        art.setImg(img.getBytes());
        System.out.println("Original Image Byte Size - " + img.getBytes().length);

        Art savedArt = artRepository.save(art,getUserIdFromToken(token) );
        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest().path("/{id}").
                buildAndExpand(savedArt.getId()).toUri();
        return ResponseEntity.created(location).body(savedArt);
    }

    private static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException ignored) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }

    // uncompress the image bytes before returning it to the angular application
    private static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException | DataFormatException ignored) {
        }
        return outputStream.toByteArray();
    }
}


