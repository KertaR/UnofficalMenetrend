package hu.kertar.unofficalmenetrend.service;

import com.google.gson.*;
import hu.kertar.unofficalmenetrend.model.menetrend.getroutes.Results;
import hu.kertar.unofficalmenetrend.service.getroutes.GetRoutesRequest;
import hu.kertar.unofficalmenetrend.service.getroutes.GetRoutesResponse;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class MenetrendService {

    private static final String MENETRENDEK_HU_URL = "https://menetrendek.hu/menetrend/newinterface/index.php";

    public Results getMenetrendek(String kezdoAllomas, String vegAllomas) {

        int kezdoAllomasSettlementId = CityMapper.getSettlementIdByCityName(kezdoAllomas);
        int vegAllomasSettlementId = CityMapper.getSettlementIdByCityName(vegAllomas);

        GetRoutesRequest request = new GetRoutesRequest();
        request.getParams().setDatum("2025-01-27");
        request.getParams().setHonnan_ls_id(0);
        request.getParams().setHonnan_settlement_id(kezdoAllomasSettlementId);
        request.getParams().setHour("1");
        request.getParams().setHova_ls_id(0);
        request.getParams().setHova_settlement_id(vegAllomasSettlementId);
        request.getParams().setMin("27");
        request.getParams().setNaptipus(0);
        request.getParams().setPreferencia("0");
        request.getParams().setVar("0");

        try {
            String jsonPayload = new Gson().toJson(request);
            HttpURLConnection connection = HTTPService.createHttpConnection(MENETRENDEK_HU_URL, "POST", jsonPayload);

            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Hiba történt a szerverrel való kommunikáció során. Hibakód: " + responseCode);
            }

            String responseBody = HTTPService.readResponseBody(connection);
            JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
            GetRoutesResponse response = new GetRoutesResponse(jsonObject);
            if (response.getStatus().equals("success")) {
                return response.getResults();
            }

            return null;
        } catch (Exception e) {
            System.err.println("Hiba: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
