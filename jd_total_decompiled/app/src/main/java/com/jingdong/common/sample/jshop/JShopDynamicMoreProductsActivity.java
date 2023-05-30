package com.jingdong.common.sample.jshop;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.app.mall.utils.s;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.display.JDRoundedBitmapDisplayer;
import com.jingdong.common.deeplinkhelper.DeepLinkJShopHomeHelper;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.helper.PDHelper;
import com.jingdong.common.sample.jshop.JShopDynamicMoreProductEntity;
import com.jingdong.common.sample.jshop.utils.JShopNextPageLoader;
import com.jingdong.common.sample.jshop.utils.JShopUtil;
import com.jingdong.common.sample.jshop.utils.JshopShowErrorViewUtils;
import com.jingdong.common.sample.jshop.utils.SpannableStringUtils;
import com.jingdong.common.search.SearchConstants;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.MySimpleAdapter;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.DPIUtil;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JShopDynamicMoreProductsActivity extends MyActivity {
    private static final String ActivityWareFunctionId = "getActivityWarePage";
    private static final String TAG = "JShopDynamicMoreProductsActivity";
    private String activityDesc;
    private String activityId;
    private String activityType;
    private String activityTypeDes;
    private ImageView home_to_top;
    private TextView jshop_more_product_content;
    private GridView jshop_more_product_list;
    private TextView jshop_more_product_time;
    private RelativeLayout jshop_more_product_title_layout;
    private LinearLayout loadingLayout;
    private Button mNoDataBtn;
    private ImageView mNoDataImage;
    private TextView mNoDataTV1;
    private TextView mNoDataTV2;
    private TextView mNoDataTV3;
    private View mNoDataView;
    private String modified;
    private JShopNextPageLoader nextPageLoaderForDynamic;
    private String shopId;
    private String shopName;
    private String venderId;
    private JshopShowErrorViewUtils utils = null;
    View.OnClickListener mRetryOnclicklistener = new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.JShopDynamicMoreProductsActivity.5
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            view.setEnabled(false);
            JShopDynamicMoreProductsActivity.this.mNoDataView.setVisibility(8);
            JShopDynamicMoreProductsActivity.this.getMoreProduct();
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.common.sample.jshop.JShopDynamicMoreProductsActivity$4  reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass4 extends JShopNextPageLoader {
        AnonymousClass4(IMyActivity iMyActivity, AdapterView adapterView, View view, String str, JSONObject jSONObject, String str2) {
            super(iMyActivity, adapterView, view, str, jSONObject, str2);
        }

        @Override // com.jingdong.common.sample.jshop.utils.JShopNextPageLoader
        protected MySimpleAdapter createAdapter(IMyActivity iMyActivity, AdapterView adapterView, ArrayList<?> arrayList) {
            return new MySimpleAdapter(JShopDynamicMoreProductsActivity.this, arrayList, R.layout.jshop_dynamic_more_product_item, new String[0], new int[0], true, new JDDisplayImageOptions().displayer(new JDRoundedBitmapDisplayer(0))) { // from class: com.jingdong.common.sample.jshop.JShopDynamicMoreProductsActivity.4.1
                @Override // com.jingdong.common.utils.MySimpleAdapter, com.jingdong.common.utils.SimpleBeanAdapter, android.widget.Adapter
                public View getView(final int i2, View view, ViewGroup viewGroup) {
                    View view2 = super.getView(i2, view, viewGroup);
                    ViewHolder viewHolder = (ViewHolder) view2.getTag();
                    if (viewHolder == null) {
                        viewHolder = new ViewHolder();
                        viewHolder.more_product_item_layout = (RelativeLayout) view2.findViewById(R.id.more_product_item_layout);
                        viewHolder.more_product_item_iv = (ImageView) view2.findViewById(R.id.more_product_item_iv);
                        viewHolder.more_product_item_iv_mask = (TextView) view2.findViewById(R.id.more_product_item_iv_mask);
                        viewHolder.more_product_item_name = (TextView) view2.findViewById(R.id.more_product_item_name);
                        viewHolder.more_product_item_price = (TextView) view2.findViewById(R.id.more_product_item_price);
                        viewHolder.more_product_item_icon = (TextView) view2.findViewById(R.id.more_product_item_icon);
                        view2.setTag(viewHolder);
                    }
                    final JShopDynamicMoreProductEntity.Product product = (JShopDynamicMoreProductEntity.Product) getItem(i2);
                    if (product != null) {
                        int i3 = 1;
                        int width = (DPIUtil.getWidth() - (((int) JShopDynamicMoreProductsActivity.this.getResources().getDimension(R.dimen.jshop_concern_list_item_margin_left_right)) * 3)) >> 1;
                        viewHolder.more_product_item_iv.setLayoutParams(new RelativeLayout.LayoutParams(width, width));
                        int i4 = width >> 1;
                        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i4, i4);
                        layoutParams.addRule(13);
                        viewHolder.more_product_item_iv_mask.setLayoutParams(layoutParams);
                        JDImageUtils.displayImage(JShopUtil.checkImageUrl(product.imgPath), viewHolder.more_product_item_iv, new JDDisplayImageOptions().displayer(new JDRoundedBitmapDisplayer(DPIUtil.dip2px(4.0f))));
                        viewHolder.more_product_item_name.setText(product.wareName);
                        viewHolder.more_product_item_price.setTextSize(1, 12.0f);
                        if (!JShopUtil.isPrice(product.jdPrice) && !JShopUtil.isToPublishPrice(product.jdPrice)) {
                            viewHolder.more_product_item_price.setText(product.jdPrice);
                            viewHolder.more_product_item_price.setTextSize(1, 16.0f);
                        } else {
                            try {
                                viewHolder.more_product_item_price.setText(SpannableStringUtils.getJDPriceSpan(JShopDynamicMoreProductsActivity.this.getString(R.string.product_jd_price_label) + product.jdPrice, 16.0f));
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                        JShopUtil.setIconAfterPrice(product.getPromotionJo(), viewHolder.more_product_item_icon);
                        try {
                            i3 = Integer.parseInt(JShopDynamicMoreProductsActivity.this.activityType);
                        } catch (NumberFormatException unused) {
                            Log.e(JShopDynamicMoreProductsActivity.TAG, "when show product mask, activitytype convert error!");
                        }
                        JShopUtil.showProductMask(viewHolder.more_product_item_iv_mask, i3, product.status);
                        view2.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.JShopDynamicMoreProductsActivity.4.1.1
                            @Override // android.view.View.OnClickListener
                            public void onClick(View view3) {
                                int i5;
                                String str;
                                String str2;
                                try {
                                    i5 = Integer.parseInt(JShopDynamicMoreProductsActivity.this.activityType);
                                } catch (NumberFormatException unused2) {
                                    Log.e(JShopDynamicMoreProductsActivity.TAG, "when show product mask, activitytype convert error!");
                                    i5 = 1;
                                }
                                if (i5 != 1 && i5 == 3) {
                                    str2 = "MShopDynamicStatetTopic_Main";
                                    str = "MShopDynamicStatetTopic_Product";
                                } else {
                                    str = "";
                                    str2 = str;
                                }
                                JDMtaUtils.sendCommonData(JShopDynamicMoreProductsActivity.this, str, JShopDynamicMoreProductsActivity.this.shopId + CartConstant.KEY_YB_INFO_LINK + i2 + CartConstant.KEY_YB_INFO_LINK + product.wareId, "", JShopDynamicMoreProductsActivity.this, "", PDHelper.getPDClassName(), "", str2, JShopDynamicMoreProductsActivity.this.shopId);
                                JShopDynamicMoreProductsActivity.this.toProductDetail(product);
                            }
                        });
                    }
                    return view2;
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.jingdong.common.sample.jshop.utils.JShopNextPageLoader
        public void handleHttpSetttingBeforeLoading(HttpSetting httpSetting) {
            super.handleHttpSetttingBeforeLoading(httpSetting);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.jingdong.common.sample.jshop.utils.JShopNextPageLoader
        public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
            super.onScroll(absListView, i2, i3, i4);
            Log.d("#############", "firstVisibleItem == " + i2);
            if (i2 > 12) {
                JShopDynamicMoreProductsActivity.this.home_to_top.setVisibility(0);
            } else {
                JShopDynamicMoreProductsActivity.this.home_to_top.setVisibility(4);
            }
        }

        @Override // com.jingdong.common.sample.jshop.utils.JShopNextPageLoader
        public void setSelection(int i2) {
        }

        @Override // com.jingdong.common.sample.jshop.utils.JShopNextPageLoader
        protected void showError() {
            if (Log.D) {
                Log.d(JShopDynamicMoreProductsActivity.TAG, "showError() -->> ?");
            }
            if (getPageNum().intValue() <= 1) {
                JShopDynamicMoreProductsActivity.this.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.JShopDynamicMoreProductsActivity.4.2
                    @Override // java.lang.Runnable
                    public void run() {
                        JShopDynamicMoreProductsActivity.this.jshop_more_product_list.setVisibility(8);
                        JShopDynamicMoreProductsActivity.this.jshop_more_product_title_layout.setVisibility(8);
                        JShopDynamicMoreProductsActivity.this.mNoDataView.setVisibility(0);
                        JShopDynamicMoreProductsActivity.this.toShowErrView();
                    }
                });
            }
        }

        @Override // com.jingdong.common.sample.jshop.utils.JShopNextPageLoader
        protected ArrayList<?> toList(HttpResponse httpResponse) {
            JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
            ArrayList<?> arrayList = new ArrayList<>();
            if (fastJsonObject != null && "0".equals(fastJsonObject.optString("code")) && fastJsonObject.optJSONObject("activity") != null) {
                JShopDynamicMoreProductEntity jShopDynamicMoreProductEntity = new JShopDynamicMoreProductEntity(fastJsonObject.optJSONObject("activity"));
                arrayList.addAll(jShopDynamicMoreProductEntity.toProductList(jShopDynamicMoreProductEntity.getProducts()));
            }
            if (arrayList.size() < 1 && getPageNum().intValue() <= 1) {
                JShopDynamicMoreProductsActivity.this.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.JShopDynamicMoreProductsActivity.4.3
                    @Override // java.lang.Runnable
                    public void run() {
                        JShopDynamicMoreProductsActivity.this.jshop_more_product_list.setVisibility(8);
                        JShopDynamicMoreProductsActivity.this.jshop_more_product_title_layout.setVisibility(8);
                        JShopDynamicMoreProductsActivity.this.mNoDataView.setVisibility(0);
                        JShopDynamicMoreProductsActivity.this.toShowEmptyView();
                    }
                });
            } else {
                JShopDynamicMoreProductsActivity.this.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.JShopDynamicMoreProductsActivity.4.4
                    @Override // java.lang.Runnable
                    public void run() {
                        JShopDynamicMoreProductsActivity.this.jshop_more_product_list.setVisibility(0);
                        JShopDynamicMoreProductsActivity.this.jshop_more_product_title_layout.setVisibility(0);
                        JShopDynamicMoreProductsActivity.this.mNoDataView.setVisibility(8);
                    }
                });
            }
            return arrayList;
        }
    }

    /* loaded from: classes6.dex */
    static class ViewHolder {
        TextView more_product_item_icon;
        ImageView more_product_item_iv;
        TextView more_product_item_iv_mask;
        RelativeLayout more_product_item_layout;
        TextView more_product_item_name;
        TextView more_product_item_price;

        ViewHolder() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getMoreProduct() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("pagesize", 10);
            jSONObject.put("page", 1);
            if (!TextUtils.isEmpty(this.venderId)) {
                jSONObject.put("venderId", this.venderId);
            }
            if (!TextUtils.isEmpty(this.activityType)) {
                jSONObject.put(JshopConst.JSHOP_ACTIVITY_TYPE, this.activityType);
            }
            if (!TextUtils.isEmpty(this.activityId)) {
                jSONObject.put("activityId", this.activityId);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        AnonymousClass4 anonymousClass4 = new AnonymousClass4(this, this.jshop_more_product_list, this.loadingLayout, ActivityWareFunctionId, jSONObject, "");
        this.nextPageLoaderForDynamic = anonymousClass4;
        anonymousClass4.setHost(Configuration.getJshopHost());
        this.nextPageLoaderForDynamic.setNeedNoDateView(false);
        this.nextPageLoaderForDynamic.showPageOne(true);
    }

    private void initView() {
        setTitleBack((ImageView) findViewById(R.id.fe));
        this.mNoDataView = findViewById(R.id.jshop_error_view);
        TextView textView = (TextView) findViewById(R.id.fd);
        if (TextUtils.isEmpty(this.shopName)) {
            textView.setText(R.string.jshop_more_product_title);
        } else {
            textView.setText(this.shopName);
        }
        ImageView imageView = (ImageView) findViewById(R.id.b88);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -1);
        layoutParams.addRule(15);
        layoutParams.addRule(11);
        imageView.setPadding(DPIUtil.dip2px(13.0f), DPIUtil.dip2px(5.0f), DPIUtil.dip2px(13.0f), DPIUtil.dip2px(5.0f));
        imageView.setLayoutParams(layoutParams);
        imageView.setImageResource(R.drawable.jshop_shop_icon);
        imageView.setVisibility(0);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.JShopDynamicMoreProductsActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int i2;
                String str;
                String str2;
                try {
                    i2 = Integer.parseInt(JShopDynamicMoreProductsActivity.this.activityType);
                } catch (NumberFormatException unused) {
                    Log.e(JShopDynamicMoreProductsActivity.TAG, "activityType convert error !");
                    i2 = 0;
                }
                if (i2 != 1 && i2 == 3) {
                    str2 = "MShopDynamicStatetTopic_Main";
                    str = "MShopDynamicStatetTopic_ToShop";
                } else {
                    str = "";
                    str2 = str;
                }
                JShopDynamicMoreProductsActivity jShopDynamicMoreProductsActivity = JShopDynamicMoreProductsActivity.this;
                String str3 = jShopDynamicMoreProductsActivity.shopId;
                JShopDynamicMoreProductsActivity jShopDynamicMoreProductsActivity2 = JShopDynamicMoreProductsActivity.this;
                JDMtaUtils.sendCommonData(jShopDynamicMoreProductsActivity, str, str3, "", jShopDynamicMoreProductsActivity2, "", "", "", str2, jShopDynamicMoreProductsActivity2.shopId);
                JShopDynamicMoreProductsActivity jShopDynamicMoreProductsActivity3 = JShopDynamicMoreProductsActivity.this;
                DeepLinkJShopHomeHelper.gotoJShopHome(jShopDynamicMoreProductsActivity3, jShopDynamicMoreProductsActivity3.shopId, JShopDynamicMoreProductsActivity.this.venderId, JShopDynamicMoreProductsActivity.this.shopName, "home", null);
            }
        });
        TextView textView2 = (TextView) findViewById(R.id.jshop_more_product_time);
        this.jshop_more_product_time = textView2;
        textView2.setText(!TextUtils.isEmpty(this.modified) ? this.modified : "");
        TextView textView3 = (TextView) findViewById(R.id.jshop_more_product_content);
        this.jshop_more_product_content = textView3;
        textView3.setText(TextUtils.isEmpty(this.activityDesc) ? "" : this.activityDesc);
        this.jshop_more_product_list = (GridView) findViewById(R.id.jshop_more_product_list);
        try {
            JShopUtil.setActivityIcon57(this, this.jshop_more_product_content, this.activityDesc, Integer.parseInt(this.activityType));
        } catch (NumberFormatException unused) {
            Log.e(TAG, "activityType convert error !");
        }
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.jshop_more_product_title_layout);
        this.jshop_more_product_title_layout = relativeLayout;
        relativeLayout.setVisibility(8);
        LinearLayout linearLayout = (LinearLayout) ImageUtil.inflate(R.layout.loading, null);
        this.loadingLayout = linearLayout;
        linearLayout.setGravity(17);
        ImageView imageView2 = (ImageView) findViewById(R.id.home_to_top);
        this.home_to_top = imageView2;
        imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.JShopDynamicMoreProductsActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (JShopDynamicMoreProductsActivity.this.jshop_more_product_list != null) {
                    JShopDynamicMoreProductsActivity.this.jshop_more_product_list.setSelection(0);
                }
            }
        });
        this.jshop_more_product_list.setOnScrollListener(new AbsListView.OnScrollListener() { // from class: com.jingdong.common.sample.jshop.JShopDynamicMoreProductsActivity.3
            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
                if (i2 > 12) {
                    JShopDynamicMoreProductsActivity.this.home_to_top.setVisibility(0);
                } else {
                    JShopDynamicMoreProductsActivity.this.home_to_top.setVisibility(4);
                }
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView absListView, int i2) {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toProductDetail(JShopDynamicMoreProductEntity.Product product) {
        if (product != null) {
            String str = getIntent().getBooleanExtra(SearchConstants.PATH_IS_FROM_HOME, false) ? SourceEntity.SOURCE_TYPE_HOME_FAVORITE : SourceEntity.SOURCE_TYPE_MYJD_FAVORITE;
            s.g(this, DeeplinkProductDetailHelper.BundleBuilder.from(Long.valueOf(product.wareId).longValue()).imageTitlePrice(product.imgPath, product.wareName, product.jdPrice).sourceEntity(new SourceEntity(str, null)).build());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.jshop_dynamic_more_products_activity);
        Intent intent = getIntent();
        if (intent != null) {
            this.shopName = intent.getStringExtra("shopName");
            this.shopId = intent.getStringExtra("shopId");
            this.venderId = intent.getStringExtra("venderId");
            this.activityType = intent.getStringExtra(JshopConst.JSHOP_ACTIVITY_TYPE);
            this.activityId = intent.getStringExtra("activityId");
            this.activityDesc = intent.getStringExtra("activityDesc");
            this.modified = intent.getStringExtra("modified");
            this.activityTypeDes = intent.getStringExtra("activityTypeDes");
        }
        setShopId(this.shopId);
        setPageId("MyFollow_MoreProduct");
        try {
            Integer.parseInt(this.activityType);
        } catch (NumberFormatException unused) {
            Log.e(TAG, "activityType convert error !");
        }
        initView();
        getMoreProduct();
    }

    public void toShowEmptyView() {
        if (this.utils == null) {
            this.utils = new JshopShowErrorViewUtils(this, (LinearLayout) this.mNoDataView);
        }
        this.mNoDataView = this.utils.getErrorViewHasRetry(null);
        this.utils.setMessageInfo(getString(R.string.jshop_no_content), getString(R.string.jshop_go_other_page), "");
        this.utils.setErrorImage(R.drawable.y_04);
        this.mNoDataView.setVisibility(0);
    }

    public void toShowErrView() {
        if (this.utils == null) {
            this.utils = new JshopShowErrorViewUtils(this, (LinearLayout) this.mNoDataView);
        }
        this.mNoDataView = this.utils.getErrorViewHasRetry(this.mRetryOnclicklistener);
        this.utils.setMessageInfo(getString(R.string.jshop_net_fail), getString(R.string.jshop_net_check), "");
        this.utils.setErrorImage(R.drawable.y_03);
        this.mNoDataView.setVisibility(0);
    }
}
