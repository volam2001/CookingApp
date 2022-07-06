package com.example.cookingapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookingapp.Model.ChiTiet;
import com.example.cookingapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ChiTietAdapter extends  RecyclerView.Adapter<ChiTietAdapter.ChiTietViewHolder>{
    private List<ChiTiet> chiTietList;

    public ChiTietAdapter(List<ChiTiet> chiTietList) {
        this.chiTietList = chiTietList;
    }

    @NonNull
    @Override
    public ChiTietViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dongchitiet, parent, false);
        return new ChiTietViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChiTietViewHolder holder, int position) {
        ChiTiet chiTiet = chiTietList.get(position);
        if (chiTiet == null)
            return;
        Picasso.get().load(chiTietList.get(position).getLink()).resize(400,400).into(holder.hinhanh);

        holder.tvChitiet.setText(chiTiet.getChitiet());
    }

    @Override
    public int getItemCount() {
        if (chiTietList != null)
            return chiTietList.size();
        return 0;
    }

    class ChiTietViewHolder extends RecyclerView.ViewHolder{
        private TextView tvChitiet;
        private ImageView hinhanh;
        public ChiTietViewHolder(@NonNull View itemView) {
            super(itemView);
            tvChitiet = itemView.findViewById(R.id.tvchitiet);
            hinhanh = itemView.findViewById(R.id.hinhanh);

        }
    }


}
