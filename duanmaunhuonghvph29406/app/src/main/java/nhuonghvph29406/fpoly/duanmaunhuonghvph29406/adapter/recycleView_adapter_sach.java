package nhuonghvph29406.fpoly.duanmaunhuonghvph29406.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Fragment.fragment_sach;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Model.sach;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.R;

public class recycleView_adapter_sach extends RecyclerView.Adapter<recycleView_adapter_sach.customItem>{
    private fragment_sach mContext;
    private List<sach> list;

    public recycleView_adapter_sach(fragment_sach mContext, List<sach> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public customItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View mView = inflater.inflate(R.layout.custom_item_sach, parent, false);
        customItem mCustomItem = new customItem(mView);
        return mCustomItem;
    }


    @Override
    public void onBindViewHolder(@NonNull customItem holder, int position) {
        holder.mMaSach.setText("Mã sách: "+list.get(holder.getAdapterPosition()).getMaSach());
        holder.mTenSach.setText("Tên sách: "+list.get(holder.getAdapterPosition()).getTenSach());
        holder.mMaLoai.setText("Mã loại: "+list.get(holder.getAdapterPosition()).getMaLoai());
        holder.mGiaThue.setText("Gía thuê: "+list.get(holder.getAdapterPosition()).getGiaThue());
        holder.mDelete.setImageResource(R.drawable.ic_baseline_delete_forever_24);
        holder.mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.xoaSach(list.get(holder.getAdapterPosition()).getMaSach());
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sach items = new sach();
                items.setMaSach(list.get(holder.getAdapterPosition()).getMaSach());
                items.setTenSach(list.get(holder.getAdapterPosition()).getTenSach());
                items.setGiaThue(list.get(holder.getAdapterPosition()).getGiaThue());
                items.setMaLoai(list.get(holder.getAdapterPosition()).getMaLoai());
                mContext.capNhap(items);
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class customItem extends RecyclerView.ViewHolder{
        public TextView mMaSach, mTenSach, mMaLoai, mGiaThue;
        public ImageView mDelete;
        public customItem(@NonNull View itemView) {
            super(itemView);
            mMaSach = itemView.findViewById(R.id.custom_item_sach_maSach);
            mTenSach = itemView.findViewById(R.id.custom_item_sach_tenSach);
            mMaLoai  = itemView.findViewById(R.id.custom_item_sach_maLoai);
            mGiaThue = itemView.findViewById(R.id.custom_item_sach_giaThue);
            mDelete = itemView.findViewById(R.id.custom_item_sach_delete);
        }
    }
}
