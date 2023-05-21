package nhuonghvph29406.fpoly.duanmaunhuonghvph29406.DB_Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Model.loaiSach;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.SQL_Dao.DB_Helper;


public class loaiSachDao {
   private SQLiteDatabase db;

    public loaiSachDao(Context context) {
        DB_Helper dbHelper = new DB_Helper(context);
        db = dbHelper.getWritableDatabase();
    }
    @SuppressLint("Range")
    public List<loaiSach> get(String sql, String...selectArgs){
        List<loaiSach> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectArgs);
        while (cursor.moveToNext()){
            loaiSach loaiSach = new loaiSach();
            loaiSach.setMaLoai(cursor.getString(cursor.getColumnIndex("maloai")));
            loaiSach.setTenLoai(cursor.getString(cursor.getColumnIndex("tenloai")));
            list.add(loaiSach);
        }
        return list;
    }
    public List<loaiSach> getAll(){
        String sql = "SELECT * FROM  loaisach";
        return get(sql);
    }
    public loaiSach getByID(String maloai){
        String sql = "SELECT * FROM loaisach WHERE masach = ?";
        List<loaiSach> list = get(sql,maloai);
        return list.get(0);
    }
    public Long insert(loaiSach loaiSach){
        ContentValues values = new ContentValues();
        values.put("maloai", loaiSach.getMaLoai());
        values.put("tenloai",loaiSach.getTenLoai());
        return db.insert("loaisach",null,values);
    }
    public int update(loaiSach loaiSach){
        ContentValues values = new ContentValues();
        values.put("maloai", loaiSach.getMaLoai());
        values.put("tenloai",loaiSach.getTenLoai());
        return db.update("loaisach",values,"maloai = ?",new String[]{loaiSach.getMaLoai()});
    }
    public int delete(String loaiSach){
       return db.delete("loaisach","maloai = ?",new String[]{loaiSach});

    }
}
