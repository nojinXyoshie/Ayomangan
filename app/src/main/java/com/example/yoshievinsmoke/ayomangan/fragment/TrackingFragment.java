package com.example.yoshievinsmoke.ayomangan.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.yoshievinsmoke.ayomangan.R;

/**
 * Created by yoshievinsmoke on 5/2/18.
 */

public class TrackingFragment extends Fragment {
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Tracking");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View x = inflater.inflate(R.layout.activity_tracking, container, false);
        Button tracking = (Button) x.findViewById(R.id.tracking);
        tracking.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Uri gmmIntentUri = Uri.parse("geo:-6.407835, 108.282516");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);

            }
        });

        return x;
    }
}
