package nhuonghvph29406.fpoly.duanmaunhuonghvph29406.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Fragment.fragment_thanh_vien;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Model.thanhVien;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.R;

public class recycleView_adapter_thanhVien extends RecyclerView.Adapter<recycleView_adapter_thanhVien.customItem> {
    private fragment_thanh_vien mContext;
    private List<thanhVien> list;

    public recycleView_adapter_thanhVien(fragment_thanh_vien mContext, List<thanhVien> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public customItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_one_item_thanhvien,parent, false);
        customItem mCustomItem = new customItem(view);
        return mCustomItem;
    }

    @Override
    public void onBindViewHolder(@NonNull customItem holder, int position) {
        holder.mMatv.setText("Mã thành viên: "+String.valueOf(list.get(holder.getAdapterPosition()).getMaTv()));
        holder.mHoTen.setText("Họ tên: "+String.valueOf(list.get(holder.getAdapterPosition()).getHoTen()));
        holder.mNgaySinh.setText("Ngày sinh: "+list.get(holder.getAdapterPosition()).getNamSinh());
        holder.mDelete.setImageResource(R.drawable.ic_baseline_delete_forever_24);
        thanhVien items = new thanhVien();
        items.setMaTv(list.get(holder.getAdapterPosition()).getMaTv());
        items.setHoTen(list.get(holder.getAdapterPosition()).getHoTen());
        items.setNamSinh(list.get(holder.getAdapterPosition()).getNamSinh());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.capNhapThanhVien(items);
            }
        });
        holder.mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.xoaThanhVien(list.get(holder.getAdapterPosition()).getMaTv());
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    final class customItem extends RecyclerView.ViewHolder {
        public TextView mMatv, mHoTen, mNgaySinh;
        public ImageView mDelete;
        public customItem(@NonNull View itemView) {
            super(itemView);
            mMatv = itemView.findViewById(R.id.custom_item_thanhVien_matv);
            mHoTen = itemView.findViewById(R.id.custom_item_thanhVien_hoTen);
            mNgaySinh = itemView.findViewById(R.id.custom_item_thanhVien_ngaySinh);
            mDelete = itemView.findViewById(R.id.custom_item_thanhVien_delete);
        }
    }
}
