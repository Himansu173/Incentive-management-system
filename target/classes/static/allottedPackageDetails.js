
function fetchEmployeeHolidayData() {
    // Make a GET request to the controller endpoint

    fetch('http://127.0.0.1:9091/getEmployeeHolidayPackage')
        .then(response => {
            // Check if the response is successful
            if (!response.ok) {
                throw new Error('Failed to fetch data');
            }
            // Parse the JSON response
            return response.json();
        })
        .then(data => {
            // Process the received data
            var incentiveTable = document.getElementById('incentiveTable');
            var incentiveBody = document.getElementById('incentiveBody');

            incentiveBody.innerHTML = '';

            for (var i = 0; i < data.length; i++) {
                var row = "<tr>";
                row += "<td>" + data[i].id + "</td>";
                row += "<td>" + data[i].employeeName + "</td>";
                row += "<td>" + data[i].holidayName + "</td>";
                row += "</tr>";
                incentiveBody.innerHTML += row;
            }

            incentiveTable.style.display = 'block';
        })
        .catch(error => {
            console.error('Error fetching data:', error.message);
        });
}
fetchEmployeeHolidayData();