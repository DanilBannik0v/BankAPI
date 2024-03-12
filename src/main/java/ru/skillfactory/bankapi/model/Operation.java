package ru.skillfactory.bankapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private int clientId;
    @Column
    private int type;
    @Column
    private int sum;
    @Column
    private Date date;
}
