package api.transporycompagny.travels.dtos;

public class LineDto {

    private String starting_point;
    private String point_of_arrival;
    private Number price;
    private Long company_key;

    public String getStarting_point() {
        return starting_point;
    }

    public void setStarting_point(String starting_point) {
        this.starting_point = starting_point;
    }

    public String getPoint_of_arrival() {
        return point_of_arrival;
    }

    public void setPoint_of_arrival(String point_of_arrival) {
        this.point_of_arrival = point_of_arrival;
    }

    public Number getPrice() {
        return price;
    }

    public void setPrice(Number price) {
        this.price = price;
    }

    public Long getCompany_key() {
        return company_key;
    }

    public void setCompany_key(Long company_key) {
        this.company_key = company_key;
    }

}
