package edu.udb.mapsandroid;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

//Implementa IntentService. Tome en cuenta que debe implementar el método onHandleIntent
public class FetchPlacesService extends IntentService {
    //Hará las veces de identificador
    public static String NOTIFICATION = "edu.udb.mapsandroid";
    public static String RESULT = "dataResult";

    private ArrayList<Place> result = new ArrayList();

    //Un identificador de clase, se pasa como parámetro en súper.
    public FetchPlacesService() {
        super("fetchplaces");
    }

    //Se ejecuta al iniciar el servicio y deja los datos preparados para ser tomados con BroadCastReceiver
    @Override
    protected void onHandleIntent(Intent intent) {
        //Llenaremos el ArrayList con nuestros datos.
        result.add(new Place("Facultad de estudios Tecnológicos",13.715578, -89.152609));
        result.add(new Place("Edificio A",13.716021, -89.153399));
        result.add(new Place("Edificio B",13.715769, -89.153387));
        result.add(new Place("Facultad de estudios Tecnológicos",13.715578, -89.152609));
        result.add(new Place("Edificio A",13.716021, -89.153399));
        result.add(new Place("Edificio B",13.715769, -89.153387));
        result.add(new Place("Aula Magna A",13.715963, -89.153740));
        result.add(new Place("Aula Magna B",13.715704, -89.153672));
        result.add(new Place("Edificio R",13.716286, -89.153661));
        result.add(new Place("Biblioteca",13.716837, -89.153570));
        result.add(new Place("Colegio Don Bosco",13.716640, -89.150315));
        result.add(new Place("Canchas",13.715568, -89.152015));
        result.add(new Place("Edificio B",13.715769, -89.153387));
        publishData();
    }

    //Método custom que se encargará de publicar los datos, para que sean capturados por MapsActivity
    public void publishData(){
        Intent intent = new Intent(NOTIFICATION);
        intent.putExtra(RESULT, result);
        sendBroadcast(intent);
    }


    /**
     * A demo class that stores and retrieves data objects with each marker.
     */
    public class MarkerDemoActivity extends AppCompatActivity implements   GoogleMap.OnMarkerClickListener, OnMapReadyCallback {

        private final LatLng PERTH = new LatLng(-31.952854, 115.857342);
        private final LatLng SYDNEY = new LatLng(-33.87365, 151.20689);
        private final LatLng BRISBANE = new LatLng(-27.47093, 153.0235);

        private Marker markerPerth;
        private Marker markerSydney;
        private Marker markerBrisbane;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_markers);
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }

        /** Called when the map is ready. */
        @Override
        public void onMapReady(GoogleMap map) {
            // Add some markers to the map, and add a data object to each marker.
            markerPerth = map.addMarker(new MarkerOptions().position(PERTH).title("Perth"));
            markerPerth.setTag(0);

            markerSydney = map.addMarker(new MarkerOptions().position(SYDNEY).title("Sydney"));
            markerSydney.setTag(0);

            markerBrisbane = map.addMarker(new MarkerOptions().position(BRISBANE).title("Brisbane"));
            markerBrisbane.setTag(0);

            // Set a listener for marker click.
            map.setOnMarkerClickListener(this);
        }

        /** Called when the user clicks a marker. */
        @Override
        public boolean onMarkerClick(final Marker marker) {

            // Retrieve the data from the marker.
            Integer clickCount = (Integer) marker.getTag();

            // Check if a click count was set, then display the click count.
            if (clickCount != null) {
                clickCount = clickCount + 1;
                marker.setTag(clickCount);
                Toast.makeText(this,marker.getTitle() + " has been clicked " + clickCount + " times.", Toast.LENGTH_SHORT).show();
            }

            // Return false to indicate that we have not consumed the event and that we wish
            // for the default behavior to occur (which is for the camera to move such that the
            // marker is centered and for the marker's info window to open, if it has one).
            return false;
        }
    }
}

