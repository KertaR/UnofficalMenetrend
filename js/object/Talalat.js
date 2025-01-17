import {NativeData} from "./NativeData.js";
import {AtszallasInfo} from "./AtszallasInfo.js";
import {JaratInfo} from "./JaratInfo.js";

class Talalat {
    constructor(data) {
        this.ind_prefix = data.ind_prefix;
        this.indulasi_hely = data.indulasi_hely;
        this.ind_kulterulet = data.ind_kulterulet;
        this.departureCity = data.departureCity;
        this.departureStation = data.departureStation;
        this.departureLs = data.departureLs;
        this.erk_prefix = data.erk_prefix;
        this.erkezesi_hely = data.erkezesi_hely;
        this.erk_kulterulet = data.erk_kulterulet;
        this.arrivalCity = data.arrivalCity;
        this.arrivalStation = data.arrivalStation;
        this.arrivalLs = data.arrivalLs;
        this.indulasi_ido = data.indulasi_ido;
        this.erkezesi_ido = data.erkezesi_ido;
        this.atszallasok_szama = data.atszallasok_szama;
        this.osszido = data.osszido;
        this.indulasi_hely_info = data.indulasi_hely_info;
        this.jaratinfok = {};
        for (const key in data.jaratinfok) {
            this.jaratinfok[key] = new JaratInfo(data.jaratinfok[key]);
        }
        this.atszallasinfok = {};
        for (const key in data.atszallasinfok) {
            this.atszallasinfok[key] = new AtszallasInfo(data.atszallasinfok[key]);
        }
        this.explanations = data.explanations;
        this.totalDistance = data.totalDistance;
        this.totalFare = data.totalFare;
        this.totalFare50 = data.totalFare50;
        this.totalFare90 = data.totalFare90;
        this.totalAdditionalTicketPrice = data.totalAdditionalTicketPrice;
        this.eTicketAvailable = data.eTicketAvailable;
        this.riskyTransfer = data.riskyTransfer;
        this.kifejtes_postjson = data.kifejtes_postjson;
        this.nativeData = data.nativeData.map(item => new NativeData(item));
        this.ossztav = data.ossztav;
        this.talalat_kozlekedik = data.talalat_kozlekedik;
    }
}

export { Talalat };