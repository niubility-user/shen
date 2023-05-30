package com.jingdong.app.mall.navigationbar;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.lib.productdetail.core.protocol.PdLVBody;
import com.jingdong.app.mall.MainFrameActivity;
import com.jingdong.app.mall.home.j;
import com.jingdong.app.mall.navigationbar.entity.NavigationBubbleEntity;
import com.jingdong.app.mall.navigationbar.entity.NavigationBubbleResult;
import com.jingdong.app.mall.navigationbar.entity.NavigationFrequencyRuleResult;
import com.jingdong.app.mall.navigationbar.entity.NavigationRuleFilterNotShowEntity;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.ui.UnAddressSelectUtils;
import com.jingdong.common.ui.address.entity.UnAddressInfo;
import com.jingdong.common.unification.navigationbar.NavigationBase;
import com.jingdong.common.unification.navigationbar.NavigationFrequencyRuleEntity;
import com.jingdong.common.unification.navigationbar.db.NavigationDBController;
import com.jingdong.common.unification.navigationbar.db.NavigationDbConstants;
import com.jingdong.common.unification.uniconfig.UnIconConfigController;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.oklog.OKLog;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class e {
    private static e d = null;

    /* renamed from: e  reason: collision with root package name */
    private static boolean f11380e = true;

    /* renamed from: f  reason: collision with root package name */
    private static boolean f11381f = false;

    /* renamed from: g  reason: collision with root package name */
    private static boolean f11382g = false;

    /* renamed from: h  reason: collision with root package name */
    private static boolean f11383h = false;

    /* renamed from: i  reason: collision with root package name */
    private static long f11384i = 86400000;
    SharedPreferences a;
    SharedPreferences.Editor b;

    /* renamed from: c  reason: collision with root package name */
    public int f11385c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements HttpGroup.OnCommonListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ int f11386g;

        a(int i2) {
            this.f11386g = i2;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            if (httpResponse != null) {
                try {
                    NavigationBubbleResult navigationBubbleResult = (NavigationBubbleResult) JDJSON.parseObject(httpResponse.getString(), NavigationBubbleResult.class);
                    if (Log.D) {
                        Log.d("Navigation_Bubble", "onEnd=" + Thread.currentThread().getName());
                        Log.d("Navigation_Bubble", "bubbleResult=" + navigationBubbleResult);
                    }
                    MainFrameActivity b = com.jingdong.app.mall.n.a.a().b();
                    if (navigationBubbleResult != null && b != null) {
                        List<NavigationBubbleEntity> list = navigationBubbleResult.result;
                        String str = "";
                        if (list != null && list.size() > 0) {
                            NavigationBubbleEntity navigationBubbleEntity = null;
                            NavigationBubbleEntity navigationBubbleEntity2 = null;
                            for (int i2 = 0; i2 < list.size(); i2++) {
                                NavigationBubbleEntity navigationBubbleEntity3 = list.get(i2);
                                if (navigationBubbleEntity3 != null) {
                                    if (navigationBubbleEntity3.type == 1) {
                                        navigationBubbleEntity = list.get(i2);
                                    }
                                    if (navigationBubbleEntity3.type == 3) {
                                        navigationBubbleEntity2 = list.get(i2);
                                    }
                                    if (e.f11380e) {
                                        String str2 = navigationBubbleEntity3.angleSwitch;
                                        String str3 = navigationBubbleEntity3.redPointSwitch;
                                        String str4 = navigationBubbleEntity3.redPointShowSwitch;
                                        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
                                            boolean unused = e.f11380e = false;
                                        }
                                        NavigationBarUtil.setAngleSwitch(str2);
                                        NavigationBarUtil.setRedPointSwitch(str3);
                                        str = str4;
                                    }
                                }
                            }
                            try {
                                if (Log.D) {
                                    Log.d("Navigation_Bubble", "bubbleEntity=" + navigationBubbleEntity);
                                    Log.d("Navigation_Effect", "effectEntity=" + navigationBubbleEntity2);
                                }
                                if (navigationBubbleEntity != null) {
                                    boolean unused2 = e.f11381f = true;
                                    navigationBubbleEntity.currentMode = this.f11386g;
                                    NavigationBase.getInstance().mSourceId = navigationBubbleEntity.sourceId;
                                    e.this.f11385c = this.f11386g;
                                    com.jingdong.app.mall.navigationbar.c.G().i0(navigationBubbleEntity);
                                    int i3 = navigationBubbleEntity.bubbleType;
                                    if (i3 == 4) {
                                        int i4 = navigationBubbleEntity.combinationTypes;
                                        if (i4 == 0) {
                                            NavigationBarUtil.changeTabView();
                                        } else if (i4 == 1) {
                                            NavigationBarUtil.showIconAndAngle();
                                        } else if (i4 == 2 || i4 == 3) {
                                            NavigationBarUtil.showIconAndBubble();
                                        }
                                    } else if (i3 == 3) {
                                        if (TextUtils.equals(navigationBubbleEntity.angleSwitch, "1")) {
                                            NavigationBarUtil.showAngle();
                                        }
                                    } else if ("0".equals(navigationBubbleEntity.bubbleSwitch)) {
                                        NavigationBarUtil.handleNewBubbleByMySelf(true);
                                    }
                                }
                                NavigationBarUtil.setShowRedPointSwitch(str);
                                NavigationBarUtil.showRedPont();
                                NavigationBarUtil.handleEffect(navigationBubbleEntity2, b);
                            } catch (Exception e2) {
                                ExceptionReporter.reportExceptionToBugly(new Exception("getBubbleInfo-" + e2));
                            }
                        }
                    }
                } catch (Exception e3) {
                    if (Log.E) {
                        e3.printStackTrace();
                    }
                }
            }
            if (NavigationBarUtil.angleRequested) {
                return;
            }
            NavigationBarUtil.angleRequested = true;
            NavigationBarUtil.showOtherAngle();
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            if (NavigationBarUtil.angleRequested) {
                return;
            }
            NavigationBarUtil.angleRequested = true;
            NavigationBarUtil.showOtherAngle();
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (Log.D) {
                    Log.d("NavigationRequest", "threadName=" + Thread.currentThread().getName() + " isHandleFrequencyRuleAndMaterialShowData=" + e.f11383h + " isFrequencyRuleInfoRequested=" + e.f11382g + " isBubbleInfoRequested=" + e.f11381f);
                }
                if (!e.f11383h) {
                    boolean unused = e.f11383h = true;
                    long currentTimeMillis = System.currentTimeMillis();
                    long j2 = e.f11384i;
                    Long.signum(j2);
                    NavigationDBController.deleteMaterialShowDataByTime(currentTimeMillis - (j2 * 180));
                    NavigationDBController.updateAndDeleteFrequencyRuleDataByState();
                }
                JSONObject k2 = e.this.k(e.this.r());
                if (!e.f11382g && !com.jingdong.app.mall.navigationbar.c.G().S()) {
                    e.this.q();
                }
                if (e.f11381f || com.jingdong.app.mall.navigationbar.c.G().S()) {
                    return;
                }
                e.this.p(k2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class c extends Thread {
        c(e eVar) {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            UnIconConfigController.getController().init();
            UnIconConfigController.getController().requestData();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class d implements HttpGroup.OnCommonListener {
        d(e eVar) {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            if (httpResponse != null) {
                try {
                    NavigationFrequencyRuleResult navigationFrequencyRuleResult = (NavigationFrequencyRuleResult) JDJSON.parseObject(httpResponse.getString(), NavigationFrequencyRuleResult.class);
                    if (Log.D) {
                        Log.d("NavigationRequest", "onEnd=" + Thread.currentThread().getName() + " frequencyRuleResult=" + navigationFrequencyRuleResult);
                    }
                    if (navigationFrequencyRuleResult != null) {
                        boolean unused = e.f11382g = true;
                        NavigationBarUtil.handleFrequencyRule(navigationFrequencyRuleResult);
                    }
                } catch (Exception e2) {
                    if (Log.E) {
                        e2.printStackTrace();
                    }
                }
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    private e() {
        SharedPreferences jdSharedPreferences = CommonBase.getJdSharedPreferences();
        this.a = jdSharedPreferences;
        this.b = jdSharedPreferences.edit();
    }

    public static synchronized e l() {
        e eVar;
        synchronized (e.class) {
            if (d == null) {
                d = new e();
            }
            eVar = d;
        }
        return eVar;
    }

    private NavigationRuleFilterNotShowEntity m(String str, Integer num, Integer num2) {
        NavigationRuleFilterNotShowEntity navigationRuleFilterNotShowEntity = new NavigationRuleFilterNotShowEntity();
        navigationRuleFilterNotShowEntity.position = str;
        navigationRuleFilterNotShowEntity.bubbleType = num;
        navigationRuleFilterNotShowEntity.combinationTypes = num2;
        return navigationRuleFilterNotShowEntity;
    }

    private boolean n() {
        long currentTimeMillis = System.currentTimeMillis();
        long j2 = this.a.getLong("last_quest_time_UnifyRequestDataHolder", 0L);
        if (Log.D) {
            Log.d("UnifyRequestDataHolder", "isAllowRequest-time=" + currentTimeMillis + " lastTime=" + j2);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Log.d("UnifyRequestDataHolder", "isAllowRequest-time=" + simpleDateFormat.format(new Date(currentTimeMillis)) + " lastTime=" + simpleDateFormat.format(new Date(j2)));
        }
        if (currentTimeMillis - j2 > 600000) {
            this.b.putLong("last_quest_time_UnifyRequestDataHolder", currentTimeMillis).apply();
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized List<NavigationRuleFilterNotShowEntity> r() {
        List<NavigationFrequencyRuleEntity> list;
        int i2;
        int i3;
        ArrayList arrayList = new ArrayList();
        List<NavigationFrequencyRuleEntity> queryUsableFrequencyRuleData = NavigationDBController.queryUsableFrequencyRuleData();
        if (queryUsableFrequencyRuleData != null && queryUsableFrequencyRuleData.size() > 0) {
            int size = queryUsableFrequencyRuleData.size();
            long currentTimeMillis = System.currentTimeMillis();
            int i4 = 0;
            int i5 = 0;
            while (i5 < size) {
                NavigationFrequencyRuleEntity navigationFrequencyRuleEntity = queryUsableFrequencyRuleData.get(i5);
                if (navigationFrequencyRuleEntity == null || navigationFrequencyRuleEntity.state != 1 || currentTimeMillis < navigationFrequencyRuleEntity.startTime || currentTimeMillis > navigationFrequencyRuleEntity.endTime || TextUtils.isEmpty(navigationFrequencyRuleEntity.position)) {
                    list = queryUsableFrequencyRuleData;
                    i2 = size;
                    i3 = i5;
                } else {
                    long currentTimeMillis2 = System.currentTimeMillis();
                    String str = navigationFrequencyRuleEntity.position;
                    long j2 = navigationFrequencyRuleEntity.statisticsCycle;
                    long j3 = f11384i;
                    Long.signum(j2);
                    int queryMaterialShowDataByTypeAndPosition = NavigationDBController.queryMaterialShowDataByTypeAndPosition(i4, -1, str, currentTimeMillis2 - (j2 * j3));
                    i3 = i5;
                    int queryMaterialShowDataByTypeAndPosition2 = NavigationDBController.queryMaterialShowDataByTypeAndPosition(1, -1, navigationFrequencyRuleEntity.position, currentTimeMillis2 - (navigationFrequencyRuleEntity.statisticsCycle * f11384i));
                    int queryMaterialShowDataByTypeAndPosition3 = NavigationDBController.queryMaterialShowDataByTypeAndPosition(2, -1, navigationFrequencyRuleEntity.position, currentTimeMillis2 - (navigationFrequencyRuleEntity.statisticsCycle * f11384i));
                    int i6 = queryMaterialShowDataByTypeAndPosition + queryMaterialShowDataByTypeAndPosition2 + queryMaterialShowDataByTypeAndPosition3;
                    if (Log.D) {
                        Log.d("NavigationRequest", "screenNavigationRuleDate()-classicBubbleNum=" + queryMaterialShowDataByTypeAndPosition + " standardBubbleNum=" + queryMaterialShowDataByTypeAndPosition2 + " specialBubbleNum=" + queryMaterialShowDataByTypeAndPosition3 + " realBubbleNum=" + i6);
                    }
                    int queryMaterialShowDataByTypeAndPosition4 = NavigationDBController.queryMaterialShowDataByTypeAndPosition(3, -1, navigationFrequencyRuleEntity.position, currentTimeMillis2 - (navigationFrequencyRuleEntity.statisticsCycle * f11384i));
                    int queryMaterialShowDataByTypeAndPosition5 = NavigationDBController.queryMaterialShowDataByTypeAndPosition(4, -1, navigationFrequencyRuleEntity.position, currentTimeMillis2 - (navigationFrequencyRuleEntity.statisticsCycle * f11384i));
                    if (Log.D) {
                        Log.d("NavigationRequest", "screenNavigationRuleDate()-realAngleNum=" + queryMaterialShowDataByTypeAndPosition4 + " realIconAbnormityNum=" + queryMaterialShowDataByTypeAndPosition5);
                    }
                    list = queryUsableFrequencyRuleData;
                    int queryMaterialShowDataByTypeAndPosition6 = NavigationDBController.queryMaterialShowDataByTypeAndPosition(4, 2, navigationFrequencyRuleEntity.position, currentTimeMillis2 - (navigationFrequencyRuleEntity.statisticsCycle * f11384i));
                    i2 = size;
                    int queryMaterialShowDataByTypeAndPosition7 = NavigationDBController.queryMaterialShowDataByTypeAndPosition(4, 3, navigationFrequencyRuleEntity.position, currentTimeMillis2 - (navigationFrequencyRuleEntity.statisticsCycle * f11384i));
                    int i7 = queryMaterialShowDataByTypeAndPosition6 + queryMaterialShowDataByTypeAndPosition7;
                    if (Log.D) {
                        Log.d("NavigationRequest", "screenNavigationRuleDate()-standardBubbleAbnormityNum=" + queryMaterialShowDataByTypeAndPosition6 + " specialBubbleAbnormityNum=" + queryMaterialShowDataByTypeAndPosition7 + " realBubbleAbnormityNum=" + i7);
                    }
                    int queryMaterialShowDataByTypeAndPosition8 = NavigationDBController.queryMaterialShowDataByTypeAndPosition(4, 1, navigationFrequencyRuleEntity.position, currentTimeMillis2 - (navigationFrequencyRuleEntity.statisticsCycle * f11384i));
                    int i8 = i6 + queryMaterialShowDataByTypeAndPosition4 + queryMaterialShowDataByTypeAndPosition5 + i7 + queryMaterialShowDataByTypeAndPosition8;
                    if (Log.D) {
                        Log.d("NavigationRequest", "screenNavigationRuleDate()-realAngleAbnormityNum=" + queryMaterialShowDataByTypeAndPosition8 + " realMaterialShowNum=" + i8 + " sumNum=" + navigationFrequencyRuleEntity.sumNum);
                    }
                    if (i8 >= navigationFrequencyRuleEntity.sumNum) {
                        NavigationRuleFilterNotShowEntity navigationRuleFilterNotShowEntity = new NavigationRuleFilterNotShowEntity();
                        if (navigationFrequencyRuleEntity.position.equals(NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_POSITION_ALL)) {
                            arrayList.clear();
                            navigationRuleFilterNotShowEntity.position = NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_POSITION_ALL;
                            arrayList.add(navigationRuleFilterNotShowEntity);
                            return arrayList;
                        }
                        navigationRuleFilterNotShowEntity.position = navigationFrequencyRuleEntity.position;
                        arrayList.add(navigationRuleFilterNotShowEntity);
                    } else {
                        int i9 = navigationFrequencyRuleEntity.bubbleNum;
                        if (i9 >= 0 && i6 >= i9) {
                            arrayList.add(m(navigationFrequencyRuleEntity.position, 0, 0));
                            arrayList.add(m(navigationFrequencyRuleEntity.position, 1, 0));
                            arrayList.add(m(navigationFrequencyRuleEntity.position, 2, 0));
                        }
                        int i10 = navigationFrequencyRuleEntity.angleNum;
                        if (i10 >= 0 && queryMaterialShowDataByTypeAndPosition4 >= i10) {
                            arrayList.add(m(navigationFrequencyRuleEntity.position, 3, 0));
                        }
                        int i11 = navigationFrequencyRuleEntity.iconAbnormityNum;
                        if (i11 >= 0 && queryMaterialShowDataByTypeAndPosition5 >= i11) {
                            arrayList.add(m(navigationFrequencyRuleEntity.position, 4, 0));
                        }
                        int i12 = navigationFrequencyRuleEntity.angleAbnormityNum;
                        if (i12 >= 0 && queryMaterialShowDataByTypeAndPosition8 >= i12) {
                            arrayList.add(m(navigationFrequencyRuleEntity.position, 4, 1));
                        }
                        int i13 = navigationFrequencyRuleEntity.bubbleAbnormityNum;
                        if (i13 >= 0 && i7 >= i13) {
                            arrayList.add(m(navigationFrequencyRuleEntity.position, 4, 2));
                            arrayList.add(m(navigationFrequencyRuleEntity.position, 4, 3));
                        }
                        i5 = i3 + 1;
                        queryUsableFrequencyRuleData = list;
                        size = i2;
                        i4 = 0;
                    }
                }
                i5 = i3 + 1;
                queryUsableFrequencyRuleData = list;
                size = i2;
                i4 = 0;
            }
            if (Log.D) {
                Log.d("NavigationRequest", "screenNavigationRuleDate()-threadName=" + Thread.currentThread().getName() + " notShowEntities=" + arrayList);
            }
            return arrayList;
        }
        return arrayList;
    }

    public synchronized JSONObject k(List<NavigationRuleFilterNotShowEntity> list) {
        JSONObject jSONObject = new JSONObject();
        if (list != null && list.size() > 0) {
            JSONArray jSONArray = new JSONArray();
            try {
                for (NavigationRuleFilterNotShowEntity navigationRuleFilterNotShowEntity : list) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("position", navigationRuleFilterNotShowEntity.position);
                    jSONObject2.put("bubbleType", navigationRuleFilterNotShowEntity.bubbleType);
                    jSONObject2.put("combinationTypes", navigationRuleFilterNotShowEntity.combinationTypes);
                    jSONArray.put(jSONObject2);
                }
                jSONObject.put("data", jSONArray);
            } catch (JSONException e2) {
                if (OKLog.D) {
                    OKLog.e("UnifyRequestDataHolder", e2);
                }
            }
            if (Log.D) {
                Log.d("NavigationRequest", "convertNotShowMaterialToParams()-params=" + jSONObject);
            }
            return jSONObject;
        }
        return jSONObject;
    }

    public void o() {
        j.d();
        if (!n()) {
            if (Log.D) {
                Log.d("UnifyRequestDataHolder", "--\u5224\u65ad\u662f\u5426\u53ef\u4ee5\u8fdb\u884c\u8bf7\u6c42--");
                return;
            }
            return;
        }
        if (Log.D) {
            Log.d("UnifyRequestDataHolder", "--\u8fdb\u884c\u672c\u6b21\u8bf7\u6c42--");
        }
        new Thread(new b()).start();
        com.jingdong.app.mall.aura.j.e();
        new c(this).start();
    }

    public synchronized void p(JSONObject jSONObject) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("getBubbleInfo");
        httpSetting.setCacheMode(2);
        httpSetting.setHost(Configuration.getNgwHost());
        if (Log.D) {
            Log.d("NavigationRequest", "requestNavigationBubbleData-params=" + jSONObject);
        }
        if (jSONObject != null && !TextUtils.isEmpty(jSONObject.toString())) {
            httpSetting.setJsonParams(jSONObject);
        }
        int i2 = NavigationBase.getInstance().navigationCurrentMode;
        httpSetting.putJsonParam("currentMode", i2 + "");
        UnAddressInfo addressCacheAddressInfo = UnAddressSelectUtils.getAddressCacheAddressInfo();
        if (Log.D) {
            Log.d("Navigation_Bubble", "addressCacheAddressInfo=" + addressCacheAddressInfo);
        }
        if (addressCacheAddressInfo != null) {
            if (Log.D) {
                Log.d("Navigation_Bubble", "longitude=" + addressCacheAddressInfo.lng + "latitude=" + addressCacheAddressInfo.lat);
            }
            httpSetting.putJsonParam(PdLVBody.LONGITUDE, addressCacheAddressInfo.lng + "");
            httpSetting.putJsonParam(PdLVBody.LATITUDE, addressCacheAddressInfo.lat + "");
        }
        httpSetting.setListener(new a(i2));
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    public synchronized void q() {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("getBubbleFrequencyRuleInfo");
        httpSetting.putJsonParam("currentMode", NavigationBase.getInstance().navigationCurrentMode + "");
        httpSetting.setCacheMode(2);
        httpSetting.setHost(Configuration.getNgwHost());
        httpSetting.setListener(new d(this));
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }
}
