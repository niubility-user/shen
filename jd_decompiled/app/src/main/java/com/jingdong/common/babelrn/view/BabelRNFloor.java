package com.jingdong.common.babelrn.view;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import com.facebook.react.ReactRootView;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.babelrn.BabelRNManager;
import com.jingdong.common.babelrn.entity.RNFloorInfoEntity;
import com.jingdong.common.babelrn.module.RNFloorEngin;
import com.jingdong.sdk.utils.DPIUtil;
import java.lang.reflect.Field;

/* loaded from: classes5.dex */
public class BabelRNFloor extends FrameLayout implements RNFloorEngin.IRNFloorBridge {
    private BabelRNManager babelRNManager;
    private final String floorId;
    private boolean forceRecreate;
    private ReactRootView reactRootView;
    private RNFloorInfoEntity rnInfoEntity;

    public BabelRNFloor(Context context) {
        super(context);
        this.floorId = String.valueOf(hashCode());
        this.forceRecreate = false;
        init();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Activity getActivityFromView(View view) {
        if (view != null) {
            for (Context context = view.getContext(); context instanceof ContextWrapper; context = ((ContextWrapper) context).getBaseContext()) {
                if (context instanceof Activity) {
                    return (Activity) context;
                }
            }
        }
        if (BaseFrameUtil.getInstance().getCurrentMyActivity() != null) {
            return (Activity) BaseFrameUtil.getInstance().getCurrentMyActivity();
        }
        return null;
    }

    private boolean isInit(ReactRootView reactRootView) {
        if (reactRootView != null) {
            try {
                Field declaredField = ReactRootView.class.getDeclaredField("mReactInstanceManager");
                declaredField.setAccessible(true);
                return declaredField.get(reactRootView) != null;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    private void recreateReactView() {
        this.reactRootView.unmountReactApplication();
        removeView(this.reactRootView);
        ReactRootView reactRootView = new ReactRootView(getContext());
        this.reactRootView = reactRootView;
        addView(reactRootView, new FrameLayout.LayoutParams(-1, -2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCombinedLayoutParams(int i2, int i3) {
        ReactRootView reactRootView = this.reactRootView;
        if (reactRootView != null) {
            ViewGroup.LayoutParams layoutParams = reactRootView.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.width = i2;
                layoutParams.height = i3;
                this.reactRootView.setLayoutParams(layoutParams);
            } else {
                this.reactRootView.setLayoutParams(new FrameLayout.LayoutParams(i2, i3));
            }
        }
        ViewGroup.LayoutParams layoutParams2 = getLayoutParams();
        if (layoutParams2 != null) {
            layoutParams2.width = i2;
            layoutParams2.height = i3;
            setLayoutParams(layoutParams2);
            return;
        }
        setLayoutParams(new ViewGroup.LayoutParams(i2, i3));
    }

    @Override // com.jingdong.common.babelrn.module.RNFloorEngin.IRNFloorBridge
    public BabelRNManager getBabelRNManager() {
        return this.babelRNManager;
    }

    public void init() {
        ReactRootView reactRootView = new ReactRootView(getContext());
        this.reactRootView = reactRootView;
        addView(reactRootView, new FrameLayout.LayoutParams(-1, -2));
        setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
    }

    public void setBabelRNManager(BabelRNManager babelRNManager) {
        this.babelRNManager = babelRNManager;
    }

    @Override // com.jingdong.common.babelrn.module.RNFloorEngin.IRNFloorBridge
    public void setFloorHeight(final int i2, final int i3) {
        post(new Runnable() { // from class: com.jingdong.common.babelrn.view.BabelRNFloor.1
            @Override // java.lang.Runnable
            public void run() {
                int i4;
                ViewGroup.LayoutParams layoutParams = BabelRNFloor.this.getLayoutParams();
                Activity activityFromView = BabelRNFloor.getActivityFromView(BabelRNFloor.this);
                int appWidth = activityFromView != null ? DPIUtil.getAppWidth(activityFromView) : 0;
                int i5 = i2;
                if (i5 >= 1 && appWidth >= 1) {
                    i4 = (i3 * appWidth) / i5;
                } else {
                    i4 = i3;
                }
                BabelRNFloor.this.setCombinedLayoutParams(layoutParams.width, i4);
                if (BabelRNFloor.this.rnInfoEntity == null || BabelRNFloor.this.rnInfoEntity.width <= 0 || appWidth <= 0) {
                    return;
                }
                BabelRNFloor.this.rnInfoEntity.height = (BabelRNFloor.this.rnInfoEntity.width * i4) / appWidth;
            }
        });
    }

    @Override // com.jingdong.common.babelrn.module.RNFloorEngin.IRNFloorBridge
    public void setForceRecreate(boolean z) {
        this.forceRecreate = z;
    }

    public void update(@NonNull RNFloorInfoEntity rNFloorInfoEntity) {
        ReactRootView reactRootView;
        this.rnInfoEntity = rNFloorInfoEntity;
        if (this.forceRecreate) {
            recreateReactView();
            this.forceRecreate = false;
        }
        BabelRNManager babelRNManager = this.babelRNManager;
        if (babelRNManager != null && babelRNManager.getReactManager() != null) {
            if (rNFloorInfoEntity.width != 0 && rNFloorInfoEntity.height != 0) {
                Activity activityFromView = getActivityFromView(this);
                if (activityFromView != null) {
                    setCombinedLayoutParams(-1, (DPIUtil.getAppWidth(activityFromView) * rNFloorInfoEntity.height) / rNFloorInfoEntity.width);
                } else {
                    setCombinedLayoutParams(-1, (DPIUtil.getWidth(getContext()) * rNFloorInfoEntity.height) / rNFloorInfoEntity.width);
                }
            }
            try {
                if (!isInit(this.reactRootView)) {
                    Bundle paramsBundle = this.babelRNManager.getParamsBundle();
                    paramsBundle.putString("data", rNFloorInfoEntity.rndata);
                    paramsBundle.putString(XView2Constants.STYLEID, rNFloorInfoEntity.styleId);
                    paramsBundle.putString("floor_event", this.floorId);
                    paramsBundle.putString("floor_id", this.floorId);
                    RNFloorEngin.getInstance().regiseter(this.floorId, this);
                    this.babelRNManager.startReactApplication(this.reactRootView, paramsBundle);
                    return;
                } else if (TextUtils.isEmpty(rNFloorInfoEntity.subStyleId) || (reactRootView = this.reactRootView) == null) {
                    return;
                } else {
                    Bundle appProperties = reactRootView.getAppProperties();
                    appProperties.putString("data", rNFloorInfoEntity.rndata);
                    this.reactRootView.setAppProperties(appProperties);
                    return;
                }
            } catch (Exception unused) {
                return;
            }
        }
        setCombinedLayoutParams(-1, 1);
    }

    @Override // com.jingdong.common.babelrn.module.RNFloorEngin.IRNFloorBridge
    public void updateScreenSize() {
        ReactRootView reactRootView = this.reactRootView;
        if (reactRootView != null) {
            reactRootView.updateScreenSize();
        }
    }
}
