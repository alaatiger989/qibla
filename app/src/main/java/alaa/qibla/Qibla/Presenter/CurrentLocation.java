package alaa.qibla.Qibla.Presenter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Environment;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Locale;

import alaa.qibla.Common.Data;
import alaa.qibla.Qibla.View.IQiblaView;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class CurrentLocation {
    TextView locationtxt;
    Double latitude = 0.0;
    Double longitude = 0.0;
    Double altitude = 0.0;
    static String TAG = "MainActivity";
    Location gps_loc = null , network_loc = null , final_loc = null;



    public  void getLocationOfuser(Activity context) {

        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if(ActivityCompat.checkSelfPermission(context , ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context , ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context , Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED)
        {

        }
        else{
            new Permissions().requestLocationStoragePermission(context); // To Request the body of the permission
        }

        try{
            //gps_loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            network_loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        if(gps_loc != null)
        {
            final_loc = gps_loc;
            latitude = final_loc.getLatitude();
            longitude = final_loc.getLongitude();
            altitude = final_loc.getAltitude();
        }
        else if(network_loc != null )
        {
            final_loc = network_loc;
            latitude = final_loc.getLatitude();
            longitude = final_loc.getLongitude();
            altitude = final_loc.getAltitude();
        }
        else{
            latitude = 0.0;
            longitude = 0.0;
        }
        try{
            Geocoder geocoder = new Geocoder(context , Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(latitude , longitude , 1);

            Data.setLatitude(latitude);
            Data.setLongitude(longitude);
            Data.setAltitude(altitude);

            if(addresses != null && addresses.size() > 0)
            {
                String address = addresses.get(0).getAddressLine(0);
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postal_code = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName();

                setDataToDataClass(address , city , state , country , postal_code , knownName );
                locationtxt.setText("Address  : " + address + "\n\n" +
                        "State  : " + state + "\n\n" +
                        "City  : " + city + "\n\n" +
                        "Country Name   : " + country + "\n\n" +
                        "Postal Code  : " + postal_code + "\n\n"+
                        "Known Name  : " + knownName + "\n\n" );
            }


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }
    private void setDataToDataClass(String address, String city, String state, String country, String postal_code, String knownName) {

        Data.setAddress(address);
        Data.setCity(city);
        Data.setState(state);
        Data.setCountry(country);
        Data.setPostalCode(postal_code);
        Data.setKnownName(knownName);
    }
}
