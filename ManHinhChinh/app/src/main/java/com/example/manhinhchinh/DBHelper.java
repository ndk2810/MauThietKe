package com.example.manhinhchinh;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DBHelper implements IDBHelper {
    Context context;
    String DB_Name = "MonAnDB.db";

    public DBHelper(Context context)
    {
        this.context = context;
        copyDatabase();
    }

    private void copyDatabase() {
        File dbFile = context.getDatabasePath(DB_Name);
        if (!dbFile.exists()) {
            try {
                InputStream is = context.getAssets().open(DB_Name);
                OutputStream os = new FileOutputStream(dbFile);
                byte[] buffer = new byte[1024];
                while (is.read(buffer) > 0) {
                    os.write(buffer);
                }
                os.flush();
                os.close();
                is.close();
            } catch (IOException e) {
                throw new RuntimeException("Error creating source database", e);
            }
        }
    }
    public SQLiteDatabase openDB(){
        return context.openOrCreateDatabase(DB_Name, Context.MODE_PRIVATE, null);
    }
    public void createTable(){
        SQLiteDatabase db = openDB();

        String sqlTaoTableDanhMuc = "create table if not exists DanhMuc(" +
                "danhMuc TEXT NOT NULL PRIMARY KEY, " +
                "hinhAnhDM BLOB)";

        String sqlTaoTableMonAn = "create table if not exists MonAn(" +
                "maMon integer NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "tenMon TEXT NOT NULL," +
                "nguyenLieu TEXT," +
                "cachNau TEXT," +
                "danhMuc TEXT," +
                "hinhAnh TEXT," +
                "yeuThich INTEGER NOT NULL CHECK(yeuThich IN (0,1))," +
                "FOREIGN KEY('danhMuc')REFERENCES DANHMUC('danhMuc'))";

        db.execSQL(sqlTaoTableDanhMuc);
        db.execSQL(sqlTaoTableMonAn);

        closeDB(db);
    }

    public void closeDB(SQLiteDatabase db){
        db.close();
    }

    public int CountMonAn(){
        SQLiteDatabase db = openDB();
        Cursor cursor = db.query("MonAn",
                new String[] {"maMon"},
                null,
                null,
                null,
                null,
                null);
        int soLuong = cursor.getCount();
        closeDB(db);
        return soLuong;
    }
    //Dùng khi search
    public ArrayList<MonAn> searchMonAnByTenMon(String TenMon){
        ArrayList<MonAn> arrayList = new ArrayList<>();
        //1
        SQLiteDatabase db = openDB();
        //2
        Cursor cursor = db.query("MonAn",
                new String[] { "tenMon"},
                "tenMon LIKE '%" + TenMon + "%'",
                null,
                null,
                null,
                null);
        //3
        while (cursor.moveToNext()){
            String tenMon =  cursor.getString(0);
            //4
            arrayList.add(new MonAn(tenMon));
        }
        //5
        closeDB(db);
        return arrayList;
    }
    public ArrayList<MonAn> searchMonAnYeuThich(String TenMon){
        ArrayList<MonAn> arrayList = new ArrayList<>();
        //1
        SQLiteDatabase db = openDB();
        //2
        Cursor cursor = db.query("MonAn",
                new String[] { "tenMon"},
                "tenMon LIKE '%" + TenMon + "%' AND yeuThich = 1",
                null,
                null,
                null,
                null);
        //3
        while (cursor.moveToNext()){
            String tenMon =  cursor.getString(0);
            //4
            arrayList.add(new MonAn(tenMon));
        }
        //5
        closeDB(db);
        return arrayList;
    }
    public ArrayList<MonAn> getMonAnByMaMon(int maMon){
        ArrayList<MonAn> arrayList = new ArrayList<>();
        //1
        SQLiteDatabase db = openDB();
        //2
        Cursor cursor = db.query("MonAn",
                null,
                "maMon = " + maMon,
                null,
                null,
                null,
                null);
        //3
        while (cursor.moveToNext()){
            int MaMon =  cursor.getInt(0);
            String tenMon =  cursor.getString(1);
            String nguyenLieu = cursor.getString(2);
            String cachNau =  cursor.getString(3);
            String danhMuc = cursor.getString(4);
            byte[] hinhAnh = cursor.getBlob(5);
            int yeuThich = cursor.getInt(6);
            //4
            arrayList.add(new MonAn(MaMon, tenMon, nguyenLieu, cachNau, danhMuc, hinhAnh, yeuThich));
        }
        //5
        closeDB(db);
        return arrayList;
    }
    public String getMaMonByTenMon(String tenMon){
        String mm = "";
        //1
        SQLiteDatabase db = openDB();
        //2
        Cursor cursor = db.query("MonAn",
                new String[] {"maMon"},
                "tenMon = '" + tenMon + "'",
                null,
                null,
                null,
                null);
        //3
        while (cursor.moveToNext()){
            int maMon = cursor.getInt(0);
            mm = String.valueOf(maMon);
        }
        //5
        closeDB(db);
        return mm;
    }
    public ArrayList<MonAn> getListMonAnByDanhMuc(String danhMuc){
        ArrayList<MonAn> arrayList = new ArrayList<>();
        //1
        SQLiteDatabase db = openDB();
        //2
        Cursor cursor = db.query("MonAn",
                new String[] { "tenMon", "nguyenLieu","cachNau","hinhAnh"},
                "danhMuc = '" + danhMuc + "'",
                null,
                null,
                null,
                null);
        //3
        while (cursor.moveToNext()){
            String tenMon =  cursor.getString(0);
            String nguyenLieu = cursor.getString(1);
            String cachNau =  cursor.getString(2);
            byte[] hinhAnh = cursor.getBlob(3);
            //4
            arrayList.add(new MonAn(tenMon, nguyenLieu, cachNau, hinhAnh));
        }
        //5
        closeDB(db);
        return arrayList;
    }
    public ArrayList<MonAn> getListMonAnByYeuThich(){
        ArrayList<MonAn> arrayList = new ArrayList<>();
        //1
        SQLiteDatabase db = openDB();
        //2
        Cursor cursor = db.query("MonAn",
                new String[] { "tenMon", "nguyenLieu","cachNau","hinhAnh"},
                "yeuThich = 1",
                null,
                null,
                null,
                null);
        //3
        while (cursor.moveToNext()){
            String tenMon =  cursor.getString(0);
            String nguyenLieu = cursor.getString(1);
            String cachNau =  cursor.getString(2);
            byte[] hinhAnh = cursor.getBlob(3);
            //4
            arrayList.add(new MonAn(tenMon, nguyenLieu, cachNau, hinhAnh));
        }
        //5
        closeDB(db);
        return arrayList;
    }
    public ArrayList<DanhMuc> getListDanhMuc(){
        ArrayList<DanhMuc> arrayList = new ArrayList<>();
        //1
        SQLiteDatabase db = openDB();
        //2
        Cursor cursor = db.query("DanhMuc",
                null,
                null,
                null,
                null,
                null,
                null);
        //3
        while (cursor.moveToNext()){
            String tenDanhMuc =  cursor.getString(0);
            byte[] hinhAnhDM = cursor.getBlob(1);
            //4
            arrayList.add(new DanhMuc(tenDanhMuc, hinhAnhDM));
        }
        //5
        closeDB(db);
        return arrayList;
    }
    public boolean updateYeuThich(MonAn monAn){
        boolean flag = false;
        //1
        SQLiteDatabase db = openDB();
        //2
        ContentValues contentValues = new ContentValues();
        contentValues.put("yeuThich", monAn.getYeuThich());
        //3
        flag = db.update("MonAn",
                contentValues,
                "tenMon = '" + monAn.getTenMon() + "'",
                null) > 0;
        //4
        closeDB(db);
        return flag;
    }
}
