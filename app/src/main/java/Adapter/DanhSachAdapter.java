package Adapter;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.luyentapthi.Notyfi;
import com.example.luyentapthi.R;

import java.util.ArrayList;
import java.util.Date;

import DAO.DanhSach_DAO;
import DTO.danhSach_DTO;

public class DanhSachAdapter extends RecyclerView.Adapter<DanhSachAdapter.viewholser> {
    Context context;
    ArrayList<danhSach_DTO>list;

    DanhSach_DAO danhSach_dao;


    public DanhSachAdapter(Context context, ArrayList<danhSach_DTO> list) {
        this.context = context;
        this.list = list;
        danhSach_dao=new DanhSach_DAO(context);
    }


    @NonNull
    @Override
    public viewholser onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=inflater.inflate(R.layout.dongdanhsach,parent,false);

        return new viewholser(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholser holder, @SuppressLint("RecyclerView") int position) {

        holder.tvtenmon.setText(list.get(position).getTenMon());
        holder.tvca.setText(list.get(position).getCa()+"");
        holder.tvphongthi.setText(list.get(position).getPhongTHi());
        holder.tvngaythi.setText(list.get(position).getNgayThi());
        holder.btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("xoa");
                builder.setMessage("ban muon xoa khong");
                builder.setPositiveButton("co", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int id=danhSach_dao.xoa(list.get(position));
                        if (id>0){
                            list.remove(position);
                            Toast.makeText(context, "xoa thah cong", Toast.LENGTH_SHORT).show();
                            notifyItemChanged(holder.getAdapterPosition());
                            Notification customNotification = new NotificationCompat.Builder(context, Notyfi.CHANEL_ID)
                                    .setSmallIcon(android.R.drawable.ic_delete)
                                    .setContentTitle("xóa")
                                    .setContentText("Bạn Đã xóa: ")
                                    .setColor(Color.BLUE)
                                    .setAutoCancel(true)
                                    .build();

// Khởi tạo Manager để quản lý notify
                            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);

// Cần kiểm tra quyền trước khi hiển thị notify
                            if (ActivityCompat.checkSelfPermission(context,
                                    android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {

                                // Gọi hộp thoại hiển thị xin quyền người dùng
                                ActivityCompat.requestPermissions((Activity) context,
                                        new String[]{Manifest.permission.POST_NOTIFICATIONS}, 999999);
                                return; // thoát khỏi hàm nếu chưa được cấp quyền
                            }
// nếu đã cấp quyền rồi thì sẽ vượt qua lệnh if trên và đến đây thì hiển thị notify
// mỗi khi hiển thị thông báo cần tạo 1 cái ID cho thông báo riêng
                            int id_notiy = (int) new Date().getTime();// lấy chuỗi time là phù hợp
//lệnh hiển thị notify
                            notificationManagerCompat.notify(id_notiy , customNotification);
                        }
                    }
                });
                builder.setNegativeButton("xoa",null);
                builder.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class viewholser extends RecyclerView.ViewHolder {

        TextView tvngaythi,tvca,tvphongthi,tvtenmon;
        Button btnsua,btnxoa;
        public viewholser(@NonNull View itemView) {
            super(itemView);
            tvca=itemView.findViewById(R.id.ca);
            tvngaythi=itemView.findViewById(R.id.ngaythi);
            tvphongthi=itemView.findViewById(R.id.phongthi);
            tvtenmon=itemView.findViewById(R.id.tenmon);
             btnsua=itemView.findViewById(R.id.btnsua);
             btnxoa=itemView.findViewById(R.id.btnxoa);
        }
    }
}
