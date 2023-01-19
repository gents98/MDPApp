package com.example.mdpapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textview;
    private Button btn;
    private BluetoothAdapter bluetoothAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setListeners();

        }



    private void setListeners() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.BLUETOOTH_CONNECT)==PackageManager.PERMISSION_DENIED) {
                if (Build.VERSION.SDK_INT >= 31) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 100);
                    return;
                }
            }
            BluetoothManager bluetoothManager=(BluetoothManager) getSystemService((Context.BLUETOOTH_SERVICE));

            if (Build.VERSION.SDK_INT>=31){
                bluetoothAdapter=bluetoothManager.getAdapter();
            }else{
                bluetoothAdapter=bluetoothAdapter.getDefaultAdapter();
            }

            if( bluetoothAdapter.isEnabled()){
                bluetoothAdapter.disable();
                imageView.setBackgroundResource(R.drawable.bluetooth_off);
                textview.setText("Bluetooth is off");


            }else{
                bluetoothAdapter.enable();
                imageView.setBackgroundResource(R.drawable.bluetooth_on);
                textview.setText("Bluetooth is on");
            }
            }
        });
    }

    private void initViews() {
//        imageView=findViewById(R.id.image);
//        textview=findViewById(R.id.textView);
//        btn= (Button) findViewById(R.id.btn);
    }


}

