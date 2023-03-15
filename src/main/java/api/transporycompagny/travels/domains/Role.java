package api.transporycompagny.travels.domains;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long role_key;

    @Column(name = "libelle", nullable = false)
    private String libelle;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<Login> logins;

    public long getRole_key() {
        return role_key;
    }

    public void setRole_key(long role_key) {
        this.role_key = role_key;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

}
