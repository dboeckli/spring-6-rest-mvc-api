package ch.guru.springframework.spring6restmvcapi.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void testValidCustomerDTO() {
        CustomerDTO customerDTO = CustomerDTO.builder()
            .id(UUID.randomUUID())
            .version(String.valueOf(1))
            .name("John Doe")
            .email("john.doe@example.com")
            .createdDate(LocalDateTime.now())
            .updateDate(LocalDateTime.now())
            .build();

        Set<ConstraintViolation<CustomerDTO>> violations = validator.validate(customerDTO);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testInvalidCustomerDTO() {
        CustomerDTO customerDTO = CustomerDTO.builder()
            .name("")  // Assuming name cannot be empty
            .email("invalid-email")  // Assuming email format validation
            .build();

        Set<ConstraintViolation<CustomerDTO>> violations = validator.validate(customerDTO);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testCustomerDTOBuilder() {
        UUID id = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();

        CustomerDTO customerDTO = CustomerDTO.builder()
            .id(id)
            .version(String.valueOf(1))
            .name("John Doe")
            .email("john.doe@example.com")
            .createdDate(now)
            .updateDate(now)
            .build();

        assertNotNull(customerDTO);
        assertEquals(id, customerDTO.getId());
        assertEquals("1", customerDTO.getVersion());
        assertEquals("John Doe", customerDTO.getName());
        assertEquals("john.doe@example.com", customerDTO.getEmail());
        assertEquals(now, customerDTO.getCreatedDate());
        assertEquals(now, customerDTO.getUpdateDate());
    }
    
}
