package ch.guru.springframework.spring6restmvcapi.events;

import ch.guru.springframework.spring6restmvcapi.dto.BeerOrderLineDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DrinkPreparedEvent {

    private BeerOrderLineDTO beerOrderLineDTO;
    
}
