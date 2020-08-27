package alaa.qibla.SplashScreen.Presenter;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Environment;

import java.io.IOException;

import alaa.qibla.Qibla.View.MainActivity;
import alaa.qibla.R;
import alaa.qibla.SplashScreen.View.ISplashScreenView;
import alaa.qibla.SplashScreen.View.SplashScreen;

public class SplashScreenPresenter {

    ISplashScreenView splashScreenView;
    public MediaPlayer audioPlayer;
    public Context context;
    public SplashScreenPresenter(ISplashScreenView splashScreenView , Context context) {
        this.splashScreenView = splashScreenView;
        this.context = context;
    }

    public void gettingAudio() throws IOException {
        audioPlayer = MediaPlayer.create(context , R.raw.loading_audio);
        audioPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        splashScreenView.onLoading(audioPlayer);
    }

}
