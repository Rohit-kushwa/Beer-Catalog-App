package com.example.simplebeercatalogapp;

import static android.content.ContentValues.TAG;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
   Context context;
   List<PostPoJo> list;

    public RecyclerViewAdapter(Context context, List<PostPoJo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_layout, null, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        String alcoholStr = list.get(position).getAlcohol();
//        String s = String.valueOf(position);
        holder.beerName.setText(list.get(position).getName());
        holder.alcohol.setText(alcoholStr);
        holder.Brand.setText(list.get(position).getBrand());
        holder.itemNum.setText(String.valueOf(position+1));

        String num = alcoholStr.replaceAll("[^0-9]", "");
//        int AlcoholInt = (Integer.parseInt(num))/10;
        float AlcoholInt = Float.parseFloat(num.replaceAll("[^\\d.]", ""));
        AlcoholInt /= 10;
        Log.d("mytagg", "onBindViewHolder: " + AlcoholInt);

        if (AlcoholInt > 5 ) {
            holder.linearLayout.setBackgroundColor(Color.parseColor("#B30000"));
        }else{
            holder.linearLayout.setBackgroundColor(Color.parseColor("#74AB37"));
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView beerName, Brand, alcohol, itemNum;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            beerName = itemView.findViewById(R.id.BeerName);
            Brand = itemView.findViewById(R.id.brandName);
            alcohol = itemView.findViewById(R.id.alcohol);
            linearLayout = itemView.findViewById(R.id.nameLinearLayout);
            itemNum = itemView.findViewById(R.id.ItemNum);
        }
    }
}
