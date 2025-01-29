const searchDeparture = document.getElementById('departure-search');
const filterDeparture = document.getElementById('departure-filter');

searchDeparture.addEventListener("input", () => {
    if (searchDeparture.value.length === 0) {
        filterDeparture.style.display = "none";
    } else {
        filterCities("departure-search", "departure-filter");
        filterDeparture.style.display = "block";
    }
});

const searchArrival = document.getElementById('arrival-search');
const filterArrival = document.getElementById('arrival-filter');

searchArrival.addEventListener("input", () => {
    if (searchArrival.value.length === 0) {
        filterArrival.style.display = "none";
    } else {
        filterCities("arrival-search", "arrival-filter");
        filterArrival.style.display = "block";
    }
});