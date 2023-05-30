package com.jingdong.sdk.platform.floor.isv.adapterView;

import android.text.TextUtils;
import android.view.ViewGroup;
import com.jingdong.sdk.platform.floor.isv.IBaseView;
import com.jingdong.sdk.platform.floor.isv.store.ViewStore;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes10.dex */
public class ISVAdapaterViewHelper {
    ITypeAdapter mTypeHeper;
    private final ViewStore mViewStore;

    public ISVAdapaterViewHelper(String str) {
        this.mViewStore = StoreMananger.getViewStore(str);
    }

    private ITypeAdapter getTypeAdapter() {
        if (this.mTypeHeper == null) {
            this.mTypeHeper = new DefaultTypeAdapter();
        }
        return this.mTypeHeper;
    }

    public void destroy() {
        this.mTypeHeper = null;
    }

    public IBaseView getHolderView(@NotNull ViewGroup viewGroup, String str) {
        if (this.mViewStore == null || viewGroup == null || TextUtils.isEmpty(str)) {
            return null;
        }
        IBaseView viewStore = this.mViewStore.getInstance(viewGroup.getContext(), str);
        viewStore.onCreateView(viewGroup);
        return viewStore;
    }

    public String getViewIdByType(int i2) {
        return getTypeAdapter().type2Key(i2);
    }

    public int getViewTyByID(String str) {
        return getTypeAdapter().key2Type(str);
    }

    public void setTypeHeper(ITypeAdapter iTypeAdapter) {
        this.mTypeHeper = iTypeAdapter;
    }
}
