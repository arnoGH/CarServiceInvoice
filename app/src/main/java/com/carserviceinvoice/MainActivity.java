package com.carserviceinvoice;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.io.File;

public class MainActivity extends AppCompatActivity {

    private Button newInvoiceButton, savedInvoicesButton, shopInfoButton, purchaseButton;
    private SharedPreferences pref;
    private int STORAGE_PERMISSION_CODE=23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Main Menu");

        pref = getSharedPreferences("shopInfoPref", Context.MODE_PRIVATE);

        newInvoiceButton = (Button) findViewById(R.id.newInvoice);
        // savedInvoicesButton = (Button) findViewById(R.id.savedInvoices);
        shopInfoButton = (Button) findViewById(R.id.shopInfo);
        purchaseButton = (Button) findViewById(R.id.purchaseLayouts);;

        newInvoiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pref.getString("shopNameKey", "Shop's Name").toString().equals("Shop's Name")
                        || pref.getString("shopNameKey", "Shop's Name").toString().equals("") ) {
                    // || pref.getString("shopLocationKey", "City, State").toString().equals("City, State")
                    //    || pref.getString("shopLocationKey", "City, State").toString().equals("")){
                    Toast.makeText(MainActivity.this, "Enter Shop Info First", Toast.LENGTH_SHORT).show();
                }
                else
                    startActivity(new Intent(MainActivity.this, VehicleInfoActivity.class));
            }
        });


        shopInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShopInfoActivity.class);
                startActivity(intent);
            }
        });

        purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
