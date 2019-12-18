package com.emedinaa.realtimetracking

import android.Manifest
import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import android.Manifest.permission
import android.Manifest.permission.ACCESS_FINE_LOCATION
import androidx.core.content.ContextCompat
import android.R.attr.path
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseReference
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationCallback
import android.content.pm.PackageManager
import android.util.Log
import com.google.firebase.database.DatabaseError

class TrackerService : Service() {

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

        requestLocationUpdates()
    }

    private fun requestLocationUpdates(){
        val request = LocationRequest()
        request.interval=10000
        request.fastestInterval=5000
        request.priority= LocationRequest.PRIORITY_HIGH_ACCURACY

        val client = LocationServices.getFusedLocationProviderClient(this)

        val permission = ContextCompat.checkSelfPermission(
            this, ACCESS_FINE_LOCATION)

        if (permission == PackageManager.PERMISSION_GRANTED) {
            client.requestLocationUpdates(request, object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult?) {
                    val ref = FirebaseDatabase.getInstance().getReference("locations/car123")
                    val location = locationResult?.lastLocation

                    location?.let {
                        Log.d("CONSOLE", "location update $it")
                        //val id = ref.child("locations").push().key
                        //ref.child("locations").child("car123").setValue(location)

                        ref.setValue(it,object:DatabaseReference.CompletionListener{
                            override fun onComplete(databaseError: DatabaseError?, databaseReference: DatabaseReference) {

                                databaseError?.let {dbError ->
                                    Log.d("CONSOLE", "error $dbError")
                                }?:run{
                                    Log.d("CONSOLE", "Good!")
                                }
                            }
                        })
                    }
                }
            }, null)
        }
    }
}
