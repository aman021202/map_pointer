package com.example.firebase

import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.cardview.widget.CardView
import androidx.core.content.getSystemService
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity() , OnMapReadyCallback {

    private var mapFragment: SupportMapFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupMap()

    }
    private fun setupMap(){
        mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val latLng1 = LatLng(41.077577 , 28.918875)
        val latLng2 = LatLng(41.029791 , 28.054209)
//declared the marker
        val markerView = (getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(R.layout.marker_layout, null)
        val cardView= markerView.findViewById<CardView>(R.id.markerCardView)

        //for making the marker

        val bitmap = Bitmap.createScaledBitmap(viewToBitmap(cardView)!!, cardView.width, cardView.height , false)
        //adjusting the size of marker according to the map size
        val smallMarkerIcon = BitmapDescriptorFactory.fromBitmap(bitmap)
        //drawing the marker on the google map
        googleMap.addMarker(MarkerOptions().position(latLng1).icon(smallMarkerIcon))


    }
    //view to bitmap and then imported the view and created the bitmap and then converted bitmap to canvas, drawn our view in the canvas and then again returned to bitmap
    private fun viewToBitmap(view : View): Bitmap{

        view.measure(View.MeasureSpec.UNSPECIFIED ,View.MeasureSpec.UNSPECIFIED )
        val bitmap= Bitmap.createBitmap(view.measuredWidth , view.measuredHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.layout(0 , 0 , view.measuredWidth , view.measuredHeight)
        view.draw(canvas)
        return bitmap

    }
}