/*
 Author:Roby de Visser
*/

package be.ordina.coldchain.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Account {

    @Id
    private String id;

    @Version
    private int version;

    private String email;
    private String password;
    private String bedrijfsnaam;
    private String straat;
    private int huisnummer;
    private String stad;
    private int postcode;
    private String btwNummer;

    @ManyToOne
    private AccountType accountType;
    @ManyToOne
    private Bedrijfsvorm bedrijfsvorm;

    public String getBtwNummer() {
        return btwNummer;
    }

    public void setBtwNummer(String btwNummer) {
        this.btwNummer = btwNummer;
    }

    public String getId() {
        return id;
    }

    public void setId(String UUID) {
        this.id = UUID;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBedrijfsnaam() {
        return bedrijfsnaam;
    }

    public void setBedrijfsnaam(String bedrijfsnaam) {
        this.bedrijfsnaam = bedrijfsnaam;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Bedrijfsvorm getBedrijfsvorm() {
        return bedrijfsvorm;
    }

    public void setBedrijfsvorm(Bedrijfsvorm bedrijfsvorm) {
        this.bedrijfsvorm = bedrijfsvorm;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public int getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(int huisnummer) {
        this.huisnummer = huisnummer;
    }

    public String getStad() {
        return stad;
    }

    public void setStad(String stad) {
        this.stad = stad;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }
}

