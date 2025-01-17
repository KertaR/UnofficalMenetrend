function populateCityList(listId, cityNames, filterId) {
    const listElement = document.getElementById(listId);
    listElement.innerHTML = "";

    cityNames.forEach(cityName => {
        const li = document.createElement("li");
        li.textContent = cityName;
        li.className = "list-group-item";
        li.style.cursor = "pointer";

        li.addEventListener("click", () => {
            document.getElementById(filterId).value = cityName;
            listElement.style.display = "none";
        });

        li.addEventListener("mouseenter", () => {
            document.getElementById(filterId).value = cityName;
            li.style.backgroundColor = "lightgray";
        });

        li.addEventListener("mouseleave", () => {
            document.getElementById(filterId).value = cityName;
            li.style.backgroundColor = "white";
        });

        listElement.appendChild(li);
    });

    listElement.style.display = "none";
}

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

        } else {
            item.style.display = "none";
        }
    });
}

function updateTable(data) {
    const tableBody = document.querySelector("#data-table tbody");
    tableBody.innerHTML = "";

    const now = new Date(); // Get current time

    data.forEach(row => {
        const tr = document.createElement("tr");

        const departureTime = new Date(parseTime(row.departureTime)); // Parse departure time
        if (departureTime < now) {
            tr.style.backgroundColor = "#ececec"; // Add class for styling
        } else {
            if (row.transportMode === "vehicles.train") {
                tr.style.backgroundColor = "#e0f7fa";
            } else if (row.transportMode === "vehicles.bus") {
                tr.style.backgroundColor = "#fffde7";
            }
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

function parseTime(timeString) {
    const [hours, minutes] = timeString.split(':');
    const now = new Date();
    now.setHours(hours, minutes, 0, 0);
    return now;
}

export { populateCityList, filterCities, updateTable };