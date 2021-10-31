package com.example.manhinhchinh;

import java.util.ArrayList;

//Nguyên lý Singleton :v

public class DanhSachLichSu {
    private static DanhSachLichSu mInstance;
    private ArrayList<MonAn> lichSuXem = null;

    public static DanhSachLichSu getInstance() {
        if(mInstance == null)
            mInstance = new DanhSachLichSu();

        return mInstance;
    }

    private DanhSachLichSu() {
        lichSuXem = new ArrayList<>();
    }
    // retrieve array from anywhere
    public ArrayList<MonAn> getArray() {
        return this.lichSuXem;
    }
    //Add element to array
    public void addToArray(MonAn monAn) {
        for (int i = 0; i < lichSuXem.size(); i++)
        {
            if (lichSuXem.get(i).getTenMon().equals(monAn.getTenMon()))
                lichSuXem.remove(i);
        }
        lichSuXem.add(0, monAn);
    }
}
