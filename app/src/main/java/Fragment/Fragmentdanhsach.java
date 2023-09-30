package Fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.luyentapthi.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import Adapter.DanhSachAdapter;
import DAO.DanhSach_DAO;
import DTO.danhSach_DTO;

public class Fragmentdanhsach extends Fragment {
    Button btnthem;
    RecyclerView rc_ds;
    ArrayList<danhSach_DTO>list;
    DanhSach_DAO danhSach_dao;
    DanhSachAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmentdanhscah,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnthem=view.findViewById(R.id.btnThem);
        rc_ds=view.findViewById(R.id.rc_ds);

        danhSach_dao=new DanhSach_DAO(getContext());
        list=danhSach_dao.getAll();
        adapter=new DanhSachAdapter(getContext(),list);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rc_ds.setLayoutManager(linearLayoutManager);
        rc_ds.setAdapter(adapter);

        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                LayoutInflater inflater=getLayoutInflater();
                View v=inflater.inflate(R.layout.itemthem,null);
                builder.setView(v);

                Dialog dialog= builder.create();
                dialog.show();

                EditText editngaythi=v.findViewById(R.id.edngaythi);
                EditText editca=v.findViewById(R.id.edca);
                EditText ediphongthi=v.findViewById(R.id.edphomngthi);
                EditText edittenmon=v.findViewById(R.id.edtenmon);
                Button btnthem=v.findViewById(R.id.themm);

                btnthem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                        builder.setTitle("them");
                        builder.setMessage("ban muon them khong");
                        builder.setPositiveButton("co", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String ngaythi=editngaythi.getText().toString();
                                 int ca= Integer.parseInt(editca.getText().toString());
                                String  phongthi=ediphongthi.getText().toString();
                                String tenmon=edittenmon.getText().toString();

                                if (ca<0|ca>6){
                                    Toast.makeText(getContext(), "ca tu 1->6", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                if (phongthi.length()>3){
                                    Toast.makeText(getContext(), "phong thi chi co 3 kys tu", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                DanhSach_DAO dao=new DanhSach_DAO(getContext());
                                danhSach_DTO danhSach_dto=new danhSach_DTO(ngaythi,ca,phongthi,tenmon);
                                long id=dao.add(danhSach_dto);
                                if (id>0){
                                    Toast.makeText(getContext(), "tthem tahnh cong", Toast.LENGTH_SHORT).show();
                                    list.clear();
                                    list.addAll(danhSach_dao.getAll());
                                    adapter.notifyDataSetChanged();
                                    dialog.dismiss();
                                }
                            }
                        });
                        builder.setNegativeButton("khong",null);
                        builder.show();

                    }
                });

            }
        });
    }
}
