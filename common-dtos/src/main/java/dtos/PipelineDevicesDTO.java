package dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PipelineDevicesDTO {
    private UUID pipelineId;
    private List<UUID> deviceIds;
    private String portName;
}
