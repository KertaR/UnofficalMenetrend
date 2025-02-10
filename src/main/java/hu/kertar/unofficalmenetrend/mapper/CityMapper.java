package hu.kertar.unofficalmenetrend.mapper;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Service
public class CityMapper {

    private static final Map<String, Integer> cityToSettlementIdMap = new HashMap<>();

    @PostConstruct
    public void init() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(getClass().getResourceAsStream("/static/cities.csv"))))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    try {
                        int settlementId = Integer.parseInt(parts[0]);
                        String cityName = parts[1];
                        cityToSettlementIdMap.put(cityName, settlementId);
                    } catch (NumberFormatException e) {
                        System.err.println("Hibás formátumú sor: " + line);
                    }
                }
            }
        }
    }

    public static int getSettlementIdByCityName(String cityName) {
        return cityToSettlementIdMap.getOrDefault(cityName, 0);
    }

    public static List<String> getAllCities() {
        Set<Map.Entry<String, Integer>> entrySet = cityToSettlementIdMap.entrySet();
        List<String> cities = new ArrayList<>(entrySet.size());
        for (Map.Entry<String, Integer> entry : entrySet) {
            String cityName = entry.getKey();
            cities.add(cityName);
        }
        return cities;
    }
}
