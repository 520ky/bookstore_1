package bookstore.bean;

public class Salt {
    private String username;
    private String salt;

    public Salt() {
    }

    public Salt(String username, String salt) {
        this.username = username;
        this.salt = salt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "Salt{" +
                "username='" + username + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }
}
