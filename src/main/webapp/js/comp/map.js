var markers = [];
var markersId = [];
var map;
var defaultCenter = new google.maps.LatLng(10.773278, 106.681854);
var curMarker = new google.maps.Marker();
var curLocation;

// initialize Search Map
function initializeSearchMap() {
	var latlng = defaultCenter;
	var myOptions = {
		center : latlng,
		zoom : 15,
		mapTypeId : google.maps.MapTypeId.ROADMAP,
		mapTypeControl : false,
		panControl : false,
		zoomControlOptions: {
	        style: google.maps.ZoomControlStyle.SMALL,
	        position: google.maps.ControlPosition.LEFT_CENTER
	    },
	};
	map = new google.maps.Map(document.getElementById("map_canvas"),
			myOptions);
};

function getLocationXY(address) {
	alert(address);
	var geocoder = new google.maps.Geocoder();
	geocoder.geocode({
		'address' : address
	}, function(results, status) {
		if (status == google.maps.GeocoderStatus.OK) {
			mapCenter = results[0].geometry.location;
			alert("1" + mapCenter);
		} else {
			mapCenter = defaultCenter;
		}
	});
};

// get User Current Location
function getCurrentLocation() {
	curMarker.setMap(null);
	curMarker = null;
	
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(function(position) {
			curLocation = new google.maps.LatLng(position.coords.latitude,
					position.coords.longitude);

			curMarker = new google.maps.Marker({
				position : curLocation,
				title : "Your Location",
				animation : google.maps.Animation.BOUNCE,
				map : map
			});
			// set center if map is not undefined
			if(map!=undefined) {
				map.setCenter(curLocation);
			}
		}, function() {
			alert('Error: The Geolocation service failed.');
		});
	} else {
		// Browser doesn't support Geolocation
		alert('Error: Your browser doesn\'t support geolocation.');
	}
}