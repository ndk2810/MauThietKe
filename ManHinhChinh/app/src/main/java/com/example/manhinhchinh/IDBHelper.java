package com.example.manhinhchinh;

import java.util.ArrayList;

public interface IDBHelper {
    void createTable();
    int CountMonAn();
    ArrayList<MonAn> searchMonAnByTenMon(String TenMon);
    ArrayList<MonAn> searchMonAnYeuThich(String TenMon);
    ArrayList<MonAn> getMonAnByMaMon(int maMon);
    String getMaMonByTenMon(String tenMon);
    ArrayList<MonAn> getListMonAnByDanhMuc(String danhMuc);
    ArrayList<MonAn> getListMonAnByYeuThich();
    ArrayList<DanhMuc> getListDanhMuc();
    boolean updateYeuThich(MonAn monAn);
}
