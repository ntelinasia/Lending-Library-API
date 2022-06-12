package com.antelif.library.domain.exception;

import com.antelif.library.application.error.GenericError;
import java.util.List;
import lombok.Getter;

/** Generic Exception. */
@Getter
public class GenericException extends RuntimeException {

  private final List<GenericError> genericErrors;

  public GenericException(GenericError genericError) {
    super(genericError.getDescription());
    this.genericErrors = List.of(genericError);
  }

  public GenericException(GenericError genericError, String args) {
    super(args);
    this.genericErrors = List.of(genericError);
  }
}
