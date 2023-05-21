package nhuonghvph29406.fpoly.duanmaunhuonghvph29406.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Fragment.fragment_loai_sach;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Model.loaiSach;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.R;

public class recycleView_adapter_loaiSach extends RecyclerView.Adapter<recycleView_adapter_loaiSach.customItem>{
    private fragment_loai_sach mContext;
    private List<loaiSach> list;

    public recycleView_adapter_loaiSach(fragment_loai_sach mContext, List<loaiSach> list) {
        this.mContext = mContext;
        this.list = list;
    }
    @NonNull
    @Override
    public customItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View mView = inflater.inflate(R.layout.custom_item_loaisach, parent, false);
        customItem mCustomItem = new customItem(mView);
        return mCustomItem;
    }

    @Override
    public void onBindViewHolder(@NonNull customItem holder, int position) {
        holder.mMaLoai.setText("Mã loại: "+String.valueOf(list.get(holder.getAdapterPosition()).getMaLoai()));
        holder.mTenLoai.setText("Tên loại: "+String.valueOf(list.get(holder.getAdapterPosition()).getTenLoai()));
        holder.mDelete.setImageResource(R.drawable.ic_baseline_delete_forever_24);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loaiSach items = new loaiSach();
                items.setMaLoai(list.get(holder.getAdapterPosition()).getMaLoai());
                items.setTenLoai(list.get(holder.getAdapterPosition()).getTenLoai());
                mContext.updateLoaiSach(items);
            }
        });
        holder.mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.deleteLoaiSach(list.get(holder.getAdapterPosition()).getMaLoai());
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class customItem extends RecyclerView.ViewHolder{
        public TextView mMaLoai, mTenLoai;
        public ImageView mDelete;
        public customItem(@NonNull View itemView) {
            super(itemView);
            mMaLoai = itemView.findViewById(R.id.custom_item_loaiSach_maLoai);
            mTenLoai = itemView.findViewById(R.id.custom_item_loaiSach_tenLoai);
            mDelete = itemView.findViewById(R.id.custom_item_loaiSach_delete);
        }
    }
}
