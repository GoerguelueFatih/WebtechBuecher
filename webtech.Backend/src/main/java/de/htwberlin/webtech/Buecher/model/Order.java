package de.htwberlin.webtech.Buecher.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "orders")
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    String id;
    String summe;
    String status;
    LocalDate localDate = LocalDate.now();


    @ManyToOne
    @JoinColumn(name = "users")
    private User user;

}
