package nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;


import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.DB_Dao.loaiSachDao;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.DB_Dao.sachDao;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Model.loaiSach;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Model.sach;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.R;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.adapter.recycleView_adapter_sach;

public class fragment_sach extends Fragment implements View.OnClickListener{
    recycleView_adapter_sach adapter;
    List<sach> list;
    private RecyclerView mRecyclerView;
   private View mView;
   private FloatingActionButton mButton_Add;
   sachDao sachDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_sach, container, false);
        mRecyclerView = mView.findViewById(R.id.fragment_Sach_recycleView);
        sachDao = new sachDao(getContext());
        list = sachDao.getAll();
        adapter = new recycleView_adapter_sach(this,list);
        mRecyclerView.setAdapter(adapter);
        mButton_Add = mView.findViewById(R.id.fragment_Sach_add);
        mButton_Add.setOnClickListener(this);
        return mView;
    }
    public void doDuLieu(){
        List<sach> mList = sachDao.getAll();
        recycleView_adapter_sach adapter = new recycleView_adapter_sach(this,mList);
        mRecyclerView.setAdapter(adapter);
    }
    public void xoaSach(String masach){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Bạn có thật sự muốn xóa ?");
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                sachDao.deleteSach(masach);
                doDuLieu();
                dialogInterface.cancel();
                Snackbar snackbar = Snackbar.make(mRecyclerView,"Xóa thành công", Snackbar.LENGTH_SHORT);
                snackbar.show();
            }
        });
        builder.show();
    }
    public void capNhap(sach items){
        Dialog dialog = new Dialog(getContext(), androidx.appcompat.R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        EditText mEditText_maSach, mEditText_tenSach, mEditText_giaThue;
        TextView mTextView_maLoai;
        Button mButton_update, mButton_huy;
        dialog.setContentView(R.layout.custom_update_sach);
        mEditText_maSach = dialog.findViewById(R.id.update_sach_maSach);
        mEditText_maSach.setText(items.getMaSach());
        mEditText_tenSach = dialog.findViewById(R.id.update_sach_tenSach);
        mEditText_tenSach.setText(items.getTenSach());
        mEditText_giaThue = dialog.findViewById(R.id.update_sach_giaThue);
        mEditText_giaThue.setText(items.getGiaThue().toString());
        mTextView_maLoai = dialog.findViewById(R.id.update_sach_maLoai);
        mTextView_maLoai.setText(items.getMaLoai());
        mButton_huy = dialog.findViewById(R.id.update_sach_btnHuy);
        mButton_update = dialog.findViewById(R.id.update_sach_update);

        mButton_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        mButton_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             sach items = new sach();
             items.setMaLoai(mTextView_maLoai.getText().toString());
             items.setMaSach(mEditText_maSach.getText().toString());
             items.setGiaThue(Float.valueOf(mEditText_giaThue.getText().toString()));
             items.setTenSach(mEditText_tenSach.getText().toString());
             sachDao.updateSach(items);
             doDuLieu();
             dialog.cancel();
             Snackbar snackbar = Snackbar.make(mRecyclerView, "Cập nhập thành công ", Snackbar.LENGTH_SHORT);
             snackbar.show();
            }
        });
        mTextView_maLoai.setOnClickListener(new View.OnClickListener() {
            ListView mListView;
            ArrayAdapter adapter;
            loaiSachDao dao = new loaiSachDao(getContext());
            List<loaiSach> listLoaiSach = dao.getAll();
            @Override
            public void onClick(View view) {
                Dialog dialog1 = new Dialog(getContext(), androidx.appcompat.R.style.Theme_AppCompat_DayNight_Dialog_Alert);
                dialog1.setContentView(R.layout.custom_show_list);
                mListView = dialog1.findViewById(R.id.show_lisview);
                List<String> listMaLoai = new ArrayList<>();
                for (loaiSach x: listLoaiSach){
                    listMaLoai.add(x.getMaLoai());
                }
                adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, listMaLoai);
                mListView.setAdapter(adapter);
                //
                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        mTextView_maLoai.setText(listMaLoai.get(i));
                        dialog1.cancel();
                    }
                });
                dialog1.show();
            }
        });
        dialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fragment_Sach_add:
                Dialog dialog = new Dialog(getContext(), androidx.appcompat.R.style.Theme_AppCompat_DayNight_Dialog_Alert);
                EditText mEditText_maSach, mEditText_tenSach, mEditText_giaThue;
                Button mButton_add, mButton_huy;
                Spinner mSpinner;
                loaiSachDao loaisachdoa = new loaiSachDao(getContext());
                //

                dialog.setContentView(R.layout.custom_add_sach);
                mEditText_maSach = dialog.findViewById(R.id.add_sach_maSach);
                mEditText_tenSach = dialog.findViewById(R.id.add_sach_tenSach);
                mEditText_giaThue = dialog.findViewById(R.id.add_sach_giaThue);
                mButton_add = dialog.findViewById(R.id.add_sach_btnAdd);
                mButton_huy = dialog.findViewById(R.id.add_sach_btnHuy);
                mSpinner = dialog.findViewById(R.id.add_sach_spiner);
                //
                List<loaiSach> listLoaiSach = loaisachdoa.getAll();
                List<String> listMaLoaiSach = new ArrayList<>();
                listMaLoaiSach.add("Mã loại");
                for (loaiSach x : listLoaiSach){
                    String maLoai = x.getMaLoai();
                    listMaLoaiSach.add(maLoai);
                }
                ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1,listMaLoaiSach);
                mSpinner.setAdapter(adapter);
                //
                mButton_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int selectItemSpiner = mSpinner.getSelectedItemPosition();
                        String valuesItem = mSpinner.getItemAtPosition(selectItemSpiner).toString();
                    if (mEditText_maSach.getText().toString().isEmpty() ||
                        mEditText_giaThue.getText().toString().isEmpty() || valuesItem.equals("Mã loại")){
                                if (mEditText_maSach.getText().toString().isEmpty()){
                                    mEditText_maSach.setError("Không được để trống");
                                    return;
                                }
                                else if(mEditText_giaThue.getText().toString().isEmpty()){
                                    mEditText_giaThue.setError("Không được để trống");
                                    return;
                                }
                                else{
                                    Snackbar snackbar = Snackbar.make(view,"Chọn mã loại",Snackbar.LENGTH_SHORT);
                                    snackbar.show();
                                    return;
                                }
                    }
                    else{
                        nhuonghvph29406.fpoly.duanmaunhuonghvph29406.DB_Dao.sachDao sachDao = new sachDao(getContext());
                        sach item = new sach();
                        item.setMaSach(mEditText_maSach.getText().toString());
                        item.setTenSach(mEditText_tenSach.getText().toString());
                        item.setGiaThue(Float.valueOf(mEditText_giaThue.getText().toString()));
                        item.setMaLoai(valuesItem);
                        sachDao.inserSach(item);
                        doDuLieu();
                        //
                        mEditText_maSach.setText("");
                        mEditText_tenSach.setText("");
                        mEditText_giaThue.setText("");
                        Snackbar snackbar = Snackbar.make(view,"Thêm thành công",Snackbar.LENGTH_LONG);
                        snackbar.setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.cancel();
                            }
                        });
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
                mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });

                dialog.show();
                break;
            default:
                break;
        }
    }
}