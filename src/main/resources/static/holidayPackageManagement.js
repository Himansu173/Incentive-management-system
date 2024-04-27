function sendHolidayDataToBackend() {
    if (!employeeName || !employeeId || isNaN(salesAmount)) {
        alert("Please fill in all fields before calculating the incentive.");
        return;
    }

    var formData = {
        name: employeeName,
        id: employeeId,
        saleCount: salesAmount
    };

    fetch('http://127.0.0.1:9091/getHolidayPackage', {
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
            fetchHolidayData();
        })
        .catch(error => {
            console.error('Error:', error);
            alert("Invalid Employee ID.");
            incentiveTable.style.display = 'none';

            // Handle error and show error message to user
        });
}

fetchHolidayData();

function fetchHolidayData() {
    // Make a GET request to the controller endpoint

    fetch('http://127.0.0.1:9091/getHolidayPackage')
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

            data.forEach(holiday => {
                var row = "<tr>";
                row += "<td>" + holiday.id + "</td>";
                row += "<td>" + holiday.holidayName + "</td>";
                row += "<td>" + holiday.duration + "</td>";
                row += "<td>" + holiday.destination + "</td>";
                row += "<td>" + holiday.location + "</td>";
                row += "<td>" + holiday.amenities + "</td>";
                row += "<td>" + holiday.eligibilitySaleCount + "</td>";
                row += "<td><a style='cursor:pointer;' onclick='updateHoliday(" + JSON.stringify(holiday) + ")'>&#x270E</a></td>";
                row += "<td><a style='cursor:pointer;' onclick='deleteHoliday(" + holiday.id + ")'>&#x1F5D1</a></td=>";
                row += "</tr>";
                incentiveBody.innerHTML += row;
            });

            incentiveTable.style.display = 'block';
        })
        .catch(error => {
            console.error('Error fetching data:', error.message);
        });
}

// // Function to delete a holiday
function deleteHoliday(id) {
    // Show confirmation modal
    var confirmationModal = document.getElementById('confirmationModal');
    confirmationModal.style.display = 'block';

    // Handle close button
    var closeButton = document.getElementsByClassName('close')[0];
    closeButton.onclick = function () {
        // Hide confirmation modal
        confirmationModal.style.display = 'none';
    };

    // Handle confirmation buttons
    var confirmDeleteBtn = document.getElementById('confirmDeleteBtn');
    var cancelDeleteBtn = document.getElementById('cancelDeleteBtn');

    // Event listener for confirmation button
    confirmDeleteBtn.onclick = function () {
        // If confirmed, send DELETE request to the server
        fetch('http://127.0.0.1:9091/deleteHolidayPackage/' + id, {
            method: 'DELETE'
        })
            .then(response => {
                if (response.ok) {
                    // If successful, fetch holiday data again to update the table
                    fetchHolidayData();
                } else {
                    throw new Error('Failed to delete holiday');
                }
            })
            .catch(error => {
                console.error('Error deleting holiday:', error.message);
            });

        // Hide confirmation modal
        confirmationModal.style.display = 'none';
    };

    // Event listener for cancel button
    cancelDeleteBtn.onclick = function () {
        // Hide confirmation modal
        confirmationModal.style.display = 'none';
    };
}

// Function to handle update button click
function updateHoliday(oldPackage) {
    // Show update modal
    var updateModal = document.getElementById('updateModal');
    var cancelUpdateBtn = document.getElementById('cancelUpdateBtn');

    updateModal.style.display = 'block';

    document.getElementById('holidayName').value = oldPackage.holidayName;
    document.getElementById('duration').value = oldPackage.duration;
    document.getElementById('destination').value = oldPackage.destination;
    document.getElementById('location').value = oldPackage.location;
    document.getElementById('amenities').value = oldPackage.amenities;
    document.getElementById('eligibilitySaleCount').value = oldPackage.eligibilitySaleCount;

    // Handle close button
    var closeButton = document.getElementsByClassName('close')[1];
    closeButton.onclick = function () {
        // Hide update modal
        updateModal.style.display = 'none';
    };

    // Handle cancel button
    var cancelUpdateBtn = document.getElementById('cancelUpdateBtn');
    cancelUpdateBtn.onclick = function () {
        // Hide update modal
        updateModal.style.display = 'none';
    };

    // Handle form submission (save)
    var updateForm = document.getElementById('updateForm');
    updateForm.onsubmit = function (event) {
        event.preventDefault(); // Prevent form submission
        // Collect updated values from input fields
        var updatedValues = {
            id: oldPackage.id,
            holidayName: document.getElementById('holidayName').value,
            duration: document.getElementById('duration').value,
            destination: document.getElementById('destination').value,
            location: document.getElementById('location').value,
            amenities: document.getElementById('amenities').value,
            eligibilitySaleCount: document.getElementById('eligibilitySaleCount').value
        };

        fetch('http://127.0.0.1:9091/updateHolidayPackage', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(updatedValues)
        })
            .then(response => {
                if (response.ok) {
                    // If successful, fetch holiday data again to update the table
                    fetchHolidayData();
                    // Hide update modal
                    // updateModal.style.display = 'none';
                } else {
                    throw new Error('Failed to update holiday');
                }
            })
            .catch(error => {
                console.error('Error updating holiday:', error.message);
            });
        updateModal.style.display = 'none';
    };
}

// Function to handle + Add button click
document.getElementById('addHolidayBtn').addEventListener('click', function () {
    // Show update modal with empty input fields
    var updateModal = document.getElementById('updateModal');
    updateModal.style.display = 'block';

    // Populate modal with empty input fields
    document.getElementById('holidayName').value = '';
    document.getElementById('duration').value = '';
    document.getElementById('destination').value = '';
    document.getElementById('location').value = '';
    document.getElementById('amenities').value = '';
    document.getElementById('eligibilitySaleCount').value = '';

    // Handle close button
    var closeButton = document.getElementsByClassName('close')[0];
    closeButton.onclick = function () {
        // Hide update modal
        updateModal.style.display = 'none';
    };

    // Handle cancel button
    var cancelUpdateBtn = document.getElementById('cancelUpdateBtn');
    cancelUpdateBtn.onclick = function () {
        // Hide update modal
        updateModal.style.display = 'none';
    };

    // Handle form submission (save)
    var updateForm = document.getElementById('updateForm');
    updateForm.onsubmit = function (event) {
        event.preventDefault(); // Prevent form submission
        // Collect input values from input fields
        var newHoliday = {
            holidayName: document.getElementById('holidayName').value,
            duration: document.getElementById('duration').value,
            destination: document.getElementById('destination').value,
            location: document.getElementById('location').value,
            amenities: document.getElementById('amenities').value,
            eligibilitySaleCount: document.getElementById('eligibilitySaleCount').value
        };

        // Send new holiday data to backend using POST method
        fetch('http://127.0.0.1:9091/saveHolidayPackage', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newHoliday)
        })
            .then(response => {
                if (response.ok) {
                    // If successful, fetch holiday data again to update the table
                    fetchHolidayData();
                    // Hide update modal
                    updateModal.style.display = 'none';
                } else {
                    throw new Error('Failed to add new holiday');
                }
            })
            .catch(error => {
                console.error('Error adding new holiday:', error.message);
            });
    };
});
