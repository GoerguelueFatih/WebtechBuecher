package de.htwberlin.webtech.Buecher.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Table(name = "books")
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    String isbn;
    @NonNull
    String title;
    @NonNull
    String author;
    @NonNull
    BigDecimal price;
    String releaseDate;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "category", referencedColumnName = "name")
    private Category category;


    @ManyToMany(mappedBy = "books")
    @JsonIgnore
    private List<Order> orders;
}
