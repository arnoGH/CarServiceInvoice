package com.carserviceinvoice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ServiceInfoActivity extends AppCompatActivity {

    private Button generateButton;
    private EditText serviceET, priceET, serviceET1, priceET1, serviceET2, priceET2, serviceET3, priceET3, serviceET4, priceET4, taxET;
    private VehicleInfo infoCar;
    private ServiceInfo infoServ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_info);
        this.setTitle("Service Information");

        Intent intent = getIntent();
        infoCar = (VehicleInfo) intent.getSerializableExtra("infoCar");

        serviceET = (EditText) findViewById(R.id.service);
        priceET = (EditText) findViewById(R.id.price);
        serviceET1 = (EditText) findViewById(R.id.service1);
        priceET1 = (EditText) findViewById(R.id.price1);
        serviceET2 = (EditText) findViewById(R.id.service2);
        priceET2 = (EditText) findViewById(R.id.price2);
        serviceET3 = (EditText) findViewById(R.id.service3);
        priceET3 = (EditText) findViewById(R.id.price3);
        serviceET4 = (EditText) findViewById(R.id.service4);
        priceET4 = (EditText) findViewById(R.id.price4);
        taxET = (EditText) findViewById(R.id.taxPerc);
        generateButton = (Button) findViewById(R.id.next);

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkEmptyFields();
            }
        });
    }

    public void checkEmptyFields(){
        // first service row must be filled all other fields can be left blank
        // if (serviceET.getText().toString().trim().length() == 0 || priceET.getText().toString().trim().length() == 0 )
           // Toast.makeText(ServiceInfoActivity.this, "Please fill in atleast first row", Toast.LENGTH_SHORT).show();
        // else {
        Intent intent = new Intent (ServiceInfoActivity.this, InvoiceActivity.class);

        infoServ = new ServiceInfo();

        // set the services
        if (serviceET.getText().toString().isEmpty())
            infoServ.setServ(" ");
        else
            infoServ.setServ(serviceET.getText().toString().trim());

        if (serviceET1.getText().toString().isEmpty())
            infoServ.setServ1(" ");
        else
            infoServ.setServ1(serviceET1.getText().toString().trim());

        if (serviceET2.getText().toString().isEmpty())
            infoServ.setServ2(" ");
        else
            infoServ.setServ2(serviceET2.getText().toString().trim());

        if (serviceET3.getText().toString().isEmpty())
            infoServ.setServ3(" ");
        else
            infoServ.setServ3(serviceET3.getText().toString().trim());

        if (serviceET4.getText().toString().isEmpty())
            infoServ.setServ4(" ");
        else
            infoServ.setServ4(serviceET4.getText().toString().trim());


        // set the prices
        if (priceET.getText().toString().isEmpty())
            infoServ.setPrice(0.0);
        else
            infoServ.setPrice(Double.parseDouble(priceET.getText().toString().trim()));

        if (priceET1.getText().toString().isEmpty())
            infoServ.setPrice1(0.0);
        else
            infoServ.setPrice1(Double.parseDouble(priceET1.getText().toString().trim()));

        if (priceET2.getText().toString().isEmpty())
            infoServ.setPrice2(0.0);
        else
            infoServ.setPrice2(Double.parseDouble(priceET2.getText().toString().trim()));

        if (priceET3.getText().toString().isEmpty())
            infoServ.setPrice3(0.0);
        else
            infoServ.setPrice3(Double.parseDouble(priceET3.getText().toString().trim()));

        if (priceET4.getText().toString().isEmpty())
            infoServ.setPrice4(0.0);
        else
            infoServ.setPrice4(Double.parseDouble(priceET4.getText().toString().trim()));

        if (taxET.getText().toString().isEmpty())
            infoServ.setTax(0.0);
        else
            infoServ.setTax(Double.parseDouble(taxET.getText().toString().trim()));

        intent.putExtra("infoServ", infoServ);
        intent.putExtra("infoCar", infoCar);
        startActivity(intent);
        // }
    }
}
