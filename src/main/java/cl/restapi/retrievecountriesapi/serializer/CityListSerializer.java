package cl.restapi.retrievecountriesapi.serializer;

import cl.restapi.retrievecountriesapi.models.City;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

public class CityListSerializer extends JsonSerializer<List<City>> {
    @Override
    public void serialize(List<City> cities, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartArray();
        for (City city : cities) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("name", city.getName());
            jsonGenerator.writeStringField("state", city.getStateCode());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
    }
}
