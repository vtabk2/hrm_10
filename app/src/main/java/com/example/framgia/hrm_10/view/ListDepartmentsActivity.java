package com.example.framgia.hrm_10.view;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.framgia.hrm_10.R;
import com.example.framgia.hrm_10.controller.DBHelper;
import com.example.framgia.hrm_10.model.DataRecyclerView;
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
        setContentView(R.layout.list_departments);
        initDBHelper();
        initViews();
    }
    private void initDBHelper() {
        mDbHelper = new DBHelper(this);
        int length;
        length = mDbHelper.getDepartmentCount();
        if (length == mDbHelper.DATABASE_NULL) {
            // create db test
            createDB_Test();
        }
        mList_departments = mDbHelper.getAllDepartments();
    }
    private void initViews() {
        DataRecyclerView mAdapter = new DataRecyclerView(mList_departments);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
    }
    private void createDB_Test() {
        // db test 7/6/2016
        mDbHelper.addDepartment(new Departments(mDbHelper.DEPARTMENT1, R.drawable.image1));
        mDbHelper.addDepartment(new Departments(mDbHelper.DEPARTMENT2,R.drawable.image2));
        mDbHelper.addDepartment(new Departments(mDbHelper.DEPARTMENT3,R.drawable.image1));
        mDbHelper.addDepartment(new Departments(mDbHelper.DEPARTMENT4,R.drawable.image2));
    }
}
