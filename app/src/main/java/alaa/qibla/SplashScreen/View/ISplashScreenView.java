package alaa.qibla.SplashScreen.View;

import android.media.MediaPlayer;

import java.io.IOException;

public interface ISplashScreenView {
    public void onLoading(MediaPlayer audioPlayer) throws IOException;
}
