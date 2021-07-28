package com.cse.shoppingapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import android.animation.ValueAnimator;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class WelcomeActivity extends AppCompatActivity {
    private LinearLayout btn_viewHolder;
    private ImageView icon;
    private Runnable onAnimationEnd = new Runnable() {
        @Override
        public void run() {
            btn_viewHolder.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE |
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        btn_viewHolder = findViewById(R.id.btn_vh);
        icon = findViewById(R.id.icon);

        animateLayoutChanges();

    }

    private void animateLayoutChanges() {
        icon.animate().scaleX(.67f).scaleY(.67f).translationY(-180f)
                .setDuration(600).setInterpolator(new AccelerateDecelerateInterpolator()).withEndAction(onAnimationEnd);

    }
}