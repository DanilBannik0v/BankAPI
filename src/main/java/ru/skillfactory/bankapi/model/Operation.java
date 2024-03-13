package ru.skillfactory.bankapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="operations")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private long clientId;
    @Column
    private int type;
    @Column
    private int sum;
    @Column
    private LocalDate localDate;

    public Operation(long clientId, int type, int sum, LocalDate localDate) {
        this.clientId = clientId;
        this.type = type;
        this.sum = sum;
        this.localDate = localDate;
    }
}
