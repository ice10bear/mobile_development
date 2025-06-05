package com.mirea.emelyanenkomo.mireaproject;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mirea.emelyanenkomo.mireaproject.R;

public class CompassFragment extends Fragment implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private Sensor magnetometer;
    private float[] lastAccelerometer = new float[3];
    private float[] lastMagnetometer = new float[3];
    private boolean lastAccelerometerSet = false;
    private boolean lastMagnetometerSet = false;
    private float[] rotationMatrix = new float[9];
    private float[] orientation = new float[3];

    private TextView directionText;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_compass, container, false);
        directionText = view.findViewById(R.id.direction_text);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager = (SensorManager) requireContext().getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            sensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor == accelerometer) {
            System.arraycopy(event.values, 0, lastAccelerometer, 0, event.values.length);
            lastAccelerometerSet = true;
        } else if (event.sensor == magnetometer) {
            System.arraycopy(event.values, 0, lastMagnetometer, 0, event.values.length);
            lastMagnetometerSet = true;
        }

        if (lastAccelerometerSet && lastMagnetometerSet) {
            SensorManager.getRotationMatrix(rotationMatrix, null, lastAccelerometer, lastMagnetometer);
            SensorManager.getOrientation(rotationMatrix, orientation);

            float azimuthInRadians = orientation[0];
            float azimuthInDegrees = (float) Math.toDegrees(azimuthInRadians);
            if (azimuthInDegrees < 0) {
                azimuthInDegrees += 360;
            }

            String direction = getDirectionFromAngle(azimuthInDegrees);
            directionText.setText("Вы смотрите на: " + direction + "\n(" + (int) azimuthInDegrees + "°)");
        }
    }

    private String getDirectionFromAngle(float degrees) {
        if (degrees >= 337.5 || degrees < 22.5) return "Север";
        else if (degrees >= 22.5 && degrees < 67.5) return "Северо-восток";
        else if (degrees >= 67.5 && degrees < 112.5) return "Восток";
        else if (degrees >= 112.5 && degrees < 157.5) return "Юго-восток";
        else if (degrees >= 157.5 && degrees < 202.5) return "Юг";
        else if (degrees >= 202.5 && degrees < 247.5) return "Юго-запад";
        else if (degrees >= 247.5 && degrees < 292.5) return "Запад";
        else if (degrees >= 292.5 && degrees < 337.5) return "Северо-запад";
        else return "";
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}