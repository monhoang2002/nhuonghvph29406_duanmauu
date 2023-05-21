package nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Model;

public class sach {
    private String maSach,maLoai;
    private String tenSach;
    private Float giaThue;

    public sach(String maSach, String maLoai, String tenSach, Float giaThue) {
        this.maSach = maSach;
        this.maLoai = maLoai;
        this.tenSach = tenSach;
        this.giaThue = giaThue;
    }

    public sach() {
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public Float getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(Float giaThue) {
        this.giaThue = giaThue;
    }
}
