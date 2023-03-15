package api.transporycompagny.travels.dtos;

public class UserDto {

    private String name;
    private String surname;
    private String login;
    private String number;
    private Long role_key;
    private String password;

    public UserDto() {
    }

    public UserDto(String name, String surname, String login, String number, Long role_key, String password) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.number = number;
        this.role_key = role_key;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getRole_key() {
        return role_key;
    }

    public void setRole_key(Long role_key) {
        this.role_key = role_key;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
