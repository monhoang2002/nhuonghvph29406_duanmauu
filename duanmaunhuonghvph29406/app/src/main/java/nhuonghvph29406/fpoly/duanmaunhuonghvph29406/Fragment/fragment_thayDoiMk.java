package nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Fragment;

import static android.util.Log.d;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;


import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.DB_Dao.thuThuDao;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Model.thuThu;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.R;

public class fragment_thayDoiMk extends Fragment {
    EditText mEditText_oldP, mEditText_newPass, mEditText_repass;
    Button mButton_save, mButton_cancel;
    View mView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_thay_doi_mk, container, false);
        String maTT = getArguments().getString("maTT");
        mEditText_oldP = mView.findViewById(R.id.old_pass);
        mEditText_newPass = mView.findViewById(R.id.newPass);
        mEditText_repass = mView.findViewById(R.id.repass);
        mButton_save = mView.findViewById(R.id.btn_save);
        mButton_cancel = mView.findViewById(R.id.btn_cancel);
        mButton_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEditText_oldP.getText().toString().isEmpty() || mEditText_newPass.getText().toString().isEmpty() ||
                    mEditText_repass.getText().toString().isEmpty()){
                    if (mEditText_oldP.getText().toString().isEmpty()){
                        mEditText_oldP.setError("Không được để trống");
                        return;
                    }
                    else if(mEditText_newPass.getText().toString().isEmpty()){
                        mEditText_newPass.setError("Không được để trống");
                        return;
                    }
                    else{
                        mEditText_repass.setError("Không được để trống");
                        return;
                    }
                }
                else {
                    if (mEditText_newPass.getText().toString().equals(mEditText_repass.getText().toString())){
                        thuThuDao dao = new thuThuDao(getContext());
                        thuThu items = dao.getById(maTT);
                        if (mEditText_oldP.getText().toString().equals(items.getMatKhau())){
                            thuThu itemNew = new thuThu();
                            itemNew.setMaTt(maTT);
                            d("ca" + "chung", "onClick: "+maTT);
                            itemNew.setHoTen(items.getHoTen());
                            itemNew.setMatKhau(mEditText_repass.getText().toString().trim());
                            d("ca" + "chung", "onClick: "+items.getHoTen());
                            d("ca" + "chung", "onClick: "+mEditText_repass.getText().toString());
                            dao.upDateThuthu(itemNew);
                            mEditText_oldP.setText("");
                            mEditText_newPass.setText("");
                            mEditText_repass.setText("");
                            Snackbar snackbar = Snackbar.make(mView, "Thay đổi thành công",Snackbar.LENGTH_SHORT);
                            snackbar.show();
                        }
                        else{
                            mEditText_oldP.setError("Không trùng mật khẩu");
                        }
                    }
                    else{
                        mEditText_repass.setError("Mật khẩu không trùng nhau");
                    }
            }
            }

        });
        mButton_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEditText_oldP.setText("");
                mEditText_newPass.setError("");
                mEditText_repass.setText("");
            }
        });
        return mView;
    }
}