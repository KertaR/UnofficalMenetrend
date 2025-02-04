package hu.kertar.unofficalmenetrend.model.menetrend.getroutes;

import com.google.gson.JsonObject;
import lombok.Getter;

@Getter
public class News {
    private final String Title;
    private final String Url;

    public News(JsonObject jsonObject) {
        this.Title = jsonObject.get("title").getAsString();
        this.Url = jsonObject.get("url").getAsString();
    }
}
