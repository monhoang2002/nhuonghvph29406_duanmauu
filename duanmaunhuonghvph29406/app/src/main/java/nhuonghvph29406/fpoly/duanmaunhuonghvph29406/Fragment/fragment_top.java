package nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.DB_Dao.phieuMuonDao;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.DB_Dao.sachDao;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Model.sapXep;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.R;

public class fragment_top extends Fragment {
    private View mView;
    ListView  listView;
    List<sapXep> list;
    List<String> listShow;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_top, container, false);
        listView = mView.findViewById(R.id.fragment_top_recycleView);
        phieuMuonDao dao = new phieuMuonDao(getContext());
        list = dao.getByAsc();
        listShow = new ArrayList<>();
        int dem = 1;
        sachDao dao1 = new sachDao(getContext());
        for (sapXep x: list){
            String items ="Top "+ dem +"       Mã sách:  "+ x.getMaSach() +"  Số lượng:  "+x.getSoLuong();
            listShow.add(items);
            dem++;
        }

        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, listShow);
        listView.setAdapter(adapter);
        return mView;
    }
}