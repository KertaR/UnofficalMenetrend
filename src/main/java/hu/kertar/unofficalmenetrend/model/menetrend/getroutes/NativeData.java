package hu.kertar.unofficalmenetrend.model.menetrend.getroutes;

import com.google.gson.JsonObject;
import lombok.Getter;

@Getter
public class NativeData {
    private final String TransportMode;

    public NativeData(JsonObject jsonObject) {
        this.TransportMode = jsonObject.get("TransportMode").getAsString();
    }
}
