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

public class ListAdapter extends BaseAdapter {

    Context context;
    private int idLayout;
    private ArrayList<MonAn> monAn;

    public ListAdapter(Context context, int idLayout, ArrayList<MonAn> monAn) {
        this.context = context;
        this.idLayout = idLayout;
        this.monAn = monAn;
    }

    @Override
    public int getCount() {
        return monAn.size();
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

        TextView txtTenMon = (TextView)convertView.findViewById(R.id.textViewTenMon);
        ImageView imageViewMon = (ImageView)convertView.findViewById(R.id.imageViewMonAnDM);
        TextView txtNguyenLieu = (TextView)convertView.findViewById(R.id.txtViewNguyenLieuLV);
        final MonAn monAn = this.monAn.get(position);
        if (!this.monAn.isEmpty())
        {
            txtTenMon.setText(monAn.getTenMon());
            txtNguyenLieu.setText(monAn.getCachNau());
            setImageViewWithByteArray(imageViewMon, monAn.getHinhAnh());
        }
        return convertView;
    }
    public static void setImageViewWithByteArray(ImageView view, byte[] data) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        view.setImageBitmap(bitmap);
    }
}

