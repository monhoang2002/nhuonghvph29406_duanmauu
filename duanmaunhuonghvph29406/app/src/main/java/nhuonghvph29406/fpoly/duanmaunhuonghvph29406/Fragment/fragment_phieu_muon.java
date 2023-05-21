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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;


import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.DB_Dao.phieuMuonDao;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.DB_Dao.sachDao;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.DB_Dao.thanhVienDao;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.DB_Dao.thuThuDao;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Model.phieuMuon;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Model.sach;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Model.thanhVien;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Model.thuThu;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.R;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.adapter.recycleView_adapter_phieuMuon;


public class fragment_phieu_muon extends Fragment implements View.OnClickListener{
    private View mView;
    RecyclerView mRecyclerView;
    recycleView_adapter_phieuMuon adapter;
    List<phieuMuon> list;
    phieuMuon phieuMuon;
    phieuMuonDao dao;
    private FloatingActionButton mButton_Add;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.fragment_phieu_muon, container, false);
        mButton_Add = mView.findViewById(R.id.fragment_phieuMuon_add);
        mButton_Add.setOnClickListener(this);
        mRecyclerView = mView.findViewById(R.id.fragment_phieuMuon_recycleView);
        dao = new phieuMuonDao(getContext());
        list = dao.getAll();
        adapter = new recycleView_adapter_phieuMuon(this,list);
        mRecyclerView.setAdapter(adapter);
        return mView;
    }
    public void doDuLieu(){
        dao = new phieuMuonDao(getContext());
        List<phieuMuon> mList = dao.getAll();
        adapter = new recycleView_adapter_phieuMuon(this,mList);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fragment_phieuMuon_add:
                Dialog dialog = new Dialog(getContext(), androidx.appcompat.R.style.Theme_AppCompat_DayNight_Dialog_Alert);
                EditText mEditText_maPhieuMuon, mEditText_tienThue;
                TextView mTextView_ngayThue, mTextView_maThanhVien, mTextView_maThuThu,
                        mTextView_maSach;
                ImageView mImageView_date;
                Spinner mSpinner_maThanhVien, mSpinner_maThuThu, mSpinner_maSach;
                Button mButton_add, mButton_huy;
                dialog.setContentView(R.layout.custom_add_item_phieumuon);
                mButton_add = dialog.findViewById(R.id.add_phieuMuon_BtnAdd);
                mButton_huy = dialog.findViewById(R.id.add_phieuMuon_BtnHuy);
                mImageView_date = dialog.findViewById(R.id.add_phieuMuon_datePicker);
                mSpinner_maThanhVien = dialog.findViewById(R.id.add_phieuMuon_Spiner_maThanhVien);
                mSpinner_maThuThu = dialog.findViewById(R.id.add_phieuMuon_spiner_maThuThu);
                mSpinner_maSach = dialog.findViewById(R.id.add_phieuMuon_spiner_maSach);
                mTextView_ngayThue = dialog.findViewById(R.id.add_phieuMuon_ngayThue);
                mEditText_maPhieuMuon = dialog.findViewById(R.id.add_phieuMuon_maPhieu);
                mEditText_tienThue = dialog.findViewById(R.id.add_phieuMuon_tienThue);
                //

                //
                mButton_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int item_matv = mSpinner_maThanhVien.getSelectedItemPosition();
                        int iTem_maTT = mSpinner_maThuThu.getSelectedItemPosition();
                        int item_MaSach = mSpinner_maSach.getSelectedItemPosition();
                        String valuesItem_matv = mSpinner_maThanhVien.getItemAtPosition(item_matv).toString();
                        String values_maTT = mSpinner_maThuThu.getItemAtPosition(iTem_maTT).toString();
                        String values_maSach = mSpinner_maSach.getItemAtPosition(item_MaSach).toString();
                        //
                        if (mEditText_maPhieuMuon.getText().toString().isEmpty() || mEditText_tienThue.getText().toString().isEmpty()
                        || valuesItem_matv.equals("Mã thành viên") || values_maTT.equals("Mã thủ thư")|| values_maSach.equals("Mã sách")){
                            if (mEditText_maPhieuMuon.getText().toString().isEmpty()){
                                mEditText_maPhieuMuon.setError("Không được để trống");
                                return;
                            }
                            else if(mEditText_tienThue.getText().toString().isEmpty()){
                                mEditText_tienThue.setError("Không được để trống");
                                return;
                            }
                            else if(valuesItem_matv.equals("Mã thành viên")){
                                Snackbar snackbar = Snackbar.make(view,"Chưa chọn mã thành viên", Snackbar.LENGTH_SHORT);
                                snackbar.show();
                                return;
                            }
                            else if(values_maTT.equals("Mã thủ thư")){
                                Snackbar snackbar = Snackbar.make(view,"Chưa chọn mã thủ thư", Snackbar.LENGTH_SHORT);
                                snackbar.show();
                                return;
                            }
                            else {
                                Snackbar snackbar = Snackbar.make(view,"Chưa chọn mã mã sách", Snackbar.LENGTH_SHORT);
                                snackbar.show();
                                return;
                            }
                        }
                        else{
//                            DateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
//                            DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
//                            Date date = null;
//                            try {
//                                date = inputFormat.parse(mTextView_ngayThue.getText().toString());
//                            } catch (ParseException e) {
//                                e.printStackTrace();
//                            }
//                            String resultDate = outputFormat.format(date);

                            //
                            phieuMuon phieuMuon = new phieuMuon();
                            phieuMuon.setMaPm(mEditText_maPhieuMuon.getText().toString());
                            phieuMuon.setTienThue(Float.parseFloat(mEditText_tienThue.getText().toString()));
                            phieuMuon.setMaTv(valuesItem_matv);
                            phieuMuon.setMaTt(values_maTT);
                            phieuMuon.setNgayThue(mTextView_ngayThue.getText().toString());
                            phieuMuon.setMasach(values_maSach);
                            phieuMuonDao phieuMuonDao = new phieuMuonDao(getContext());
                            phieuMuonDao.inserPhieuMuon(phieuMuon);
                            mEditText_maPhieuMuon.setText("");
                            mEditText_tienThue.setText("");
                            mTextView_ngayThue.setText("");
                            doDuLieu();
                            dialog.cancel();
                            Snackbar snackbar = Snackbar.make(mView,"Thêm thành công", Snackbar.LENGTH_SHORT);
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
                    @Override
                    public void onClick(View view) {
                    }
                });
                //
                thanhVienDao tvDao = new thanhVienDao(getContext());
                List<thanhVien> tv = tvDao.getAll();
                List<String> listMaTV = new ArrayList<>();
                listMaTV.add("Mã thành viên");
                for (thanhVien x : tv){
                    listMaTV.add(x.getMaTv());
                }
                ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1,listMaTV);
                mSpinner_maThanhVien.setAdapter(adapter);
                //
                mSpinner_maThanhVien.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                //
                thuThuDao TTdao = new thuThuDao(getContext());
                List<thuThu> listTT = TTdao.getAll();
                List<String> listMaTT = new ArrayList<>();
                listMaTT.add("Mã thủ thư");
                for (thuThu x : listTT){
                    listMaTT.add(x.getMaTt());
                }
                ArrayAdapter adapter1 = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1,listMaTT);
                mSpinner_maThuThu.setAdapter(adapter1);
                mSpinner_maThuThu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                //
                sachDao sachDao = new sachDao(getContext());
                List<sach> listSach = sachDao.getAll();
                List<String> listMaSach = new ArrayList<>();
                listMaSach.add("Mã sách");
                for (sach x : listSach){
                    listMaSach.add(x.getMaSach());
                }
                ArrayAdapter adapter2 = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1,listMaSach);
                mSpinner_maSach.setAdapter(adapter2);
                //
                mSpinner_maSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

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
                        datePickerDialog.show();
                    }
                });
                dialog.show();
                break;
            default:
                break;
        }
    }
    public void deletePhieuMuon( String maPhieuMuon){
        phieuMuonDao phieuMuonDao = new phieuMuonDao(getContext());
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Bạn có muốn xóa không ?");
        builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                phieuMuonDao.deletePhieuMuon(maPhieuMuon);
                doDuLieu();
                dialogInterface.cancel();
                Snackbar snackbar = Snackbar.make(mView, "Xóa thành công", Snackbar.LENGTH_SHORT);
                snackbar.show();
            }
        });
        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.show();

        doDuLieu();
    }
        public void clickItemRecycleView(nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Model.phieuMuon phieuMuon){
          Dialog dialog = new Dialog(getContext(), androidx.appcompat.R.style.Theme_AppCompat_DayNight_Dialog_Alert);
          EditText mEditText_maPM, mEditText_tienThue;
          TextView mTextView_ngayThue, mTextView_maThanhVien, mTextView_maThuThu, mTextView_maSach,
                  mEditText_trangThai;
          ImageView mImageView_date;
          Button mButton_btn_update,mButton_btnHuy;


          dialog.setContentView(R.layout.custom_update_phieumuon);
          mEditText_maPM = dialog.findViewById(R.id.update_phieuMuon_maPhieu);
          mEditText_tienThue = dialog.findViewById(R.id.update_phieuMuon_tienThue);
          mTextView_ngayThue = dialog.findViewById(R.id.update_phieuMuon_ngayThue);
          mEditText_trangThai = dialog.findViewById(R.id.update_phieuMuon_trangThai);
          mTextView_maThanhVien = dialog.findViewById(R.id.update_phieuMuon_maThanhVien);
          mTextView_maThuThu = dialog.findViewById(R.id.update_phieuMuon_maThuThu);
          mTextView_maSach = dialog.findViewById(R.id.update_phieuMuon_maSach);
          mImageView_date = dialog.findViewById(R.id.update_phieuMuon_datePicker);
          mButton_btn_update = dialog.findViewById(R.id.update_phieuMuon_BtnAdd);
          mButton_btnHuy = dialog.findViewById(R.id.update_phieuMuon_BtnHuy);

          //
            mEditText_maPM.setText(phieuMuon.getMaPm());
            mEditText_tienThue.setText(phieuMuon.getTienThue()+"");
            mTextView_ngayThue.setText(phieuMuon.getNgayThue());
            mEditText_trangThai.setText(phieuMuon.getTrangthai());
            mTextView_maThanhVien.setText(phieuMuon.getMaTv());
            mTextView_maThuThu.setText(phieuMuon.getMaTt());
            mTextView_maSach.setText(phieuMuon.getMasach());
            //
            mButton_btnHuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.cancel();
                }
            });
            mButton_btn_update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    phieuMuon phieuMuon = new phieuMuon();
                    phieuMuon.setMaPm(mEditText_maPM.getText().toString());
                    phieuMuon.setTienThue(Float.parseFloat(mEditText_tienThue.getText().toString()));
                    phieuMuon.setMaTv(mTextView_maThanhVien.getText().toString());
                    phieuMuon.setMaTt(mTextView_maThuThu.getText().toString());
                    phieuMuon.setNgayThue(mTextView_ngayThue.getText().toString());
                    phieuMuon.setMasach(mTextView_maSach.getText().toString());
                    phieuMuon.setTrangthai(mEditText_trangThai.getText().toString());
                    dao.updatePhieuMuon(phieuMuon);
                    doDuLieu();
                    Snackbar snackbar = Snackbar.make(mView , "Cập nhập thành công", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                    dialog.cancel();
                }
            });
            //
            mTextView_maThanhVien.setOnClickListener(new View.OnClickListener() {
                ListView mListView;
                ArrayAdapter adapter;
                List<thanhVien> listTV;
                List<String> listMTV ;
                thanhVienDao dao;
                @Override
                public void onClick(View view) {
                    //
                    Dialog dialog1 = new Dialog(getContext());
                    dialog1.setContentView(R.layout.custom_show_list);
                    mListView = dialog1.findViewById(R.id.show_lisview);
                    dao = new thanhVienDao(getContext());
                    listTV = dao.getAll();
                    listMTV = new ArrayList<>();
                    for (thanhVien x : listTV){
                        listMTV.add(x.getMaTv());
                    }
                    adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,listMTV);
                    mListView.setAdapter(adapter);
                    mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            mTextView_maThanhVien.setText(listMTV.get(i));
                            dialog1.cancel();
                        }
                    });
                    dialog1.show();

                }
            });
            //
            mTextView_maThuThu.setOnClickListener(new View.OnClickListener() {
                thuThuDao dao;
                List<thuThu> listTT;
                ArrayAdapter adapter;
                ListView mListView;
                @Override
                public void onClick(View view) {
                    dao = new thuThuDao(getContext());
                    listTT = dao.getAll();
                    List<String> listMaTT = new ArrayList<>();
                    for (thuThu x : listTT){
                        listMaTT.add(x.getMaTt());
                    }
                    //
                    Dialog dialog = new Dialog(getContext());
                    dialog.setContentView(R.layout.custom_show_list);
                    mListView = dialog.findViewById(R.id.show_lisview);
                    adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1,listMaTT);
                    mListView.setAdapter(adapter);
                    mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            mTextView_maThuThu.setText(listMaTT.get(i));
                            dialog.cancel();
                        }
                    });
                    dialog.show();
                }
            });
            //
            mTextView_maSach.setOnClickListener(new View.OnClickListener() {
                sachDao dao;
                List<sach> listSach;
                ArrayAdapter adapter;
                ListView mListView;
                @Override
                public void onClick(View view) {
                    dao = new sachDao(getContext());
                    listSach = dao.getAll();
                    List<String> listMaSach = new ArrayList<>();
                    for (sach x : listSach){
                        listMaSach.add(x.getMaSach());
                    }
                    //
                    Dialog dialog = new Dialog(getContext());
                    dialog.setContentView(R.layout.custom_show_list);
                    mListView = dialog.findViewById(R.id.show_lisview);
                    adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1,listMaSach);
                    mListView.setAdapter(adapter);
                    mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            mTextView_maSach.setText(listMaSach.get(i));
                            dialog.cancel();
                        }
                    });
                    dialog.show();
                }
            });
            //
            mEditText_trangThai.setOnClickListener(new View.OnClickListener() {
                ArrayAdapter adapter;
                @Override
                public void onClick(View view) {
                    Dialog dialog = new Dialog(getContext());
                    dialog.setContentView(R.layout.custom_show_list);
                    List<String> list = new ArrayList<>();
                    list.add("Chưa trả");
                    list.add("Đã trả");
                    adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1,list);
                    ListView mListView = dialog.findViewById(R.id.show_lisview);
                    mListView.setAdapter(adapter);
                    mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            mEditText_trangThai.setText(list.get(i));
                            dialog.cancel();
                        }
                    });
                    dialog.show();
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
                    datePickerDialog.show();
                }
            });
          dialog.show();
        }
}