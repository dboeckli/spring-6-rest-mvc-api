package ch.guru.springframework.spring6restmvcapi.dto.update;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BeerOrderLineUpdateDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        Locale.setDefault(Locale.US);
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void testValidBeerOrderLineUpdateDTO() {
        BeerOrderLineUpdateDTO dto = BeerOrderLineUpdateDTO.builder()
            .id(UUID.randomUUID())
            .beerId(UUID.randomUUID())
            .orderQuantity(10)
            .quantityAllocated(5)
            .build();

        Set<ConstraintViolation<BeerOrderLineUpdateDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testInvalidBeerOrderLineUpdateDTO_NullBeerId() {
        BeerOrderLineUpdateDTO dto = BeerOrderLineUpdateDTO.builder()
            .id(UUID.randomUUID())
            .beerId(null)
            .orderQuantity(10)
            .quantityAllocated(5)
            .build();

        Set<ConstraintViolation<BeerOrderLineUpdateDTO>> violations = validator.validate(dto);
        assertEquals(1, violations.size());
        assertEquals("must not be null", violations.iterator().next().getMessage());
    }

    @Test
    void testInvalidBeerOrderLineUpdateDTO_InvalidOrderQuantity() {
        BeerOrderLineUpdateDTO dto = BeerOrderLineUpdateDTO.builder()
            .id(UUID.randomUUID())
            .beerId(UUID.randomUUID())
            .orderQuantity(0)
            .quantityAllocated(5)
            .build();

        Set<ConstraintViolation<BeerOrderLineUpdateDTO>> violations = validator.validate(dto);
        assertEquals(1, violations.size());
        assertEquals("Quantity On Hand must be greater than 0", violations.iterator().next().getMessage());
    }

    @Test
    void testBeerOrderLineUpdateDTOBuilder() {
        UUID id = UUID.randomUUID();
        UUID beerId = UUID.randomUUID();
        int orderQuantity = 15;
        int quantityAllocated = 7;

        BeerOrderLineUpdateDTO dto = BeerOrderLineUpdateDTO.builder()
            .id(id)
            .beerId(beerId)
            .orderQuantity(orderQuantity)
            .quantityAllocated(quantityAllocated)
            .build();

        assertNotNull(dto);
        assertEquals(id, dto.getId());
        assertEquals(beerId, dto.getBeerId());
        assertEquals(orderQuantity, dto.getOrderQuantity());
        assertEquals(quantityAllocated, dto.getQuantityAllocated());
    }
}
