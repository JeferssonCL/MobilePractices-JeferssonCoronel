package com.example.projectmaps.util

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority

class LocationHelper(private val context: Context) {

    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(context)
    }

    private var locationCallback: LocationCallback? = null
    private var locationRequest: LocationRequest? = null

    fun startLocationUpdates(onLocationUpdate: (Location) -> Unit) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        createLocationRequest()
        createLocationCallback(onLocationUpdate)
        registerLocationUpdates()
    }

    private fun createLocationRequest() {
        locationRequest = LocationRequest.Builder(10000)
            .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
            .setMinUpdateIntervalMillis(5000)
            .build()
    }

    private fun createLocationCallback(onLocationUpdate: (Location) -> Unit) {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                for (location in result.locations) {
                    onLocationUpdate(location)
                }
            }
        }
    }

    private fun registerLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        locationRequest?.let { request ->
            locationCallback?.let { callback ->
                fusedLocationClient.requestLocationUpdates(
                    request,
                    callback,
                    Looper.getMainLooper()
                )
            }
        }
    }

    fun stopLocationUpdates() {
        locationCallback?.let {
            fusedLocationClient.removeLocationUpdates(it)
            locationCallback = null
        }
    }

    fun getLastLocation(onLocationReceived: (Location?) -> Unit) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            onLocationReceived(null)
            return
        }

        fetchLastKnownLocation(onLocationReceived)
    }

    private fun fetchLastKnownLocation(onLocationReceived: (Location?) -> Unit) {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                onLocationReceived(location)
            }
            .addOnFailureListener {
                onLocationReceived(null)
            }
    }
}