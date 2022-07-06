package com.example.cookingapp.ui.listmonan;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookingapp.Adapter.ListDanhSachAdapter;
import com.example.cookingapp.Adapter.MonAnAdapter;
import com.example.cookingapp.Model.ListMonAn;
import com.example.cookingapp.R;

import java.util.ArrayList;
import java.util.List;

public class ListDSMNActivity extends AppCompatActivity {
    private MonAnAdapter monAnAdapter;
    private List<ListMonAn> listMonAnList;
    private RecyclerView rcvListDSMN;
    private ListDanhSachAdapter listDanhSachAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danhsachlistmonan);
        rcvListDSMN = findViewById(R.id.rcvdanhsachmonan);
        listMonAnList = new ArrayList<>();
        listDanhSachAdapter = new ListDanhSachAdapter(listMonAnList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvListDSMN.setLayoutManager(linearLayoutManager);
        rcvListDSMN.setAdapter(listDanhSachAdapter);

    }
}
