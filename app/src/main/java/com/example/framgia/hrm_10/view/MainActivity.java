package com.example.framgia.hrm_10.view;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.framgia.hrm_10.R;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mSignin;
    private EditText mName, mPass;
    private TextView mCreate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }
    private void initViews() {
        mSignin = (Button) findViewById(R.id.button_login);
        mName = (EditText) findViewById(R.id.edit_username);
        mPass = (EditText) findViewById(R.id.edit_password);
        mCreate = (TextView) findViewById(R.id.text_create);
        mCreate.setVisibility(View.GONE);
        // login
        mSignin.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        String _name = mName.getText().toString();
        String _pass = mPass.getText().toString();
        Toast.makeText(getBaseContext(), R.string.login1, Toast.LENGTH_LONG).show();
        startActivity(new Intent(getBaseContext(), ListDepartmentsActivity.class));
    }
}
