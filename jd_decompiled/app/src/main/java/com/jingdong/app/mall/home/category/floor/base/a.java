package com.jingdong.app.mall.home.category.floor.base;

import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.app.mall.home.category.floor.decoration.CaDividerDecoration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes4.dex */
public final class a {
    public static final void a(@NotNull RecyclerView recyclerView, @Nullable CaDividerDecoration caDividerDecoration, int i2) {
        try {
            if (recyclerView.getItemDecorationCount() > 0) {
                recyclerView.removeItemDecorationAt(0);
            }
            if (caDividerDecoration != null) {
                caDividerDecoration.g(i2);
                recyclerView.addItemDecoration(caDividerDecoration, 0);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
