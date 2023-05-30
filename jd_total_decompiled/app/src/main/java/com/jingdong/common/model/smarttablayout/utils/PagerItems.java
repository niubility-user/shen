package com.jingdong.common.model.smarttablayout.utils;

import android.content.Context;
import com.jingdong.common.model.smarttablayout.utils.PagerItem;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public abstract class PagerItems<T extends PagerItem> extends ArrayList<T> {
    private final Context context;

    /* JADX INFO: Access modifiers changed from: protected */
    public PagerItems(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return this.context;
    }
}
