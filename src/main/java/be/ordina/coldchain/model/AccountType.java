/*
 Author:Roby de Visser
*/

package be.ordina.coldchain.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class AccountType {

    @Id
    private String id;

    @Version
    private int version;

    private String naam;

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

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }
}
