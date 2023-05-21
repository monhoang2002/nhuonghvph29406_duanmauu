package nhuonghvph29406.fpoly.duanmaunhuonghvph29406.SQL_Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DB_Helper extends SQLiteOpenHelper {
    public static final String DB_NAME = "PNLib";
    public static final int VERSION = 5;
    public DB_Helper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }
    private static final String CREATE_TABLE_THUTHU = "CREATE TABLE thuthu " +
            "(matt TEXT PRIMARY KEY," +
            "matkhau TEXT, hoten TEXT)";
//
    public static final String CREATE_TABLE_PHIEUMUON = "CREATE TABLE phieumuon" +
            "(mapm TEXT PRIMARY KEY, " +
            "matv TEXT REFERENCES thanhvien(matv)," +
            "matt TEXT REFERENCES thuthu(matt)," +
            "masach TEXT REFERENCES sach(masach)," +
            "tienthue FLOAT, ngaytra DATE, trangthai TEXT)";
    public static final String CREATE_TABLE_THANHVIEN = "CREATE TABLE thanhvien(" +
            "matv TEXT PRIMARY KEY," +
            "namsinh TEXT, " +
            "hoten TEXT)";
    public static final String CREATE_TABLE_LOAISACH = "CREATE TABLE loaisach(" +
            "maloai TEXT PRIMARY KEY," +
            "tenloai TEXT)";
    public static final String CRETE_TABLE_SACH = "CREATE TABLE sach" +
            "(masach TEXT PRIMARY KEY," +
            "tensach TEXT,giathue FLOAT," +
            "maloai TEXT REFERENCES loaisach(maloai))";
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_THUTHU);
        db.execSQL(CREATE_TABLE_PHIEUMUON);
        db.execSQL(CREATE_TABLE_THANHVIEN);
        db.execSQL(CREATE_TABLE_LOAISACH);
        db.execSQL(CRETE_TABLE_SACH);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String DROP_TABLE_THUTHU = ("DROP TABLE IF EXISTS thuthu ");
        db.execSQL(DROP_TABLE_THUTHU);
        String DROP_TABLE_PHIEUMUON = ("DROP TABLE IF EXISTS phieumuon ");
        db.execSQL(DROP_TABLE_PHIEUMUON);
        String DROP_TABLE_THANHVIEN = ("DROP TABLE IF EXISTS thanhvien ");
        db.execSQL(DROP_TABLE_THANHVIEN);
        String DROP_TABLE_LOAISACH = ("DROP TABLE IF EXISTS loaisach ");
        db.execSQL(DROP_TABLE_LOAISACH);
        String DROP_TABLE_SACH = ("DROP TABLE IF EXISTS sach ");
        db.execSQL(DROP_TABLE_SACH);
        onCreate(db);

    }
}
