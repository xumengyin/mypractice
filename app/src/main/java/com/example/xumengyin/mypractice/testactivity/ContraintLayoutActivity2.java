package com.example.xumengyin.mypractice.testactivity;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.TextView;


import com.example.xumengyin.mypractice.R;

/**
 * Created by Administrator on 2017/12/27.
 */

public class ContraintLayoutActivity2 extends BaseActivity {


    View view1, view2;
    TextView textview3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view1 = findViewById(R.id.view2);
        view2 = findViewById(R.id.viewholder);
        textview3 = findViewById(R.id.text3);
        createAni(view1, 4000).start();
        createAni(view2, 6000).start();
        textview3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              ConstraintLayout.LayoutParams layoutParams= (ConstraintLayout.LayoutParams) textview3.getLayoutParams();
              layoutParams.leftMargin=layoutParams.leftMargin+100;
              layoutParams.rightMargin=layoutParams.rightMargin+100;
              textview3.setLayoutParams(layoutParams);
            }
        });

    }

    @Override
    protected int getcontentView() {
        return R.layout.contraint_test;
    }

    private ValueAnimator createAni(final View view, long time) {
        ValueAnimator ani = ValueAnimator.ofFloat(0, 359);
        ani.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) view.getLayoutParams();
                layoutParams.circleAngle = (float) valueAnimator.getAnimatedValue();
                view.setLayoutParams(layoutParams);
            }
        });
        ani.setDuration(time);
        ani.setRepeatCount(-1);
        ani.setRepeatMode(ValueAnimator.RESTART);
        ani.setInterpolator(new AccelerateInterpolator());
        return ani;
    }

}
