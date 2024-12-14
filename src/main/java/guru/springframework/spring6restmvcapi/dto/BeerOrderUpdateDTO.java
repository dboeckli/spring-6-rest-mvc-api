package guru.springframework.spring6restmvcapi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

/**
 * Created by jt, Spring Framework Guru.
 */
@Data
@Builder
public class BeerOrderUpdateDTO {
    private String customerRef;

    @NotNull
    private UUID customerId;

    private Set<BeerOrderLineUpdateDTO> beerOrderLines;

    private BeerOrderShipmentUpdateDTO beerOrderShipment;

    private BigDecimal paymentAmount;
}