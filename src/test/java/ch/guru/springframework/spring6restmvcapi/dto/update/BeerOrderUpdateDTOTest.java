package ch.guru.springframework.spring6restmvcapi.dto.update;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BeerOrderUpdateDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        Locale.setDefault(Locale.US);
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void testValidBeerOrderUpdateDTO() {
        BeerOrderUpdateDTO dto = BeerOrderUpdateDTO.builder()
            .customerRef("REF123")
            .customerId(UUID.randomUUID())
            .beerOrderLines(new HashSet<>())
            .beerOrderShipment(new BeerOrderShipmentUpdateDTO())
            .paymentAmount(new BigDecimal("100.00"))
            .build();

        Set<ConstraintViolation<BeerOrderUpdateDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testInvalidBeerOrderUpdateDTO_NullCustomerId() {
        BeerOrderUpdateDTO dto = BeerOrderUpdateDTO.builder()
            .customerRef("REF123")
            .customerId(null)
            .build();

        Set<ConstraintViolation<BeerOrderUpdateDTO>> violations = validator.validate(dto);
        assertEquals(1, violations.size());
        assertEquals("must not be null", violations.iterator().next().getMessage());
    }

    @Test
    void testBeerOrderUpdateDTOBuilder() {
        String customerRef = "REF456";
        UUID customerId = UUID.randomUUID();
        Set<BeerOrderLineUpdateDTO> beerOrderLines = new HashSet<>();
        BeerOrderShipmentUpdateDTO shipment = new BeerOrderShipmentUpdateDTO();
        BigDecimal paymentAmount = new BigDecimal("150.00");

        BeerOrderUpdateDTO dto = BeerOrderUpdateDTO.builder()
            .customerRef(customerRef)
            .customerId(customerId)
            .beerOrderLines(beerOrderLines)
            .beerOrderShipment(shipment)
            .paymentAmount(paymentAmount)
            .build();

        assertNotNull(dto);
        assertEquals(customerRef, dto.getCustomerRef());
        assertEquals(customerId, dto.getCustomerId());
        assertEquals(beerOrderLines, dto.getBeerOrderLines());
        assertEquals(shipment, dto.getBeerOrderShipment());
        assertEquals(paymentAmount, dto.getPaymentAmount());
    }    
}
