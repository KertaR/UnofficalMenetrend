package hu.kertar.unofficalmenetrend.model.menetrend.getroutes;

import com.google.gson.JsonObject;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;

@Getter
public class Results {
    private final String napkiiras;
    private final String date_got;
    private final int daytype_got;
    private final String apache_hostname;
    private final UsedParams used_params;
    private final List<Talalat> talalatok;

    public Results(JsonObject jsonObject)
    {
        this.napkiiras = jsonObject.get("napkiiras").getAsString();
        this.date_got = jsonObject.get("date_got").getAsString();
        this.daytype_got = jsonObject.get("daytype_got").getAsInt();
        this.apache_hostname = jsonObject.get("apache_hostname").getAsString();
        this.used_params = new UsedParams(jsonObject.get("used_params").getAsJsonObject());
        this.talalatok = jsonObject.get("talalatok").getAsJsonObject().entrySet().stream()
                .map(entry -> new Talalat(entry.getValue().getAsJsonObject()))
                .collect(Collectors.toList());

    }

}
