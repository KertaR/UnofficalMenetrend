package hu.kertar.unofficalmenetrend.model.menetrend.getroutes;

import com.google.gson.JsonObject;

import lombok.Getter;

@Getter
public class Debug {
    private final int Duration;

    public Debug( JsonObject jsonObject ) {
        this.Duration = jsonObject.get("Duration").getAsInt();
    }
}
