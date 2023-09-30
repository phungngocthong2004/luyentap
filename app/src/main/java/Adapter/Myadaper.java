package Adapter;

import android.bluetooth.le.ScanSettings;


import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import Fragment.Fragmentdanhsach;
import Fragment.Fragmnetthongtin;

public class Myadaper extends FragmentStateAdapter {
    public Myadaper(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position){
            case 0:
                return new Fragmentdanhsach();

            case 1:
                return new Fragmnetthongtin();

            default:
                return new Fragmentdanhsach();
        }


    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
