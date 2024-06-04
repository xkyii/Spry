package com.xkyii.spry.entity;

import jakarta.persistence.*;

import java.util.Date;

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

    @Column(name = "username", length = 32, unique = true, nullable = false)
    private String username;

    @Column(name = "nickname", length = 32)
    private String nickname;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Column(name = "email", length = 64)
    private String email;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "deleted_by")
    private Long deletedBy;

    @Column(name = "deleted_at")
    private Date deletedAt;

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

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(Long deletedBy) {
        this.deletedBy = deletedBy;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }
}
