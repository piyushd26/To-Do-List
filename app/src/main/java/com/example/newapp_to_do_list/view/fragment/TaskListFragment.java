package com.example.newapp_to_do_list.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newapp_to_do_list.MyDBHelper;
import com.example.newapp_to_do_list.R;
import com.example.newapp_to_do_list.SimpleDividerItemDecoration;
import com.example.newapp_to_do_list.common.Utility;
import com.example.newapp_to_do_list.model.pojo.Plans;
import com.example.newapp_to_do_list.view.DataInterface;
import com.example.newapp_to_do_list.view.activity.MainActivity;
import com.example.newapp_to_do_list.view.adapter.AllTasksListAdapter;
import com.example.newapp_to_do_list.view.adapter.TaskAdapter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class TaskListFragment extends Fragment implements DataInterface {


    View view;
    LinearLayoutManager linearLayoutManager;

    TaskAdapter taskAdapter;
    TextView presentDay;
    RecyclerView recyclerView_taskfragment;
    MyDBHelper helper;
    List<String> ALLdateFrom = new ArrayList<>();
    List<String> allList = new ArrayList<>();
    Utility utility = new Utility();
    DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    int countRows;
    TextView toolbar_taskpending;
    Integer selectedPositionFromDetailAdapter;
    String fromloop = "fromloop";
    List<String> planDataFromDatabase = new ArrayList<>();
    List<Integer> listOfPositionFfromDatabase = new ArrayList<>();
    Plans plans = new Plans();
    int countTaskFromLoop = 0;
    Date datefrom = null;
    RecyclerView recyclerView_allTaskList;
    Date datecurrent = null;
    List<String> newListAll = new ArrayList<>();
    TextView allTasks;
    List<String> swipedPositionFromAdapter = new ArrayList<>();

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_tasklist, container, false);
        presentDay = view.findViewById(R.id.calender);
        recyclerView_taskfragment = view.findViewById(R.id.rv_tasklist);
        toolbar_taskpending = view.findViewById(R.id.numberoftask);
        helper = new MyDBHelper(BaseFragment.getMainActivity());
        allTasks = view.findViewById(R.id.tv_pendin);
        recyclerView_allTaskList = view.findViewById(R.id.rv_alltasks);
        countRows = helper.getCount();

        retrieveDatabase();
        return view;
    }

    public void retrieveDatabase() {
        helper = new MyDBHelper(BaseFragment.getMainActivity());

        for (int p = 0; p < countRows; p++) {

            planDataFromDatabase = helper.getDBData(p + 1);
            ALLdateFrom.addAll(Collections.singletonList(planDataFromDatabase.get(1)));

            if (selectedPositionFromDetailAdapter != null) {
                adapterSelected(p);
            } else {
                adapterNotSelected(p);
            }
        }
         adapterAllTaskList();
    }

    private void adapterAllTaskList() {

        allTasks.setVisibility(View.GONE);
        recyclerView_allTaskList.setVisibility(View.GONE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        AllTasksListAdapter allTasksListAdapter = new AllTasksListAdapter(getContext(), newListAll, countRows);
        recyclerView_allTaskList.setLayoutManager(linearLayoutManager);
        recyclerView_allTaskList.setHasFixedSize(true);
        recyclerView_allTaskList.setAdapter(allTasksListAdapter);

    }


    private void adapterNotSelected(int p) {

        Calendar calendar = Calendar.getInstance();
        int currentDateCalendar = calendar.get(Calendar.DATE);
        try {
            datefrom = sdf.parse(ALLdateFrom.get(p));
            datecurrent = sdf.parse(utility.getCurrentDate(currentDateCalendar));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (sdf.format(datefrom).compareTo(sdf.format(datecurrent)) == 0) {
            countTaskFromLoop = countTaskFromLoop + 1;
            listOfPositionFfromDatabase.add(p);
            adapter_tasklist(planDataFromDatabase, fromloop, countTaskFromLoop, listOfPositionFfromDatabase);

        }
    }

    private void adapterSelected(int p) {

        try {
            datefrom = sdf.parse(ALLdateFrom.get(p));
            datecurrent = sdf.parse(utility.getCurrentDate(selectedPositionFromDetailAdapter));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (sdf.format(datefrom).compareTo(sdf.format(datecurrent)) == 0) {
            countTaskFromLoop = countTaskFromLoop + 1;
            listOfPositionFfromDatabase.add(p);
            adapter_tasklist(planDataFromDatabase, fromloop, countTaskFromLoop, listOfPositionFfromDatabase);
        }

    }

    public void insertdatabase(MainActivity mainActivity, Plans planData) {
        helper = new MyDBHelper(mainActivity);
        String res = helper.insertPlanData(planData);
        Log.e("spl", "insertdatabase: " + res);
    }


    public void adapter_tasklist(List<String> data, String from, int countTasks, final List<Integer> newposition) {
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView_taskfragment.setHasFixedSize(true);
        taskAdapter = new TaskAdapter(getContext(), data, from, countTasks, newposition);
        recyclerView_taskfragment.setLayoutManager(linearLayoutManager);
        recyclerView_taskfragment.addItemDecoration(new SimpleDividerItemDecoration(getContext()));
        recyclerView_taskfragment.setAdapter(taskAdapter);
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                helper = new MyDBHelper(BaseFragment.getMainActivity());
                int position = viewHolder.getAdapterPosition();



                //

                Toast.makeText(getContext(), String.valueOf(position), Toast.LENGTH_LONG).show();
                taskAdapter.notifyDataSetChanged();
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView_taskfragment);


    }


    public void setPostion(Integer integer) {
        this.selectedPositionFromDetailAdapter = integer;
    }

    @Override
    public void data(String presentday, String AddlistToolbar, String ProfileToolbar, Context context, int count) {
        BaseFragment.getMainActivity().settoolbarText(presentday, count);
    }


    public void setPostionFromTaskAdapter(String position) {
        this.swipedPositionFromAdapter.add(position);
    }
}

