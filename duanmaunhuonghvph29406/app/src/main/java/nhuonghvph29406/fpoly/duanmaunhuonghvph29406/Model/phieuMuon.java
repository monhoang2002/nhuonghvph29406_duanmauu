package nhuonghvph29406.fpoly.duanmaunhuonghvph29406.Model;

public class phieuMuon {
    private String maPm,maTv,maTt,masach;
    private float tienThue;
    private String ngayThue,trangthai;

    public phieuMuon(String maPm, String maTv, String maTt, String masach, float tienThue, String ngayThue, String trangthai) {
        this.maPm = maPm;
        this.maTv = maTv;
        this.maTt = maTt;
        this.masach = masach;
        this.tienThue = tienThue;
        this.ngayThue = ngayThue;
        this.trangthai = trangthai;

    }

    public phieuMuon() {
    }

    public String getMaPm() {
        return maPm;
    }

    public void setMaPm(String maPm) {
        this.maPm = maPm;
    }

    public String getMaTv() {
        return maTv;
    }

    public void setMaTv(String maTv) {
        this.maTv = maTv;
    }

    public String getMaTt() {
        return maTt;
    }

    public void setMaTt(String maTt) {
        this.maTt = maTt;
    }

    public String getMasach() {
        return masach;
    }

    public void setMasach(String masach) {
        this.masach = masach;
    }

    public float getTienThue() {
        return tienThue;
    }

    public void setTienThue(float tienThue) {
        this.tienThue = tienThue;
    }

    public String getNgayThue() {
        return ngayThue;
    }

    public void setNgayThue(String ngayThue) {
        this.ngayThue = ngayThue;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }
}
