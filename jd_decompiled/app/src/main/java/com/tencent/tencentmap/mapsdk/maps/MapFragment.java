package com.tencent.tencentmap.mapsdk.maps;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@TargetApi(12)
/* loaded from: classes9.dex */
public class MapFragment extends Fragment {
    private TencentMap mTencentMap;
    public MapView mapV = null;
    private boolean isOnTop = true;

    public static MapFragment newInstance(Context context) {
        MapFragment mapFragment = new MapFragment();
        mapFragment.initMap(context);
        return mapFragment;
    }

    public TencentMap getMap() {
        if (this.mTencentMap == null) {
            this.mTencentMap = this.mapV.getMap();
        }
        return this.mTencentMap;
    }

    public void initMap(Context context) {
        if (this.mapV == null) {
            this.mapV = onCreateMapView(context.getApplicationContext(), null);
        }
    }

    @Override // android.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override // android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public MapView onCreateMapView(Context context, TencentMapOptions tencentMapOptions) {
        return new MapView(context, tencentMapOptions);
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mapV == null) {
            this.mapV = onCreateMapView(getActivity().getBaseContext(), null);
        }
        this.mapV.setOnTop(this.isOnTop);
        return this.mapV;
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        this.mapV.onDestroy();
        super.onDestroy();
    }

    @Override // android.app.Fragment
    public void onDestroyView() {
        this.mapV.onDestroy();
        super.onDestroyView();
    }

    @Override // android.app.Fragment
    public void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        super.onInflate(activity, attributeSet, bundle);
    }

    @Override // android.app.Fragment, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override // android.app.Fragment
    public void onPause() {
        this.mapV.onPause();
        super.onPause();
    }

    @Override // android.app.Fragment
    public void onResume() {
        this.mapV.onResume();
        super.onResume();
    }

    @Override // android.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    @Override // android.app.Fragment
    public void onStart() {
        this.mapV.onStart();
        super.onStart();
    }

    @Override // android.app.Fragment
    public void onStop() {
        this.mapV.onStop();
        super.onStop();
    }

    @Override // android.app.Fragment
    public void setArguments(Bundle bundle) {
        super.setArguments(bundle);
    }

    public void setOnTop(boolean z) {
        this.isOnTop = z;
    }
}
