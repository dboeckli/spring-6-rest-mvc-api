package ch.guru.springframework.spring6restmvcapi.dto.update;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BeerOrderShipmentUpdateDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        Locale.setDefault(Locale.US);
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void testValidBeerOrderShipmentUpdateDTO() {
        BeerOrderShipmentUpdateDTO dto = BeerOrderShipmentUpdateDTO.builder()
            .trackingNumber("TRACK123456")
            .build();

        Set<ConstraintViolation<BeerOrderShipmentUpdateDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testInvalidBeerOrderShipmentUpdateDTO_BlankTrackingNumber() {
        BeerOrderShipmentUpdateDTO dto = BeerOrderShipmentUpdateDTO.builder()
            .trackingNumber("")
            .build();

        Set<ConstraintViolation<BeerOrderShipmentUpdateDTO>> violations = validator.validate(dto);
        assertEquals(1, violations.size());
        assertEquals("must not be blank", violations.iterator().next().getMessage());
    }

    @Test
    void testInvalidBeerOrderShipmentUpdateDTO_NullTrackingNumber() {
        BeerOrderShipmentUpdateDTO dto = BeerOrderShipmentUpdateDTO.builder()
            .trackingNumber(null)
            .build();

        Set<ConstraintViolation<BeerOrderShipmentUpdateDTO>> violations = validator.validate(dto);
        assertEquals(1, violations.size());
        assertEquals("must not be blank", violations.iterator().next().getMessage());
    }

    @Test
    void testBeerOrderShipmentUpdateDTOBuilder() {
        String trackingNumber = "TRACK987654";

        BeerOrderShipmentUpdateDTO dto = BeerOrderShipmentUpdateDTO.builder()
            .trackingNumber(trackingNumber)
            .build();

        assertNotNull(dto);
        assertEquals(trackingNumber, dto.getTrackingNumber());
    }    
}
