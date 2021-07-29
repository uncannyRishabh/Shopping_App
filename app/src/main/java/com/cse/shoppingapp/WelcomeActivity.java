package com.cse.shoppingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

@SuppressWarnings({"FieldMayBeFinal","FieldCanBeLocal"})
public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout btn_viewHolder;
    private ImageView icon;
    private Button login,signup,continue_as_guest;

    private Runnable onAnimationEnd = new Runnable() {
        @Override
        public void run() {
            btn_viewHolder.setVisibility(View.VISIBLE);
        }
    };

    public WelcomeActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_ShoppingApp);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE |
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        btn_viewHolder = findViewById(R.id.btn_vh);
        icon = findViewById(R.id.icon);
        login = findViewById(R.id.w_btn_1);
        login.setOnClickListener(this);
        signup = findViewById(R.id.w_btn_2);
        signup.setOnClickListener(this);
        continue_as_guest = findViewById(R.id.w_btn_3);
        continue_as_guest.setOnClickListener(this);

        animateLayoutChanges();

    }

    private void animateLayoutChanges() {
        icon.animate().scaleX(.67f).scaleY(.67f).translationY(-200f)
                .setDuration(600).setInterpolator(new AccelerateDecelerateInterpolator())
                .withEndAction(onAnimationEnd);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.w_btn_1:
                Intent i1 = new Intent(this,LoginActivity.class);
                ActivityOptions options = ActivityOptions.
                        makeSceneTransitionAnimation(this, Pair.create(icon,"sharedTrans_1")
                                , Pair.create(login,"sharedTrans_2"));
                startActivity(i1,options.toBundle());
                break;
            case R.id.w_btn_2:
                Intent i2 = new Intent(this,RegisterActivity.class);
                ActivityOptions options_ = ActivityOptions.
                        makeSceneTransitionAnimation(this, Pair.create(icon,"sharedTrans_1")
                                , Pair.create(signup,"sharedTrans_2"));
                startActivity(i2,options_.toBundle());
                break;
            case R.id.w_btn_3:
                Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}