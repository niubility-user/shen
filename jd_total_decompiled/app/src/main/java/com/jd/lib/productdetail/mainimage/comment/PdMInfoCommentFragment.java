package com.jd.lib.productdetail.mainimage.comment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.jd.lib.productdetail.mainimage.R;
import com.jingdong.jdsdk.auraSetting.AuraFragmentHelper;
import java.lang.reflect.Method;

/* loaded from: classes15.dex */
public class PdMInfoCommentFragment extends Fragment {
    @Nullable

    /* renamed from: g  reason: collision with root package name */
    public Fragment f4637g;
    @Nullable

    /* renamed from: h  reason: collision with root package name */
    public Method f4638h;
    @Nullable

    /* renamed from: i  reason: collision with root package name */
    public Method f4639i;
    @Nullable

    /* renamed from: j  reason: collision with root package name */
    public Method f4640j;
    @Nullable

    /* renamed from: k  reason: collision with root package name */
    public Method f4641k;
    @Nullable

    /* renamed from: l  reason: collision with root package name */
    public Method f4642l;
    @Nullable

    /* renamed from: m  reason: collision with root package name */
    public Method f4643m;
    @Nullable

    /* renamed from: n  reason: collision with root package name */
    public Method f4644n;
    @Nullable
    public Method o;

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i2, int i3, Intent intent) {
        Fragment fragment = this.f4637g;
        if (fragment != null) {
            fragment.onActivityResult(i2, i3, intent);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Class<?> cls;
        View inflate = layoutInflater.inflate(R.layout.lib_pd_mainimage_comment_list, (ViewGroup) null);
        Fragment newFragment = AuraFragmentHelper.getInstance().newFragment(getActivity(), "com.jd.lib.shareorder.CommentListFragment");
        this.f4637g = newFragment;
        if (newFragment != null) {
            newFragment.setArguments(getArguments());
            getChildFragmentManager().beginTransaction().replace(R.id.lib_pd_main_comment_list_container, this.f4637g).commit();
        }
        Fragment fragment = this.f4637g;
        if (fragment != null && (cls = fragment.getClass()) != null) {
            try {
                if (this.f4638h == null) {
                    this.f4638h = cls.getMethod("initData", new Class[0]);
                }
                if (this.f4639i == null) {
                    this.f4639i = cls.getMethod("recycle", new Class[0]);
                }
                if (this.f4640j == null) {
                    this.f4640j = cls.getMethod("restore", Integer.TYPE);
                }
                if (this.f4641k == null) {
                    this.f4641k = cls.getMethod("refresh", Bundle.class);
                }
                if (this.f4642l == null) {
                    this.f4642l = cls.getMethod("setTagInfo", Bundle.class);
                }
                if (this.f4643m == null) {
                    this.f4643m = cls.getMethod("showPage", Bundle.class);
                }
                if (this.f4644n == null) {
                    this.f4644n = cls.getMethod("setShadowMainSku", Bundle.class);
                }
                if (this.o == null) {
                    this.o = cls.getMethod("setCurVisible", Bundle.class);
                }
            } catch (NoSuchMethodException e2) {
                e2.printStackTrace();
            }
        }
        return inflate;
    }
}
