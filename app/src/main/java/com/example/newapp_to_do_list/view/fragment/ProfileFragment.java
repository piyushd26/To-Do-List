package com.example.newapp_to_do_list.view.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.newapp_to_do_list.CircleTransform;
import com.example.newapp_to_do_list.R;
import com.example.newapp_to_do_list.view.DataInterface;
import com.squareup.picasso.Picasso;


public class ProfileFragment extends Fragment implements DataInterface {

    View view;
    CardView back;
    ImageView profile_image;
    TextView profile_name;

    public ProfileFragment() {

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         view = inflater.inflate(R.layout.fragment_profile, container, false);

         back=view.findViewById(R.id.profile_imageview_forpallete);
         profile_image=view.findViewById(R.id.profile_profile_images);
         profile_name=view.findViewById(R.id.profile_name);

        Picasso.with(getContext()).load(R.mipmap.tom).transform(new CircleTransform()).into(profile_image);






        return view;
    }


    @Override
    public void data(String presentday, String AddlistToolbar, String ProfileToolbar, Context context, int count) {
        BaseFragment.getMainActivity().settoolbarText(ProfileToolbar,count);

    }
}