package kr.edcan.hospital.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;
import kr.edcan.hospital.R;
import kr.edcan.hospital.data.Hospital;
import kr.edcan.hospital.data.HospitalSearchData;
import kr.edcan.hospital.utils.HospitalSearchAdapter;
import kr.edcan.hospital.utils.NetworkService;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by kotohana5706 on 2015. 8. 30..
 */
public class Tab_Search extends Fragment implements View.OnClickListener {
    MaterialSpinner spinner;
    ImageView search;
    public static String ENDPOINT = "http://bamtoll.moe";
    RestAdapter restAdapter;
    NetworkService service;
    ListView listView;
    View v, mainView;
    EditText edit;
    LinearLayout layout;
    ArrayList<HospitalSearchData> arrayList;
    HospitalSearchAdapter searchAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_search, container, false);
        this.v = v;
        setRest();
        setDefault();
        searchAll();
//        listEvery();
        return v;
    }

    private void searchHospital(String s) {
//        arrayList = new ArrayList<>();
//        service.searchHospital(s, new Callback<List<Hospital>>() {
//            @Override
//            public void success(List<Hospital> hospitals, Response response) {
//                for (Hospital hospital : hospitals) {
//                    arrayList.add(new HospitalSearchData(hospital.name, hospital.address, hospital.tel, hospital.location));
//                }
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        searchAdapter = new HospitalSearchAdapter(getContext(), arrayList);
//        listView.setAdapter(searchAdapter);
        Toast.makeText(getContext(), "검색 기능은 준비중입니다!ㅠㅠ", Toast.LENGTH_SHORT).show();
    }

    public void searchAll(){
        arrayList = new ArrayList<>();
        service.allHospital(new Callback<List<Hospital>>() {
            @Override
            public void success(List<Hospital> hospitals, Response response) {
                arrayList.add(new HospitalSearchData("한성동물병원", "서울시 관악로 신림로 265", "02-872-7609", 1));
                for (Hospital hospital : hospitals) {
                    arrayList.add(new HospitalSearchData(
                            hospital.name, hospital.address, hospital.tel, hospital.location
                    ));
                    searchAdapter = new HospitalSearchAdapter(getContext(), arrayList);
                    listView.setAdapter(searchAdapter);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void setRest() {
        restAdapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .build();
        service = restAdapter.create(NetworkService.class);
    }

    public void setDefault() {
        layout = (LinearLayout) getActivity().findViewById(R.id.asdf);
        String[] items = {"모든 지역", "서울/경기", "강원도", "경상도", "충청도", "전라도"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_textstyle, items);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_textstyle);
        spinner = (MaterialSpinner) v.findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectCategorySort(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        search = (ImageView) layout.findViewById(R.id.main_search);
        edit = (EditText) layout.findViewById(R.id.edittext_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchHospital(edit.getText().toString());
            }
        });
        listView = (ListView) v.findViewById(R.id.listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getContext(), HospitalInfoViewActivity.class));
            }
        });
    }

    private void selectCategorySort(final int position) {
        if(position==0) searchAll();
        else {
            arrayList = new ArrayList<>();
            service.allHospital(new Callback<List<Hospital>>() {
                @Override
                public void success(List<Hospital> hospitals, Response response) {
                    for (Hospital hospital : hospitals) {
                        if (hospital.location == position) {
                            arrayList.add(new HospitalSearchData(
                                    hospital.name, hospital.address, hospital.tel, hospital.location
                            ));
                            searchAdapter = new HospitalSearchAdapter(getContext(), arrayList);
                            listView.setAdapter(searchAdapter);
                        }
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
    }
}
