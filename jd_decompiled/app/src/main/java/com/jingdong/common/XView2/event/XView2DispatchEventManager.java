package com.jingdong.common.XView2.event;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.XView2.XView2;
import com.jingdong.common.XView2.XView2Manager;
import com.jingdong.common.XView2.business.PermissionBridge;
import com.jingdong.common.XView2.business.RecommendTips;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.XView2.entity.XViewConfigEntity;
import com.jingdong.common.XView2.layer.IReLoadLayer;
import com.jingdong.common.XView2.utils.XView2Utils;
import com.jingdong.common.XView2.utils.log.XViewLogPrint;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.lang.reflect.Method;
import java.util.ArrayList;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class XView2DispatchEventManager implements IPopEvent, IBusinessEvent {
    protected Context mActivity;
    public XView2 mXView2Instance;
    public XViewConfigEntity mXViewConfigEntity;
    private ArrayList<XViewConfigEntity.LayersEntity> mLayersEntityList = new ArrayList<>();
    public Handler mHandler = new Handler(Looper.getMainLooper());

    public XView2DispatchEventManager(XView2 xView2) {
        this.mXView2Instance = xView2;
    }

    @Override // com.jingdong.common.XView2.event.IPopEvent
    public void backAction(JSONObject jSONObject) {
        XView2 xView2;
        if (jSONObject == null) {
            return;
        }
        try {
            XViewConfigEntity.LayersEntity popLayerById = XView2Utils.getPopLayerById(this.mLayersEntityList, jSONObject.optString(XView2Constants.LAYER_ID));
            if (popLayerById == null || (xView2 = this.mXView2Instance) == null) {
                return;
            }
            xView2.startXView2ById(popLayerById, jSONObject);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jingdong.common.XView2.event.IPopEvent
    public void changeLayout(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        try {
            XView2 xView2 = this.mXView2Instance;
            if (xView2 != null) {
                xView2.changeXView2Layout(jSONObject);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jingdong.common.XView2.event.IPopEvent
    public void closeLayer(JSONObject jSONObject) {
        XViewConfigEntity.LayersEntity popAbleEntity;
        if (jSONObject == null) {
            return;
        }
        try {
            XView2 xView2 = this.mXView2Instance;
            if (xView2 == null || (popAbleEntity = xView2.getPopAbleEntity(jSONObject)) == null) {
                return;
            }
            this.mXView2Instance.closeXView2ById(popAbleEntity);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jingdong.common.XView2.event.IPopEvent
    public void dispatchPopEvent(Context context, Bundle bundle) {
        Class<?> cls;
        Context context2;
        if (context == null || bundle == null) {
            return;
        }
        this.mActivity = context;
        String string = bundle.getString("params");
        if (TextUtils.isEmpty(string)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(string);
            String optString = jSONObject.optString("methodName");
            String optString2 = jSONObject.optString("moduleName");
            String optString3 = jSONObject.optString("action");
            JSONObject optJSONObject = jSONObject.optJSONObject("params");
            XViewLogPrint.JDXLog.e(XView2Constants.TAG, "moduleName " + optString2);
            if (!TextUtils.isEmpty(optString3)) {
                optString = optString3.trim();
                optString2 = JumpUtil.XVIEW2_NXVIEW;
            }
            if (!TextUtils.isEmpty(optString2)) {
                if (optString2.equals(((BaseActivity) context).getClass().getName())) {
                    cls = context.getClass();
                    context2 = context;
                } else {
                    if (!optString2.equals(XView2Constants.XVIEW2_POP_EVENT_NAME) && !optString2.equals(JumpUtil.XVIEW2_NXVIEW)) {
                        cls = Class.forName(optString2);
                        context2 = cls.newInstance();
                    }
                    cls = XView2DispatchEventManager.class;
                    context2 = this;
                }
            } else {
                cls = XView2.class;
                context2 = this.mXView2Instance;
            }
            try {
                Method method = cls.getMethod(optString, JSONObject.class);
                if (method != null) {
                    method.setAccessible(true);
                    method.invoke(context2, optJSONObject);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    @Override // com.jingdong.common.XView2.event.IBusinessEvent
    public void openLocationPermission(JSONObject jSONObject) {
        PermissionBridge.requestLocationWithScene((Activity) this.mActivity);
    }

    @Override // com.jingdong.common.XView2.event.IBusinessEvent
    public void openRecommend(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        int optInt = jSONObject.optInt(XView2Constants.STYLEID);
        String str = XView2Constants.OPEN_RECOMMEND + optInt;
        RecommendTips.openRecommendSwitch(this.mActivity, optInt);
    }

    @Override // com.jingdong.common.XView2.event.IPopEvent
    public void preview(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        final String optString = jSONObject.optString(XView2Constants.TARGETOPENAPP);
        if (TextUtils.isEmpty(optString)) {
            return;
        }
        String optString2 = jSONObject.optString("layerID");
        if (TextUtils.isEmpty(optString2)) {
            return;
        }
        String optString3 = jSONObject.optString("testH5Url");
        HttpGroup.CustomOnAllListener customOnAllListener = new HttpGroup.CustomOnAllListener() { // from class: com.jingdong.common.XView2.event.XView2DispatchEventManager.3
            {
                XView2DispatchEventManager.this = this;
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                JDJSONObject optJSONObject;
                XViewConfigEntity.TargetsEntity targetsEntity;
                if (httpResponse == null || httpResponse.getFastJsonObject() == null) {
                    return;
                }
                try {
                    JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                    if (fastJsonObject == null || (optJSONObject = fastJsonObject.optJSONObject("data")) == null) {
                        return;
                    }
                    XViewConfigEntity xViewConfigEntity = (XViewConfigEntity) JDJSON.parseObject(optJSONObject.toString(), XViewConfigEntity.class);
                    if (xViewConfigEntity != null) {
                        XView2Manager.getInstance().setAllConfigXViewConfigEntity(xViewConfigEntity);
                    }
                    if (XView2Manager.getInstance().getAllConfigXViewConfigEntity() != null && !XView2Utils.isListEmpty(XView2Manager.getInstance().getAllConfigXViewConfigEntity().targets) && XView2Manager.getInstance().getAllConfigXViewConfigEntity().targets.get(0) != null && (targetsEntity = XView2Manager.getInstance().getAllConfigXViewConfigEntity().targets.get(0)) != null && !XView2Utils.isListEmpty(targetsEntity.layers) && targetsEntity.layers.get(0) != null) {
                        StringBuilder sb = new StringBuilder();
                        XViewConfigEntity.LayersEntity layersEntity = targetsEntity.layers.get(0);
                        sb.append(layersEntity.layerId);
                        sb.append("_Preview");
                        layersEntity.layerId = sb.toString();
                    }
                    XView2Utils.clickJump(XView2DispatchEventManager.this.mActivity, optString);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                String str = "onError" + httpError.getMessage() + "error" + httpError.toString();
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        };
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("xviewPreview");
        httpSetting.putJsonParam(XView2Constants.LAYER_ID, optString2);
        httpSetting.putJsonParam("env", "pro");
        httpSetting.putJsonParam("testH5Url", optString3);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setHost("beta-api.m.jd.com");
        httpSetting.setListener(customOnAllListener);
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    @Override // com.jingdong.common.XView2.event.IPopEvent
    public void removeLayer(JSONObject jSONObject) {
        XViewConfigEntity.LayersEntity popAbleEntity;
        if (jSONObject == null) {
            return;
        }
        try {
            XView2 xView2 = this.mXView2Instance;
            if (xView2 == null || (popAbleEntity = xView2.getPopAbleEntity(jSONObject)) == null) {
                return;
            }
            this.mXView2Instance.closeXView2ById(popAbleEntity);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jingdong.common.XView2.event.IPopEvent
    public void setLayerVisible(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        try {
            XView2 xView2 = this.mXView2Instance;
            if (xView2 == null || xView2.getPopAbleEntity(jSONObject) == null) {
                return;
            }
            this.mXView2Instance.setXViewVisible(jSONObject);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jingdong.common.XView2.event.IPopEvent
    public void showLayer(final JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        try {
            XView2 xView2 = this.mXView2Instance;
            if (xView2 == null) {
                return;
            }
            final XViewConfigEntity.LayersEntity popAbleEntity = xView2.getPopAbleEntity(jSONObject);
            if (popAbleEntity != null) {
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    startXView2ById(popAbleEntity, jSONObject);
                } else {
                    this.mHandler.post(new Runnable() { // from class: com.jingdong.common.XView2.event.XView2DispatchEventManager.1
                        {
                            XView2DispatchEventManager.this = this;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            XView2DispatchEventManager.this.startXView2ById(popAbleEntity, jSONObject);
                        }
                    });
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jingdong.common.XView2.event.IPopEvent
    public void startXView2ById(XViewConfigEntity.LayersEntity layersEntity, final JSONObject jSONObject) {
        if (layersEntity != null) {
            XView2 xView2 = this.mXView2Instance;
            IReLoadLayer iReLoadLayer = new IReLoadLayer() { // from class: com.jingdong.common.XView2.event.XView2DispatchEventManager.2
                {
                    XView2DispatchEventManager.this = this;
                }

                @Override // com.jingdong.common.XView2.layer.IReLoadLayer
                public void reloadLayer(XViewConfigEntity.LayersEntity layersEntity2) {
                    int i2;
                    if (layersEntity2 == null || (i2 = layersEntity2.mHasReloadCount) != 0) {
                        return;
                    }
                    layersEntity2.mHasReloadCount = i2 + 1;
                    XView2DispatchEventManager.this.startXView2ById(layersEntity2, jSONObject);
                }
            };
            layersEntity.mReLoadLayerCallBack = iReLoadLayer;
            if (xView2.isCanPopByParams(jSONObject, iReLoadLayer)) {
                this.mXView2Instance.startXView2ById(layersEntity, jSONObject);
            }
        }
    }

    public void upDateLayerEntities(ArrayList<XViewConfigEntity.LayersEntity> arrayList) {
        this.mLayersEntityList = arrayList;
    }
}
