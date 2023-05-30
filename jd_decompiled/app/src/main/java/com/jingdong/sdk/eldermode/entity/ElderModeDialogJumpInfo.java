package com.jingdong.sdk.eldermode.entity;

import com.jd.lib.babel.task.viewkit.VKEventUtil;
import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import com.jingdong.jdsdk.constant.CartConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\n\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u0003\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\b\u0010\u0004J0\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\t\u001a\u00020\u00022\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0005H\u00d6\u0001\u00a2\u0006\u0004\b\u000e\u0010\u0007J\u0010\u0010\u000f\u001a\u00020\u0002H\u00d6\u0001\u00a2\u0006\u0004\b\u000f\u0010\u0004J\u001a\u0010\u0012\u001a\u00020\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003\u00a2\u0006\u0004\b\u0012\u0010\u0013R\u001b\u0010\n\u001a\u0004\u0018\u00010\u00058\u0006@\u0006\u00a2\u0006\f\n\u0004\b\n\u0010\u0014\u001a\u0004\b\u0015\u0010\u0007R\u0019\u0010\t\u001a\u00020\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\t\u0010\u0016\u001a\u0004\b\u0017\u0010\u0004R\u0019\u0010\u000b\u001a\u00020\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u000b\u0010\u0016\u001a\u0004\b\u0018\u0010\u0004\u00a8\u0006\u001b"}, d2 = {"Lcom/jingdong/sdk/eldermode/entity/ElderModeDialogJumpInfo;", "", "", "component1", "()I", "", "component2", "()Ljava/lang/String;", "component3", "jumpType", CartConstant.KEY_JUMPURL, VKEventUtil.JUMP_NEEDLOGIN, JDViewKitEventHelper.ActionType_Copy, "(ILjava/lang/String;I)Lcom/jingdong/sdk/eldermode/entity/ElderModeDialogJumpInfo;", "toString", "hashCode", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getJumpUrl", "I", "getJumpType", "getNeedLogin", "<init>", "(ILjava/lang/String;I)V", "eldermodelib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes7.dex */
public final /* data */ class ElderModeDialogJumpInfo {
    private final int jumpType;
    @Nullable
    private final String jumpUrl;
    private final int needLogin;

    public ElderModeDialogJumpInfo(int i2, @Nullable String str, int i3) {
        this.jumpType = i2;
        this.jumpUrl = str;
        this.needLogin = i3;
    }

    public static /* synthetic */ ElderModeDialogJumpInfo copy$default(ElderModeDialogJumpInfo elderModeDialogJumpInfo, int i2, String str, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i2 = elderModeDialogJumpInfo.jumpType;
        }
        if ((i4 & 2) != 0) {
            str = elderModeDialogJumpInfo.jumpUrl;
        }
        if ((i4 & 4) != 0) {
            i3 = elderModeDialogJumpInfo.needLogin;
        }
        return elderModeDialogJumpInfo.copy(i2, str, i3);
    }

    /* renamed from: component1  reason: from getter */
    public final int getJumpType() {
        return this.jumpType;
    }

    @Nullable
    /* renamed from: component2  reason: from getter */
    public final String getJumpUrl() {
        return this.jumpUrl;
    }

    /* renamed from: component3  reason: from getter */
    public final int getNeedLogin() {
        return this.needLogin;
    }

    @NotNull
    public final ElderModeDialogJumpInfo copy(int jumpType, @Nullable String jumpUrl, int needLogin) {
        return new ElderModeDialogJumpInfo(jumpType, jumpUrl, needLogin);
    }

    public boolean equals(@Nullable Object other) {
        if (this != other) {
            if (other instanceof ElderModeDialogJumpInfo) {
                ElderModeDialogJumpInfo elderModeDialogJumpInfo = (ElderModeDialogJumpInfo) other;
                return this.jumpType == elderModeDialogJumpInfo.jumpType && Intrinsics.areEqual(this.jumpUrl, elderModeDialogJumpInfo.jumpUrl) && this.needLogin == elderModeDialogJumpInfo.needLogin;
            }
            return false;
        }
        return true;
    }

    public final int getJumpType() {
        return this.jumpType;
    }

    @Nullable
    public final String getJumpUrl() {
        return this.jumpUrl;
    }

    public final int getNeedLogin() {
        return this.needLogin;
    }

    public int hashCode() {
        int i2 = this.jumpType * 31;
        String str = this.jumpUrl;
        return ((i2 + (str != null ? str.hashCode() : 0)) * 31) + this.needLogin;
    }

    @NotNull
    public String toString() {
        return "ElderModeDialogJumpInfo(jumpType=" + this.jumpType + ", jumpUrl=" + this.jumpUrl + ", needLogin=" + this.needLogin + ")";
    }
}
