package emailclient.network;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Request implements Serializable {

    public enum Type {
        SEND_MESSAGE,
        GET_MESSAGES,
        FILTER_MESSAGES
    }

    private final Type type;
    private final Map<String, Object> data = new HashMap<>();

    public Request(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void put(String key, Object value) {
        data.put(key, value);
    }

    public Object get(String key) {
        return data.get(key);
    }
}
