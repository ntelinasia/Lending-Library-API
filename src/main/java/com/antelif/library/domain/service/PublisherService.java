package com.antelif.library.domain.service;

import static com.antelif.library.application.error.GenericError.DUPLICATE_PUBLISHER;
import static com.antelif.library.application.error.GenericError.PUBLISHER_CREATION_FAILED;

import com.antelif.library.domain.converter.PublisherConverter;
import com.antelif.library.domain.dto.request.PublisherRequest;
import com.antelif.library.domain.dto.response.PublisherResponse;
import com.antelif.library.domain.exception.DuplicateEntityException;
import com.antelif.library.domain.exception.EntityCreationException;
import com.antelif.library.infrastructure.repository.PublisherRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** Publisher service. */
@Service
@RequiredArgsConstructor
public class PublisherService {

  private final PublisherRepository publisherRepository;
  private final PublisherConverter converter;

  /**
   * Adds publisher to database.
   *
   * @param publisherRequest the request DTO to get information about the publisher to create.
   * @return a publisher response DTO.
   */
  public PublisherResponse addPublisher(PublisherRequest publisherRequest) {

    var persistedEntity =
        Optional.ofNullable(publisherRepository.getPublishersByName(publisherRequest.getName()));

    if (persistedEntity.isPresent() && !persistedEntity.get().isEmpty()) {
      throw new DuplicateEntityException(DUPLICATE_PUBLISHER);
    }

    return Optional.of(converter.convertFromRequestToEntity(publisherRequest))
        .map(publisherRepository::save)
        .map(converter::convertFromEntityToResponse)
        .orElseThrow(() -> new EntityCreationException(PUBLISHER_CREATION_FAILED));
  }
}
