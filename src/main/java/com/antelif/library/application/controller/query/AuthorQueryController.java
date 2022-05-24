package com.antelif.library.application.controller.query;

import static com.antelif.library.domain.common.Constants.ID;
import static com.antelif.library.domain.common.Constants.SURNAME;

import com.antelif.library.domain.dto.response.AuthorResponse;
import com.antelif.library.domain.service.AuthorService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** Author query controller. */
@RestController
@Slf4j
@RequestMapping(value = "/library/authors")
@RequiredArgsConstructor
public class AuthorQueryController {
  private final AuthorService authorService;

  /**
   * Get authors by surname endpoint.
   *
   * @param surname the surname of the author
   * @return a list of author response DTOs for the provided name and surname.
   */
  @GetMapping
  public ResponseEntity<List<AuthorResponse>> getAuthorBySurname(
      @RequestParam(value = SURNAME) String surname) {
    log.info("Received request to fetch authors with surname {}", surname);
    return ResponseEntity.ok(authorService.getAuthorsBySurname(surname));
  }

  /**
   * Get author by id endpoint.
   *
   * @param id the id of the author to retrieve.
   * @return an author response DTO for the provided id.
   */
  @GetMapping(value = "/{id}")
  public ResponseEntity<AuthorResponse> getAuthorById(@PathVariable(value = ID) Long id) {
    log.info("Received request to fetch author with id {}", id);
    return ResponseEntity.ok(authorService.getAuthorById(id));
  }
}
