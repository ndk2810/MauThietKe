package com.example.manhinhchinh;

public class MonAn {
    private int maMon;
    private String tenMon, nguyenLieu, cachNau, danhMuc;
    private byte[] hinhAnh;
    //private Integer yeuThich;
    YeuThichStrategy yeuThichStrategy;

    public MonAn(int maMon, String tenMon, String nguyenLieu, String cachNau, String danhMuc, byte[] hinhAnh, Integer yeuThich) {
        this.maMon = maMon;
        this.tenMon = tenMon;
        this.nguyenLieu = nguyenLieu;
        this.cachNau = cachNau;
        this.danhMuc = danhMuc;
        this.hinhAnh = hinhAnh;
        //this.yeuThich = yeuThich;

        if (yeuThich == 1)
            yeuThichStrategy = new LikedStrategy();
        else
            yeuThichStrategy = new UnlikedStrategy();
    }
    public MonAn(String tenMon) {
        this.tenMon = tenMon;
    }
    public MonAn(String tenMon, String nguyenLieu, String cachNau, byte[] hinhAnh) {
        this.tenMon = tenMon;
        this.nguyenLieu = nguyenLieu;
        this.cachNau = cachNau;
        this.hinhAnh = hinhAnh;
    }

    //Getter và setter
    public String getTenMon() {
        return tenMon;
    }
    public String getNguyenLieu() {
        return nguyenLieu;
    }
    public String getCachNau() {
        return cachNau;
    }
    public String getDanhMuc() {
        return danhMuc;
    }
    public byte[] getHinhAnh() {
        return hinhAnh;
    }
    public Integer getYeuThich() {
        if (yeuThichStrategy.currentState() == "Liked")
            return 1;
        return 0;
    }
    //public void setYeuThich(Integer yeuThich) {
    //    this.yeuThich = yeuThich;
    //}
    public int getMaMon() {
        return maMon;
    }

    @Override
    public String toString() {
        return tenMon;
    }
}
