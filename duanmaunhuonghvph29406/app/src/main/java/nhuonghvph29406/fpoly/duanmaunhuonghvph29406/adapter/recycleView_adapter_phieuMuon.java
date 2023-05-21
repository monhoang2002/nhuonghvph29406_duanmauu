package nhuonghvph29406.fpoly.duanmaunhuonghvph29406.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Fragment.fragment_phieu_muon;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Model.phieuMuon;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.R;

public class recycleView_adapter_phieuMuon extends RecyclerView.Adapter<recycleView_adapter_phieuMuon.oneItem>{
    private fragment_phieu_muon mContext;
    private List<phieuMuon> list;

    public recycleView_adapter_phieuMuon(fragment_phieu_muon mContext, List<phieuMuon> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public oneItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View mView = inflater.inflate(R.layout.custom_item_phieumuon, parent, false);
        oneItem mOneItem = new oneItem(mView);
        return mOneItem;
    }

    @Override
    public void onBindViewHolder(@NonNull oneItem holder, int position) {
        holder.mMaPhieu.setText("Mã phiếu: "+list.get(holder.getAdapterPosition()).getMaPm());
        holder.mThanhVien.setText("Mã thành viên: "+list.get(holder.getAdapterPosition()).getMaTv());
        holder.mTenSach.setText("Mã sách: "+list.get(holder.getAdapterPosition()).getMasach());
        holder.mTienThue.setText("Tiền thuê: "+ list.get(holder.getAdapterPosition()).getTienThue());
        holder.mTrangThai.setText("Trạng thái: "+list.get(holder.getAdapterPosition()).getTrangthai());
        holder.mNgayThue.setText("Ngày mượn: " +list.get(holder.getAdapterPosition()).getNgayThue());
        holder.mDelete.setImageResource(R.drawable.ic_baseline_delete_forever_24);
        //
        holder.mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.deletePhieuMuon(list.get(holder.getAdapterPosition()).getMaPm());
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phieuMuon pm = new phieuMuon();
                pm.setMaPm(list.get(holder.getAdapterPosition()).getMaPm());
                pm.setTienThue(list.get(holder.getAdapterPosition()).getTienThue());
                pm.setNgayThue(list.get(holder.getAdapterPosition()).getNgayThue());
                pm.setMaTv(list.get(holder.getAdapterPosition()).getMaTv());
                pm.setMaTt(list.get(holder.getAdapterPosition()).getMaTt());
                pm.setMasach(list.get(holder.getAdapterPosition()).getMasach());
                pm.setTrangthai(list.get(holder.getAdapterPosition()).getTrangthai());
                mContext.clickItemRecycleView(pm);
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class oneItem extends RecyclerView.ViewHolder{
        public TextView mMaPhieu, mThanhVien, mTenSach, mTienThue, mTrangThai, mNgayThue;
        public ImageView mDelete;
        public oneItem(@NonNull View itemView) {
            super(itemView);
            mMaPhieu = itemView.findViewById(R.id.custom_item_phieuMuon_maPhieu);
            mThanhVien = itemView.findViewById(R.id.custom_item_phieuMuon_thanhVien);
            mTenSach = itemView.findViewById(R.id.custom_item_phieuMuon_tenSach);
            mTienThue = itemView.findViewById(R.id.custom_item_phieuMuon_tienThue);
            mTrangThai = itemView.findViewById(R.id.custom_item_phieuMuon_trangThai);
            mNgayThue = itemView.findViewById(R.id.custom_item_phieuMuon_ngayThue);
            mDelete = itemView.findViewById(R.id.custom_item_phieuMuon_delete);
        }
    }
}
