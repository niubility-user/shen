package com.jingdong.aura.sdk.provided;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundView;
import com.jingdong.aura.sdk.update.R;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.jdsdk.auraSetting.AuraFragmentHelper;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class ProvidedBundleNotFoundFragment extends Fragment {
    private String fragmentName;
    private boolean mIsVisibleToUser = true;
    private FrameLayout mProvidedTipContainer;
    private ArrayList<String> notPreparedBundles;
    ProvidedBundleNotFoundView providedBundleNotFoundView;
    private View rootView;
    private Bundle targetArguments;
    private Fragment targetFragment;

    /* JADX INFO: Access modifiers changed from: private */
    public boolean needRestartActivity(String str) {
        return "com.jd.lib.personal.view.fragment.JDPersonalFragment".equals(str) || "com.jd.lib.category.JDCategoryFragment".equals(str) || "com.jd.lib.cart.JDShoppingCartFragment".equals(str) || "com.jd.lib.shareorder.CommentListFragment".equals(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void restartActivity() {
        if (getActivity() != null) {
            Intent intent = getActivity().getIntent();
            getActivity().finish();
            startActivity(intent);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        if (bundle != null) {
            bundle.remove("android:support:fragments");
        }
        super.onCreate(bundle);
        this.targetArguments = getArguments();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        String str;
        View inflate = layoutInflater.inflate(R.layout.provided_bundle_download_container, (ViewGroup) null);
        this.rootView = inflate;
        ProvidedBundleNotFoundView providedBundleNotFoundView = (ProvidedBundleNotFoundView) inflate.findViewById(R.id.provided_bundle_notfound_view);
        this.providedBundleNotFoundView = providedBundleNotFoundView;
        providedBundleNotFoundView.setIsVisibleToUser(this.mIsVisibleToUser);
        Fragment fragment = this.targetFragment;
        if (fragment == null || (fragment instanceof ProvidedBundleNotFoundFragment)) {
            this.targetFragment = AuraFragmentHelper.getInstance().newFragment(getActivity(), this.fragmentName);
        }
        Fragment fragment2 = this.targetFragment;
        boolean z = true;
        if (fragment2 != null && !(fragment2 instanceof ProvidedBundleNotFoundFragment)) {
            Bundle bundle2 = this.targetArguments;
            if (bundle2 != null) {
                fragment2.setArguments(bundle2);
            }
            this.rootView.findViewById(R.id.provided_fragmentview).setVisibility(8);
            FragmentManager childFragmentManager = getChildFragmentManager();
            this.targetFragment.setUserVisibleHint(true);
            childFragmentManager.beginTransaction().replace(R.id.provided_container, this.targetFragment).commit();
            return this.rootView;
        }
        this.rootView.findViewById(R.id.provided_bundle_title_divider_line).setVisibility(8);
        this.rootView.findViewById(R.id.provided_bundle_title_back).setVisibility(8);
        FrameLayout frameLayout = (FrameLayout) this.rootView.findViewById(R.id.provided_bundle_download_tip_container);
        this.mProvidedTipContainer = frameLayout;
        frameLayout.setVisibility(4);
        ArrayList<String> arrayList = this.notPreparedBundles;
        if (arrayList != null && arrayList.size() > 0) {
            if (c.a.containsKey(this.fragmentName)) {
                str = this.fragmentName;
                z = false;
            } else {
                str = this.fragmentName;
            }
            c.a(str, z);
            this.providedBundleNotFoundView.a(this.notPreparedBundles, this.fragmentName, XView2Constants.FRAGMENT);
            this.providedBundleNotFoundView.setProvidedBundleDownloadListener(new ProvidedBundleNotFoundView.g() { // from class: com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundFragment.1
                @Override // com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundView.g
                public final void a() {
                    if (ProvidedBundleNotFoundFragment.this.getActivity() != null) {
                        ProvidedBundleNotFoundFragment providedBundleNotFoundFragment = ProvidedBundleNotFoundFragment.this;
                        if (!providedBundleNotFoundFragment.needRestartActivity(providedBundleNotFoundFragment.fragmentName)) {
                            if (ProvidedBundleNotFoundFragment.this.targetFragment == null || (ProvidedBundleNotFoundFragment.this.targetFragment instanceof ProvidedBundleNotFoundFragment)) {
                                ProvidedBundleNotFoundFragment.this.targetFragment = AuraFragmentHelper.getInstance().newFragment(ProvidedBundleNotFoundFragment.this.getActivity(), ProvidedBundleNotFoundFragment.this.fragmentName);
                            }
                            if (ProvidedBundleNotFoundFragment.this.targetFragment != null && !(ProvidedBundleNotFoundFragment.this.targetFragment instanceof ProvidedBundleNotFoundFragment)) {
                                if (ProvidedBundleNotFoundFragment.this.targetArguments != null) {
                                    ProvidedBundleNotFoundFragment.this.targetFragment.setArguments(ProvidedBundleNotFoundFragment.this.targetArguments);
                                }
                                ProvidedBundleNotFoundFragment.this.rootView.findViewById(R.id.provided_fragmentview).setVisibility(8);
                                FragmentManager childFragmentManager2 = ProvidedBundleNotFoundFragment.this.getChildFragmentManager();
                                ProvidedBundleNotFoundFragment.this.targetFragment.setUserVisibleHint(true);
                                childFragmentManager2.beginTransaction().replace(R.id.provided_container, ProvidedBundleNotFoundFragment.this.targetFragment).commit();
                                return;
                            }
                        }
                        ProvidedBundleNotFoundFragment.this.restartActivity();
                    }
                }
            });
        }
        return this.rootView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        if (this.rootView != null) {
            this.rootView = null;
        }
        if (this.mProvidedTipContainer != null) {
            this.mProvidedTipContainer = null;
        }
        if (this.providedBundleNotFoundView != null) {
            this.providedBundleNotFoundView = null;
        }
        if (this.targetFragment != null) {
            this.targetFragment = null;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        Fragment fragment = this.targetFragment;
        if (fragment == null || (fragment instanceof ProvidedBundleNotFoundFragment) || fragment.isAdded() || this.rootView == null) {
            return;
        }
        Bundle bundle = this.targetArguments;
        if (bundle != null) {
            this.targetFragment.setArguments(bundle);
        }
        this.rootView.findViewById(R.id.provided_fragmentview).setVisibility(8);
        this.targetFragment.setUserVisibleHint(true);
        getChildFragmentManager().beginTransaction().replace(R.id.provided_container, this.targetFragment).commitAllowingStateLoss();
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        try {
            Fragment fragment = this.targetFragment;
            if (fragment != null && !(fragment instanceof ProvidedBundleNotFoundFragment) && fragment.isAdded()) {
                getChildFragmentManager().beginTransaction().remove(this.targetFragment).commitAllowingStateLoss();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        super.onSaveInstanceState(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
    }

    public void setNotPreparedBundles(ArrayList<String> arrayList, String str) {
        this.notPreparedBundles = arrayList;
        this.fragmentName = str;
    }

    @Override // androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        Fragment fragment = this.targetFragment;
        if (fragment != null && !(fragment instanceof ProvidedBundleNotFoundFragment)) {
            fragment.setUserVisibleHint(z);
        }
        com.jingdong.aura.sdk.update.b.c.a("ProvidedFragment", "isVisibleToUser".concat(String.valueOf(z)));
        ProvidedBundleNotFoundView providedBundleNotFoundView = this.providedBundleNotFoundView;
        if (providedBundleNotFoundView != null) {
            providedBundleNotFoundView.setIsVisibleToUser(z);
        }
        this.mIsVisibleToUser = z;
    }
}
