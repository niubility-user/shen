package com.jingdong.common.XView2;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.jd.framework.json.JDJSON;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.cleanmvp.ui.BaseFragment;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.XView2.datapare.IXView2DataCallBack;
import com.jingdong.common.XView2.datapare.JDXView2DataPresenter;
import com.jingdong.common.XView2.entity.XViewConfigEntity;
import com.jingdong.common.XView2.page.PageFile;
import com.jingdong.common.XView2.strategy.downloader.XViewDownloadClient;
import com.jingdong.common.XView2.strategy.preDownLoadManager.PreDownLoadManager;
import com.jingdong.common.XView2.utils.XView2Utils;
import com.jingdong.common.XView2.utils.log.XViewLogPrint;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.jump.OpenAppJumpController;
import com.jingdong.common.login.LoginEvent;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.jdsdk.JdSdk;
import java.lang.ref.SoftReference;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class XView2Manager {
    private static volatile XView2Manager mInstance;
    public static boolean mIsHasXViewData;
    public static boolean mIsXViewEnable;
    private Activity mCurrentActivity;
    private JDPerfActivityLifecycleCallbacks mJDPerfActivityLifecycleCallbacks;
    private JDXView2DataPresenter mJDXViewDataPresenter;
    private ConcurrentHashMap<Integer, XView2> mXViewActivityMap;
    private XViewConfigEntity mXViewConfigEntity;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public class JDPerfActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
        JDPerfActivityLifecycleCallbacks() {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(@NonNull Activity activity, Bundle bundle) {
            XView2Manager.this.onCreate(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(@NonNull Activity activity) {
            XView2Manager.this.onDestroy(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(@NonNull Activity activity) {
            XView2Manager.this.mCurrentActivity = null;
            XView2Manager.this.onPause(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(@NonNull Activity activity) {
            XView2Manager.this.mCurrentActivity = activity;
            XView2Manager.this.onResume(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(@NonNull Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(@NonNull Activity activity) {
            XView2Manager.this.onStop(activity);
        }
    }

    public static XView2Manager getInstance() {
        if (mInstance == null) {
            synchronized (XView2Manager.class) {
                if (mInstance == null) {
                    mInstance = new XView2Manager();
                }
            }
        }
        return mInstance;
    }

    private void initXViewFloat(Application application) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onCreate(@NonNull Activity activity) {
        ConcurrentHashMap<Integer, XView2> concurrentHashMap = this.mXViewActivityMap;
        if (concurrentHashMap == null) {
            return;
        }
        XView2 xView2 = concurrentHashMap.get(Integer.valueOf(System.identityHashCode(activity)));
        if (xView2 != null) {
            xView2.onCreate(activity);
            return;
        }
        XView2 xView22 = new XView2();
        xView22.configXView(this.mXViewConfigEntity);
        xView22.onCreate(activity);
        this.mXViewActivityMap.put(Integer.valueOf(System.identityHashCode(activity)), xView22);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDestroy(@NonNull Activity activity) {
        ConcurrentHashMap<Integer, XView2> concurrentHashMap = this.mXViewActivityMap;
        if (concurrentHashMap != null) {
            XView2 xView2 = concurrentHashMap.get(Integer.valueOf(System.identityHashCode(activity)));
            if (xView2 != null) {
                xView2.onDestroy(activity);
            }
            this.mXViewActivityMap.remove(Integer.valueOf(System.identityHashCode(activity)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onPause(Activity activity) {
        XView2 xView2;
        ConcurrentHashMap<Integer, XView2> concurrentHashMap = this.mXViewActivityMap;
        if (concurrentHashMap == null || (xView2 = concurrentHashMap.get(Integer.valueOf(System.identityHashCode(activity)))) == null) {
            return;
        }
        xView2.onPause(activity);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onResume(@NonNull Activity activity) {
        XView2 xView2;
        ConcurrentHashMap<Integer, XView2> concurrentHashMap = this.mXViewActivityMap;
        if (concurrentHashMap == null || (xView2 = concurrentHashMap.get(Integer.valueOf(System.identityHashCode(activity)))) == null) {
            return;
        }
        xView2.onResume(activity);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onStop(@NonNull Activity activity) {
        XView2 xView2;
        ConcurrentHashMap<Integer, XView2> concurrentHashMap = this.mXViewActivityMap;
        if (concurrentHashMap == null || (xView2 = concurrentHashMap.get(Integer.valueOf(System.identityHashCode(activity)))) == null) {
            return;
        }
        xView2.onStop(activity);
    }

    public XViewConfigEntity getAllConfigXViewConfigEntity() {
        return this.mXViewConfigEntity;
    }

    public ConcurrentHashMap<Integer, XView2> getConcurrentHashMap() {
        return this.mXViewActivityMap;
    }

    public Activity getCurrentActivity() {
        return this.mCurrentActivity;
    }

    public JSONObject getLayerBasicInfoByLayerId(String str) {
        Activity activity;
        XView2 xView2;
        if (TextUtils.isEmpty(str) || (activity = this.mCurrentActivity) == null || (xView2 = this.mXViewActivityMap.get(Integer.valueOf(System.identityHashCode(activity)))) == null) {
            return null;
        }
        return xView2.getLayerBasicInfoByLayerId(str);
    }

    public JSONArray getPopAbleLayersByBuzID(JSONObject jSONObject, Context context) {
        Activity activity;
        XView2 xView2;
        if (jSONObject == null || context == null || TextUtils.isEmpty(jSONObject.optString(XView2Constants.BUZID)) || (activity = this.mCurrentActivity) == null || (xView2 = this.mXViewActivityMap.get(Integer.valueOf(System.identityHashCode(activity)))) == null) {
            return null;
        }
        return xView2.getPopAbleLayersByBuzID(jSONObject);
    }

    public boolean getXViewCanPopStatus(JSONObject jSONObject) {
        Activity activity;
        XView2 xView2;
        if (jSONObject == null || (activity = this.mCurrentActivity) == null || (xView2 = this.mXViewActivityMap.get(Integer.valueOf(System.identityHashCode(activity)))) == null) {
            return false;
        }
        return xView2.getXViewPopStatus(jSONObject);
    }

    public boolean getXViewCanPopStatusByLayerId(String str) {
        Activity activity;
        XView2 xView2;
        if (TextUtils.isEmpty(str) || (activity = this.mCurrentActivity) == null || (xView2 = this.mXViewActivityMap.get(Integer.valueOf(System.identityHashCode(activity)))) == null) {
            return false;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(XView2Constants.LAYER_ID, str);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return xView2.getXViewPopStatus(jSONObject);
    }

    public void install(Application application) {
        if (application == null) {
            return;
        }
        if (SwitchQueryFetcher.getSwitchIntValue(SwitchQueryFetcher.XVIEW2_ENABLE, 0) == 4) {
            XViewLogPrint.JDXLog.d(XView2Constants.TAG, "XView2.0\u5173\u95ed");
            mIsXViewEnable = false;
            return;
        }
        mIsXViewEnable = true;
        JDPerfActivityLifecycleCallbacks jDPerfActivityLifecycleCallbacks = new JDPerfActivityLifecycleCallbacks();
        this.mJDPerfActivityLifecycleCallbacks = jDPerfActivityLifecycleCallbacks;
        application.registerActivityLifecycleCallbacks(jDPerfActivityLifecycleCallbacks);
        this.mJDXViewDataPresenter = new JDXView2DataPresenter(application);
        PageFile.getPageFile().buildFiles();
        this.mXViewActivityMap = new ConcurrentHashMap<>();
        XView2Utils.safeRegisterEventBus(this);
        XView2Utils.registerXViewWidget();
        XViewDownloadClient.init(XViewDownloadClient.newBuilder(JdSdk.getInstance().getApplicationContext()));
        XViewLogPrint.initLogProxy();
        initXViewFloat(application);
    }

    public void onEventMainThread(BaseEvent baseEvent) {
        if (baseEvent instanceof LoginEvent) {
            if (baseEvent.getType() == LoginEvent.TYPE_LOGIN || baseEvent.getType() == LoginEvent.TYPE_LOGOUT) {
                requestXViewData();
            }
        }
    }

    public void requestXViewData() {
        JDXView2DataPresenter jDXView2DataPresenter = this.mJDXViewDataPresenter;
        if (jDXView2DataPresenter != null) {
            jDXView2DataPresenter.getXViewConfigData(new IXView2DataCallBack() { // from class: com.jingdong.common.XView2.XView2Manager.1
                @Override // com.jingdong.common.XView2.datapare.IXView2DataCallBack
                public void fail(String str) {
                }

                @Override // com.jingdong.common.XView2.datapare.IXView2DataCallBack
                public void success(Object obj) {
                    if (obj == null) {
                        return;
                    }
                    XView2Manager.this.mXViewConfigEntity = (XViewConfigEntity) obj;
                    XView2Manager.mIsHasXViewData = true;
                    PreDownLoadManager.getManager().preDownLoadCache(XView2Manager.this.mXViewConfigEntity);
                    if (XView2Manager.this.mXViewActivityMap == null || XView2Manager.this.mCurrentActivity == null) {
                        return;
                    }
                    if (!TextUtils.isEmpty(XView2Utils.getTargetNameByActivity(new SoftReference(XView2Manager.this.mCurrentActivity), XView2Manager.this.mXViewConfigEntity))) {
                        XView2 xView2 = (XView2) XView2Manager.this.mXViewActivityMap.get(Integer.valueOf(System.identityHashCode(XView2Manager.this.mCurrentActivity)));
                        if (xView2 != null) {
                            XViewLogPrint.JDXLog.e(XView2Constants.TAG, "needResume " + JDJSON.toJSONString(obj));
                            xView2.configXView(XView2Manager.this.mXViewConfigEntity);
                            xView2.onResume(XView2Manager.this.mCurrentActivity);
                            FragmentManager supportFragmentManager = ((BaseActivity) XView2Manager.this.mCurrentActivity).getSupportFragmentManager();
                            if (supportFragmentManager != null) {
                                List<Fragment> fragments = supportFragmentManager.getFragments();
                                if (XView2Utils.isListEmpty(fragments)) {
                                    return;
                                }
                                for (Fragment fragment : fragments) {
                                    if (fragment instanceof BaseFragment) {
                                        xView2.onXViewFragmentResumed(fragment);
                                    }
                                }
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    XViewLogPrint.JDXLog.e(XView2Constants.TAG, "not hit");
                }
            });
        }
    }

    public void setAllConfigXViewConfigEntity(XViewConfigEntity xViewConfigEntity) {
        this.mXViewConfigEntity = xViewConfigEntity;
    }

    public void startXView2(Context context, String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3) {
        Bundle bundle = new Bundle();
        try {
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put("moduleName", XView2Constants.XVIEW2_POP_EVENT_NAME);
            jSONObject4.put("methodName", str);
            JSONObject jSONObject5 = new JSONObject();
            if (jSONObject != null && jSONObject.length() > 0) {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    jSONObject5.put(next, jSONObject.opt(next));
                }
            }
            XView2Utils.convertArgs(jSONObject5, XView2Constants.STATE, jSONObject2);
            XView2Utils.convertArgs(jSONObject5, XView2Constants.APPENDURLPARAMS, jSONObject3);
            jSONObject4.put("params", jSONObject5);
            bundle.putString("params", jSONObject4.toString());
            JumpUtil.execJumpByDes(JumpUtil.XVIEW2_NXVIEW, context, bundle);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void startXView2ByOpenAppUrl(Context context, String str) {
        if (TextUtils.isEmpty(str) || context == null || !(context instanceof BaseActivity)) {
            return;
        }
        OpenAppJumpController.dispatchJumpRequestInApp(context, Uri.parse(str));
    }

    public void startXView2LayerScrolling(JSONObject jSONObject) {
        XView2 xView2;
        Activity activity = this.mCurrentActivity;
        if (activity == null || (xView2 = this.mXViewActivityMap.get(Integer.valueOf(System.identityHashCode(activity)))) == null) {
            return;
        }
        xView2.startXView2LayerScrolling(jSONObject);
    }

    public void stopXView2LayerScrolling(JSONObject jSONObject) {
        XView2 xView2;
        Activity activity = this.mCurrentActivity;
        if (activity == null || (xView2 = this.mXViewActivityMap.get(Integer.valueOf(System.identityHashCode(activity)))) == null) {
            return;
        }
        xView2.stopXView2LayerScrolling(jSONObject);
    }

    public void uninstall(Application application) {
        JDPerfActivityLifecycleCallbacks jDPerfActivityLifecycleCallbacks = this.mJDPerfActivityLifecycleCallbacks;
        if (jDPerfActivityLifecycleCallbacks != null) {
            application.unregisterActivityLifecycleCallbacks(jDPerfActivityLifecycleCallbacks);
            this.mJDPerfActivityLifecycleCallbacks = null;
        }
        this.mXViewActivityMap = null;
    }
}
