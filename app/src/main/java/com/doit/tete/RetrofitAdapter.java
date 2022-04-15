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

    // 데이터를 넘겨받을 리스트 생성
    private List<ArrayList<Data>> items;

    // 받아온 데이터를 클래스안에서 사용할수있게 생성자 추가
    public RetrofitAdapter(List<ArrayList<Data>> items) {
        this.items = items;
    }

    // 만들어둔 아이템XML을 레이아웃 인플레이터 하고 뷰타입의 아이템뷰 변수에 저장후 리턴값으로는 뷰홀더 객체생성후 아이템뷰를 생성자로 전달
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item, parent, false);
        return new ViewHolder(itemView);

    }

    // 뷰홀더가 재생성 될때
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ArrayList<Data> item = items.get(position); // 데이터클래스타입의 ArrayList item을 선언후 받아온 JSon데이터의 포지션값을 가져옴 // items는 0,1,2,3 총 4가지 값을 가지고있음
        Data i = item.get(position); // Data타입의 i선언후 item의 포지션값을 가져옴
        ArrayList<Device> y = i.getDevices(); // Device 타입의 ArrayList y를 선언후 i에 들어있는 Device배열을 가져옴
        // 가져온 데이터들을 뷰에 셋팅
        holder.tv.setText(i.getCodeNm());
        holder.textView.setText(y.get(0).getDeviceDesc());
        holder.textView2.setText(y.get(0).getDeviceName());

        Picasso.get()
                .load(y.get(0).getImageUrl())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return items.size();

    }

    // 뷰홀더클래스 생성후 리사이클러뷰홀더 상속
    // 리사이클러뷰안의 뷰홀더 객체에 보여줄 데이터들
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // 뷰홀더에서 사용할 변수선언
        private TextView textView, textView2, tv;
        private ImageView imageView;


        // 아이템xml정보를 가진 아이템뷰를 받아와 각 뷰를 연결
        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            tv = itemView.findViewById(R.id.tv);
            textView2 = itemView.findViewById(R.id.textView2);
            imageView = itemView.findViewById(R.id.imageView);
        }

    }

}
