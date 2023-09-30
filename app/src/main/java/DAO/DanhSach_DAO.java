package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import DTO.danhSach_DTO;
import Dbhepper.MyHepplper;

public class DanhSach_DAO {
    MyHepplper myHepplperl;
    SQLiteDatabase db;

    public DanhSach_DAO(Context context){
        myHepplperl=new MyHepplper(context);
        db=myHepplperl.getWritableDatabase();

    }

    public  long add(danhSach_DTO danhSach_dto){
        ContentValues values=new ContentValues();
        values.put("ngaythi",danhSach_dto.getNgayThi());
        values.put("ca",danhSach_dto.getCa());
        values.put("phongthi",danhSach_dto.getPhongTHi());
        values.put("tenmon",danhSach_dto.getTenMon());
        return db.insert("tb_danhsachthi",null,values);

    }
    public  int update(danhSach_DTO danhSach_dto){
        ContentValues values=new ContentValues();
        values.put("ngaythi",danhSach_dto.getNgayThi());
        values.put("ca",danhSach_dto.getCa());
        values.put("phongthi",danhSach_dto.getPhongTHi());
        values.put("tenmon",danhSach_dto.getTenMon());
        String[] dk=new String[]{String.valueOf(danhSach_dto.getId())};
        return db.update("tb_danhsachthi",null,"id=?",dk);

    }
    public  int xoa(danhSach_DTO danhSach_dto){
        String[] dk=new String[]{String.valueOf(danhSach_dto.getId())};
        return  db.delete("tb_danhsachthi","id=?",dk);
    }
    public ArrayList<danhSach_DTO> getAll(){
        ArrayList<danhSach_DTO>list=new ArrayList<>();
        Cursor c= db.rawQuery("select * from tb_danhsachthi ",null);
        if (c!=null&& c.getCount()>0){
            c.moveToFirst();
            do {
                list.add(new danhSach_DTO(c.getInt(0),c.getString(1), c.getInt(2), c.getString(3), c.getString(4)));
            }while (c.moveToNext());
        }
        return list;
    }
}
