package kr.edcan.hospital.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.GregorianCalendar;

import kr.edcan.hospital.R;

public class SetMedicineActivity extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton fab;
    EditText title;
    LinearLayout time, date, daily_time;
    TextView timeText, dateText, dailyTimeText;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    int year, month, day, hour, minute, second = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_medicine);
        sharedPreferences = getSharedPreferences("asdf", 0);
        editor = sharedPreferences.edit();
        setActionbar();
        setDefault();
    }

    private void setDefault() {
        fab = (FloatingActionButton) findViewById(R.id.confirm_medicine);
        fab.setOnClickListener(this);
        title = (EditText) findViewById(R.id.setmedi_title);
        time = (LinearLayout) findViewById(R.id.setmedi_time_touch);
        date = (LinearLayout) findViewById(R.id.setmedi_date_touch);
        daily_time = (LinearLayout) findViewById(R.id.setmedi_daily_time_touch);
        timeText=  (TextView) findViewById(R.id.setmedi_time);
        dateText = (TextView) findViewById(R.id.setmedi_date);
        dailyTimeText = (TextView) findViewById(R.id.setmedi_daily_time);
        time.setOnClickListener(this);
        date.setOnClickListener(this);
        daily_time.setOnClickListener(this);
        GregorianCalendar calendar = new GregorianCalendar();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
    }

    private void setActionbar() {
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setTitle("약 먹을 시간 설정");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.setmedi_time_touch:
                DatePickerDialog datePickerDialog = new DatePickerDialog(SetMedicineActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year1, int monthOfYear, int dayOfMonth) {
                        year = year1;
                        month = monthOfYear;
                        day = dayOfMonth;
                    }
                }, year, month, day);
                datePickerDialog.show();
                timeText.setText(year+"년 "+(month+1)+"월 "+day+"일 부터");
                break;
            case R.id.setmedi_date_touch:
                MaterialDialog gigan = new MaterialDialog.Builder(SetMedicineActivity.this)
                        .title("베타 버전은 3일로 제한됩니다")
                        .content("정식 버전에서 업데이트됩니다")
                        .positiveText("확인")
                        .show();
                dateText.setText("앞으로 3일간");
                break;
            case R.id.setmedi_daily_time_touch:
                TimePickerDialog tpdia = new TimePickerDialog(SetMedicineActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute1) {
                        hour = hourOfDay;
                        minute = minute1;
                        dailyTimeText.setText(hour+":"+minute);
                    }
                }, hour,minute,true);
                tpdia.show();
                break;
            case R.id.confirm_medicine:
                editor.putInt("year", year);
                editor.putInt("month", month);
                editor.putInt("day", day);
                editor.putInt("hour", hour);
                editor.putInt("minute", minute);
                editor.commit();
                Toast.makeText(SetMedicineActivity.this, "저장되었습니다!", Toast.LENGTH_SHORT).show();
                finish();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
