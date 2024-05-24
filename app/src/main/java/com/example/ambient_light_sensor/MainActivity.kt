package com.example.ambient_light_sensor

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity(), SensorEventListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sensorManager = getSystemService(SENSOR_SERVICE) as? SensorManager
        if (sensorManager != null) {
            val ambientSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)

            if (ambientSensor != null) {
                sensorManager.registerListener(
                    this,
                    ambientSensor,
                    SensorManager.SENSOR_DELAY_NORMAL
                )
            }
        } else {
            Toast.makeText(this, "Sensor service not started", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onSensorChanged(event: SensorEvent?) {
        if(event?.sensor?.type==Sensor.TYPE_LIGHT) {
            findViewById<TextView>(R.id.tv_sensor_values).text =
                "X: ${event.values[0]}, Y: ${event.values[1]}, Z: ${event.values[2]}"
        }

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
}