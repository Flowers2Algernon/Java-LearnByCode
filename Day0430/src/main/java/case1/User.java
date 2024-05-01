package case1;

public class User {
    private String username;
    private String password;
    private String diskPath;

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

    public String getDiskPath() {
        return diskPath;
    }

    public void setDiskPath(String diskPath) {
        this.diskPath = diskPath;
    }

    public User(String username, String password, String diskPath) {
        this.username = username;
        this.password = password;
        this.diskPath = diskPath;
    }
}
