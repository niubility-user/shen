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
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onItemClick(android.widget.AdapterView<?> r1, android.view.View r2, int r3, long r4) {
            /*
                r0 = this;
                com.jingdong.app.mall.widget.editWidget.WidgetEditMoreSettingActivity r1 = com.jingdong.app.mall.widget.editWidget.WidgetEditMoreSettingActivity.this
                java.lang.String r1 = com.jingdong.app.mall.widget.editWidget.WidgetEditMoreSettingActivity.u(r1)
                java.lang.String r3 = "xiaomi"
                boolean r1 = android.text.TextUtils.equals(r1, r3)
                r3 = 0
                if (r1 == 0) goto L1e
                com.jingdong.jdsdk.JdSdk r1 = com.jingdong.jdsdk.JdSdk.getInstance()
                android.app.Application r1 = r1.getApplication()
                java.lang.String r4 = "chanelDataXIAOMI"
                android.content.SharedPreferences r1 = r1.getSharedPreferences(r4, r3)
                goto L3c
            L1e:
                com.jingdong.app.mall.widget.editWidget.WidgetEditMoreSettingActivity r1 = com.jingdong.app.mall.widget.editWidget.WidgetEditMoreSettingActivity.this
                java.lang.String r1 = com.jingdong.app.mall.widget.editWidget.WidgetEditMoreSettingActivity.u(r1)
                java.lang.String r4 = "vivo"
                boolean r1 = android.text.TextUtils.equals(r1, r4)
                if (r1 == 0) goto L3b
                com.jingdong.jdsdk.JdSdk r1 = com.jingdong.jdsdk.JdSdk.getInstance()
                android.app.Application r1 = r1.getApplication()
                java.lang.String r4 = "chanelDataVIVO"
                android.content.SharedPreferences r1 = r1.getSharedPreferences(r4, r3)
                goto L3c
            L3b:
                r1 = 0
            L3c:
                android.content.SharedPreferences$Editor r1 = r1.edit()
                boolean r4 = r2 instanceof android.widget.RelativeLayout
                if (r4 == 0) goto L9f
                android.widget.RelativeLayout r2 = (android.widget.RelativeLayout) r2
                android.view.View r2 = r2.getChildAt(r3)
                boolean r4 = r2 instanceof android.widget.TextView
                if (r4 == 0) goto L9f
                android.widget.TextView r2 = (android.widget.TextView) r2
                java.lang.CharSequence r2 = r2.getText()
                java.lang.String r2 = (java.lang.String) r2
                com.jingdong.app.mall.widget.editWidget.WidgetEditMoreSettingActivity r4 = com.jingdong.app.mall.widget.editWidget.WidgetEditMoreSettingActivity.this
                java.util.List r4 = com.jingdong.app.mall.widget.editWidget.WidgetEditMoreSettingActivity.v(r4)
                if (r4 == 0) goto L9e
                com.jingdong.app.mall.widget.editWidget.WidgetEditMoreSettingActivity r4 = com.jingdong.app.mall.widget.editWidget.WidgetEditMoreSettingActivity.this
                java.util.List r4 = com.jingdong.app.mall.widget.editWidget.WidgetEditMoreSettingActivity.v(r4)
                boolean r4 = r4.isEmpty()
                if (r4 == 0) goto L6b
                goto L9e
            L6b:
                r4 = 0
            L6c:
                com.jingdong.app.mall.widget.editWidget.WidgetEditMoreSettingActivity r5 = com.jingdong.app.mall.widget.editWidget.WidgetEditMoreSettingActivity.this
                java.util.List r5 = com.jingdong.app.mall.widget.editWidget.WidgetEditMoreSettingActivity.v(r5)
                int r5 = r5.size()
                if (r3 >= r5) goto L9c
                com.jingdong.app.mall.widget.editWidget.WidgetEditMoreSettingActivity r5 = com.jingdong.app.mall.widget.editWidget.WidgetEditMoreSettingActivity.this
                java.util.List r5 = com.jingdong.app.mall.widget.editWidget.WidgetEditMoreSettingActivity.v(r5)
                java.lang.Object r5 = r5.get(r3)
                if (r5 == 0) goto L99
                com.jingdong.app.mall.widget.editWidget.WidgetEditMoreSettingActivity r5 = com.jingdong.app.mall.widget.editWidget.WidgetEditMoreSettingActivity.this
                java.util.List r5 = com.jingdong.app.mall.widget.editWidget.WidgetEditMoreSettingActivity.v(r5)
                java.lang.Object r5 = r5.get(r3)
                com.jingdong.app.mall.widget.model.CardConfigVO$CardConfig$NegativeOneCardChannelVO r5 = (com.jingdong.app.mall.widget.model.CardConfigVO.CardConfig.NegativeOneCardChannelVO) r5
                java.lang.String r5 = r5.title
                boolean r5 = android.text.TextUtils.equals(r2, r5)
                if (r5 == 0) goto L99
                r4 = r3
            L99:
                int r3 = r3 + 1
                goto L6c
            L9c:
                r3 = r4
                goto La1
            L9e:
                return
            L9f:
                java.lang.String r2 = ""
            La1:
                com.jingdong.app.mall.widget.editWidget.WidgetEditMoreSettingActivity r4 = com.jingdong.app.mall.widget.editWidget.WidgetEditMoreSettingActivity.this
                int r4 = com.jingdong.app.mall.widget.editWidget.WidgetEditMoreSettingActivity.w(r4)
                r5 = 1
                if (r4 != r5) goto Lb0
                java.lang.String r4 = "settingSelect1"
                r1.putInt(r4, r3)
                goto Lbd
            Lb0:
                com.jingdong.app.mall.widget.editWidget.WidgetEditMoreSettingActivity r4 = com.jingdong.app.mall.widget.editWidget.WidgetEditMoreSettingActivity.this
                int r4 = com.jingdong.app.mall.widget.editWidget.WidgetEditMoreSettingActivity.x(r4)
                if (r4 != r5) goto Lbd
                java.lang.String r4 = "settingSelect2"
                r1.putInt(r4, r3)
            Lbd:
                r1.apply()
                com.jingdong.app.mall.widget.editWidget.WidgetEditMoreSettingActivity r1 = com.jingdong.app.mall.widget.editWidget.WidgetEditMoreSettingActivity.this
                android.content.Intent r1 = r1.getIntent()
                if (r1 != 0) goto Lc9
                return
            Lc9:
                java.lang.String r3 = "text"
                r1.putExtra(r3, r2)
                com.jingdong.app.mall.widget.editWidget.WidgetEditMoreSettingActivity r2 = com.jingdong.app.mall.widget.editWidget.WidgetEditMoreSettingActivity.this
                r2.setResult(r5, r1)
                com.jingdong.app.mall.widget.editWidget.WidgetEditMoreSettingActivity r1 = com.jingdong.app.mall.widget.editWidget.WidgetEditMoreSettingActivity.this
                r1.finish()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.widget.editWidget.WidgetEditMoreSettingActivity.b.onItemClick(android.widget.AdapterView, android.view.View, int, long):void");
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
