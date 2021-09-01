package dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PipelineDevicesDTO {
    private String pipelineId;
    private List<String> deviceIds;
    private String portName;
}
