package com.example.framgia.hrm_10.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.framgia.hrm_10.R;

public class MainActivity extends AppCompatActivity {
    private Button sign_in;
    private EditText name,pass;
    private TextView create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        onLogin();
    }

    private void initViews() {
        sign_in=(Button)findViewById(R.id.button);
        name=(EditText)findViewById(R.id.editText);
        pass=(EditText)findViewById(R.id.editText2);
        create=(TextView)findViewById(R.id.textView2);
        create.setVisibility(View.GONE);
    }

    private void onLogin() {
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _name=name.getText().toString();
                String _pass=pass.getText().toString();
                    Toast.makeText(getBaseContext(),R.string.login1,Toast.LENGTH_LONG).show();
            }
        });
    }
}
