package api.transporycompagny.travels.dtos;

public class TicketDto {

    private Long user_key;
    private Long start_line_key;

    public TicketDto() {
    }

    public TicketDto(Long user_key, Long start_line_key) {
        this.user_key = user_key;
        this.start_line_key = start_line_key;
    }

    public Long getUser_key() {
        return user_key;
    }

    public void setUser_key(Long user_key) {
        this.user_key = user_key;
    }

    public Long getStart_line_key() {
        return start_line_key;
    }

    public void setStart_line_key(Long start_line_key) {
        this.start_line_key = start_line_key;
    }

}
