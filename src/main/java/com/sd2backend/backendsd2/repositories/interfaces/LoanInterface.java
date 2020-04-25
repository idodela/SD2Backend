package com.sd2backend.backendsd2.repositories.interfaces;

import com.sd2backend.backendsd2.models.Art;
import com.sd2backend.backendsd2.models.LoanedArts;

import java.sql.Date;
import java.util.List;

public interface LoanInterface {

    LoanedArts makeNewLoan(String userId, int artId, String adress, String place, String zipCode , Date datGeleend , Date datTerug);

    List<Art> findLoanedArtsByUser(String userId);

}
