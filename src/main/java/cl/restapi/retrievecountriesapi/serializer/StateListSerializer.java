package cl.restapi.retrievecountriesapi.serializer;

import cl.restapi.retrievecountriesapi.models.State;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

public class StateListSerializer extends JsonSerializer<List<State>> {
    @Override
    public void serialize(List<State> states, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartArray();
        for (State state : states) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("name", state.getName());
            jsonGenerator.writeStringField("code", state.getCode());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
    }
}
