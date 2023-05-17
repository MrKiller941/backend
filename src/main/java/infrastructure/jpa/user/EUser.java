package infrastructure.jpa.user;

import java.io.Serializable;

import application.auth.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"users\"")
public class EUser implements Serializable {
    
    @Id
    @Column(name = "\"u_id\"")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "\"u_login\"")
    private String login;

    @Column(name = "\"u_password\"")
    private String password;

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setAll(User user){
        setLogin(user.getLogin());
        setPassword(user.getPassword());
    }
}
