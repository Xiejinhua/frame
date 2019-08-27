package cn.pdc.pos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pdc.pos.util.ActivityUtil;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.hello_tv)
    TextView helloTv;
    private Activity activity;

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityUtil.init(this);
        ActivityUtil.initMainActivity(this);
        activity = this;
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ActivityUtil.init(this);
        ActivityUtil.initMainActivity(this);
    }

    @OnClick(R.id.hello_tv)
    public void onViewClicked() {
        Toast.makeText(activity, "点击了...", Toast.LENGTH_SHORT).show();
    }
}
