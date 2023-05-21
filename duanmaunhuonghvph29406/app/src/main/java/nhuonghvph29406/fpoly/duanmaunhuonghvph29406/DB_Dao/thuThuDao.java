package nhuonghvph29406.fpoly.duanmaunhuonghvph29406.DB_Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Model.thuThu;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.SQL_Dao.DB_Helper;

public class thuThuDao {
    private SQLiteDatabase db;
    //"(matt INTEGER PRIMARY KEY AUTOINCREMENT," +
    //            "matkhau TEXT, hoten TEXT)";

    public thuThuDao(Context context) {
        DB_Helper dbHelper = new DB_Helper(context);
        db = dbHelper.getWritableDatabase();
    }
    @SuppressLint("Range")
    public List<thuThu> get(String sql, String...agrs){
        List<thuThu> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,agrs);
        while (cursor.moveToNext()){
            thuThu thuThu = new thuThu();
            thuThu.setMaTt(cursor.getString(cursor.getColumnIndex("matt")));
            thuThu.setMatKhau(cursor.getString(cursor.getColumnIndex("matkhau")));
            thuThu.setHoTen(cursor.getString(cursor.getColumnIndex("hoten")));
            list.add(thuThu);
        }
        return list;
    }
    public List<thuThu> getAll(){
        String sql = "SELECT * FROM thuthu";
        return get(sql);
    }
    public thuThu getById(String matt){
        String sql = " SELECT * FROM thuthu WHERE matt = ?";
        List<thuThu> list = get(sql,matt);
        return list.get(0);
    }
    public Long inserThuThu(thuThu thuthu){
        ContentValues values = new ContentValues();
        values.put("matt",thuthu.getMaTt());
        values.put("matkhau",thuthu.getMatKhau());
        values.put("hoten",thuthu.getHoTen());
        return db.insert("thuthu",null,values);
    }
    public int upDateThuthu(thuThu thuthu){
        ContentValues values = new ContentValues();
        values.put("matt",thuthu.getMaTt());
        values.put("matkhau",thuthu.getMatKhau());
        values.put("hoten",thuthu.getHoTen());
        return db.update("thuthu",values,"matt = ?",new String[]{thuthu.getMaTt()});
    }
    public int deleteThuthu(String matt){
       return db.delete("thuthu","matt = ?",new String[]{matt});
    }
}
