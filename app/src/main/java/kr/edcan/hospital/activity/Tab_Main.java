package kr.edcan.hospital.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.getbase.floatingactionbutton.FloatingActionButton;

import kr.edcan.hospital.R;
import kr.edcan.hospital.data.Advice;
import kr.edcan.hospital.utils.NetworkService;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by kotohana5706 on 2015. 8. 30..
 */
public class Tab_Main extends Fragment implements View.OnClickListener {
    MaterialDialog progress;
    String result;
    RestAdapter restAdapter;
    public static String ENDPOINT = "http://bamtoll.moe";
    NetworkService service;
    String daily_advice;
    TextView header_title, header_description;
    TextView body_medicine, body_medicine_time, body_tip, body_hospital, body_hospital_time;
    View v;
    FloatingActionButton fab;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_main, container, false);
        this.v = v;
        setDefault();
        setRest();
        loadData();
        return v;
    }

    public void loadData() {
        progress = new MaterialDialog.Builder(getContext())
                .progress(true, 0)
                .title("데이터를 로딩중입니다")
                .content("잠시만 기다려주세요")
                .show();
        // TODO: Load Data from Server
        service.getAdvice(new Callback<Advice>() {
            @Override
            public void success(Advice advice, Response response) {
                daily_advice = advice.content;
                body_tip.setText(daily_advice);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getContext(), error.getResponse().getStatus() + "", Toast.LENGTH_SHORT).show();
            }
        });
        progress.dismiss();
    }
    public void setRest() {
        restAdapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .build();
        service = restAdapter.create(NetworkService.class);
    }
    private void setDefault() {
        fab = (FloatingActionButton) v.findViewById(R.id.main_menu);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getContext(), AnimalAddEditActivity.class).putExtra("isFirst", false));
                startActivity(new Intent(getContext(), SetMedicineActivity.class).putExtra("isFirst", false));
            }
        });
        header_title = (TextView) v.findViewById(R.id.main_header_name);
        header_description = (TextView) v.findViewById(R.id.main_header_description);
        body_medicine = (TextView) v.findViewById(R.id.main_body_medicine);
        body_hospital_time = (TextView) v.findViewById(R.id.main_body_hospital_left);
        body_hospital = (TextView) v.findViewById(R.id.main_body_hospital);
        body_medicine_time = (TextView) v.findViewById(R.id.main_body_medicine_time);
        body_tip = (TextView) v.findViewById(R.id.main_body_tip);
        calculateTime();
    }

    public void calculateTime() {
//        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("asdf", 0);
//        int hour = sharedPreferences.getInt("hour", -1);
//        int minute = sharedPreferences.getInt("minute", -1);
//        Calendar calendar = new GregorianCalendar();
//        int nowhour = calendar.get(Calendar.HOUR_OF_DAY);
//        int nowminute = calendar.get(Calendar.MINUTE);
//        int resulthour, resultmin;
//        resulthour = hour - nowhour;
//        resultmin = minute - nowminute;
//        if (hour == -1 || minute == -1) result = "설정되지 않음";
//        else {
//            if (resulthour < 0) {
//                resulthour = resulthour * -1;
//                result = resulthour + "시간 " + resultmin + "분 남음";
//            }
//
//            if (resultmin < 0) {
//                resultmin = 60 - (resultmin * -1);
//                result = resulthour + "시간 " + resultmin + "분 남음";
//            }
//        }
//        body_medicine_time.setText(result);
        body_medicine_time.setText("심장사상충약");
        body_medicine.setText("2시간 15분 남음");
    }

    @Override
    public void onClick(View v) {

    }
}
