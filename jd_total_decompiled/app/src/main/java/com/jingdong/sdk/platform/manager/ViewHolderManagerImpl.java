package com.jingdong.sdk.platform.manager;

import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.sdk.platform.base.BaseData;
import com.jingdong.sdk.platform.base.BaseViewHolder;
import com.jingdong.sdk.platform.base.ViewHolder;
import com.jingdong.sdk.platform.config.PlatformConfig;
import com.jingdong.sdk.platform.utils.PlatformTools;
import java.util.Iterator;
import java.util.List;
import jpbury.t;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public class ViewHolderManagerImpl implements ViewHolderManager {
    private ViewHolderStore mViewHolderStore = new ViewHolderStore();

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewHolderManagerImpl() {
        List<String> pluginList = PlatformConfig.getPluginList();
        if (pluginList == null || pluginList.isEmpty()) {
            return;
        }
        Iterator<String> it = pluginList.iterator();
        while (it.hasNext()) {
            doAllPlatfromInit(it.next());
        }
    }

    private final void doAllPlatfromInit(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            Class<?> cls = Class.forName(str);
            if (cls != null) {
                cls.getMethod(XView2Constants.XVIEW2_ACTION_INIT, ViewHolderManager.class).invoke(null, this);
            }
        } catch (ClassNotFoundException e2) {
            if (PlatformTools.D) {
                PlatformTools.d(t.f20145j, e2.getMessage());
            }
        } catch (IllegalAccessException e3) {
            if (PlatformTools.D) {
                PlatformTools.d(t.f20145j, e3.getMessage());
            }
        } catch (NoSuchMethodException e4) {
            if (PlatformTools.D) {
                PlatformTools.d(t.f20145j, e4.getMessage());
            }
        } catch (Exception e5) {
            if (PlatformTools.D) {
                PlatformTools.d(t.f20145j, e5.getMessage());
            }
        }
    }

    @Override // com.jingdong.sdk.platform.manager.ViewHolderManager
    public ViewHolder createViewHolder(Context context, BaseData baseData, String str) {
        return this.mViewHolderStore.createViewHolder(context, baseData, str, null);
    }

    @Override // com.jingdong.sdk.platform.manager.ViewHolderManager
    public Class<? extends BaseViewHolder> getClassById(String str) {
        return this.mViewHolderStore.getClassById(str);
    }

    @Override // com.jingdong.sdk.platform.manager.ViewHolderManager
    public Class<? extends BaseViewHolder> getClassByType(int i2) {
        return this.mViewHolderStore.getClassByType(i2);
    }

    @Override // com.jingdong.sdk.platform.manager.ViewHolderManager
    public String getClassNameById(String str) {
        return this.mViewHolderStore.getClassNameById(str);
    }

    @Override // com.jingdong.sdk.platform.manager.ViewHolderManager
    public String getClassNameByType(int i2) {
        return this.mViewHolderStore.getClassNameByType(i2);
    }

    @Override // com.jingdong.sdk.platform.manager.ViewHolderManager
    public String getIdByType(int i2) {
        return this.mViewHolderStore.getIdByType(i2);
    }

    @Override // com.jingdong.sdk.platform.manager.ViewHolderManager
    public int getTypeById(String str) {
        return this.mViewHolderStore.getTypeById(str);
    }

    @Override // com.jingdong.sdk.platform.manager.ViewHolderManager
    public int getViewHolderCount() {
        return this.mViewHolderStore.getViewHolderCount();
    }

    @Override // com.jingdong.sdk.platform.manager.ViewHolderManager
    public void register(String str, Class<? extends BaseViewHolder> cls) {
        this.mViewHolderStore.register(str, cls);
    }

    @Override // com.jingdong.sdk.platform.manager.ViewHolderManager
    public ViewHolder createViewHolder(Context context, BaseData baseData, String str, ViewGroup viewGroup) {
        return this.mViewHolderStore.createViewHolder(context, baseData, str, viewGroup);
    }
}
