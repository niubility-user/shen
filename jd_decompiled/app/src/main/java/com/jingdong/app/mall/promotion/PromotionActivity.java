package com.jingdong.app.mall.promotion;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.common.entity.Promotion;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.utils.MySimpleAdapter;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class PromotionActivity extends MyActivity {

    /* renamed from: g  reason: collision with root package name */
    private ListView f11558g;

    /* renamed from: h  reason: collision with root package name */
    private String f11559h;

    /* renamed from: i  reason: collision with root package name */
    private TextView f11560i;

    /* renamed from: j  reason: collision with root package name */
    private ArrayList<Promotion> f11561j = new ArrayList<>();

    /* renamed from: k  reason: collision with root package name */
    private MySimpleAdapter f11562k;

    /* renamed from: l  reason: collision with root package name */
    private JSONArrayPoxy f11563l;

    /* renamed from: m  reason: collision with root package name */
    private TextView f11564m;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements HttpGroup.OnCommonListener {

        /* renamed from: com.jingdong.app.mall.promotion.PromotionActivity$a$a  reason: collision with other inner class name */
        /* loaded from: classes4.dex */
        class RunnableC0374a implements Runnable {
            RunnableC0374a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                PromotionActivity.this.f11560i.setVisibility(0);
                PromotionActivity.this.f11558g.setVisibility(8);
            }
        }

        /* loaded from: classes4.dex */
        class b implements Runnable {
            b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                PromotionActivity.this.f11561j.addAll(Promotion.toList(PromotionActivity.this.f11563l, 0));
                PromotionActivity.this.f11562k.notifyDataSetChanged();
            }
        }

        a() {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            if (Log.D) {
                Log.d("PromotionActivity", "httpResponse.getJSONObject():" + httpResponse.getJSONObject());
            }
            PromotionActivity.this.f11563l = httpResponse.getJSONObject().getJSONArrayOrNull("cmsPromotionsAll");
            if (Log.D) {
                Log.d("PromotionActivity", "jsonArrayPoxy:" + PromotionActivity.this.f11563l);
            }
            if (PromotionActivity.this.f11563l != null && PromotionActivity.this.f11563l.length() != 0) {
                PromotionActivity.this.post(new b());
            } else {
                PromotionActivity.this.post(new RunnableC0374a());
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            if (Log.D) {
                Log.d("PromotionActivity", "onError()");
            }
            PromotionActivity.this.showError();
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b extends MySimpleAdapter {
        b(PromotionActivity promotionActivity, IMyActivity iMyActivity, List list, int i2, String[] strArr, int[] iArr) {
            super(iMyActivity, list, i2, strArr, iArr);
        }

        @Override // com.jingdong.common.utils.MySimpleAdapter, com.jingdong.common.utils.SimpleBeanAdapter, android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            return super.getView(i2, view, viewGroup);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class c implements AdapterView.OnItemClickListener {
        c() {
        }

        /* JADX WARN: Type inference failed for: r9v1, types: [android.widget.Adapter] */
        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
            view.setPressed(false);
            Promotion promotion = (Promotion) adapterView.getAdapter().getItem(i2);
            if (promotion != null) {
                Intent intent = new Intent(PromotionActivity.this, PromotionMessageActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("promotion_id", promotion.promotionId);
                bundle.putString("name", promotion.promotionName);
                bundle.putString("comeFrom", "home");
                intent.putExtras(bundle);
                intent.putExtra("com.360buy:navigationDisplayFlag", -1);
                JDMtaUtils.sendCommonData(PromotionActivity.this, "Activity_Activityid", "" + promotion.promotionId, "", PromotionActivity.this, "", PromotionMessageActivity.class, "");
                PromotionActivity.this.startActivityInFrame(intent);
            }
        }
    }

    private void B() {
        if (Log.D) {
            Log.d("PromotionActivity", "-->> addListeners()\u65b9\u6cd5");
        }
        this.f11558g.setOnItemClickListener(new c());
    }

    private void C() {
        if (Log.D) {
            Log.d("PromotionActivity", "-->> getCmsActivityList()\u65b9\u6cd5");
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId(this.f11559h);
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.setEffect(1);
        httpSetting.setNotifyUser(true);
        httpSetting.setListener(new a());
        getHttpGroupaAsynPool().add(httpSetting);
    }

    private void D() {
        if (Log.D) {
            Log.d("PromotionActivity", "-->> setAdapter()\u65b9\u6cd5");
        }
        b bVar = new b(this, this, this.f11561j, R.layout.promotion_category_item, new String[]{"imageUrl"}, new int[]{R.id.promotion_category_item_img});
        this.f11562k = bVar;
        this.f11558g.setAdapter((ListAdapter) bVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showError() {
        if (Log.D) {
            Log.d("PromotionActivity", "showError()\u51fa\u9519");
        }
    }

    @Override // com.jingdong.common.BaseActivity
    public String getPageParam() {
        return this.f11559h;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.promotion_activity);
        this.f11559h = "getCmsPromotionsAll";
        TextView textView = (TextView) findViewById(R.id.fd);
        this.f11564m = textView;
        textView.setText(getResources().getString(R.string.promotion));
        this.f11558g = (ListView) findViewById(R.id.promotion_listview);
        setTitleBack((ImageView) findViewById(R.id.fe));
        this.f11560i = (TextView) findViewById(R.id.promotion_no_data);
        D();
        B();
        C();
    }

    @Override // com.jingdong.common.BaseActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (Log.D) {
            Log.d("PromotionActivity", "keyCode -->> " + i2);
        }
        if (i2 == 4) {
            return false;
        }
        return super.onKeyDown(i2, keyEvent);
    }
}
