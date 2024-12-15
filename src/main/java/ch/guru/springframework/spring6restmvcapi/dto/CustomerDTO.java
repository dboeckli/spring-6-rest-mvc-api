package ch.guru.springframework.spring6restmvcapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    private UUID id;

    private String version;

    private String name;

    private String email;

    private LocalDateTime createdDate;

    private LocalDateTime updateDate;

}
