package DTO;

public class danhSach_DTO {
    private int id;
    private String ngayThi;
    private  int ca;
    private String phongTHi;
    private String tenMon;

    public danhSach_DTO(int id, String ngayThi, int ca, String phongTHi, String tenMon) {
        this.id = id;
        this.ngayThi = ngayThi;
        this.ca = ca;
        this.phongTHi = phongTHi;
        this.tenMon = tenMon;
    }

    public danhSach_DTO() {
    }

    public danhSach_DTO(String ngayThi, int ca, String phongTHi, String tenMon) {
        this.ngayThi = ngayThi;
        this.ca = ca;
        this.phongTHi = phongTHi;
        this.tenMon = tenMon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNgayThi() {
        return ngayThi;
    }

    public void setNgayThi(String ngayThi) {
        this.ngayThi = ngayThi;
    }

    public int getCa() {
        return ca;
    }

    public void setCa(int ca) {
        this.ca = ca;
    }

    public String getPhongTHi() {
        return phongTHi;
    }

    public void setPhongTHi(String phongTHi) {
        this.phongTHi = phongTHi;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }
}
