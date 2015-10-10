package kr.edcan.hospital.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import kr.edcan.hospital.R;
import kr.edcan.hospital.data.Session;
import kr.edcan.hospital.utils.NetworkService;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SplashActivity extends AppCompatActivity {

    public static String ENDPOINT = "http://bamtoll.moe";
    EditText user_login_id, user_login_pwd, user_login_re_pwd;
    FrameLayout splash_login;
    RestAdapter restAdapter;
    NetworkService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setDefault();
        splash_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service.userLogin(user_login_id.getText().toString(), user_login_pwd.getText().toString(), new Callback<Session>() {
                    @Override
                    public void success(Session session, Response response) {
                        startActivity(new Intent(getApplicationContext(), TutorialActivity.class).putExtra("isExtra", true));
                        finish();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(SplashActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    private void setDefault() {
        restAdapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .build();
        service = restAdapter.create(NetworkService.class);
        user_login_id = (EditText) findViewById(R.id.user_login_id);
        user_login_pwd = (EditText) findViewById(R.id.user_login_pw);
        splash_login = (FrameLayout) findViewById(R.id.splash_login);
    }
}
