package hu.kertar.unofficalmenetrend.model.menetrend.getroutes;

import com.google.gson.JsonObject;
import lombok.Getter;


@Getter
public class UsedParams {
    private final String mode;
    private final int SearchStartTime;

    public UsedParams(JsonObject jsonObject)
    {
        this.mode = jsonObject.get("mode").getAsString();
        this.SearchStartTime = jsonObject.get("SearchStartTime").getAsInt();

    }
}
