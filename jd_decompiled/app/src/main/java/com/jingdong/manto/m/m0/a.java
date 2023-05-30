package com.jingdong.manto.m.m0;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.manto.R;
import com.jingdong.manto.h;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.c0;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.q.n;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoThreadUtils;
import com.jingdong.manto.widget.input.o;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class a extends d0 {

    /* renamed from: com.jingdong.manto.m.m0.a$a  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    class RunnableC0583a implements Runnable {
        final /* synthetic */ h a;
        final /* synthetic */ JSONObject b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ ArrayList f13450c;
        final /* synthetic */ int d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ String f13451e;

        /* renamed from: com.jingdong.manto.m.m0.a$a$a  reason: collision with other inner class name */
        /* loaded from: classes15.dex */
        class C0584a implements AdapterView.OnItemClickListener {
            final /* synthetic */ com.jingdong.manto.widget.h.a a;

            C0584a(com.jingdong.manto.widget.h.a aVar) {
                this.a = aVar;
            }

            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
                HashMap hashMap = new HashMap();
                hashMap.put("tapIndex", Integer.valueOf(i2));
                RunnableC0583a runnableC0583a = RunnableC0583a.this;
                runnableC0583a.a.a(runnableC0583a.d, a.this.putErrMsg(IMantoBaseModule.SUCCESS, hashMap, runnableC0583a.f13451e));
                this.a.dismiss();
            }
        }

        /* renamed from: com.jingdong.manto.m.m0.a$a$b */
        /* loaded from: classes15.dex */
        class b implements DialogInterface.OnCancelListener {
            b() {
            }

            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                RunnableC0583a runnableC0583a = RunnableC0583a.this;
                runnableC0583a.a.a(runnableC0583a.d, a.this.putErrMsg("cancel", null, runnableC0583a.f13451e));
            }
        }

        RunnableC0583a(h hVar, JSONObject jSONObject, ArrayList arrayList, int i2, String str) {
            this.a = hVar;
            this.b = jSONObject;
            this.f13450c = arrayList;
            this.d = i2;
            this.f13451e = str;
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (this.a.d) {
                Activity activity = a.this.getCore(this.a).getActivity();
                com.jingdong.manto.widget.h.a aVar = new com.jingdong.manto.widget.h.a(activity);
                int parseColor = MantoDensityUtils.parseColor(this.b.optString("itemColor", ""), Color.parseColor(JDDarkUtil.COLOR_0000000));
                ViewGroup viewGroup = (ViewGroup) View.inflate(activity, R.layout.manto_actionsheet_layout, null);
                aVar.setContentView(viewGroup);
                ListView listView = (ListView) viewGroup.findViewById(R.id.list);
                listView.setAdapter((ListAdapter) new b(this.f13450c, parseColor));
                listView.setOnItemClickListener(new C0584a(aVar));
                aVar.setOnCancelListener(new b());
                this.a.h().a(aVar);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes15.dex */
    public static final class b extends BaseAdapter {
        private final ArrayList<String> a;
        private final int b;

        /* renamed from: com.jingdong.manto.m.m0.a$b$a  reason: collision with other inner class name */
        /* loaded from: classes15.dex */
        private final class C0585a {
            TextView a;

            private C0585a(b bVar) {
            }

            /* synthetic */ C0585a(b bVar, RunnableC0583a runnableC0583a) {
                this(bVar);
            }
        }

        b(ArrayList<String> arrayList, int i2) {
            this.a = arrayList;
            this.b = i2;
        }

        private String b(int i2) {
            return this.a.get(i2);
        }

        @Override // android.widget.Adapter
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public String getItem(int i2) {
            return b(i2);
        }

        @Override // android.widget.Adapter
        public final int getCount() {
            return this.a.size();
        }

        @Override // android.widget.Adapter
        public final long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.Adapter
        public final View getView(int i2, View view, ViewGroup viewGroup) {
            C0585a c0585a;
            if (view == null) {
                view = View.inflate(viewGroup.getContext(), R.layout.manto_actionsheet_item_layout, null);
                c0585a = new C0585a(this, null);
                c0585a.a = (TextView) view.findViewById(R.id.title);
                view.setTag(c0585a);
            } else {
                c0585a = (C0585a) view.getTag();
            }
            c0585a.a.setText(b(i2));
            c0585a.a.setTextColor(this.b);
            return view;
        }
    }

    @Override // com.jingdong.manto.m.d0
    public final void exec(h hVar, JSONObject jSONObject, int i2, String str) {
        n pageView = c0.getPageView(hVar);
        if (pageView == null) {
            MantoLog.w("JsApiShowActionSheet", "invoke JsApi JsApiShowActionSheet failed, current page view is null.");
            hVar.a(i2, putErrMsg("fail", null, str));
            return;
        }
        o.b(pageView);
        String optString = jSONObject.optString("itemList");
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONArray(optString);
            for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                arrayList.add(jSONArray.getString(i3));
            }
            MantoThreadUtils.runOnUIThread(new RunnableC0583a(hVar, jSONObject, arrayList, i2, str));
        } catch (Exception e2) {
            MantoLog.e("JsApiShowActionSheet", e2.getMessage());
            hVar.a(i2, putErrMsg("fail", null, str));
        }
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "showActionSheet";
    }
}
