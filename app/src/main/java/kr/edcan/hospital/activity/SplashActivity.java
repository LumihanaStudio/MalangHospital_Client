package kr.edcan.hospital.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import kr.edcan.hospital.utils.NetworkService;
import kr.edcan.hospital.R;
import kr.edcan.hospital.data.Session;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SplashActivity extends AppCompatActivity {

    public static String ENDPOINT = "http://bamtoll.moe";
    EditText user_login_id, user_login_pwd, user_login_re_pwd;
    Button user_login;
    TextView current_state;
    EditText user_register_id, user_register_pwd, user_register_re_pwd;
    Button user_register;
    TextView register_state;
    RestAdapter restAdapter;
    NetworkService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setDefault();
        user_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service.userLogin(user_login_id.getText().toString(), user_login_pwd.getText().toString(), new Callback<Session>() {
                    @Override
                    public void success(Session session, Response response) {
                        current_state.setText(session.log);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        current_state.setText(error.getResponse().getStatus()+"");
                    }
                });
            }
        });
        user_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service.signUp(user_register_id.getText().toString(), user_register_pwd.getText().toString(), user_register_re_pwd.getText().toString(), new Callback<Session>() {
                    @Override
                    public void success(Session session, Response response) {
                        register_state.setText(session.log);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        register_state.setText(error.getResponse().getStatus()+"");
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
        user_login_re_pwd = (EditText) findViewById(R.id.user_login_reinput_pw);
        user_register_id = (EditText) findViewById(R.id.user_register_id);
        user_register_pwd = (EditText) findViewById(R.id.user_register_pw);
        user_register_re_pwd = (EditText) findViewById(R.id.user_register_reinput_pw);
        user_login = (Button) findViewById(R.id.user_login_btn);
        user_register = (Button) findViewById(R.id.user_register_btn);
        register_state = (TextView) findViewById(R.id.user_register_state_text);
        current_state = (TextView) findViewById(R.id.user_login_state_text);
    }
}
