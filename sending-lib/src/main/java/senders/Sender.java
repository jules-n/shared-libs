package senders;

import java.util.Map;

public interface Sender {
    void send(Map<String, Object> parameters, Object value);
}
