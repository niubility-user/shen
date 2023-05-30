package com.jingdong.sdk.platform.floor;

import android.text.TextUtils;
import com.jingdong.sdk.platform.manager.ViewHolderManagerProxy;
import com.jingdong.sdk.platform.utils.PlatformTools;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* loaded from: classes10.dex */
class FloorManagerImpl implements FloorManager {
    private static final String TAG = "FloorManagerImpl";
    private String mMoudleName;
    private int floorIndex = 20000;
    private final LinkedHashMap<String, Class<? extends BaseFloor>> FLOOR_ID_CLASS = new LinkedHashMap<>(32);
    private final LinkedHashMap<String, Integer> FLOOR_ID_TYPE = new LinkedHashMap<>(32);
    private final ArrayList<String> FLOOR_TYPE_REUSE_FLOOR = new ArrayList<>(8);
    private final ArrayList<String> FLOOR_TYPE_LINE_FLOOR = new ArrayList<>(8);
    private String emptyDiliverLine = "empty";

    /* JADX INFO: Access modifiers changed from: package-private */
    public FloorManagerImpl(String str) {
        this.mMoudleName = str;
        if (this.floorIndex <= ViewHolderManagerProxy.getInstance().getViewHolderCount() + 10000 + 20) {
            this.floorIndex *= 10;
        }
    }

    private synchronized int generateFloorIndex() {
        int i2;
        i2 = this.floorIndex + 1;
        this.floorIndex = i2;
        return i2;
    }

    @Override // com.jingdong.sdk.platform.floor.FloorManager
    public Class<? extends BaseFloor> getClassById(String str) {
        try {
            return this.FLOOR_ID_CLASS.get(str);
        } catch (Exception e2) {
            if (PlatformTools.D) {
                PlatformTools.e(TAG, "mId = " + str + "has not been register!");
            }
            PlatformTools.catchException(e2);
            return null;
        }
    }

    @Override // com.jingdong.sdk.platform.floor.FloorManager
    public Class<? extends BaseFloor> getClassByType(int i2) {
        return getClassById(getIdByType(i2));
    }

    @Override // com.jingdong.sdk.platform.floor.FloorManager
    public String getIdByType(int i2) {
        Set<Map.Entry<String, Integer>> entrySet;
        if (i2 > 0 && (entrySet = this.FLOOR_ID_TYPE.entrySet()) != null && !entrySet.isEmpty()) {
            for (Map.Entry<String, Integer> entry : entrySet) {
                if (entry != null && entry.getValue().intValue() == i2) {
                    return entry.getKey();
                }
            }
        }
        return null;
    }

    @Override // com.jingdong.sdk.platform.floor.FloorManager
    public int getTypeById(String str) {
        try {
            return this.FLOOR_ID_TYPE.get(str).intValue();
        } catch (Exception e2) {
            if (PlatformTools.D) {
                PlatformTools.e(TAG, "mId " + str + " has not been register!");
                PlatformTools.catchException(e2);
            }
            return 0;
        }
    }

    @Override // com.jingdong.sdk.platform.floor.FloorManager
    public boolean isEmptyLine(String str) {
        return TextUtils.equals(str, this.emptyDiliverLine);
    }

    @Override // com.jingdong.sdk.platform.floor.FloorManager
    public boolean isFloorReUsed(String str) {
        return this.FLOOR_TYPE_REUSE_FLOOR.contains(str);
    }

    @Override // com.jingdong.sdk.platform.floor.FloorManager
    public boolean isLine(String str) {
        return this.FLOOR_TYPE_LINE_FLOOR.contains(str);
    }

    @Override // com.jingdong.sdk.platform.floor.FloorManager
    public void registerFloor(String str, Class<? extends BaseFloor> cls) {
        registerFloor(str, cls, false, false);
    }

    @Override // com.jingdong.sdk.platform.floor.FloorManager
    public final void setEmptyDiliverLine(String str) {
        this.emptyDiliverLine = str;
    }

    @Override // com.jingdong.sdk.platform.floor.FloorManager
    public boolean isFloorReUsed(int i2) {
        return isFloorReUsed(getIdByType(i2));
    }

    @Override // com.jingdong.sdk.platform.floor.FloorManager
    public boolean isLine(int i2) {
        return isLine(getIdByType(i2));
    }

    @Override // com.jingdong.sdk.platform.floor.FloorManager
    public final void registerFloor(String str, Class<? extends BaseFloor> cls, boolean z, boolean z2) {
        if (cls == null || TextUtils.isEmpty(str)) {
            return;
        }
        if (!this.FLOOR_ID_CLASS.containsKey(str)) {
            this.FLOOR_ID_CLASS.put(str, cls);
            if (!this.FLOOR_ID_TYPE.containsKey(str)) {
                this.FLOOR_ID_TYPE.put(str, Integer.valueOf(generateFloorIndex()));
            }
            if (z) {
                this.FLOOR_TYPE_LINE_FLOOR.add(str);
            }
            if (z2) {
                this.FLOOR_TYPE_REUSE_FLOOR.add(str);
                return;
            }
            return;
        }
        throw new IllegalArgumentException(str + " has been register for class " + getClassById(str).getName());
    }
}
