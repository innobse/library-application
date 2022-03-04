package ru.learnup.vtb.library.libraryapplication.repository.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Collection;

/**
 * Description
 *
 * @author bse71
 * Created on 18.02.2022
 * @since
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "authors")
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "author-with-books",
                attributeNodes = {
                        @NamedAttributeNode("id"),
                        @NamedAttributeNode("name"),
                        @NamedAttributeNode(value = "books", subgraph = "author-books")
                },

                subgraphs = {
                        @NamedSubgraph(name = "author-books", attributeNodes = {
                                @NamedAttributeNode("id"),
                                @NamedAttributeNode("name")
                        })
                }
        )
})
//@Cacheable
//@org.hibernate.annotations.Cache(include = "non-lazy", region = "author.id", usage = CacheConcurrencyStrategy.READ_ONLY)
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authors_id_seq")
    @SequenceGenerator(name = "authors_id_seq", sequenceName = "authors_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name", length = 64)
    private String name;

    @Version
    private Integer version;

    @OneToMany(mappedBy = "author")
    private Collection<BookEntity> books;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(String.format("%s (%d)\n", name, id));
//        for (BookEntity book : books) {
//            sb.append(book).append("\n");
//        }
        return sb.toString();
    }
}
