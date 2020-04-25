package com.sd2backend.backendsd2.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "Loan")
public class LoanedArts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "User_id")
    private User User_id;


    @OneToMany(mappedBy = "Loan_id")
    private List<Art> loanedArts ;

    private String plaats;

    private String adres ;

    private String postcode ;

    private Date datGeleend;

    private Date datTerug;


    public LoanedArts() {
    }

    public LoanedArts(@NotNull User user_id, String plaats, String adres, String postcode, Date datGeleend, Date datTerug) {
        User_id = user_id;
        this.plaats = plaats;
        this.adres = adres;
        this.postcode = postcode;
        this.datGeleend = datGeleend;
        this.datTerug = datTerug;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Art> getLoanedArts() {
        return loanedArts;
    }

    public void setLoanedArts(List<Art> loanedArts) {
        this.loanedArts = loanedArts;
    }

    public String getPlaats() {
        return plaats;
    }

    public void setPlaats(String plaats) {
        this.plaats = plaats;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Date getDatGeleend() {
        return datGeleend;
    }

    public void setDatGeleend(Date datGeleend) {
        this.datGeleend = datGeleend;
    }

    public Date getDatTerug() {
        return datTerug;
    }

    public void setDatTerug(Date datTerug) {
        this.datTerug = datTerug;
    }

    public int getId() {
        return id;
    }


    public User getUser_id() {
        return User_id;
    }

    public void setUser_id(User user_id) {
        this.User_id = user_id;
    }
}
