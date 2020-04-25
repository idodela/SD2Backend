package com.sd2backend.backendsd2.repositories;

import com.sd2backend.backendsd2.models.Art;
import com.sd2backend.backendsd2.models.LoanedArts;
import com.sd2backend.backendsd2.models.User;
import com.sd2backend.backendsd2.repositories.interfaces.LoanInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

@Repository
@Transactional
public class LoanRepository implements LoanInterface {

    @Autowired
    private EntityManager entityManager;

    @Override
    public LoanedArts makeNewLoan(String userId, int artId, String adress, String place, String zipCode , Date datGeleend , Date datTerug ) {
        User user = entityManager.find(User.class, userId);

        LoanedArts loanedArts = new LoanedArts( user , place, adress,
        zipCode, datGeleend, datTerug);

        Art art = entityManager.find(Art.class, artId);
        art.setLoan_id(loanedArts);


        entityManager.merge(loanedArts);

        entityManager.merge(art);

        return loanedArts;

    }



    @Override
    public List<Art> findLoanedArtsByUser(String userId) {
        User user = entityManager.find(User.class, userId);

        TypedQuery<Art> query = entityManager.createQuery("SELECT a FROM Art a JOIN a.Loan_id l WHERE l.User_id = :user",
                Art.class);
        query.setParameter("user", user);

        return query.getResultList();
    }
}
