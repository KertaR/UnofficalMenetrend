package hu.kertar.unofficalmenetrend.model.menetrend.getroutes;

import com.google.gson.JsonObject;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class Talalat {
    private final String ind_prefix;
    private final String indulasi_hely;
    private final Boolean ind_kulterulet;
    private final String departureCity;
    private final String departureStation;
    private final int departureLs;
    private final String erk_prefix;
    private final String erkezesi_hely;
    private final Boolean erk_kulterulet;
    private final String arrivalCity;
    private final String arrivalStation;
    private final int arrivalLs;
    private final String indulasi_ido;
    private final String erkezesi_ido;
    private final String atszallasok_szama;
    private final String osszido;
    private final int indulasi_hely_info;
    //private final List<JaratInfo> jaratinfok;
    private final List<AtszallasInfo> atszallasinfok;
    private final List<String> explanations;
    private final int totalDistance;
    private final int totalFare;
    private final int totalFare50;
    private final int totalFare90;
    private final int totalAdditionalTicketPrice;
    private final int eTicketAvailable;
    private final Boolean riskyTransfer;
    private final NativeData nativeData;

    public Talalat(JsonObject jsonObject) {
        this.ind_prefix = jsonObject.get("ind_prefix").getAsString();
        this.indulasi_hely = jsonObject.get("indulasi_hely").getAsString();
        this.ind_kulterulet = jsonObject.get("ind_kulterulet").getAsBoolean();
        this.departureCity = jsonObject.get("departureCity").getAsString();
        this.departureStation = jsonObject.get("departureStation").getAsString();
        this.departureLs = jsonObject.get("departureLs").getAsInt();
        this.erk_prefix = jsonObject.get("erk_prefix").getAsString();
        this.erkezesi_hely = jsonObject.get("erkezesi_hely").getAsString();
        this.erk_kulterulet = jsonObject.get("erk_kulterulet").getAsBoolean();
        this.arrivalCity = jsonObject.get("arrivalCity").getAsString();
        this.arrivalStation = jsonObject.get("arrivalStation").getAsString();
        this.arrivalLs = jsonObject.get("arrivalLs").getAsInt();
        this.indulasi_ido = jsonObject.get("indulasi_ido").getAsString();
        this.erkezesi_ido = jsonObject.get("erkezesi_ido").getAsString();
        this.atszallasok_szama = jsonObject.get("atszallasok_szama").getAsString();
        this.osszido = jsonObject.get("osszido").getAsString();
        this.indulasi_hely_info = jsonObject.get("indulasi_hely_info").getAsInt();

        //this.jaratinfok = jsonObject.get("jaratinfok").getAsJsonObject().entrySet().stream()
        //        .map(entry -> new JaratInfo(entry.getValue().getAsJsonObject()))
        //        .collect(Collectors.toList());

        this.atszallasinfok = jsonObject.get("atszallasinfok").getAsJsonObject().entrySet().stream()
                .map(entry -> new AtszallasInfo(entry.getValue().getAsJsonObject()))
                .collect(Collectors.toList());

        this.explanations = new ArrayList<>(); //TODO: Befejezni
        this.totalDistance = jsonObject.get("totalDistance").getAsInt();
        this.totalFare = jsonObject.get("totalFare").getAsInt();
        this.totalFare50 = jsonObject.get("totalFare50").getAsInt();
        this.totalFare90 = jsonObject.get("totalFare90").getAsInt();
        this.totalAdditionalTicketPrice = jsonObject.get("totalAdditionalTicketPrice").getAsInt();
        this.eTicketAvailable = jsonObject.get("eTicketAvailable").getAsInt();
        this.riskyTransfer = jsonObject.get("riskyTransfer").getAsBoolean();
        this.nativeData = new NativeData(jsonObject.get("nativeData").getAsJsonArray().get(0).getAsJsonObject());

    }
}
