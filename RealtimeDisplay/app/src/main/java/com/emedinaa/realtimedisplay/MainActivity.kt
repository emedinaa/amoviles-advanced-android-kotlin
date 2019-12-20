package com.emedinaa.realtimedisplay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.FirebaseDatabase

import android.util.Log
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.*
import com.google.android.gms.maps.model.LatLng


class MainActivity : AppCompatActivity() ,OnMapReadyCallback {

    private var googleMap: GoogleMap?=null
    private var mMarkers = mutableMapOf<String?,Marker?>()
    private  var polyline:Polyline ?=null

    override fun onMapReady(map: GoogleMap?) {
        googleMap= map
        polyline = googleMap?.addPolyline(PolylineOptions())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mapFragment: SupportMapFragment = supportFragmentManager.findFragmentById(R.id.map)
                as SupportMapFragment
        mapFragment.getMapAsync(this)

        subscribeToUpdates()
    }


    private fun subscribeToUpdates(){

        val ref = FirebaseDatabase.getInstance().reference
        ref.child("locations").addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
                setMarker(dataSnapshot)
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, previousChildName: String?) {
                setMarker(dataSnapshot)
            }

            override fun onChildMoved(dataSnapshot: DataSnapshot, previousChildName: String?) {}

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}

            override fun onCancelled(error: DatabaseError) {
                Log.d("CONSOLE", "Failed to read value.", error.toException())
            }
        })
    }

    private fun setMarker(dataSnapshot: DataSnapshot) {
        // When a location update is received, put or update
        // its value in mMarkers, which contains all the markers
        // for locations received, so that we can build the
        // boundaries required to show them all on the map at once
        val key = dataSnapshot.key
        val value = dataSnapshot.value as HashMap<String, Any>?
        Log.v("CONSOLE", "key ${dataSnapshot.key} value ${dataSnapshot.value}")

        value?.let { itValue->
            val lat = itValue["latitude"].toString().toDouble()
            val lng = itValue["longitude"].toString().toDouble()

            val location = LatLng(lat, lng)

            if (!mMarkers.containsKey(key)) {
                mMarkers[key]= googleMap?.addMarker(MarkerOptions().title(key).position(location))
            } else {
                mMarkers[key]?.setPosition(location)
            }

            val builder = LatLngBounds.Builder()

            Log.v("CONSOLE","markers size ${mMarkers.size}")
            for ((_,v) in mMarkers){
                v?.let {
                    builder.include(it.position)
                }

            }
            //googleMap?.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 10))
            googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(location,13f))
            addPoint(location)
        }
    }

    private fun addPoint(latLng:LatLng){
        val points = polyline?.points
        if(points?.contains(latLng)==true){
            return
        }
        points?.add(latLng)
        polyline?.points= points
    }
}
