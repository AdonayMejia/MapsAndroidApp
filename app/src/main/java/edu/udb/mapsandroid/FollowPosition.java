package edu.udb.mapsandroid;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class FollowPosition implements LocationListener {

    private LocationManager locationManager;
    Context context;
    private static int DISTANCE = 1;
    private static int TIME = 3000; //3sec
    private String provider;
    Marker point;
    private GoogleMap mMap;

    public FollowPosition(GoogleMap mMap, Context context) {
        this.setmMap(mMap);
        this.context = context;
        /********** Asigna Gps location service LocationManager object ***********/
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            provider = LocationManager.GPS_PROVIDER;
        }else{
            provider = LocationManager.NETWORK_PROVIDER;
        }
    }

    public void register(Context context) {
        //NOS MOSTRARA UN ERROR . SELECCIONE LAS OPCIONES OFRECIDAS POR EL IDE
        //PARA SOLVERTAR EL PROBLEMA
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        //INICIALIZAMOS LA LOCALIZACIÓN DEL DISPOSITIVO
        //RECUERDE LAS OPCIONES:
        //*PROVEEDOR
        //TIEMPO DE ACTUALIZACION
        //DISTANCIA MINIMA
        //LISTENER (EN ESTE CASO EL LISTENER ES LA MISMA CLASE
        //CONSULTE AL INSTRUCTOR PARA MAS DETALLES
        this.locationManager.requestLocationUpdates(provider,
                TIME,   // 30 segundos
                10, this); // 10 metros
    }

    public void unRegister(Context context) {

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        this.locationManager.removeUpdates(this);
    }



    @Override
    public void onLocationChanged(Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions currentPosition = new MarkerOptions();
        currentPosition.position(latLng);
        currentPosition.title("Aquí estoy");
        currentPosition.icon(BitmapDescriptorFactory.fromResource(R.mipmap.locationicon));
        //Quitamos el viejo punto
        if(point != null) point.remove();
        //Agregamos el nuevo punto
        point = getmMap().addMarker(currentPosition);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }
    @Override
    public void onProviderEnabled(String provider) {
    }
    @Override
    public void onProviderDisabled(String provider) {
    }
    public GoogleMap getmMap() {
        return mMap;
    }
    public void setmMap(GoogleMap mMap) {
        this.mMap = mMap;
    }

}
