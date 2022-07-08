package com.example.cookingapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.cookingapp.Adapter.MonAnAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.cookingapp.Model.ChiTiet;
import com.example.cookingapp.Model.MonAn;
import com.example.cookingapp.R;
import com.example.cookingapp.ui.chitietmonan.ChiTietActivity;
import com.example.cookingapp.ui.danau.DaNauActivity;
import com.example.cookingapp.ui.listmonan.ListDSMNActivity;
import com.example.cookingapp.ui.themmonan.ThemMonActivity;
import com.example.cookingapp.ui.timkiem.TimKiemActivity;
import com.example.cookingapp.ui.yeuthich.YeuThichActivity;
import com.example.cookingapp.ui.yeuthich.YeuThichActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity  extends AppCompatActivity {
    private RecyclerView rcvDSMA, rcvDSMADN;
    private MonAnAdapter monanAdapter, monandexuatAdapter;
    private List<MonAn> listMonAn, listMonAnDaNau, listMonAnDeXuat;
    private FloatingActionButton floatingActionButton;
    private SearchView searchView;
    private NavigationView navigationView;
    private List<String> monAnChuaNau;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityhome);
        navigationView = findViewById(R.id.navigationview);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_home)
                {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(intent);
                }
                else if (id ==R.id.navdanau)
                {
                    Intent intent = new Intent(getApplicationContext(), DaNauActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(intent);
                }
                else if (id ==R.id.navyeuthich)
                {
                    Intent intent = new Intent(getApplicationContext(), YeuThichActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(intent);
                }
                return true;
            }
        });
        searchView = findViewById(R.id.editTimkiem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Intent intent = new Intent(getApplicationContext(), TimKiemActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                TimKiemActivity timKiemActivity = new TimKiemActivity();
                Bundle bundle = new Bundle();
                bundle.putString("query", s);
                intent.putExtras(bundle);
                getApplicationContext().startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.d("TAG", s);
                return false;
            }
        });

        floatingActionButton = findViewById(R.id.btnthemmonan);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ThemMonActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }
        });
        loadMonAn();
        loadMonAnDeXuat();
    }

    @Override
    public void onBackPressed() {

    }

    public void loadMonAn()
    {
        rcvDSMA = findViewById(R.id.recycleDSMN);
        listMonAn = new ArrayList<>();
        monanAdapter = new MonAnAdapter(listMonAn, getApplicationContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        rcvDSMA.setLayoutManager(linearLayoutManager);
        rcvDSMA.setAdapter(monanAdapter);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        databaseReference.child("monan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap: snapshot.getChildren())
                {
                    MonAn monAn = (MonAn) snap.getValue(MonAn.class);
                    listMonAn.add(monAn);
                }
                monanAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public List<String> checkMonDaNau()
    {
        final List<String> answer = new ArrayList<>();
        listMonAnDaNau = new ArrayList<>();
        final String[] monan_stringFlag = new String[8];
        monan_stringFlag[0] = "vịt";
        monan_stringFlag[1] = "canh";
        monan_stringFlag[2] = "rau";
        monan_stringFlag[3] = "cá";
        monan_stringFlag[4] = "kho";
        monan_stringFlag[5] = "thịt";
        monan_stringFlag[6] = "gà";
        monan_stringFlag[7] = "bò";

        final boolean[] monan_booleanFlag = new boolean[8];
        monan_booleanFlag[0] = true;
        monan_booleanFlag[1] = true;
        monan_booleanFlag[2] = true;
        monan_booleanFlag[3] = true;
        monan_booleanFlag[4] = true;
        monan_booleanFlag[5] = true;
        monan_booleanFlag[6] = true;
        monan_booleanFlag[7] = true;
        listMonAnDaNau = new ArrayList<>();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        databaseReference.child("danau").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap: snapshot.getChildren())
                {
                    MonAn monAn = (MonAn) snap.getValue( MonAn.class);
                    for(int i = 0; i <monan_booleanFlag.length;i++)
                    {
                        if (monAn.getTenmonan().toLowerCase(Locale.ROOT) == monan_stringFlag[i])
                        {
                            monan_booleanFlag[i] = false;
                            answer.add(monan_stringFlag[i]);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        List<String> ans = new ArrayList<>();
        for (int i=0; i<monan_booleanFlag.length; i++)
        {
            if (monan_booleanFlag[i] == true)
            {
                ans.add(monan_stringFlag[i]);
            }
        }
        return ans;
    }
    public void loadMonAnDeXuat()
    {
        rcvDSMADN = findViewById(R.id.recycleDSMADN);
        listMonAnDeXuat = new ArrayList<>();
        monandexuatAdapter = new MonAnAdapter(listMonAnDeXuat, getApplicationContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        rcvDSMADN.setLayoutManager(linearLayoutManager);
        rcvDSMADN.setAdapter(monandexuatAdapter);
        List<String> monAnChuaNau = new ArrayList<>();
        monAnChuaNau = checkMonDaNau();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        List<String> finalMonAnChuaNau = monAnChuaNau;
        databaseReference.child("monan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap: snapshot.getChildren())
                {
                    MonAn monAn = (MonAn) snap.getValue( MonAn.class);
                    String tenMon = monAn.getTenmonan();
                    if (finalMonAnChuaNau != null) {
                        for (int i = 0; i < finalMonAnChuaNau.size(); i++) {
                            if (monAn.getTenmonan().toLowerCase(Locale.ROOT).contains(finalMonAnChuaNau.get(i).toLowerCase(Locale.ROOT))) {
                                listMonAnDeXuat.add(monAn);
                                break;
                            }
                        }
                    }
                }
                monandexuatAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

    });
    }
    @Override
    protected void onRestart() {
        super.onRestart();
    }
}
