package com.example.sample;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.google.android.material.card.MaterialCardView;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;


public class HomeFragment extends Fragment {


    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";




    public HomeFragment() {

    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    ViewFlipper flip;
    LinearLayout dotsLayout;
    ImageView[] dots = new ImageView[3];
    MaterialCardView[] cards = new MaterialCardView[6];
    TextView[] texts =new TextView[6];
    int[] inactive_images = {R.drawable.gray_regular_service,R.drawable.gray_airbrush_paint,R.drawable.gray_denting,
    R.drawable.gray_car_scanning,R.drawable.gray_ac_service,R.drawable.gray_car_wash};

    int[] active_images = {R.drawable.regular_service,R.drawable.airbrush_paint,R.drawable.denting,
            R.drawable.car_scanning,R.drawable.ac_service,R.drawable.car_wash};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String mParam1;
        String mParam2;
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        cards[0] = view.findViewById(R.id.card1);
        cards[1] = view.findViewById(R.id.card2);
        cards[2] = view.findViewById(R.id.card3);
        cards[3] = view.findViewById(R.id.card4);
        cards[4] = view.findViewById(R.id.card5);
        cards[5] = view.findViewById(R.id.card6);
        flip = view.findViewById(R.id.carousel);
        flip.setAutoStart(true);
        flip.setFlipInterval(4000);
        flip.setInAnimation(getContext(),R.anim.slide_in);
        flip.setOutAnimation(getContext(),R.anim.slide_out);
        dotsLayout = view.findViewById(R.id.dots_layout);
        setActiveDot(flip.getDisplayedChild());
        flip.getInAnimation().setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                setActiveDot(flip.getDisplayedChild());
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        for(int i=0;i<=5;i++){
            int finalI = i;
            cards[i].setOnClickListener(v -> {
                cards[finalI].toggle();
                if(cards[finalI].isChecked()){
                    cards[finalI].setStrokeColor(ResourcesCompat.getColor(getResources(),R.color.blue_700,null));
                    cards[finalI].setStrokeWidth(2);
                    texts[finalI] = (TextView) cards[finalI].getChildAt(0);
                    texts[finalI].setCompoundDrawablesWithIntrinsicBounds(0,active_images[finalI],0,0);
                }else{
                    cards[finalI].setStrokeColor(null);
                    cards[finalI].setStrokeWidth(0);
                    texts[finalI] = (TextView) cards[finalI].getChildAt(0);
                    texts[finalI].setCompoundDrawablesWithIntrinsicBounds(0,inactive_images[finalI],0,0);
                }
            });
        }
    }
    void setActiveDot(int position){
        if(dotsLayout.getChildCount() > 0){
            dotsLayout.removeAllViews();
        }
        for(int i=0;i<=2;i++){
            dots[i] = new ImageView(getContext());
            if(i==position){
                dots[i].setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.active_dot));
            }else{
                dots[i].setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.inactive_dot));
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(12,0,12,0);
            dotsLayout.addView(dots[i],layoutParams);
        }
    }
}