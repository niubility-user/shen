package com.jingdong.app.mall.personel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.WebActivity;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.common.entity.BackExchange;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.utils.EditTextUtils;
import com.jingdong.common.utils.MySimpleAdapter;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.HostConfig;
import com.jingdong.jdsdk.config.HostConstants;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.URLParamMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes4.dex */
public class MyBackAndExchangeActivity extends MyActivity {

    /* renamed from: g  reason: collision with root package name */
    private ListView f11526g;

    /* renamed from: h  reason: collision with root package name */
    private ArrayList<BackExchange> f11527h = new ArrayList<>();

    /* renamed from: i  reason: collision with root package name */
    private MySimpleAdapter f11528i;

    /* renamed from: j  reason: collision with root package name */
    private TextView f11529j;

    /* renamed from: k  reason: collision with root package name */
    private TextView f11530k;

    /* renamed from: l  reason: collision with root package name */
    private LinearLayout f11531l;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends MySimpleAdapter {
        a(MyBackAndExchangeActivity myBackAndExchangeActivity, IMyActivity iMyActivity, List list, int i2, String[] strArr, int[] iArr) {
            super(iMyActivity, list, i2, strArr, iArr);
        }

        @Override // com.jingdong.common.utils.MySimpleAdapter, com.jingdong.common.utils.SimpleBeanAdapter, android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            if (Log.D) {
                Log.d("MyBackAndExchangeActivity", " setAdapter -->> position: " + i2);
            }
            View view2 = super.getView(i2, view, viewGroup);
            ImageView imageView = (ImageView) view2.findViewById(R.id.personel_item_icon);
            if (i2 == 0) {
                imageView.setBackgroundResource(R.drawable.back_repair_apply);
            } else if (i2 == 1) {
                imageView.setBackgroundResource(R.drawable.back_repair_check);
            }
            return view2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b implements AdapterView.OnItemClickListener {
        b() {
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [android.widget.Adapter] */
        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
            if (Log.D) {
                Log.d("MyBackAndExchangeActivity", " setEvent -->> position :" + i2);
            }
            BackExchange backExchange = (BackExchange) adapterView.getAdapter().getItem(i2);
            URLParamMap uRLParamMap = new URLParamMap();
            uRLParamMap.put(RemoteMessageConst.TO, backExchange.url);
            Intent intent = new Intent(MyBackAndExchangeActivity.this, WebActivity.class);
            SerializableContainer serializableContainer = new SerializableContainer();
            serializableContainer.setMap(uRLParamMap);
            intent.putExtra("urlParamMap", serializableContainer);
            intent.putExtra("urlAction", backExchange.action);
            MyBackAndExchangeActivity.this.startActivityInFrame(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class c implements HttpGroup.OnCommonListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ ExceptionReporter f11533g;

        /* loaded from: classes4.dex */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                MyBackAndExchangeActivity.this.f11531l.setVisibility(8);
                MyBackAndExchangeActivity.this.f11530k.setVisibility(0);
            }
        }

        /* loaded from: classes4.dex */
        class b implements Runnable {
            b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                MyBackAndExchangeActivity.this.f11531l.setVisibility(8);
                MyBackAndExchangeActivity.this.f11530k.setVisibility(0);
            }
        }

