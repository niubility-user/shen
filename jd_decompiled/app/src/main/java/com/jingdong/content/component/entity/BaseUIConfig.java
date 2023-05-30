package com.jingdong.content.component.entity;

import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import com.jingdong.jdsdk.constant.JshopConst;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000f\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u00a2\u0006\u0004\b\"\u0010#J\u0010\u0010\u0003\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0005\u0010\u0004J\u0010\u0010\u0006\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0006\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0007\u0010\u0004J8\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00022\b\b\u0002\u0010\u000b\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000f\u001a\u00020\u000eH\u00d6\u0001\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0012\u001a\u00020\u0011H\u00d6\u0001\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u001a\u0010\u0016\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003\u00a2\u0006\u0004\b\u0016\u0010\u0017R\"\u0010\n\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\n\u0010\u0018\u001a\u0004\b\u0019\u0010\u0004\"\u0004\b\u001a\u0010\u001bR\"\u0010\u000b\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000b\u0010\u0018\u001a\u0004\b\u001c\u0010\u0004\"\u0004\b\u001d\u0010\u001bR\"\u0010\t\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\t\u0010\u0018\u001a\u0004\b\u001e\u0010\u0004\"\u0004\b\u001f\u0010\u001bR\"\u0010\b\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\b\u0010\u0018\u001a\u0004\b \u0010\u0004\"\u0004\b!\u0010\u001b\u00a8\u0006$"}, d2 = {"Lcom/jingdong/content/component/entity/BaseUIConfig;", "", "", "component1", "()F", "component2", "component3", "component4", JshopConst.JSHOP_PROMOTIO_W, JshopConst.JSHOP_PROMOTIO_H, JshopConst.JSHOP_PROMOTIO_X, JshopConst.JSHOP_PROMOTIO_Y, JDViewKitEventHelper.ActionType_Copy, "(FFFF)Lcom/jingdong/content/component/entity/BaseUIConfig;", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "F", "getX", "setX", "(F)V", "getY", "setY", "getH", "setH", "getW", "setW", "<init>", "(FFFF)V", "content-component-widget_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public final /* data */ class BaseUIConfig {
    private float h;
    private float w;
    private float x;
    private float y;

    public BaseUIConfig(float f2, float f3, float f4, float f5) {
        this.w = f2;
        this.h = f3;
        this.x = f4;
        this.y = f5;
    }

    public static /* synthetic */ BaseUIConfig copy$default(BaseUIConfig baseUIConfig, float f2, float f3, float f4, float f5, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            f2 = baseUIConfig.w;
        }
        if ((i2 & 2) != 0) {
            f3 = baseUIConfig.h;
        }
        if ((i2 & 4) != 0) {
            f4 = baseUIConfig.x;
        }
        if ((i2 & 8) != 0) {
            f5 = baseUIConfig.y;
        }
        return baseUIConfig.copy(f2, f3, f4, f5);
    }

    /* renamed from: component1  reason: from getter */
    public final float getW() {
        return this.w;
    }

    /* renamed from: component2  reason: from getter */
    public final float getH() {
        return this.h;
    }

    /* renamed from: component3  reason: from getter */
    public final float getX() {
        return this.x;
    }

    /* renamed from: component4  reason: from getter */
    public final float getY() {
        return this.y;
    }

    @NotNull
    public final BaseUIConfig copy(float w, float h2, float x, float y) {
        return new BaseUIConfig(w, h2, x, y);
    }

    public boolean equals(@Nullable Object other) {
        if (this != other) {
            if (other instanceof BaseUIConfig) {
                BaseUIConfig baseUIConfig = (BaseUIConfig) other;
                return Float.compare(this.w, baseUIConfig.w) == 0 && Float.compare(this.h, baseUIConfig.h) == 0 && Float.compare(this.x, baseUIConfig.x) == 0 && Float.compare(this.y, baseUIConfig.y) == 0;
            }
            return false;
        }
        return true;
    }

    public final float getH() {
        return this.h;
    }

    public final float getW() {
        return this.w;
    }

    public final float getX() {
        return this.x;
    }

    public final float getY() {
        return this.y;
    }

    public int hashCode() {
        return (((((Float.floatToIntBits(this.w) * 31) + Float.floatToIntBits(this.h)) * 31) + Float.floatToIntBits(this.x)) * 31) + Float.floatToIntBits(this.y);
    }

    public final void setH(float f2) {
        this.h = f2;
    }

    public final void setW(float f2) {
        this.w = f2;
    }

    public final void setX(float f2) {
        this.x = f2;
    }

    public final void setY(float f2) {
        this.y = f2;
    }

    @NotNull
    public String toString() {
        return "BaseUIConfig(w=" + this.w + ", h=" + this.h + ", x=" + this.x + ", y=" + this.y + ")";
    }
}
