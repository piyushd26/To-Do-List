package com.example.newapp_to_do_list.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newapp_to_do_list.MyDBHelper;
import com.example.newapp_to_do_list.R;
import com.example.newapp_to_do_list.view.fragment.BaseFragment;
import com.example.newapp_to_do_list.view.fragment.TaskListFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AllTasksListAdapter extends RecyclerView.Adapter<AllTasksListAdapter.ViewHolder> {

    int rowsCount;
    List<String> allTaskList = new ArrayList<>();
    Context cnx;
    Random random = new Random();
    TaskListFragment taskListFragment = new TaskListFragment();
    MyDBHelper myDBHelper = new MyDBHelper(BaseFragment.getMainActivity());

    public AllTasksListAdapter(Context context, List<String> planDataFromDatabase, int countRows) {
        this.cnx = context;
        this.allTaskList = planDataFromDatabase;
        this.rowsCount = countRows;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(cnx).inflate(R.layout.item_alltasklist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        allTaskList = myDBHelper.getDBData(position + 1);
        taskListFragment.setPostionFromTaskAdapter(allTaskList.get(0));
        if (allTaskList != null) {
            int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
            holder.cardView.setCardBackgroundColor(color);
            holder.Name.setText(allTaskList.get(0));
            holder.Date.setText(allTaskList.get(4));
            holder.Time.setText(allTaskList.get(1));
        } else {
            Toast.makeText(cnx, "AllTaskList is NUll", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public int getItemCount() {
        return rowsCount;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Name;
        TextView Date;
        TextView Time;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            Name = itemView.findViewById(R.id.tv_name);
            cardView = itemView.findViewById(R.id.alltask_carview);
            Date = itemView.findViewById(R.id.tv_dates);
            Time = itemView.findViewById(R.id.tv_time);

        }
    }
}
