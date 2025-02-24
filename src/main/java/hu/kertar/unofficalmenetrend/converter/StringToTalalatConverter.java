package hu.kertar.unofficalmenetrend.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hu.kertar.api.getroutes.Talalat;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToTalalatConverter implements Converter<String, Talalat> {

    private final ObjectMapper objectMapper; // Inject ObjectMapper

    public StringToTalalatConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Talalat convert(String source) {
        if (source == null || source.isEmpty()) {
            return null;
        }
        try {
            return objectMapper.readValue(source, Talalat.class); // Convert from JSON string
        } catch (JsonProcessingException e) {
            // Handle error appropriately
            return null; // Or throw a custom exception
        }
    }
}
