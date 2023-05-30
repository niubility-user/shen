package com.jingdong.sdk.platform.floor.isv;

import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.jd.dynamic.apis.DYContainerConfig;
import com.jd.dynamic.apis.DynamicContainer;
import com.jd.dynamic.apis.IContainerCallback;
import com.jd.dynamic.apis.IDynamicDriver;
import com.jd.dynamic.base.CommFunction;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.base.IFunctionFactory;
import com.jd.dynamic.base.XMLParse;
import com.jd.dynamic.entity.ViewNode;
import com.jd.dynamic.lib.error.DynamicException;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.BaseActivity;
import com.jingdong.sdk.aac.model.BaseViewModel;
import com.jingdong.sdk.aac.navigator.BaseNavigator;
import com.jingdong.sdk.aac.ui.LifecycleBaseViewHolder;
import com.jingdong.sdk.platform.base.BaseEntity;
import com.jingdong.sdk.platform.base.BaseViewHolder;
import com.jingdong.sdk.platform.floor.BaseFloor;
import com.jingdong.sdk.platform.floor.entity.BaseFloorData;
import com.jingdong.sdk.platform.floor.entity.BaseTemplateEntity;
import com.jingdong.sdk.platform.floor.isv.BaseFunction;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes10.dex */
public class BaseDynFloor<K extends BaseFloorData, V extends BaseTemplateEntity, VM extends BaseViewModel, N extends BaseNavigator> extends BaseFloor<K, V, VM, N> {
    private DynamicTemplateEngine engine;
    protected String localTemp;
    protected String mBizFiled;
    private DynamicContainer mContainer;
    public JSONObject mData;
    private BaseFunction.DataSupport mDataSuport;

    public BaseDynFloor(Context context, K k2, String str, ViewGroup viewGroup) {
        super(context, k2, str, viewGroup);
        this.localTemp = "";
    }

    public CommFunction getFunction(BaseDynFloor<K, V, VM, N> baseDynFloor, Context context, String str, BaseFunction.DataSupport dataSupport) {
        K k2 = this.mFloorData;
        if (k2 == 0) {
            return null;
        }
        return ISVConst.getFunction(((BaseFloorData) k2).mMoudleName, baseDynFloor, context, str, dataSupport);
    }

    private InputStream getLocalXmlStream(String str) throws IOException {
        return getContext().getAssets().open(str + ".xml");
    }

    public void isvReport(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("app", "app");
        hashMap.put("m", ISVConst.getModelType(((BaseFloorData) this.mFloorData).mMoudleName));
        hashMap.put("mid", ((BaseTemplateEntity) this.mFloorEntity).mId);
        hashMap.put("bid", ((BaseTemplateEntity) this.mFloorEntity).bId);
        hashMap.put("t", 1);
        hashMap.put("st", 13);
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("msg", (Object) str);
        hashMap.put("ext", jDJSONObject.toJSONString());
        com.jingdong.sdk.lib.isvmonitor.a.a.a(hashMap);
    }

    private void onRefreshDyn() {
    }

    public void refreshView() {
        ViewGroup viewGroup = this.mRootView;
        if (viewGroup != null) {
            viewGroup.post(new BaseFloor<K, V, VM, N>.FloorRunnable() { // from class: com.jingdong.sdk.platform.floor.isv.BaseDynFloor.4
                {
                    BaseDynFloor.this = this;
                }

                @Override // com.jingdong.sdk.platform.floor.BaseFloor.FloorRunnable
                protected void runs() {
                    ((BaseViewHolder) BaseDynFloor.this).mRootView.removeAllViews();
                    ((BaseViewHolder) BaseDynFloor.this).mRootView.addView(BaseDynFloor.this.mContainer, new FrameLayout.LayoutParams(-1, -2));
                }
            });
        }
    }

    @Override // com.jingdong.sdk.aac.ui.LifecycleBaseViewHolder
    protected N createNavigator() {
        return null;
    }

    public Object getExtData() {
        return null;
    }

    public String getPackName() {
        return getContext() != null ? getContext().getPackageName() : "";
    }

    public String getSysCode() {
        K k2 = this.mFloorData;
        return k2 != 0 ? ISVConst.getSystemCode(((BaseFloorData) k2).mMoudleName) : "";
    }

