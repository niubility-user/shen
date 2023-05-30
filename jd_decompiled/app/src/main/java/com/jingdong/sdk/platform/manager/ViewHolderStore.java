package com.jingdong.sdk.platform.manager;

import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.jingdong.sdk.platform.base.BaseData;
import com.jingdong.sdk.platform.base.BaseViewHolder;
import com.jingdong.sdk.platform.base.UnProguard;
import com.jingdong.sdk.platform.base.ViewHolder;
import com.jingdong.sdk.platform.floor.constant.BaseFloorConstant;
import com.jingdong.sdk.platform.utils.PlatformTools;
import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public class ViewHolderStore extends UnProguard {
    private static final int START_INDEX = 10000;
    private static final String TAG = "ViewHolderStore";
    private final LinkedHashMap<String, Class<? extends BaseViewHolder>> FLOOR_ID_CLASS = new LinkedHashMap<>(32);

    public ViewHolder createViewHolder(Context context, BaseData baseData, String str, ViewGroup viewGroup) {
        Class<? extends BaseViewHolder> classById = getClassById(str);
        if (classById == null) {
            return null;
        }
        try {
            Constructor<?> constructor = classById.getConstructors()[0];
            constructor.setAccessible(true);
            return (BaseViewHolder) constructor.newInstance(context, baseData, str, viewGroup);
        } catch (Exception e2) {
            if (PlatformTools.D) {
                e2.printStackTrace();
                return null;
            }
            return null;
        }
    }

    public Class<? extends BaseViewHolder> getClassById(String str) {
        Class<? extends BaseViewHolder> cls;
        try {
            if (str.startsWith("bpConfig_")) {
                cls = this.FLOOR_ID_CLASS.get(BaseFloorConstant.PLATFORM_FLOOR_PUPPET);
            } else {
                cls = this.FLOOR_ID_CLASS.get(str);
            }
            return cls;
        } catch (Exception e2) {
            if (PlatformTools.D) {
                e2.printStackTrace();
            }
            return null;
        }
    }

    public Class<? extends BaseViewHolder> getClassByType(int i2) {
        Set<String> keySet = this.FLOOR_ID_CLASS.keySet();
        if (keySet == null || keySet.isEmpty()) {
            return null;
        }
        int i3 = 1;
        for (String str : keySet) {
            if (i2 == i3 + 10000) {
                return this.FLOOR_ID_CLASS.get(str);
            }
            i3++;
        }
        return null;
    }

    public String getClassNameById(String str) {
        try {
            return this.FLOOR_ID_CLASS.get(str).getName();
        } catch (Exception e2) {
            if (PlatformTools.D) {
                e2.printStackTrace();
            }
            return "";
        }
    }

    public String getClassNameByType(int i2) {
        Set<String> keySet = this.FLOOR_ID_CLASS.keySet();
        if (keySet == null || keySet.isEmpty()) {
            return "";
        }
        int i3 = 1;
        for (String str : keySet) {
            if (i2 == i3 + 10000) {
                return this.FLOOR_ID_CLASS.get(str).getName();
            }
            i3++;
        }
        return "";
    }

    public String getIdByType(int i2) {
        Set<String> keySet = this.FLOOR_ID_CLASS.keySet();
        if (keySet == null || keySet.isEmpty()) {
            return "";
        }
        int i3 = 1;
        for (String str : keySet) {
            if (i2 == i3 + 10000) {
                return str;
            }
            i3++;
        }
        return "";
    }

    public int getTypeById(String str) {
        int i2;
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        Set<String> keySet = this.FLOOR_ID_CLASS.keySet();
        if (keySet != null && !keySet.isEmpty()) {
            Iterator<String> it = keySet.iterator();
            i2 = 1;
            while (it.hasNext()) {
                if (str.equals(it.next())) {
                    break;
                }
                i2++;
            }
        }
        i2 = -1;
        return i2 == -1 ? i2 : i2 + 10000;
    }

    public int getViewHolderCount() {
        return this.FLOOR_ID_CLASS.size();
    }

    public final void register(String str, Class<? extends BaseViewHolder> cls) {
        if (cls == null || !TextUtils.isEmpty(str)) {
            if (!this.FLOOR_ID_CLASS.containsKey(str)) {
                this.FLOOR_ID_CLASS.put(str, cls);
                return;
            }
            throw new IllegalArgumentException(str + " has been register for class " + getClassNameById(str));
        }
    }
}
