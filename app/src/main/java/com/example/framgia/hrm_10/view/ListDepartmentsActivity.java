package com.example.framgia.hrm_10.view;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.framgia.hrm_10.R;
import com.example.framgia.hrm_10.controller.DBHelper;
import com.example.framgia.hrm_10.controller.DBTest;
import com.example.framgia.hrm_10.controller.DepartmentsRecyclerView;
import com.example.framgia.hrm_10.model.Departments;
import java.util.List;
/**
 * Created by framgia on 02/06/2016.
 */
public class ListDepartmentsActivity extends AppCompatActivity {
    private DBHelper mDbHelper;
    private List<Departments> mList_departments;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_departments);
        initDBHelper();
        initViews();
    }
    private void initDBHelper() {
        mDbHelper = new DBHelper(this);
        int length;
        length = mDbHelper.getDepartmentCount();
        if (length == DBTest.DATABASE_NULL) {
            // create db test
            DBTest.create(mDbHelper);
        }
        mList_departments = mDbHelper.getAllDepartments();
    }
    private void initViews() {
        DepartmentsRecyclerView mAdapter = new DepartmentsRecyclerView(mList_departments);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
    }
}
