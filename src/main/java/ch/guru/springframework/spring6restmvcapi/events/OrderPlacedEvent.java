package ch.guru.springframework.spring6restmvcapi.events;

import ch.guru.springframework.spring6restmvcapi.dto.BeerOrderDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderPlacedEvent {

    private BeerOrderDTO beerOrderDTO;
}
