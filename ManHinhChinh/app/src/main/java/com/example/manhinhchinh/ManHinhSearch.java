package com.example.manhinhchinh;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ManHinhSearch extends AppCompatActivity {
    ListView listView;
    ArrayList<MonAn> arrayList;
    ArrayAdapter arrayAdapter;
    ProxyDBHelper proxyDBHelper;
    TextView textView;
    EditText txtTimKiem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_search);

        listView = findViewById(R.id.listViewSearch);
        textView = findViewById(R.id.textViewSearch);
        txtTimKiem = findViewById(R.id.editTextTimKiemS);

        proxyDBHelper = new ProxyDBHelper(getApplicationContext());
        proxyDBHelper.createTable();

        arrayList = new ArrayList<>();

        arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);
        handleIntent(getIntent());
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

            txtTimKiem.setText(query);

            arrayAdapter.clear();
            arrayList.addAll(proxyDBHelper.searchMonAnByTenMon(query));
            arrayAdapter.notifyDataSetChanged();
            listView.setAdapter(arrayAdapter);

            txtTimKiem.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String thongTinTK = txtTimKiem.getText().toString();
                    arrayAdapter.clear();
                    arrayList.addAll(proxyDBHelper.searchMonAnByTenMon(thongTinTK));
                    arrayAdapter.notifyDataSetChanged();
                }
                @Override
                public void afterTextChanged(Editable s) {
                }
            });

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    textView.setText((String)listView.getItemAtPosition(position).toString().trim());
                    final String tenMon = textView.getText().toString();
                    String maMon = proxyDBHelper.getMaMonByTenMon(tenMon);
                    Intent intent = new Intent(getApplicationContext(), ChiTietMonAn.class);
                    intent.putExtra("maMon", maMon);
                    startActivity(intent);
                }
            });
        }
    }
}