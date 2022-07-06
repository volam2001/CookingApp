package com.example.cookingapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookingapp.Model.ListMonAn;
import com.example.cookingapp.R;

import java.util.List;

public class ListDanhSachAdapter extends RecyclerView.Adapter <ListDanhSachAdapter.ListDanhSachViewHolder>{
    private List<ListMonAn> listMonAnList;
    private ListMonAn listMonAn;

    public ListDanhSachAdapter(List<ListMonAn> listMonAnList) {
        this.listMonAn = listMonAn;
    }

    @NonNull
    @Override
    public ListDanhSachViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dongdanhsach, parent, false);
        return new ListDanhSachViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListDanhSachViewHolder holder, int position) {
        ListMonAn listMonAn = listMonAnList.get(position);
        if(listMonAn ==null)
            return;
        holder.tendanhsach.setText(listMonAn.getTenplaylist());
    }

    @Override
    public int getItemCount() {
        if (listMonAnList!=null)
            return listMonAnList.size();
        return 0;
    }


    public class ListDanhSachViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView tendanhsach;
        public ListDanhSachViewHolder(@NonNull View itemView) {

            super(itemView);
            tendanhsach = itemView.findViewById(R.id.txttendanhsach);
            imageView = itemView.findViewById(R.id.hinhanh);
        }
    }
}

