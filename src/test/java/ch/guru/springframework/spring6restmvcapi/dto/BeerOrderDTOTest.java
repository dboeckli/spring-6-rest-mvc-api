package ch.guru.springframework.spring6restmvcapi.dto;

import ch.guru.springframework.spring6restmvcapi.dto.create.BeerOrderShipmentDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BeerOrderDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void testValidBeerOrderDTO() {
        BeerOrderDTO beerOrderDTO = BeerOrderDTO.builder()
            .id(UUID.randomUUID())
            .version(1L)
            .customerRef("CUST123")
            .customer(new CustomerDTO())
            .beerOrderLines(new HashSet<>())
            .beerOrderShipment(new BeerOrderShipmentDTO())
            .paymentAmount(new BigDecimal("100.00"))
            .build();

        Set<ConstraintViolation<BeerOrderDTO>> violations = validator.validate(beerOrderDTO);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testBeerOrderDTOBuilder() {
        UUID orderId = UUID.randomUUID();
        CustomerDTO customer = new CustomerDTO();
        Set<BeerOrderLineDTO> orderLines = new HashSet<>();
        BeerOrderShipmentDTO shipment = new BeerOrderShipmentDTO();

        BeerOrderDTO beerOrderDTO = BeerOrderDTO.builder()
            .id(orderId)
            .version(1L)
            .customerRef("CUST123")
            .customer(customer)
            .beerOrderLines(orderLines)
            .beerOrderShipment(shipment)
            .paymentAmount(new BigDecimal("100.00"))
            .build();

        assertNotNull(beerOrderDTO);
        assertEquals(orderId, beerOrderDTO.getId());
        assertEquals(1L, beerOrderDTO.getVersion());
        assertEquals("CUST123", beerOrderDTO.getCustomerRef());
        assertEquals(customer, beerOrderDTO.getCustomer());
        assertEquals(orderLines, beerOrderDTO.getBeerOrderLines());
        assertEquals(shipment, beerOrderDTO.getBeerOrderShipment());
        assertEquals(new BigDecimal("100.00"), beerOrderDTO.getPaymentAmount());
    }

}
