package json_converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class DTOToMessageJSONConverter<T> {
    private final ObjectMapper objectMapper;

    public DTOToMessageJSONConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @SneakyThrows
    public <T> T deserialize(String msg, Class<T> dtoType) {
        return objectMapper.readValue(msg, dtoType);
    }

    @SneakyThrows
    public String serialize(T object) {
        return objectMapper.writeValueAsString(object);
    }
}
