package com.example.manhinhchinh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class LichSu extends AppCompatActivity {
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    ListView listView;
    ProxyDBHelper proxyDBHelper;
    TextView textView;
    ArrayList<MonAn> arrayList;
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Lịch sử");
        setContentView(R.layout.activity_lich_su);

        listView = (ListView)findViewById(R.id.lstViewLichSu);
        textView = (TextView) findViewById(R.id.textViewLichSu);

        proxyDBHelper = new ProxyDBHelper(LichSu.this);

        ActionBar actionBar = getSupportActionBar();
        Drawable d = getResources().getDrawable(R.drawable.actionbar_background);
        //ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#0F9D58"));
        actionBar.setBackgroundDrawable(d);
        drawerLayout = findViewById(R.id.DrawerLayout);
        navigationView = findViewById(R.id.nav_view);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.item_TrangChu: {
                    Intent trangChu = new Intent(getApplicationContext(), TrangChu.class);
                    startActivity(trangChu);
                    finish();
                    return true;
                }
                case R.id.item_LichSu: {
                    Toast.makeText(LichSu.this, "Bạn đang ở Yêu thích", Toast.LENGTH_LONG).show();
                    return true;
                }
                case R.id.item_YeuThich: {
                    Intent yeuThich = new Intent(getApplicationContext(), YeuThich.class);
                    startActivity(yeuThich);
                    finish();
                    return true;
                }
            }
            return true;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        arrayList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);

        arrayAdapter.clear();
        arrayList.addAll(DanhSachLichSu.getInstance().getArray());
        arrayAdapter.notifyDataSetChanged();

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            textView.setText((String)listView.getItemAtPosition(position).toString().trim());
            final String tenMon = textView.getText().toString();
            String maMon = proxyDBHelper.getMaMonByTenMon(tenMon);
            Intent intent = new Intent(getApplicationContext(), ChiTietMonAn.class);
            intent.putExtra("maMon", maMon);
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        else if (item.getItemId() == R.id.search) {
            Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

}