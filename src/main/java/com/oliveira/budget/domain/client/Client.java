package com.oliveira.budget.domain.client;

import com.oliveira.budget.domain.user.User;
import jakarta.persistence.*;

import java.util.UUID;

@Table(name = "clients")
@Entity
public class Client {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String email;
    private String phone;

    @OneToOne(mappedBy = "clients", cascade = CascadeType.ALL)
    private Address address;

    @JoinColumn(name = "user_id")
    private User user;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
