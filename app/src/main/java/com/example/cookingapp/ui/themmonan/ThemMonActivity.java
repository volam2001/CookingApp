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
    EditText txttenmonan, txttendaubep,txtnguyenlieu, txtbuoc1, txtbuoc2, txtbuoc3, txtbuoc4;
    ImageView hinhanh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dongthemmonan);
        txttenmonan = findViewById(R.id.txttenmonan);
        txttendaubep = findViewById(R.id.txttendaubep);
        txtnguyenlieu = findViewById(R.id.txtnguyenlieu);
        btnthemmon = findViewById(R.id.btnthemmonan);
        hinhanh = findViewById(R.id.hinhanh);
        txtbuoc1 = findViewById(R.id.txtchitiet1);
        txtbuoc2 = findViewById(R.id.txtchitiet2);
        txtbuoc3 = findViewById(R.id.txtchitiet3);
        txtbuoc4 = findViewById(R.id.txtchitiet4);
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
        String hinhanh;
        List<ChiTiet> chiTietList = new ArrayList<>();

        String buoc1 = txtbuoc1.getText().toString();
        String buoc2 = txtbuoc2.getText().toString();
        String buoc3 = txtbuoc3.getText().toString();
        String buoc4 = txtbuoc4.getText().toString();
        if (buoc1 != "Bước 1")
        {
            String linkchitiet = "https://cdn.tgdd.vn/2021/05/CookProduct/47884A90-BACC-4926-B0B9-D0F50640C8AE-SunnyTrinh-Copy(2)-1200x676.jpeg";
            ChiTiet chiTiet1 = new ChiTiet(linkchitiet, buoc1);
            chiTietList.add(chiTiet1);
        }
        if (buoc2 != "Bước 2")
        {
            String linkchitiet = "https://cdn.tgdd.vn/2021/05/CookProduct/47884A90-BACC-4926-B0B9-D0F50640C8AE-SunnyTrinh-Copy(2)-1200x676.jpeg";
            ChiTiet chiTiet2 = new ChiTiet(linkchitiet, buoc2);
            chiTietList.add(chiTiet2);
        }
        if (buoc3 != "Bước 3")
        {
            String linkchitiet = "https://cdn.tgdd.vn/2021/05/CookProduct/47884A90-BACC-4926-B0B9-D0F50640C8AE-SunnyTrinh-Copy(2)-1200x676.jpeg";
            ChiTiet chiTiet3 = new ChiTiet(linkchitiet, buoc3);
            chiTietList.add(chiTiet3);
        }
        if (buoc4 != "Bước 4")
        {
            String linkchitiet = "https://cdn.tgdd.vn/2021/05/CookProduct/47884A90-BACC-4926-B0B9-D0F50640C8AE-SunnyTrinh-Copy(2)-1200x676.jpeg";
            ChiTiet chiTiet4 = new ChiTiet(linkchitiet, buoc4);
            chiTietList.add(chiTiet4);
        }

        hinhanh = "https://cdn.tgdd.vn/2021/05/CookProduct/47884A90-BACC-4926-B0B9-D0F50640C8AE-SunnyTrinh-Copy(2)-1200x676.jpeg";
        MonAn monAn = new MonAn(txttenmonan.getText().toString(), hinhanh, txttendaubep.getText().toString(), txtnguyenlieu.getText().toString(), chiTietList);
        String key = txttenmonan.getText().toString();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference().child("monan/"+key);
        databaseReference.setValue(monAn);
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
