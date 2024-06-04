package com.xkyii.spry.entity;

import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @SequenceGenerator(
        name = "userSeq",
        sequenceName = "seq_user_id",
        allocationSize = 1,
        initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSeq")
    private Long id;

    private String username;

    private String password;

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
