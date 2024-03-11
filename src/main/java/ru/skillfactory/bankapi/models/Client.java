package ru.skillfactory.bankapi.models;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private int balance;

    public void setId(long id) {
        this.id = id;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
