package ru.learnup.vtb.library.libraryapplication.repository.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

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
@Table(name = "books")
@NamedQueries({
        @NamedQuery(name = "book.findlikename", query = "from BookEntity b where b.name like :pattern")
})
//@Cacheable
//@org.hibernate.annotations.Cache(include = "all", region = "book.id", usage = CacheConcurrencyStrategy.READ_ONLY)
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "books_id_seq", sequenceName = "books_id_seq")
    private Long id;

    @Column(name = "name", length = 64)
    private String name;

    @Version
    private Integer version;

    @JoinColumn(name = "author_id")
    @ManyToOne
    private AuthorEntity author;

    @Override
    public String toString() {
        return String.format("%s %s (%d)", author.getName(), name, id);
    }
}
