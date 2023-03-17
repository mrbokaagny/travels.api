package api.transporycompagny.travels.domains;

import java.util.List;

import api.transporycompagny.travels.utils.AbstractDateTime;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "functions")
public class Function extends AbstractDateTime {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long function_key;

    @Column(name = "libelle")
    private String libelle;

    @OneToMany(mappedBy = "function", cascade = CascadeType.ALL)
    private List<Login> login;

    public long getFunction_key() {
        return function_key;
    }

    public void setFunction_key(long function_key) {
        this.function_key = function_key;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

}
