package api.transporycompagny.travels.dtos;

public class StartALineDto {

    private Long car_key;
    private Long company_key;
    private Long destination_key;

    public StartALineDto() {
    }

    public StartALineDto(Long car_key, Long company_key, Long destination_key) {
        this.car_key = car_key;
        this.destination_key = destination_key;
        this.company_key = company_key;
    }

    public Long getCar_key() {
        return car_key;
    }

    public void setCar_key(Long car_key) {
        this.car_key = car_key;
    }

    public Long getCompany_key() {
        return company_key;
    }

    public void setCompany_key(Long company_key) {
        this.company_key = company_key;
    }

    public Long getDestination_key() {
        return destination_key;
    }

    public void setDestination_key(Long destionation_key) {
        this.destination_key = destionation_key;
    }

}
