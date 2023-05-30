package com.jingdong.common.sample.jshop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.utils.LoginUser;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.app.mall.utils.s;
import com.jingdong.common.deeplinkhelper.DeepLinkJShopHomeHelper;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.common.helper.PDHelper;
import com.jingdong.common.sample.jshop.utils.JShopUtil;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class JshopSignupActivity extends MyActivity implements View.OnClickListener {
    private static final int ANIMATION_STATIC = 1000;
    private static final int ANIMATION_UP = 1000;
    private static final String TAG = "JshopSignupActivity";
    private Button btnReSignup;
    private JDJSONArray cateJSON;
    private String code;
    private COLOR_STATUS color_status;
    private int isSign;
    private View mBgScale;
    private ViewGroup mBgTranslate;
    private GridView mGridView;
    private LayoutInflater mJshopProductItemInflater;
    private LayoutInflater mJshopRestPrizeItemInflater;
    private LinearLayout mJshopRestPrizeLayout;
    private TextView mJshopSignupMore;
    private TextView mJshopSignupTitleRight;
    private ImageView mJshopTitleImagebackground;
    private TextView mSignActivityDate;
    private TextView mSignAttach;
    private ViewGroup mSignContent;
    private TextView mSignTitle;
    private TextView mSignTitleAlready;
    private ViewGroup mSignupFailedView;
    private ViewGroup mSignupScrollView;
    public SourceEntity mSource;
    public String prizeNote;
    private ArrayList<ProductEntity> productEntities;
    private ArrayList<RestEntity> restEntities;
    private String shopId;
    private String signActivityDate;
    private String signActivityName;
    private String signActivityRuleContent;
    public int signPrizeCouponType;
    private int signPrizeTitleType;
    private String signTitle;
    private String signTitleAttach;
    private String venderId;
    private int COLOR_RED = -961453;
    private int COLOR_YELLOW = -25574;
    private int COLOR_BLUE = -13721089;
    private boolean needAnimation = true;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public enum COLOR_STATUS {
        RED,
        YELLOW,
        BLUE
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public class ProductAdapter extends BaseAdapter {
        private ArrayList<ProductEntity> products = new ArrayList<>();

        public ProductAdapter() {
            for (int i2 = 0; i2 < JshopSignupActivity.this.productEntities.size(); i2++) {
                this.products.add(((ProductEntity) JshopSignupActivity.this.productEntities.get(i2)).m23clone());
            }
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.products.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i2) {
            return this.products.get(i2);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            String str;
            if (view == null) {
                view = JshopSignupActivity.this.mJshopProductItemInflater.inflate(R.layout.jshop_sign_up_item, (ViewGroup) null);
                viewHolder = new ViewHolder();
                viewHolder.mSignItemImage = (ImageView) view.findViewById(R.id.jshop_sign_item_image);
                viewHolder.mSignItemTitle = (TextView) view.findViewById(R.id.jshop_sign_item_title);
                viewHolder.mSignItemPrice = (TextView) view.findViewById(R.id.jshop_sign_item_price);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            ProductEntity productEntity = this.products.get(i2);
            JDImageUtils.displayImage(productEntity.imgPath, viewHolder.mSignItemImage);
            viewHolder.mSignItemTitle.setText(productEntity.wareName);
            TextView textView = viewHolder.mSignItemPrice;
            if (JShopUtil.isPrice(productEntity.jdPrice) || JShopUtil.isToPublishPrice(productEntity.jdPrice)) {
                str = "\u00a5" + productEntity.jdPrice;
            } else {
                str = productEntity.jdPrice;
            }
            textView.setText(str);
            return view;
        }
    }

    /* loaded from: classes6.dex */
    class ProductEntity {
        public String imgPath;
        public String jdPrice;
        public String mPrice;
        public String wareId;
        public String wareName;

        public ProductEntity() {
        }

        public ProductEntity(JDJSONObject jDJSONObject) {
            this.wareId = jDJSONObject.optString("wareId");
            this.wareName = jDJSONObject.optString(JshopConst.JSKEY_PRODUCT_WARENAME);
            this.imgPath = jDJSONObject.optString(JshopConst.JSKEY_PRODUCT_IMGPATH);
            this.mPrice = jDJSONObject.optString(JshopConst.JSKEY_PRODUCT_MPRICE);
            this.jdPrice = jDJSONObject.optString(JshopConst.JSKEY_PRODUCT_JDPRICE);
        }

        /* renamed from: clone  reason: merged with bridge method [inline-methods] */
        public ProductEntity m23clone() {
            ProductEntity productEntity = new ProductEntity();
            productEntity.wareId = this.wareId;
            productEntity.wareName = this.wareName;
            productEntity.imgPath = this.imgPath;
            productEntity.mPrice = this.mPrice;
            productEntity.jdPrice = this.jdPrice;
            return productEntity;
        }
    }

    /* loaded from: classes6.dex */
    public class ResizeAnimation extends Animation {
        private int deltaHeight;
        private int startHeight;
        private View view;

        public ResizeAnimation(View view) {
            this.view = view;
        }

        @Override // android.view.animation.Animation
        protected void applyTransformation(float f2, Transformation transformation) {
            this.view.getLayoutParams().height = (int) (this.startHeight + (this.deltaHeight * f2));
            this.view.requestLayout();
        }

        public void setParams(int i2, int i3) {
            this.startHeight = i2;
            this.deltaHeight = i3 - i2;
        }

        @Override // android.view.animation.Animation
        public boolean willChangeBounds() {
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class RestEntity {
        private String prizeNote;
        public String restNote;

        public RestEntity(JDJSONObject jDJSONObject) {
            this.restNote = jDJSONObject.optString("restNote");
            this.prizeNote = jDJSONObject.optString("prizeNote");
        }
    }

    /* loaded from: classes6.dex */
    static class ViewHolder {
        public ImageView mSignItemImage;
        public TextView mSignItemPrice;
        public TextView mSignItemTitle;

        ViewHolder() {
        }
    }

    private void checkMoreItems() {
        JDMtaUtils.sendCommonData(this, "ShopCheckIn_MoreProducts", "", "", this, "", "JshopProductListActivity", "", "Shop_CheckIn", this.shopId);
        Intent intent = new Intent();
        intent.putExtra("page_id", "Shop_CheckInMore");
        intent.putExtra("shopId", this.shopId);
        intent.putExtra("sortKey", 0);
        Log.d(TAG, "cateJSON:" + this.cateJSON);
        if (this.cateJSON != null) {
            Log.d(TAG, "<<<<<<<<<<<<<<<<<<<<<<<:" + this.cateJSON.toString());
            intent.putExtra("cateJSON", this.cateJSON.toString());
        }
        intent.putExtra("type", 1);
        Bundle bundle = new Bundle();
        bundle.putSerializable("source", this.mSource);
        intent.putExtras(bundle);
        DeepLinkJShopHomeHelper.gotoJShopProductList(this, intent.getExtras());
    }

    private String getSignTitleAlreadyString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\u4eca\u5929");
        sb.append(getString(this.isSign == 2 ? R.string.jshop_sign_yiling : R.string.jshop_sign_zhongle));
        StringBuilder sb2 = new StringBuilder();
        sb2.append(((sb.toString() + "<font color='#facc00'>") + this.prizeNote) + "</font>");
        sb2.append(getString(this.signPrizeCouponType == 0 ? R.string.jshop_sign_coupon_jingquan : R.string.jshop_sign_coupon_dongquan));
        String sb3 = sb2.toString();
        Log.d(TAG, "finalString:" + sb3);
        return sb3;
    }

    private void initTitle() {
        setTitleBack((ImageView) findViewById(R.id.fe));
        TextView textView = (TextView) findViewById(R.id.jshop_signup_title_right_text);
        this.mJshopSignupTitleRight = textView;
        textView.setOnClickListener(this);
    }

    private void initView() {
        this.mSignTitle = (TextView) findViewById(R.id.tv_sign_title);
        this.mSignAttach = (TextView) findViewById(R.id.tv_sign_title_attach);
        this.mSignActivityDate = (TextView) findViewById(R.id.tv_sign_activity_date);
        this.mBgScale = findViewById(R.id.jshop_bg_scale);
        this.mBgTranslate = (ViewGroup) findViewById(R.id.jshop_title_title_layout);
        this.mSignTitleAlready = (TextView) findViewById(R.id.tv_sign_title_already);
        this.mJshopTitleImagebackground = (ImageView) findViewById(R.id.jshop_title_image_background);
        Button button = (Button) findViewById(R.id.btn_resign);
        this.btnReSignup = button;
        button.setOnClickListener(this);
        TextView textView = (TextView) findViewById(R.id.jshop_signup_more);
        this.mJshopSignupMore = textView;
        textView.setOnClickListener(this);
        GridView gridView = (GridView) findViewById(R.id.jshop_sign_grid);
        this.mGridView = gridView;
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.jingdong.common.sample.jshop.JshopSignupActivity.2
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
                long parseLong = Long.parseLong(((ProductEntity) JshopSignupActivity.this.productEntities.get(i2)).wareId);
                Log.d(JshopSignupActivity.TAG, "id:" + parseLong);
                Log.d(JshopSignupActivity.TAG, "mSource:" + JshopSignupActivity.this.mSource);
                JDMtaUtils.sendCommonData(JshopSignupActivity.this, "ShopCheckIn_Productid", JshopSignupActivity.this.shopId + CartConstant.KEY_YB_INFO_LINK + parseLong, "", JshopSignupActivity.this, "", PDHelper.getPDClassName(), "", "Shop_CheckIn", JshopSignupActivity.this.shopId);
                s.g(JshopSignupActivity.this, DeeplinkProductDetailHelper.BundleBuilder.from(parseLong).imageTitlePrice(((ProductEntity) JshopSignupActivity.this.productEntities.get(i2)).imgPath, ((ProductEntity) JshopSignupActivity.this.productEntities.get(i2)).wareName, ((ProductEntity) JshopSignupActivity.this.productEntities.get(i2)).jdPrice).sourceEntity(JshopSignupActivity.this.mSource).build());
            }
        });
        this.mSignupScrollView = (ViewGroup) findViewById(R.id.akm);
        this.mSignupFailedView = (ViewGroup) findViewById(R.id.jshop_sign_failed_layout);
        this.mJshopRestPrizeLayout = (LinearLayout) findViewById(R.id.jshop_rest_prize);
        this.mJshopRestPrizeItemInflater = LayoutInflater.from(this);
        this.mJshopProductItemInflater = LayoutInflater.from(this);
        this.mSignContent = (ViewGroup) findViewById(R.id.jshop_sign_content);
        this.mSignupScrollView.setVisibility(4);
        this.mSignupFailedView.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postShopSignReq() {
        this.mSignupScrollView.setVisibility(8);
        this.mSignupFailedView.setVisibility(8);
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setHost(Configuration.getJshopHost());
        httpSetting.setFunctionId("getShopSignPage");
        httpSetting.putJsonParam("shopId", this.shopId);
        httpSetting.putJsonParam("venderId", this.venderId);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setEffect(1);
        httpSetting.setNotifyUser(true);
        httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.sample.jshop.JshopSignupActivity.3
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                if (httpResponse == null) {
                    JshopSignupActivity.this.code = "0";
                } else {
                    JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                    Log.d(JshopSignupActivity.TAG, "jObjectProxy:" + fastJsonObject);
                    try {
                        JDJSONObject optJSONObject = fastJsonObject.optJSONObject("signInfo");
                        JshopSignupActivity.this.cateJSON = fastJsonObject.optJSONArray(JshopConst.JSKEY_SHOP_CATE);
                        JDJSONArray optJSONArray = optJSONObject.optJSONArray("restPrize");
                        JDJSONArray optJSONArray2 = optJSONObject.optJSONArray("products");
                        JshopSignupActivity.this.code = fastJsonObject.optString("code");
                        JshopSignupActivity.this.isSign = optJSONObject.optInt(JshopConst.JSKEY_JSHOP_ISSIGN);
                        JshopSignupActivity.this.signPrizeCouponType = optJSONObject.optInt("signPrizeCouponType");
                        JshopSignupActivity.this.prizeNote = optJSONObject.optString("prizeNote");
                        JshopSignupActivity.this.signTitle = optJSONObject.optString("signTitle");
                        JshopSignupActivity.this.signPrizeTitleType = optJSONObject.optInt("signPrizeTitleType");
                        JshopSignupActivity.this.signActivityRuleContent = optJSONObject.optString("signActivityRuleContent");
                        JshopSignupActivity.this.signTitleAttach = optJSONObject.optString("signTitleAttach");
                        JshopSignupActivity.this.signActivityDate = optJSONObject.optString("signActivityDate");
                        JshopSignupActivity.this.signActivityName = optJSONObject.optString("signActivityName");
                        JshopSignupActivity jshopSignupActivity = JshopSignupActivity.this;
                        String str = JshopSignupActivity.this.shopId + CartConstant.KEY_YB_INFO_LINK;
                        JshopSignupActivity jshopSignupActivity2 = JshopSignupActivity.this;
                        JDMtaUtils.sendCommonData(jshopSignupActivity, "ShopHome_CheckInCoupon", str, "", jshopSignupActivity2, "", "", "", "Shop_ShopMain", jshopSignupActivity2.shopId);
                        if (JshopSignupActivity.this.restEntities == null) {
                            JshopSignupActivity.this.restEntities = new ArrayList();
                        } else {
                            JshopSignupActivity.this.restEntities.clear();
                        }
                        for (int i2 = 0; i2 < optJSONArray.size(); i2++) {
                            JshopSignupActivity.this.restEntities.add(new RestEntity(optJSONArray.getJSONObject(i2)));
                        }
                        if (JshopSignupActivity.this.productEntities == null) {
                            JshopSignupActivity.this.productEntities = new ArrayList();
                        } else {
                            JshopSignupActivity.this.productEntities.clear();
                        }
                        int size = optJSONArray2.size() < 10 ? optJSONArray2.size() : 10;
                        for (int i3 = 0; i3 < size; i3++) {
                            JshopSignupActivity.this.productEntities.add(new ProductEntity(optJSONArray2.getJSONObject(i3)));
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                JshopSignupActivity.this.post(new Runnable() { // from class: com.jingdong.common.sample.jshop.JshopSignupActivity.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        JshopSignupActivity jshopSignupActivity3 = JshopSignupActivity.this;
                        jshopSignupActivity3.updateUI("0".equals(jshopSignupActivity3.code));
                    }
                });
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                JshopSignupActivity.this.mSignupScrollView.setVisibility(8);
                JshopSignupActivity.this.mSignupFailedView.setVisibility(0);
                JshopSignupActivity.this.mJshopSignupTitleRight.setVisibility(4);
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
        getHttpGroupaAsynPool().add(httpSetting);
    }

    public static int px2dip(Context context, float f2) {
        return (int) ((f2 / BaseInfo.getDensity()) + 0.5f);
    }

    private void setBackgroundResource(COLOR_STATUS color_status) {
        COLOR_STATUS color_status2 = this.color_status;
        if (color_status2 == COLOR_STATUS.RED) {
            this.mBgScale.setBackgroundColor(this.COLOR_RED);
            this.mBgTranslate.setBackgroundColor(this.COLOR_RED);
            this.mJshopTitleImagebackground.setBackgroundResource(R.drawable.jshop_sign_scene_happy);
        } else if (color_status2 == COLOR_STATUS.YELLOW) {
            this.mBgScale.setBackgroundColor(this.COLOR_YELLOW);
            this.mBgTranslate.setBackgroundColor(this.COLOR_YELLOW);
            this.mJshopTitleImagebackground.setBackgroundResource(R.drawable.jshop_sign_scene_regret);
        } else if (color_status2 == COLOR_STATUS.BLUE) {
            this.mBgScale.setBackgroundColor(this.COLOR_BLUE);
            this.mBgTranslate.setBackgroundColor(this.COLOR_BLUE);
            this.mJshopTitleImagebackground.setBackgroundResource(R.drawable.jshop_sign_scene_sad);
        }
        this.mSignTitle.setText(this.signTitle);
        this.mSignAttach.setText(this.signTitleAttach);
        this.mSignActivityDate.setText(this.signActivityDate);
        this.mSignTitleAlready.setText(Html.fromHtml(getSignTitleAlreadyString()));
    }

    private void startAnimation() {
        setBackgroundResource(this.color_status);
        this.mSignupScrollView.setVisibility(0);
        for (int i2 = 0; i2 < this.restEntities.size(); i2++) {
            View inflate = this.mJshopRestPrizeItemInflater.inflate(R.layout.jshop_rest_prize_item, (ViewGroup) null);
            ((TextView) inflate.findViewById(R.id.jshop_rest_note)).setText(this.restEntities.get(i2).restNote);
            ((TextView) inflate.findViewById(R.id.jshop_rest_prize_note)).setText(this.restEntities.get(i2).prizeNote);
            this.mJshopRestPrizeLayout.addView(inflate);
        }
        this.mGridView.setAdapter((ListAdapter) new ProductAdapter());
        if (!this.needAnimation) {
            this.mSignContent.setVisibility(0);
        } else {
            post(new Runnable() { // from class: com.jingdong.common.sample.jshop.JshopSignupActivity.4
                @Override // java.lang.Runnable
                public void run() {
                    JshopSignupActivity jshopSignupActivity = JshopSignupActivity.this;
                    ResizeAnimation resizeAnimation = new ResizeAnimation(jshopSignupActivity.mBgScale);
                    resizeAnimation.setDuration(1000L);
                    resizeAnimation.setInterpolator(new DecelerateInterpolator());
                    resizeAnimation.setParams(JshopSignupActivity.this.mSignupScrollView.getMeasuredHeight(), 0);
                    resizeAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.jingdong.common.sample.jshop.JshopSignupActivity.4.1
                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationEnd(Animation animation) {
                        }

                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationRepeat(Animation animation) {
                        }

                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationStart(Animation animation) {
                            JshopSignupActivity.this.mSignContent.setVisibility(0);
                        }
                    });
                    JshopSignupActivity.this.mBgScale.startAnimation(resizeAnimation);
                }
            }, 1000);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateUI(boolean z) {
        int i2;
        Log.d(TAG, "updateUI, " + z);
        if (TextUtils.isEmpty(this.signActivityRuleContent)) {
            this.mJshopSignupTitleRight.setVisibility(4);
        } else {
            this.mJshopSignupTitleRight.setVisibility(0);
        }
        if (z && (i2 = this.isSign) != -1) {
            if (i2 == 3) {
                Toast.makeText(this, "\u5546\u5bb6\u672a\u5f00\u901a", 1).show();
                finish();
            }
            int i3 = this.isSign;
            this.needAnimation = i3 == 1;
            if (i3 == 2) {
                this.color_status = COLOR_STATUS.YELLOW;
            } else if (this.signPrizeTitleType == 0) {
                this.color_status = COLOR_STATUS.BLUE;
            } else {
                this.color_status = COLOR_STATUS.RED;
            }
            this.mSignupScrollView.setVisibility(8);
            this.mSignupFailedView.setVisibility(8);
            startAnimation();
            return;
        }
        this.mSignupScrollView.setVisibility(8);
        this.mSignupFailedView.setVisibility(0);
        this.mJshopSignupTitleRight.setVisibility(4);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_resign) {
            postShopSignReq();
        } else if (id == R.id.jshop_signup_more) {
            checkMoreItems();
        } else if (id != R.id.jshop_signup_title_right_text) {
        } else {
            Intent intent = new Intent(this, JshopPromotionRule.class);
            intent.putExtra("name", this.signActivityName);
            intent.putExtra("ruleDetail", this.signActivityRuleContent);
            startActivity(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView(R.layout.jshop_sign_up);
        Intent intent = getIntent();
        if (intent != null) {
            this.shopId = intent.getStringExtra("shopId");
            this.venderId = intent.getStringExtra("venderId");
            Log.d(TAG, "shopId: " + this.shopId + ", vernderId: " + this.venderId);
            this.mSource = (SourceEntity) intent.getSerializableExtra("source");
        }
        setShopId(this.shopId);
        setPageId("Shop_CheckIn");
        if (intent == null || this.shopId == null || this.venderId == null) {
            Log.d(TAG, "\u53c2\u6570\u4f20\u9012\u4e0d\u5b8c\u6574\uff0c\u7ed3\u675f\u5f53\u524d\u9875\u9762");
            Toast.makeText(this, "\u5f53\u524d\u4e0d\u80fd\u7b7e\u5230", 0).show();
            finish();
        }
        initTitle();
        initView();
        post(new Runnable() { // from class: com.jingdong.common.sample.jshop.JshopSignupActivity.1
            @Override // java.lang.Runnable
            public void run() {
                LoginUser.getInstance().executeLoginRunnable(JshopSignupActivity.this, new Runnable() { // from class: com.jingdong.common.sample.jshop.JshopSignupActivity.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        JshopSignupActivity.this.postShopSignReq();
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }
}
