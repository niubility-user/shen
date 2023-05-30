package com.jingdong.common.ui.address;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.jd.framework.json.JDJSON;
import com.jd.lib.un.address.R;
import com.jingdong.common.ui.JdAddressSelectActivity;
import com.jingdong.common.ui.address.UnAddressSelectView;
import com.jingdong.common.ui.address.entity.UnAddressInfo;
import com.jingdong.common.ui.homemix.entity.ShopParam;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.common.utils.DeepDarkChangeManager;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes6.dex */
public class UnHomeAddressSelectFragment extends Fragment implements UnAddressSelectView.OnUnAddressListener {
    private JdAddressSelectActivity addressSelectActivity;
    private UnAddressSelectHelper helper;
    private DeepDarkChangeManager.OnUIModeChangeListener onUIModeChangeListener = new DeepDarkChangeManager.OnUIModeChangeListener() { // from class: com.jingdong.common.ui.address.UnHomeAddressSelectFragment.1
        {
            UnHomeAddressSelectFragment.this = this;
        }

        @Override // com.jingdong.common.utils.DeepDarkChangeManager.OnUIModeChangeListener
        public void onUIModeChanged(int i2) {
            if (UnHomeAddressSelectFragment.this.selectView != null) {
                UnHomeAddressSelectFragment.this.selectView.refreshTheme();
            }
        }
    };
    private RelativeLayout rootView;
    private String saveBusiness;
    private String sceneId;
    private UnAddressSelectView selectView;
    private ShopParam shopParam;
    private String sku;
    private String source;

    private void addUnSelectView() {
        double appHeight = DPIUtil.getAppHeight(getActivity());
        Double.isNaN(appHeight);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, (int) (appHeight * 0.85d));
        layoutParams.addRule(8);
        if (TextUtils.isEmpty(this.sku)) {
            this.helper = new UnAddressSelectHelper((Context) getActivity(), 4, true);
        } else {
            this.helper = new UnAddressSelectHelper((Context) getActivity(), 2, true);
        }
        this.helper.setSceneId(this.sceneId);
        UnAddressSelectView selectView = this.helper.getSelectView();
        this.selectView = selectView;
        selectView.setSceneId(this.sceneId);
        this.selectView.setOnClickListener(null);
        this.rootView.addView(this.helper.getSelectView(), layoutParams);
        this.helper.setOnUnAddressListener(this);
        this.helper.initData(this.sku, this.shopParam, this.saveBusiness, 1);
        this.helper.setSource(this.source);
    }

    private void initData() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.sku = arguments.getString("sku");
            this.shopParam = (ShopParam) arguments.getParcelable("shopParams");
            this.saveBusiness = arguments.getString("saveBusiness");
            this.source = arguments.getString("source");
            this.sceneId = arguments.getString("sceneId");
        }
    }

    private void initView(View view) {
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.root_view);
        this.rootView = relativeLayout;
        relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.ui.address.UnHomeAddressSelectFragment.2
            {
                UnHomeAddressSelectFragment.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                UnHomeAddressSelectFragment.this.getActivity().finish();
            }
        });
        addUnSelectView();
    }

    public static UnHomeAddressSelectFragment newInstance(String str, ShopParam shopParam, String str2, String str3, String str4) {
        UnHomeAddressSelectFragment unHomeAddressSelectFragment = new UnHomeAddressSelectFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("shopParams", shopParam);
        bundle.putString("sku", str);
        bundle.putString("saveBusiness", str2);
        bundle.putString("source", str3);
        bundle.putString("sceneId", str4);
        unHomeAddressSelectFragment.setArguments(bundle);
        return unHomeAddressSelectFragment;
    }

    @Override // com.jingdong.common.ui.address.UnAddressSelectView.OnUnAddressListener
    public void addressSelected(UnAddressInfo unAddressInfo, boolean z) {
        if (unAddressInfo != null) {
            unAddressInfo.saveBusiness = this.saveBusiness;
            unAddressInfo.source = this.source;
        }
        this.addressSelectActivity.routerBack(JDJSON.toJSONString(unAddressInfo));
        Intent intent = getActivity().getIntent();
        intent.putExtra(ThemeTitleConstant.TITLE_ADDRESS_DRAWABLE_ID, JDJSON.toJSONString(unAddressInfo));
        getActivity().setResult(-1, intent);
        getActivity().finish();
    }

    @Override // com.jingdong.common.ui.address.UnAddressSelectView.OnUnAddressListener
    public void close() {
        this.addressSelectActivity.routerBack("");
        getActivity().finish();
    }

    public void locationRefresh() {
        UnAddressSelectView unAddressSelectView = this.selectView;
        if (unAddressSelectView != null) {
            unAddressSelectView.refresh();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.addressSelectActivity = (JdAddressSelectActivity) getActivity();
        DeepDarkChangeManager.getInstance().addDeepDarkChangeListener(this.onUIModeChangeListener);
        initData();
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.un_fragment_address_home, viewGroup, false);
        initView(inflate);
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        DeepDarkChangeManager.getInstance().removeDeepDarkChangeListener(this.onUIModeChangeListener);
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        locationRefresh();
    }
}
