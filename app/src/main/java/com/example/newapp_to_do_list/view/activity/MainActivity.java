package com.example.newapp_to_do_list.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newapp_to_do_list.MyDBHelper;
import com.example.newapp_to_do_list.R;
import com.example.newapp_to_do_list.common.Utility;
import com.example.newapp_to_do_list.view.DataInterface;
import com.example.newapp_to_do_list.view.adapter.DetailsAdapter;
import com.example.newapp_to_do_list.view.fragment.AddTaskFragment;
import com.example.newapp_to_do_list.view.fragment.BaseFragment;
import com.example.newapp_to_do_list.view.fragment.ProfileFragment;
import com.example.newapp_to_do_list.view.fragment.TaskListFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class MainActivity extends AppCompatActivity implements DataInterface {

    public static final String TABHOME = "tab_home";
    public static final String TABPROFILE = "tab_profile";
    TaskListFragment taskListFragment = new TaskListFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    Calendar cal = null;
    int barDate;
    String barMonth;
    BottomNavigationView bottomNavigationView;
    String barDay;
    int date = 0, day = 0;
    int month;
    FragmentManager fragmentManager;
    String fordetailadapter;
    LinearLayoutManager linearLayoutManager;
    DetailsAdapter detailsAdapter;
    FragmentTransaction fragmentTransaction;
    String newDay;
    AddTaskFragment addTaskFragment = new AddTaskFragment();
    String newDate;
    RecyclerView recyclerView;
    TextView present_Day;
    int pendingTask_;
    String presentDayFromInterface;
    String currentTab;
    String dayPresent;
    TextView numberOfTask_TV;

    private HashMap<String, Stack<Fragment>> TD_Stacks;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.home_bn:
                    selectedTab(TABHOME);
                    recyclerView.setVisibility(View.VISIBLE);

                    break;
                case R.id.profile_bn:
                    selectedTab(TABPROFILE);
                    recyclerView.setVisibility(View.GONE);
                    break;

            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        present_Day = findViewById(R.id.calender);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        BaseFragment.setMainActivity(this);

        recyclerView = findViewById(R.id.rv_dateday);
        numberOfTask_TV = findViewById(R.id.numberoftask);
        addTaskFragment.setRV(recyclerView);
        recyclerView.setVisibility(View.VISIBLE);

        init();
        toolbar();
        floatingActionButton();

        cal = Calendar.getInstance();
        barDate = cal.get(Calendar.DATE);
        barMonth = newDate;
        barDay = newDay;
        if (presentDayFromInterface != null) {
            present_Day.setText(presentDayFromInterface);
        } else {
            present_Day.setText(barMonth.concat(", ").concat(String.valueOf(barDate)).concat(" ").concat(barDay));
            dayPresent = barMonth.concat(", ").concat(String.valueOf(barDate)).concat(" ").concat(barDay);
        }
        if (pendingTask_ != 0) {
            numberOfTask_TV.setText(pendingTask_);
        }
        adapterDateDay();

    }

    private void adapterDateDay() {

        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        Calendar calendar3 = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat_day = new SimpleDateFormat("EEE");
        SimpleDateFormat simpleDateFormat_date = new SimpleDateFormat("d");
        SimpleDateFormat simpleDateFormat_month = new SimpleDateFormat("MMM");
        List<String> DAYList = new ArrayList<>();
        List<Integer> DATEList = new ArrayList<>();
        List<String> MONTHList = new ArrayList<>();
        for (int e = 0; e <= 9; e++) {

            DAYList.add(simpleDateFormat_day.format(calendar1.getTime()));
            calendar1.add(Calendar.DAY_OF_WEEK, 1);

        }
        for (int d = 0; d <= 9; d++) {

            DATEList.add(Integer.valueOf(simpleDateFormat_date.format(calendar2.getTime())));
            calendar2.add(Calendar.DATE, 1);

        }


        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setHasFixedSize(true);
        detailsAdapter = new DetailsAdapter(this, DATEList, DAYList, this, dayPresent);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(detailsAdapter);
    }

    private void init() {

        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        TD_Stacks = new HashMap<String, Stack<Fragment>>();
        TD_Stacks.put(TABHOME, new Stack<Fragment>());
        TD_Stacks.put(TABPROFILE, new Stack<Fragment>());

        bottomNavigationView.setSelectedItemId(R.id.home_bn);


    }

    private void toolbar() {


        Calendar calendar = Calendar.getInstance();

        date = calendar.get(Calendar.DATE);
        month = calendar.get(Calendar.MONTH);

        int endDateofMonth = 0;
        switch (month) {
            case 0:
                newDate = "JAN";
                endDateofMonth = 31;
                break;
            case 1:
                newDate = "FEB";
                endDateofMonth = 28;
                break;
            case 2:
                newDate = "MARCH";
                endDateofMonth = 31;
                break;
            case 3:
                newDate = "APRIL";
                endDateofMonth = 30;
                break;
            case 4:
                newDate = "MAY";
                endDateofMonth = 31;
                break;
            case 5:
                newDate = "JUNE";
                endDateofMonth = 30;
                break;
            case 6:
                newDate = "JULY";
                endDateofMonth = 31;
                break;
            case 7:
                newDate = "AUG";
                endDateofMonth = 31;
                break;
            case 8:
                newDate = "SEPT";
                endDateofMonth = 30;
                break;
            case 9:
                newDate = "OCT";
                endDateofMonth = 31;
                break;
            case 10:
                newDate = "NOV";
                endDateofMonth = 30;
                break;
            case 11:
                newDate = "DEC";
                endDateofMonth = 31;
                break;
        }


        day = calendar.get(Calendar.DAY_OF_WEEK);

        switch (day) {
            case 1:
                newDay = "SUNDAY";
                break;
            case 2:
                newDay = "MONDAY";
                break;
            case 3:
                newDay = "TUESDAY";
                break;
            case 4:
                newDay = "WEDNESDAY";
                break;
            case 5:
                newDay = "THURSDAY";
                break;
            case 6:
                newDay = "FRIDAY";
                break;
            case 7:
                newDay = "SATURDAY";
                break;
        }
    }

    private void floatingActionButton() {
        FloatingActionButton floatingActionButton;
        floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setVisibility(View.GONE);
                pushFragment_forBNV(addTaskFragment);

            }
        });
    }

    public void pushFragment_forBNV(Fragment fragment) {
       // TextView toolbar = findViewById(R.id.calender);
       // toolbar.setText(toolbar_name);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.root_frame_main, fragment);
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.slide_in_up, R.anim.slid_in_down);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    private void selectedTab(String tabid) {
        currentTab = tabid;

        if (tabid.equals(TABHOME)) {
            taskListFragment = new TaskListFragment();
            pushFragment(tabid, taskListFragment, present_Day.getText().toString());
        } else if (tabid.equals(TABPROFILE)) {
            profileFragment = new ProfileFragment();
            pushFragment(tabid, profileFragment, "Profile");

        }
    }

    public void pushFragment(String tabid, Fragment fragment, String setTextToolbar) {
        TextView toolbar = findViewById(R.id.calender);
        toolbar.setText(setTextToolbar);

        TD_Stacks.get(tabid).push(fragment);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.root_frame_main, fragment);
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.slide_in_up, R.anim.slid_in_down);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @Override
    public void onBackPressed() {
        if (TD_Stacks.get(currentTab).size() == 1) {

            finish();
            return;
        }
        //  Fragment fragment  = TD_Stacks.get(currentTab).elementAt(TD_Stacks.get(currentTab).size()-1);


        /* Goto previous fragment in navigation stack of this tab */
        popFragments();
    }

    private void popFragments() {

        if (recyclerView.getVisibility() == View.GONE) {
            recyclerView.setVisibility(View.VISIBLE);
        }

        Fragment fragment = TD_Stacks.get(currentTab).elementAt(TD_Stacks.get(currentTab).size() - 1);

        TD_Stacks.get(currentTab).pop();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.root_frame_main, fragment);
        fragmentTransaction.commit();
    }


    @Override
    public void data(String presentday, String AddlistToolbar, String ProfileToolbar, Context context, int count) {
        BaseFragment.getMainActivity().settoolbarText(presentday, count);
        fordetailadapter = presentday;
    }

    public void settoolbarText(String text, int counr) {
        presentDayFromInterface = text;
        pendingTask_ = counr;


    }


}