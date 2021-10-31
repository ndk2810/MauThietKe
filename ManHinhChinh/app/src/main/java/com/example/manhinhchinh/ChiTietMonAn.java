package com.example.manhinhchinh;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.ArrayList;

public class ChiTietMonAn extends AppCompatActivity {
    TextView txtTenMon, txtNguyenLieu, txtCachNau, txtYeuThich;
    ArrayList<MonAn> arrayList;
    ProxyDBHelper proxyDBHelper;
    ImageView imageViewHinhAnh;
    LikeButton yeuThichBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_mon_an);

        txtTenMon = findViewById(R.id.txtTenMon);
        txtNguyenLieu = findViewById(R.id.txtNguyenLieu);
        txtCachNau = findViewById(R.id.txtCachNau);
        txtYeuThich = (TextView)findViewById(R.id.txtYeuThich);
        imageViewHinhAnh = findViewById(R.id.imageViewHinhAnh);
        yeuThichBtn = (LikeButton)findViewById(R.id.yeuThichButton);

        proxyDBHelper = new ProxyDBHelper(ChiTietMonAn.this);
        proxyDBHelper.createTable();

        arrayList = new ArrayList<>();

        Intent thisIntent = getIntent();
        String mm = thisIntent.getStringExtra("maMon");
        arrayList.addAll(proxyDBHelper.getMonAnByMaMon(Integer.parseInt(mm)));

        MonAn monAn = arrayList.get(0);
        txtCachNau.setText(monAn.getCachNau());
        txtTenMon.setText(monAn.getTenMon());
        txtNguyenLieu.setText(monAn.getNguyenLieu());
        setImageViewWithByteArray(imageViewHinhAnh, monAn.getHinhAnh());
        checkYeuThich(monAn);
        DanhSachLichSu.getInstance().addToArray(monAn);

        yeuThichBtn.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                //monAn.setYeuThich(1);
                monAn.yeuThichStrategy = new LikedStrategy();
                UpdateDB();
            }
            @Override
            public void unLiked(LikeButton likeButton) {
                //monAn.setYeuThich(0);
                txtYeuThich.setText("Yêu thích");
                monAn.yeuThichStrategy = new UnlikedStrategy();
                UpdateDB();
            }
            private void UpdateDB()
            {
                proxyDBHelper.updateYeuThich(monAn);
                checkYeuThich(monAn);
            }
        });
    }
    public static void setImageViewWithByteArray(ImageView view, byte[] data) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        view.setImageBitmap(bitmap);
    }
    private void checkYeuThich(MonAn monAn)
    {
        if (monAn.getYeuThich() == 0)
            return;
        yeuThichBtn.setLiked(true);
        txtYeuThich.setText("Đã yêu thích");
    }
}