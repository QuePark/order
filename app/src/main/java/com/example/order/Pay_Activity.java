package com.example.order;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Pay_Activity extends AppCompatActivity {

    RecyclerView recyclerView;

    TextView total_pay;

    ConstraintLayout pay_button;

    WifiManager wifiManager;

    int total;
    int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_);

        recyclerView = (RecyclerView) findViewById(R.id.pay_list);
        pay_button = (ConstraintLayout) findViewById(R.id.constraintLayout6);
        PayAdapter payAdapter = new PayAdapter(this, Order_Activity.orders);

        recyclerView.setAdapter(payAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        total_pay = (TextView) findViewById(R.id.textView14);

        total = calTotal(Order_Activity.orders);

        total_pay.setText("총 " + String.valueOf(total) + "원");

        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        pay_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BroadcastReceiver wifiScanReceiver = new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context c, Intent intent) {
                        boolean success = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false);
                        if (success) {
                            scanSuccess();
                        } else {
                            scanFailure();
                        }
                    }
                };

                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
                getApplicationContext().registerReceiver(wifiScanReceiver, intentFilter);

                boolean success = wifiManager.startScan();
                if (!success) {
                    scanFailure();
                }
            }
        });
    }

    public int calTotal(ArrayList<Menu> menus){
        int total = 0;

        for (Menu menu : menus){
            total = total + menu.totalPrice();
        }

        return total;
    }

    public void showCustomDialog(boolean in_order, int wifi1, int wifi2){
        num = 0;

        View dialogView = LayoutInflater.from(this).inflate(R.layout.pay_dialog, (ViewGroup) findViewById(android.R.id.content), false);

        ((TextView) dialogView.findViewById(R.id.price_text)).setText(String.valueOf(total) + "원");

        final TextView add_text = (TextView) dialogView.findViewById(R.id.add_text);
        final TextView price_text = (TextView) dialogView.findViewById(R.id.textView5);
        final TextView all = (TextView) dialogView.findViewById(R.id.all_table);

        ConstraintLayout add_button = (ConstraintLayout) dialogView.findViewById(R.id.add_button);

        ConstraintLayout out = (ConstraintLayout) dialogView.findViewById(R.id.out_layout);
        ConstraintLayout table = (ConstraintLayout) dialogView.findViewById(R.id.shop_layout);

        final ConstraintLayout set1 = (ConstraintLayout) dialogView.findViewById(R.id.table_set1);
        final ConstraintLayout set2 = (ConstraintLayout) dialogView.findViewById(R.id.table_set2);
        final ConstraintLayout set3 = (ConstraintLayout) dialogView.findViewById(R.id.table_set3);

        final ArrayList<TextView> tables = new ArrayList<TextView>();

        tables.add((TextView) dialogView.findViewById(R.id.table1));
        tables.add((TextView) dialogView.findViewById(R.id.table2));
        tables.add((TextView) dialogView.findViewById(R.id.table3));
        tables.add((TextView) dialogView.findViewById(R.id.table4));
        tables.add((TextView) dialogView.findViewById(R.id.table5));
        tables.add((TextView) dialogView.findViewById(R.id.table6));
        tables.add((TextView) dialogView.findViewById(R.id.table7));
        tables.add((TextView) dialogView.findViewById(R.id.table8));
        tables.add((TextView) dialogView.findViewById(R.id.table9));
        tables.add((TextView) dialogView.findViewById(R.id.table10));
        tables.add((TextView) dialogView.findViewById(R.id.table11));
        tables.add((TextView) dialogView.findViewById(R.id.table12));
        tables.add((TextView) dialogView.findViewById(R.id.table13));
        tables.add((TextView) dialogView.findViewById(R.id.table14));
        tables.add((TextView) dialogView.findViewById(R.id.table15));
        tables.add((TextView) dialogView.findViewById(R.id.table16));

        for (int i = 0; i < tables.size(); i++){
            final int number = i;
            TextView table_button = tables.get(i);
            table_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tables.get(num).setBackgroundResource(R.drawable.not_choosen_table);
                    tables.get(num).setTextColor(0xff000000);

                    num = number;

                    tables.get(num).setBackgroundResource(R.drawable.number_back);
                    tables.get(num).setTextColor(0xff00BCD4);
                }
            });
        }

        if (in_order){
            out.setVisibility(View.GONE);
            table.setVisibility(View.VISIBLE);
        } else {
            out.setVisibility(View.VISIBLE);
            out.setVisibility(View.GONE);
        }

        if (wifi1 > -60){
            //set1
            set1.setVisibility(View.VISIBLE);
            if (wifi1 >= - 44){
                num = 3;
            } else if (wifi1 >= -46){
                num = 0;
            } else if (wifi1 == -47){
                num = 1;
            } else if (wifi1 == -48){
                num = 4;
            } else {
                num = 2;
            }
        } else if (wifi2 < -70){
            //set2
            set2.setVisibility(View.VISIBLE);
            if (wifi1 > -74){
                num = 10;
            } else if (wifi1 > -75){
                num  = 9;
            } else if (wifi1 > -76){
                num = 8;
            } else if (wifi1 > -77){
                num = 7;
            } else if (wifi1 > -78){
                num = 6;
            } else {
                num = 5;
            }
        } else {
            //set3
            set3.setVisibility(View.VISIBLE);
            if (wifi2 >= - 60){
                num = 11;
            } else if (wifi2 >= -64){
                num = 12;
            } else if (wifi2 >= -67){
                num = 13;
            } else if (wifi1 < -75){
                num = 14;
            } else {
                num = 15;
            }
        }

        tables.get(num).setBackgroundResource(R.drawable.number_back);
        tables.get(num).setTextColor(0xff00BCD4);

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                all.setVisibility(View.GONE);
                set1.setVisibility(View.VISIBLE);
                set2.setVisibility(View.VISIBLE);
                set3.setVisibility(View.VISIBLE);
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.cancel();
                setResult(1111);
                finish();
            }
        });
    }

    private void scanSuccess() {
        List<ScanResult> results = wifiManager.getScanResults();
        String wifis = "";
        boolean in_order = false;
        int wifi1 = -100;
        int wifi2 = -100;

        for (ScanResult result : results){
            if (result.SSID.equals("TP-LINK_7568") && result.BSSID.equals("18:a6:f7:91:75:68")){
                in_order = true;
                wifi1 = result.level;
            }

            if (result.SSID.equals("BOKSOONDOGA") && result.BSSID.equals("88:36:6c:90:93:dc")){
                in_order = true;
                wifi2 = result.level;
            }
        }

        showCustomDialog(in_order, wifi1, wifi2);
    }

    private void scanFailure() {
        Toast.makeText(this, "잠시후 다시 이용해주세요.", Toast.LENGTH_SHORT).show();
    }

    public void changeTable(final int pre, final int cur){

    }
}
