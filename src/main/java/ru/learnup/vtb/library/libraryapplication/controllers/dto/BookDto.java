package ru.learnup.vtb.library.libraryapplication.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.learnup.vtb.library.libraryapplication.model.Author;

/**
 * Description
 *
 * @author bse71
 * Created on 12.02.2022
 * @since
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDto {

    @JsonProperty("title")
    private String name;

    @JsonProperty
    private Author author;

}
