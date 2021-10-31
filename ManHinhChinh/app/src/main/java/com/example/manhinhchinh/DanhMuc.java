package com.example.manhinhchinh;

public class DanhMuc {
    private String tenDanhMuc;
    private byte[] hinhAnhDM;

    public DanhMuc(String tenDanhMuc, byte[] hinhAnhDM) {
        this.tenDanhMuc = tenDanhMuc;
        this.hinhAnhDM = hinhAnhDM;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }

    public byte[] getHinhAnhDM() {
        return hinhAnhDM;
    }

    public void setHinhAnhDM(byte[] hinhAnhDM) {
        this.hinhAnhDM = hinhAnhDM;
    }
}
