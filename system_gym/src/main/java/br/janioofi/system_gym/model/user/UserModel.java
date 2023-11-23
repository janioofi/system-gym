package br.janioofi.system_gym.model.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "tb_user")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id_user;

    @NotEmpty
    @Column(unique = true, length = 30, nullable = false)
    private String login;

    @NotEmpty
    @Column(nullable = false)
    private String password;

    @NotEmpty
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public UserModel() {
    }

    public UserModel(String id_user, String login, String password, UserRole role) {
        this.id_user = id_user;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
