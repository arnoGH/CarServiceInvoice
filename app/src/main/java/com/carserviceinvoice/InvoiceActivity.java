package com.carserviceinvoice;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.Manifest;

public class InvoiceActivity extends AppCompatActivity {

    private TextView dateTV, shopNameTV, shopLocationTV, customerNameTV, carMakeTV,
            carModelTV, carYearTV, carMileageTV, servTV, serv1TV, serv2TV, serv3TV,
            serv4TV, priceTV, price1TV, price2TV, price3TV, price4TV, subTotalTV,
            taxTV, totalTV;
    private LinearLayout layout;
    private Button saveButton, sendButton;
    private VehicleInfo infoCar;
    private ServiceInfo infoServ;
    private SharedPreferences pref;
    private View screenCapt;
    private int STORAGE_PERMISSION_CODE=23;
    private boolean isSaved = false;
    private File file;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        this.setTitle("Invoice");

        screenCapt = findViewById(R.id.linearLayoutNoButton);

        // vehicle and service objects
        Intent intent = getIntent();
        infoCar = (VehicleInfo) intent.getSerializableExtra("infoCar");
        infoServ = (ServiceInfo) intent.getSerializableExtra("infoServ");

        // shop information preference
        pref = getSharedPreferences("shopInfoPref", Context.MODE_PRIVATE);

        dateTV = (TextView) findViewById(R.id.date);
        String str = DateFormat.getDateInstance().format(new Date());
        dateTV.setText(str);

        shopNameTV = (TextView) findViewById(R.id.shopName);
        shopNameTV.setText(pref.getString("shopNameKey", "Shop's Name"));

        shopLocationTV = (TextView) findViewById(R.id.cityState);
        shopLocationTV.setText(pref.getString("shopLocationKey", "City, State"));

        customerNameTV = (TextView) findViewById(R.id.customerNameActual);
        customerNameTV.setText(infoCar.getCustName());

        carMakeTV = (TextView) findViewById(R.id.carMakeActual);
        carMakeTV.setText(infoCar.getCarMake());

        carModelTV = (TextView) findViewById(R.id.carModelActual);
        carModelTV.setText(infoCar.getCarModel());

        carYearTV = (TextView) findViewById(R.id.yearActual);
        carYearTV.setText(infoCar.getYear());

        carMileageTV = (TextView) findViewById(R.id.mileageActual);
        carMileageTV.setText(infoCar.getMileage());

        // create service textviews
        servTV = (TextView) findViewById(R.id.serv);
        serv1TV = (TextView) findViewById(R.id.serv1);
        serv2TV = (TextView) findViewById(R.id.serv2);
        serv3TV = (TextView) findViewById(R.id.serv3);
        serv4TV = (TextView) findViewById(R.id.serv4);

        // create price textviews
        priceTV = (TextView) findViewById(R.id.priceServ);
        price1TV = (TextView) findViewById(R.id.priceServ1);
        price2TV = (TextView) findViewById(R.id.priceServ2);
        price3TV = (TextView) findViewById(R.id.priceServ3);
        price4TV = (TextView) findViewById(R.id.priceServ4);

        // set service texts
        servTV.setText(infoServ.getServ());
        serv1TV.setText(infoServ.getServ1());
        serv2TV.setText(infoServ.getServ2());
        serv3TV.setText(infoServ.getServ3());
        serv4TV.setText(infoServ.getServ4());

        // set price texts
        if (infoServ.getPrice() == 0.0)
            priceTV.setText("");
        else
            priceTV.setText(String.valueOf(infoServ.getPrice()));

        if (infoServ.getPrice1() == 0.0)
            price1TV.setText("");
        else
            price1TV.setText(String.valueOf(infoServ.getPrice1()));

        if (infoServ.getPrice2() == 0.0)
            price2TV.setText("");
        else
            price2TV.setText(String.valueOf(infoServ.getPrice2()));

        if (infoServ.getPrice3() == 0.0)
            price3TV.setText("");
        else
            price3TV.setText(String.valueOf(infoServ.getPrice3()));

        if (infoServ.getPrice4() == 0.0)
            price4TV.setText("");
        else
            price4TV.setText(String.valueOf(infoServ.getPrice4()));

        subTotalTV = (TextView) findViewById(R.id.subtotalActual);
        subTotalTV.setText(String.valueOf(infoServ.getSubTotal()));

        taxTV = (TextView) findViewById(R.id.taxActual);
        taxTV.setText(String.valueOf(infoServ.getTaxDollars()));

        totalTV = (TextView) findViewById(R.id.totalActual);
        totalTV.setText(String.valueOf(infoServ.getTotal()));

        saveButton = (Button) findViewById(R.id.saveInvoice);
        sendButton = (Button) findViewById(R.id.sendInvoice);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // get users permission before writing to memory
                ActivityCompat.requestPermissions(InvoiceActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);

                screenCapt.setDrawingCacheEnabled(true);
                screenCapt.buildDrawingCache(true);
                screenCapt.setBackgroundColor(Color.parseColor("#ffffff"));
                Bitmap image = Bitmap.createBitmap(screenCapt.getDrawingCache());
                screenCapt.setDrawingCacheEnabled(false);

                // path to sd card
                File path = Environment.getExternalStorageDirectory();

                // create folder
                File dir = new File(path + "/Invoices/" );
                dir.mkdirs();

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
                String  currentTimeStamp = dateFormat.format(new Date());
                file = new File(dir, "inv" + currentTimeStamp + ".png");

                OutputStream out = null;

                try {
                    out = new FileOutputStream(file);
                    image.compress(Bitmap.CompressFormat.PNG, 100, out);
                    out.flush();
                    out.close();
                    sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file.getAbsolutePath())));
                    isSaved = true;
                    Toast.makeText(InvoiceActivity.this, "Invoice Saved to Your Gallery", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSaved == true) {
                    // app permission to read from memory
                    ActivityCompat.requestPermissions(InvoiceActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);

                    // create intent
                    Intent picMessageIntent = new Intent(Intent.ACTION_SEND);
                    picMessageIntent.setType("image/png");
                    picMessageIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
                    startActivity(Intent.createChooser(picMessageIntent, "Share Via"));

                    // just in case invoice doesn't attach message
                    Toast.makeText(InvoiceActivity.this, "If attachment fails to load, manually attach invoice from gallery", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(InvoiceActivity.this, "Save Invoice First", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}


