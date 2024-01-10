// OpenWeatherMap API. Do not share it publicly.
const api = '7329adde15660656920342e78257e068'; // Replace with your API

window.addEventListener('load', () => {
    let long;
    let lat;

    // Accessing Geolocation of User
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition((position) => {
            // Storing Longitude and Latitude in variables
            long = position.coords.longitude;
            lat = position.coords.latitude;

            // Fetch weather data using the OpenWeatherMap API
            fetch(`https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${long}&appid=${api}&units=metric`)
                .then(response => response.json())
                .then(data => {
                    // Handle the weather data as needed
                    console.log(data);
                })
                .catch(error => {
                    console.error('Error fetching weather data:', error);
                });
        });
    }
});