        c(ExceptionReporter exceptionReporter) {
            this.f11533g = exceptionReporter;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            if (Log.D) {
                Log.d("MyBackAndExchangeActivity", " onEnd -->> ");
            }
            JSONObjectProxy jSONObject = httpResponse.getJSONObject();
            if (Log.D) {
                Log.d("MyBackAndExchangeActivity", " onEnd -->> jsonObject : " + jSONObject);
            }
            JSONArrayPoxy jSONArrayOrNull = jSONObject.getJSONArrayOrNull("subItemList");
            if (jSONArrayOrNull == null) {
                this.f11533g.reportHttpBusinessException(httpResponse);
            }
            if (Log.D) {
                Log.d("MyBackAndExchangeActivity", " onEnd -->> jsonArray : " + jSONArrayOrNull);
            }
            if (MyBackAndExchangeActivity.this.f11527h != null) {
                MyBackAndExchangeActivity.this.f11527h.clear();
            }
            ArrayList<BackExchange> list = BackExchange.toList(jSONArrayOrNull);
            if (list != null && list.size() > 0) {
                MyBackAndExchangeActivity.this.f11527h.addAll(BackExchange.toList(jSONArrayOrNull));
                MyBackAndExchangeActivity.this.z();
                return;
            }
            MyBackAndExchangeActivity.this.post(new b());
            this.f11533g.reportHttpBusinessException(httpResponse);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            MyBackAndExchangeActivity.this.post(new a());
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            httpSettingParams.putJsonParam("type", "ReturnBack");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class d implements Comparator<BackExchange> {
        d(MyBackAndExchangeActivity myBackAndExchangeActivity) {
        }

        @Override // java.util.Comparator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public int compare(BackExchange backExchange, BackExchange backExchange2) {
            Integer num = backExchange.group;
            if (num != null && backExchange2.group != null) {
                if (num.intValue() > backExchange2.group.intValue()) {
                    return 1;
                }
                if (backExchange.group.intValue() - backExchange2.group.intValue() != 0 && backExchange.group.intValue() < backExchange2.group.intValue()) {
                    return -1;
                }
            }
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class e implements Runnable {
        e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            MyBackAndExchangeActivity.this.f11528i.notifyDataSetChanged();
            MyBackAndExchangeActivity.this.f11530k.setVisibility(8);
            MyBackAndExchangeActivity.this.f11531l.setVisibility(0);
        }
    }

    private void A() {
        if (Log.D) {
            Log.d("MyBackAndExchangeActivity", " queryBackExchange -->> ");
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setHost(HostConfig.getInstance().getHost(HostConstants.PERSONAL_HOST));
        httpSetting.setFunctionId("jdHomeShowItem");
        httpSetting.setListener(new c(new ExceptionReporter(httpSetting)));
        httpSetting.setNotifyUser(true);
        getHttpGroupaAsynPool().add(httpSetting);
    }

    private void B() {
        if (Log.D) {
            Log.d("MyBackAndExchangeActivity", " setAdapter -->> ");
        }
        a aVar = new a(this, this, this.f11527h, R.layout.personel_list_item, new String[]{"name"}, new int[]{R.id.personel_item_text});
        this.f11528i = aVar;
        this.f11526g.setAdapter((ListAdapter) aVar);
    }

    private void C() {
        if (Log.D) {
            Log.d("MyBackAndExchangeActivity", " setEvent -->> ");
        }
        this.f11526g.setOnItemClickListener(new b());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z() {
        if (Log.D) {
            Log.d("MyBackAndExchangeActivity", " createListView -->> ");
        }
        ArrayList<BackExchange> arrayList = this.f11527h;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        if (Log.D) {
            Log.d("MyBackAndExchangeActivity", " createListView -->> size:" + this.f11527h.size());
        }
        Collections.sort(this.f11527h, new d(this));
        post(new e());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Log.D) {
            Log.d("MyBackAndExchangeActivity", " onCreate -->> ");
        }
        setContentView(R.layout.my_back_exchange);
        this.f11526g = (ListView) findViewById(R.id.back_exchange_list);
        TextView textView = (TextView) findViewById(R.id.fd);
        this.f11529j = textView;
        EditTextUtils.setTextViewText(textView, getIntent(), getString(R.string.pg_my_jd_my_back_exchange));
        setTitleBack((ImageView) findViewById(R.id.fe));
        this.f11530k = (TextView) findViewById(R.id.back_no_data);
        this.f11531l = (LinearLayout) findViewById(R.id.personal_back_list_layout);
        B();
        C();
        A();
    }
}
