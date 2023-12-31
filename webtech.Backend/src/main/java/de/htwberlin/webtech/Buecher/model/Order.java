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
    String total;
    String status;
    LocalDate localDate = LocalDate.now();


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
