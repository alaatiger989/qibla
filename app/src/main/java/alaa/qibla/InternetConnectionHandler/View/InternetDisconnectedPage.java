package alaa.qibla.InternetConnectionHandler.View;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import alaa.qibla.R;
import alaa.qibla.SplashScreen.View.SplashScreen;


public class InternetDisconnectedPage extends AppCompatActivity {

    public WifiManager wifiManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet_connection);

        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        /*Thread th = new Thread() {
            public void run() {
                try {
                    sleep(4000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally{
                   //Intent intent = new Intent(InternetDisconnectedPage.this , (Fragment)PrayTimes.class);
                   // startActivity(intent)

                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.container_internet_connection, new BottomNavigationButton()).addToBackStack(null).commit();



                }
            }

        };
        th.start();*/


    }

    @Override
    protected void onStart() {

        super.onStart();
        IntentFilter intentFilter = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(wifiStateReceiver , intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(wifiStateReceiver);
    }

    private BroadcastReceiver wifiStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int wifiStateExtra = intent.getIntExtra(wifiManager.EXTRA_WIFI_STATE , wifiManager.WIFI_STATE_UNKNOWN);
            switch (wifiStateExtra)
            {
                case WifiManager.WIFI_STATE_ENABLED:

                Intent intent1 = new Intent(InternetDisconnectedPage.this , SplashScreen.class);
                startActivity(intent1);


                    break;
                case WifiManager.WIFI_STATE_DISABLED:
                    Toast.makeText(InternetDisconnectedPage.this , "Please Check your Internet Connection " , Toast.LENGTH_LONG).show();
                    break;

            }
        }
    };
    public void onPause(){

        //new CurrentLocation().getLocationOfuser(MainActivity.this); // The permissions
        super.onPause();
        finish();
    }

}
