package de.htwberlin.webtech.Buecher.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "book_order")
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookOrder {

    @Id
    String id;
    String name;
    String kosten;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
