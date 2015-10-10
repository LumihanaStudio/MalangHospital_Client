package kr.edcan.hospital.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;

import kr.edcan.hospital.R;
import kr.edcan.hospital.data.Review;
import kr.edcan.hospital.utils.HospitalReviewAdapter;


/**
 * Created by kotohana5706 on 2015. 8. 30..
 */
public class View_Tab_Review extends Fragment implements View.OnClickListener {

    HospitalReviewAdapter adapter;
    ArrayList<Review> arrayList;

    ListView listview;
    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.view_review, container, false);
        this.v=  v;
        setDefault();
        return v;
    }

    private void setDefault() {
        listview = (ListView) this.v.findViewById(R.id.listview_review);
        arrayList = new ArrayList<>();
        arrayList.add(new Review("의사선생님이 친절하셔서 너무 좋았어요!", "명지*"));
        arrayList.add(new Review("원장선생님이 정말 친절하세요!", "김영*"));
        arrayList.add(new Review("시설이 깨끗하고 청결해서 정말 좋았어요!", "임세*"));
        arrayList.add(new Review("개발자가 암것도 안해요 ㅠㅠ!", "말랑*"));
        adapter = new HospitalReviewAdapter(getContext(), arrayList);
        listview.setAdapter(adapter);
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                new MaterialDialog.Builder(getContext())
                        .title("신고하시겠습니까?")
                        .content("허위 후기로 신고합니다.\n거짓 신고 시 이용이 제한될 수 있습니다.")
                        .positiveText("신고")
                        .negativeText("취소")
                        .callback(new MaterialDialog.ButtonCallback() {
                            @Override
                            public void onPositive(MaterialDialog dialog) {
                                Toast.makeText(getContext(), "신고가 접수되었습니다.",Toast.LENGTH_SHORT).show();
                                super.onPositive(dialog);
                            }
                        })
                .show();
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.add_achieve:
//                startActivity(new Intent(getContext(), AddAchievement.class));
//                break;
        }
    }
}
