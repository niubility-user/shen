package com.tencent.tencentmap.mapsdk.maps;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

/* loaded from: classes9.dex */
public class SupportMapFragment extends Fragment {
    private TencentMap mTencentMap;
    public MapView mapV = null;
    private boolean isOnTop = false;

    public static SupportMapFragment newInstance(Context context) {
        SupportMapFragment supportMapFragment = new SupportMapFragment();
        supportMapFragment.initMap(context);
        return supportMapFragment;
    }

    public TencentMap getMap() {
        if (this.mTencentMap == null) {
            this.mTencentMap = this.mapV.getMap();
        }
        return this.mTencentMap;
    }

    public void initMap(Context context) {
        this.mapV = onCreateMapView(context, null);
    }

    public MapView onCreateMapView(Context context, TencentMapOptions tencentMapOptions) {
        return new MapView(context, tencentMapOptions);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mapV == null) {
            this.mapV = onCreateMapView(getActivity().getBaseContext(), null);
        }
        this.mapV.setOnTop(this.isOnTop);
        return this.mapV;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        this.mapV.onDestroy();
        super.onDestroy();
        this.mapV = null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        this.mapV.onDestroy();
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        this.mapV.onPause();
        super.onPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        this.mapV.onResume();
        super.onResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        this.mapV.onStart();
        super.onStart();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        this.mapV.onStop();
        super.onStop();
    }

    @Override // androidx.fragment.app.Fragment
    public void setArguments(Bundle bundle) {
        super.setArguments(bundle);
    }

    public void setOnTop(boolean z) {
        this.isOnTop = z;
    }
}
