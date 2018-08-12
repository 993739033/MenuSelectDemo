package project.scode.com.menuselectdemo;

import android.animation.AnimatorSet;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_menu_1, btn_menu_2, btn_menu_3;
    FrameLayout layout_container;
    int beSelectFragment = -1;
    int containerH = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initView();

        initListener();
    }

    private void initListener() {
        btn_menu_1.setOnClickListener(this);
        btn_menu_2.setOnClickListener(this);
        btn_menu_3.setOnClickListener(this);

    }

    private void initView() {
        btn_menu_1 = findViewById(R.id.btn_menu_1);
        btn_menu_2 = findViewById(R.id.btn_menu_2);
        btn_menu_3 = findViewById(R.id.btn_menu_3);

        layout_container = findViewById(R.id.layout_container);
        addFragments();

    }

    public void addFragments() {
        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction transaction = fm.beginTransaction();
        Fragment fragment1 = FragmentFractory.getMeunFragment("ml");
        Fragment fragment2 = FragmentFractory.getMeunFragment("cd");
        Fragment fragment3 = FragmentFractory.getMeunFragment("fl");
        transaction.add(R.id.layout_container, fragment1, R.id.btn_menu_1 + "");
        transaction.add(R.id.layout_container, fragment2, R.id.btn_menu_2 + "");
        transaction.add(R.id.layout_container, fragment3, R.id.btn_menu_3 + "");

        transaction.hide(fragment1);
        transaction.hide(fragment2);
        transaction.hide(fragment3);
        transaction.commit();

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

    }

    @Override
    public void onClick(View view) {
        showItem(view.getId() + "");
    }


    public void showItem(String id) {
        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction transaction = fm.beginTransaction();
        Fragment fragment = fm.findFragmentByTag(id);
        hideAll();
        if (layout_container.getVisibility() == View.GONE || beSelectFragment != Integer.parseInt(id)) {
            beSelectFragment = Integer.parseInt(id);
            transaction.show(fragment);
            layout_container.setVisibility(View.VISIBLE);
            showAnim(layout_container,fragment);
        } else {
            hideAnim();
        }
        transaction.commit();
    }

    private void hideAll() {
        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction transaction = fm.beginTransaction();
        for (BaseFragment fragment : FragmentFractory.fragments.values()) {
            if (!fragment.isHidden()) {
                transaction.hide(fragment);
            }
        }
        transaction.commit();
    }

    private void showAnim(final View v, final Fragment fragment) {

        AnimationSet animset = new AnimationSet(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.2f, 1f);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                v.setVisibility(View.VISIBLE);
                ((BaseFragment)fragment).showTemp();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        TranslateAnimation translateAnimation = new TranslateAnimation(0,0,-DimenUtils.dip2px(containerH)-20,0);
        animset.addAnimation(alphaAnimation);
        animset.addAnimation(translateAnimation);
        animset.setDuration(200);
        v.startAnimation(animset);

    }



    public void hideAnim() {
        beSelectFragment = -1;
        AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0.2f);
        alphaAnimation.setDuration(300);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                layout_container.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        layout_container.startAnimation(alphaAnimation);

    }

}
