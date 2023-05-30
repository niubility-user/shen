package com.jingdong.sdk.platform.floor;

import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.sdk.aac.util.SyncEventBus;
import com.jingdong.sdk.platform.PlatformHelper;
import com.jingdong.sdk.platform.floor.constant.BaseFloorConstant;
import com.jingdong.sdk.platform.floor.entity.BaseFloorData;
import com.jingdong.sdk.platform.floor.entity.BaseTemplateEntity;
import com.jingdong.sdk.platform.floor.entity.FloorShowData;
import com.jingdong.sdk.platform.floor.listener.OnFloorShowedListener;
import com.jingdong.sdk.platform.floor.utils.FloorTemplateCreator;
import com.jingdong.sdk.platform.utils.PlatformTools;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes10.dex */
class FloorBuilderImpl extends BaseFloorConstant implements FloorBuilder {
    private static final String TAG = "FloorBuilderImpl";
    private FloorTemplateCreator floorTemplateCreator;
    private BaseFloorData mBaseFloorData;
    private FloorManager mFloorManager;
    private ArrayList<BaseTemplateEntity> mFloors;
    private OnFloorShowedListener mOnFloorShowListener;
    private ArrayList<BaseTemplateEntity> mTemplates;
    private SyncEventBus.EventBusListener listener = new SyncEventBus.EventBusListener() { // from class: com.jingdong.sdk.platform.floor.FloorBuilderImpl.1
        @Override // com.jingdong.sdk.aac.util.SyncEventBus.EventBusListener
        public String getActionName() {
            return BaseFloorConstant.ACTION_FLOOR_BASE;
        }

        @Override // com.jingdong.sdk.aac.util.SyncEventBus.EventBusListener
        public Object getData(String str) {
            return null;
        }

        @Override // com.jingdong.sdk.aac.util.SyncEventBus.EventBusListener
        public boolean isValid() {
            return true;
        }

        @Override // com.jingdong.sdk.aac.util.SyncEventBus.EventBusListener
        public void onEvent(String str, Object obj) {
            if (TextUtils.equals(str, BaseFloorConstant.EVENT_FLOOR_HIDE_FLOOR)) {
                Bundle bundle = null;
                if (obj != null && (obj instanceof Bundle)) {
                    bundle = (Bundle) obj;
                }
                if (bundle != null) {
                    FloorBuilderImpl.this.hideFloor(bundle.getString("key"), bundle.getString("key1"));
                }
            }
        }
    };
    private boolean mIsDestroy = false;

    private FloorBuilderImpl(BaseFloorData baseFloorData) {
        if (baseFloorData != null && !TextUtils.isEmpty(baseFloorData.mMoudleName) && !TextUtils.isEmpty(baseFloorData.mManageKey)) {
            this.mBaseFloorData = baseFloorData;
            this.mFloors = new ArrayList<>();
            this.mFloorManager = FloorManagerProxy.getInstances(baseFloorData.mMoudleName);
            SyncEventBus.getInstances(baseFloorData.mManageKey).register(this.listener);
            return;
        }
        throw new IllegalArgumentException("moudleName and managerKey cannot be null!");
    }

    private void add(BaseTemplateEntity baseTemplateEntity) {
        this.mFloors.add(baseTemplateEntity);
    }

    private BaseTemplateEntity buildDiliverLine(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return buildLineFloor(str, -1);
    }

    private BaseTemplateEntity buildFloor(BaseTemplateEntity baseTemplateEntity, int i2) {
        int indexOf;
        ArrayList<BaseTemplateEntity> arrayList = this.mFloors;
        if (arrayList == null || (indexOf = arrayList.indexOf(baseTemplateEntity)) == -1) {
            if (i2 == -1) {
                add(baseTemplateEntity);
            } else {
                this.mFloors.add(i2, baseTemplateEntity);
            }
        } else if (PlatformTools.D) {
            PlatformTools.d("FloorBuilderProxy", "index = " + indexOf);
        }
        baseTemplateEntity.isShow = false;
        return baseTemplateEntity;
    }

    private void buildFloorByTemplate() {
        BaseTemplateEntity buildDiliverLine;
        this.mFloors.clear();
        ArrayList<BaseTemplateEntity> arrayList = this.mTemplates;
        if (arrayList != null && !arrayList.isEmpty()) {
            Iterator<BaseTemplateEntity> it = this.mTemplates.iterator();
            while (it.hasNext()) {
                BaseTemplateEntity next = it.next();
                if (next != null && next.isValid) {
                    if (TextUtils.isEmpty(next.mId)) {
                        if (PlatformTools.D) {
                            throw new NullPointerException("Template: " + next.getClass().getName() + " mId can not be empty !!!!");
                        }
                    } else {
                        if (!TextUtils.isEmpty(next.divideLine) && !this.mFloorManager.isEmptyLine(next.divideLine) && (buildDiliverLine = buildDiliverLine(next.divideLine)) != null) {
                            buildDiliverLine.sortId = next.sortId;
                            buildDiliverLine.refId = next.refId;
                        }
                        buildFloor(next);
                    }
                }
            }
        }
        showFloors(build());
    }

