package com.jingdong.app.mall.widget.editWidget;

import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.app.mall.widget.model.CardConfigVO;
import com.jingdong.app.mall.widget.vivo.VIVOMultiFunctionWidget;
import com.jingdong.app.mall.widget.xiaomi.MIUIMultiFunctionWidget;
import com.jingdong.jdsdk.JdSdk;
import java.util.List;

/* loaded from: classes4.dex */
public class WidgetEditSettingActivity extends MyActivity implements View.OnClickListener {

    /* renamed from: g  reason: collision with root package name */
    private String f12046g;

    /* renamed from: h  reason: collision with root package name */
    private TextView f12047h;

    /* renamed from: i  reason: collision with root package name */
    private TextView f12048i;

    /* renamed from: j  reason: collision with root package name */
    private TextView f12049j;

    /* renamed from: k  reason: collision with root package name */
    private ImageView f12050k;

    /* renamed from: l  reason: collision with root package name */
    private TextView f12051l;

    /* renamed from: m  reason: collision with root package name */
    private RelativeLayout f12052m;

    /* renamed from: n  reason: collision with root package name */
    private RelativeLayout f12053n;
    private List<CardConfigVO.CardConfig.NegativeOneCardChannelVO> o;
    private int p;
    private int q;
    private String r;
    private String s;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, @Nullable Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i3 == 1) {
            String stringExtra = intent.getStringExtra("text");
            this.f12046g = stringExtra;
            if (stringExtra != null) {
                this.f12051l.setText(stringExtra);
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addtional_item_arrow0 /* 2131689715 */:
            case R.id.tv_finish /* 2131694571 */:
                Intent intent = new Intent();
                SharedPreferences sharedPreferences = null;
                if (TextUtils.equals(this.s, "xiaomi")) {
                    sharedPreferences = JdSdk.getInstance().getApplication().getSharedPreferences("chanelDataXIAOMI", 0);
                } else if (TextUtils.equals(this.s, "vivo")) {
                    sharedPreferences = JdSdk.getInstance().getApplication().getSharedPreferences("chanelDataVIVO", 0);
                }
                int i2 = sharedPreferences.getInt("settingSelect1", 0);
                int i3 = sharedPreferences.getInt("settingSelect2", 1);
                Bundle bundle = new Bundle();
                bundle.putInt("settingSelect1", i2);
                bundle.putInt("settingSelect2", i3);
                intent.putExtras(bundle);
                if (TextUtils.equals("xiaomi", this.s)) {
                    intent.setComponent(new ComponentName(this, MIUIMultiFunctionWidget.class));
                } else if (TextUtils.equals("vivo", this.s)) {
                    intent.setComponent(new ComponentName(this, VIVOMultiFunctionWidget.class));
                }
                intent.setAction("com.jingdong.app.mall.widget.editWidget.WIDGETSETTING_REFRESH");
                sendBroadcast(intent);
                Intent intent2 = new Intent();
                intent2.setAction("android.intent.action.MAIN");
                intent2.addCategory("android.intent.category.HOME");
                startActivity(intent2);
                finish();
                return;
            case R.id.rl_setting1 /* 2131694438 */:
                Intent intent3 = new Intent();
                Bundle bundle2 = new Bundle();
                bundle2.putInt("moreSettingSelect1", 1);
                bundle2.putInt("moreSettingSelect2", 0);
                bundle2.putString("functionSettingList", this.r);
                bundle2.putString("fromBrand", this.s);
                intent3.putExtras(bundle2);
                if (view instanceof RelativeLayout) {
                    View childAt = ((RelativeLayout) view).getChildAt(0);
                    if (childAt instanceof TextView) {
                        this.f12051l = (TextView) childAt;
                    }
                }
                intent3.setClass(this, WidgetEditMoreSettingActivity.class);
                startActivityForResult(intent3, 1);
                return;
            case R.id.rl_setting2 /* 2131694439 */:
                Intent intent4 = new Intent();
                Bundle bundle3 = new Bundle();
                bundle3.putInt("moreSettingSelect1", 0);
                bundle3.putInt("moreSettingSelect2", 1);
                bundle3.putString("functionSettingList", this.r);
                bundle3.putString("fromBrand", this.s);
                intent4.putExtras(bundle3);
                if (view instanceof RelativeLayout) {
                    View childAt2 = ((RelativeLayout) view).getChildAt(0);
                    if (childAt2 instanceof TextView) {
                        this.f12051l = (TextView) childAt2;
                    }
                }
                intent4.setClass(this, WidgetEditMoreSettingActivity.class);
                startActivityForResult(intent4, 1);
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        int size;
        int i2;
        super.onCreate(bundle);
        setContentView(R.layout.activity_setting);
        this.f12047h = (TextView) findViewById(R.id.tv_setting1);
        this.f12048i = (TextView) findViewById(R.id.tv_setting2);
        this.f12052m = (RelativeLayout) findViewById(R.id.rl_setting1);
        this.f12053n = (RelativeLayout) findViewById(R.id.rl_setting2);
        this.f12050k = (ImageView) findViewById(R.id.addtional_item_arrow0);
        this.f12049j = (TextView) findViewById(R.id.tv_finish);
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        Bundle extras = intent.getExtras();
        if (extras != null) {
            this.p = extras.getInt("select1");
            this.q = extras.getInt("select2");
            this.r = extras.getString("functionList");
            this.s = extras.getString("fromBrand");
            if (!TextUtils.isEmpty(this.r)) {
                this.o = (List) new Gson().fromJson(this.r, new TypeToken<List<CardConfigVO.CardConfig.NegativeOneCardChannelVO>>(this) { // from class: com.jingdong.app.mall.widget.editWidget.WidgetEditSettingActivity.1
                }.getType());
                SharedPreferences sharedPreferences = null;
                if (TextUtils.equals(this.s, "xiaomi")) {
                    sharedPreferences = JdSdk.getInstance().getApplication().getSharedPreferences("chanelDataXIAOMI", 0);
                } else if (TextUtils.equals(this.s, "vivo")) {
                    sharedPreferences = JdSdk.getInstance().getApplication().getSharedPreferences("chanelDataVIVO", 0);
                }
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putString("functionSettingList", this.r);
                edit.putInt("settingSelect1", this.p);
                edit.putInt("settingSelect2", this.q);
                edit.apply();
                List<CardConfigVO.CardConfig.NegativeOneCardChannelVO> list = this.o;
                if (list != null && !list.isEmpty() && (size = this.o.size()) > (i2 = this.p) && size > this.q) {
                    if (this.o.get(i2) != null) {
                        this.f12047h.setText(this.o.get(this.p).title);
                    }
                    if (this.o.get(this.q) != null) {
                        this.f12048i.setText(this.o.get(this.q).title);
                    }
                }
            }
        }
        this.f12052m.setOnClickListener(this);
        this.f12053n.setOnClickListener(this);
        this.f12050k.setOnClickListener(this);
        this.f12049j.setOnClickListener(this);
    }
}
