package hu.kertar.unofficalmenetrend.model.menetrend.getroutes;

import com.google.gson.JsonObject;

import lombok.Getter;

@Getter
public class JaratInfo {
    private final int prebuy;
    private final int alacsonypadlos;
    private final int network;
    private final int nagysebessegu;
    private final String jelleg;
    private final int emeltszintu;
    private final String vonalnev;
    private final int wifi;
    private final int internet;
    private final int internetes_jegy;
    private final int terelout;
    private final String vonalelnevezes;
    private final String remark;
    //private final News news;
    private final String CountyPass;
    private final int distance;
    private final int fare;
    private final int no_discountable_fare;
    private final int additional_ticket_price;
    private final int seat_ticket_price;
    private final String train_cat;
    private final int fare_50_percent;
    private final int fare_90_percent;
    private final int dcLsId;
    private final Boolean bube_accepted;

    public JaratInfo( JsonObject jsonObject ) {
        this.prebuy = jsonObject.get( "prebuy" ).getAsInt();
        this.alacsonypadlos = jsonObject.get( "alacsonypadlos" ).getAsInt();
        this.network = jsonObject.get( "network" ).getAsInt();
        this.nagysebessegu = jsonObject.get( "nagysebessegu" ).getAsInt();
        this.jelleg = jsonObject.get( "jelleg" ).getAsString();
        this.emeltszintu = jsonObject.get( "emeltszintu" ).getAsInt();
        this.vonalnev = jsonObject.get( "vonalnev" ).getAsString();
        this.wifi = jsonObject.get( "wifi" ).getAsInt();
        this.internet = jsonObject.get( "internet" ).getAsInt();
        this.internetes_jegy = jsonObject.get( "internetes_jegy" ).getAsInt();
        this.terelout = jsonObject.get( "terelout" ).getAsInt();
        this.vonalelnevezes = jsonObject.get( "vonalelnevezes" ).getAsString();
        this.remark = jsonObject.get( "remark" ).getAsString();
        //this.news = new News(jsonObject.get("news").getAsJsonObject());
        this.CountyPass = jsonObject.get( "CountyPass" ).getAsString();
        this.distance = jsonObject.get( "distance" ).getAsInt();
        this.fare = jsonObject.get( "fare" ).getAsInt();
        this.no_discountable_fare = jsonObject.get( "no_discountable_fare" ).getAsInt();
        this.additional_ticket_price = jsonObject.get( "additional_ticket_price" ).getAsInt();
        this.seat_ticket_price = jsonObject.get( "seat_ticket_price" ).getAsInt();
        this.train_cat = jsonObject.get( "train_cat" ).getAsString();
        this.fare_50_percent = jsonObject.get( "fare_50_percent" ).getAsInt();
        this.fare_90_percent = jsonObject.get( "fare_90_percent" ).getAsInt();
        this.dcLsId = jsonObject.get( "dcLsId" ).getAsInt();
        this.bube_accepted = jsonObject.get( "bube_accepted" ).getAsBoolean();
    }
}
