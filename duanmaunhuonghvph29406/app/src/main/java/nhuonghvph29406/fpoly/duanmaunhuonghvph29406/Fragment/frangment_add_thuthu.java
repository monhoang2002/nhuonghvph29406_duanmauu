package nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;


import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.DB_Dao.thuThuDao;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.ManHinhChinh;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Model.thuThu;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.R;

public class frangment_add_thuthu extends Fragment {
    EditText mEditText_maThuThu, mEditText_TenThuThu, mEditText_MatKhau, mEditText_repass;
    Button mButton_add, mButton_huy;
    View mView;
    ManHinhChinh manHinhChinh;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.custom_add_thuthu, container, false);
        mEditText_maThuThu = mView.findViewById(R.id.custom_add_mathuthu);
        mEditText_TenThuThu = mView.findViewById(R.id.custom_add_thuthu_tenThuThu);
        mEditText_MatKhau = mView.findViewById(R.id.custom_add_thuthu_mk);
        mEditText_repass = mView.findViewById(R.id.custom_add_thuthu_repass);
        mButton_add = mView.findViewById(R.id.custom_add_thuthu_add);
        mButton_huy = mView.findViewById(R.id.custom_add_thuthu_huy);
        mButton_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEditText_maThuThu.getText().toString().isEmpty() || mEditText_TenThuThu.getText().toString().isEmpty()
                || mEditText_MatKhau.getText().toString().isEmpty() || mEditText_repass.getText().toString().isEmpty()){
                    if (mEditText_maThuThu.getText().toString().isEmpty()){
                        mEditText_TenThuThu.setError("Không được để trống");
                        return;
                    }
                    else if (mEditText_TenThuThu.getText().toString().isEmpty()){
                        mEditText_TenThuThu.setError("Không được để trống");
                        return;
                    }
                    else if( mEditText_MatKhau.getText().toString().isEmpty()){
                        mEditText_MatKhau.setError("Không được để trống");
                        return;
                    }
                    else{
                        mEditText_repass.setError("Không được để trống");
                        return;
                    }
                }
                else {
                    if (mEditText_MatKhau.getText().toString().equals(mEditText_repass.getText().toString())){
                        thuThuDao dao = new thuThuDao(getContext());
                        thuThu items = new thuThu();
                        items.setMaTt(mEditText_maThuThu.getText().toString().trim());
                        items.setHoTen(mEditText_TenThuThu.getText().toString().trim());
                        items.setMatKhau(mEditText_repass.getText().toString().trim());
                        dao.inserThuThu(items);
                        mEditText_maThuThu.setText("");
                        mEditText_TenThuThu.setText("");
                        mEditText_MatKhau.setText("");
                        mEditText_repass.setText("");
                        Snackbar snackbar = Snackbar.make(mView,"Tạo tài khoản thành công", Snackbar.LENGTH_SHORT);
                        snackbar.show();

                    }
                    else{
                        mEditText_repass.setError("Mật khẩu không trùng nhau");
                        return;
                    }
                }

            }
        });
        mButton_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEditText_maThuThu.setText("");
                mEditText_TenThuThu.setText("");
                mEditText_MatKhau.setText("");
                mEditText_repass.setText("");
            }
        });
        return mView;
    }
}