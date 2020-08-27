package alaa.qibla.Qibla.Presenter;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import alaa.qibla.Common.Data;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

public class Permissions extends AppCompatActivity {
    private int STORAGE_LOCATION_PERMISSION_CODE = 1;

    public void requestLocationStoragePermission(final Activity context) {
        if( ActivityCompat.shouldShowRequestPermissionRationale(context , Manifest.permission.WRITE_EXTERNAL_STORAGE))
        {
            new AlertDialog.Builder(context)
                    .setTitle("Permission needed")
                    .setMessage("This Permission needed To save Files to your Phone")
                    .setPositiveButton("ok " , new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(context , new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE ,  READ_EXTERNAL_STORAGE , ACCESS_FINE_LOCATION} , STORAGE_LOCATION_PERMISSION_CODE);
                            new CurrentLocation().getLocationOfuser(context);
                            Toast.makeText(context , "Welcomce From " + Data.getCity() , Toast.LENGTH_LONG).show();
                        }
                    }).setNegativeButton("Cancel" , new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    context.finish();
                }
            }).create().show();

        }
        else{
            ActivityCompat.requestPermissions(context , new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE ,  READ_EXTERNAL_STORAGE , ACCESS_FINE_LOCATION} , STORAGE_LOCATION_PERMISSION_CODE);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == STORAGE_LOCATION_PERMISSION_CODE)
        {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {

                Toast.makeText(getApplicationContext() , "Permission Granted" , Toast.LENGTH_LONG).show();
                Log.i("alaa2" , "Granted");

            }
            else{
                Toast.makeText(getApplicationContext() , "Permission Denied" , Toast.LENGTH_LONG).show();
                Log.i("alaa2" , "Revoked");


            }
        }
    }
}
