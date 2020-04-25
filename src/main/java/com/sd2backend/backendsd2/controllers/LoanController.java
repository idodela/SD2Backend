package com.sd2backend.backendsd2.controllers;

import com.sd2backend.backendsd2.models.Art;
import com.sd2backend.backendsd2.models.LoanedArts;
import com.sd2backend.backendsd2.models.helper.AppconfigJ;
import com.sd2backend.backendsd2.repositories.LoanRepository;
import com.sd2backend.backendsd2.security.JWToken;
import com.sd2backend.backendsd2.security.JWTokenInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {


    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    AppconfigJ appconfigJ;

    private String getUserIdFromToken(String token) {
        String s = token.replace("Bearer ", "");
        JWTokenInfo jwTokenInfo = JWToken.decode(s, appconfigJ.passphrase);
        return jwTokenInfo.getId();
    }


    @GetMapping("")
    public List<Art> getLoanedArts(@RequestHeader(name = "Authorization") String token){
        return loanRepository.findLoanedArtsByUser(getUserIdFromToken(token));
    }


    //not done
    @PostMapping("")
   public  LoanedArts saveLoan(@RequestHeader(name = "Authorization") String token, @RequestParam(value = "artId") int artId,
                               @RequestParam(value = "plaats") String place,
                               @RequestParam(value = "adres") String adress,
                               @RequestParam(value = "postcode") String zipCode,
                               @RequestParam(value = "datGeleend") Date datGeleend,
                               @RequestParam(value = "datTerug") Date datTerug

                               ){

     return    loanRepository.makeNewLoan(getUserIdFromToken(token), artId, adress, place, zipCode, datGeleend, datTerug);

    }

}
