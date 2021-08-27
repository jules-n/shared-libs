package dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortValueDTO {
    private UUID deviceId;
    private String tenantId;
    private String portName;
    private Object portValue;
    private LocalDateTime receivingTime;
}
