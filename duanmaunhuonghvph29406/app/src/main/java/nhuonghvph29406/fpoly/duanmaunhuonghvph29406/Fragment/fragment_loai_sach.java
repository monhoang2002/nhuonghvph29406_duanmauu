package nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;


import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.DB_Dao.loaiSachDao;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Model.loaiSach;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.R;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.adapter.recycleView_adapter_loaiSach;

public class fragment_loai_sach extends Fragment implements View.OnClickListener{
    private View mView;
    recycleView_adapter_loaiSach adapter;
    private RecyclerView mRecyclerView;
    private FloatingActionButton mButton_Add;
    nhuonghvph29406.fpoly.duanmaunhuonghvph29406.DB_Dao.loaiSachDao loaiSachDao;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_loai_sach, container, false);
        loaiSachDao = new loaiSachDao(getContext());
        mRecyclerView = mView.findViewById(R.id.fragment_loaiSach_recycleView);
        List<loaiSach> list = loaiSachDao.getAll();
        adapter = new recycleView_adapter_loaiSach(this, list);
        mRecyclerView.setAdapter(adapter);
        //
        mButton_Add = mView.findViewById(R.id.fragment_loaiSach_add);
        mButton_Add.setOnClickListener(this);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0){
                    mButton_Add.hide();
                }
                else{
                    mButton_Add.show();
                }
            }
        });
        return mView;
    }
    public void doDuLieu(){
        List<loaiSach> list = loaiSachDao.getAll();
        adapter = new recycleView_adapter_loaiSach(this, list);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.fragment_loaiSach_add:
                Dialog dialog = new Dialog(getContext(), androidx.appcompat.R.style.Theme_AppCompat_DayNight_Dialog_Alert);
                EditText mEditText_maLoai, mEditText_tenLoai;
                Button mButton_add, mButton_huy;
                dialog.setContentView(R.layout.custom_add_loaisach);
                mEditText_maLoai = dialog.findViewById(R.id.add_loaiSach_maLoai);
                mEditText_tenLoai = dialog.findViewById(R.id.add_loaiSach_tenLoai);
                mButton_add = dialog.findViewById(R.id.add_loaiSach_btnAdd);
                mButton_huy = dialog.findViewById(R.id.add_loaiSach_btnHuy);
                //
                mButton_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mEditText_maLoai.getText().toString().isEmpty() || mEditText_tenLoai.getText().toString().isEmpty()){
                            if (mEditText_maLoai.getText().toString().isEmpty()){
                                mEditText_maLoai.setError("Không được để trống");
                            }
                            else{
                                mEditText_tenLoai.setError("Không được để trống");
                            }
                            return;
                        }
                        else{
                            loaiSach item = new loaiSach();
                            item.setMaLoai(mEditText_maLoai.getText().toString());
                            item.setTenLoai(mEditText_tenLoai.getText().toString());
                            loaiSachDao.insert(item);
                            doDuLieu();
                            //
                            mEditText_maLoai.setText("");
                            mEditText_tenLoai.setText("");
                            Snackbar snackbar = Snackbar.make(mView,"Thêm thanh Công",Snackbar.LENGTH_LONG);
                           dialog.cancel();
                            snackbar.show();
                        }
                    }
                });
                mButton_huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                        doDuLieu();
                    }
                });
                dialog.show();
                break;
            default:
                break;
        }
    }
    public void deleteLoaiSach(String maloai){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Bạn có muốn xóa không");
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                loaiSachDao.delete(maloai);
                doDuLieu();
                dialogInterface.cancel();
                Snackbar snackbar = Snackbar.make(mView,"Đã xóa thành công", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.show();
    }
    public void updateLoaiSach(loaiSach loaiSach){
        EditText mEditText_maLoai, mEditText_tenLoai;
        Button mButton_update, mButton_huy;
        Dialog dialog = new Dialog(getContext(), androidx.appcompat.R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        dialog.setContentView(R.layout.custom_update_loaisach);
        mEditText_maLoai = dialog.findViewById(R.id.update_loaiSach_maLoai);
        mEditText_tenLoai = dialog.findViewById(R.id.update_loaiSach_tenLoai);
        mButton_huy = dialog.findViewById(R.id.update_loaiSach_btnHuy);
        mButton_update = dialog.findViewById(R.id.update_loaiSach_btnAdd);

        mEditText_maLoai.setText(loaiSach.getMaLoai());
        mEditText_tenLoai.setText(loaiSach.getTenLoai());

        mButton_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        mButton_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loaiSach items = new loaiSach();
                items.setMaLoai(mEditText_maLoai.getText().toString());
                items.setTenLoai(mEditText_tenLoai.getText().toString());
                loaiSachDao.update(items);
                doDuLieu();
                dialog.cancel();
                Snackbar snackbar = Snackbar.make(mView,"Cập nhập thành công",Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });
        dialog.show();

    }
}