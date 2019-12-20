package com.emedinaa.realtimetracking

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*
import android.app.ActivityManager
import android.view.View

class MainActivity : AppCompatActivity(),ActivityCompat.OnRequestPermissionsResultCallback {

    private val PERMISSION_REQUEST = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonStart.setOnClickListener {
            startService(Intent(MainActivity@this, TrackerService::class.java))
            updateButtons()
        }

        buttonStop.setOnClickListener {
            stopService(Intent(MainActivity@this, TrackerService::class.java))
            updateButtons()
        }

        buttonStart.visibility = View.GONE
        buttonStop.visibility = View.GONE

        updateButtons()
    }

    private fun updateButtons(){
        if(isServiceRunning(TrackerService::class.java)){
            buttonStop.visibility = View.VISIBLE
            buttonStart.visibility= View.GONE
        }else{
            buttonStop.visibility = View.GONE
            buttonStart.visibility= View.VISIBLE
        }
    }

    override fun onResume() {
        super.onResume()
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
        } else {
            requestPermission()
        }
    }

    private fun requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_FINE_LOCATION)) {

        }else{

            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),PERMISSION_REQUEST)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == PERMISSION_REQUEST) {
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
               Toast.makeText(this, "Permiso denegado",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isServiceRunning(serviceClass: Class<*>): Boolean {
        val manager = getSystemService(ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                return true
            }
        }
        return false
    }
}
