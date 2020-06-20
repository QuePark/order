package com.example.order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private Context context;
    private ViewGroup viewGroup;
    private ArrayList<Menu> orders = new ArrayList<Menu>();
    private ArrayList<Menu> menus = new ArrayList<Menu>();

    private int num;

    public RecyclerViewAdapter(Context context, ArrayList<Menu> menus, ArrayList<Menu> orders, ViewGroup viewGroup){
        this.context = context;
        this.menus = menus;
        this.orders = orders;
        this.viewGroup = viewGroup;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_menu, parent, false);
            ViewHolder holder = new ViewHolder(view, viewType);

            return holder;
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_layout, parent, false);
            ViewHolder holder = new ViewHolder(view, viewType);

            return holder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if (holder.getItemViewType() == 1) {
            holder.menu_name.setText(menus.get(position-1).getName());
            holder.menu_price.setText(menus.get(position-1).getPrice() + " 원");

            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showCustomDialog(menus.get(position-1));
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        int ViewType = 1;
        if (position == 0){
            ViewType = 0;
        }
        return ViewType;
    }

    @Override
    public int getItemCount() {
        return menus.size() + 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView menu_name, menu_price;
        LinearLayout layout;

        public ViewHolder(@NonNull View itemView, int viewtype) {
            super(itemView);

            if (viewtype == 1){
                menu_name = itemView.findViewById(R.id.menu_name);
                menu_price = itemView.findViewById(R.id.menu_price);
                layout = itemView.findViewById(R.id.parent);
            }
        }
    }

    public void showCustomDialog(final Menu menu){
        num = 1;

        View dialogView = LayoutInflater.from(context).inflate(R.layout.order_dialog, viewGroup, false);

        ((TextView) dialogView.findViewById(R.id.textView3)).setText(menu.getName());
        ((TextView) dialogView.findViewById(R.id.price_text)).setText("+" + String.valueOf(menu.getPrice()) + "원");

        final TextView add_text = (TextView) dialogView.findViewById(R.id.add_text);
        final TextView price_text = (TextView) dialogView.findViewById(R.id.textView5);
        final TextView num_text = (TextView) dialogView.findViewById(R.id.num_text);

        ConstraintLayout add_button = (ConstraintLayout) dialogView.findViewById(R.id.add_button);

        add_text.setText(String.valueOf(num) + "개 담기");
        price_text.setText(String.valueOf(menu.getPrice() * num) + "원");
        num_text.setText(String.valueOf(num));

        ImageView add = (ImageView) dialogView.findViewById(R.id.add);
        ImageView remove = (ImageView) dialogView.findViewById(R.id.remove);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num++;
                add_text.setText(String.valueOf(num) + "개 담기");
                price_text.setText(String.valueOf(menu.getPrice() * num) + "원");
                num_text.setText(String.valueOf(num));
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (num != 1){
                    num--;
                    add_text.setText(String.valueOf(num) + "개 담기");
                    price_text.setText(String.valueOf(menu.getPrice() * num) + "원");
                    num_text.setText(String.valueOf(num));
                }
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialogView);

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < num; i++){
                    addOrder(menu);
                }
                alertDialog.cancel();
            }
        });
    }

    public void addOrder(Menu menu){
        boolean find = false;

        for (Menu menu1 : orders){
            if (menu1.getName().equals(menu.getName())){
                menu1.plusNum();
                find = true;
                break;
            }
        }

        if (!find){
            orders.add(new Menu(menu.getName(), menu.getPrice(), 1));
        }
    }
}
