package com.awesome.supergroup.arduinotp;

import android.content.Context;
import android.hardware.usb.UsbManager;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoho.android.usbserial.driver.UsbSerialDriver;
import com.hoho.android.usbserial.driver.UsbSerialProber;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.usb.*;

import java.io.FileDescriptor;
import java.io.IOException;

/**
 * Created by Alx on 29/04/2015.
 */
public class DisplayManager implements Runnable {

    //gestion de la distance en entrée
    private DistanceManager distance;
    //on y print la distance
    private final TextView t ;
    private final MainActivity mainActivity;
    //l'image de radar a mettre a jour
    private ImageView iv;
    private UsbManager theUsbManager;

    public DisplayManager(MainActivity theActivity, DistanceManager managerDistance, UsbManager aUsbManager) {
        this.mainActivity = theActivity;
        iv= (ImageView)mainActivity.findViewById(R.id.radarImage);
        t = (TextView)mainActivity.findViewById(R.id.Distance);
        distance = managerDistance;
        theUsbManager = aUsbManager;
    }

    @Override
    public void run() {
        while (true){

            mainActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                    int theDistance = distance.getDistance();
//                    t.setText(distance.getMetersFromCM(theDistance));
//                    if (theDistance >= 250) {
//                        iv.setImageResource(R.mipmap.green_signal);
//                    } else if (theDistance < 250 && theDistance >= 150) {
//                        iv.setImageResource(R.mipmap.yellow_signal);
//                    } else if (theDistance < 150 && theDistance >= 75) {
//                        iv.setImageResource(R.mipmap.orange_signal);
//                    } else if (theDistance < 75) {
//                        iv.setImageResource(R.mipmap.red_signal);
//                    }

                    // Find the first available driver.
// Get UsbManager from Android.
                    UsbManager manager = (UsbManager) mainActivity.getSystemService(Context.USB_SERVICE);
                    UsbAccessory accessory;
                    accessory = manager.getAccessoryList()[0];

                    ParcelFileDescriptor file = manager.openAccessory(accessory);


// Find the first available driver.



                    try {
                        //instancier input strem new fileInputStream(file) et voir si on peut lire de la data
                    }catch (Exception e){
                        t.setText(e.getMessage());
                        e.printStackTrace();
                    }
                }
            });
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
