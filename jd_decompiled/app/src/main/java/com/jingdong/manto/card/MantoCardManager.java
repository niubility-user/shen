package com.jingdong.manto.card;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.annotation.NonNull;
import com.jingdong.app.mall.bundle.dolphinlib.common.util.EtModelMaker;
import com.jingdong.manto.launch.LaunchParam;
import com.jingdong.manto.launch.MantoPreLaunchProcess;
import com.jingdong.manto.launch.UIConfig;
import com.jingdong.manto.network.common.IMantoHttpListener;
import com.jingdong.manto.network.common.MantoJDHttpHandler;
import com.jingdong.manto.network.mantorequests.c0;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoThreadUtils;
import com.jingdong.manto.utils.MantoTrack;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class MantoCardManager {
    private static final int ERROR_CODE_COUNT_LIMIT = -3;
    private static final int ERROR_CODE_GET_ACTIVITIES_ERROR = -1;
    private static final int ERROR_CODE_INVALID_PARAMETER = -2;
    private static final String ERROR_MSG_COUNT_LIMIT = "\u5361\u7247\u6570\u76ee\u8d85\u9650";
    private static final String ERROR_MSG_GET_ACTIVITIES_ERROR = "\u83b7\u53d6\u6d3b\u52a8\u6570\u636e\u51fa\u9519";
    private static final String ERROR_MSG_INVALID_PARAMETER = "\u6d3b\u52a8\u8bf7\u6c42\u53c2\u6570\u4e0d\u5408\u6cd5";
    private static final String TAG = "MantoCardManager";
    public static final boolean TIME_COST_SWITCH = false;
    private Set<MantoCardView> cardViews = new CopyOnWriteArraySet();
    private Activity host;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class a implements Runnable {
        final /* synthetic */ CardRequestParameter a;

        a(MantoCardManager mantoCardManager, CardRequestParameter cardRequestParameter) {
            this.a = cardRequestParameter;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                HashMap hashMap = new HashMap();
                JSONObject makeExpoETModel = MantoCardHelper.makeExpoETModel(this.a);
                if (makeExpoETModel == null) {
                    return;
                }
                hashMap.put(EtModelMaker.KEY_ET_MODEL, makeExpoETModel.toString());
                MantoTrack.sendExposureData(com.jingdong.a.g(), MantoCardHelper.CARD_EXPOSURE_LOAD_CARD, "", "", "", "", "J_SmartCard", hashMap);
            } catch (Throwable unused) {
            }
        }
    }

    /* loaded from: classes15.dex */
    class b implements Runnable {
        final /* synthetic */ CardRequestCallback a;

        b(MantoCardManager mantoCardManager, CardRequestCallback cardRequestCallback) {
            this.a = cardRequestCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.onError(-2, MantoCardManager.ERROR_MSG_INVALID_PARAMETER);
        }
    }

    /* loaded from: classes15.dex */
    class c extends IMantoHttpListener {
        final /* synthetic */ CardRequestParameter a;
        final /* synthetic */ CardRequestCallback b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ long f13002c;

        /* loaded from: classes15.dex */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                c cVar = c.this;
                MantoCardManager.this.generateCardView(cVar.a, "", cVar.b, cVar.f13002c);
            }
        }

        /* loaded from: classes15.dex */
        class b implements Runnable {
            b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                c.this.b.onError(-1, MantoCardManager.ERROR_MSG_GET_ACTIVITIES_ERROR);
            }
        }

        /* renamed from: com.jingdong.manto.card.MantoCardManager$c$c  reason: collision with other inner class name */
        /* loaded from: classes15.dex */
        class RunnableC0505c implements Runnable {
            RunnableC0505c() {
            }

            @Override // java.lang.Runnable
            public void run() {
                c.this.b.onError(-1, MantoCardManager.ERROR_MSG_GET_ACTIVITIES_ERROR);
            }
        }

        c(CardRequestParameter cardRequestParameter, CardRequestCallback cardRequestCallback, long j2) {
            this.a = cardRequestParameter;
            this.b = cardRequestCallback;
            this.f13002c = j2;
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onError(JSONObject jSONObject, Throwable th) {
            super.onError(jSONObject, th);
            MantoThreadUtils.runOnUIThreadImmediately(new RunnableC0505c());
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onSuccess(JSONObject jSONObject) {
            Runnable bVar;
            JSONObject optJSONObject;
            if (jSONObject != null && TextUtils.equals("0", jSONObject.optString("code")) && (optJSONObject = jSONObject.optJSONObject("result")) != null) {
                String optString = optJSONObject.optString("activityUuid");
                if (!TextUtils.isEmpty(optString)) {
                    MantoCardHelper.addUUidCache(this.a, optString);
                    bVar = new a();
                    MantoThreadUtils.runOnUIThreadImmediately(bVar);
                }
            }
            bVar = new b();
            MantoThreadUtils.runOnUIThreadImmediately(bVar);
        }
    }

    /* loaded from: classes15.dex */
    class d implements Runnable {
        final /* synthetic */ CardRequestParameter a;
        final /* synthetic */ CardRequestCallback b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ long f13003c;

        d(CardRequestParameter cardRequestParameter, CardRequestCallback cardRequestCallback, long j2) {
            this.a = cardRequestParameter;
            this.b = cardRequestCallback;
            this.f13003c = j2;
        }

        @Override // java.lang.Runnable
        public void run() {
            MantoCardManager.this.generateCardView(this.a, "", this.b, this.f13003c);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class e implements CardLaunchCallback {
        final /* synthetic */ CardRequestCallback a;
        final /* synthetic */ MantoCardView b;

        e(long j2, CardRequestCallback cardRequestCallback, MantoCardView mantoCardView) {
            this.a = cardRequestCallback;
            this.b = mantoCardView;
        }

        @Override // com.jingdong.manto.card.CardLaunchCallback
        public void onBeginLaunch() {
        }

        @Override // com.jingdong.manto.card.CardLaunchCallback
        public void onCreateRuntime() {
        }

        @Override // com.jingdong.manto.card.CardLaunchCallback
        public void onInitRuntime() {
        }

        @Override // com.jingdong.manto.card.CardLaunchCallback
        public void onLaunchError(MantoPreLaunchProcess.LaunchError launchError) {
            this.a.onError(launchError.errorCode, launchError.msg);
            MantoCardManager.this.removeCardView(this.b);
        }

        @Override // com.jingdong.manto.card.CardLaunchCallback
        public void onLaunchSuccess() {
            this.a.onSuccess(this.b);
        }

        @Override // com.jingdong.manto.card.CardLaunchCallback
        public void onPrepareSuccess(boolean z) {
        }

        @Override // com.jingdong.manto.card.CardLaunchCallback
        public void onShowSplash() {
        }

        @Override // com.jingdong.manto.card.CardLaunchCallback
        public void onStart() {
        }
    }

    public MantoCardManager(Activity activity) {
        this.host = activity;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void generateCardView(CardRequestParameter cardRequestParameter, String str, CardRequestCallback cardRequestCallback, long j2) {
        if (this.cardViews.size() >= MantoCardHelper.getAllowCardsCount()) {
            if (cardRequestCallback != null) {
                cardRequestCallback.onError(-3, ERROR_MSG_COUNT_LIMIT);
                return;
            }
            return;
        }
        MantoCardView mantoCardView = new MantoCardView(com.jingdong.a.g());
        mantoCardView.setCardMode(CardMode.LIMIT_API);
        mantoCardView.setCardRequestParameter(cardRequestParameter);
        addCardView(mantoCardView);
        mantoCardView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        LaunchParam launchParam = new LaunchParam();
        if (str != null) {
            launchParam.extrasJson = str;
        }
        launchParam.appId = cardRequestParameter.getCardID();
        launchParam.debugType = cardRequestParameter.getDebugType();
        launchParam.cardHeight = cardRequestParameter.getCardHeight();
        launchParam.cardWidth = cardRequestParameter.getCardWidth();
        launchParam.isCard = true;
        launchParam.uiConfig = new UIConfig.Builder().setHideTabBar(true).setHideSplash(true).setHideNavigationBar(true).build();
        mantoCardView.launchMiniProgram(launchParam, new e(j2, cardRequestCallback, mantoCardView));
    }

    public void addCardView(MantoCardView mantoCardView) {
        if (mantoCardView != null) {
            this.cardViews.add(mantoCardView);
            mantoCardView.setHostActivity(this.host);
        }
    }

    public void onActivityResult(int i2, int i3, Intent intent) {
        Iterator<MantoCardView> it = this.cardViews.iterator();
        while (it.hasNext()) {
            it.next().onActivityResult(i2, i3, intent);
        }
    }

    public void onBackPressed() {
        Iterator<MantoCardView> it = this.cardViews.iterator();
        while (it.hasNext()) {
            it.next().onBackPressed();
        }
    }

    public void onDestroy() {
        for (MantoCardView mantoCardView : this.cardViews) {
            MantoLog.d(TAG, "CardView onDestroy " + mantoCardView);
            mantoCardView.onDestroy();
        }
        this.cardViews.clear();
    }

    public void onPause() {
        Iterator<MantoCardView> it = this.cardViews.iterator();
        while (it.hasNext()) {
            it.next().onPause();
        }
    }

    public void onRequestPermissionsResult(int i2, @NonNull String[] strArr, @NonNull int[] iArr) {
        Iterator<MantoCardView> it = this.cardViews.iterator();
        while (it.hasNext()) {
            it.next().onRequestPermissionsResult(i2, strArr, iArr);
        }
    }

    public void onResume() {
        Iterator<MantoCardView> it = this.cardViews.iterator();
        while (it.hasNext()) {
            it.next().onResume();
        }
    }

    public void removeCardView(MantoCardView mantoCardView) {
        if (mantoCardView != null) {
            this.cardViews.remove(mantoCardView);
            mantoCardView.onDestroy();
            MantoLog.d(TAG, "removeCardView destroy " + mantoCardView);
            ViewParent parent = mantoCardView.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(mantoCardView);
            }
        }
    }

    public void reportCardViewExpoData(MantoCardView mantoCardView) {
        CardRequestParameter cardRequestParameter;
        if (mantoCardView == null || (cardRequestParameter = mantoCardView.getCardRequestParameter()) == null) {
            return;
        }
        com.jingdong.manto.b.d().networkIO().execute(new a(this, cardRequestParameter));
    }

    public void requestCardView(CardRequestParameter cardRequestParameter, CardRequestCallback cardRequestCallback) {
        long currentTimeMillis = System.currentTimeMillis();
        if (cardRequestParameter == null || cardRequestCallback == null) {
            return;
        }
        if (!cardRequestParameter.isValid()) {
            MantoThreadUtils.runOnUIThreadImmediately(new b(this, cardRequestCallback));
        } else if (cardRequestParameter.isDependsOnCardActivitySwitch()) {
            MantoJDHttpHandler.commit(new c0(cardRequestParameter.getCardID(), cardRequestParameter.getVendorId(), cardRequestParameter.getScene()), new c(cardRequestParameter, cardRequestCallback, currentTimeMillis));
        } else {
            MantoThreadUtils.runOnUIThreadImmediately(new d(cardRequestParameter, cardRequestCallback, currentTimeMillis));
        }
    }
}
