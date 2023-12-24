package model;

import java.io.Serializable;

public class LoaiHangHoa implements Serializable {
    private String maloai,tenloai;

    public LoaiHangHoa() {
    }

    public LoaiHangHoa(String tenloai) {
        this.tenloai = tenloai;
    }

    public LoaiHangHoa(String maloai, String tenloai) {
        this.maloai = maloai;
        this.tenloai = tenloai;
    }

    public String getMaloai() {
        return maloai;
    }

    public void setMaloai(String maloai) {
        this.maloai = maloai;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }
}
