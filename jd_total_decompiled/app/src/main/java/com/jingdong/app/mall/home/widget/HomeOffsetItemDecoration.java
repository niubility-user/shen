package com.jingdong.app.mall.home.widget;

import android.graphics.Rect;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.app.mall.home.floor.view.view.MallFloorLineMore;

/* loaded from: classes4.dex */
public class HomeOffsetItemDecoration extends RecyclerView.ItemDecoration {
    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(@NonNull Rect rect, @NonNull View view, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
        super.getItemOffsets(rect, view, recyclerView, state);
        if (view instanceof MallFloorLineMore) {
            rect.top = ((MallFloorLineMore) view).getTopOverlay();
        }
    }
}
