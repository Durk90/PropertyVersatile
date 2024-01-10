document.addEventListener('DOMContentLoaded', function () {
    var map = L.map('map').setView([53.349805, -6.26031], 7);

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '© OpenStreetMap contributors'
    }).addTo(map);

    var defaultIcon = L.icon({
        iconUrl: 'https://cdn4.iconfinder.com/data/icons/pictype-free-vector-icons/16/home-512.png',
        iconSize: [25, 25],
        iconAnchor: [12, 12],
        popupAnchor: [0, -12],
    });

    var houses = [
        // Engineers coordinates **************************************************************************************
        { name: "Alan Ashe", coords: [53.595770626337256, -6.7279258047858965], iconUrl: 'https://cdn4.iconfinder.com/data/icons/pictype-free-vector-icons/16/home-512.png', color: "blue", id: "plumber" },
        { name: "Benny Raggett", coords: [52.35138794449713, -7.332403855430152], iconUrl: 'https://cdn4.iconfinder.com/data/icons/pictype-free-vector-icons/16/home-512.png', color: "green", id: "generaloperative" },
        { name: "Brendan Doyle", coords: [52.980337860461, -6.0474707878394325], iconUrl: 'https://cdn4.iconfinder.com/data/icons/pictype-free-vector-icons/16/home-512.png', color: "green", id: "generaloperative" },
        { name: "Brian Condron", coords: [53.58703429041637, -7.150980383419357], iconUrl: 'https://cdn4.iconfinder.com/data/icons/pictype-free-vector-icons/16/home-512.png', color: "yellow", id: "electrician" },
        { name: "Colin O loughlin", coords: [53.31971737635233, -6.300638346535033], iconUrl: 'https://cdn4.iconfinder.com/data/icons/pictype-free-vector-icons/16/home-512.png', color: "yellow", id: "electrician" },
        { name: "Danny Doyle", coords: [53.78885200063957, -6.885952052226714], iconUrl: 'https://cdn4.iconfinder.com/data/icons/pictype-free-vector-icons/16/home-512.png', color: "aqua", id: "rgiplumber" },
        { name: "Dean Fitzgerald", coords: [52.34705893220324, -7.416448703445855], iconUrl: 'https://cdn4.iconfinder.com/data/icons/pictype-free-vector-icons/16/home-512.png', color: "yellow", id: "electrician" },
        { name: "Derek Farrelly", coords: [53.39448865594821, -6.182938591897311], iconUrl: 'https://cdn4.iconfinder.com/data/icons/pictype-free-vector-icons/16/home-512.png', color: "yellow", id: "electrician" },
        { name: "Essam Mohamed", coords: [54.000117672349745, -6.369332159994597], iconUrl: 'https://cdn4.iconfinder.com/data/icons/pictype-free-vector-icons/16/home-512.png', color: "yellow", id: "electrician" },
        { name: "Graham Kearney", coords: [53.34571845668476, -6.258846288861894], iconUrl: 'https://cdn4.iconfinder.com/data/icons/pictype-free-vector-icons/16/home-512.png', color: "aqua", id: "rgiplumber" },
        { name: "Mahammad Yamin", coords: [53.33520928189302, -6.293505287010704], iconUrl: 'https://cdn4.iconfinder.com/data/icons/pictype-free-vector-icons/16/home-512.png', color: "green", id: "generaloperative" },
        { name: "Mark Brennan", coords: [52.80545628396496, -7.210798102695499], iconUrl: 'https://cdn4.iconfinder.com/data/icons/pictype-free-vector-icons/16/home-512.png', color: "green", id: "generaloperative" },
        { name: "Martin Fahy", coords: [52.84753450611217, -8.989151846080299], iconUrl: 'https://cdn4.iconfinder.com/data/icons/pictype-free-vector-icons/16/home-512.png', color: "aqua", id: "rgiplumber" },
        { name: "Martin Greaney", coords: [53.39967160784859, -8.994592907659843], iconUrl: 'https://cdn4.iconfinder.com/data/icons/pictype-free-vector-icons/16/home-512.png', color: "green", id: "generaloperative" },
        { name: "Michael Murphy", coords: [54.05852582691052, -8.921876458492532], iconUrl: 'https://cdn4.iconfinder.com/data/icons/pictype-free-vector-icons/16/home-512.png', color: "blue", id: "plumber" },
        { name: "Nathan Collins", coords: [53.19459617180482, -6.116920507783985], iconUrl: 'https://cdn4.iconfinder.com/data/icons/pictype-free-vector-icons/16/home-512.png', color: "grey", id: "ac" },
        { name: "Neil Hipwell", coords: [53.661129988631515, -9.115587138677299], iconUrl: 'https://cdn4.iconfinder.com/data/icons/pictype-free-vector-icons/16/home-512.png', color: "aqua", id: "rgiplumber" },
        { name: "Padraig kelly", coords: [54.1009962978716, -6.199949270047535], iconUrl: 'https://cdn4.iconfinder.com/data/icons/pictype-free-vector-icons/16/home-512.png', color: "blue", id: "plumber" },
        { name: "Richard Rogers", coords: [53.613280172274706, -6.188871315833415], iconUrl: 'https://cdn4.iconfinder.com/data/icons/pictype-free-vector-icons/16/home-512.png', color: "blue", id: "plumber" },
        { name: "Sanwar Akand", coords: [53.37267849802628, -6.290443915844879], iconUrl: 'https://cdn4.iconfinder.com/data/icons/pictype-free-vector-icons/16/home-512.png', color: "grey", id: "ac" },
        { name: "Seamus McGovern", coords: [54.19637046057441, -7.571272500767458], iconUrl: 'https://cdn4.iconfinder.com/data/icons/pictype-free-vector-icons/16/home-512.png', color: "yellow", id: "electrician" },
        { name: "Seamus Thompson", coords: [53.49337687236042, -6.805236167007079], iconUrl: 'https://cdn4.iconfinder.com/data/icons/pictype-free-vector-icons/16/home-512.png', color: "yellow", id: "electrician" },
        { name: "Seamus Walsh", coords: [52.44659715163007, -9.485623030073855], iconUrl: 'https://cdn4.iconfinder.com/data/icons/pictype-free-vector-icons/16/home-512.png', color: "green", id: "generaloperative" },
        { name: "Terry Wearen", coords: [53.1619920519904, -7.189314100028763], iconUrl: 'https://cdn4.iconfinder.com/data/icons/pictype-free-vector-icons/16/home-512.png', color: "green", id: "generaloperative" },
        { name: "Vincent Kelly", coords: [52.912599132584056, -6.837695883689851], iconUrl: 'https://cdn4.iconfinder.com/data/icons/pictype-free-vector-icons/16/home-512.png', color: "yellow", id: "electrician" },
        { name: "Unnamed Technician", coords: [54.280429325070855, -9.82214213660884], iconUrl: 'https://cdn4.iconfinder.com/data/icons/pictype-free-vector-icons/16/home-512.png', color: "grey", id: "ac" },
    
    ];

    houses.forEach(house => {
        var houseMarkerOptions = {
            icon: house.iconUrl ? defaultIcon : undefined
        };

        house.marker = L.marker(house.coords, houseMarkerOptions).addTo(map).bindPopup(house.name);
        house.circle = L.circle(house.coords, {
            color: house.color || 'blue',
            fillColor: house.color || 'blue',
            fillOpacity: 0.2,
            radius: 100000 // 100km in meters
        });

        if (house.iconUrl) {
            let isRingVisible = true;
            house.marker.on('click', function () {
                if (isRingVisible) {
                    if (house.circle) map.removeLayer(house.circle);
                } else {
                    if (house.circle) map.addLayer(house.circle);
                }
                isRingVisible = !isRingVisible;
            });
        }
    });

    function toggleButton(button, id) {
        var isActive = false;

        button.addEventListener("click", function () {
            isActive = !isActive;
            button.classList.toggle("selected", isActive);
            toggleMarkers(id, isActive);
        });
    }

    function toggleMarkers(id, show) {
        houses.forEach(house => {
            if (house.id === id) {
                house.marker.getElement().classList.toggle("hidden", !show);
                if (house.circle) {
                    show ? map.addLayer(house.circle) : map.removeLayer(house.circle);
                }
            }
        });
    }

    // Toggle Buttons
    toggleButton(document.getElementById("Electricians"), "electrician");
    toggleButton(document.getElementById("GeneralOperatives"), "generaloperative");
    toggleButton(document.getElementById("Plumbers"), "plumber");
    toggleButton(document.getElementById("RGIPlumbers"), "rgiplumber");
    toggleButton(document.getElementById("acs"), "ac");

    // Reset Button
    var resetButton = document.getElementById("resetButton");
    resetButton.addEventListener("click", function () {
        houses.forEach(house => {
            house.marker.getElement().classList.add("hidden");
            if (house.circle) map.removeLayer(house.circle);
        });
    });
});
