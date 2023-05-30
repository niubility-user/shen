package com.jd.dynamic.lib.viewparse.b.carouselView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.jd.dynamic.lib.views.ItemView;

/* loaded from: classes13.dex */
public class e extends Fragment {

    /* renamed from: g */
    private int f2331g;

    /* renamed from: h */
    private f f2332h;

    /* renamed from: i */
    private ItemView f2333i;

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        ItemView itemView;
        if (viewGroup != null && (itemView = this.f2333i) != null) {
            View parse = itemView.parse();
            this.f2333i.bindData(parse, this.f2332h.k(this.f2331g));
            return parse;
        }
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }
}
