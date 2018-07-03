package com.example.akshayparmar.module12_mapapp

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.BitmapRegionDecoder
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.speech.tts.TextToSpeech
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.BoringLayout
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.Status
//import com.google.android.gms.gcm.Task
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment
import com.google.android.gms.location.places.ui.PlaceSelectionListener
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Tasks
import kotlinx.android.synthetic.main.activity_map.*
import java.io.IOException
import java.lang.Math.random
import java.security.Permission
import java.security.Permissions
import java.util.*
import kotlin.math.log

class MapActivity: AppCompatActivity(), OnMapReadyCallback, PlaceSelectionListener,GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnMarkerClickListener{

    //Variables
    val TAG:String = "MapActivity"
    val FINE_LOCATION: String = android.Manifest.permission.ACCESS_FINE_LOCATION
    val COARSE_LOCATION: String = android.Manifest.permission.ACCESS_FINE_LOCATION
    var mLocationGranted: Boolean = false
    val LOCATION_PERMISSION_REQUEST_CODE: Int = 321
    lateinit var mMap: GoogleMap
    lateinit var mFusedLocationProviderClient: FusedLocationProviderClient
    val DEFAULT_ZOOM:Float = 15f
    lateinit var currentLocation: Location
    lateinit var destinationLocation : LatLng


    //Widgets
    lateinit var mGps:ImageView
    lateinit var mchangemap:Button




    override fun onMarkerClick(marker: Marker?): Boolean {
        Log.d(TAG, "onMarkerCLick is clicked")
        showRouteToDestination(currentLocation, destinationLocation)
        return true
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
       Log.e(TAG, "onConnectionFailed : Connection Failed occured...")
    }

    override fun onPlaceSelected(place: Place?) {
        Log.d(TAG, "Place selected..")
        moveCamera(LatLng(place?.latLng?.latitude!!, place.latLng.longitude), DEFAULT_ZOOM, place.address.toString())
        destinationLocation = place.latLng
    }

    override fun onError(status: Status?) {
        Log.i(TAG, "An error occurred: " + status)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        var autocompleteFragment = fragmentManager.findFragmentById(R.id.place_autocomplete_fragment) as PlaceAutocompleteFragment

        autocompleteFragment.setOnPlaceSelectedListener(this)


        mGps = findViewById(R.id.img_gps)
        mchangemap = findViewById(R.id.button3)

        getLocationPermission()
        init()
    }

    private fun init(){
        Log.d(TAG, "init: initializing here")


//        autocompleteFragment.setOnEditorActionListener(object: TextView.OnEditorActionListener{
//            override fun onEditorAction(textView: TextView?, actionId: Int, event: KeyEvent?): Boolean {
//                if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE
//                        || event?.action == KeyEvent.ACTION_DOWN || event?.action == KeyEvent.KEYCODE_ENTER){
//
//                    geoLocate()
//                }
//               return false
//            }
//
//        })

        mchangemap.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                Log.d(TAG,"onClick: Clicked the change map type")
                chngeMapType()
            }
        })

        mGps.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                Log.d(TAG, "onClick: clicked gps location")
                getDeviceLocation()
            }

        })
        hideSoftKeyboard()
    }


