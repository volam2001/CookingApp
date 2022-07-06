package com.example.cookingapp.Adapter;
import com.example.cookingapp.MainActivity;
import com.example.cookingapp.Model.ChiTiet;
import com.example.cookingapp.Model.MonAn;
import com.example.cookingapp.R;
import com.example.cookingapp.ui.chitietmonan.ChiTietActivity;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

public class MonAnAdapter extends RecyclerView.Adapter<MonAnAdapter.MonAnViewHolder>{
    private List<MonAn> listMonan;
    private Context context;

    public MonAnAdapter(List<MonAn> listMonan, Context context) {
        this.listMonan = listMonan;
        this.context = context;
    }

    @NonNull
    @Override
    public MonAnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dongmonan, parent, false);
        return new MonAnViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonAnViewHolder holder, int position) {
        final MonAn monAn = listMonan.get(position);
        Log.d(listMonan.get(position).getChitietcacbuoc().get(0).getChitiet(), "onBindViewHolder: ");
        if (monAn == null)
        {
            return;
        }
        Picasso.get().load(listMonan.get(position).getHinhanh()).resize(400,400).into(holder.hinhanh);
        holder.txttenmonan.setText(monAn.getTenmonan());
        holder.txttendaubep.setText(monAn.getTendaubep());
        holder.txtnguyenlieu.setText(monAn.getNguyenlieu());
        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(monAn.getTenmonan(), "onClick: ");
                Log.d(monAn.getChitietcacbuoc().get(0).getChitiet(), "onClick: ");
                goDetail(monAn.getChitietcacbuoc());
            }
        });
        //parameter là function
    }
    private void goDetail(List <ChiTiet> chitietmonan)
    {
        Intent intent = new Intent(context, ChiTietActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Bundle bundle = new Bundle();
        bundle.putSerializable("chiTietList", (Serializable) chitietmonan);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
    @Override
    public int getItemCount() {
        if (listMonan != null)
                return listMonan.size();
        return 0;
    }

    class MonAnViewHolder extends RecyclerView.ViewHolder{
        private TextView txttenmonan, txttendaubep, txtnguyenlieu;
        private ImageView hinhanh;
        private LinearLayout layoutItem;


        public MonAnViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutItem = itemView.findViewById(R.id.item_layout);
            txttenmonan = itemView.findViewById(R.id.txttenmonan);
            txttendaubep = itemView.findViewById(R.id.txttendaubep);
            txtnguyenlieu = itemView.findViewById(R.id.txtnguyenlieu);
            hinhanh = itemView.findViewById(R.id.hinhanh);

        }
    }

}
