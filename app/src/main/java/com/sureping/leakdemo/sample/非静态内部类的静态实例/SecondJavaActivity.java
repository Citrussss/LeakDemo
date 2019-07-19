package com.sureping.leakdemo.sample.非静态内部类的静态实例;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import com.sureping.leakdemo.R;
import com.sureping.leakdemo.base.BaseActivity;

public class SecondJavaActivity extends BaseActivity {
    private static Object inner;
    private Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createInnerClass();
                finish();
            }
        });
    }


    private void createInnerClass(){
        class InnerClass{

        }
        test(new Integer[]{1,2,3});
        inner = new InnerClass();
    }
    private void test(Integer... a){

    }
}
