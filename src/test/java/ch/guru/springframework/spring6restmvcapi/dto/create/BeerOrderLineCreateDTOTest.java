package ch.guru.springframework.spring6restmvcapi.dto.create;

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

class BeerOrderLineCreateDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        Locale.setDefault(Locale.US);
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void testValidBeerOrderLineCreateDTO() {
        BeerOrderLineCreateDTO dto = BeerOrderLineCreateDTO.builder()
            .beerId(UUID.randomUUID())
            .orderQuantity(10)
            .build();

        Set<ConstraintViolation<BeerOrderLineCreateDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testInvalidBeerOrderLineCreateDTO_NullBeerId() {
        BeerOrderLineCreateDTO dto = BeerOrderLineCreateDTO.builder()
            .beerId(null)
            .orderQuantity(10)
            .build();

        Set<ConstraintViolation<BeerOrderLineCreateDTO>> violations = validator.validate(dto);
        assertEquals(1, violations.size());
        assertEquals("must not be null", violations.iterator().next().getMessage());
    }

    @Test
    void testInvalidBeerOrderLineCreateDTO_InvalidOrderQuantity() {
        BeerOrderLineCreateDTO dto = BeerOrderLineCreateDTO.builder()
            .beerId(UUID.randomUUID())
            .orderQuantity(0)
            .build();

        Set<ConstraintViolation<BeerOrderLineCreateDTO>> violations = validator.validate(dto);
        assertEquals(1, violations.size());
        assertEquals("Quantity On Hand must be greater than 0", violations.iterator().next().getMessage());
    }

    @Test
    void testBeerOrderLineCreateDTOBuilder() {
        UUID beerId = UUID.randomUUID();
        int orderQuantity = 5;

        BeerOrderLineCreateDTO dto = BeerOrderLineCreateDTO.builder()
            .beerId(beerId)
            .orderQuantity(orderQuantity)
            .build();

        assertNotNull(dto);
        assertEquals(beerId, dto.getBeerId());
        assertEquals(orderQuantity, dto.getOrderQuantity());
    }
}
