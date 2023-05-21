package nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Model;

public class thuThu {
    private String maTt;
    private String hoTen,matKhau;

    public thuThu() {
    }

    public thuThu(String maTt, String hoTen, String matKhau) {
        this.maTt = maTt;
        this.hoTen = hoTen;
        this.matKhau = matKhau;
    }

    public String getMaTt() {
        return maTt;
    }

    public void setMaTt(String maTt) {
        this.maTt = maTt;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}
