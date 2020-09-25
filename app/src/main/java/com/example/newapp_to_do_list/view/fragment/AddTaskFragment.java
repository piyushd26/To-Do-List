package com.example.newapp_to_do_list.view.fragment;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newapp_to_do_list.MyBroadcastReceiver;
import com.example.newapp_to_do_list.R;
import com.example.newapp_to_do_list.model.pojo.Plans;
import com.example.newapp_to_do_list.view.DataInterface;
import com.example.newapp_to_do_list.view.activity.MainActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static android.content.Context.ALARM_SERVICE;

public class AddTaskFragment extends Fragment implements DataInterface {


    public static final int My_Permission = 0;
    View view;
    CheckBox checkBox;
    Button button;
    EditText alarm;
    TextView editText;
    TextView from_date;
    TextView clearr;
    TextView to_date;
    EditText ed_titleoftask;
    TimePickerDialog timePickerDialog;
    String ampm;
    RecyclerView recyclerView_;
    Calendar c;
    Button alarm_btn;
    MyBroadcastReceiver myBroadcastReceiver;
    int currentMinute, currentHour;
    int tp_hour;
    int tp_min;
    String tp_ampm;
    int dateTimePicker_year;
    int dateTimePicker_monthofyear;
    int dateTimePicker_dayofMonth;
    int dateTimePicker_year2;
    int dateTimePicker_monthofyear2;
    int dateTimePicker_dayofMonth2;
    EditText writetask;
    int tp_checked_min;
    TaskListFragment taskListFragment = new TaskListFragment();
    MainActivity mainActivity;
    String presentDay;
    String addlistheadeing;
    private Calendar date;
    double seconds;
    String presentDay_Interface;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_add_task, container, false);
        button = view.findViewById(R.id.btn_addtask);
        editText = view.findViewById(R.id.ed_addtask);
        clearr = view.findViewById(R.id.claer_all_btn);
        checkBox = view.findViewById(R.id.checkbox);
        from_date = view.findViewById(R.id.fromdate);
        to_date = view.findViewById(R.id.todate);
        alarm = view.findViewById(R.id.alarm);
        alarm_btn = view.findViewById(R.id.alarm_btn);
        writetask = view.findViewById(R.id.title_of_task_ed);
        ed_titleoftask = view.findViewById(R.id.ed_addtask);
        //recyclerView_= view.findViewById(R.id.rv_dateday);


        onclicks();

        return view;
    }


    private void onclicks() {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fromdate = from_date.getText().toString();
                String todate = to_date.getText().toString();

                String alarmTime = alarm.getText().toString();
                if (checkBox.isChecked()) {
                    tp_checked_min = tp_min - 10;
                    alarm.setText(tp_hour + ":" + tp_checked_min + " " + ampm);
                }
                Calendar calendarCheck_date = Calendar.getInstance();
                int todaysDate = calendarCheck_date.get(Calendar.DATE);
                int todaysMonth = calendarCheck_date.get(Calendar.MONTH);
                int todaysYear = calendarCheck_date.get(Calendar.YEAR);
                int currenHour = calendarCheck_date.get(Calendar.HOUR);
                int currentMinutes = calendarCheck_date.get(Calendar.MINUTE);
                int currentSecond = calendarCheck_date.get(Calendar.SECOND);
                int AMPM = calendarCheck_date.get(Calendar.AM_PM);
                String currentAMPM;
                switch (AMPM) {
                    case 1:
                        currentAMPM = "PM";
                        break;
                    default:
                        currentAMPM = "AM";
                        break;
                }

                SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
                SimpleDateFormat sdf_time = new SimpleDateFormat("hh:mm a");
                sdf2.setTimeZone(TimeZone.getTimeZone("IST"));
                sdf_time.setTimeZone(TimeZone.getTimeZone("IST"));
                Date strDate = null;
                Date endDate = null;
                Date currentCompeleteDate = null;
                Date currentTime = null;
                Date timePicked = null;
                String sd = dateTimePicker_dayofMonth + "-" + dateTimePicker_monthofyear + "-" + dateTimePicker_year;
                String ed = dateTimePicker_dayofMonth2 + "-" + dateTimePicker_monthofyear2 + "-" + dateTimePicker_year2;
                try {

                    strDate = sdf2.parse(sd);
                    endDate = sdf2.parse(ed);

                    timePicked = sdf_time.parse(alarmTime);
                    currentCompeleteDate = sdf2.parse(todaysDate + "-" + todaysMonth + "-" + todaysYear);
                    currentTime = sdf_time.parse(currenHour + ":" + currentMinutes + " " + currentAMPM);


                } catch (ParseException e) {
                    e.printStackTrace();
                }


                if (endDate.after(strDate) && strDate.before(endDate) || endDate.equals(strDate)) {


                    if (currentCompeleteDate.equals(strDate)) {
                        Toast.makeText(getContext(), "Task set for today", Toast.LENGTH_LONG).show();
                        if (currentTime.equals(timePicked)){


                            SimpleDateFormat sdf_ = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
                            String String_future_date = dateTimePicker_dayofMonth + "-" + dateTimePicker_monthofyear + "-" + dateTimePicker_year + " " + "12" + ":" + "00" + ":" + "00";
                            String String_current_date = calendarCheck_date.get(Calendar.DAY_OF_MONTH) + "-" + calendarCheck_date.get(Calendar.MONTH) + "-" + calendarCheck_date.get(Calendar.YEAR) + " " + calendarCheck_date.get(Calendar.HOUR) + ":" + calendarCheck_date.get(Calendar.MINUTE) + ":" + calendarCheck_date.get(Calendar.SECOND);

                            Date Date_future_date = null;
                            Date Date_current_date = null;
                            try {
                                Date_future_date = sdf_.parse(String_future_date);
                                Date_current_date = sdf_.parse(String_current_date);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            long milliseconds = Date_future_date.getTime() - Date_current_date.getTime();
                            seconds = milliseconds / 1000.0;


                            startAlert(seconds);
                        }
                    }

                    Plans plan = new Plans();
                    plan.setName(ed_titleoftask.getText().toString());
                    plan.setFromdate(fromdate);
                    plan.setTodate(todate);
                    plan.setCurrenttime(sdf_time.format(currentTime));
                    plan.setTimepicked(sdf_time.format(timePicked));
                    plan.setAbouttask(writetask.getText().toString());

                    taskListFragment.insertdatabase(BaseFragment.getMainActivity(), plan);
                    recyclerView_.setVisibility(View.VISIBLE);
                    BaseFragment.getMainActivity().pushFragment_forBNV(taskListFragment, presentDay);


                } else {
                    Toast.makeText(getContext(), "Check Date  ", Toast.LENGTH_LONG).show();
                }


            }
        });

        from_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePicker(from_date);
            }
        });
        to_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePicker2(to_date);
            }
        });
        alarm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showTimePicker();
            }

        });

        clearr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writetask.getText().clear();
                ed_titleoftask.getText().clear();
            }
        });


    }

    private void showDateTimePicker2(final TextView to_date) {

        final Calendar currentDate = Calendar.getInstance();
        date = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                date.set(year, monthOfYear, dayOfMonth);


                dateTimePicker_year2 = year;
                dateTimePicker_monthofyear2 = monthOfYear + 1;
                dateTimePicker_dayofMonth2 = dayOfMonth;
                to_date.setText(dateTimePicker_dayofMonth2 + "-" + dateTimePicker_monthofyear2 + "-" + dateTimePicker_year2);

            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), dateSetListener, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();

    }

    private void showTimePicker() {

        c = Calendar.getInstance();
        currentHour = c.get(Calendar.HOUR);
        currentMinute = c.get(Calendar.MINUTE);

        timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                if (hourOfDay >= 12) {
                    ampm = "PM";
                } else {
                    ampm = "AM";
                }


                alarm.setText(hourOfDay + ":" + minute + " " + ampm);
                tp_hour = hourOfDay;
                tp_min = minute;
                tp_ampm = ampm;
            }
        }, currentHour, currentMinute, false);
        timePickerDialog.show();


    }

    private void showDateTimePicker(final TextView from_date) {


        final Calendar currentDate = Calendar.getInstance();
        date = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                date.set(year, monthOfYear, dayOfMonth);


                dateTimePicker_year = year;
                dateTimePicker_monthofyear = monthOfYear + 1;
                dateTimePicker_dayofMonth = dayOfMonth;
                from_date.setText(dateTimePicker_dayofMonth + "-" + dateTimePicker_monthofyear + "-" + dateTimePicker_year);


            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), dateSetListener, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();

    }

    private void startAlert(double seconds) {

        //((5Hours X 60 minutes) + 30minutes)60sec = total seconds   20 44

      //  int startAlert_hh = tp_hour * 60;
      //  int startAlert_mm = startAlert_hh + tp_min;
      //  int startAlert_ss = startAlert_mm * 60;

        int i = Integer.parseInt(String.valueOf(seconds));


        myBroadcastReceiver = new MyBroadcastReceiver(getContext(), writetask.getText().toString(), editText.getText().toString());


        Intent intent = new Intent(getContext(), MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                getContext(), 234324243, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                + (i * 1000), pendingIntent);
        Toast.makeText(getContext(), "Alarm set in " + i + " seconds", Toast.LENGTH_LONG).show();


    }


    public void setRV(RecyclerView recyclerView) {
        this.recyclerView_ = recyclerView;
    }


    @Override
    public void data(String presentday, String AddlistToolbar, String ProfileToolbar, Context context, int count) {
        this.presentDay_Interface=presentday;
        BaseFragment.getMainActivity().settoolbarText(AddlistToolbar,count);

    }
}


