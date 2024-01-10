var map = L.map('map').setView([53.349805, -6.26031], 7);

L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
  attribution: '© OpenStreetMap contributors'
}).addTo(map);

//Sites and houses data***************************************************************************************************************************************************
var electricianMarker;
var electricianCircle;
var generaloperativeMarker;
var generaloperativeCircle;
var plumberMarker;
var plumberCircle;
var acMarker;
var acCircle;

var houses = [
  //Engineers coordinates***************************************************************************************************************************************************

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
    icon: house.iconUrl ? L.icon({
      iconUrl: house.iconUrl,
      iconSize: [25, 25],
      iconAnchor: [12, 12],
      popupAnchor: [0, -12],
    }) : undefined
  };

  house.marker = L.marker(house.coords, houseMarkerOptions).addTo(map).bindPopup(house.name);
  house.circle = L.circle(house.coords, {
    color: house.color || 'blue',
    fillColor: house.color || 'blue',
    fillOpacity: 0.2,
    radius: 100000 // 100km in meters
  });

  // Add a click event listener to the marker's icon
  if (house.iconUrl) {
    let isRingVisible = true; // Variable to track the ring's visibility

    house.marker.on('click', function () {
      if (isRingVisible) {
        if (house.circle) {
          map.removeLayer(house.circle);
        }
      } else {
        if (house.circle) {
          map.addLayer(house.circle);
        }
      }

      isRingVisible = !isRingVisible; // Toggle the ring's visibility
    });
  }
});





//Button listeners***************************************************************************************************************************************************

// Initialize a counter variable
var electriciansButtonCounter = 0;

// Event listener for the "Electricians" button
var electriciansButton = document.getElementById("Electricians");
electriciansButton.addEventListener("click", function () {
  electriciansButtonCounter++;

  if (electriciansButtonCounter % 2 === 1) {
    electriciansButton.classList.add("selected");
    // Show electrician markers
    houses.forEach(house => {
      if (house.id === "electrician") {
        house.marker.getElement().classList.remove("hidden");
        if (house.circle) {
          map.addLayer(house.circle);
        }
      }
    });
  } else {
    electriciansButton.classList.remove("selected");
    // Hide electrician markers
    houses.forEach(house => {
      if (house.id === "electrician") {
        house.marker.getElement().classList.add("hidden");
        if (house.circle) {
          map.removeLayer(house.circle);
        }
      }
    });
  }
});



// Initialize a counter variable
var generalOperativesButtonCounter = 0;

// Event listener for the "General Operatives" button
var generalOperativesButton = document.getElementById("GeneralOperatives");
generalOperativesButton.addEventListener("click", function () {
  generalOperativesButtonCounter++;

  if (generalOperativesButtonCounter % 2 === 1) {
    generalOperativesButton.classList.add("selected");
    // Show general operative markers
    houses.forEach(house => {
      if (house.id === "generaloperative") {
        house.marker.getElement().classList.remove("hidden");
        if (house.circle) {
          map.addLayer(house.circle);
        }
      }
    });
  } else {
    generalOperativesButton.classList.remove("selected");
    // Hide general operative markers
    houses.forEach(house => {
      if (house.id === "generaloperative") {
        house.marker.getElement().classList.add("hidden");
        if (house.circle) {
          map.removeLayer(house.circle);
        }
      }
    });
  }
});


// Initialize a counter variable
var plumbersButtonCounter = 0;
// Event listener for the "Plumbers" button
var plumbersButton = document.getElementById("Plumbers");
plumbersButton.addEventListener("click", function () {
  plumbersButtonCounter++;

  if (plumbersButtonCounter % 2 === 1) {
    plumbersButton.classList.add("selected");
    // Show plumber markers and rings
    houses.forEach(house => {
      if (house.id === "plumber") {
        house.marker.getElement().classList.remove("hidden");
        if (house.circle) {
          map.addLayer(house.circle);
        }
      }
    });
  } else {
    plumbersButton.classList.remove("selected");
    // Hide plumber markers and rings
    houses.forEach(house => {
      if (house.id === "plumber") {
        house.marker.getElement().classList.add("hidden");
        if (house.circle) {
          map.removeLayer(house.circle);
        }
      }
    });
  }
});



// Initialize a counter variable
var RGIPlumbersButtonCounter = 0;

