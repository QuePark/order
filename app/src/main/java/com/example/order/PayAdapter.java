package com.example.order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PayAdapter extends RecyclerView.Adapter<PayAdapter.ViewHolder>{

    private Context context;
    private ArrayList<Menu> menus = new ArrayList<Menu>();

    public PayAdapter(Context context, ArrayList<Menu> menus) {
        this.context = context;
        this.menus = menus;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pay_menu, parent, false);
        ViewHolder holder = new ViewHolder(view, viewType);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.menu_name.setText(menus.get(position).getName());
        holder.menu_num.setText("수량 : " + String.valueOf(menus.get(position).getNumber()) + "개");
        holder.menu_price.setText("+" + String.valueOf(menus.get(position).totalPrice()) + "원");
    }

    @Override
    public int getItemCount() {
        return menus.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView menu_name, menu_price, menu_num;
        LinearLayout layout;

        public ViewHolder(@NonNull View itemView, int viewtype) {
            super(itemView);

            menu_name = itemView.findViewById(R.id.menu_name);
            menu_price = itemView.findViewById(R.id.menu_price);
            menu_num = itemView.findViewById(R.id.menu_num);
            layout = itemView.findViewById(R.id.parent);
        }
    }
}
