package com.example.order;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Order_Activity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference shoplist = database.getReference("shop");

    static ArrayList<Menu> orders = new ArrayList<Menu>();

    FloatingActionButton fab;

    TextView shopname, opentext;
    TextView toolname;

    TextView info, call;
    ConstraintLayout info_layout;

    RecyclerView recyclerView;

    String shopnum;
    Shop shop;

    ConstraintLayout splash;

    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_);

        back = (ImageView) findViewById(R.id.back_button);
        splash = (ConstraintLayout) findViewById(R.id.splash_layout);
        shopnum = getIntent().getStringExtra("shop");
        shopname = (TextView) findViewById(R.id.shop_name);
        opentext = (TextView) findViewById(R.id.open_text);
        toolname = (TextView) findViewById(R.id.tool_name);
        call = (TextView) findViewById(R.id.call_text);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        info_layout = (ConstraintLayout) findViewById(R.id.info_layout);
        info = (TextView) findViewById(R.id.detail_text);

        fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (info_layout.getVisibility() == View.GONE){
                    info.setText("숨기기");
                    info_layout.setVisibility(View.VISIBLE);
                } else {
                    info.setText("자세히 보기");
                    info_layout.setVisibility(View.GONE);
                }
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Order_Activity.this, "" + orders.size(), Toast.LENGTH_SHORT).show();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (orders.size() > 0){
                    Intent intent = new Intent(getApplicationContext(), Pay_Activity.class);
                    startActivityForResult(intent, 1111);
                } else {
                    Toast.makeText(Order_Activity.this, "매뉴를 선택해 주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        shoplist.child(shopnum).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                setShop(dataSnapshot.getValue(Shop.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Order_Activity.this, "데이터를 불러올 수 없습니다. 다시 시도해 주세요.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    public void setShop(Shop temp){
        shop = temp;

        shopname.setText(shop.getName());
        toolname.setText(shop.getName());

        if (shop.getOpen()){
            opentext.setText("영업중");
            opentext.setBackgroundResource(R.drawable.shop_open);
            opentext.setTextColor(0xffffffff);
            opentext.setTypeface(Typeface.DEFAULT_BOLD);
        } else {
            opentext.setText("영업 준비중");
        }

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, shop.getMenus(), orders, (ViewGroup) findViewById(android.R.id.content));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        splash.setVisibility(View.GONE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1111){
            if (resultCode == 1111){
                finish();
            }
        }
    }
}
