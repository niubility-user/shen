package com.jingdong.app.mall.promotion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.common.entity.PromotionMessage;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.common.utils.MySimpleAdapter;
import com.jingdong.common.utils.NextPageLoader;
import com.jingdong.common.utils.SimpleBeanAdapter;
import com.jingdong.common.utils.SimpleSubViewBinder;
import com.jingdong.common.utils.adapter.SimpleImageProcessor;
import com.jingdong.common.utils.adapter.UIRunnable;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.cache.GlobalImageCache;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class PromotionMessageActivity extends MyActivity {

    /* renamed from: g */
    private ListView f11569g;

    /* renamed from: h */
    private TextView f11570h;

    /* renamed from: i */
    private String f11571i;

    /* renamed from: j */
    private String f11572j;

    /* renamed from: k */
    private String f11573k;

    /* renamed from: l */
    private JSONObject f11574l;

    /* renamed from: m */
    private LinearLayout f11575m;

    /* renamed from: n */
    private String f11576n;

    /* loaded from: classes4.dex */
    public class a implements AdapterView.OnItemClickListener {
        a() {
            PromotionMessageActivity.this = r1;
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [android.widget.Adapter] */
        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
            view.setPressed(false);
            PromotionMessage promotionMessage = (PromotionMessage) adapterView.getAdapter().getItem(i2);
            if (promotionMessage != null) {
                Intent intent = new Intent(PromotionMessageActivity.this, PromotionProductListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("content", promotionMessage.content);
                bundle.putString("activityId", promotionMessage.activity_id);
                bundle.putString("catelogyId", promotionMessage.catelogyId);
                bundle.putString("title", promotionMessage.title);
                bundle.putString("landPageId", PromotionMessageActivity.this.f11576n);
                bundle.putString("comeFrom", PromotionMessageActivity.this.f11573k);
                intent.putExtras(bundle);
                intent.putExtra("com.360buy:navigationDisplayFlag", -1);
                PromotionMessageActivity.this.startActivityInFrame(intent);
            }
        }
    }

    /* loaded from: classes4.dex */
    public class b extends NextPageLoader {

        /* loaded from: classes4.dex */
        class a extends MySimpleAdapter {
            a(b bVar, IMyActivity iMyActivity, List list, int i2, String[] strArr, int[] iArr) {
                super(iMyActivity, list, i2, strArr, iArr);
            }

            @Override // com.jingdong.common.utils.MySimpleAdapter, com.jingdong.common.utils.SimpleBeanAdapter, android.widget.Adapter
            public View getView(int i2, View view, ViewGroup viewGroup) {
                View view2 = super.getView(i2, view, viewGroup);
                TextView textView = (TextView) view2.findViewById(R.id.promotion_message_end_time);
                PromotionMessage promotionMessage = (PromotionMessage) getItem(i2);
                if (promotionMessage != null) {
                    textView.setText("\u622a\u6b62\u65e5\u671f\uff1a" + promotionMessage.endDate);
                }
                return view2;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(IMyActivity iMyActivity, AdapterView adapterView, View view, String str, JSONObject jSONObject, String str2) {
            super(iMyActivity, adapterView, view, str, jSONObject, str2);
            PromotionMessageActivity.this = r8;
        }

        @Override // com.jingdong.common.utils.NextPageLoader
        protected MySimpleAdapter createAdapter(IMyActivity iMyActivity, AdapterView adapterView, ArrayList<?> arrayList) {
            a aVar = new a(this, PromotionMessageActivity.this, arrayList, R.layout.promotion_message_item, new String[]{"endDate", "imageUrl"}, new int[]{R.id.promotion_message_end_time, R.id.promotion_message_item_imageview});
            aVar.setViewBinder(new SimpleSubViewBinder(new c(null)));
            return aVar;
        }

        @Override // com.jingdong.common.utils.NextPageLoader
        public void setSelection(int i2) {
        }

        @Override // com.jingdong.common.utils.NextPageLoader
        protected void showError() {
            if (Log.D) {
                Log.d("PromotionMessageActivity", "PromotionMessageActivity-->> getPromotionMsgList()\u51fa\u9519");
            }
        }

        @Override // com.jingdong.common.utils.NextPageLoader
        protected ArrayList<?> toList(HttpResponse httpResponse) {
            JSONArrayPoxy jSONArrayOrNull = httpResponse.getJSONObject().getJSONArrayOrNull("cmsActivityList");
            if (Log.D) {
                Log.d("PromotionMessageActivity", "getPromotionMsgList()-->>onEnd()-->>jsonArrayPoxy:" + jSONArrayOrNull);
            }
            return PromotionMessage.toList(jSONArrayOrNull, 0);
        }
    }

    /* loaded from: classes4.dex */
    private static class c extends SimpleImageProcessor {

        /* loaded from: classes4.dex */
        private static class a extends UIRunnable {
            public a(SimpleBeanAdapter.SubViewHolder subViewHolder, GlobalImageCache.ImageState imageState) {
                super(subViewHolder, imageState);
            }

            @Override // com.jingdong.common.utils.adapter.UIRunnable
            public View getItemView() {
                SimpleBeanAdapter.SubViewHolder subViewHolder = getSubViewHolder();
                Object item = subViewHolder.getAdapter().getItem(subViewHolder.getPosition());
                if (item != null && subViewHolder.getSubData().equals(((PromotionMessage) item).imageUrl)) {
                    if (Log.D) {
                        Log.d(c.class.getName(), "subData and imageUrl is equals -->> ");
                    }
                    return super.getItemView();
                } else if (Log.D) {
                    Log.d(c.class.getName(), "subData and imageUrl not equals -->> ");
                    return null;
                } else {
                    return null;
                }
            }
        }

        private c() {
        }

        @Override // com.jingdong.common.utils.adapter.SimpleImageProcessor
        protected UIRunnable provideUIRunnable(SimpleBeanAdapter.SubViewHolder subViewHolder, GlobalImageCache.ImageState imageState) {
            return new a(subViewHolder, imageState);
        }

        /* synthetic */ c(a aVar) {
            this();
        }
    }

    private void w() {
        if (Log.D) {
            Log.d("PromotionMessageActivity", "-->> getPromotionMsgList()\u65b9\u6cd5");
        }
        try {
            this.f11574l.put("promotionsID", this.f11572j);
        } catch (JSONException e2) {
            if (Log.D) {
                e2.printStackTrace();
            }
        }
        b bVar = new b(this, this.f11569g, this.f11575m, this.f11571i, this.f11574l, getResources().getString(R.string.i6));
        bVar.setPageSize(10);
        bVar.setEffect(true);
        bVar.setHttpNotifyUser(true);
        bVar.showPageOne();
    }

    private void x() {
        this.f11569g.setOnItemClickListener(new a());
    }

    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.promotion_message_activity);
        Bundle extras = getIntent().getExtras();
        TextView textView = (TextView) findViewById(R.id.fd);
        this.f11570h = textView;
        textView.setText(extras.getString("name"));
        this.f11571i = "getCmsActivityListByPromotionsID";
        setTitleBack((ImageView) findViewById(R.id.fe));
        this.f11572j = extras.getString("promotion_id");
        this.f11573k = extras.getString("comeFrom");
        this.f11576n = extras.getString("landPageId");
        this.f11569g = (ListView) findViewById(R.id.promotion_message_listview);
        this.f11574l = new JSONObject();
        LinearLayout linearLayout = (LinearLayout) ImageUtil.inflate(R.layout.loading, null);
        this.f11575m = linearLayout;
        linearLayout.setGravity(17);
        x();
        w();
    }

    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        ListView listView = this.f11569g;
        if (listView != null) {
            listView.requestLayout();
            this.f11569g.invalidate();
        }
    }
}
