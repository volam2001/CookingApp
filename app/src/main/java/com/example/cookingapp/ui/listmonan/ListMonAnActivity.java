package com.example.cookingapp.ui.listmonan;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cookingapp.Adapter.MonAnAdapter;
import com.example.cookingapp.R;

public class ListMonAnActivity extends AppCompatActivity {
    private MonAnAdapter monAnAdapterl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danhsachmonan);
    }
}
