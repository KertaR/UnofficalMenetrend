package hu.kertar.unofficalmenetrend.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hu.kertar.api.getroutes.Talalat;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TalalatToStringConverter implements Converter<Talalat, String> {

    private final ObjectMapper objectMapper; // Inject ObjectMapper

    public TalalatToStringConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String convert(Talalat source) {
        if (source == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(source); // Convert to JSON string
        } catch (JsonProcessingException e) {
            // Handle error appropriately (e.g., log, throw exception)
            return null; // Or throw a custom exception
        }
    }
}