package com.example.xumengyin.mypractice.testactivity;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.transition.TransitionManager;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.example.xumengyin.mypractice.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/27.
 */

public class ContraintLayoutActivity3 extends BaseActivity {

    @BindView(R.id.text1)
    View view1;
    @BindView(R.id.text2)
    View view2;
    @BindView(R.id.text3)
    View view3;
    @BindView(R.id.apply_btn)
    Button apply;
    @BindView(R.id.reset_btn)
    Button reset;

    ConstraintSet constraintSet1 = new ConstraintSet();
    ConstraintSet constraintSet2 = new ConstraintSet();
    @BindView(R.id.linearLayout)
    ConstraintLayout vContraintlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        constraintSet1.clone(vContraintlayout);
        constraintSet2.clone(vContraintlayout);

    }

    @OnClick(R.id.reset_btn)
    public void reset(View view) {
        constraintSet2.applyTo(vContraintlayout);

    }

    @OnClick(R.id.apply_btn)
    public void apply(View view) {
        TransitionManager.beginDelayedTransition(vContraintlayout);
        pratiace5();
    }

    private void pratiace1() {
        constraintSet1.setMargin(R.id.text1,ConstraintSet.TOP,200);
        constraintSet1.applyTo(vContraintlayout);
    }
    private void pratiace2() {
        constraintSet1.centerHorizontally(R.id.text1,R.id.linearLayout);
        constraintSet1.centerHorizontally(R.id.text2,R.id.linearLayout);
        constraintSet1.centerHorizontally(R.id.text3,R.id.linearLayout);
        constraintSet1.applyTo(vContraintlayout);
    }
    private void pratiace3() {
        constraintSet1.centerHorizontally(R.id.text1,R.id.linearLayout);
        constraintSet1.centerVertically(R.id.text1,R.id.linearLayout);
        constraintSet1.applyTo(vContraintlayout);
    }
    private void pratiace4() {
        constraintSet1.constrainWidth(R.id.text1,600);
        constraintSet1.constrainWidth(R.id.text2,600);
        constraintSet1.constrainWidth(R.id.text3,600);
        constraintSet1.applyTo(vContraintlayout);
    }
    private void pratiace5() {
        constraintSet1.clear(R.id.text1);
        constraintSet1.connect(R.id.text1,ConstraintSet.LEFT,R.id.linearLayout,ConstraintSet.LEFT,0);
        constraintSet1.connect(R.id.text1,ConstraintSet.TOP,R.id.linearLayout,ConstraintSet.TOP,0);
        constraintSet1.connect(R.id.text1,ConstraintSet.BOTTOM,R.id.linearLayout,ConstraintSet.BOTTOM,0);
        constraintSet1.connect(R.id.text1,ConstraintSet.RIGHT,R.id.linearLayout,ConstraintSet.RIGHT,0);
        constraintSet1.applyTo(vContraintlayout);
    }

    @Override
    protected int getcontentView() {
        return R.layout.contraint_test2;
    }


}
