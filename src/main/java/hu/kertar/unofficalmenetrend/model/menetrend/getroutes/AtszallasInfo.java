package hu.kertar.unofficalmenetrend.model.menetrend.getroutes;

import com.google.gson.JsonObject;
import lombok.Getter;

@Getter
public class AtszallasInfo {
    private final String atszallasinfo;
    private final String atszallohely;
    private final String atszallaskorlatozas;

    public AtszallasInfo(JsonObject jsonObject) {
        this.atszallasinfo = jsonObject.get("atszallasinfo").getAsString();
        this.atszallohely = jsonObject.get("atszallohely").getAsString();
        this.atszallaskorlatozas = jsonObject.get("atszallaskorlatozas").getAsString();
    }
}
