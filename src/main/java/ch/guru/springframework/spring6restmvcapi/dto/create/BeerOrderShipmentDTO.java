package ch.guru.springframework.spring6restmvcapi.dto.create;

import jakarta.validation.constraints.NotBlank;
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
public class BeerOrderShipmentDTO {
    private UUID id;
    private Long version;

    @NotBlank
    private String trackingNumber;

    private Timestamp createdDate;
    private Timestamp lastModifiedDate;
    
}
