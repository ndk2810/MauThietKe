package com.example.manhinhchinh;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class TrangChu extends AppCompatActivity {

    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    ArrayList<DanhMuc> danhMucArrayList;
    ProxyDBHelper proxyDBHelper;
    GridView gridViewDanhMuc;
    TextView textView, textViewMACN;
    ImageView imgViewMACN;
    LinearLayout linearLayoutMACN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.TrangChu);
        setContentView(R.layout.activity_trangchu);
        textView = findViewById(R.id.textViewPHD);
        textViewMACN = findViewById(R.id.textViewMACN);
        linearLayoutMACN = findViewById(R.id.linearLayoutMACN);

        proxyDBHelper = new ProxyDBHelper(TrangChu.this);
        proxyDBHelper.createTable();

        int soLuongMonAn = proxyDBHelper.CountMonAn();
        int rand = getRandomNumber(1, soLuongMonAn);

        imgViewMACN = (ImageView)findViewById(R.id.imageViewMonCuaNgay);

        ArrayList<MonAn> arrayMonAnCuaNgay = new ArrayList<>();
        arrayMonAnCuaNgay.addAll(proxyDBHelper.getMonAnByMaMon(rand));

        MonAn monAnCuaNgay = arrayMonAnCuaNgay.get(0);
        textViewMACN.setText(monAnCuaNgay.getTenMon());
        ChiTietMonAn.setImageViewWithByteArray(imgViewMACN, monAnCuaNgay.getHinhAnh());

        gridViewDanhMuc = (GridView)findViewById(R.id.gvDanhMuc);

        danhMucArrayList = new ArrayList<>();
        danhMucArrayList.addAll(proxyDBHelper.getListDanhMuc());
        GridAdapter gridAdapter = new GridAdapter(this, R.layout.custom_gridview, danhMucArrayList);
        gridAdapter.notifyDataSetChanged();

        gridViewDanhMuc.setAdapter(gridAdapter);

        gridViewDanhMuc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textView = (TextView)view.findViewById(R.id.txtTenDanhMuc);
                final String tenDanhMuc = textView.getText().toString();
                Intent intent = new Intent(getApplicationContext(), DanhMucChiTiet.class);
                intent.putExtra("tenDanhMuc", tenDanhMuc);
                startActivity(intent);
            }
        });

        linearLayoutMACN.setClickable(true);
        linearLayoutMACN.setOnClickListener(v -> {
            Intent intent = new Intent(TrangChu.this, ChiTietMonAn.class);
            intent.putExtra("maMon", String.valueOf(rand));
            startActivity(intent);
        });

        ActionBar actionBar = getSupportActionBar();
        Drawable d = getResources().getDrawable(R.drawable.actionbar_background);

        actionBar.setBackgroundDrawable(d);

        drawerLayout = findViewById(R.id.DrawerLayout);
        navigationView = findViewById(R.id.nav_view);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_TrangChu: {
                        Toast.makeText(TrangChu.this, "Bạn đang ở Trang Chủ", Toast.LENGTH_LONG).show();
                        return true;
                    }
                    case R.id.item_LichSu: {
                        Intent lichSu = new Intent(getApplicationContext(), LichSu.class);
                        startActivity(lichSu);
                        finish();
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
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}