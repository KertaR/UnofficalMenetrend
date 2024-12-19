let data = []; // Az adatok tárolása
let citiesData = [];  // Városok adatai (id és név)

// Városok betöltése a szűrésválasztókhoz
async function loadCities() {
    try {
        const response = await fetch("resources/cities.txt");
        const textData = await response.text();

        // Város és settlement_id feldolgozása
        citiesData = textData
            .split("\n")
            .map(line => line.trim())
            .filter(line => line)
            .map(line => {
                const [id, ...nameParts] = line.split(" ");
                return { id: parseInt(id, 10), name: nameParts.join(" ") };
            });

        // Csak a neveket adjuk át a megjelenítéshez
        const cityNames = citiesData.map(city => city.name);

        // Szűrésválasztók feltöltése
        populateCityList("departure-list", cityNames, "departure-filter");
        populateCityList("arrival-list", cityNames, "arrival-filter");
    } catch (error) {
        console.error("Hiba történt a városok betöltésekor:", error);
    }
}

// Városok feltöltése az <ul> listába
function populateCityList(listId, cityNames, filterId) {
    const listElement = document.getElementById(listId);
    listElement.innerHTML = ""; // Lista törlése

    cityNames.forEach(cityName => {
        const li = document.createElement("li");
        li.textContent = cityName;
        li.className = "list-group-item";
        li.style.cursor = "pointer";

        // Kattintás esemény hozzáadása
        li.addEventListener("click", () => {
            document.getElementById(filterId).value = cityName; // Kiválasztott város beírása
            listElement.style.display = "none"; // Lista elrejtése
        });

        listElement.appendChild(li);
    });

    // Lista alapértelmezett rejtése
    listElement.style.display = "none";
}

// Szűrés funkció az input mezőkhöz
function filterCities(filterId, listId) {
    const input = document.getElementById(filterId).value.toLowerCase();
    const listElement = document.getElementById(listId);
    const listItems = listElement.getElementsByTagName("li");

    // Ha nincs input, rejtse el a listát
    if (!input) {
        listElement.style.display = "none";
        return;
    }

    // Mutassa azokat az elemeket, amelyek megfelelnek a szűrésnek
    listElement.style.display = "block";

    Array.from(listItems).forEach(item => {
        item.style.display = item.textContent.toLowerCase().includes(input) ? "" : "none";
    });
}

// Payload módosítása (település ID hozzáadása)
async function loadTableData() {
    const tableBody = document.querySelector("#data-table tbody");
    tableBody.innerHTML = ""; // Táblázat törlése

    const departureName = document.getElementById("departure-filter").value;
    const arrivalName = document.getElementById("arrival-filter").value;

    const departureCity = citiesData.find(city => city.name === departureName);
    const arrivalCity = citiesData.find(city => city.name === arrivalName);

    const payload = {
        func: "getRoutes",
        params: {
            datum: "2024-12-20",
            erk_stype: "megallo",
            helyi: "No",
            honnan: departureName,
            honnan_ls_id: 0,
            honnan_settlement_id: departureCity ? departureCity.id : 0,
            honnan_site_code: "",
            hour: "23",
            hova: arrivalName,
            hova_ls_id: 0,
            hova_settlement_id: arrivalCity ? arrivalCity.id : 0,
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

        if (jsonData.status === "success") {
            data = Object.values(jsonData.results.talalatok).map(talalat => {
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

            updateTable(data);
        } else {
            console.error("Nem sikerült a válasz feldolgozása:", jsonData);
        }
    } catch (error) {
        console.error("Hiba történt az adatok betöltésekor:", error);
    }
}

// Táblázat frissítése az aktuális adat tömb alapján
function updateTable(data) {
    const tableBody = document.querySelector("#data-table tbody");
    tableBody.innerHTML = ""; // Táblázat törlése

    data.forEach(row => {
        const tr = document.createElement("tr");

        // Sor színének beállítása transportMode alapján
        if (row.transportMode === "vehicles.train") {
            tr.style.backgroundColor = "#e0f7fa"; // Halványkék
        } else if (row.transportMode === "vehicles.bus") {
            tr.style.backgroundColor = "#fffde7"; // Halványsárga
        }

        tr.innerHTML = `
            <td style="background-color: ${tr.style.backgroundColor}">${row.departure}</td>
            <td style="background-color: ${tr.style.backgroundColor}">${row.arrival}</td>
            <td style="background-color: ${tr.style.backgroundColor}">${row.departureTime}</td>
            <td style="background-color: ${tr.style.backgroundColor}">${row.arrivalTime}</td>
            <td style="background-color: ${tr.style.backgroundColor}">${row.transfers}</td>
            <td style="background-color: ${tr.style.backgroundColor}">${row.totalDuration}</td>
        `;
        tableBody.appendChild(tr);
    });
}

// Táblázat rendezési funkció
function enableSorting() {
    const headers = document.querySelectorAll("#data-table th");

    headers.forEach(header => {
        header.addEventListener("click", () => {
            const key = header.getAttribute("data-sort"); // Rendezési kulcs
            const ascending = header.dataset.order !== "asc"; // Rendezési sorrend
            header.dataset.order = ascending ? "asc" : "desc";

            // Rendezés a kulcs alapján
            data.sort((a, b) => {
                if (a[key] < b[key]) return ascending ? -1 : 1;
                if (a[key] > b[key]) return ascending ? 1 : -1;
                return 0;
            });

            // Táblázat frissítése
            updateTable(data);
        });
    });
}

// Kizárólag rendezési funkció engedélyezése az oldal betöltésekor
document.addEventListener("DOMContentLoaded", () => {
    loadCities(); // Városok betöltése és megjelenítése
    enableSorting(); // Rendezés engedélyezése az oszlopokhoz
});