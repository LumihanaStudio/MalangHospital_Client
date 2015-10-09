package kr.edcan.hospital.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import fr.ganfra.materialspinner.MaterialSpinner;
import kr.edcan.hospital.R;


/**
 * Created by kotohana5706 on 2015. 8. 30..
 */
    public class Tab_Search extends Fragment {
    MaterialSpinner spinner;
    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.main_search,container,false);
        this.v = v;
        return v;
    }
    public void setDefault(){
        String[] items = {"서울/경기", "강원도", "경상도", "충청도", "전라도"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner = (MaterialSpinner)v.findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
    }
}
