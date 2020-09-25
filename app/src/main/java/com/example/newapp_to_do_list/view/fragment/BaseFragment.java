package com.example.newapp_to_do_list.view.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.newapp_to_do_list.common.Constants;
import com.example.newapp_to_do_list.view.activity.MainActivity;

public class BaseFragment extends Fragment {

    static MainActivity mainActivity;


    public static MainActivity getMainActivity() {
        return mainActivity;
    }

    public static void setMainActivity(MainActivity mainActivity) {
        BaseFragment.mainActivity = mainActivity;
    }


    public SharedPreferences getsharedPreferences() {

        return mainActivity.getSharedPreferences(Constants.SharedPreference, Context.MODE_PRIVATE);
    }
}
