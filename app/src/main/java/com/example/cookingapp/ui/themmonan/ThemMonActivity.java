package com.example.cookingapp.ui.themmonan;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookingapp.Adapter.ChiTietAdapter;
import com.example.cookingapp.Adapter.MonAnAdapter;
import com.example.cookingapp.MainActivity;
import com.example.cookingapp.Model.ChiTiet;
import com.example.cookingapp.Model.MonAn;
import com.example.cookingapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ThemMonActivity extends AppCompatActivity {
    private Button btnthemmon, btncancel;
    EditText tenmonan, tendaubep,nguyenlieu;
    ImageView hinhanh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dongthemmonan);
        tenmonan = findViewById(R.id.txttenmonan);
        tendaubep = findViewById(R.id.txttendaubep);
        nguyenlieu = findViewById(R.id.txtnguyenlieu);
        btnthemmon = findViewById(R.id.btnthemmonan);
        hinhanh = findViewById(R.id.hinhanh);
        btncancel = findViewById(R.id.btncancle);
        btnthemmon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickbtn();
            }
        });
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }
        });

    }
    public void clickbtn()
    {
//        String tenmonan, String hinhanh, String tendaubep,
//         //String nguyenlieu, List<ChiTiet> chitietcacbuoc
//        List <ChiTiet> chiTietList = null;
//        ChiTiet chitiet1 = new ChiTiet("https://cdn.tgdd.vn/2021/05/CookProduct/47884A90-BACC-4926-B0B9-D0F50640C8AE-SunnyTrinh-Copy(2)-1200x676.jpeg", "Chưa có chi tiết");
//        chiTietList.add(chitiet1);
//        String hinhanh = "https://cdn.tgdd.vn/2021/05/CookProduct/47884A90-BACC-4926-B0B9-D0F50640C8AE-SunnyTrinh-Copy(2)-1200x676.jpeg";
//        MonAn monAn = new MonAn(tenmonan.getText().toString(), hinhanh, tendaubep.getText().toString(), nguyenlieu.getText().toString(), chiTietList);
//        String key = tenmonan.getText().toString();
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference databaseReference = database.getReference().child("monan").child(key);
//        databaseReference.setValue(monAn);
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Bạn đã thêm món thành công");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();

    }
}
