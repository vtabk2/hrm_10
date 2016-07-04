package com.example.framgia.hrm_10.view.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.framgia.hrm_10.R;
import com.example.framgia.hrm_10.controller.database.DBHelper;
import com.example.framgia.hrm_10.controller.database.UnsignedName;
import com.example.framgia.hrm_10.controller.recyclerviewdata.DataRecyclerViewAdapter;
import com.example.framgia.hrm_10.controller.settings.Settings;
import com.example.framgia.hrm_10.model.data.Staff;
import com.example.framgia.hrm_10.model.listenner.EndlessRecyclerViewScrollListener;
import com.example.framgia.hrm_10.model.listenner.OnClickItemListener;
import com.example.framgia.hrm_10.view.editstaffdepartment.StaffActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by framgia on 22/06/2016.
 */
public class SearchStaffActivity extends AppCompatActivity implements OnClickItemListener, View.OnClickListener {
    RecyclerView.LayoutManager mLayoutManager;
    private DBHelper mDbHelper;
    private RecyclerView mRecyclerView;
    private DataRecyclerViewAdapter mAdapterRecyclerView;
    private List<Staff> mStaffList;
    private EditText mEditTextSearch;
    private RadioGroup mRadioGroupSearch;
    private boolean mIscreated;
    private int mPosition;
    private EndlessRecyclerViewScrollListener mEndlessRecyclerViewScrollListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initDbHelper();
        initViews();
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
        int id = mStaffList.get(mPosition).getId();
        Staff staff = mDbHelper.getDbStaff().getStaff(id);
        mStaffList.set(mPosition, staff);
        mAdapterRecyclerView.notifyItemChanged(mPosition);
        hideAutoEditText();
    }

    private void initDbHelper() {
        mDbHelper = new DBHelper(this);
        mDbHelper.createDbDepartment();
        mDbHelper.createDbStatus();
        mDbHelper.createDbStaff();
    }

    private void initViews() {
        mStaffList = new ArrayList<>();
        mEditTextSearch = (EditText) findViewById(R.id.edit_search);
        Button buttonSearch = (Button) findViewById(R.id.button_search);
        mRadioGroupSearch = (RadioGroup) findViewById(R.id.radioGroup_search);
        String query = getIntent().getStringExtra(Settings.INTENT_DATA);
        mStaffList = mDbHelper.getDbStaff().getListStaffByName(Settings.START_INDEX_DEFAULT, UnsignedName.removeAccent(query), EndlessRecyclerViewScrollListener.STAFF_PER_PAGE);
        mEditTextSearch.setText(query);
        mAdapterRecyclerView = new DataRecyclerViewAdapter(mStaffList, DataRecyclerViewAdapter.TYPE_STAFF);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_search);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getBaseContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapterRecyclerView);
        mAdapterRecyclerView.setOnClickItemListener(this);
        buttonSearch.setOnClickListener(this);
        mEndlessRecyclerViewScrollListener = new EndlessRecyclerViewScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int startIndex) {
                getListStaff(startIndex);
            }
        };
        mRecyclerView.addOnScrollListener(mEndlessRecyclerViewScrollListener);
    }

    private void getListStaff(int startIndex) {
        String query = mEditTextSearch.getText().toString().trim();
        List<Staff> listNextPage;
        switch (mRadioGroupSearch.getCheckedRadioButtonId()) {
            case R.id.radioButton_Phone:
                listNextPage = mDbHelper.getDbStaff().getListStaffByPhone(startIndex, query, EndlessRecyclerViewScrollListener.STAFF_PER_PAGE);
                break;
            case R.id.radioButton_NameDepartment:
                listNextPage = mDbHelper.getDbStaff().getListStaffByDepartment(startIndex, query, EndlessRecyclerViewScrollListener.STAFF_PER_PAGE);
                break;
            default:
                listNextPage = mDbHelper.getDbStaff().getListStaffByName(startIndex, UnsignedName.removeAccent(query), EndlessRecyclerViewScrollListener.STAFF_PER_PAGE);
                break;
        }
        if (listNextPage != null) {
            mStaffList.addAll(listNextPage);
            mAdapterRecyclerView.notifyDataSetChanged();
        }
    }

    private void doSearch() {
        mStaffList.clear();
        mEndlessRecyclerViewScrollListener.reset();
        getListStaff(Settings.START_INDEX_DEFAULT);
        hideAutoEditText();
    }

    private void hideAutoEditText() {
        InputMethodManager imm = (InputMethodManager) getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEditTextSearch.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }

    @Override
    public void onClick(View v) {
        doSearch();
    }

    @Override
    public void onClickItem(View view, int position) {
        mPosition = position;
        Intent intent = new Intent(getApplicationContext(), StaffActivity.class);
        intent.putExtra(Settings.ID_STAFF, mStaffList.get(position).getId());
        intent.putExtra(Settings.SETTINGS, Settings.SHOW_STAFF);
        startActivity(intent);
    }

    @Override
    public void onLongClickItem(View view, int position) {
        mPosition = position;
        Intent intent = new Intent(getApplicationContext(), StaffActivity.class);
        intent.putExtra(Settings.ID_STAFF, mStaffList.get(position).getId());
        intent.putExtra(Settings.SETTINGS, Settings.EDIT_STAFF);
        startActivity(intent);
    }
}
