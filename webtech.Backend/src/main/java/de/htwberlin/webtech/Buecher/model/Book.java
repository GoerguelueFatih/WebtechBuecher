package de.htwberlin.webtech.Buecher.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "books")
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    String isbn;
    String title;
    String cost;
    String releaseDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category", referencedColumnName = "name")
    private Category category;

    @Transient
    private Long categoryId;

    @Transient
    private String categoryName;

}
