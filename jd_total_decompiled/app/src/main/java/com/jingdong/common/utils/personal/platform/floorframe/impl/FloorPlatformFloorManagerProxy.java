package com.jingdong.common.utils.personal.platform.floorframe.impl;

import com.jingdong.common.utils.personal.platform.floorframe.isv.ISVDynFloor;
import com.jingdong.common.utils.personal.platform.floorframe.isv.ISVDynFunction;
import com.jingdong.sdk.platform.PlatformHelper;
import com.jingdong.sdk.platform.floor.BaseFloor;
import com.jingdong.sdk.platform.floor.FloorManager;
import com.jingdong.sdk.platform.floor.isv.ISVConst;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0015\u001a\u00020\u0007\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u0003\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0005\u001a\u00020\u0000H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0006J7\u0010\f\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00072\u001e\u0010\u000b\u001a\u001a\u0012\u0016\b\u0001\u0012\u0012\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\n0\tH\u0016\u00a2\u0006\u0004\b\f\u0010\rJG\u0010\f\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00072\u001e\u0010\u000b\u001a\u001a\u0012\u0016\b\u0001\u0012\u0012\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\n0\t2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000eH\u0016\u00a2\u0006\u0004\b\f\u0010\u0011R\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0015\u001a\u00020\u00078\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0016\u00a8\u0006\u0019"}, d2 = {"Lcom/jingdong/common/utils/personal/platform/floorframe/impl/FloorPlatformFloorManagerProxy;", "", "", "initISVDynamic", "()V", "getFloorManager", "()Lcom/jingdong/common/utils/personal/platform/floorframe/impl/FloorPlatformFloorManagerProxy;", "", "mId", "Ljava/lang/Class;", "Lcom/jingdong/sdk/platform/floor/BaseFloor;", "floorClass", "registerFloor", "(Ljava/lang/String;Ljava/lang/Class;)V", "", "isLine", "reUsed", "(Ljava/lang/String;Ljava/lang/Class;ZZ)V", "Lcom/jingdong/sdk/platform/floor/FloorManager;", "mFloorManager", "Lcom/jingdong/sdk/platform/floor/FloorManager;", "moduleName", "Ljava/lang/String;", "<init>", "(Ljava/lang/String;)V", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public class FloorPlatformFloorManagerProxy {
    private FloorManager mFloorManager;
    private final String moduleName;

    public FloorPlatformFloorManagerProxy(@NotNull String str) {
        this.moduleName = str;
    }

    private final void initISVDynamic() {
        ISVConst.init(this.moduleName, "myjd", "", ISVDynFloor.class, ISVDynFunction.class);
    }

    @NotNull
    public FloorPlatformFloorManagerProxy getFloorManager() {
        if (this.mFloorManager == null) {
            this.mFloorManager = PlatformHelper.getFloorManager(this.moduleName);
        }
        initISVDynamic();
        return this;
    }

    public void registerFloor(@NotNull String mId, @NotNull Class<? extends BaseFloor<?, ?, ?, ?>> floorClass) {
        if (PlatformHelper.isFloorRegister(this.moduleName, mId)) {
            return;
        }
        registerFloor(mId, floorClass, false, false);
    }

    public void registerFloor(@NotNull String mId, @NotNull Class<? extends BaseFloor<?, ?, ?, ?>> floorClass, boolean isLine, boolean reUsed) {
        FloorManager floorManager;
        if (PlatformHelper.isFloorRegister(this.moduleName, mId) || (floorManager = this.mFloorManager) == null) {
            return;
        }
        floorManager.registerFloor(mId, floorClass, isLine, reUsed);
    }
}
