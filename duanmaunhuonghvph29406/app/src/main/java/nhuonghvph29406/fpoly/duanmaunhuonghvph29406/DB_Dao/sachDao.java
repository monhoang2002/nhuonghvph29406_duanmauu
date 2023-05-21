package nhuonghvph29406.fpoly.duanmaunhuonghvph29406.DB_Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Model.sach;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.SQL_Dao.DB_Helper;

public class sachDao {
    private SQLiteDatabase db;

    public sachDao(Context context) {
        DB_Helper dbHelper = new DB_Helper(context);
        db = dbHelper.getWritableDatabase();
    }
    //"(masach INTEGER PRIMARY KEY AUTOINCREMENT," +
    //            "tensach TEXT,giathue FLOAT," +
    //
    //
    //    "maloai INTEGER REFERENCES loaisach(maloai))";
    @SuppressLint("Range")
    public List<sach> get(String sql, String...agrs){
        ArrayList<sach> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,agrs);
        while (cursor.moveToNext()){
            sach sach = new sach();
            sach.setMaSach(cursor.getString(cursor.getColumnIndex("masach")));
            sach.setTenSach(cursor.getString(cursor.getColumnIndex("tensach")));
            sach.setGiaThue(cursor.getFloat(cursor.getColumnIndex("giathue")));
            sach.setMaLoai(cursor.getString(cursor.getColumnIndex("maloai")));
            list.add(sach);
        }
        return list;
    }
    public List<sach> getAll(){
        String sql = "SELECT * FROM sach";
        return get(sql);
    }
    public sach getById(String masach){
        String sql = "SELECT * FROM sach WHERE masach = ?";
        List<sach> list = get(sql,masach);
        return list.get(0);
    }
    public Long inserSach(sach sach){
        ContentValues values = new ContentValues();
        values.put("masach", sach.getMaSach());
        values.put("tensach",sach.getTenSach());
        values.put("giathue",sach.getGiaThue());
        values.put("maloai",sach.getMaLoai());
        return db.insert("sach",null,values);
    }
    public int updateSach(sach sach){
        ContentValues values = new ContentValues();
        values.put("masach", sach.getMaSach());
        values.put("tensach",sach.getTenSach());
        values.put("giathue",sach.getGiaThue());
        values.put("maloai",sach.getMaLoai());
       return db.update("sach",values,"masach = ?",new String[]{String.valueOf(sach.getMaSach())});
    }
    public int deleteSach(String sach){
       return db.delete("sach","masach = ?",new String[]{sach});
    }
}
