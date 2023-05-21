package nhuonghvph29406.fpoly.duanmaunhuonghvph29406;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.DB_Dao.thuThuDao;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Model.thuThu;


public class activity_login extends AppCompatActivity implements View.OnClickListener{
    private EditText mEditText_Ten, mEditText_matKhau;
    private Button mButton_dangNhap, mButton_dangHuy;
    private CheckBox mCheckBox;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.man_hinh_dangnhap);
        sharedPreferences = getSharedPreferences("MK" ,MODE_PRIVATE);
        mEditText_Ten = findViewById(R.id.dangNhap_Ten);
        mEditText_matKhau = findViewById(R.id.dangNhap_matKhau);
        mButton_dangNhap = findViewById(R.id.dangNhap_login);
        mButton_dangHuy = findViewById(R.id.dangNhap_huy);
        mCheckBox =  findViewById(R.id.dangNhap_luu);

        mButton_dangNhap.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.dangNhap_login:
                //
                thuThuDao dao = new thuThuDao(activity_login.this);
                List<thuThu> list = dao.getAll();
                if (mEditText_Ten.getText().toString().equals("admin") && mEditText_matKhau.getText().toString().equals("123")){
                    // todo....
                }
                else{
                    try {
                        thuThu thuThu = dao.getById(mEditText_Ten.getText().toString());
                        if (thuThu.getMatKhau().equals(mEditText_matKhau.getText().toString())){
                            Intent intent = new Intent(this,ManHinhChinh.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("TK", mEditText_Ten.getText().toString());
                            bundle.putString("MK", mEditText_matKhau.getText().toString());
                            intent.putExtras(bundle);
                            startActivity(intent);
                            finish();
                            Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            mEditText_matKhau.setError("Sai mật khẩu");
                        }
                    }
                    catch (Exception X){
                        mEditText_Ten.setError("Không tồn tại mật khẩu");
                    }
                }
                break;
            case R.id.dangNhap_huy:
                break;
            case R.id.dangNhap_luu:
                break;
        }
    }
}