    @Override // com.jingdong.sdk.aac.ui.LifecycleBaseViewHolder
    public Class<VM> getViewModelClass() {
        return null;
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    public void initView() {
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    public ViewGroup onCreatedView(ViewGroup viewGroup) {
        FrameLayout frameLayout = new FrameLayout(getContext());
        frameLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        return frameLayout;
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    public void onCreatedView() {
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    public void onDestroyView() {
    }

    public void setVisable(String str) {
        if (TextUtils.equals(str, "0")) {
            hideFloor();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    public /* bridge */ /* synthetic */ void showData(BaseEntity baseEntity) {
        showData((BaseDynFloor<K, V, VM, N>) ((BaseTemplateEntity) baseEntity));
    }

    public void updateData() {
        if (this.mData == null || this.mFloorData == 0) {
            return;
        }
        if (ISVConst.IS_DEBUG) {
            useLocal(this.mBizFiled);
            return;
        }
        this.mDataSuport = new BaseFunction.DataSupport(getSysCode(), this.mData, (BaseTemplateEntity) this.mFloorEntity);
        if (this.mContainer == null) {
            IDynamicDriver driver = DynamicSdk.getDriver();
            DYContainerConfig dYContainerConfig = new DYContainerConfig(getContext(), getSysCode(), this.mBizFiled, new IFunctionFactory() { // from class: com.jingdong.sdk.platform.floor.isv.BaseDynFloor.2
                {
                    BaseDynFloor.this = this;
                }

                @Override // com.jd.dynamic.base.IFunctionFactory
                public CommFunction createCommFunction(String str) {
                    BaseDynFloor baseDynFloor = BaseDynFloor.this;
                    return baseDynFloor.getFunction(baseDynFloor, baseDynFloor.getContext(), str, BaseDynFloor.this.mDataSuport);
                }
            });
            dYContainerConfig.setPackageName(getPackName());
            this.mContainer = driver.createContainer(dYContainerConfig);
        }
        DynamicContainer dynamicContainer = this.mContainer;
        if (dynamicContainer != null) {
            dynamicContainer.setData(this.mData);
            if (this.mContainer.load()) {
                refreshView();
            } else {
                this.mContainer.load(new IContainerCallback() { // from class: com.jingdong.sdk.platform.floor.isv.BaseDynFloor.3
                    {
                        BaseDynFloor.this = this;
                    }

                    @Override // com.jd.dynamic.apis.IContainerCallback
                    public void onError(@NotNull DynamicException dynamicException) {
                        BaseDynFloor.this.isvReport(dynamicException.toString());
                    }

                    @Override // com.jd.dynamic.apis.IContainerCallback
                    public void onSuccess() {
                        BaseDynFloor.this.refreshView();
                    }
                });
            }
        }
    }

    protected void useLocal(String str) {
        try {
            if ((this.mContext instanceof BaseActivity) && (this.mRootView instanceof FrameLayout)) {
                XMLParse xMLParse = new XMLParse(getLocalXmlStream(str));
                ViewNode parse = xMLParse.parse();
                parse.unBindMaps = xMLParse.unBindMaps;
                this.mDataSuport = new BaseFunction.DataSupport(getSysCode(), this.mData, (BaseTemplateEntity) this.mFloorEntity);
                if (this.engine == null) {
                    this.engine = new DynamicTemplateEngine(getContext().getPackageName(), (BaseActivity) this.mContext, (FrameLayout) this.mRootView, new IFunctionFactory() { // from class: com.jingdong.sdk.platform.floor.isv.BaseDynFloor.1
                        {
                            BaseDynFloor.this = this;
                        }

                        @Override // com.jd.dynamic.base.IFunctionFactory
                        public CommFunction createCommFunction(String str2) {
                            if (((LifecycleBaseViewHolder) BaseDynFloor.this).isDestroy || ((BaseViewHolder) BaseDynFloor.this).mFloorEntity == null) {
                                return null;
                            }
                            BaseDynFloor baseDynFloor = BaseDynFloor.this;
                            return baseDynFloor.getFunction(baseDynFloor, baseDynFloor.getContext(), str2, BaseDynFloor.this.mDataSuport);
                        }
                    });
                }
                this.mData.toString();
                this.engine.initTemplate(parse, this.mData, null);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void showData(V v) {
        V v2 = this.mFloorEntity;
        if (v2 != 0 && !TextUtils.isEmpty(((BaseTemplateEntity) v2).mfStyleId)) {
            this.mBizFiled = ((BaseTemplateEntity) this.mFloorEntity).mfStyleId;
        } else {
            isvReport("mfStyleId is empty");
            hideFloor();
        }
        Object obj = v.mData;
        if (obj instanceof String) {
            try {
                this.mData = new JSONObject((String) v.mData);
                updateData();
            } catch (JSONException unused) {
                isvReport("jsonObj parse error");
                hideFloor();
            }
        } else if (obj instanceof JSONObject) {
            this.mData = (JSONObject) obj;
            updateData();
        } else if (obj instanceof JDJSONObject) {
            try {
                this.mData = new JSONObject(((JDJSONObject) obj).toJSONString());
                updateData();
            } catch (JSONException unused2) {
                isvReport("jsonObj parse error");
                hideFloor();
            }
        } else {
            hideFloor();
        }
    }
}
