package com.jingdong.sdk.platform.floor.isv;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.sdk.platform.base.ICloneableData;
import com.jingdong.sdk.platform.floor.isv.adapterView.StoreMananger;
import com.jingdong.sdk.platform.floor.isv.store.OptionStore;
import com.jingdong.sdk.platform.floor.isv.store.ViewStore;

/* loaded from: classes10.dex */
public class ISVSdk {
    Builer builer;

    /* loaded from: classes10.dex */
    public static class Builer {
        Class<? extends BaseDynFloor> baseFloor;
        Class<? extends BaseFunction> baseFunctionClass;
        Context context;
        String dynSystemCode;
        IBaseCooperateExt iBaseCooperateExt;
        String isvType;
        String moduleName;

        public Builer baseCooperateExt(IBaseCooperateExt iBaseCooperateExt) {
            this.iBaseCooperateExt = iBaseCooperateExt;
            return this;
        }

        public Builer baseFloor(Class<? extends BaseDynFloor> cls) {
            this.baseFloor = cls;
            return this;
        }

        public Builer baseFunctionClass(Class<? extends BaseFunction> cls) {
            this.baseFunctionClass = cls;
            return this;
        }

        public ISVSdk build() {
            return new ISVSdk(this);
        }

        public Builer context(Context context) {
            this.context = context;
            return this;
        }

        public Builer dynSystemCode(String str) {
            this.dynSystemCode = str;
            return this;
        }

        public Builer isvType(String str) {
            this.isvType = str;
            return this;
        }

        public Builer moduleName(String str) {
            this.moduleName = str;
            return this;
        }
    }

    public OptionStore getOptionStore() {
        Builer builer = this.builer;
        if (builer == null || TextUtils.isEmpty(builer.moduleName)) {
            return null;
        }
        return StoreMananger.getOptionStore(this.builer.moduleName);
    }

    public ViewStore getViewStore() {
        Builer builer = this.builer;
        if (builer == null || TextUtils.isEmpty(builer.moduleName)) {
            return null;
        }
        return StoreMananger.getViewStore(this.builer.moduleName);
    }

    public FloorCooperateManager initCooperateManager() {
        FloorCooperateManager floorCooperateManager = new FloorCooperateManager(this.builer.moduleName);
        Builer builer = this.builer;
        floorCooperateManager.init(builer.context, (ICloneableData) null, builer.iBaseCooperateExt);
        return floorCooperateManager;
    }

    public void initDynamic() {
        Builer builer = this.builer;
        ISVConst.init(builer.moduleName, builer.dynSystemCode, builer.isvType, builer.baseFloor, builer.baseFunctionClass);
    }

    public void preLoad() {
        if (this.builer == null) {
            return;
        }
        OptionStore optionStore = getOptionStore();
        if (optionStore != null) {
            optionStore.preLoad(this.builer.context);
        }
        ViewStore viewStore = getViewStore();
        if (viewStore != null) {
            viewStore.preLoad(this.builer.context);
        }
    }

    private ISVSdk(Builer builer) {
        this.builer = builer;
    }
}
