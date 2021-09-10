package dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultDTO {
    private UUID pipelineId;
    private String tenantId;
    private UUID deviceId;
    private String portName;
    private Object value;
}
