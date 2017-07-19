package com.carserviceinvoice;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ShopInfoActivity extends AppCompatActivity {

    private Button backButton, saveButton;
    private EditText shopNameET, shopLocationET;
    private String shopName, shopLocation;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_info);
        this.setTitle("Shop Info");

        backButton = (Button) findViewById(R.id.goBack);
        saveButton = (Button) findViewById(R.id.save);

        shopNameET = (EditText) findViewById(R.id.nameOfShop);
        shopLocationET = (EditText) findViewById(R.id.location);

        pref = getSharedPreferences("shopInfoPref", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();

        shopNameET.setText(pref.getString("shopNameKey", "Shop's Name"));
        shopLocationET.setText(pref.getString("shopLocationKey", "City, State"));

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShopInfoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (shopNameET.getText().toString().equals("Shop's Name"))
                    Toast.makeText(ShopInfoActivity.this, "Enter Valid Shop Name", Toast.LENGTH_SHORT).show();
                else {
                    shopName = shopNameET.getText().toString();
                    shopLocation = shopLocationET.getText().toString();

                    editor.putString("shopNameKey", shopName);
                    editor.putString("shopLocationKey", shopLocation);
                    editor.commit();

                    Toast.makeText(ShopInfoActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
