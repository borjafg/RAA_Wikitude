var World = {
	loaded: false,

	init: function initFn() {
		this.createOverlays();
	},

	createOverlays: function createOverlaysFn() {
		/*
			First an AR.ImageTracker needs to be created in order to start the recognition engine. It is initialized with a AR.TargetCollectionResource specific to the target collection that should be used. Optional parameters are passed as object in the last argument. In this case a callback function for the onTargetsLoaded trigger is set. Once the tracker loaded all its target images, the function worldLoaded() is called.

			Important: If you replace the tracker file with your own, make sure to change the target name accordingly.
			Use a specific target name to respond only to a certain target or use a wildcard to respond to any or a certain group of targets.

			Adding multiple targets to a target collection is straightforward. Simply follow our Target Management Tool documentation. Each target in the target collection is identified by its target name. By using this target name, it is possible to create an AR.ImageTrackable for every target in the target collection.
		*/
		this.targetCollectionResource = new AR.TargetCollectionResource("assets/raa.wtc", {
		});

		this.tracker = new AR.ImageTracker(this.targetCollectionResource, {
			onTargetsLoaded: this.worldLoaded,
            onError: function(errorMessage) {
            	alert(errorMessage);
            }
		});

		/*
			The next step is to create the augmentation. In this example an image resource is created and passed to the AR.ImageDrawable. A drawable is a visual component that can be connected to an IR target (AR.ImageTrackable) or a geolocated object (AR.GeoObject). The AR.ImageDrawable is initialized by the image and its size. Optional parameters allow for position it relative to the recognized target.
		*/
		var imageRestaurant = new AR.ImageResource("assets/imageRestauran.png");
		
		var restaurantButton = new AR.ImageDrawable(imageRestaurant, 0.5, {
			  offsetX : 0.4,
			  rotation : 0,
			  onClick : function() {
				  searchNearestRestauran(World.lat,World.lon,World.alt);
			}
		});
		
		var imageHotel = new AR.ImageResource("assets/imageHotel.png");
		
		var hotelButton = new AR.ImageDrawable(imageHotel, 0.5, {
			  offsetX : -0.35,
			  rotation : 0,
			  onClick : function() {
				  searchNearestHotel(World.lat,World.lon,World.alt);
			}
		});
		
		var page = new AR.ImageTrackable(this.tracker, "tripadvisor", {
			drawables: {
				cam: [restaurantButton,hotelButton]
			},
			onImageRecognized: this.removeLoadingBar,
            onError: function(errorMessage) {
            	alert(errorMessage);
            }
		});
		
	},

	removeLoadingBar: function() {
		if (!World.loaded) {
			document.getElementById('loadingMessage').parentElement.removeChild();
		}
	},

	worldLoaded: function worldLoadedFn() {
		document.getElementById('loadingMessage').innerHTML ="<a> latitud: " + World.lat+ " / </a>"+"<a> longitud: " + World.lon+ " / </a>"+"<a> altitud: " + World.alt+ "  </a>";

	}
};



AR.context.onLocationChanged = function(latitude, longitude, altitude, accuracy){
	World.lat=latitude;
	World.lon=longitude;
	World.alt=altitude
	World.init();
}

function  searchNearestRestauran(latitude, longitude, altitude){
	
}

function  searchNearestHotel(latitude, longitude, altitude){
	
}