//    private fun geoLocate(){
//        Log.d(TAG, "geoLocate: geolocating here")
//
//        var searchString: String = mSearchText.text.toString()
//        var geocoder: Geocoder = Geocoder(this@MapActivity)   //Look
//        var list = listOf<Address>()
//        try {
//            list = geocoder.getFromLocationName(searchString, 1)
//        }catch (e: IOException){
//            Log.e(TAG, "geoLocate: IOException ${e.message}")
//        }
//
//        if (list.size > 0){
//            var address: Address = list.get(0) as Address
//            Log.e(TAG,"geoLocate: Found a Address ${address.toString()}")
//
//            moveCamera(LatLng(address.latitude, address.longitude),DEFAULT_ZOOM,address.getAddressLine(0))
//        }
//
//    }

    override fun onMapReady(googleMap: GoogleMap?) {
        Toast.makeText(this,"Map is Ready", Toast.LENGTH_SHORT).show()

        try{
            var success: Boolean = googleMap!!.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.style_json))

            if(!success){
                Log.e(TAG, "style parsing failed")
            }
        }catch (e: Resources.NotFoundException){
           Log.e(TAG, "can't find style. Error $e")
        }

        mMap = googleMap!!


        Log.d(TAG,"onMapReady: Map is ready")

        if (mLocationGranted){
            getDeviceLocation()

            if(ActivityCompat.checkSelfPermission(this, FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this,COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                return
            }

            mMap.isMyLocationEnabled = true
            mMap.uiSettings.isMyLocationButtonEnabled = false

            init()
            mMap.setOnMarkerClickListener(this)
        }
    }


    fun initMap(){
        Log.d(TAG, "initMap: initializing Map")
        var mapFragment: SupportMapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this@MapActivity)

    }

    fun getDeviceLocation(){
        Log.d(TAG,"getDevicelocation: getting the device location")

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        try {
            if (mLocationGranted){
                var location = mFusedLocationProviderClient.lastLocation as com.google.android.gms.tasks.Task
                location.addOnCompleteListener{
                     if (it.isSuccessful){
                         Log.d(TAG, "getDeviceLocation: Found Location")
                         currentLocation = it.result as Location
                         //currentLocation = it.result
                         moveCamera(LatLng(currentLocation.latitude, currentLocation.longitude), DEFAULT_ZOOM,"My Location")
                     }else{
                         Log.d(TAG,"OnComplete: Current loation is null")
                         Toast.makeText(this,"Unable to get current location",Toast.LENGTH_SHORT).show()
                     }
                }
            }
        }catch(e: SecurityException){
             Log.e(TAG,"getDeviceLocation: SecurityException ${e.message}")
        }




    }

    private fun moveCamera(latLng: LatLng, zoom: Float, title: String){
        Log.d(TAG, "movecamera: Moving the camera to: lat: ${latLng.latitude} , lag: ${latLng.longitude}")
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,zoom))

        var c_icon = BitmapDescriptorFactory.fromResource(R.drawable.ic_marker)

        if (!title.equals("My Location")){
            var options: MarkerOptions = MarkerOptions().position(latLng).title(title).icon(c_icon)
            mMap.addMarker(options)
        }

       hideSoftKeyboard()


    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        Log.d(TAG, "onRequestPermission called")
       // super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        mLocationGranted = false

        when(requestCode){
            LOCATION_PERMISSION_REQUEST_CODE ->
            if (grantResults.size > 0){
                for (i in 0 until grantResults.size){
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED){
                        mLocationGranted = false
                        Log.d(TAG,"onRequestPermission Failed")
                        return
                    }else{
                        Log.d(TAG, "onRequestPermissionResult: Permission granted")
                        mLocationGranted = true
                        //Map Initialization
                        initMap()
                    }
                }

            }

        }

    }


    fun getLocationPermission(){
        Log.d(TAG, "getLocationPermission : here")
        var permissions = Array<String>(2){android.Manifest.permission.ACCESS_FINE_LOCATION;
            android.Manifest.permission.ACCESS_FINE_LOCATION}
        if(ContextCompat.checkSelfPermission(this.applicationContext, FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if (ContextCompat.checkSelfPermission(this.applicationContext,COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                mLocationGranted = true
                initMap()
            }else{
                ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE)
            }

        }else{
            ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE)
        }
    }

    private fun hideSoftKeyboard(){
        //this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        var inputManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = this.currentFocus
        if (view == null) {
            view = View(this)
        }
        inputManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun rand(a: Int, b: Int) = Random().nextInt(b + 1 - a) + a

    private fun chngeMapType() {
        when(rand(0, 4)){
            0-> mMap.mapType = GoogleMap.MAP_TYPE_NONE
            1 -> mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
            2 -> mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
            3 -> mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
            4 -> mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
            else -> Toast.makeText(this,"Something Went Wrong",Toast.LENGTH_LONG).show()
        }
    }

    private fun showRouteToDestination(currLocation: Location, destLocation: LatLng){
        val uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?saddr=%f,%f(%s)&daddr=%f,%f (%s)", currLocation.latitude, currLocation.longitude, "My Home.!",
                destLocation.latitude, destLocation.longitude, "Travel to Destination")

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))

        intent.`package` = "com.google.android.apps.maps"

        startActivity(intent)
    }


}