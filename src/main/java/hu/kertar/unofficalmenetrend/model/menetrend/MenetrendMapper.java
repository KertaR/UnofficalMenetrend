package hu.kertar.unofficalmenetrend.model.menetrend;

import com.google.gson.JsonObject;
import lombok.Getter;

@Getter
public class MenetrendMapper {

    public static Menetrend mapToMenetrend(JsonObject json) {

        Menetrend menetrend = new Menetrend();
        menetrend.setDeparture( json.get("indulasi_hely").getAsString() );
        menetrend.setArrival( json.get("erkezesi_hely").getAsString() );
        menetrend.setDepartureTime( json.get("indulasi_ido").getAsString() );
        menetrend.setArrivalTime( json.get("erkezesi_ido").getAsString() );
        menetrend.setTransfers( json.get("atszallasok_szama").getAsString() );
        menetrend.setTotalDuration( json.get("osszido").getAsString() );
        return menetrend;
    }
}
