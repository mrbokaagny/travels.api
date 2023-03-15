package api.transporycompagny.travels.dtos;

public class CarDto {

    private String libelle;
    private String matricule;
    private Number number_of_place;
    private Long company_key;

    public CarDto() {
    }

    public CarDto(String libelle, String matricule, Number number_of_place, Long company_key) {
        this.libelle = libelle;
        this.matricule = matricule;
        this.number_of_place = number_of_place;
        this.company_key = company_key;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public Number getNumber_of_place() {
        return number_of_place;
    }

    public void setNumber_of_place(Number number_of_place) {
        this.number_of_place = number_of_place;
    }

    public Long getCompany_key() {
        return company_key;
    }

    public void setCompany_key(Long company_key) {
        this.company_key = company_key;
    }

}
