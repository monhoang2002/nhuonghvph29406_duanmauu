package nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Model;

public class thanhVien {
    private String maTv;
    private String hoTen, namSinh;

    public thanhVien() {
    }

    public thanhVien(String maTv, String hoTen, String namSinh) {
        this.maTv = maTv;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
    }

    public String getMaTv() {
        return maTv;
    }

    public void setMaTv(String maTv) {
        this.maTv = maTv;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }
}
