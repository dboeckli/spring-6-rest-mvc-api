package ch.guru.springframework.spring6restmvcapi.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BeerOrderLineDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void testValidBeerOrderLineDTO() {
        BeerOrderLineDTO beerOrderLineDTO = BeerOrderLineDTO.builder()
            .id(UUID.randomUUID())
            .version(1L)
            .orderQuantity(10)
            .quantityAllocated(5)
            .orderLineStatus(BeerOrderLineStatus.NEW)
            .beer(new BeerDTO())
            .createdDate(Timestamp.from(Instant.now()))
            .updateDate(Timestamp.from(Instant.now()))
            .build();

        Set<ConstraintViolation<BeerOrderLineDTO>> violations = validator.validate(beerOrderLineDTO);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testInvalidBeerOrderLineDTO() {
        BeerOrderLineDTO beerOrderLineDTO = BeerOrderLineDTO.builder()
            .orderQuantity(0)  // This should violate the @Min constraint
            .build();

        Set<ConstraintViolation<BeerOrderLineDTO>> violations = validator.validate(beerOrderLineDTO);
        assertEquals(1, violations.size());
        assertEquals("Quantity On Hand must be greater than 0", violations.iterator().next().getMessage());
    }

    @Test
    void testBeerOrderLineDTOBuilder() {
        UUID id = UUID.randomUUID();
        BeerDTO beer = new BeerDTO();
        Timestamp now = Timestamp.from(Instant.now());

        BeerOrderLineDTO beerOrderLineDTO = BeerOrderLineDTO.builder()
            .id(id)
            .version(1L)
            .orderQuantity(10)
            .quantityAllocated(5)
            .orderLineStatus(BeerOrderLineStatus.NEW)
            .beer(beer)
            .createdDate(now)
            .updateDate(now)
            .build();

        assertNotNull(beerOrderLineDTO);
        assertEquals(id, beerOrderLineDTO.getId());
        assertEquals(1L, beerOrderLineDTO.getVersion());
        assertEquals(10, beerOrderLineDTO.getOrderQuantity());
        assertEquals(5, beerOrderLineDTO.getQuantityAllocated());
        assertEquals(BeerOrderLineStatus.NEW, beerOrderLineDTO.getOrderLineStatus());
        assertEquals(beer, beerOrderLineDTO.getBeer());
        assertEquals(now, beerOrderLineDTO.getCreatedDate());
        assertEquals(now, beerOrderLineDTO.getUpdateDate());
    }
    

}
