package com.toong.androidcustomspecificprogressbar;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by PhanVanLinh on 30/08/2017.
 * phanvanlinh.94vn@gmail.com
 */

public class SpecificProgressBar extends RelativeLayout {
    //    private ImageView imgImage;
    //    private TextView tvText;

    private RelativeLayout loRoot;
    private View loProgress;

    public SpecificProgressBar(Context context) {
        this(context, null);
    }

    public SpecificProgressBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpecificProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs);
        getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
    }

    private void init(AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(R.layout.custom_layout, this, true);

        loRoot = findViewById(R.id.layout_root);
        loProgress = findViewById(R.id.layout_progress);
    }

    public void setProgress(final int progress) {
        loProgress.getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        loProgress.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                        loProgress.getLayoutParams().width = getWidth() * progress / 100;
                    }
                });
    }

    private void addAView(final View view, final int progress) {
        loRoot.addView(view);
        view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                view.getViewTreeObserver().removeOnPreDrawListener(this);

                MarginLayoutParams layoutParams = (MarginLayoutParams) view.getLayoutParams();
                layoutParams.leftMargin = getWidth() * progress / 100 - view.getWidth() / 2;

                view.setLayoutParams(layoutParams);
                return false;
            }
        });
    }

    public void addGold(int progress, int value) {
        View child = LayoutInflater.from(getContext()).inflate(R.layout.item_gold, null);
        ((TextView) child.findViewById(R.id.text_gold)).setText("+" + value);
        addAView(child, progress);
    }

    public void addWalkingPoint(int progress, int value) {
        View child = LayoutInflater.from(getContext()).inflate(R.layout.item_walking_point, null);
        ((TextView) child.findViewById(R.id.text_walking_point)).setText("+" + value);
        addAView(child, progress);
    }
}
