package com.example.framgia.hrm_10.view;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.example.framgia.hrm_10.R;
import com.example.framgia.hrm_10.controller.DBHelper;
import com.example.framgia.hrm_10.model.Staff;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by framgia on 08/06/2016.
 */
public class ListStaffActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_staff);
    }
}
