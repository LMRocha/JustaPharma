package iesb.justapharma.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Telephony;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import iesb.justapharma.R;
import iesb.justapharma.domain.Medicamento;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private EditText endereco;
    private Button btGetEndereco;
    private Location minhaLocalizacao;
    private Address mapEndereco;
    private Medicamento medicamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
        medicamento = new Medicamento();
        endereco = (EditText) findViewById(R.id.endereco);
        btGetEndereco = (Button) findViewById(R.id.btGetEndereco);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        medicamento.setPrincipioAtivo(extras.getString("MED_PRINCIPIO_ATIVO"));
        medicamento.setProduto(extras.getString("MED_PRODUTO"));
        medicamento.setValorExcedente(extras.getDouble("MED_EXCEDENTE"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.setMyLocationEnabled(true);

        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(loc == null) {
            loc = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }

        minhaLocalizacao = loc;
        //minhaLocalizacao = mMap.getMyLocation();
        LatLng latLng = new LatLng(minhaLocalizacao.getLatitude(), minhaLocalizacao.getLongitude());
        mMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10));

        //getLocationAddress(minhaLocalizacao);



        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {

                minhaLocalizacao = mMap.getMyLocation();
                LatLng latLng = new LatLng(minhaLocalizacao.getLatitude(), minhaLocalizacao.getLongitude());
                mMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(10));

                getLocationAddress(minhaLocalizacao);

                return true;
            }
        });
    }
        private void getLocationAddress(Location location){

        Geocoder geocoder = new Geocoder(this);
            try {
                List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                this.endereco.setText(addresses.get(0).getAddressLine(0).toString());

            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    public void selecionarEndereco(View view){

        Intent intent = new Intent(this, CadastrarEstabelecimento.class);
        intent.putExtra("MED_PRINCIPIO_ATIVO",medicamento.getPrincipioAtivo());
        intent.putExtra("MED_PRODUTO", medicamento.getProduto());
        intent.putExtra("MED_EXCEDENTE",medicamento.getValorExcedente());
        intent.putExtra("ENDERECO_RASTREADO",this.endereco.getText().toString());
        intent.putExtra("ID", "FROM_MAPS_ACTIVITY");
        startActivity(intent);


        Toast.makeText(this,"Endereço selecionado",Toast.LENGTH_SHORT).show();

    }
}
