const searchDeparture = document.getElementById('departure-search');
const searchArrival = document.getElementById('arrival-search');

const filterDeparture = document.getElementById('departure-filter');
const filterArrival = document.getElementById('arrival-filter');

searchDeparture.addEventListener("input", () => {
    if (searchDeparture.value.length === 0) {
        filterDeparture.style.display = "none";
    } else {
        filterCities("departure-search", "departure-filter");
        filterDeparture.style.display = "block";
    }
});

searchArrival.addEventListener("input", () => {
    if (searchArrival.value.length === 0) {
        filterArrival.style.display = "none";
    } else {
        filterCities("arrival-search", "arrival-filter");
        filterArrival.style.display = "block";
    }
});

function filterCities(filterId, listId) {
    const input = document.getElementById(filterId).value.toLowerCase();
    const listElement = document.getElementById(listId);
    const listItems = Array.from(listElement.getElementsByTagName("li")); // Convert to array for sorting

    listElement.style.display = "block";

    // Sort the list items based on whether they start with the input
    listItems.sort((a, b) => {
        const cityNameA = a.textContent.toLowerCase();
        const cityNameB = b.textContent.toLowerCase();

        const startsWithA = cityNameA.startsWith(input);
        const startsWithB = cityNameB.startsWith(input);

        if (startsWithA && !startsWithB) return -1;
        if (!startsWithA && startsWithB) return 1;
        return 0; // Maintain original order if both or neither start with input
    });

    listItems.forEach((item, index) => {
        const cityName = item.textContent;
        const lowerCaseCityName = cityName.toLowerCase();

        if (lowerCaseCityName.includes(input)) {
            item.style.display = "";
            const regex = new RegExp(input, 'gi');
            item.innerHTML = cityName.replace(regex, (match) => `<b>${match}</b>`);

            listElement.appendChild(item); // Re-append to update order in the DOM

            item.addEventListener('click', () => {
                document.getElementById(filterId).value = item.textContent;
                // Opcionálisan elrejthetjük a listát a kiválasztás után
                listElement.style.display = 'none';
            });

        } else {
            item.style.display = "none";
        }
    });
}