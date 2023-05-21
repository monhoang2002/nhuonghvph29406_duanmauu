package nhuonghvph29406.fpoly.duanmaunhuonghvph29406.DB_Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Model.thanhVien;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.SQL_Dao.DB_Helper;

public class thanhVienDao {
    private SQLiteDatabase db;

    public thanhVienDao(Context context) {
        DB_Helper dbHelper = new DB_Helper(context);
        db = dbHelper.getWritableDatabase();
    }
    // "matv INTEGER PRIMARY KEY AUTOINCREMENT," +
    //            "namsinh TEXT, " +
    //            "hoten TEXT)";
    @SuppressLint("Range")
    public List<thanhVien> get(String sql, String...agrs){
        ArrayList<thanhVien> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,agrs);
        while (cursor.moveToNext()){
            thanhVien thanhVien = new thanhVien();
            thanhVien.setMaTv(cursor.getString(cursor.getColumnIndex("matv")));
            thanhVien.setNamSinh(cursor.getString(cursor.getColumnIndex("namsinh")));
            thanhVien.setHoTen(cursor.getString(cursor.getColumnIndex("hoten")));
            list.add(thanhVien);
        }
     return list;
    }
    public List<thanhVien> getAll(){
        String sql = "SELECT * FROM thanhvien";
        return get(sql);
    }
    public thanhVien getById(String maTv){
        String sql = "SELECT * FROM thanhvien WHERE matv = ?";
        List<thanhVien> list = get(sql,maTv);
        return list.get(0);
    }
    public Long inserThanhVien(thanhVien thanhVien){
        ContentValues values = new ContentValues();
        values.put("matv", thanhVien.getMaTv());
        values.put("namsinh",thanhVien.getNamSinh());
        values.put("hoten",thanhVien.getHoTen());
        return db.insert("thanhvien",null,values);
    }
    public int updateThanhVien(thanhVien thanhVien){
        ContentValues values = new ContentValues();
        values.put("matv", thanhVien.getMaTv());
        values.put("namsinh",thanhVien.getNamSinh());
        values.put("hoten",thanhVien.getHoTen());
        return db.update("thanhvien",values,"matv = ?", new String[]{String.valueOf(thanhVien.getMaTv())});
    }
    public int deleteThanhVien(String thanhVien){
        return db.delete("thanhvien","matv = ?",new String[]{thanhVien});
    }
}
