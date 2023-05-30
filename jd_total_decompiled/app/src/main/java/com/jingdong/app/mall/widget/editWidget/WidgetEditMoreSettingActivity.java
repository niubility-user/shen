package com.jingdong.app.mall.widget.editWidget;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.app.mall.widget.model.CardConfigVO;
import com.jingdong.jdsdk.JdSdk;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class WidgetEditMoreSettingActivity extends MyActivity {

    /* renamed from: g  reason: collision with root package name */
    private ListView f12036g;

    /* renamed from: h  reason: collision with root package name */
    private ImageView f12037h;

    /* renamed from: i  reason: collision with root package name */
    private List<CardConfigVO.CardConfig.NegativeOneCardChannelVO> f12038i;

    /* renamed from: j  reason: collision with root package name */
    private List<String> f12039j = new ArrayList();

    /* renamed from: k  reason: collision with root package name */
    private int f12040k;

    /* renamed from: l  reason: collision with root package name */
    private int f12041l;

    /* renamed from: m  reason: collision with root package name */
    private String f12042m;

    /* renamed from: n  reason: collision with root package name */
    private String f12043n;

    /* loaded from: classes4.dex */
    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            WidgetEditMoreSettingActivity.this.setResult(2, WidgetEditMoreSettingActivity.this.getIntent());
            WidgetEditMoreSettingActivity.this.finish();
        }
    }

    /* loaded from: classes4.dex */
    class b implements AdapterView.OnItemClickListener {
        b() {
        }

        /* JADX WARN: Removed duplicated region for block: B:32:0x00aa  */
        /* JADX WARN: Removed duplicated region for block: B:33:0x00b0  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x00c8 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:39:0x00c9  */
        @Override // android.widget.AdapterView.OnItemClickListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
            SharedPreferences sharedPreferences;
            String str;
            Intent intent;
            int i3 = 0;
            if (TextUtils.equals(WidgetEditMoreSettingActivity.this.f12043n, "xiaomi")) {
                sharedPreferences = JdSdk.getInstance().getApplication().getSharedPreferences("chanelDataXIAOMI", 0);
            } else {
                sharedPreferences = TextUtils.equals(WidgetEditMoreSettingActivity.this.f12043n, "vivo") ? JdSdk.getInstance().getApplication().getSharedPreferences("chanelDataVIVO", 0) : null;
            }
            SharedPreferences.Editor edit = sharedPreferences.edit();
            if (view instanceof RelativeLayout) {
                View childAt = ((RelativeLayout) view).getChildAt(0);
                if (childAt instanceof TextView) {
                    str = (String) ((TextView) childAt).getText();
                    if (WidgetEditMoreSettingActivity.this.f12038i == null || WidgetEditMoreSettingActivity.this.f12038i.isEmpty()) {
                        return;
                    }
                    int i4 = 0;
                    while (i3 < WidgetEditMoreSettingActivity.this.f12038i.size()) {
                        if (WidgetEditMoreSettingActivity.this.f12038i.get(i3) != null && TextUtils.equals(str, ((CardConfigVO.CardConfig.NegativeOneCardChannelVO) WidgetEditMoreSettingActivity.this.f12038i.get(i3)).title)) {
                            i4 = i3;
                        }
                        i3++;
                    }
                    i3 = i4;
                    if (WidgetEditMoreSettingActivity.this.f12040k == 1) {
                        if (WidgetEditMoreSettingActivity.this.f12041l == 1) {
                            edit.putInt("settingSelect2", i3);
                        }
                    } else {
                        edit.putInt("settingSelect1", i3);
                    }
                    edit.apply();
                    intent = WidgetEditMoreSettingActivity.this.getIntent();
                    if (intent != null) {
                        return;
                    }
                    intent.putExtra("text", str);
                    WidgetEditMoreSettingActivity.this.setResult(1, intent);
                    WidgetEditMoreSettingActivity.this.finish();
                    return;
                }
            }
            str = "";
            if (WidgetEditMoreSettingActivity.this.f12040k == 1) {
            }
            edit.apply();
            intent = WidgetEditMoreSettingActivity.this.getIntent();
            if (intent != null) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_more_setting);
        this.f12036g = (ListView) findViewById(R.id.lv_moreSetting);
        ImageView imageView = (ImageView) findViewById(R.id.addtional_item_arrow0);
        this.f12037h = imageView;
        imageView.setOnClickListener(new a());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        Bundle extras = intent.getExtras();
        if (extras != null) {
            this.f12040k = extras.getInt("moreSettingSelect1");
            this.f12041l = extras.getInt("moreSettingSelect2");
            String string = extras.getString("fromBrand");
            this.f12043n = string;
            SharedPreferences sharedPreferences = null;
            int i2 = 0;
            if (TextUtils.equals(string, "xiaomi")) {
                sharedPreferences = JdSdk.getInstance().getApplication().getSharedPreferences("chanelDataXIAOMI", 0);
            } else if (TextUtils.equals(this.f12043n, "vivo")) {
                sharedPreferences = JdSdk.getInstance().getApplication().getSharedPreferences("chanelDataVIVO", 0);
            }
            int i3 = sharedPreferences.getInt("settingSelect1", 0);
            int i4 = sharedPreferences.getInt("settingSelect2", 1);
            String string2 = extras.getString("functionSettingList");
            this.f12042m = string2;
            if (!TextUtils.isEmpty(string2)) {
                this.f12038i = (List) new Gson().fromJson(this.f12042m, new TypeToken<List<CardConfigVO.CardConfig.NegativeOneCardChannelVO>>(this) { // from class: com.jingdong.app.mall.widget.editWidget.WidgetEditMoreSettingActivity.2
                }.getType());
            }
            List<CardConfigVO.CardConfig.NegativeOneCardChannelVO> list = this.f12038i;
            if (list != null && !list.isEmpty() && this.f12040k == 1) {
                while (i2 < this.f12038i.size()) {
                    CardConfigVO.CardConfig.NegativeOneCardChannelVO negativeOneCardChannelVO = this.f12038i.get(i2);
                    if (negativeOneCardChannelVO != null && i2 != i4) {
                        this.f12039j.add(negativeOneCardChannelVO.title);
                    }
                    i2++;
                }
            } else {
                List<CardConfigVO.CardConfig.NegativeOneCardChannelVO> list2 = this.f12038i;
                if (list2 != null && !list2.isEmpty() && this.f12041l == 1) {
                    while (i2 < this.f12038i.size()) {
                        CardConfigVO.CardConfig.NegativeOneCardChannelVO negativeOneCardChannelVO2 = this.f12038i.get(i2);
                        if (negativeOneCardChannelVO2 != null && i2 != i3) {
                            this.f12039j.add(negativeOneCardChannelVO2.title);
                        }
                        i2++;
                    }
                }
            }
        }
        this.f12036g.setAdapter((ListAdapter) new com.jingdong.app.mall.widget.editWidget.a(this.f12039j));
        this.f12036g.setOnItemClickListener(new b());
    }
}
