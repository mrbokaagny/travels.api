package api.transporycompagny.travels.dtos;

public class CityDto {

    private String libelle;
    private Long country_key;

    public CityDto(){}

    public CityDto(String libelle, Long country_key) {
        this.libelle = libelle;
        this.country_key = country_key;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Long getCountry_key() {
        return country_key;
    }

    public void setCountry_key(Long country_key) {
        this.country_key = country_key;
    }

    
}
