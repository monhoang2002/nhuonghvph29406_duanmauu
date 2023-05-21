package nhuonghvph29406.fpoly.duanmaunhuonghvph29406;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;


import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.DB_Dao.thuThuDao;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Fragment.fragment_doanh_thu;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Fragment.fragment_loai_sach;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Fragment.fragment_phieu_muon;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Fragment.fragment_sach;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Fragment.fragment_thanh_vien;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Fragment.fragment_thayDoiMk;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Fragment.fragment_top;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Fragment.frangment_add_thuthu;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Model.thuThu;
import nhuonghvph29406.fpoly.duanmaunhuonghvph29406.SQL_Dao.DB_Helper;

public class ManHinhChinh extends AppCompatActivity implements View.OnClickListener {
    private DrawerLayout mDrawerLayout;
    private ImageView mImageView;
    private TextView mTitle;
    private NavigationView mNavigationView;
    private Toolbar mToolbar;
    private FragmentManager fragmentManager;
    public FragmentTransaction fragmentTransaction;
    Intent intent;
    Bundle bundle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.man_hinh_chinh);

        //        DB_Helper dbHelper = new DB_Helper(getApplicationContext());
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        dbHelper.close();

        //
        mDrawerLayout = findViewById(R.id.main_drawerLayout);
        mImageView = findViewById(R.id.main_menu);
        mTitle = findViewById(R.id.main_title);
        mTitle.setText("Quản lý phiếu mượn");
        mImageView.setOnClickListener(this);
        setSupportActionBar(mToolbar);
         intent = getIntent();
         bundle = intent.getExtras();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container,new fragment_phieu_muon());
        fragmentTransaction.addToBackStack(fragment_phieu_muon.class.getName());
        fragmentTransaction.commit();
        mNavigationView = findViewById(R.id.main_Navigation);
        clickNavigationItem();
        String ten = bundle.getString("TK");
        thuThuDao dao = new thuThuDao(this);
        thuThu itemTT = dao.getById(ten);
        TextView textView = mNavigationView.getHeaderView(0).findViewById(R.id.name);
        if (itemTT == null){
            textView.setText("Welcome");
        }
        else{
            textView.setText("Welcome: "+itemTT.getHoTen());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.main_menu:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.main_Navigation:
                break;
        }
    }
    public void clickNavigationItem() {
        mNavigationView = findViewById(R.id.main_Navigation);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_quanLiPhieuMuon:
                        mTitle.setText("Quản lý phiếu mượn");
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.container,new fragment_phieu_muon());
                        fragmentTransaction.addToBackStack(fragment_phieu_muon.class.getName());
                        fragmentTransaction.commit();
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_quanLiLoaiSach:
                        mTitle.setText("Quản lý loại sách");
                        mDrawerLayout.closeDrawers();
                        FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
                        fragmentTransaction1.replace(R.id.container,new fragment_loai_sach());
                        fragmentTransaction1.addToBackStack(fragment_loai_sach.class.getName());
                        fragmentTransaction1.commit();
                        break;
                    case R.id.nav_quanLiSach:
                        mTitle.setText("Quản lý sách");
                        FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
                        fragmentTransaction2.replace(R.id.container,new fragment_sach());
                        fragmentTransaction2.addToBackStack(fragment_sach.class.getName());
                        fragmentTransaction2.commit();
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_quanLiThanhVien:
                        mTitle.setText("Quản lý thành viên");
                        FragmentTransaction fragmentTransaction3 = fragmentManager.beginTransaction();
                        fragmentTransaction3.replace(R.id.container,new fragment_thanh_vien());
                        fragmentTransaction3.addToBackStack(fragment_thanh_vien.class.getName());
                        fragmentTransaction3.commit();
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_10SachMuonNhieuNhat:
                        mTitle.setText("Top 10 sách mượn");
                        FragmentTransaction fragmentTransaction4 = fragmentManager.beginTransaction();
                        fragmentTransaction4.replace(R.id.container,new fragment_top());
                        fragmentTransaction4.addToBackStack(fragment_top.class.getName());
                        fragmentTransaction4.commit();
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_doanhThu:
                        mTitle.setText("Doanh thu");
                        FragmentTransaction fragmentTransaction5 = fragmentManager.beginTransaction();
                        fragmentTransaction5.replace(R.id.container,new fragment_doanh_thu());
                        fragmentTransaction5.addToBackStack(fragment_doanh_thu.class.getName());
                        fragmentTransaction5.commit();
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_doiMatKhau:
                        mTitle.setText("Thay đổi mật khẩu");
                        fragment_thayDoiMk fragment_thayDoiMk = new fragment_thayDoiMk();
                        Bundle bundle1 = new Bundle();
                        bundle1.putString("maTT",bundle.getString("TK"));
                        fragment_thayDoiMk.setArguments(bundle1);
                        FragmentTransaction fragmentTransaction6 = fragmentManager.beginTransaction();
                        fragmentTransaction6.replace(R.id.container,fragment_thayDoiMk);
                        fragmentTransaction6.addToBackStack(fragment_thayDoiMk.class.getName());
                        fragmentTransaction6.commit();
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_dangXuat:
                        Intent mIntent = new Intent(ManHinhChinh.this, activity_login.class);
                        startActivity(mIntent);
                        finish();
                        Toast.makeText(ManHinhChinh.this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_dangKy:
                        String ten = bundle.getString("TK");
                        String mk = bundle.getString("MK");
                        if (ten.equals("nhuong")){
                            mTitle.setText("Thêm tài khoản");
                            FragmentTransaction fragmentTransaction7 = fragmentManager.beginTransaction();
                            fragmentTransaction7.replace(R.id.container,new frangment_add_thuthu());
                            fragmentTransaction7.addToBackStack(frangment_add_thuthu.class.getName());
                            fragmentTransaction7.commit();
                            mDrawerLayout.closeDrawers();

                        }
                        else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(ManHinhChinh.this);
                            builder.setTitle("Chỉ có Admin mới có thể thêm tài khoản");
                            builder.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                            builder.show();
                        }
                        break;
                    default:
                        break;

                }
                return true;
            }
        });
    }
}
