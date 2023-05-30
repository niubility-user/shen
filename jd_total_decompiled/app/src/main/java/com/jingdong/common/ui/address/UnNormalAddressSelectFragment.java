package com.jingdong.common.ui.address;

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
import com.jingdong.common.entity.AddressGlobal;
import com.jingdong.common.ui.JDAddressSelectView;
import com.jingdong.common.ui.JdAddressSelectActivity;
import com.jingdong.common.ui.UnAddressSelectUtils;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.common.utils.DeepDarkChangeManager;
import com.jingdong.common.utils.JDAddressSelectViewHelper;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes6.dex */
public class UnNormalAddressSelectFragment extends Fragment implements JDAddressSelectViewHelper.OnAddressListener {
    private AddressGlobal addressGlobal;
    private JDAddressSelectView addressSelectView;
    private JDAddressSelectViewHelper helper;
    private RelativeLayout rootView;
    private String sceneId;
    private JdAddressSelectActivity selectActivity;
    private String sku;
    private int canEditLevel = -1;
    private String saveBusiness = "";
    private String source = "";
    private DeepDarkChangeManager.OnUIModeChangeListener onUIModeChangeListener = new DeepDarkChangeManager.OnUIModeChangeListener() { // from class: com.jingdong.common.ui.address.UnNormalAddressSelectFragment.1
        {
            UnNormalAddressSelectFragment.this = this;
        }

        @Override // com.jingdong.common.utils.DeepDarkChangeManager.OnUIModeChangeListener
        public void onUIModeChanged(int i2) {
            if (UnNormalAddressSelectFragment.this.addressSelectView != null) {
                UnNormalAddressSelectFragment.this.addressSelectView.refreshTheme();
            }
        }
    };

    private void addJdSelectView() {
        double appHeight = DPIUtil.getAppHeight(getActivity());
        Double.isNaN(appHeight);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, (int) (appHeight * 0.85d));
        layoutParams.addRule(8);
        if (TextUtils.isEmpty(this.sku)) {
            this.helper = new JDAddressSelectViewHelper(getActivity(), 4, true);
        } else {
            this.helper = new JDAddressSelectViewHelper(getActivity(), 2, true);
        }
        this.helper.setSceneId(this.sceneId);
        JDAddressSelectView jDAddressSelectView = (JDAddressSelectView) this.helper.getView();
        this.addressSelectView = jDAddressSelectView;
        jDAddressSelectView.setSceneId(this.sceneId);
        this.addressSelectView.setTopCorners(true);
        this.rootView.addView(this.addressSelectView, layoutParams);
        this.helper.setData(this.sku, 1);
        this.helper.setOnAddressListener(this);
        this.addressSelectView.setSaveBusiness(this.saveBusiness);
        this.addressSelectView.setSource(this.source);
        this.addressSelectView.setClickAddressEnable(this.canEditLevel);
    }

    private void initData() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.sku = arguments.getString("sku");
            this.addressGlobal = (AddressGlobal) arguments.getParcelable(ThemeTitleConstant.TITLE_ADDRESS_DRAWABLE_ID);
            this.canEditLevel = arguments.getInt("canEditLevel");
            this.saveBusiness = arguments.getString("saveBusiness");
            this.source = arguments.getString("source");
            this.sceneId = arguments.getString("sceneId");
        }
    }

    private void initView(View view) {
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.root_view);
        this.rootView = relativeLayout;
        relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.ui.address.UnNormalAddressSelectFragment.2
            {
                UnNormalAddressSelectFragment.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                UnNormalAddressSelectFragment.this.getActivity().finish();
            }
        });
        addJdSelectView();
    }

    public static UnNormalAddressSelectFragment newInstance(String str, AddressGlobal addressGlobal, int i2, String str2, String str3, String str4) {
        UnNormalAddressSelectFragment unNormalAddressSelectFragment = new UnNormalAddressSelectFragment();
        Bundle bundle = new Bundle();
        bundle.putString("sku", str);
        bundle.putParcelable(ThemeTitleConstant.TITLE_ADDRESS_DRAWABLE_ID, addressGlobal);
        bundle.putInt("canEditLevel", i2);
        bundle.putString("saveBusiness", str2);
        bundle.putString("source", str3);
        bundle.putString("sceneId", str4);
        unNormalAddressSelectFragment.setArguments(bundle);
        return unNormalAddressSelectFragment;
    }

    @Override // com.jingdong.common.utils.JDAddressSelectViewHelper.OnAddressListener
    public void onAddressSelected(int i2, AddressGlobal addressGlobal) {
        addressGlobal.toString();
    }

    @Override // com.jingdong.common.utils.JDAddressSelectViewHelper.OnAddressListener
    public void onClose() {
        this.selectActivity.routerBack("");
        getActivity().finish();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.selectActivity = (JdAddressSelectActivity) getActivity();
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

    @Override // com.jingdong.common.utils.JDAddressSelectViewHelper.OnAddressListener
    public void onLoadAddressed(boolean z, boolean z2, JDAddressSelectViewHelper.AddressEntity addressEntity) {
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        AddressGlobal addressGlobal = this.addressGlobal;
        if (addressGlobal == null) {
            this.helper.showAddress();
        } else {
            this.addressSelectView.showThreeAddress(addressGlobal);
        }
    }

    @Override // com.jingdong.common.utils.JDAddressSelectViewHelper.OnAddressListener
    public void onSelectCompleted(int i2, AddressGlobal addressGlobal, boolean z) {
        addressGlobal.toString();
        addressGlobal.setSaveBusiness(this.saveBusiness);
        addressGlobal.setSource(this.source);
        UnAddressSelectUtils.saveAddress(addressGlobal);
        Intent intent = getActivity().getIntent();
        intent.putExtra(ThemeTitleConstant.TITLE_ADDRESS_DRAWABLE_ID, JDJSON.toJSONString(UnAddressSelectUtils.addressGlobalToAddressInfo(addressGlobal)));
        getActivity().setResult(-1, intent);
        this.selectActivity.routerBack(JDJSON.toJSONString(UnAddressSelectUtils.addressGlobalToAddressInfo(addressGlobal)));
        getActivity().finish();
    }
}
