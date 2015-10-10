package kr.edcan.hospital.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import kr.edcan.hospital.R;

public class AnimalAddEditActivity extends AppCompatActivity {

    Intent intent;
    boolean isFirst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_add_edit);
        checkIsFirst();
        setActionbar();
    }

    private void checkIsFirst() {
        intent = getIntent();
        isFirst = intent.getBooleanExtra("isFirst",false);
    }

    private void setActionbar() {
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setTitle((isFirst)?"작성하기":"수정하기");
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
