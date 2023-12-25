package model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class HangHoa implements Serializable {
    private String maHang;
    private String tenHang;
    private String Tenloai;
    private String hinhanh;
    private   double gia,size;

    public HangHoa() {
    }

    public HangHoa(String tenHang, String tenloai, String hinhanh, double gia, double size) {
        this.tenHang = tenHang;
        this.Tenloai = tenloai;
        this.hinhanh = hinhanh;
        this.gia = gia;
        this.size = size;

    }

    public HangHoa(String maHang, String tenHang, String tenloai, String hinhanh, double gia, double size) {
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.Tenloai = tenloai;
        this.hinhanh = hinhanh;
        this.gia = gia;
        this.size = size;
    }

    public String getMaHang() {
        return maHang;
    }

    public void setMaHang(String maHang) {
        this.maHang = maHang;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public String getTenloai() {
        return Tenloai;
    }

//    public void setTenloai(String tenloai) {
//        Tenloai = tenloai;
//    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }


    public void setLoaiHangHoa(String tenLoai) {
        this.Tenloai = tenLoai;
    }
}
