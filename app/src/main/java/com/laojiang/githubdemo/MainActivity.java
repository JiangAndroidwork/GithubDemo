package com.laojiang.githubdemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "mainactivity";
    private TextView ivCha;
    private GridView gvButtons;
    private GridViewAdapter adapter;
    private boolean downs =true;
    private LinearLayout llParents;
    private int screenHeight;
    private LinearLayout llBelow;
    private boolean ups = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screenHeight = ScreenUtils.getScreenHeight(this);
        ivCha = (TextView) findViewById(R.id.tv_qiu);
        llParents = (LinearLayout) findViewById(R.id.ll_parents);
        gvButtons = (GridView) findViewById(R.id.gv_button);
        llBelow = (LinearLayout) findViewById(R.id.ll_below);
        String[] btStrs = new String[15];
        for (int i =0;i<15;i++){
            btStrs[i] = "我是按钮"+(i);
        }
        adapter = new GridViewAdapter(this,btStrs);
        gvButtons.setAdapter(adapter);
        gvButtons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        initAnimation();
                        break;
                    case 1:
                        initAnimationSet();
                        break;
                    case 2:
                        if (downs) {
                            init2();
                            downs = false;
                        }else {
                            init3();
                            downs =true;
                        }
                        break;
                    case 3:
                        if (ups){
                            init4();
                            ups = false;
                        }else {
                            init5();
                            ups = true;
                        }

                        break;
                }
            }
        });
    }
    private void init5() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(llBelow,"translationY",0,100);
        animator.setDuration(500);
        animator.start();
    }
//向上
    private void init4() {

        ObjectAnimator animator = ObjectAnimator.ofFloat(llBelow,"translationY",100,0);
        animator.setDuration(500);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                if (llBelow.getVisibility()==View.GONE)
                    llBelow.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animator.start();
    }

    private void init2() {


        Log.d(TAG,"我是高度-=="+screenHeight);
        ObjectAnimator an1 = ObjectAnimator.ofFloat(llParents,"xx",200f,screenHeight);
        an1.setDuration(500);
       an1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
           @Override
           public void onAnimationUpdate(ValueAnimator valueAnimator) {
               float animatedValue = (float) valueAnimator.getAnimatedValue();
               RelativeLayout.LayoutParams ll = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
               ll.height = (int) animatedValue;
               llParents.setLayoutParams(ll);
           }
       });
        an1.start();
    }
    private void init3() {
        ObjectAnimator an1 = ObjectAnimator.ofFloat(llParents,"xx",screenHeight,200f);
        an1.setDuration(500);
        an1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedValue = (float) valueAnimator.getAnimatedValue();
                RelativeLayout.LayoutParams ll = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                ll.height = (int) animatedValue;
                llParents.setLayoutParams(ll);
            }
        });
        an1.start();
    }

    private void initAnimationSet() {
        float x = ivCha.getX();
        ObjectAnimator an1 = ObjectAnimator.ofFloat(ivCha,"scaleX",1f,2f);
        ObjectAnimator an2 = ObjectAnimator.ofFloat(ivCha,"scaleY",1f,2f);
            ObjectAnimator an3 = ObjectAnimator.ofFloat(ivCha,"x",x,0f,x);
        ObjectAnimator an5 = ObjectAnimator.ofFloat(ivCha,"scaleX",2f,1f);
        ObjectAnimator an6 = ObjectAnimator.ofFloat(ivCha,"scaleY",2f,1f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(1000);
        animatorSet.playTogether(an1,an2,an3);
        animatorSet.playTogether(an5,an6);

        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
//                ViewGroup parent = (ViewGroup) ivCha.getParent();
//                if (parent!=null){
//                    parent.removeView(ivCha);
//                }
            }
        });
        animatorSet.start();
    }

    private void initAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(ivCha,"zhy",1.0f,0.0f)
                .setDuration(500);

        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedValue = (float) valueAnimator.getAnimatedValue();
                ivCha.setAlpha(animatedValue);
                ivCha.setScaleX(animatedValue);
                ivCha.setScaleY(animatedValue);
            }

        });

    }
}
