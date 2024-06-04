package com.xkyii.spry.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "t_user")
public class User {
    @Id
    @Column(name = "id")
    @SequenceGenerator(
        name = "userSeq",
        sequenceName = "seq_user_id",
        allocationSize = 1,
        initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSeq")
    private Long id;

    @Column(name = "username", length = 32)
    private String username;

    @Column(name = "nickname", length = 32)
    private String nickname;

    @Column(name = "password", length = 255)
    private String password;

    @Column(name = "email", length = 64)
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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
}
