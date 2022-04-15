package com.doit.tete;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class RetrofitAdapter extends RecyclerView.Adapter<RetrofitAdapter.ViewHolder> {

    private List<ArrayList<Data.Device>> items;
//    private List<ArrayList<Data>> items1;

//    public RetrofitAdapter(List<ArrayList<Data>> items1) {
//        this.items1 = items1;
//    }

    public RetrofitAdapter(List<ArrayList<Data.Device>> items) {
        this.items = items;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item, parent, false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ArrayList<Data.Device> item = items.get(position);
       // ArrayList<Data> item1 = items1.get(position);
       // holder.tv.setText(item1.get(0).getCodeNm());
        holder.textView.setText(item.get(0).getDeviceDesc());
        holder.textView2.setText(item.get(0).getDeviceName());
        Log.d("Test", String.valueOf(item));
        Picasso.get()
                .load(item.get(0).getImageUrl())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
       return items.size();

    }


//    public void addItem(Data item) {               // 데이터를 가져와 arraylist에 저장할 메서드
//    items.add(item);         //list안에 가져온 파라미터  추가
//    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView, textView2,tv;
        private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
//            tv = itemView.findViewById(R.id.tv);
            textView2 = itemView.findViewById(R.id.textView2);
            imageView = itemView.findViewById(R.id.imageView);
        }

//        public void setItem(Device item) {
//            textView.setText(item.getDeviceName());
//            textView2.setText(item.getDeviceDesc());
//            // imageView.setText(item.getOpenDt());
//        }
    }

}
