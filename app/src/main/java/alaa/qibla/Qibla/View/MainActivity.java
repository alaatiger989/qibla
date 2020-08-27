package alaa.qibla.Qibla.View;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import alaa.qibla.Common.Data;
import alaa.qibla.InternetConnectionHandler.Presenter.InternetConnection;
import alaa.qibla.InternetConnectionHandler.View.InternetDisconnectedPage;
import alaa.qibla.Qibla.Presenter.CurrentLocation;
import alaa.qibla.Qibla.Presenter.Permissions;
import alaa.qibla.Qibla.Presenter.QiblaPresenter;
import alaa.qibla.R;
import alaa.qibla.SplashScreen.View.SplashScreen;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements SensorEventListener, IQiblaView {

    @BindView(R.id.addressText)
    TextView textAddress;
    @BindView(R.id.qibla_compass)
    ImageView image;
    @BindView(R.id.textAngle)
    TextView textAngle;
    @BindView(R.id.needle_in_compass)
    ImageView needleInCompass;
    @BindView(R.id.needle)
    RelativeLayout mPointer;
    private static SensorManager mSensorManager;
    private Sensor sensor;
    private QiblaPresenter qiblaPresenter;
    private float direction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        new Permissions().requestLocationStoragePermission(MainActivity.this);
        if(InternetConnection.isConnectedToNetwork(MainActivity.this))
        {
            new CurrentLocation().getLocationOfuser(MainActivity.this);
            qiblaPresenter = new QiblaPresenter(this);
            qiblaPresenter.getUserLocation();
            qiblaPresenter.setAddress();


            mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
            sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
            if (sensor != null) {
                // for the system's orientation sensor registered listeners
                mSensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);//SensorManager.SENSOR_DELAY_Fastest
            } else {
                Toast.makeText(getApplicationContext(), "Not Supported", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Intent intent = new Intent(MainActivity.this , InternetDisconnectedPage.class);
            startActivity(intent);
            finish();
        }



    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME); //SensorManager.SENSOR_DELAY_Fastest
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int degree = Math.round(sensorEvent.values[0]);

        // create a rotation animation (reverse turn degree degrees)
        RotateAnimation ra = new RotateAnimation(-degree - 40, direction, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        // how long the animation will take place
        ra.setDuration(210);
        // set the animation after the end of the reservation status
        ra.setFillAfter(true);
        // Start the animation
        mPointer.startAnimation(ra);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onGettingUserLocation(float direction) {
        this.direction = direction;
    }

    @Override
    public void onShowingUserLocation(String address) {
        textAddress.setText(address);
    }


}
