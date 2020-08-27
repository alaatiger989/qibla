package alaa.qibla.Qibla.Presenter;

import android.hardware.GeomagneticField;
import android.location.Location;

import alaa.qibla.Common.Data;
import alaa.qibla.Qibla.Model.QiblaModel;
import alaa.qibla.Qibla.View.IQiblaView;

public class QiblaPresenter{

    IQiblaView qiblaView ;
    Location userLoc;
    Location destinationLoc;
    QiblaModel qiblaModel;
    public float head , bearTo;
    public QiblaPresenter(IQiblaView qiblaView) {
        this.qiblaView = qiblaView;
    }



    public void getUserLocation()
    {

        userLoc=new Location("service Provider");
        userLoc.setLongitude(Data.getLongitude());
        userLoc.setLatitude(Data.getLatitude());
        userLoc.setAltitude(Data.getAltitude());
        bearTo=userLoc.bearingTo(getDestinationLocation());

        GeomagneticField geoField = new GeomagneticField( Double.valueOf( userLoc.getLatitude() ).floatValue(), Double
                .valueOf( userLoc.getLongitude() ).floatValue(),
                Double.valueOf( userLoc.getAltitude() ).floatValue(),
                System.currentTimeMillis() );
        head -= geoField.getDeclination(); // converts magnetic north into true north
        if (bearTo < 0) {
            bearTo = bearTo + 360;
            //bearTo = -100 + 360  = 260;
        }
        getDirection(bearTo , head);
    }

    private void getDirection(float bearTo, float head) {
        float direction = bearTo - head;
        qiblaModel = new QiblaModel(bearTo , head ,direction);
        //The link between the presenter and the view
        qiblaView.onGettingUserLocation(direction);
    }

    public Location getDestinationLocation()
    {
        destinationLoc = new Location("service Provider");
        destinationLoc.setLatitude(21.422487); //kaaba latitude setting
        destinationLoc.setLongitude(39.826206); //kaaba longitude setting
        return destinationLoc;
    }

    public void setAddress(){
        if (Data.getAddress()!=null)
        {
            qiblaView.onShowingUserLocation(Data.getCity());
        }
        else{
            qiblaView.onShowingUserLocation("in progress ... ");
        }
    }

}
