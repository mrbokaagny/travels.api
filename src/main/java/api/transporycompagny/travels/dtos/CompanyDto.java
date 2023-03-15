package api.transporycompagny.travels.dtos;

public class CompanyDto {

    private String name;
    private Number number_of_car;
    private Number number_of_line;
    private Long city_key;
    private String longitude ;
    private String latitude ;

    public CompanyDto() {
    }

    public CompanyDto(String name, Number number_of_car, Number number_of_line, Long city_key , String longitude , String latitude) {
        this.name = name;
        this.number_of_car = number_of_car;
        this.number_of_line = number_of_line;
        this.city_key = city_key;
        this.longitude = longitude ;
        this.latitude = latitude ;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Number getNumber_of_car() {
        return number_of_car;
    }

    public void setNumber_of_car(Number number_of_car) {
        this.number_of_car = number_of_car;
    }

    public Number getNumber_of_line() {
        return number_of_line;
    }

    public void setNumber_of_line(Number number_of_line) {
        this.number_of_line = number_of_line;
    }

    public Long getCity_key() {
        return city_key;
    }

    public void setCity_key(Long city_key) {
        this.city_key = city_key;
    }

    

}
