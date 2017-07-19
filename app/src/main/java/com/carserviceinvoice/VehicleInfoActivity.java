package com.carserviceinvoice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class VehicleInfoActivity extends AppCompatActivity {

    private Button nextButton;
    private EditText customerNameET, carMakeET, carModelET, carYearET, carMileageET;
    private VehicleInfo infoCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_info);
        this.setTitle("Vehicle Information");

        customerNameET = (EditText) findViewById(R.id.name);

        carMakeET = (EditText) findViewById(R.id.make);
        carModelET = (EditText) findViewById(R.id.model);
        carYearET = (EditText) findViewById(R.id.year);
        carMileageET = (EditText) findViewById(R.id.mileage);
        nextButton = (Button) findViewById(R.id.next);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkEmptyField();
            }
        });
    }

    public void checkEmptyField(){
        // customer name must be filled all others can be left blank
        if (customerNameET.getText().toString().trim().length() == 0 )
            Toast.makeText(VehicleInfoActivity.this, "Enter Customer Name", Toast.LENGTH_SHORT).show();
        else {
            Intent intent = new Intent (VehicleInfoActivity.this, ServiceInfoActivity.class);
            infoCar = new VehicleInfo(
                    customerNameET.getText().toString().trim(),
                    carMakeET.getText().toString().trim(),
                    carModelET.getText().toString().trim(),
                    carYearET.getText().toString().trim(),
                    carMileageET.getText().toString().trim()
            );

            intent.putExtra("infoCar", infoCar);
            startActivity(intent);
        }
    }
}
