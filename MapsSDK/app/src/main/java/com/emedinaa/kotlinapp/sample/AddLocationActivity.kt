package com.emedinaa.kotlinapp.sample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.emedinaa.kotlinapp.R
import com.emedinaas.kotlinapp.sample.Markers
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_add_location.*

class AddLocationActivity : AppCompatActivity(),OnMapReadyCallback {

    /*
    -12.0908801,-77.0496838,13z
     */
    private var placeLat:Double=-12.0908801
    private var placeLng:Double=-77.0496838
    private var placeName:String=""
    private var placeAddress:String=""
    private var googleMap: GoogleMap?=null
    private var marker: Marker?=null

    override fun onMapReady(map: GoogleMap?) {
        googleMap= map
        renderMarker()
        enableEvents()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_location)
        val mapFragment: SupportMapFragment = supportFragmentManager.findFragmentById(R.id.map)
                as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    private fun enableEvents(){
        /*googleMap?.setOnMapClickListener {
           it?.let {
               marker?.position=it
               Log.v("CONSOLE", "position ${marker?.position}")
           }
        }*/
        googleMap?.setOnCameraMoveListener {
            marker?.let {
                it.position= googleMap?.cameraPosition?.target
                Log.v("CONSOLE", "position ${it.position}")
            }
        }

        buttonAdd.setOnClickListener {
            //savePlacePosition(marker?.position?.latitude?:0.0, marker?.position?.longitude?:0.0)
            savePlace(marker?.position?.latitude,marker?.position?.longitude,"Lugar xyz")
            goToMap()
        }
    }

    private fun savePlace(lat:Double?, lng:Double?, name:String){
        Markers.addPlace(Place(lat?:0.0,lng?:0.0,name))
    }

    private fun renderMarker(){
        val latLng= LatLng(placeLat,placeLng)
        marker= googleMap?.addMarker(MarkerOptions().draggable(true).position(latLng).title(placeName).icon(BitmapDescriptorFactory.fromResource(R.drawable.baseline_room_black_48)))
        val center = CameraUpdateFactory.newLatLngZoom(latLng,16f)
        googleMap?.moveCamera(center)
    }

    private fun goToMap(){
        startActivity(Intent(this, GMapActivity::class.java))
    }

}
