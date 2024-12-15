package ch.guru.springframework.spring6restmvcapi.dto;

import ch.guru.springframework.spring6restmvcapi.dto.create.BeerOrderShipmentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeerOrderDTO {
    private UUID id;
    private Long version;

    private BigDecimal paymentAmount;

    private String customerRef;

    private CustomerDTO customer;

    private Set<BeerOrderLineDTO> beerOrderLines;

    private BeerOrderShipmentDTO beerOrderShipment;

    private Timestamp createdDate;
    private Timestamp updateDate;
}
