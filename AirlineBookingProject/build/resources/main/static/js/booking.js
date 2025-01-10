document.addEventListener("DOMContentLoaded", function() {
    fetchBookings();

    const bookingForm = document.getElementById("bookingForm");
    if (bookingForm) {
        bookingForm.addEventListener("submit", function(event) {
            event.preventDefault();
            createBooking();
        });
    }
});

function fetchBookings() {
    fetch('/api/bookings')
        .then(response => response.json())
        .then(data => {
            const bookingList = document.getElementById("bookingList");
            if (bookingList) {
                bookingList.innerHTML = "";
                data.forEach(booking => {
                    const listItem = document.createElement("li");
                    listItem.textContent = `Booking ID: ${booking.id}, Flight: ${booking.flightNumber}, User: ${booking.userEmail}, Seats: ${booking.seatsBooked}`;
                    bookingList.appendChild(listItem);
                });
            }
        })
        .catch(error => console.error('Error fetching bookings:', error));
}

function createBooking() {
    const flightNumber = document.getElementById("flightNumber").value;
    const userEmail = document.getElementById("userEmail").value;
    const seatsBooked = document.getElementById("seatsBooked").value;

    fetch('/api/bookings', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ flightNumber, userEmail, seatsBooked }),
    })
    .then(response => response.json())
    .then(data => {
        console.log('Booking created:', data);
        fetchBookings();
    })
    .catch(error => console.error('Error creating booking:', error));
}
