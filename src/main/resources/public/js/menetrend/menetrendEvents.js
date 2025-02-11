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

document.addEventListener('DOMContentLoaded', function () {
    const sortableHeaders = document.querySelectorAll('.sortable-header');

    sortableHeaders.forEach(header => {
        header.addEventListener('click', function () {
            const table = header.closest('table');
            const tbody = table.querySelector('tbody');
            const rows = Array.from(tbody.querySelectorAll('tr'));
            const sortColumn = header.dataset.sort;
            const isAscending = header.classList.contains('asc');

            // Reset minden más header ikonját
            sortableHeaders.forEach(h => {
                if (h !== header) {
                    h.classList.remove('asc', 'desc');
                    h.querySelector('i').classList.remove('fa-sort-up', 'fa-sort-down');
                    h.querySelector('i').classList.add('fa-sort');
                }
            });

            header.classList.toggle('asc', !isAscending);
            header.classList.toggle('desc', isAscending);

            const sortIcon = header.querySelector('i');
            sortIcon.classList.remove('fa-sort', 'fa-sort-up', 'fa-sort-down');
            if (header.classList.contains('asc')) {
                sortIcon.classList.add('fa-sort-up');
            } else {
                sortIcon.classList.add('fa-sort-down');
            }


            const columnIndex = Array.from(header.parentNode.children).indexOf(header);

            const sortedRows = rows.sort((rowA, rowB) => {
                const cellA = rowA.querySelectorAll('td')[columnIndex].textContent.trim();
                const cellB = rowB.querySelectorAll('td')[columnIndex].textContent.trim();

                let valueA = cellA;
                let valueB = cellB;

                if (sortColumn === 'departureTime' || sortColumn === 'arrivalTime') {
                    valueA = parseTime(cellA);
                    valueB = parseTime(cellB);
                } else if (sortColumn === 'transfers' || sortColumn === 'totalDuration') {
                    valueA = parseInt(cellA);
                    valueB = parseInt(cellB);
                }


                if (header.classList.contains('asc')) {
                    if (valueA < valueB) return -1;
                    if (valueA > valueB) return 1;
                    return 0;
                } else {
                    if (valueA > valueB) return -1;
                    if (valueA < valueB) return 1;
                    return 0;
                }
            });

            tbody.innerHTML = '';
            sortedRows.forEach(row => tbody.appendChild(row));
        });
    });

    function parseTime(timeStr) {
        const parts = timeStr.split(':');
        return new Date(1970, 0, 1, parseInt(parts[0], 10), parseInt(parts[1], 10));
    }
});