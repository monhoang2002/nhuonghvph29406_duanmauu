package nhuonghvph29406.fpoly.duanmaunhuonghvph29406.DB_Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Model.phieuMuon;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Model.sapXep;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.SQL_Dao.DB_Helper;

public class phieuMuonDao {
    private SQLiteDatabase db;

    public phieuMuonDao(Context context) {
        DB_Helper dbHelper = new DB_Helper(context);
        db = dbHelper.getWritableDatabase();
    }
    // "(mapm INTEGER PRIMARY KEY AUTOINCREMENT, " +
    //            "matv INTEGER REFERENCES thanhvien(matv)," +
    //            "matt INTEGER REFERENCES thuthu(matt)," +
    //            "masach INTEGER REFERENCES sach(masach)," +
    //            "tienthue FLOAT, ngaytra TEXT)";
    @SuppressLint("Range")
    public List<phieuMuon> get(String sql, String...agrs){
       List<phieuMuon> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,agrs);
        while (cursor.moveToNext()){
            phieuMuon phieuMuon = new phieuMuon();
            phieuMuon.setMaPm(cursor.getString(cursor.getColumnIndex("mapm")));
            phieuMuon.setMaTv(cursor.getString(cursor.getColumnIndex("matv")));
            phieuMuon.setMaTt(cursor.getString(cursor.getColumnIndex("matt")));
            phieuMuon.setMasach(cursor.getString(cursor.getColumnIndex("masach")));
            phieuMuon.setTienThue(cursor.getFloat(cursor.getColumnIndex("tienthue")));
            phieuMuon.setNgayThue(cursor.getString(cursor.getColumnIndex("ngaytra")));
            phieuMuon.setTrangthai(cursor.getString(cursor.getColumnIndex("trangthai")));
            list.add(phieuMuon);
        }
        return list;
    }
    @SuppressLint("Range")
    public List<sapXep> getByAsc(){
        String sql = "select *,count(*) from phieumuon group by masach order by count(*) DESC LIMIT 10";
        List<sapXep> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()) {
            sapXep sX = new sapXep();
            sX.setMaSach(cursor.getString(cursor.getColumnIndex("masach")));
            sX.setSoLuong(String.valueOf(cursor.getInt(cursor.getColumnIndex("count(*)"))));
            list.add(sX);
        }
        return list;
        //
    }
    public List<phieuMuon> getByDays(String fist, String last){
        String sql = "SELECT * FROM phieumuon WHERE ngaytra BETWEEN '"+fist+"' AND '"+last+"'";
        return get(sql);
    }
    public List<phieuMuon> getAll(){
        String sql = "SELECT * FROM phieumuon";
       return get(sql);
    }
    public phieuMuon getById(String mapm){
        String sql = "SELECT * FROM phieumuon WHERE mapm = ?";
        List<phieuMuon> list = get(sql,mapm);
        return list.get(0);
    }
    public long inserPhieuMuon(phieuMuon phieuMuon){
        ContentValues values = new ContentValues();
        values.put("mapm",phieuMuon.getMaPm());
        values.put("matv",phieuMuon.getMaTv());
        values.put("matt",phieuMuon.getMaTt());
        values.put("masach",phieuMuon.getMasach());
        values.put("tienthue",phieuMuon.getTienThue());
        values.put("ngaytra",phieuMuon.getNgayThue());
        values.put("trangthai",phieuMuon.getTrangthai());
       return db.insert("phieumuon",null,values);
    }
    public int updatePhieuMuon(phieuMuon phieuMuon){
        ContentValues values = new ContentValues();
        values.put("mapm",phieuMuon.getMaPm());
        values.put("matv",phieuMuon.getMaTv());
        values.put("matt",phieuMuon.getMaTt());
        values.put("masach",phieuMuon.getMasach());
        values.put("tienthue",phieuMuon.getTienThue());
        values.put("ngaytra",phieuMuon.getNgayThue());
        values.put("trangthai",phieuMuon.getTrangthai());
        return db.update("phieumuon",values,"mapm = ?",new String[]{String.valueOf(phieuMuon.getMaPm())});
    }
    public int deletePhieuMuon(String phieuMuon){
       return db.delete("phieumuon","mapm = ?",new String[]{phieuMuon});
    }
}
