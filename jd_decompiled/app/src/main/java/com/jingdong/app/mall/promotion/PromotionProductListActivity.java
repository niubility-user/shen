package com.jingdong.app.mall.promotion;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.product.CommercialRuleActivity;
import com.jingdong.app.mall.utils.LoginUser;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.app.mall.utils.s;
import com.jingdong.common.entity.Commercial;
import com.jingdong.common.entity.Product;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.helper.PDHelper;
import com.jingdong.common.jump.OpenAppJumpController;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.common.utils.MySimpleAdapter;
import com.jingdong.common.utils.NextPageLoader;
import com.jingdong.common.utils.SimpleBeanAdapter;
import com.jingdong.common.utils.SimpleSubViewBinder;
import com.jingdong.common.utils.adapter.SimpleImageProcessor;
import com.jingdong.common.utils.adapter.UIRunnable;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.Constants;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.jdsdk.utils.cache.GlobalImageCache;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class PromotionProductListActivity extends MyActivity implements AdapterView.OnItemClickListener {
    private String A;

    /* renamed from: g  reason: collision with root package name */
    private TextView f11579g;

    /* renamed from: h  reason: collision with root package name */
    private TextView f11580h;

    /* renamed from: i  reason: collision with root package name */
    private Button f11581i;

    /* renamed from: j  reason: collision with root package name */
    private RelativeLayout f11582j;

    /* renamed from: k  reason: collision with root package name */
    private GridView f11583k;

    /* renamed from: l  reason: collision with root package name */
    private LinearLayout f11584l;

    /* renamed from: m  reason: collision with root package name */
    private MySimpleAdapter f11585m;

    /* renamed from: n  reason: collision with root package name */
    private String f11586n;
    private JSONObject o;
    private String p;
    private String q;
    private String r;
    private String s;
    private String t;
    int u;
    private Commercial v;
    private Long w;
    private String x;
    private String y;
    private SourceEntity z;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent(PromotionProductListActivity.this, CommercialRuleActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("title", (String) PromotionProductListActivity.this.f11579g.getText());
            bundle.putString("detail", PromotionProductListActivity.this.p);
            intent.putExtras(bundle);
            intent.putExtra("com.360buy:navigationDisplayFlag", -1);
            PromotionProductListActivity.this.startActivityInFrame(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b extends NextPageLoader {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes4.dex */
        public class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                PromotionProductListActivity.this.J();
                if (PromotionProductListActivity.this.f11579g != null) {
                    PromotionProductListActivity.this.f11579g.setText(PromotionProductListActivity.this.x + "");
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.jingdong.app.mall.promotion.PromotionProductListActivity$b$b  reason: collision with other inner class name */
        /* loaded from: classes4.dex */
        public class ViewOnClickListenerC0375b implements View.OnClickListener {

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ String f11590g;

            /* renamed from: h  reason: collision with root package name */
            final /* synthetic */ String f11591h;

            /* renamed from: com.jingdong.app.mall.promotion.PromotionProductListActivity$b$b$a */
            /* loaded from: classes4.dex */
            class a implements Runnable {
                a() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    ViewOnClickListenerC0375b viewOnClickListenerC0375b = ViewOnClickListenerC0375b.this;
                    com.jingdong.app.mall.utils.f.a(PromotionProductListActivity.this, "coupon", viewOnClickListenerC0375b.f11590g, viewOnClickListenerC0375b.f11591h);
                }
            }

            ViewOnClickListenerC0375b(String str, String str2) {
                this.f11590g = str;
                this.f11591h = str2;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LoginUser.getInstance().executeLoginRunnable(PromotionProductListActivity.this, new a());
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes4.dex */
        public class c implements Runnable {
            c() {
            }

            @Override // java.lang.Runnable
            public void run() {
                PromotionProductListActivity.this.f11580h.setVisibility(0);
                PromotionProductListActivity.this.f11583k.setVisibility(8);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes4.dex */
        public class d implements Runnable {
            d() {
            }

            @Override // java.lang.Runnable
            public void run() {
                PromotionProductListActivity.this.f11580h.setVisibility(0);
                PromotionProductListActivity.this.f11583k.setVisibility(8);
            }
        }

        /* loaded from: classes4.dex */
        class e implements Runnable {
            e() {
            }

            @Override // java.lang.Runnable
            public void run() {
                PromotionProductListActivity.this.f11580h.setVisibility(0);
                PromotionProductListActivity.this.f11583k.setVisibility(8);
            }
        }

        /* loaded from: classes4.dex */
        class f extends MySimpleAdapter {
            f(b bVar, IMyActivity iMyActivity, List list, int i2, String[] strArr, int[] iArr) {
                super(iMyActivity, list, i2, strArr, iArr);
            }

            @Override // com.jingdong.common.utils.MySimpleAdapter, com.jingdong.common.utils.SimpleBeanAdapter, android.widget.Adapter
            public View getView(int i2, View view, ViewGroup viewGroup) {
                return super.getView(i2, view, viewGroup);
            }
        }

        b(IMyActivity iMyActivity, AdapterView adapterView, View view, String str, JSONObject jSONObject) {
            super(iMyActivity, adapterView, view, str, jSONObject);
        }

        private void a(HttpResponse httpResponse) {
            JSONObjectProxy jSONObject = httpResponse.getJSONObject();
            if (jSONObject != null) {
                String stringOrNull = jSONObject.getStringOrNull("couponStatus");
                String stringOrNull2 = jSONObject.getStringOrNull("roleIds");
                String stringOrNull3 = jSONObject.getStringOrNull("couponURL");
                if (TextUtils.isEmpty(stringOrNull)) {
                    PromotionProductListActivity.this.M(8, DPIUtil.dip2px(6.0f));
                } else if ("1".equals(stringOrNull)) {
                    if (PromotionProductListActivity.this.f11582j == null) {
                        return;
                    }
                    PromotionProductListActivity.this.M(0, DPIUtil.dip2px(0.0f));
                    PromotionProductListActivity.this.f11582j.setOnClickListener(new ViewOnClickListenerC0375b(stringOrNull2, stringOrNull3));
                } else {
                    PromotionProductListActivity.this.M(8, DPIUtil.dip2px(6.0f));
                }
            }
        }

        private ArrayList<Product> b(HttpResponse httpResponse) {
            JSONObjectProxy jSONObject = httpResponse.getJSONObject();
            if (Log.D) {
                Log.d("PromotionProductListActivity", "NextPageLoader-->>toList()-->> jsonObject:" + jSONObject);
            }
            ArrayList<Product> arrayList = new ArrayList<>();
            if (jSONObject != null && !jSONObject.isNull("cmsActivityWareList")) {
                arrayList = Product.toList(jSONObject.getJSONArrayOrNull("cmsActivityWareList"), 20);
            } else if (!jSONObject.isNull("wareInfoList")) {
                arrayList = Product.toList(jSONObject.getJSONArrayOrNull("wareInfoList"), 20);
            }
            if (Log.D) {
                Log.d("PromotionProductListActivity", "NextPageLoader-->>toList()-->> list:" + arrayList);
                Log.d("PromotionProductListActivity", "NextPageLoader-->>getAllProductList():" + getAllProductList());
            }
            if ((arrayList == null || arrayList.size() < 1) && (getAllProductList() == null || getAllProductList().size() < 1)) {
                PromotionProductListActivity.this.post(new d());
            }
            return arrayList;
        }

        private void c(HttpResponse httpResponse) {
            PromotionProductListActivity.this.y = httpResponse.getJSONObject().getStringOrNull("promotionDetail");
            PromotionProductListActivity.this.x = httpResponse.getJSONObject().getStringOrNull("title");
            if (TextUtils.isEmpty(PromotionProductListActivity.this.x)) {
                PromotionProductListActivity.this.x = "\u4eac\u4e1c\u6d3b\u52a8";
            }
            PromotionProductListActivity.this.post(new a());
            PromotionProductListActivity.this.M(8, DPIUtil.dip2px(6.0f));
        }

        private ArrayList<Product> d(HttpResponse httpResponse) {
            JSONObjectProxy jSONObject = httpResponse.getJSONObject();
            ArrayList<Product> arrayList = new ArrayList<>();
            if (PromotionProductListActivity.this.w != null && jSONObject != null) {
                arrayList = Product.toList(jSONObject.getJSONArrayOrNull("activityProducts"), 1);
            }
            if (Log.D) {
                Log.d("PromotionProductListActivity", "NextPageLoader-->>toList()-->> list:" + arrayList);
                Log.d("PromotionProductListActivity", "NextPageLoader-->>getAllProductList():" + getAllProductList());
            }
            if ((arrayList == null || arrayList.size() < 1) && (getAllProductList() == null || getAllProductList().size() < 1)) {
                PromotionProductListActivity.this.post(new c());
            }
            return arrayList;
        }

        @Override // com.jingdong.common.utils.NextPageLoader
        protected MySimpleAdapter createAdapter(IMyActivity iMyActivity, AdapterView adapterView, ArrayList<?> arrayList) {
            if (Log.D) {
                Log.d("PromotionProductListActivity", "NextPageLoader-->>createAdapter()-->>itemList:" + arrayList.size());
            }
            PromotionProductListActivity.this.f11585m = new f(this, PromotionProductListActivity.this, arrayList, R.layout.promotion_product_list_grid_item, new String[]{"imageurl", "name", JshopConst.JSKEY_PRODUCT_JDPRICE}, new int[]{R.id.promotion_product_list_gridview_item_image, R.id.promotion_product_list_gridview_item_name, R.id.promotion_product_list_gridview_item_jd_price});
            PromotionProductListActivity.this.f11585m.setViewBinder(new SimpleSubViewBinder(new f(null)));
            return PromotionProductListActivity.this.f11585m;
        }

        @Override // com.jingdong.common.utils.NextPageLoader, com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            super.onEnd(httpResponse);
            if (PromotionProductListActivity.this.u == 1) {
                c(httpResponse);
            } else {
                a(httpResponse);
            }
        }

        @Override // com.jingdong.common.utils.NextPageLoader
        public void setSelection(int i2) {
        }

        @Override // com.jingdong.common.utils.NextPageLoader
        protected void showError() {
            if (Log.D) {
                Log.d("PromotionProductListActivity", "PromotionProductListActivity-->>handleGridView()\u65b9\u6cd5\u51fa\u9519");
            }
            PromotionProductListActivity.this.post(new e());
        }

        @Override // com.jingdong.common.utils.NextPageLoader
        protected ArrayList<?> toList(HttpResponse httpResponse) {
            if (PromotionProductListActivity.this.u == 1) {
                return d(httpResponse);
            }
            return b(httpResponse);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class c implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ int f11597g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ int f11598h;

        c(int i2, int i3) {
            this.f11597g = i2;
            this.f11598h = i3;
        }

        @Override // java.lang.Runnable
        public void run() {
            PromotionProductListActivity.this.f11582j.setVisibility(this.f11597g);
            PromotionProductListActivity.this.f11583k.setPadding(DPIUtil.dip2px(8.0f), this.f11598h, DPIUtil.dip2px(8.0f), DPIUtil.dip2px(6.0f));
        }
    }

    /* loaded from: classes4.dex */
    class d implements Runnable {
        d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (PromotionProductListActivity.this.f11585m != null) {
                    PromotionProductListActivity.this.f11585m.notifyDataSetChanged();
                }
            } catch (Exception e2) {
                if (Log.E) {
                    e2.printStackTrace();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class e implements View.OnClickListener {
        e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent(PromotionProductListActivity.this, CommercialRuleActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("title", PromotionProductListActivity.this.x);
            bundle.putString("detail", PromotionProductListActivity.this.y);
            intent.putExtras(bundle);
            PromotionProductListActivity.this.startActivity(intent);
        }
    }

    /* loaded from: classes4.dex */
    private static class f extends SimpleImageProcessor {

        /* loaded from: classes4.dex */
        private static class a extends UIRunnable {
            public a(SimpleBeanAdapter.SubViewHolder subViewHolder, GlobalImageCache.ImageState imageState) {
                super(subViewHolder, imageState);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.jingdong.common.utils.adapter.UIRunnable
            public View getItemView() {
                SimpleBeanAdapter.SubViewHolder subViewHolder = getSubViewHolder();
                Object item = subViewHolder.getAdapter().getItem(subViewHolder.getPosition());
                if (item != null && subViewHolder.getSubData().equals(((Product) item).getImageUrl())) {
                    if (Log.D) {
                        Log.d(f.class.getName(), "subData and imageUrl is equals -->> ");
                    }
                    return super.getItemView();
                } else if (Log.D) {
                    Log.d(f.class.getName(), "subData and imageUrl not equals -->> ");
                    return null;
                } else {
                    return null;
                }
            }
        }

        private f() {
        }

        @Override // com.jingdong.common.utils.adapter.SimpleImageProcessor
        protected UIRunnable provideUIRunnable(SimpleBeanAdapter.SubViewHolder subViewHolder, GlobalImageCache.ImageState imageState) {
            return new a(subViewHolder, imageState);
        }

        /* synthetic */ f(a aVar) {
            this();
        }
    }

    private void I() {
        if (Log.D) {
            Log.d("PromotionProductListActivity", "handleActivityRule()\u65b9\u6cd5");
        }
        if (this.u == 1) {
            J();
        } else if (TextUtils.isEmpty(this.p)) {
        } else {
            Button button = (Button) findViewById(R.id.fc);
            this.f11581i = button;
            button.setVisibility(0);
            this.f11581i.setText(getResources().getString(R.string.rule_title));
            this.f11581i.setOnClickListener(new a());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void J() {
        if (TextUtils.isEmpty(this.y)) {
            return;
        }
        if (this.f11581i == null) {
            Button button = (Button) findViewById(R.id.fc);
            this.f11581i = button;
            button.setText(getResources().getString(R.string.rule_title));
            this.f11581i.setOnClickListener(new e());
        }
        this.f11581i.setVisibility(0);
    }

    private void K(String str, JSONObject jSONObject) {
        if (Log.D) {
            Log.d("PromotionProductListActivity", "handleGridView()\u65b9\u6cd5");
        }
        b bVar = new b(this, this.f11583k, this.f11584l, str, jSONObject);
        bVar.setPageSize(10);
        bVar.setEffect(true);
        bVar.setHttpNotifyUser(true);
        bVar.showPageOne();
    }

    private void L() {
        this.v = (Commercial) getIntent().getExtras().getSerializable("commercial");
        try {
            this.z = (SourceEntity) getIntent().getSerializableExtra("source");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        Commercial commercial = this.v;
        if (commercial != null) {
            this.w = Long.valueOf(Long.parseLong(commercial.id));
        }
        if (this.w != null) {
            this.f11586n = "newViewActivity";
            JSONObject jSONObject = new JSONObject();
            this.o = jSONObject;
            try {
                jSONObject.put("activityId", this.w);
                this.o.put("sourceValue", this.v.getSourceValue());
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void M(int i2, int i3) {
        post(new c(i2, i3));
    }

    private void initData() {
        Bundle extras = getIntent().getExtras();
        this.f11586n = "getCmsActivityWareList";
        this.o = new JSONObject();
        this.q = extras.getString("activityId");
        this.r = extras.getString("comeFrom");
        this.A = extras.getString("logId");
        this.s = extras.getString("landPageId");
        String string = extras.getString("functionId");
        if (!TextUtils.isEmpty(string)) {
            this.f11586n = string;
        }
        String string2 = extras.getString("paramsJsonString");
        try {
            if (!TextUtils.isEmpty(string2)) {
                this.o = new JSONObject(string2);
            } else {
                this.o.put(Constants.STORY_SHARE_PAGE_PARAM_KEY, this.q);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        this.p = extras.getString("content");
        this.t = extras.getString("title");
    }

    @Override // com.jingdong.common.BaseActivity
    public String getPageParam() {
        if (!TextUtils.isEmpty(this.q)) {
            return this.q;
        }
        if (!TextUtils.isEmpty(this.A)) {
            return this.A;
        }
        return this.f11586n;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.promotion_product_list_activity);
        int intExtra = getIntent().getIntExtra("DATA_TYPE_NAME", -1);
        this.u = intExtra;
        if (intExtra == 1) {
            L();
        } else {
            initData();
        }
        TextView textView = (TextView) findViewById(R.id.fd);
        this.f11579g = textView;
        textView.setText(this.t);
        setTitleBack((ImageView) findViewById(R.id.fe));
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.promotion_get_coupon);
        this.f11582j = relativeLayout;
        CommonBase.fixBackBroundRepeat(relativeLayout);
        GridView gridView = (GridView) findViewById(R.id.promotion_product_list_gridview);
        this.f11583k = gridView;
        gridView.setOnItemClickListener(this);
        this.f11580h = (TextView) findViewById(R.id.promotion_no_data);
        LinearLayout linearLayout = (LinearLayout) ImageUtil.inflate(R.layout.loading, null);
        this.f11584l = linearLayout;
        linearLayout.setGravity(17);
        I();
        K(this.f11586n, this.o);
    }

    /* JADX WARN: Type inference failed for: r9v1, types: [android.widget.Adapter] */
    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
        SourceEntity sourceEntity;
        view.setPressed(false);
        Product product = (Product) adapterView.getAdapter().getItem(i2);
        if (product != null) {
            if (this.u == 1) {
                sourceEntity = this.z;
            } else if (this.r.equals("home")) {
                sourceEntity = new SourceEntity(SourceEntity.SOURCE_TYPE_PROMOTION_FROM_HOME, this.q);
            } else if (this.r.equals("category")) {
                sourceEntity = new SourceEntity(SourceEntity.SOURCE_TYPE_PROMOTION_FROM_CATEGORY, this.q);
            } else if (this.r.equals("salse")) {
                if (this.A == null) {
                    this.A = "";
                }
                if (Log.D) {
                    Log.d("PromotionProductListActivity", "setOnItemClick() sourceValue : " + this.A);
                }
                sourceEntity = new SourceEntity(SourceEntity.SOURCE_TYPE_PROM_HOME_FLOOR, this.A);
            } else if (OpenAppJumpController.FROM_M_DESTINATION.equals(this.r)) {
                sourceEntity = new SourceEntity(SourceEntity.SOURCE_TYPE_PROM_M_DESTINATION_PAGE, this.s);
            } else {
                sourceEntity = this.r.equals("floor") ? new SourceEntity(SourceEntity.SOURCE_TYPE_PROM_HOME_FLOOR, this.f11586n) : null;
            }
            s.i(this, product.getId(), null, sourceEntity);
            JDMtaUtils.sendCommonData(this, "Productlist_Productid", "", "", this, "", PDHelper.getPDClassName(), "");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        post(new d());
    }
}
