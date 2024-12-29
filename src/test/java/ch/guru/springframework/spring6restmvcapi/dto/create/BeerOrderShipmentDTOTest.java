package ch.guru.springframework.spring6restmvcapi.dto.create;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BeerOrderShipmentDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        Locale.setDefault(Locale.US);
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void testValidBeerOrderShipmentDTO() {
        BeerOrderShipmentDTO dto = BeerOrderShipmentDTO.builder()
            .id(UUID.randomUUID())
            .version(1L)
            .trackingNumber("TRACK123")
            .createdDate(Timestamp.from(Instant.now()))
            .lastModifiedDate(Timestamp.from(Instant.now()))
            .build();

        Set<ConstraintViolation<BeerOrderShipmentDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testInvalidBeerOrderShipmentDTO_BlankTrackingNumber() {
        BeerOrderShipmentDTO dto = BeerOrderShipmentDTO.builder()
            .id(UUID.randomUUID())
            .version(1L)
            .trackingNumber("")
            .createdDate(Timestamp.from(Instant.now()))
            .lastModifiedDate(Timestamp.from(Instant.now()))
            .build();

        Set<ConstraintViolation<BeerOrderShipmentDTO>> violations = validator.validate(dto);
        assertEquals(1, violations.size());
        assertEquals("must not be blank", violations.iterator().next().getMessage());
    }

    @Test
    void testInvalidBeerOrderShipmentDTO_NullTrackingNumber() {
        BeerOrderShipmentDTO dto = BeerOrderShipmentDTO.builder()
            .id(UUID.randomUUID())
            .version(1L)
            .trackingNumber(null)
            .createdDate(Timestamp.from(Instant.now()))
            .lastModifiedDate(Timestamp.from(Instant.now()))
            .build();

        Set<ConstraintViolation<BeerOrderShipmentDTO>> violations = validator.validate(dto);
        assertEquals(1, violations.size());
        assertEquals("must not be blank", violations.iterator().next().getMessage());
    }

    @Test
    void testBeerOrderShipmentDTOBuilder() {
        UUID id = UUID.randomUUID();
        Long version = 1L;
        String trackingNumber = "TRACK123";
        Timestamp createdDate = Timestamp.from(Instant.now());
        Timestamp lastModifiedDate = Timestamp.from(Instant.now());

        BeerOrderShipmentDTO dto = BeerOrderShipmentDTO.builder()
            .id(id)
            .version(version)
            .trackingNumber(trackingNumber)
            .createdDate(createdDate)
            .lastModifiedDate(lastModifiedDate)
            .build();

        assertNotNull(dto);
        assertEquals(id, dto.getId());
        assertEquals(version, dto.getVersion());
        assertEquals(trackingNumber, dto.getTrackingNumber());
        assertEquals(createdDate, dto.getCreatedDate());
        assertEquals(lastModifiedDate, dto.getLastModifiedDate());
    }
}
