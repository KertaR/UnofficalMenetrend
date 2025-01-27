package hu.kertar.unofficalmenetrend.service;

import com.google.gson.*;
import hu.kertar.unofficalmenetrend.model.menetrend.Menetrend;
import hu.kertar.unofficalmenetrend.model.menetrend.MenetrendMapper;
import hu.kertar.unofficalmenetrend.model.menetrend.caller.GetRoutes;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class MenetrendService {

    private static final String MENETRENDEK_HU_URL = "https://menetrendek.hu/menetrend/newinterface/index.php";

    public List<Menetrend> getMenetrendek(String kezdoAllomas, String vegAllomas) {

        int kezdoAllomasSettlementId = CityMapper.getSettlementIdByCityName(kezdoAllomas);
        int vegAllomasSettlementId = CityMapper.getSettlementIdByCityName(vegAllomas);

        GetRoutes payload = new GetRoutes();
        payload.getParams().setDatum("2025-01-27");
        payload.getParams().setHonnan_ls_id(0);
        payload.getParams().setHonnan_settlement_id(kezdoAllomasSettlementId);
        payload.getParams().setHour("1");
        payload.getParams().setHova_ls_id(0);
        payload.getParams().setHova_settlement_id(vegAllomasSettlementId);
        payload.getParams().setMin("27");
        payload.getParams().setNaptipus(0);
        payload.getParams().setPreferencia("0");
        payload.getParams().setVar("0");

        try {
            String jsonPayload = new Gson().toJson(payload);
            HttpURLConnection connection = HTTPService.createHttpConnection(MENETRENDEK_HU_URL, "POST", jsonPayload);

            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Hiba történt a szerverrel való kommunikáció során. Hibakód: " + responseCode);
            }

            String responseBody = HTTPService.readResponseBody(connection);
            JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
            if (jsonObject.get("status") != null && Objects.equals(jsonObject.get("status").getAsString(), "success")) {
                JsonObject temp = jsonObject.get("results").getAsJsonObject().get("talalatok").getAsJsonObject();
                List<Menetrend> menetrendek = new ArrayList<>();
                for (String key : temp.keySet()) {
                    JsonElement jsonElement = temp.get(key);
                    Menetrend menetrend = MenetrendMapper.mapToMenetrend(jsonElement.getAsJsonObject());
                    menetrendek.add(menetrend);
                }
                return menetrendek;
            }

            return new ArrayList<>();
        } catch (Exception e) {
            System.err.println("Hiba: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
