package com.example.manhinhchinh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DanhMucChiTiet extends AppCompatActivity {
    ListView listView;
    ArrayList<MonAn> arrayList;
    ProxyDBHelper proxyDBHelper;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_muc_chi_tiet);
        listView = findViewById(R.id.lstViewLichSu);
        textView = findViewById(R.id.textView5);

        proxyDBHelper = new ProxyDBHelper(DanhMucChiTiet.this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent thisIntent = getIntent();
        String tenDanhMuc = thisIntent.getStringExtra("tenDanhMuc");
        setTitle(tenDanhMuc);

        arrayList = new ArrayList<>();

        ListAdapter listAdapter = new ListAdapter(DanhMucChiTiet.this, R.layout.custom_listview, arrayList);

        arrayList.addAll(proxyDBHelper.getListMonAnByDanhMuc(tenDanhMuc));
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            textView = view.findViewById(R.id.textViewTenMon);
            final String tenMon = textView.getText().toString();
            String maMon = proxyDBHelper.getMaMonByTenMon(tenMon);
            Intent intent = new Intent(getApplicationContext(), ChiTietMonAn.class);
            intent.putExtra("maMon", maMon);
            startActivity(intent);
        });
    }
}