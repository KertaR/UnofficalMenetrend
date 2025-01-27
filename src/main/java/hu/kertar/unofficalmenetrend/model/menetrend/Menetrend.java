package hu.kertar.unofficalmenetrend.model.menetrend;

import lombok.Data;

@Data
public class Menetrend {
    private String departure;
    private String arrival;
    private String departureTime;
    private String arrivalTime;
    private String transfers;
    private String totalDuration;

    public String getDeparture() {
        return departure;
    }

    public String getArrival() {
        return arrival;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getTransfers() {
        return transfers;
    }

    public String getTotalDuration() {
        return totalDuration;
    }
}
