package ch.guru.springframework.spring6restmvcapi.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeerOrderLineDTO {
    private UUID id;
    private Long version;

    @Min(value = 1, message = "Quantity On Hand must be greater than 0")
    private Integer orderQuantity;

    private Integer quantityAllocated;

    private BeerOrderLineStatus orderLineStatus;

    private BeerDTO beer;

    private Timestamp createdDate;
    private Timestamp updateDate;
}
