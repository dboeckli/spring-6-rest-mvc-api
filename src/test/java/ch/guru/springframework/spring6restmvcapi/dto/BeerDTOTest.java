package ch.guru.springframework.spring6restmvcapi.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BeerDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void testValidBeerDTO() {
        BeerDTO beerDTO = BeerDTO.builder()
            .beerName("Test Beer")
            .beerStyle(BeerStyle.ALE)
            .upc("123456")
            .price(new BigDecimal("10.99"))
            .build();

        Set<ConstraintViolation<BeerDTO>> violations = validator.validate(beerDTO);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testInvalidBeerDTO() {
        BeerDTO beerDTO = BeerDTO.builder()
            .beerName("")
            .beerStyle(null)
            .upc("")
            .price(null)
            .build();

        Set<ConstraintViolation<BeerDTO>> violations = validator.validate(beerDTO);
        assertEquals(4, violations.size());
    }

    @Test
    void testBeerDTOBuilder() {
        BeerDTO beerDTO = BeerDTO.builder()
            .beerName("Test Beer")
            .beerStyle(BeerStyle.LAGER)
            .upc("123456")
            .price(new BigDecimal("9.99"))
            .quantityOnHand(100)
            .build();

        assertNotNull(beerDTO);
        assertEquals("Test Beer", beerDTO.getBeerName());
        assertEquals(BeerStyle.LAGER, beerDTO.getBeerStyle());
        assertEquals("123456", beerDTO.getUpc());
        assertEquals(new BigDecimal("9.99"), beerDTO.getPrice());
        assertEquals(100, beerDTO.getQuantityOnHand());
    }
    
}
