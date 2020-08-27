package alaa.qibla.SplashScreen.View;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import alaa.qibla.InternetConnectionHandler.Presenter.InternetConnection;
import alaa.qibla.InternetConnectionHandler.View.InternetDisconnectedPage;
import alaa.qibla.Qibla.View.MainActivity;
import alaa.qibla.R;
import alaa.qibla.SplashScreen.Presenter.SplashScreenPresenter;

public class SplashScreen extends AppCompatActivity implements ISplashScreenView{

    SplashScreenPresenter splashScreenPresenter;
    MediaPlayer audioPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        if(InternetConnection.isConnectedToNetwork(SplashScreen.this))
        {
            splashScreenPresenter = new SplashScreenPresenter(this ,SplashScreen.this);
            try {
                splashScreenPresenter.gettingAudio();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            Intent intent = new Intent(SplashScreen.this , InternetDisconnectedPage.class);
            startActivity(intent);
            finish();
        }

    }
    @Override
    public void onLoading(MediaPlayer audioPlayer) throws IOException {
        this.audioPlayer = audioPlayer;
        audioPlayer.start();
        audioPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Intent intent = new Intent(SplashScreen.this , MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(SplashScreen.this , "Please Wait ... " , Toast.LENGTH_LONG).show();

    }
}
