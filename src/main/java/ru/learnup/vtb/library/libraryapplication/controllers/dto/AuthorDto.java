package ru.learnup.vtb.library.libraryapplication.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

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
public class AuthorDto extends RepresentationModel<AuthorDto> {

    @JsonProperty
    private Long id;

    @JsonProperty
    private String name;

}
