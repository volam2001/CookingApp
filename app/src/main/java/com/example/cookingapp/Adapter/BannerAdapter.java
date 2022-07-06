package com.example.cookingapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookingapp.R;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.BannerViewHolder> {
    @NonNull
    @Override
    public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dongchitiet, parent, false);
        return new BannerAdapter.BannerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BannerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class BannerViewHolder extends RecyclerView.ViewHolder {
        private ImageView hinhanh;
        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            hinhanh = itemView.findViewById(R.id.hinhanh);

        }
    }
}
