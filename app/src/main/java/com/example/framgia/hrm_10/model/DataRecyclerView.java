package com.example.framgia.hrm_10.model;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.framgia.hrm_10.R;
import java.util.List;
/**
 * Created by framgia on 07/06/2016.
 */
public class DataRecyclerView extends RecyclerView.Adapter<DataRecyclerView.MyViewHolder> {
    private List<Departments> mList;
    public DataRecyclerView(List<Departments> list) {
        this.mList = list;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_department_vi, parent, false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Departments departments = mList.get(position);
        holder.mName.setText(departments.getName());
        holder.mImage.setImageResource(departments.getIdImage());
    }
    @Override
    public int getItemCount() {
        return mList.size();
    }
    protected class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mName;
        public ImageView mImage;
        public MyViewHolder(View view) {
            super(view);
            mName = (TextView) view.findViewById(R.id.text_name);
            mImage = (ImageView) view.findViewById(R.id.image_department);
        }
    }
}
