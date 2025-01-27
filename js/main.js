import {loadCities, loadData, citiesData, data} from './data.js';
import {populateCityList, filterCities, updateTable} from './ui.js';
import {enableSorting} from './events.js';

document.addEventListener("DOMContentLoaded", () => {
    loadCities().then(() => {
        // Városok betöltését követően inicializáld a szűrésválasztókat
        const cityNames = citiesData.map(city => city.name);
        populateCityList("departure-list", cityNames, "departure-filter");
        populateCityList("arrival-list", cityNames, "arrival-filter");
    });

    enableSorting();

    // Eseménykezelő a kereséshez
    const searchButton = document.getElementById("search-button");
    searchButton.addEventListener("click", () => {
        const departureName = document.getElementById("departure-filter").value;
        const arrivalName = document.getElementById("arrival-filter").value;

        const departureCity = citiesData.find(city => city.name === departureName);
        const arrivalCity = citiesData.find(city => city.name === arrivalName);

        if (departureCity && arrivalCity) {
            loadData(departureCity.id, arrivalCity.id)
                .then(() => {
                    updateTable(data);
                });
        } else {
            console.error("Nem található a megadott város.");
        }
    });

    // Eseménykezelők a szűréshez (a megfelelő elemekre)
    const departureFilter = document.getElementById("departure-filter");
    const departureList = document.getElementById("departure-list");
    const arrivalFilter = document.getElementById("arrival-filter");
    const arrivalList = document.getElementById("arrival-list");

    departureFilter.addEventListener("input", () => {
        if (departureFilter.value.length === 0) {
            departureList.style.display = "none";
        } else {
            filterCities("departure-filter", "departure-list");
            departureList.style.display = "block";
        }
    });

    arrivalFilter.addEventListener("input", () => {
        if (arrivalFilter.value.length === 0) {
            arrivalList.style.display = "none";
        } else {
            filterCities("arrival-filter", "arrival-list");
            arrivalList.style.display = "block";
        }
    });
});