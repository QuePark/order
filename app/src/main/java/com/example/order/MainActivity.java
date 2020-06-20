package com.example.order;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button order;

    String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.CHANGE_WIFI_STATE, Manifest.permission.ACCESS_WIFI_STATE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermissions();
    }

    public void checkPermissions(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CHANGE_WIFI_STATE) != PackageManager.PERMISSION_GRANTED){
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_WIFI_STATE) != PackageManager.PERMISSION_GRANTED){
                        start();
                    } else {
                        ActivityCompat.requestPermissions(this, permissions, 1);
                    }
                } else {
                    ActivityCompat.requestPermissions(this, permissions, 1);
                }
            } else {
                ActivityCompat.requestPermissions(this, permissions, 1);
            }
        } else {
            ActivityCompat.requestPermissions(this, permissions, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean denied = false;

        if (grantResults.length > 0){
            for (int i : grantResults){
                if (i != PackageManager.PERMISSION_GRANTED){
                    denied = true;
                    break;
                }
            }

            if (!denied){
                start();
            } else {
                finish();
            }
        }
    }

    public void start(){
        order = (Button) findViewById(R.id.order_button);

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Order_Activity.class);
                intent.putExtra("shop", "000000");
                startActivity(intent);
            }
        });
    }
}
