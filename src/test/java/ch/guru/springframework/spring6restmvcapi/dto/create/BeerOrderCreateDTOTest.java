package ch.guru.springframework.spring6restmvcapi.dto.create;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BeerOrderCreateDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        Locale.setDefault(Locale.US);
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void testValidBeerOrderCreateDTO() {
        BeerOrderCreateDTO beerOrderCreateDTO = BeerOrderCreateDTO.builder()
            .customerRef("REF123")
            .customerId(UUID.randomUUID())
            .beerOrderLines(new HashSet<>())
            .build();

        Set<ConstraintViolation<BeerOrderCreateDTO>> violations = validator.validate(beerOrderCreateDTO);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testInvalidBeerOrderCreateDTO() {
        BeerOrderCreateDTO beerOrderCreateDTO = BeerOrderCreateDTO.builder()
            .customerRef("REF123")
            .customerId(null)  // This should violate the @NotNull constraint
            .build();

        Set<ConstraintViolation<BeerOrderCreateDTO>> violations = validator.validate(beerOrderCreateDTO);
        assertEquals(1, violations.size());
        assertEquals("must not be null", violations.iterator().next().getMessage());
    }

    @Test
    void testBeerOrderCreateDTOBuilder() {
        String customerRef = "REF123";
        UUID customerId = UUID.randomUUID();
        Set<BeerOrderLineCreateDTO> beerOrderLines = new HashSet<>();
        beerOrderLines.add(new BeerOrderLineCreateDTO());

        BeerOrderCreateDTO beerOrderCreateDTO = BeerOrderCreateDTO.builder()
            .customerRef(customerRef)
            .customerId(customerId)
            .beerOrderLines(beerOrderLines)
            .build();

        assertNotNull(beerOrderCreateDTO);
        assertEquals(customerRef, beerOrderCreateDTO.getCustomerRef());
        assertEquals(customerId, beerOrderCreateDTO.getCustomerId());
        assertEquals(beerOrderLines, beerOrderCreateDTO.getBeerOrderLines());
    }
}
