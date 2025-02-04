package hu.kertar.unofficalmenetrend.service.getroutes;

import com.google.gson.JsonObject;
import hu.kertar.unofficalmenetrend.model.menetrend.getroutes.Results;
import lombok.Getter;

@Getter
public class GetRoutesResponse {
    private final String status;
    private final Results results;

    public GetRoutesResponse(JsonObject jsonObject)
    {
        this.status = jsonObject.get("status").getAsString();
        this.results = new Results(jsonObject.get("results").getAsJsonObject());
    }
}
