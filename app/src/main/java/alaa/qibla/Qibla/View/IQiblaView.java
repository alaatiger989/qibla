package alaa.qibla.Qibla.View;

import android.location.Location;

public interface IQiblaView {
    public void onGettingUserLocation(float direction);
    public void onShowingUserLocation(String address);
}
