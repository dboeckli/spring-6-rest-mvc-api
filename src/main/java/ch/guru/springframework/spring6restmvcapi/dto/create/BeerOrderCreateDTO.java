package ch.guru.springframework.spring6restmvcapi.dto.create;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BeerOrderCreateDTO {

    private String customerRef;

    @NotNull
    private UUID customerId;

    private Set<BeerOrderLineCreateDTO> beerOrderLines;
    
}
