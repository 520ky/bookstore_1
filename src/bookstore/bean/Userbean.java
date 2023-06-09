package bookstore.bean;


public class Userbean {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String url;
    private String userAdmin;

    public Userbean() {
    }

    public Userbean(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Userbean{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", url='" + url + '\'' +
                ", userAdmin='" + userAdmin + '\'' +
                '}';
    }

    public Userbean(String username, String password, String email, String url) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.url = url;
    }

    public Userbean(String username, String password, String userAdmin) {
        this.username = username;
        this.password = password;
        this.userAdmin = userAdmin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserAdmin() {
        return userAdmin;
    }

    public void setUserAdmin(String userAdmin) {
        this.userAdmin = userAdmin;
    }

    public Userbean(Integer id, String username, String password, String email, String url, String userAdmin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.url = url;
        this.userAdmin = userAdmin;
    }
}
