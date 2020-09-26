package com.example.newapp_to_do_list.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newapp_to_do_list.MyDBHelper;
import com.example.newapp_to_do_list.R;
import com.example.newapp_to_do_list.view.DataInterface;
import com.example.newapp_to_do_list.view.fragment.BaseFragment;
import com.example.newapp_to_do_list.view.fragment.TaskListFragment;

import java.util.ArrayList;
import java.util.List;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.ViewHolder> {



    DataInterface datainterace1;
    Context cnx;
    List<String> day_ = new ArrayList<>();
    List<Integer> date_ = new ArrayList<>();
    int selectedPosition;
    TaskListFragment taskListFragment = new TaskListFragment();
    String presentDay;
    MyDBHelper myDBHelper = new MyDBHelper(BaseFragment.getMainActivity());


    public DetailsAdapter(Context context, List<Integer> date, List<String> day, DataInterface dataInterface, String fordetailadapter) {
        this.date_ = date;
        this.day_ = day;
        this.cnx = context;
        this.datainterace1 = dataInterface;
        this.presentDay = fordetailadapter;

        dataInterface.data(presentDay, "Add New Task", "Profile", BaseFragment.getMainActivity(),myDBHelper.getCount() );


    }

    public DetailsAdapter() {


    }

    @NonNull
    @Override
    public DetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(cnx).inflate(R.layout.item_rv, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final DetailsAdapter.ViewHolder holder, final int position) {

        holder.day.setText(day_.get(position));
        holder.date.setText(Integer.toString(date_.get(position)));

        if (selectedPosition == position) {
            holder.cardview.setBackgroundResource(R.drawable.backgroundforcardaview);
            taskListFragment.setPostion(date_.get(selectedPosition));



        } else {
            holder.cardview.setBackgroundResource(R.drawable.background_grey);


        }


        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = position;

                notifyDataSetChanged();

                taskListFragment = new TaskListFragment();
                taskListFragment.setPostion(date_.get(selectedPosition));
                BaseFragment.getMainActivity().pushFragment_forBNV(taskListFragment, presentDay);


            }

        });


    }

    @Override
    public int getItemCount() {

        return date_.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        TextView day;
        CardView cardview;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            day = itemView.findViewById(R.id.day);

            cardview = itemView.findViewById(R.id.imageview_);

        }
    }


}
