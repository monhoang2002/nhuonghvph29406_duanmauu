package nhuonghvph29406.fpoly.duanmaunhuonghvph29406;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.DB_Dao.thuThuDao;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Model.thuThu;

public class activity_splash_screen extends AppCompatActivity {
    LinearLayout mLinearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chao);
        mLinearLayout = findViewById(R.id.manHinhCho);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
            mLinearLayout.setVerticalGravity(View.INVISIBLE);
                thuThuDao thuThuDao = new thuThuDao(activity_splash_screen.this);
                thuThu tt = new thuThu();
                tt.setMaTt("nhuong");
                tt.setHoTen("hoang van nhuong");
                tt.setMatKhau("123");
                thuThuDao.inserThuThu(tt);
                Intent intent = new Intent(activity_splash_screen.this, activity_login.class);
                startActivity(intent);
                finish();
            }
        },2000);

    }
}