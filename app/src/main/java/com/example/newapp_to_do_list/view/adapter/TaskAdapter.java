package com.example.newapp_to_do_list.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newapp_to_do_list.MyDBHelper;
import com.example.newapp_to_do_list.R;
import com.example.newapp_to_do_list.model.pojo.Plans;
import com.example.newapp_to_do_list.view.fragment.BaseFragment;
import com.example.newapp_to_do_list.view.fragment.TaskListFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {


    List<Integer> new_position = new ArrayList<>();
    int count;
    String fromText;
    Context cnx;
    Random random = new Random();

    MyDBHelper myDBHelper = new MyDBHelper(BaseFragment.getMainActivity());
    Plans plans = new Plans();
    List<String> planDataFromDatabase = new ArrayList<>();
    TaskListFragment taskListFragment = new TaskListFragment();


    public TaskAdapter(Context context, List<String> data, String from, int countTasks, List<Integer> newposition) {
        this.cnx = context;
        planDataFromDatabase = data;
        this.fromText = from;
        this.count = countTasks;
        this.new_position = newposition;

    }

    @NonNull
    @Override


    public TaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(cnx).inflate(R.layout.item_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TaskAdapter.ViewHolder holder, int position) {


        if (fromText.equals("fromloop")) {
            int posi = new_position.get(position);
            planDataFromDatabase = myDBHelper.getDBData(posi+1);

        }
        plans.setTaskpending(myDBHelper.getCount());
        if (planDataFromDatabase != null) {


            holder.planeName.setText(planDataFromDatabase.get(0));
            int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
            holder.cardView.setCardBackgroundColor(color);
            holder.currentTime.setText(planDataFromDatabase.get(3));
            holder.PlanDetail.setText(planDataFromDatabase.get(5));
            holder.time_from_to.setText(planDataFromDatabase.get(1).concat("  -  ").concat(planDataFromDatabase.get(2)));
            if (planDataFromDatabase.get(4) != null) {
                holder.timePicked.setText(planDataFromDatabase.get(4));
            } else {
                holder.timePicked.setText("");
            }


            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.layout.getVisibility() == View.GONE) {
                        TransitionManager.beginDelayedTransition(holder.cardView, new AutoTransition());
                        holder.layout.setVisibility(View.VISIBLE);
                        holder.time_from_to.setVisibility(View.VISIBLE);
                        holder.currentTime.setVisibility(View.VISIBLE);
                        holder.bar.setVisibility(View.VISIBLE);


                    } else {
                        TransitionManager.beginDelayedTransition(holder.cardView, new AutoTransition());
                        holder.layout.setVisibility(View.GONE);
                        holder.time_from_to.setVisibility(View.GONE);
                        holder.currentTime.setVisibility(View.GONE);
                        holder.bar.setVisibility(View.GONE);


                    }

                }
            });


        }


    }

    @Override
    public int getItemCount() {



            return new_position.size();


    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView time_from_to;
        CardView cardView;
        TextView planeName;
        ConstraintLayout linearLayout;
        TextView timePicked;
        TextView currentTime;
        TextView PlanDetail;
        ImageView bar;
        TextView layout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            time_from_to = itemView.findViewById(R.id.date_from);
            currentTime = itemView.findViewById(R.id.date_to);
            cardView = itemView.findViewById(R.id.task_cardview);
            planeName = itemView.findViewById(R.id.plan_name);
            bar = itemView.findViewById(R.id.bar);
            linearLayout = itemView.findViewById(R.id.stretch_linearlayout);
            timePicked = itemView.findViewById(R.id.time_picked);
            PlanDetail = itemView.findViewById(R.id.plandetails);
            layout = itemView.findViewById(R.id.plandetails);

        }

    }


}
