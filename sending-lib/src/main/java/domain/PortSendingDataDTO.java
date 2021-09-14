package domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class PortSendingDataDTO extends  DeviceSendingDataDTO{
    private String portName;
}
