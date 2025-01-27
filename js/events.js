import {filterCities} from './ui.js';
import {loadData, citiesData, data} from './data.js';
import {updateTable} from './ui.js';

document.addEventListener("DOMContentLoaded", () => {

    // Keresés gomb eseménykezelője
    const searchButton = document.getElementById("search-button");
    searchButton.addEventListener("click", () => {
        const departureName = document.getElementById("departure-filter").value;
        const arrivalName = document.getElementById("arrival-filter").value;

        const departureCity = citiesData.find(city => city.name === departureName);
        const arrivalCity = citiesData.find(city => city.name === arrivalName);

        if (departureCity && arrivalCity) {
            loadData(departureCity.id, arrivalCity.id)
                .then(() => {
                    updateTable(data); // Táblázat frissítése az új adatokkal
                });
        } else {
            console.error("Nem található a megadott város.");
        }
    });
});


function enableSorting() {
    const headers = document.querySelectorAll("#data-table th");

    headers.forEach(header => {
        header.addEventListener("click", () => {
            const key = header.getAttribute("data-sort");
            const ascending = header.dataset.order !== "asc";
            header.dataset.order = ascending ? "asc" : "desc";

            data.sort((a, b) => {
                if (a[key] < b[key]) return ascending ? -1 : 1;
                if (a[key] > b[key]) return ascending ? 1 : -1;
                return 0;
            });

            updateTable(data);
        });
    });
}

export {enableSorting};
