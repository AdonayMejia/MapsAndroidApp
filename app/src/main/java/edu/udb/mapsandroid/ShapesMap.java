package edu.udb.mapsandroid;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import java.util.ArrayList;

/**
 * Permite dibujar una serie de figuras geométricas en un mapa
 */
public class ShapesMap {
    GoogleMap mMap;

    public ShapesMap(GoogleMap mMap){
        this.mMap = mMap;
    }
    /**
     *
     * @param points
     * @param width
     * @param color
     */
    /*Dibujamos líneas a partir de la opción PolylineOptions.addAll*/
    public void drawLine(ArrayList<LatLng> points,int width, int color){
        PolylineOptions options= new PolylineOptions();
        options.addAll(points);
        options.width(width);
        options.color(color);
        Polyline polyline = mMap.addPolyline(options);
        polyline.setColor(color);
    }
    /**
     * Dibujamos un polígono a partir de la opción PolygonOptions.addAll
     * @param points
     * @param strokeWidth
     * @param strokeColor
     * @param fillColor
     */
    public void drawPoligon(ArrayList<LatLng> points, int strokeWidth, int strokeColor, int fillColor){
        PolygonOptions options = new PolygonOptions();
        options.addAll(points);
        Polygon polygon= mMap.addPolygon(options);
        polygon.setStrokeColor(strokeColor);
        polygon.setStrokeWidth(strokeWidth);
        polygon.setFillColor(fillColor);
        polygon.setGeodesic(true);
    }
    /**
     * Dibujamos un círculo a partir de CircleOptions
     * @param point
     * @param radius
     * @param strokeColor
     * @param strokeWidth
     * @param fillColor
     */
    public void drawCircle(LatLng point, int radius, int strokeColor, int strokeWidth, int fillColor){
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(point);
        circleOptions.radius(radius);
        circleOptions.strokeColor(strokeColor);
        circleOptions.strokeWidth(strokeWidth);
        circleOptions.fillColor(fillColor);
        Circle circle = mMap.addCircle(circleOptions);
    }

}
