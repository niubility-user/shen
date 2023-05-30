package com.jingdong.app.mall.bundle.productdetailcard;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.airbnb.lottie.LottieAnimationView;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.bundle.productdetailcard.adapter.ProductCardAdapter;
import com.jingdong.app.mall.bundle.productdetailcard.entity.PdCardCallBack;
import com.jingdong.app.mall.bundle.productdetailcard.entity.PdCardData;
import com.jingdong.app.mall.bundle.productdetailcard.entity.PdCardFloorInfo;
import com.jingdong.common.BaseApplication;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.ui.LottieLoadingView;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class PdCardView extends LinearLayout {
    public static final String TAG = "PdCardView";
    private ProductCardAdapter adapter;
    private Context context;
    private View defaultLayout;
    private Handler handler;
    private boolean isDestroy;
    private FrameLayout loadingLayout;
    private LottieAnimationView mLottieAnimationView;
    private PdCardCallBack pdCardCallBack;
    private RecyclerView recyclerView;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class a implements HttpGroup.OnCommonListener {

        /* renamed from: com.jingdong.app.mall.bundle.productdetailcard.PdCardView$a$a  reason: collision with other inner class name */
        /* loaded from: classes3.dex */
        class RunnableC0259a implements Runnable {

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ HttpResponse f8245g;

            RunnableC0259a(HttpResponse httpResponse) {
                this.f8245g = httpResponse;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (PdCardView.this.isDestroy) {
                    return;
                }
                PdCardView.this.showLoadingView(false);
                try {
                    PdCardView.this.showData(this.f8245g.getFastJsonObject());
                } catch (Exception e2) {
                    e2.printStackTrace();
                    if (Log.D) {
                        Log.d(PdCardView.TAG, "\u6570\u636e\u89e3\u6790\u5f02\u5e38\uff1a" + e2.getMessage());
                    }
                    PdCardView.this.showDefalutLayout(true);
                }
            }
        }

        /* loaded from: classes3.dex */
        class b implements Runnable {
            b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (PdCardView.this.isDestroy) {
                    return;
                }
                PdCardView.this.showLoadingView(false);
                PdCardView.this.showDefalutLayout(true);
            }
        }

        a() {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            if (PdCardView.this.isDestroy || PdCardView.this.handler == null) {
                return;
            }
            PdCardView.this.handler.post(new RunnableC0259a(httpResponse));
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            if (PdCardView.this.isDestroy || PdCardView.this.handler == null) {
                return;
            }
            PdCardView.this.handler.post(new b());
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            Log.d(PdCardView.TAG, "");
        }
    }

    public PdCardView(Context context) {
        super(context);
        this.isDestroy = false;
        this.handler = new Handler(Looper.getMainLooper());
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        if (Log.D) {
            Log.d(TAG, "initView:" + context);
        }
        if (context != null) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.productdetailcard_card_layout, this);
            if (Log.D) {
                Log.d(TAG, "initView:\u521b\u5efaview" + inflate);
            }
            if (inflate != null) {
                this.recyclerView = (RecyclerView) inflate.findViewById(R.id.productdetailcard_pd_recyclerlist);
                this.adapter = new ProductCardAdapter(context);
                this.recyclerView.setLayoutManager(new LinearLayoutManager(context));
                this.recyclerView.setAdapter(this.adapter);
                this.loadingLayout = (FrameLayout) inflate.findViewById(R.id.productdetailcard_loading);
                View lottieLoadingView = BaseApplication.getLottieLoadingView();
                if (Build.VERSION.SDK_INT >= 16 && (lottieLoadingView instanceof LottieAnimationView)) {
                    LottieAnimationView lottieAnimationView = (LottieAnimationView) lottieLoadingView;
                    this.mLottieAnimationView = lottieAnimationView;
                    this.loadingLayout.addView(lottieAnimationView);
                } else {
                    this.loadingLayout.addView(lottieLoadingView);
                }
                this.defaultLayout = inflate.findViewById(R.id.productdetailcard_default_layout);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showData(JDJSONObject jDJSONObject) {
        ArrayList<PdCardFloorInfo> arrayList;
        if (jDJSONObject != null) {
            if (Log.D) {
                Log.d(TAG, "showData:\u89e3\u6790json\uff1a" + jDJSONObject);
            }
            PdCardData pdCardData = new PdCardData(jDJSONObject);
            if (this.recyclerView != null && this.adapter != null && (arrayList = pdCardData.floorEntity) != null && !arrayList.isEmpty()) {
                if (Log.D) {
                    Log.d(TAG, "showData:\u8bbe\u7f6e\u6570\u636e\u6761\u6570\uff1a" + pdCardData.floorEntity.size());
                }
                this.adapter.a(pdCardData.floorEntity);
                return;
            }
            if (Log.D) {
                Log.d(TAG, "view\u5e03\u5c40\u52a0\u8f7d\u5931\u8d25\u6216\u8005\u65e0\u6570\u636e----  recyclerView:" + this.recyclerView + "  floorEntity\u6570\u636e:" + pdCardData);
            }
            showDefalutLayout(true);
            return;
        }
        showDefalutLayout(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDefalutLayout(boolean z) {
        if (z) {
            PdCardCallBack pdCardCallBack = this.pdCardCallBack;
            if (pdCardCallBack != null) {
                pdCardCallBack.onError();
            }
            View view = this.defaultLayout;
            if (view != null) {
                view.setVisibility(0);
                return;
            }
            return;
        }
        View view2 = this.defaultLayout;
        if (view2 != null) {
            view2.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showLoadingView(boolean z) {
        if (z) {
            FrameLayout frameLayout = this.loadingLayout;
            if (frameLayout != null) {
                frameLayout.setVisibility(0);
                LottieAnimationView lottieAnimationView = this.mLottieAnimationView;
                if (lottieAnimationView == null || lottieAnimationView.isAnimating()) {
                    return;
                }
                this.mLottieAnimationView.playAnimation();
                return;
            }
            return;
        }
        FrameLayout frameLayout2 = this.loadingLayout;
        if (frameLayout2 != null) {
            if (frameLayout2.getVisibility() == 0) {
                this.loadingLayout.setVisibility(8);
            }
            LottieAnimationView lottieAnimationView2 = this.mLottieAnimationView;
            if (lottieAnimationView2 == null || !lottieAnimationView2.isAnimating()) {
                return;
            }
            this.mLottieAnimationView.cancelAnimation();
        }
    }

    public void loadData(PdCardCallBack pdCardCallBack) {
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("source", "balance");
        jDJSONObject.put("skuId", "752707");
        loadData(jDJSONObject, pdCardCallBack);
    }

    public void onDestroy() {
        this.isDestroy = true;
        LottieAnimationView lottieAnimationView = this.mLottieAnimationView;
        if (lottieAnimationView != null) {
            LottieLoadingView.freeLottieMemory(lottieAnimationView);
        }
    }

    public PdCardView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isDestroy = false;
        this.handler = new Handler(Looper.getMainLooper());
        initView(context);
    }

    public void loadData(JDJSONObject jDJSONObject, PdCardCallBack pdCardCallBack) {
        this.pdCardCallBack = pdCardCallBack;
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setCacheMode(2);
        httpSetting.setEffect(1);
        httpSetting.setNotifyUser(true);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setHost(Configuration.getWareHost());
        httpSetting.setFunctionId(CartConstant.FUNCTION_ID_MINISKUDETAIL);
        httpSetting.putJsonParam(jDJSONObject);
        httpSetting.setListener(new a());
        showDefalutLayout(false);
        showLoadingView(true);
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }
}
