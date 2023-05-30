package com.jingdong.app.mall.home;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jd.dynamic.DYConstants;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.app.mall.utils.s;
import com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.common.entity.Commercial;
import com.jingdong.common.entity.Product;
import com.jingdong.common.entity.ShareInfo;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.helper.PDHelper;
import com.jingdong.common.jump.OpenAppJumpController;
import com.jingdong.common.ui.HandlerRecycleBitmapDrawable;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.MySimpleAdapter;
import com.jingdong.common.utils.NextPageLoader;
import com.jingdong.common.utils.ShareUtil;
import com.jingdong.common.utils.SimpleBeanAdapter;
import com.jingdong.common.utils.SimpleSubViewBinder;
import com.jingdong.common.utils.adapter.SimpleImageProcessor;
import com.jingdong.common.utils.adapter.UIRunnable;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.ClickConstant;
import com.jingdong.jdsdk.constant.Constants;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.jdsdk.utils.cache.GlobalImageCache;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class FloorProductListActivity extends MyActivity {
    private String A;
    private String B;
    private String C;
    private String D;
    private boolean E;
    private String F;
    private int G;
    private String H;
    private Commercial I;
    private Long J;
    private SourceEntity K;

    /* renamed from: g  reason: collision with root package name */
    private RelativeLayout f8474g;

    /* renamed from: h  reason: collision with root package name */
    private TextView f8475h;

    /* renamed from: i  reason: collision with root package name */
    private TextView f8476i;

    /* renamed from: j  reason: collision with root package name */
    private Button f8477j;

    /* renamed from: k  reason: collision with root package name */
    private ImageView f8478k;

    /* renamed from: l  reason: collision with root package name */
    private ImageView f8479l;

    /* renamed from: m  reason: collision with root package name */
    private ImageView f8480m;

    /* renamed from: n  reason: collision with root package name */
    private ListView f8481n;
    private LinearLayout o;
    private final int p = (com.jingdong.app.mall.home.floor.common.d.f9279g * R2.attr.amountMaxSize) / 1000;
    private final int q = (com.jingdong.app.mall.home.floor.common.d.f9279g * 500) / 1000;
    private View r;
    int s;
    private String t;
    private JSONObject u;
    private String v;
    private String w;
    private String x;
    private String y;
    private String z;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (Log.D) {
                Log.d("PromotionProductListActivity", " -->> mShareTitle : " + FloorProductListActivity.this.A);
                Log.d("PromotionProductListActivity", " -->> mShare : " + FloorProductListActivity.this.z);
            }
            FloorProductListActivity floorProductListActivity = FloorProductListActivity.this;
            floorProductListActivity.z = floorProductListActivity.getString(com.jingdong.app.mall.R.string.wx_share_activity_content_hint);
            String string = FloorProductListActivity.this.getString(com.jingdong.app.mall.R.string.wx_share_activity_content);
            ShareUtil.panel(FloorProductListActivity.this, new ShareInfo(FloorProductListActivity.this.B, string, string, FloorProductListActivity.this.getString(com.jingdong.app.mall.R.string.wx_share_down_link), FloorProductListActivity.this.z, ClickConstant.CLICK_SHARE_VALUE_ACTIVITY, null, ((BitmapDrawable) FloorProductListActivity.this.getResources().getDrawable(com.jingdong.app.mall.R.drawable.jd_buy_icon)).getBitmap()));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b extends com.jingdong.app.mall.home.o.a.b {

        /* loaded from: classes4.dex */
        class a extends JDSimpleImageLoadingListener {

            /* renamed from: com.jingdong.app.mall.home.FloorProductListActivity$b$a$a  reason: collision with other inner class name */
            /* loaded from: classes4.dex */
            class C0268a extends com.jingdong.app.mall.home.o.a.b {

                /* renamed from: g  reason: collision with root package name */
                final /* synthetic */ Bitmap f8485g;

                C0268a(Bitmap bitmap) {
                    this.f8485g = bitmap;
                }

                @Override // com.jingdong.app.mall.home.o.a.b
                public void safeRun() {
                    FloorProductListActivity.this.f8480m.setVisibility(8);
                    HandlerRecycleBitmapDrawable handlerRecycleBitmapDrawable = new HandlerRecycleBitmapDrawable(this.f8485g, FloorProductListActivity.this);
                    handlerRecycleBitmapDrawable.setNeedPadding(false);
                    FloorProductListActivity.this.f8479l.setImageDrawable(handlerRecycleBitmapDrawable);
                    FloorProductListActivity.this.f8479l.invalidate();
                    FloorProductListActivity.this.f8477j.setVisibility(0);
                    FloorProductListActivity.this.f8478k.setVisibility(0);
                    if (TextUtils.isEmpty(FloorProductListActivity.this.C)) {
                        return;
                    }
                    try {
                        FloorProductListActivity.this.f8481n.setBackgroundColor(Color.parseColor(FloorProductListActivity.this.C));
                        FloorProductListActivity.this.f8474g.setBackgroundColor(Color.parseColor(FloorProductListActivity.this.C));
                    } catch (Exception e2) {
                        if (Log.E) {
                            e2.printStackTrace();
                        }
                    }
                }
            }

            a() {
            }

            @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                if (bitmap != null) {
                    com.jingdong.app.mall.home.o.a.f.E0(new C0268a(bitmap));
                }
            }
        }

        b() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        public void safeRun() {
            FloorProductListActivity.this.E = true;
            if (!TextUtils.isEmpty(FloorProductListActivity.this.y)) {
                FloorProductListActivity.this.f8475h.setText(FloorProductListActivity.this.y);
            }
            if (TextUtils.isEmpty(FloorProductListActivity.this.D)) {
                return;
            }
            JDImageUtils.loadImage(FloorProductListActivity.this.D, FloorProductListActivity.this.f8479l, new a());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class c extends NextPageLoader {

        /* loaded from: classes4.dex */
        class a extends com.jingdong.app.mall.home.o.a.b {
            a() {
            }

            @Override // com.jingdong.app.mall.home.o.a.b
            public void safeRun() {
                FloorProductListActivity.this.f8476i.setVisibility(0);
                FloorProductListActivity.this.f8481n.setVisibility(8);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes4.dex */
        public class b {
            View a;
            ImageView b;

            /* renamed from: c  reason: collision with root package name */
            TextView f8489c;
            TextView d;

            /* renamed from: e  reason: collision with root package name */
            String f8490e;

            b(c cVar) {
            }
        }

        /* renamed from: com.jingdong.app.mall.home.FloorProductListActivity$c$c  reason: collision with other inner class name */
        /* loaded from: classes4.dex */
        class C0269c extends MySimpleAdapter {
            C0269c(IMyActivity iMyActivity, List list, int i2, String[] strArr, int[] iArr) {
                super(iMyActivity, list, i2, strArr, iArr);
            }

            private b a(LinearLayout linearLayout) {
                ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
                layoutParams.width = FloorProductListActivity.this.q;
                linearLayout.setLayoutParams(layoutParams);
                linearLayout.removeAllViews();
                View inflate = ImageUtil.inflate(com.jingdong.app.mall.R.layout.floor_product_list_grid_item, null);
                linearLayout.addView(inflate);
                b bVar = new b(c.this);
                bVar.a = inflate;
                bVar.b = (ImageView) inflate.findViewById(com.jingdong.app.mall.R.id.promotion_product_list_gridview_item_image);
                bVar.f8489c = (TextView) inflate.findViewById(com.jingdong.app.mall.R.id.promotion_product_list_gridview_item_name);
                bVar.d = (TextView) inflate.findViewById(com.jingdong.app.mall.R.id.promotion_product_list_gridview_item_jd_price);
                return bVar;
            }

            private void b(b bVar, Product product) {
                if (bVar == null) {
                    return;
                }
                if (product != null) {
                    if (bVar.a.getVisibility() != 0) {
                        bVar.a.setVisibility(0);
                    }
                    if (!bVar.f8490e.equals(product.getImageUrl()) || bVar.b.getDrawable() == null) {
                        JDImageUtils.displayImage(product.getImageUrl(), bVar.b);
                        bVar.f8490e = product.getImageUrl();
                    }
                    bVar.f8489c.setText(product.getName());
                    bVar.d.setText(product.getJdPriceRMB());
                    FloorProductListActivity.this.g0(bVar.a, product);
                    return;
                }
                bVar.a.setVisibility(4);
            }

            @Override // com.jingdong.common.utils.MySimpleAdapter, com.jingdong.common.utils.SimpleBeanAdapter, android.widget.Adapter
            public View getView(int i2, View view, ViewGroup viewGroup) {
                b[] bVarArr;
                View view2 = super.getView(i2, view, viewGroup);
                if (view2 != null && view2.getTag() != null) {
                    bVarArr = (b[]) view2.getTag();
                    view2.setTag(bVarArr);
                } else if (view2 != null) {
                    bVarArr = new b[]{a((LinearLayout) view2.findViewById(com.jingdong.app.mall.R.id.floor_item_left)), a((LinearLayout) view2.findViewById(com.jingdong.app.mall.R.id.floor_item_right))};
                    view2.setTag(bVarArr);
                } else {
                    bVarArr = null;
                }
                Product[] productArr = (Product[]) getItem(i2);
                if (productArr != null && productArr.length > 0) {
                    b(bVarArr[0], productArr[0]);
                }
                if (productArr != null && productArr.length > 1) {
                    b(bVarArr[1], productArr[1]);
                }
                return view2;
            }
        }

        /* loaded from: classes4.dex */
        class d extends com.jingdong.app.mall.home.o.a.b {

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ MySimpleAdapter f8492g;

            d(MySimpleAdapter mySimpleAdapter) {
                this.f8492g = mySimpleAdapter;
            }

            @Override // com.jingdong.app.mall.home.o.a.b
            public void safeRun() {
                if (FloorProductListActivity.this.r != null) {
                    this.f8492g.addHeaderView(FloorProductListActivity.this.f8481n, FloorProductListActivity.this.r);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes4.dex */
        public class e extends com.jingdong.app.mall.home.o.a.b {
            e() {
            }

            @Override // com.jingdong.app.mall.home.o.a.b
            public void safeRun() {
                FloorProductListActivity.this.f8476i.setVisibility(0);
                FloorProductListActivity.this.f8481n.setVisibility(8);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes4.dex */
        public class f extends com.jingdong.app.mall.home.o.a.b {
            f() {
            }

            @Override // com.jingdong.app.mall.home.o.a.b
            public void safeRun() {
                FloorProductListActivity.this.f8476i.setVisibility(0);
                FloorProductListActivity.this.f8481n.setVisibility(8);
            }
        }

        c(IMyActivity iMyActivity, AdapterView adapterView, View view, String str, JSONObject jSONObject) {
            super(iMyActivity, adapterView, view, str, jSONObject);
        }

        private void a(HttpResponse httpResponse) {
            JSONObjectProxy jSONObject = httpResponse.getJSONObject();
            if (jSONObject != null) {
                FloorProductListActivity.this.y = jSONObject.getStringOrNull("activityIntro");
                FloorProductListActivity.this.z = jSONObject.getStringOrNull("share");
                FloorProductListActivity.this.A = jSONObject.getStringOrNull(MBaseKeyNames.SHARE_TITLE);
                FloorProductListActivity.this.B = jSONObject.getStringOrNull("title");
                FloorProductListActivity.this.C = jSONObject.getStringOrNull(DYConstants.DY_BG_COLOR);
                FloorProductListActivity.this.D = jSONObject.getStringOrNull("bannerUrl");
                FloorProductListActivity.this.b0();
            }
        }

        private ArrayList<Product[]> b(HttpResponse httpResponse) {
            JSONObjectProxy jSONObject = httpResponse.getJSONObject();
            if (Log.D) {
                Log.d("PromotionProductListActivity", "NextPageLoader-->>toList()-->> jsonObject:" + jSONObject);
            }
            ArrayList<Product> arrayList = new ArrayList<>();
            if (jSONObject != null && !jSONObject.isNull("cmsActivityWareList")) {
                arrayList = Product.toList(jSONObject.getJSONArrayOrNull("cmsActivityWareList"), 20);
            } else if (jSONObject != null && !jSONObject.isNull("wareInfoList")) {
                arrayList = Product.toList(jSONObject.getJSONArrayOrNull("wareInfoList"), 20);
            }
            if ((arrayList == null || arrayList.size() < 1) && (getAllProductList() == null || getAllProductList().size() < 1)) {
                com.jingdong.app.mall.home.o.a.f.E0(new e());
            }
            ArrayList<Product[]> arrayList2 = new ArrayList<>();
            if (arrayList != null) {
                for (int i2 = 0; i2 < arrayList.size(); i2 += 2) {
                    Product[] productArr = new Product[2];
                    productArr[0] = arrayList.get(i2);
                    int i3 = i2 + 1;
                    if (i3 >= arrayList.size()) {
                        productArr[1] = null;
                        this.isPaging = false;
                    } else {
                        productArr[1] = arrayList.get(i3);
                    }
                    arrayList2.add(productArr);
                }
            }
            return arrayList2;
        }

        private void c(HttpResponse httpResponse) {
            JSONObjectProxy jSONObject = httpResponse.getJSONObject();
            if (jSONObject != null) {
                FloorProductListActivity.this.z = jSONObject.getStringOrNull("share");
                FloorProductListActivity.this.A = jSONObject.getStringOrNull(MBaseKeyNames.SHARE_TITLE);
                FloorProductListActivity.this.B = jSONObject.getStringOrNull("title");
                FloorProductListActivity.this.C = jSONObject.getStringOrNull(DYConstants.DY_BG_COLOR);
                FloorProductListActivity.this.D = jSONObject.getStringOrNull("bannerUrl");
                FloorProductListActivity.this.b0();
            }
        }

        private ArrayList<Product[]> d(HttpResponse httpResponse) {
            JSONObjectProxy jSONObject = httpResponse.getJSONObject();
            ArrayList<Product> arrayList = new ArrayList<>();
            if (FloorProductListActivity.this.J != null && jSONObject != null) {
                arrayList = Product.toList(jSONObject.getJSONArrayOrNull("activityProducts"), 1);
            }
            if ((arrayList == null || arrayList.size() < 1) && (getAllProductList() == null || getAllProductList().size() < 1)) {
                com.jingdong.app.mall.home.o.a.f.E0(new f());
            }
            ArrayList<Product[]> arrayList2 = new ArrayList<>();
            if (arrayList != null) {
                for (int i2 = 0; i2 < arrayList.size(); i2 += 2) {
                    Product[] productArr = new Product[2];
                    productArr[0] = arrayList.get(i2);
                    int i3 = i2 + 1;
                    if (i3 >= arrayList.size()) {
                        productArr[1] = null;
                        this.isPaging = false;
                    } else {
                        productArr[1] = arrayList.get(i3);
                    }
                    arrayList2.add(productArr);
                }
            }
            return arrayList2;
        }

        @Override // com.jingdong.common.utils.NextPageLoader
        protected MySimpleAdapter createAdapter(IMyActivity iMyActivity, AdapterView adapterView, ArrayList<?> arrayList) {
            if (Log.D) {
                Log.d("PromotionProductListActivity", "NextPageLoader-->>createAdapter()-->>itemList:" + arrayList.size());
            }
            C0269c c0269c = new C0269c(FloorProductListActivity.this, arrayList, com.jingdong.app.mall.R.layout.floor_product_listview_item, new String[0], new int[0]);
            c0269c.setViewBinder(new SimpleSubViewBinder(new e(null)));
            com.jingdong.app.mall.home.o.a.f.E0(new d(c0269c));
            return c0269c;
        }

        @Override // com.jingdong.common.utils.NextPageLoader, com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            super.onEnd(httpResponse);
            if (FloorProductListActivity.this.s == 1) {
                c(httpResponse);
            } else {
                a(httpResponse);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.jingdong.common.utils.NextPageLoader
        public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
            super.onScroll(absListView, i2, i3, i4);
            FloorProductListActivity.this.G = i2;
        }

        @Override // com.jingdong.common.utils.NextPageLoader
        public void setSelection(int i2) {
        }

        @Override // com.jingdong.common.utils.NextPageLoader
        protected void showError() {
            if (Log.D) {
                Log.d("PromotionProductListActivity", "PromotionProductListActivity-->>handleGridView()\u65b9\u6cd5\u51fa\u9519");
            }
            com.jingdong.app.mall.home.o.a.f.E0(new a());
        }

        @Override // com.jingdong.common.utils.NextPageLoader
        protected ArrayList<?> toList(HttpResponse httpResponse) {
            if (FloorProductListActivity.this.s == 1) {
                return d(httpResponse);
            }
            return b(httpResponse);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class d implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Product f8496g;

        d(Product product) {
            this.f8496g = product;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SourceEntity sourceEntity;
            if (this.f8496g != null) {
                FloorProductListActivity floorProductListActivity = FloorProductListActivity.this;
                if (floorProductListActivity.s == 1) {
                    sourceEntity = floorProductListActivity.K;
                } else if (!floorProductListActivity.w.equals("home")) {
                    if (!FloorProductListActivity.this.w.equals("category")) {
                        if (!FloorProductListActivity.this.w.equals("salse")) {
                            if (!OpenAppJumpController.FROM_M_DESTINATION.equals(FloorProductListActivity.this.w)) {
                                if (FloorProductListActivity.this.w.equals("floor")) {
                                    if (FloorProductListActivity.this.H == null) {
                                        FloorProductListActivity.this.H = "";
                                    }
                                    if (Log.D) {
                                        Log.d("PromotionProductListActivity", "setOnItemClick() sourceValue : " + FloorProductListActivity.this.H);
                                    }
                                    sourceEntity = new SourceEntity(SourceEntity.SOURCE_TYPE_PROM_HOME_FLOOR, FloorProductListActivity.this.H);
                                } else {
                                    sourceEntity = null;
                                }
                            } else {
                                sourceEntity = new SourceEntity(SourceEntity.SOURCE_TYPE_PROM_M_DESTINATION_PAGE, FloorProductListActivity.this.x);
                            }
                        } else {
                            sourceEntity = new SourceEntity(SourceEntity.SOURCE_TYPE_PROMOTION_FROM_SLIDE_SCREEN, FloorProductListActivity.this.v);
                        }
                    } else {
                        sourceEntity = new SourceEntity(SourceEntity.SOURCE_TYPE_PROMOTION_FROM_CATEGORY, FloorProductListActivity.this.v);
                    }
                } else {
                    sourceEntity = new SourceEntity(SourceEntity.SOURCE_TYPE_PROMOTION_FROM_HOME, FloorProductListActivity.this.v);
                }
                try {
                    JDMtaUtils.sendCommonData(FloorProductListActivity.this, "Activity_Productid", this.f8496g.getId() + "", "", FloorProductListActivity.this, "", PDHelper.getPDClassName(), "");
                } catch (Exception e2) {
                    if (Log.D) {
                        e2.printStackTrace();
                    }
                }
                s.i(FloorProductListActivity.this, this.f8496g.getId(), null, sourceEntity);
            }
        }
    }

    /* loaded from: classes4.dex */
    private static class e extends SimpleImageProcessor {

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
                        Log.d(e.class.getName(), "subData and imageUrl is equals -->> ");
                    }
                    return super.getItemView();
                } else if (Log.D) {
                    Log.d(e.class.getName(), "subData and imageUrl not equals -->> ");
                    return null;
                } else {
                    return null;
                }
            }
        }

        private e() {
        }

        @Override // com.jingdong.common.utils.adapter.SimpleImageProcessor
        protected UIRunnable provideUIRunnable(SimpleBeanAdapter.SubViewHolder subViewHolder, GlobalImageCache.ImageState imageState) {
            return new a(subViewHolder, imageState);
        }

        /* synthetic */ e(a aVar) {
            this();
        }
    }

    private void a0() {
        ListView listView = (ListView) findViewById(com.jingdong.app.mall.R.id.promotion_product_list_gridview);
        this.f8481n = listView;
        listView.setSelector(17170445);
        View inflate = ImageUtil.inflate(com.jingdong.app.mall.R.layout.floor_banner, null);
        this.r = inflate;
        RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(com.jingdong.app.mall.R.id.layout_floor_banner);
        this.f8474g = relativeLayout;
        ViewGroup.LayoutParams layoutParams = relativeLayout.getLayoutParams();
        layoutParams.height = this.p;
        this.f8474g.setLayoutParams(layoutParams);
        this.f8475h = (TextView) this.f8474g.findViewById(com.jingdong.app.mall.R.id.textview_floor_banner_intro);
        this.f8479l = (ImageView) this.f8474g.findViewById(com.jingdong.app.mall.R.id.imageview_floor_banner_server);
        this.f8480m = (ImageView) this.f8474g.findViewById(com.jingdong.app.mall.R.id.imageview_floor_banner_default);
        this.f8476i = (TextView) findViewById(com.jingdong.app.mall.R.id.promotion_no_data);
        this.f8477j = (Button) findViewById(com.jingdong.app.mall.R.id.button_floor_banner_share);
        ImageView imageView = (ImageView) findViewById(com.jingdong.app.mall.R.id.fe);
        this.f8478k = imageView;
        setTitleBack(imageView);
        LinearLayout linearLayout = (LinearLayout) ImageUtil.inflate(com.jingdong.app.mall.R.layout.loading, null);
        this.o = linearLayout;
        linearLayout.setGravity(17);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b0() {
        if (this.E) {
            return;
        }
        com.jingdong.app.mall.home.o.a.f.E0(new b());
    }

    private void c0(String str, JSONObject jSONObject) {
        if (Log.D) {
            Log.d("PromotionProductListActivity", "handleListView()\u65b9\u6cd5");
        }
        c cVar = new c(this, this.f8481n, this.o, str, jSONObject);
        cVar.setPageSize(10);
        cVar.setColSize(2);
        cVar.setEffect(true);
        cVar.setHttpNotifyUser(true);
        cVar.showPageOne();
    }

    private void d0() {
        this.f8477j.setOnClickListener(new a());
    }

    private void e0() {
        Bundle extras = getIntent().getExtras();
        this.t = "getCmsActivityWareList";
        this.u = new JSONObject();
        this.v = extras.getString("activityId");
        this.w = extras.getString("comeFrom");
        this.H = extras.getString("logId");
        extras.getString("jdmJson");
        extras.getString("jdmActId");
        this.x = extras.getString("landPageId");
        String string = extras.getString("functionId");
        if (!TextUtils.isEmpty(string)) {
            this.t = string;
        }
        String string2 = extras.getString("paramsJsonString");
        this.F = string2;
        try {
            if (!TextUtils.isEmpty(string2)) {
                this.u = new JSONObject(this.F);
            } else {
                this.u.put(Constants.STORY_SHARE_PAGE_PARAM_KEY, this.v);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    private void f0() {
        this.I = (Commercial) getIntent().getExtras().getSerializable("commercial");
        try {
            this.K = (SourceEntity) getIntent().getSerializableExtra("source");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        Commercial commercial = this.I;
        if (commercial != null) {
            this.J = Long.valueOf(Long.parseLong(commercial.id));
        }
        if (this.J != null) {
            this.t = "newViewActivity";
            JSONObject jSONObject = new JSONObject();
            this.u = jSONObject;
            try {
                jSONObject.put("activityId", this.J);
                this.u.put("sourceValue", this.I.getSourceValue());
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
        }
    }

    public void g0(View view, Product product) {
        view.setOnClickListener(new d(product));
    }

    @Override // com.jingdong.common.BaseActivity
    public String getPageParam() {
        if (TextUtils.isEmpty(this.v)) {
            return this.v;
        }
        if (!TextUtils.isEmpty(this.H)) {
            return this.H;
        }
        return this.t;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(com.jingdong.app.mall.R.layout.floor_product_list_activity);
        setNetworkModel(false);
        int intExtra = getIntent().getIntExtra("DATA_TYPE_NAME", -1);
        this.s = intExtra;
        if (intExtra == 1) {
            f0();
        } else {
            e0();
        }
        a0();
        this.f8481n.setBackgroundColor(0);
        this.f8474g.setBackgroundColor(0);
        d0();
        c0(this.t, this.u);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        int i2;
        super.onResume();
        ListView listView = this.f8481n;
        if (listView == null || (i2 = this.G) <= 0) {
            return;
        }
        listView.setSelection(i2);
    }
}
