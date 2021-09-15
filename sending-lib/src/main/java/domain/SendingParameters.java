package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendingParameters implements Serializable {
    private String sendingClassName;
    private Map<String, Object> parameters;
}