// Event listener for the "RGIPlumbers" button
var RGIPlumbersButton = document.getElementById("RGIPlumbers");
RGIPlumbersButton.addEventListener("click", function () {
  RGIPlumbersButtonCounter++;

  if (RGIPlumbersButtonCounter % 2 === 1) {
    RGIPlumbersButton.classList.add("selected");
    // Show RGI plumber markers and rings
    houses.forEach(house => {
      if (house.id === "rgiplumber") {
        house.marker.getElement().classList.remove("hidden");
        if (house.circle) {
          map.addLayer(house.circle);
        }
      }
    });
  } else {
    RGIPlumbersButton.classList.remove("selected");
    // Hide RGI plumber markers and rings
    houses.forEach(house => {
      if (house.id === "rgiplumber") {
        house.marker.getElement().classList.add("hidden");
        if (house.circle) {
          map.removeLayer(house.circle);
        }
      }
    });
  }
});



// Initialize a counter variable
var ACSButtonCounter = 0;

// Event listener for the "ACs" button
var ACSButton = document.getElementById("acs");
ACSButton.addEventListener("click", function () {
  ACSButtonCounter++;

  if (ACSButtonCounter % 2 === 1) {
    ACSButton.classList.add("selected");
    // Show AC markers and rings
    houses.forEach(house => {
      if (house.id === "ac") {
        house.marker.getElement().classList.remove("hidden");
        if (house.circle) {
          map.addLayer(house.circle);
        }
      }
    });
  } else {
    ACSButton.classList.remove("selected");
    // Hide AC markers and rings
    houses.forEach(house => {
      if (house.id === "ac") {
        house.marker.getElement().classList.add("hidden");
        if (house.circle) {
          map.removeLayer(house.circle);
        }
      }
    });
  }
});

//OPW Toggle
var opwsButtonCounter = 0;

var opwsButton = document.getElementById("opws");
opwsButton.addEventListener("click", function () {
  opwsButtonCounter++;

  if (opwsButtonCounter % 2 === 1) {
    opwsButton.classList.add("selected");
  } else {
    opwsButton.classList.remove("selected");
  }

  cities.forEach(city => {
    if (city.id.trim() === "opw") { // Trim the ID value before checking
      if (opwsButtonCounter % 2 === 1) {
        map.addLayer(city.marker);
      } else {
        map.removeLayer(city.marker);
      }
    }
  });
});

//AnPost Toggle
var anpostsButtonCounter = 0;

var anpostsButton = document.getElementById("anposts");
anpostsButton.addEventListener("click", function () {
  anpostsButtonCounter++;

  if (anpostsButtonCounter % 2 === 1) {
    anpostsButton.classList.add("selected");
  } else {
    anpostsButton.classList.remove("selected");
  }

  cities.forEach(city => {
    if (city.id.trim() === "anpost") { // Trim the ID value before checking
      if (anpostsButtonCounter % 2 === 1) {
        map.addLayer(city.marker);
      } else {
        map.removeLayer(city.marker);
      }
    }
  });
});

//IPS Toggle 
var ipssButtonCounter = 0;

var ipssButton = document.getElementById("ipss");
ipssButton.addEventListener("click", function () {
  ipssButtonCounter++;

  if (ipssButtonCounter % 2 === 1) {
    ipssButton.classList.add("selected");
  } else {
    ipssButton.classList.remove("selected");
  }

  cities.forEach(city => {
    if (city.id.trim() === "ips") { // Trim the ID value before checking
      if (ipssButtonCounter % 2 === 1) {
        map.addLayer(city.marker);
      } else {
        map.removeLayer(city.marker);
      }
    }
  });
});

//HSE Toggle 
var hsesButtonCounter = 0;

var hsesButton = document.getElementById("hses");
hsesButton.addEventListener("click", function () {
  hsesButtonCounter++;

  if (hsesButtonCounter % 2 === 1) {
    hsesButton.classList.add("selected");
  } else {
    hsesButton.classList.remove("selected");
  }

  cities.forEach(city => {
    if (city.id.trim() === "hse") { // Trim the ID value before checking
      if (hsesButtonCounter % 2 === 1) {
        map.addLayer(city.marker);
      } else {
        map.removeLayer(city.marker);
      }
    }
  });
});

