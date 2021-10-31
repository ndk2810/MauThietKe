package com.example.manhinhchinh;

import android.content.Context;

import java.util.ArrayList;

public class ProxyDBHelper implements IDBHelper {
    IDBHelper DBHelper;

    public ProxyDBHelper(Context context) {
        DBHelper = new DBHelper(context);
    }

    @Override
    public void createTable() {
        DBHelper.createTable();
    }

    @Override
    public int CountMonAn() {
        return DBHelper.CountMonAn();
    }

    @Override
    public ArrayList<MonAn> searchMonAnByTenMon(String TenMon) {
        return DBHelper.searchMonAnByTenMon(TenMon);
    }

    @Override
    public ArrayList<MonAn> searchMonAnYeuThich(String TenMon) {
        return DBHelper.searchMonAnYeuThich(TenMon);
    }

    @Override
    public ArrayList<MonAn> getMonAnByMaMon(int maMon) {
        return DBHelper.getMonAnByMaMon(maMon);
    }

    @Override
    public String getMaMonByTenMon(String tenMon) {
        return DBHelper.getMaMonByTenMon(tenMon);
    }

    @Override
    public ArrayList<MonAn> getListMonAnByDanhMuc(String danhMuc) {
        return DBHelper.getListMonAnByDanhMuc(danhMuc);
    }

    @Override
    public ArrayList<MonAn> getListMonAnByYeuThich() {
        return DBHelper.getListMonAnByYeuThich();
    }

    @Override
    public ArrayList<DanhMuc> getListDanhMuc() {
        return DBHelper.getListDanhMuc();
    }

    @Override
    public boolean updateYeuThich(MonAn monAn) {
        return DBHelper.updateYeuThich(monAn);
    }
}
