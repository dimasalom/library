package dima.spring.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
@NamedEntityGraph(name = "genre-entity-graph", attributeNodes = {@NamedAttributeNode("genre")})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "book_name")
    private String name;

    @ManyToOne(targetEntity = Author.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", foreignKey = @ForeignKey(name = "fk_authors"))
    private Author author;

    @ManyToOne(targetEntity = Genre.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "genre_id", foreignKey = @ForeignKey(name = "fk_genres"))
    private Genre genre;
}
