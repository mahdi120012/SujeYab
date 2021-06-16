package ir.e.sujeyab.Map;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.plugins.annotation.OnSymbolClickListener;
import com.mapbox.mapboxsdk.plugins.annotation.OnSymbolLongClickListener;
import com.mapbox.mapboxsdk.plugins.annotation.Symbol;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions;
import com.mapbox.mapboxsdk.style.layers.Property;

import ir.e.sujeyab.R;
import ir.map.sdk_map.MapirStyle;
import ir.map.sdk_map.maps.MapView;

public class ShowMap extends AppCompatActivity implements MapboxMap.OnMapLongClickListener {
    ImageView imgBack;
    ConstraintLayout clSelect;
    MapboxMap map;
    Style mapStyle;
    MapView mapView;
    LatLng samplePoint = new LatLng(34.6416, 50.8746);
    LatLng selectedPoint;
    int sampleZoom = 11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_map);
        clSelect = findViewById(R.id.clSelect);
        imgBack = findViewById(R.id.imgBack);


        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        clSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Toast.makeText(ShowMap.this, "انتخاب شد", Toast.LENGTH_SHORT).show();
            }
        });


        mapView = findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {
                map = mapboxMap;
                map.setStyle(new Style.Builder().fromUri(MapirStyle.MAIN_MOBILE_VECTOR_STYLE), new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {
                        mapStyle = style;
                        zoomToSpecificLocation();
                        map.addOnMapLongClickListener(ShowMap.this);
                    }
                });
            }
        });
    }
    private void zoomToSpecificLocation() {
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(samplePoint, sampleZoom));



    }
    @Override
    public boolean onMapLongClick(@NonNull LatLng point) {

        selectedPoint = point;

        samplePoint = new LatLng(point.getLatitude(), point.getLongitude());
        addSymbolToMap();

        Toast.makeText(this, point.getLatitude() + ", " + point.getLongitude(), Toast.LENGTH_SHORT).show();

        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }


    private void addSymbolToMap() {
        mapStyle.addImage("sample_image_id", getResources().getDrawable(R.drawable.mapbox_marker_icon_default));
// create symbol manager object
        SymbolManager sampleSymbolManager = new SymbolManager(mapView, map, mapStyle);
        sampleSymbolManager.addClickListener(new OnSymbolClickListener() {
            @Override
            public void onAnnotationClick(Symbol symbol) {
                //Toast.makeText(ShowMap.this, "", Toast.LENGTH_SHORT).show();
                Toast.makeText(ShowMap.this, "This is CLICK_EVENT", Toast.LENGTH_SHORT).show();
            }
        });
        sampleSymbolManager.addLongClickListener(new OnSymbolLongClickListener() {
            @Override
            public void onAnnotationLongClick(Symbol symbol) {
                Toast.makeText(ShowMap.this, "This is LONG_CLICK_EVENT", Toast.LENGTH_SHORT).show();
            }
        });
// set non-data-driven properties, such as:
        sampleSymbolManager.setIconAllowOverlap(true);
        sampleSymbolManager.setIconRotationAlignment(Property.ICON_ROTATION_ALIGNMENT_VIEWPORT);
// Add symbol at specified lat/lon
        SymbolOptions sampleSymbolOptions = new SymbolOptions();
        sampleSymbolOptions.withLatLng(samplePoint);
        sampleSymbolOptions.withIconImage("sample_image_id");
        sampleSymbolOptions.withIconSize(1.0f);
// save created Symbol Object for later access
/*
        sampleSymbolOptions.withLatLng(samplePoint);
        sampleSymbolOptions.withIconImage("sample_image_id");
        sampleSymbolOptions.withIconSize(1.0f);
        //sampleSymbolOptions.withTextFont(new String[]{"IranSans-Noto"});
        sampleSymbolOptions.withTextColor("#ff5252");
        sampleSymbolOptions.withTextAnchor(Property.TEXT_ANCHOR_BOTTOM);
*/

        Symbol sampleSymbol = sampleSymbolManager.create(sampleSymbolOptions);

        mapStyle.removeSource("sample_image_id");
    }

}