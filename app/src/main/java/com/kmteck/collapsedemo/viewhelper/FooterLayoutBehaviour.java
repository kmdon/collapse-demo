package com.kmteck.collapsedemo.viewhelper;

import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

public class FooterLayoutBehaviour extends CoordinatorLayout.Behavior<LinearLayout> {
    private int height;


    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, LinearLayout child, int layoutDirection) {
        height = (int) child.getY();
        Log.d("Footer", "Child : " + height);
        return super.onLayoutChild(parent, child, layoutDirection);
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull LinearLayout child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull LinearLayout child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @ViewCompat.NestedScrollType int type) {
        if (dyConsumed > 0) {
            slideDown(child);
        } else if (dyConsumed < 0) {
            slideUp(child);
        }
    }

    private void slideUp(LinearLayout child) {
        child.clearAnimation();
        child.animate().translationY(0).setDuration(200);
    }

    private void slideDown(LinearLayout child) {
        child.clearAnimation();

        // you can change the delay 
        child.animate().translationY(height).setDuration(200);
    }
}