    private BaseTemplateEntity buildLineFloor(String str, int i2) {
        FloorTemplateCreator floorTemplateCreator = this.floorTemplateCreator;
        if (floorTemplateCreator != null) {
            BaseTemplateEntity createFloorTemplate = floorTemplateCreator.createFloorTemplate(str, "");
            if (i2 >= 0) {
                this.mFloors.add(i2, createFloorTemplate);
            } else {
                add(createFloorTemplate);
            }
            createFloorTemplate.addToFloor(true);
            createFloorTemplate.isShow = false;
            return createFloorTemplate;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static FloorBuilderImpl createInstance(BaseFloorData baseFloorData, OnFloorShowedListener onFloorShowedListener) {
        FloorBuilderImpl floorBuilderImpl = new FloorBuilderImpl(baseFloorData);
        floorBuilderImpl.setOnFloorShowedListener(onFloorShowedListener);
        return floorBuilderImpl;
    }

    private boolean existFloor(BaseTemplateEntity baseTemplateEntity) {
        return hasTemplate() && this.mTemplates.indexOf(baseTemplateEntity) != -1;
    }

    private int findInsertIndex(int i2) {
        int i3;
        ArrayList<BaseTemplateEntity> arrayList = this.mFloors;
        if (arrayList == null || arrayList.isEmpty()) {
            return -1;
        }
        int i4 = 0;
        Iterator<BaseTemplateEntity> it = this.mFloors.iterator();
        while (true) {
            if (!it.hasNext()) {
                i3 = -1;
                break;
            } else if (it.next().sortId > i2) {
                i3 = i4;
                break;
            } else {
                i4++;
            }
        }
        return i3 == -1 ? i4 : i3;
    }

    private boolean hasTemplate() {
        ArrayList<BaseTemplateEntity> arrayList = this.mTemplates;
        return (arrayList == null || arrayList.isEmpty()) ? false : true;
    }

    private boolean isLine(BaseTemplateEntity baseTemplateEntity) {
        return this.mFloorManager.isLine(baseTemplateEntity.mId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyFloorChange(BaseTemplateEntity baseTemplateEntity, boolean z) {
        ArrayList<BaseTemplateEntity> build = build();
        if (PlatformTools.D && baseTemplateEntity != null) {
            PlatformTools.d(TAG, "notifyFloorChange start -- isadd = " + z + baseTemplateEntity.mId + " _ " + baseTemplateEntity.bId);
        }
        OnFloorShowedListener onFloorShowedListener = this.mOnFloorShowListener;
        if (onFloorShowedListener != null) {
            onFloorShowedListener.onFloorChange(new FloorShowData(build, baseTemplateEntity, z));
        }
    }

    private int replaceFloor(BaseTemplateEntity baseTemplateEntity) {
        ArrayList<BaseTemplateEntity> arrayList;
        if (TextUtils.isEmpty(baseTemplateEntity.refId) || (arrayList = this.mFloors) == null || arrayList.isEmpty()) {
            return -1;
        }
        Iterator<BaseTemplateEntity> it = this.mFloors.iterator();
        boolean z = false;
        int i2 = 0;
        while (it.hasNext()) {
            BaseTemplateEntity next = it.next();
            if (TextUtils.equals(next.refId, baseTemplateEntity.refId)) {
                next.isValid = false;
                baseTemplateEntity.sortId = next.sortId;
                i2++;
                z = true;
            } else if (z) {
                return i2;
            } else {
                i2++;
            }
        }
        return -1;
    }

    private synchronized void showFloors(ArrayList<BaseTemplateEntity> arrayList) {
        if (arrayList != null) {
            if (!arrayList.isEmpty()) {
                if (PlatformTools.D) {
                    PlatformTools.d(TAG, "showFloors start -- = floorEntitys.size() " + arrayList.size());
                }
                OnFloorShowedListener onFloorShowedListener = this.mOnFloorShowListener;
                if (onFloorShowedListener != null) {
                    onFloorShowedListener.onFloorChange(new FloorShowData(arrayList));
                }
            }
        }
    }

    @Override // com.jingdong.sdk.platform.floor.FloorBuilder
    public ArrayList<BaseTemplateEntity> build() {
        ArrayList<BaseTemplateEntity> arrayList = new ArrayList<>();
        ArrayList<BaseTemplateEntity> arrayList2 = this.mFloors;
        if (arrayList2 != null && !arrayList2.isEmpty()) {
            Iterator<BaseTemplateEntity> it = this.mFloors.iterator();
            BaseTemplateEntity baseTemplateEntity = null;
            while (it.hasNext()) {
                BaseTemplateEntity next = it.next();
                if (next.isAddToFloor()) {
                    int floorViewType = getFloorViewType(next.mId);
                    if (floorViewType > 0) {
                        next.itemViewType = floorViewType;
                        if (PlatformTools.D) {
                            PlatformTools.d(TAG, "build floor ---" + next.mId + " itemViewType =  " + floorViewType);
                        }
                        if (!isLine(next)) {
                            if (baseTemplateEntity != null && isLine(baseTemplateEntity)) {
                                arrayList.add(baseTemplateEntity);
                            }
                            arrayList.add(next);
                        } else if (baseTemplateEntity != null) {
                            if (isLine(baseTemplateEntity) && this.mFloorManager.getTypeById(baseTemplateEntity.mId) <= this.mFloorManager.getTypeById(next.mId)) {
                            }
                        }
                        baseTemplateEntity = next;
                    } else {
                        next.addToFloor(false);
                        if (PlatformTools.D) {
                            PlatformTools.d(TAG, "build floor ---" + next.mId + " has not be register!!!!!");
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    @Override // com.jingdong.sdk.platform.floor.FloorBuilder
    public int getFloorViewType(String str) {
        return PlatformHelper.getFloorTypeById(this.mBaseFloorData.mMoudleName, str);
    }

    @Override // com.jingdong.sdk.platform.floor.FloorBuilder
    public void hideFloor(String str, String str2) {
        hideFloor(new BaseTemplateEntity(str, str2));
    }

    @Override // com.jingdong.sdk.platform.floor.FloorBuilder
    public void onDestroy() {
        this.mIsDestroy = true;
        this.listener = null;
        this.mOnFloorShowListener = null;
        this.mBaseFloorData = null;
        ArrayList<BaseTemplateEntity> arrayList = this.mFloors;
        if (arrayList != null) {
            arrayList.clear();
            this.mFloors = null;
        }
        ArrayList<BaseTemplateEntity> arrayList2 = this.mTemplates;
        if (arrayList2 != null) {
            arrayList2.clear();
            this.mTemplates = null;
        }
    }

    @Override // com.jingdong.sdk.platform.floor.FloorBuilder
    public void setFloorTemplateCreator(FloorTemplateCreator floorTemplateCreator) {
        this.floorTemplateCreator = floorTemplateCreator;
    }

    @Override // com.jingdong.sdk.platform.floor.FloorBuilder
    public void setOnFloorShowedListener(OnFloorShowedListener onFloorShowedListener) {
        this.mOnFloorShowListener = onFloorShowedListener;
    }

    @Override // com.jingdong.sdk.platform.floor.FloorBuilder
    public void showAllFloors(ArrayList<BaseTemplateEntity> arrayList) {
        if (this.mIsDestroy || arrayList == null || arrayList.isEmpty()) {
            return;
        }
        this.mTemplates = arrayList;
        buildFloorByTemplate();
    }

    @Override // com.jingdong.sdk.platform.floor.FloorBuilder
    public void showFloor(BaseTemplateEntity baseTemplateEntity) {
        if (this.mIsDestroy || !existFloor(baseTemplateEntity) || this.mFloors.indexOf(baseTemplateEntity) == -1 || !baseTemplateEntity.isAddToFloor()) {
            return;
        }
        notifyFloorChange(baseTemplateEntity, true);
    }

    @Override // com.jingdong.sdk.platform.floor.FloorBuilder
    public void updateFloors() {
        notifyFloorChange(null, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public static FloorBuilderImpl createInstance(BaseFloorData baseFloorData) {
        return new FloorBuilderImpl(baseFloorData);
    }

    @Override // com.jingdong.sdk.platform.floor.FloorBuilder
    public void hideFloor(final BaseTemplateEntity baseTemplateEntity) {
        if (baseTemplateEntity == null) {
            return;
        }
        SyncEventBus.postToMainThread(new Runnable() { // from class: com.jingdong.sdk.platform.floor.FloorBuilderImpl.2
            @Override // java.lang.Runnable
            public void run() {
                int indexOf;
                if (FloorBuilderImpl.this.mIsDestroy || FloorBuilderImpl.this.mFloors == null || (indexOf = FloorBuilderImpl.this.mFloors.indexOf(baseTemplateEntity)) == -1) {
                    return;
                }
                if (PlatformTools.D) {
                    PlatformTools.d("FloorBuilderProxy", "index = " + indexOf + baseTemplateEntity.mId + baseTemplateEntity.bId);
                }
                BaseTemplateEntity baseTemplateEntity2 = (BaseTemplateEntity) FloorBuilderImpl.this.mFloors.get(indexOf);
                if (baseTemplateEntity2.isAddToFloor()) {
                    baseTemplateEntity2.addToFloor(false);
                    FloorBuilderImpl.this.notifyFloorChange(baseTemplateEntity2, false);
                }
            }
        });
    }

    private BaseTemplateEntity buildFloor(BaseTemplateEntity baseTemplateEntity) {
        return buildFloor(baseTemplateEntity, -1);
    }
}
