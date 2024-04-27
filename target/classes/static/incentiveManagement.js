function sendEmployeeDataToBackend() {
    var incentiveTable = document.getElementById('incentiveTable');
    var employeeName = document.getElementById('employeeName').value;
    var employeeId = document.getElementById('employeeId').value;
    var salesAmount = parseFloat(document.getElementById('salesAmount').value);

    if (!employeeName || !employeeId || isNaN(salesAmount)) {
        alert("Please fill in all fields before calculating the incentive.");
        return;
    }

    var formData = {
        name: employeeName,
        id: employeeId,
        saleCount: salesAmount
    };

    fetch('http://127.0.0.1:9091/saveSale', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })
        .then(response => {
            if (response.ok) {
                return response.text();
            }
            throw new Error('Network response was not ok.');
        })
        .then(data => {
            // console.log(data); // Log success message from backend
            // alert(data);
            // Update UI or show success message to user
            fetchEmployeeData();
        })
        .catch(error => {
            console.error('Error:', error);
            alert("Invalid Employee ID.");
            incentiveTable.style.display = 'none';

            // Handle error and show error message to user
        });
}

function fetchEmployeeData() {
    // Make a GET request to the controller endpoint

    fetch('http://127.0.0.1:9091/getDetailDashboard')
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
            var employeeId = document.getElementById('employeeId').value;
            var employeeName = document.getElementById('employeeName').value;
            var tableTag = document.getElementById('tableTag');

            // Clear previous data
            incentiveBody.innerHTML = '';
            tableTag.innerHTML = 'Incentive Details';

            // Find the closest valid target
            var validTarget = null;
            for (var i = 0; i < data.length; i++) {
                if (employeeId == (data[i].id)) {
                    validTarget = data[i];
                }
            }

            // Populate incentive details table
            if (validTarget && validTarget.name == employeeName) {
                var row = "<tr>";
                row += "<td>" + validTarget.id + "</td>";
                row += "<td>" + validTarget.name + "</td>";
                row += "<td>" + validTarget.saleCount + "</td>";
                row += "<td>" + validTarget.incentivePercentage + "</td>";
                row += "<td>" + validTarget.bonus + "</td>";
                row += "<td>" + validTarget.holidaypackageEligibility + "</td>";
                row += "</tr>";
                incentiveBody.innerHTML = row;
                // Display incentive details table
                incentiveTable.style.display = 'block';
            }
            else {
                alert("Enter your name correctly.");
                incentiveTable.style.display = 'none';

            }
        })
        .catch(error => {
            // Handle any errors that occurred during the fetch
            console.error('Error fetching data:', error.message);
        });
}

function fetchAllEmployeeData() {
    // Make a GET request to the controller endpoint

    fetch('http://127.0.0.1:9091/getDetailDashboard')
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
            var tableTag = document.getElementById('tableTag');

            tableTag.innerHTML = 'Employee Details';
            // Clear previous data
            incentiveBody.innerHTML = '';

            for (var i = 0; i < data.length; i++) {
                var row = "<tr>";
                row += "<td>" + data[i].id + "</td>";
                row += "<td>" + data[i].name + "</td>";
                row += "<td>" + data[i].saleCount + "</td>";
                row += "<td>" + data[i].incentivePercentage + "</td>";
                row += "<td>" + data[i].bonus + "</td>";
                row += "<td>" + data[i].holidaypackageEligibility + "</td>";
                row += "</tr>";
                incentiveBody.innerHTML += row;
            }
            // Display incentive details table
            incentiveTable.style.display = 'block';
        })
        .catch(error => {
            // Handle any errors that occurred during the fetch
            console.error('Error fetching data:', error.message);
        });
}