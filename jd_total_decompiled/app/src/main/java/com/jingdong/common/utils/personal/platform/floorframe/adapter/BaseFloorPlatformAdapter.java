package com.jingdong.common.utils.personal.platform.floorframe.adapter;

import android.content.Context;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.entity.personal.base.BaseFloorEntity;
import com.jingdong.common.utils.personal.extensions.ReflectExtensionsKt;
import com.jingdong.common.utils.personal.platform.floorframe.isv.ISVDynFloor;
import com.jingdong.sdk.platform.floor.BaseFloor;
import com.jingdong.sdk.platform.floor.adapter.FloorRecyclerViewAdapter;
import com.jingdong.sdk.platform.floor.entity.BaseFloorData;
import com.jingdong.sdk.platform.floor.entity.BaseTemplateEntity;
import com.jingdong.sdk.platform.floor.floors.CommonFloor;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u0012\u0006\u0010\u0017\u001a\u00020\u0016\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0015\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u001f\u0010\n\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\n\u0010\u000bJ\r\u0010\r\u001a\u00020\f\u00a2\u0006\u0004\b\r\u0010\u000eR\u0016\u0010\u000f\u001a\u00020\f8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0003\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0011\u00a8\u0006\u001a"}, d2 = {"Lcom/jingdong/common/utils/personal/platform/floorframe/adapter/BaseFloorPlatformAdapter;", "Lcom/jingdong/sdk/platform/floor/adapter/FloorRecyclerViewAdapter;", "", "spanCount", "", "setSpanCount", "(I)V", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "holder", "position", "onBindViewHolder", "(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;I)V", "Landroidx/recyclerview/widget/GridLayoutManager$SpanSizeLookup;", "getSpanSizeLookup", "()Landroidx/recyclerview/widget/GridLayoutManager$SpanSizeLookup;", "spanSizeLookup", "Landroidx/recyclerview/widget/GridLayoutManager$SpanSizeLookup;", "I", "Landroid/content/Context;", AnnoConst.Constructor_Context, "Lcom/jingdong/sdk/platform/floor/entity/BaseFloorData;", "baseFloorData", "Landroidx/recyclerview/widget/RecyclerView;", "recyclerView", "<init>", "(Landroid/content/Context;Lcom/jingdong/sdk/platform/floor/entity/BaseFloorData;Landroidx/recyclerview/widget/RecyclerView;)V", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public abstract class BaseFloorPlatformAdapter extends FloorRecyclerViewAdapter {
    private int spanCount;
    private final GridLayoutManager.SpanSizeLookup spanSizeLookup;

    public BaseFloorPlatformAdapter(@NotNull Context context, @NotNull BaseFloorData baseFloorData, @NotNull RecyclerView recyclerView) {
        super(context, baseFloorData, recyclerView);
        this.spanCount = -1;
        this.spanSizeLookup = new GridLayoutManager.SpanSizeLookup() { // from class: com.jingdong.common.utils.personal.platform.floorframe.adapter.BaseFloorPlatformAdapter$spanSizeLookup$1
            @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int position) {
                int i2;
                int i3;
                int i4;
                int i5;
                try {
                    List<BaseTemplateEntity> data = BaseFloorPlatformAdapter.this.getData();
                    if (data == null) {
                        i3 = BaseFloorPlatformAdapter.this.spanCount;
                        return i3;
                    } else if (position >= data.size() || !(data.get(position) instanceof BaseFloorEntity)) {
                        i4 = BaseFloorPlatformAdapter.this.spanCount;
                        return i4;
                    } else {
                        BaseTemplateEntity baseTemplateEntity = data.get(position);
                        if (baseTemplateEntity != null) {
                            i5 = BaseFloorPlatformAdapter.this.spanCount;
                            return ((BaseFloorEntity) baseTemplateEntity).getSpanSize(i5);
                        }
                        throw new TypeCastException("null cannot be cast to non-null type com.jingdong.common.entity.personal.base.BaseFloorEntity");
                    }
                } catch (Throwable unused) {
                    i2 = BaseFloorPlatformAdapter.this.spanCount;
                    return i2;
                }
            }
        };
    }

    @NotNull
    public final GridLayoutManager.SpanSizeLookup getSpanSizeLookup() {
        return this.spanSizeLookup;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x002b A[Catch: all -> 0x0030, TRY_LEAVE, TryCatch #0 {all -> 0x0030, blocks: (B:3:0x0003, B:7:0x000b, B:9:0x000f, B:11:0x0019, B:13:0x001e, B:21:0x002b), top: B:24:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    @Override // com.jingdong.sdk.platform.floor.adapter.FloorRecyclerViewAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onBindViewHolder(@NotNull RecyclerView.ViewHolder holder, int position) {
        BaseFloor baseFloor;
        boolean z;
        super.onBindViewHolder(holder, position);
        try {
            FloorRecyclerViewAdapter.FloorViewHolder floorViewHolder = (FloorRecyclerViewAdapter.FloorViewHolder) (!(holder instanceof FloorRecyclerViewAdapter.FloorViewHolder) ? null : holder);
            if (floorViewHolder == null || (baseFloor = (BaseFloor) ReflectExtensionsKt.getFieldValue(floorViewHolder, "baseFloor")) == null) {
                return;
            }
            if (!(baseFloor instanceof ISVDynFloor) && !(baseFloor instanceof CommonFloor)) {
                z = false;
                if ((z ? baseFloor : null) == null) {
                    holder.itemView.setBackgroundColor(0);
                    return;
                }
                return;
            }
            z = true;
            if ((z ? baseFloor : null) == null) {
            }
        } catch (Throwable unused) {
        }
    }

    public final void setSpanCount(int spanCount) {
        this.spanCount = spanCount;
    }
}
