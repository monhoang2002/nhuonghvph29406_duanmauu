package nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.DB_Dao.phieuMuonDao;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Model.phieuMuon;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.R;


public class fragment_doanh_thu extends Fragment {
    private View mView;
    EditText mEditText_fistDays, mEditText_LastDay;
    TextView mTextView_hienThi;
    Button mButton_tinh;
        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_doanh_thu, container, false);
        mEditText_fistDays = mView.findViewById(R.id.fagment_doanhThu_tuNgay);
        mEditText_LastDay = mView.findViewById(R.id.fragment_doanhThu_denNgay);
        mTextView_hienThi = mView.findViewById(R.id.fragment_doanhThu_Tien);
        mButton_tinh = mView.findViewById(R.id.fragment_doanhThu_tinh);

        mButton_tinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEditText_fistDays.getText().toString().isEmpty() || mEditText_LastDay.getText().toString().isEmpty()){
                    if (mEditText_fistDays.getText().toString().isEmpty()){
                        mEditText_fistDays.setError("Không được bỏ trống");
                    }
                    else{
                        mEditText_LastDay.setError("Không được bỏ trống");
                    }
                }
                else{
                    SimpleDateFormat input = new SimpleDateFormat("dd/mm/yyyy");
                    SimpleDateFormat outPut = new SimpleDateFormat("yyyy-mm-dd");
                    try {
                        Date date1 = input.parse(mEditText_fistDays.getText().toString());
                        String fist1 = outPut.format(date1);
                        Date date2 = input.parse(mEditText_LastDay.getText().toString());
                        String last = outPut.format(date2);
                        phieuMuonDao dao = new phieuMuonDao(getContext());
                        List<phieuMuon> listPM = dao.getByDays(fist1, last);
                        Float tong = 0F;
                        for (phieuMuon x : listPM){
                            tong += Float.parseFloat(String.valueOf(x.getTienThue()));
                        }
                        mTextView_hienThi.setText(String.valueOf(tong)+"VND");


                    } catch (ParseException e) {
                        Snackbar snackbar = Snackbar.make(mView,"Vui lòng nhập đúng định dạng",Snackbar.LENGTH_SHORT);
                        snackbar.show();
                        e.printStackTrace();
                    }
                }
            }
        });
        return mView;
    }
}