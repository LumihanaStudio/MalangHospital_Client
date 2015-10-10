package kr.edcan.hospital.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.balysv.materialripple.MaterialRippleLayout;

import kr.edcan.hospital.R;


/**
 * Created by kotohana5706 on 2015. 8. 30..
 */
public class View_Tab_Info extends Fragment implements View.OnClickListener {

    MaterialRippleLayout mp;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.view_info, container, false);
        mp = (MaterialRippleLayout)v.findViewById(R.id.view_add_to_my);
        return v;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }
}
