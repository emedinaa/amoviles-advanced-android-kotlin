package com.emedinaa.kotlinapp.sample

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import com.emedinaa.kotlinapp.R
import com.emedinaas.kotlinapp.sample.Markers


class GMapActivity : AppCompatActivity(), OnMapReadyCallback {

    private var placesMap:MutableMap<Marker, Place> = mutableMapOf()
    private var places:List<Place> = emptyList()
    private var googleMap: GoogleMap?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gmap)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val mapFragment: SupportMapFragment = supportFragmentManager.findFragmentById(R.id.map)
                as SupportMapFragment
        mapFragment.getMapAsync(this)

        loadPlaces()
    }

    override fun onMapReady(map: GoogleMap?) {
        googleMap= map

        googleMap?.setOnMarkerClickListener {
            val place:Place?= placesMap[it]
            place?.let {
                showPlace(it)
            }
            false }

        googleMap?.setOnInfoWindowClickListener{
            val place:Place?= placesMap[it]
            place?.let {
                goToGoogleMaps(it)
            }
        }

        renderMarkers()
    }


    private fun loadPlaces(){
        places= Markers.places()
    }

    private fun showPlace(place:Place){
        Toast.makeText(this,"Place : $place",Toast.LENGTH_LONG).show()
    }

    private fun goToGoogleMaps(place:Place){
        //https://developers.google.com/maps/documentation/urls/android-intents
        val gmmIntentUri = Uri.parse("geo:".plus(place.lat).plus(",").plus(place.lng))
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        if (mapIntent.resolveActivity(packageManager) != null) {
            startActivity(mapIntent)
        }

    }

    private fun  renderMarkers(){
        val builder = LatLngBounds.Builder()
        var latLng=LatLng(0.0,0.0)
        places.forEach {
            Log.v("CONSOLE","place $it")
            latLng=LatLng(it.lat,it.lng)
            builder.include(latLng)
            val placeName= it.name
            val marker= googleMap?.addMarker(MarkerOptions().draggable(true).position(latLng).title(placeName).icon(BitmapDescriptorFactory.fromResource(R.drawable.baseline_room_black_48)))
            marker?.let {m->
                placesMap[m]=it
            }

        }
        val bounds = builder.build()
        centerProvidersMap(bounds)
    }

    private fun centerProvidersMap(bounds: LatLngBounds) {
        val width = resources.displayMetrics.widthPixels
        val height = resources.displayMetrics.heightPixels
        val padding = (width * 0.20).toInt() // offset from edges of the map 12% of screen

        val cu = CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding)
        googleMap?.animateCamera(cu)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
