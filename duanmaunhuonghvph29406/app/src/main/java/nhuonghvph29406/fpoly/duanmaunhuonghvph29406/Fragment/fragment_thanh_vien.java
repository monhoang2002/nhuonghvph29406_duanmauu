package nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.Locale;


import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.DB_Dao.thanhVienDao;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Model.thanhVien;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.R;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.adapter.recycleView_adapter_thanhVien;

public class fragment_thanh_vien extends Fragment implements View.OnClickListener {
    private View mView;
    recycleView_adapter_thanhVien adapter;
    List<thanhVien> list;
    private RecyclerView mRecyclerView;
    private FloatingActionButton mButton_Add;
    nhuonghvph29406.fpoly.duanmaunhuonghvph29406.DB_Dao.thanhVienDao thanhVienDao;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_thanh_vien, container, false);
        thanhVienDao = new thanhVienDao(getContext());
        mRecyclerView = mView.findViewById(R.id.fragment_thanh_vien_recycleView);
        mButton_Add = mView.findViewById(R.id.fragment_thanh_vien_add);
        //

        list = thanhVienDao.getAll();
        adapter = new recycleView_adapter_thanhVien(this,list);
        mRecyclerView.setAdapter(adapter);
        //

        mButton_Add.setOnClickListener(this);
        return mView;
    }
    public void doRaList(){
        List<thanhVien> mlist = thanhVienDao.getAll();
        adapter = new recycleView_adapter_thanhVien(this, mlist);
        mRecyclerView.setAdapter(adapter);
    }
    public void xoaThanhVien(String mathanhVien){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Bạn có muốn xóa");
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                thanhVienDao.deleteThanhVien(mathanhVien);
                doRaList();
                Snackbar snackbar = Snackbar.make(mRecyclerView,"Xóa thành công ", Snackbar.LENGTH_SHORT);
                snackbar.show();
            }
        });
        builder.show();
    }
    public void capNhapThanhVien(thanhVien thanhVien){
         Dialog dialog = new Dialog(getContext(), androidx.appcompat.R.style.Theme_AppCompat_DayNight_Dialog_Alert);
         TextView mTextView_maTV, mTextView_hoTen, mTextView_ngaySinh;
         Button mButton_add, mButton_huy;
         ImageView mImageView_date;
         dialog.setContentView(R.layout.custom_update_thanhvien);
         mImageView_date = dialog.findViewById(R.id.update_thanhVien_datePicker);
         mTextView_ngaySinh = dialog.findViewById(R.id.update_thanhVien_namSinh);
         mTextView_maTV = dialog.findViewById(R.id.update_thanhVien_maThanhVien);
         mTextView_hoTen = dialog.findViewById(R.id.update_thanhVien_hoTen);
         mButton_huy = dialog.findViewById(R.id.update_thanhVien_btnHuy);
         mButton_add = dialog.findViewById(R.id.update_thanhVien_BtnAdd);

         mTextView_maTV.setText(thanhVien.getMaTv());
         mTextView_hoTen.setText(thanhVien.getHoTen());
         mTextView_ngaySinh.setText(thanhVien.getNamSinh());
        mButton_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
         //
        mButton_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thanhVien items = new thanhVien();
                items.setMaTv(mTextView_maTV.getText().toString());
                items.setHoTen((mTextView_hoTen.getText().toString()));
                items.setNamSinh(mTextView_ngaySinh.getText().toString());
                thanhVienDao.updateThanhVien(items);
                doRaList();
                dialog.cancel();
                Snackbar snackbar = Snackbar.make(mView, "Cập nhập thành công",Snackbar.LENGTH_SHORT);
                snackbar.show();
            }
        });
         mImageView_date.setOnClickListener(new View.OnClickListener() {
             @RequiresApi(api = Build.VERSION_CODES.N)
             @Override
             public void onClick(View view) {
                 DatePickerDialog datePickerDialog = new DatePickerDialog(getContext());
                 datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                     @Override
                     public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                         int moth = i1+1;
                         String moths = "";
                         if (i1<9 || i2<10) {
                             if (i1 < 9) {
                                 if (i2 <10){
                                     moths = "0"+(i1+1);
                                     mTextView_ngaySinh.setText(i+"-"+moths+"-"+"0"+i2);
                                 }
                                 else{
                                     moths = "0" + (i1 + 1);
                                     mTextView_ngaySinh.setText(i + "-" + moths + "-" + i2);
                                 }
                             }
                             else {
                                 if (i1 < 9) {
                                     moths = "0" + (i1 + 1);
                                     mTextView_ngaySinh.setText( i+ "-" + moths + "-" + "0" + i2);
                                 } else {
                                     mTextView_ngaySinh.setText( i + "-" + moth + "-" + "0" + i2);
                                 }
                             }
                             //
                         }
                         else{
                             mTextView_ngaySinh.setText(i+"-"+(i1+1)+"-"+i2);;
                         }
                     }
                 });
                 datePickerDialog.show();
             }
         });
         dialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fragment_thanh_vien_add:
                Dialog dialog = new Dialog(getContext(), androidx.appcompat.R.style.Theme_AppCompat_DayNight_Dialog_Alert);
                EditText mEditText_maThanhVien, mEditText_hoTen;
                TextView mTextView_ngayThue;
                ImageView mImageView_date;
                Button mButton_add, mButton_huy;
                dialog.setContentView(R.layout.custom_add_thanhvien);
                mEditText_maThanhVien = dialog.findViewById(R.id.add_thanhVien_maThanhVien);
                mEditText_hoTen = dialog.findViewById(R.id.add_thanhVien_hoTen);
                mTextView_ngayThue = dialog.findViewById(R.id.add_thanhVien_namSinh);
                mImageView_date = dialog.findViewById(R.id.add_thanhVien_datePicker);
                mButton_add = dialog.findViewById(R.id.add_thanhVien_BtnAdd);
                mButton_huy = dialog.findViewById(R.id.add_thanhVien_btnHuy);
                mButton_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    if (mEditText_hoTen.getText().toString().isEmpty() || mEditText_maThanhVien.getText().toString().isEmpty()||
                    mTextView_ngayThue.getText().toString().isEmpty()){
                        if (mEditText_hoTen.getText().toString().isEmpty()){
                            mEditText_maThanhVien.setError("Không để trống");
                            return;
                        }
                        else if(mEditText_maThanhVien.getText().toString().isEmpty()){
                            mEditText_maThanhVien.setError("Không để trống");
                            return;
                        }
                        else{
                            mTextView_ngayThue.setError("Không để trống");
                        }
                    }
                    else{
                        thanhVienDao thanhVienDao = new thanhVienDao(getContext());
                        thanhVien items = new thanhVien();
                        items.setMaTv(mEditText_maThanhVien.getText().toString().toLowerCase(Locale.ROOT));
                        items.setHoTen(mEditText_hoTen.getText().toString().trim());
                        items.setNamSinh(mTextView_ngayThue.getText().toString().trim());
                        thanhVienDao.inserThanhVien(items);
                        doRaList();
                        dialog.cancel();
                        Snackbar snackbar = Snackbar.make(mView,"Thêm thành công",Snackbar.LENGTH_SHORT);
                        snackbar.show();
                    }
                    }
                });
                mButton_huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       dialog.cancel();
                    }
                });
                mImageView_date.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onClick(View view) {
                        DatePickerDialog pickerDialog = new DatePickerDialog(getContext());
                        pickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                int moth = i1+1;
                                String moths = "";
                                if (i1<9 || i2<10) {
                                    if (i1 < 9) {
                                        if (i2 <10){
                                            moths = "0"+(i1+1);
                                            mTextView_ngayThue.setText(i+"-"+moths+"-"+"0"+i2);
                                        }
                                        else{
                                            moths = "0" + (i1 + 1);
                                            mTextView_ngayThue.setText(i + "-" + moths + "-" + i2);
                                        }
                                    }
                                    else {
                                        if (i1 < 9) {
                                            moths = "0" + (i1 + 1);
                                            mTextView_ngayThue.setText( i+ "-" + moths + "-" + "0" + i2);
                                        } else {
                                            mTextView_ngayThue.setText( i + "-" + moth + "-" + "0" + i2);
                                        }
                                    }
                                    //
                                }
                                else{
                                    mTextView_ngayThue.setText(i+"-"+(i1+1)+"-"+i2);;
                                }
                            }
                        });
                        pickerDialog.show();
                    }
                });
                dialog.show();
                break;
            default:
                break;
        }
    }
}