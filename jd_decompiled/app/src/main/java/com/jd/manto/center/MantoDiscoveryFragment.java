package com.jd.manto.center;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.manto.center.adapter.MantoDiscoveryAdapter;
import com.jd.manto.center.decoration.MantoDiscoveryDecoration;
import com.jd.manto.center.k.h;
import com.jd.manto.center.model.entity.MantoDiscoveryBean;
import com.jingdong.cleanmvp.presenter.BaseNavigator;
import com.jingdong.cleanmvp.ui.MvpBaseFragment;
import com.jingdong.common.utils.ImageUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes17.dex */
public class MantoDiscoveryFragment extends MvpBaseFragment<com.jd.manto.center.i.a, BaseNavigator> implements com.jd.manto.center.j.a {

    /* renamed from: l */
    public static final String f6356l = MantoDiscoveryFragment.class.getSimpleName();

    /* renamed from: g */
    private RecyclerView f6357g;

    /* renamed from: h */
    private MantoDiscoveryAdapter f6358h;

    /* renamed from: i */
    private List<MantoDiscoveryBean> f6359i = new ArrayList();

    /* renamed from: j */
    private LinearLayout f6360j;

    /* renamed from: k */
    private TextView f6361k;

    private void b(View view) {
        this.f6357g = (RecyclerView) view.findViewById(R.id.manto_center_discovert_rv);
        this.f6360j = (LinearLayout) view.findViewById(R.id.manto_discovery_error_container);
        this.f6361k = (TextView) view.findViewById(R.id.manto_discovery_error_tip);
    }

    private void c() {
        if (this.f6358h == null) {
            this.f6357g.setLayoutManager(new LinearLayoutManager(getActivity()));
            this.f6357g.addItemDecoration(new MantoDiscoveryDecoration(getActivity()));
            MantoDiscoveryAdapter mantoDiscoveryAdapter = new MantoDiscoveryAdapter(getActivity(), this.f6359i);
            this.f6358h = mantoDiscoveryAdapter;
            this.f6357g.setAdapter(mantoDiscoveryAdapter);
        }
    }

    @Override // com.jingdong.cleanmvp.ui.MvpBaseFragment
    @Nullable
    /* renamed from: a */
    public com.jd.manto.center.i.a createPresenter() {
        return new com.jd.manto.center.i.a(this);
    }

    @Override // com.jingdong.cleanmvp.ui.MvpBaseFragment
    protected BaseNavigator createNavigator() {
        return null;
    }

    @Override // com.jingdong.cleanmvp.presenter.IBaseUI
    public void hideProgress() {
    }

    @Override // com.jd.manto.center.j.a
    public void i() {
        h.l(this.f6360j);
        h.i(this.f6361k, "\u7f51\u7edc\u8bf7\u6c42\u5931\u8d25\uff0c\u8bf7\u68c0\u67e5\u60a8\u7684\u7f51\u7edc");
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment
    public View onCreateViews(LayoutInflater layoutInflater, Bundle bundle) {
        return ImageUtil.inflate(this.thisActivity.getApplicationContext(), R.layout.manto_center_discovery_fragment, (ViewGroup) null);
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (z) {
            return;
        }
        com.jd.manto.center.h.b.p(getActivity(), "J_Applets_Find");
        getPresenter().b();
    }

    @Override // com.jingdong.sdk.platform.lib.ui.CompactFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        com.jd.manto.center.h.b.p(getContext(), "J_Applets_Find");
        getPresenter().b();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        b(this.rootView);
        c();
    }

    @Override // com.jd.manto.center.j.a
    public void p(ArrayList<MantoDiscoveryBean> arrayList) {
        h.b(this.f6360j);
        this.f6359i.clear();
        this.f6359i.addAll(arrayList);
        if (arrayList != null && arrayList.size() > 10) {
            MantoDiscoveryBean mantoDiscoveryBean = new MantoDiscoveryBean();
            mantoDiscoveryBean.type = 5;
            this.f6359i.add(mantoDiscoveryBean);
        }
        MantoDiscoveryAdapter mantoDiscoveryAdapter = this.f6358h;
        if (mantoDiscoveryAdapter != null) {
            mantoDiscoveryAdapter.notifyDataSetChanged();
        }
    }

    @Override // com.jd.manto.center.j.a
    public void showEmptyView() {
        h.l(this.f6360j);
        h.i(this.f6361k, "\u5185\u5bb9\u8d70\u4e22\u5566~");
    }

    @Override // com.jingdong.cleanmvp.presenter.IBaseUI
    public void showProgress() {
    }
}
