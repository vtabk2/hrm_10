package com.example.framgia.hrm_10.controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.framgia.hrm_10.R;
import com.example.framgia.hrm_10.model.Departments;
import com.example.framgia.hrm_10.model.OnClickItemListener;
import com.example.framgia.hrm_10.model.Staff;

import java.util.List;

/**
 * Created by framgia on 07/06/2016.
 */
public class DataRecyclerViewAdapter extends RecyclerView.Adapter<DataRecyclerViewAdapter.MyViewHolder> {
    public static final int TYPE_DEPARTMENT = 0;
    public static final int TYPE_STAFF = 1;
    private List<Departments> mDepartmentList;
    private List<Staff> mStaffList;
    private int type;
    private OnClickItemListener mOnClickItemListener;

    public DataRecyclerViewAdapter(List<Staff> staffList, int typeStaff) {
        this.mStaffList = staffList;
        this.type = typeStaff;
    }

    public DataRecyclerViewAdapter(List<Departments> departmentsList) {
        this.mDepartmentList = departmentsList;
        this.type = TYPE_DEPARTMENT;
    }

    @Override
    public int getItemViewType(int position) {
        return type;
    }

    public void setOnClickItemListener(OnClickItemListener mOnClickItemListener) {
        this.mOnClickItemListener = mOnClickItemListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (type) {
            case TYPE_DEPARTMENT:
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_department_vi, parent, false);
                return new MyViewHolder(itemView);
            case TYPE_STAFF:
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_staff_vi, parent, false);
                return new MyViewHolder(view);
            default:
                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_department_vi, parent, false);
                return new MyViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.mImage.setImageDrawable(null);
        Glide.clear(holder.mImage);
        switch (type) {
            case TYPE_DEPARTMENT:
                Departments departments = mDepartmentList.get(position);
                Glide.with(holder.itemView.getContext()).load(departments.getIdImage())
                        .thumbnail(0.5f)
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.mImage);
                holder.mName.setText(departments.getName());
                break;
            case TYPE_STAFF:
                Staff staff = mStaffList.get(position);
                holder.mName.setText(staff.getName());
                Glide.with(holder.itemView.getContext()).load(R.drawable.img_staff)
                        .thumbnail(0.5f)
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.mImage);
                break;
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClickItemListener != null) {
                    mOnClickItemListener.onClickItem(holder.itemView, holder.getLayoutPosition());
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnClickItemListener != null) {
                    mOnClickItemListener.onLongClickItem(holder.itemView, holder.getLayoutPosition());
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        switch (type) {
            case TYPE_DEPARTMENT:
                return mDepartmentList == null ? 0 : mDepartmentList.size();
            case TYPE_STAFF:
                return mStaffList == null ? 0 : mStaffList.size();
            default:
                return 0;
        }
    }

    protected class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mName;
        public ImageView mImage;

        public MyViewHolder(final View view) {
            super(view);
            switch (type) {
                case TYPE_DEPARTMENT:
                    mName = (TextView) view.findViewById(R.id.text_name);
                    mImage = (ImageView) view.findViewById(R.id.image_department);
                    break;
                case TYPE_STAFF:
                    mName = (TextView) view.findViewById(R.id.text_nameStaff);
                    mImage = (ImageView) view.findViewById(R.id.image_staff);
                    break;
            }
        }
    }
}
