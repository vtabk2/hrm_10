package com.example.framgia.hrm_10.view.showstaffdepartment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.framgia.hrm_10.R;
import com.example.framgia.hrm_10.controller.database.DBHelper;
import com.example.framgia.hrm_10.controller.recyclerviewdata.DataRecyclerViewAdapter;
import com.example.framgia.hrm_10.controller.settings.Settings;
import com.example.framgia.hrm_10.model.data.Departments;
import com.example.framgia.hrm_10.model.data.Staff;
import com.example.framgia.hrm_10.model.listenner.EndlessRecyclerViewScrollListener;
import com.example.framgia.hrm_10.model.listenner.OnClickItemListener;
import com.example.framgia.hrm_10.view.editstaffdepartment.DepartmentActivity;
import com.example.framgia.hrm_10.view.editstaffdepartment.StaffActivity;
import com.example.framgia.hrm_10.view.home.MainActivity;
import com.example.framgia.hrm_10.view.search.SearchStaffActivity;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

/**
 * Created by framgia on 02/06/2016.
 */
public class ListDepartmentStaffActivity extends AppCompatActivity implements OnClickItemListener {
    private DBHelper mDbHelper;
    private List<Departments> mDepartmentsList;
    private List<Staff> mStaffList;
    private RecyclerView mRecyclerView;
    private DataRecyclerViewAdapter mAdapterRecyclerView;
    private boolean mIscreated;
    private int mTypeDataRecyclerViewAdapter;
    private int mIdDepartment;
    private SearchView.OnQueryTextListener mSearchViewListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_department_staff);
        initDBHelper();
        checkInitViews();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mIscreated = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mIscreated) {
            showUpdateViews();
        }
    }

    private void showUpdateViews() {
        switch (mTypeDataRecyclerViewAdapter) {
            case DataRecyclerViewAdapter.TYPE_DEPARTMENT:
                mDepartmentsList.clear();
                mDepartmentsList.addAll(mDbHelper.getDbDepartment().getAllDepartments());
                mAdapterRecyclerView.notifyDataSetChanged();
                break;
            case DataRecyclerViewAdapter.TYPE_STAFF:
                mStaffList.clear();
                getListStaff(Settings.START_INDEX_DEFAULT);
                break;
        }
    }

    private void checkInitViews() {
        String settings = getIntent().getStringExtra(Settings.SETTINGS);
        switch (settings) {
            case Settings.SHOW_DEPARTMENT:
                initViews(DataRecyclerViewAdapter.TYPE_DEPARTMENT);
                break;
            case Settings.SHOW_STAFF:
                mIdDepartment = getIntent().getIntExtra(Settings.ID_DEPARTMENT, Settings.ID_DEPARTMENT_DEFAULT);
                if (mIdDepartment > Settings.ID_DEPARTMENT_NULL) {
                    initViews(DataRecyclerViewAdapter.TYPE_STAFF);
                    getListStaff(Settings.START_INDEX_DEFAULT);
                }
                break;
        }
    }

    private void initDBHelper() {
        mDbHelper = new DBHelper(this);
        mDbHelper.createDbDepartment();
        mDbHelper.createDbStaff();
        mDepartmentsList = mDbHelper.getDbDepartment().getAllDepartments();
    }

    private void initViews(final int type) {
        mStaffList = new ArrayList<Staff>();
        mTypeDataRecyclerViewAdapter = type;
        mAdapterRecyclerView = null;
        switch (type) {
            case DataRecyclerViewAdapter.TYPE_DEPARTMENT:
                mAdapterRecyclerView = new DataRecyclerViewAdapter(mDepartmentsList);
                break;
            case DataRecyclerViewAdapter.TYPE_STAFF:
                mAdapterRecyclerView = new DataRecyclerViewAdapter(mStaffList, type);
                break;
        }
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getBaseContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new SlideInUpAnimator());
        mRecyclerView.setAdapter(mAdapterRecyclerView);
        mAdapterRecyclerView.setOnClickItemListener(this);
        mSearchViewListener = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(getApplicationContext(), SearchStaffActivity.class);
                intent.putExtra(Settings.INTENT_DATA, query);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // do nothing
                return false;
            }
        };
        mRecyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int startIndex) {
                getListStaff(startIndex);
            }
        });
    }

    private void getListStaff(int startIndex) {
        List<Staff> listNextPage = mDbHelper.getDbStaff().getListStaffbyIdDepartment(startIndex, mIdDepartment, EndlessRecyclerViewScrollListener.STAFF_PER_PAGE);
        if (listNextPage != null) {
            mStaffList.addAll(listNextPage);
            mAdapterRecyclerView.notifyDataSetChanged();
        }
    }

    @Override
    public void onClickItem(View view, int position) {
        switch (mAdapterRecyclerView.getItemViewType(position)) {
            case DataRecyclerViewAdapter.TYPE_DEPARTMENT:
                Intent intent = new Intent(getApplicationContext(), ListDepartmentStaffActivity.class);
                intent.putExtra(Settings.ID_DEPARTMENT, mDepartmentsList.get(position).getId());
                intent.putExtra(Settings.SETTINGS, Settings.SHOW_STAFF);
                startActivity(intent);
                break;
            case DataRecyclerViewAdapter.TYPE_STAFF:
                intent = new Intent(getApplicationContext(), StaffActivity.class);
                intent.putExtra(Settings.ID_STAFF, mStaffList.get(position).getId());
                intent.putExtra(Settings.SETTINGS, Settings.SHOW_STAFF);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onLongClickItem(View view, int position) {
        int type = mAdapterRecyclerView.getItemViewType(position);
        switch (type) {
            case DataRecyclerViewAdapter.TYPE_DEPARTMENT:
                Intent intent = new Intent(getApplicationContext(), DepartmentActivity.class);
                intent.putExtra(Settings.ID_DEPARTMENT, mDepartmentsList.get(position).getId());
                intent.putExtra(Settings.SETTINGS, Settings.EDIT_DEPARTMENT);
                startActivity(intent);
                break;
            case DataRecyclerViewAdapter.TYPE_STAFF:
                intent = new Intent(getApplicationContext(), StaffActivity.class);
                intent.putExtra(Settings.ID_STAFF, mStaffList.get(position).getId());
                intent.putExtra(Settings.SETTINGS, Settings.EDIT_STAFF);
                startActivity(intent);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(mTypeDataRecyclerViewAdapter==DataRecyclerViewAdapter.TYPE_DEPARTMENT) {
            getMenuInflater().inflate(R.menu.options_menu, menu);
            MenuItem searchMenuItem = menu.findItem(R.id.search);
            SearchView searchView = (SearchView) searchMenuItem.getActionView();
            searchView.setOnQueryTextListener(mSearchViewListener);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.addStaff:
                Intent intent = new Intent(getBaseContext(), StaffActivity.class);
                intent.putExtra(Settings.SETTINGS, Settings.ADD_STAFF);
                startActivity(intent);
                break;
            case R.id.addDepartment:
                intent = new Intent(getApplicationContext(), DepartmentActivity.class);
                intent.putExtra(Settings.SETTINGS, Settings.ADD_DEPARTMENT);
                startActivity(intent);
                break;
            case R.id.logOut:
                AlertDialog myAlertDialog = createAlertDialog();
                myAlertDialog.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private AlertDialog createAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getText(R.string.message))
                .setCancelable(false)
                .setPositiveButton(getText(R.string.ok),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(myIntent);
                                finish();
                            }
                        })
                .setNeutralButton(getText(R.string.cancel), null);
        AlertDialog dialog = builder.create();
        return dialog;
    }

    @Override
    public void onBackPressed() {
        switch (mTypeDataRecyclerViewAdapter) {
            case DataRecyclerViewAdapter.TYPE_DEPARTMENT:
                // nothing
                break;
            default:
                super.onBackPressed();
                break;
        }
    }
}