//Others Toggle
var othersButtonCounter = 0;

var othersButton = document.getElementById("others");
othersButton.addEventListener("click", function () {
  othersButtonCounter++;

  if (othersButtonCounter % 2 === 1) {
    othersButton.classList.add("selected");
  } else {
    othersButton.classList.remove("selected");
  }

  cities.forEach(city => {
    if (city.id.trim() === "other") { // Trim the ID value before checking
      if (othersButtonCounter % 2 === 1) {
        map.addLayer(city.marker);
      } else {
        map.removeLayer(city.marker);
      }
    }
  });
});

// Reset Button
var resetButton = document.getElementById("resetButton");
resetButton.addEventListener("click", function () {
  // Reset all button states
  electriciansButtonCounter = 0;
  electriciansButton.classList.remove("selected");

  generalOperativesButtonCounter = 0;
  generalOperativesButton.classList.remove("selected");

  plumbersButtonCounter = 0;
  plumbersButton.classList.remove("selected");

  RGIPlumbersButtonCounter = 0;
  RGIPlumbersButton.classList.remove("selected");

  ACSButtonCounter = 0;
  ACSButton.classList.remove("selected");

  opwsButtonCounter = 0;
  opwsButton.classList.remove("selected");

  anpostsButtonCounter = 0;
  anpostsButton.classList.remove("selected");

  ipssButtonCounter = 0;
  ipssButton.classList.remove("selected");

  hsesButtonCounter = 0;
  hsesButton.classList.remove("selected");

  otherssButtonCounter = 0;
  othersButton.classList.remove("selected");

  // Reset markers and rings
  houses.forEach(house => {
    house.marker.getElement().classList.add("hidden");
    if (house.circle) {
      map.removeLayer(house.circle);
    }
  });

  cities.forEach(city => {
    map.removeLayer(city.marker);
  });
});

// Event listener for the "ToggleElectriciansCircle" button
var toggleElectriciansCircleButton = document.getElementById("ToggleElectriciansCircle");
toggleElectriciansCircleButton.addEventListener("click", function () {
  houses.forEach(house => {
    if (house.id === "electrician") {
      if (house.circle) {
        if (map.hasLayer(house.circle)) {
          map.removeLayer(house.circle);
        } else {
          map.addLayer(house.circle);
        }
      }
    }
  });
});

// Event listener for the "ToggleGeneralOperativesCircle" button
var toggleGeneralOperativesCircleButton = document.getElementById("ToggleGeneralOperativesCircle");
toggleGeneralOperativesCircleButton.addEventListener("click", function () {
  houses.forEach(house => {
    if (house.id === "generaloperative") {
      if (house.circle) {
        if (map.hasLayer(house.circle)) {
          map.removeLayer(house.circle);
        } else {
          map.addLayer(house.circle);
        }
      }
    }
  });
});

// Event listener for the "TogglePlumbersCircle" button
var togglePlumbersCircleButton = document.getElementById("TogglePlumbersCircle");
togglePlumbersCircleButton.addEventListener("click", function () {
  houses.forEach(house => {
    if (house.id === "plumber") {
      if (house.circle) {
        if (map.hasLayer(house.circle)) {
          map.removeLayer(house.circle);
        } else {
          map.addLayer(house.circle);
        }
      }
    }
  });
});

// Event listener for the "ToggleRGIPlumbersCircle" button
var toggleRGIPlumbersCircleButton = document.getElementById("ToggleRGIPlumbersCircle");
toggleRGIPlumbersCircleButton.addEventListener("click", function () {
  houses.forEach(house => {
    if (house.id === "rgiplumber") {
      if (house.circle) {
        if (map.hasLayer(house.circle)) {
          map.removeLayer(house.circle);
        } else {
          map.addLayer(house.circle);
        }
      }
    }
  });
});

// Event listener for the "ToggleACCircle" button
var toggleACCircleButton = document.getElementById("ToggleACCircle");
toggleACCircleButton.addEventListener("click", function () {
  houses.forEach(house => {
    if (house.id === "ac") {
      if (house.circle) {
        if (map.hasLayer(house.circle)) {
          map.removeLayer(house.circle);
        } else {
          map.addLayer(house.circle);
        }
      }
    }
  });
});
