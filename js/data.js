import { MenetrendTalalat } from './object/MenetrendTalalat.js';

let data = []; // Az adatok tárolása
let citiesData = [];

async function loadCities() {
    try {
        const response = await fetch("resources/cities.txt");
        const textData = await response.text();

        citiesData = textData
            .split("\n")
            .map(line => line.trim())
            .filter(line => line)
            .map(line => {
                const [id, ...nameParts] = line.split(" ");
                return { id: parseInt(id, 10), name: nameParts.join(" ") };
            });

    } catch (error) {
        console.error("Hiba történt a városok betöltésekor:", error);
    }
}

async function loadData(departureCityId, arrivalCityId) {
    const departureName = citiesData.find(city => city.id === departureCityId)?.name || "";
    const arrivalName = citiesData.find(city => city.id === arrivalCityId)?.name || "";

    const today = new Date();
    const year = today.getFullYear();
    const month = String(today.getMonth() + 1).padStart(2, '0'); // Month is 0-indexed
    const day = String(today.getDate()).padStart(2, '0');
    const dateString = `${year}-${month}-${day}`;

    const payload = {
        func: "getRoutes",
        params: {
            datum: dateString,
            erk_stype: "megallo",
            helyi: "No",
            honnan: departureName,
            honnan_ls_id: 0,
            honnan_settlement_id: departureCityId,
            honnan_site_code: "",
            hour: "23",
            hova: arrivalName,
            hova_ls_id: 0,
            hova_settlement_id: arrivalCityId,
            hova_site_code: "",
            ind_stype: "megallo",
            keresztul_stype: "megallo",
            maxatszallas: "5",
            maxvar: "240",
            maxwalk: "1000",
            min: "00",
            napszak: 0,
            naptipus: 0,
            odavissza: 0,
            preferencia: "0",
            rendezes: "1",
            submitted: 1,
            talalatok: 1,
            target: 0,
            utirany: "oda",
            var: "0",
        },
    };

    try {
        const response = await fetch("https://menetrendek.hu/menetrend/newinterface/index.php", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(payload),
        });

        if (!response.ok) {
            throw new Error("Hiba történt a szerverrel való kommunikáció során.");
        }

        const jsonData = await response.json();
        const menetrendTalalat = new MenetrendTalalat(jsonData);

        if (menetrendTalalat.status === "success") {
            data = Object.values(menetrendTalalat.results.talalatok).map(talalat => {
                let transportMode = "other";
                if (talalat.nativeData && talalat.nativeData[0]?.TransportMode?.includes("vehicles.train")) {
                    transportMode = "vehicles.train";
                } else if (talalat.nativeData && talalat.nativeData[0]?.TransportMode?.includes("vehicles.bus")) {
                    transportMode = "vehicles.bus";
                }

                return {
                    departure: `${talalat.departureCity}, ${talalat.departureStation}`,
                    arrival: `${talalat.arrivalCity}, ${talalat.arrivalStation}`,
                    departureTime: talalat.indulasi_ido,
                    arrivalTime: talalat.erkezesi_ido,
                    transfers: talalat.atszallasok_szama,
                    totalDuration: talalat.osszido,
                    transportMode,
                };
            });

            // Itt frissítheted a táblázatot, vagy más műveletet végezhetsz az adatokkal
            // Például: updateTable(data);
        } else {
            console.error("Nem sikerült a válasz feldolgozása:", jsonData);
        }
    } catch (error) {
        console.error("Hiba történt az adatok betöltésekor:", error);
    }
}

export { loadCities, loadData, citiesData, data };
