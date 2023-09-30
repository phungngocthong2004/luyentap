package com.example.luyentapthi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import Adapter.Myadaper;

public class colletionfreamnet extends Fragment {
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    Myadaper myadaper;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.coolection,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myadaper=new Myadaper(this);
        viewPager2=view.findViewById(R.id.viewpegaer2);
         viewPager2.setAdapter(myadaper);

         tabLayout=view.findViewById(R.id.tablayout);
        TabLayoutMediator mediator=new TabLayoutMediator(tabLayout, viewPager2, true, true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
              switch (position){
                  case 0:
                      tab.setText("danh sach");
                 case 1:
                      tab.setText("thong tin");

              }
            }
        });
        mediator.attach();
    }
}
