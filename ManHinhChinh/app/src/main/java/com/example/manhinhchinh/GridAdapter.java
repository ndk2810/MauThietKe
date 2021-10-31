package com.example.manhinhchinh;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {

    Context context;
    private int idLayout;
    private ArrayList<DanhMuc> danhMuc;

    public GridAdapter(Context context, int idLayout, ArrayList<DanhMuc> danhMuc) {
        this.context = context;
        this.idLayout = idLayout;
        this.danhMuc = danhMuc;
    }

    @Override
    public int getCount() {
        return danhMuc.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(idLayout, parent, false);
        }
        TextView txtTenDanhMuc = (TextView)convertView.findViewById(R.id.txtTenDanhMuc);
        ImageView imageViewDM = (ImageView)convertView.findViewById(R.id.imageViewDanhMuc);
        final DanhMuc dm = danhMuc.get(position);
        if (!danhMuc.isEmpty())
        {
            txtTenDanhMuc.setText(dm.getTenDanhMuc());
            setImageViewWithByteArray(imageViewDM, dm.getHinhAnhDM());
        }
        return convertView;
    }
    public static void setImageViewWithByteArray(ImageView view, byte[] data) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        view.setImageBitmap(bitmap);
    }
}

