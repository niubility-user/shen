package com.jingdong.common.ui.address;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.ui.UnAddressSelectUtils;
import com.jingdong.common.ui.address.UnAddressSelectView;
import com.jingdong.common.ui.address.entity.UnAddressInfo;
import com.jingdong.common.ui.address.listener.OnAddressCoverageListener;
import com.jingdong.common.ui.address.listener.OnAddressInfoListener;
import com.jingdong.common.ui.address.listener.OnSingleAddressCoverageListener;
import com.jingdong.common.ui.homemix.entity.ShopParam;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes6.dex */
public class UnAddressEnableHelper {
    private OnAddressInfoListener onAddressInfoListener;
    private String sceneId;
    private ShopParam shopParam;
    private String sku;

    public UnAddressEnableHelper(@NonNull ShopParam shopParam, String str, @NonNull OnAddressInfoListener onAddressInfoListener) {
        this.sceneId = "locService";
        this.onAddressInfoListener = onAddressInfoListener;
        this.shopParam = shopParam;
        this.sku = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getLocation() {
        UnAddressSelectUtils.getLocAddress(this.sceneId, new OnAddressInfoListener() { // from class: com.jingdong.common.ui.address.UnAddressEnableHelper.3
            @Override // com.jingdong.common.ui.address.listener.OnAddressInfoListener
            public void onResult(UnAddressInfo unAddressInfo) {
                if (unAddressInfo != null && UnAddressSelectUtils.lngLatIsEnable(unAddressInfo.lng, unAddressInfo.lat)) {
                    UnAddressEnableHelper.this.reqeustSingleCoverage(unAddressInfo, true);
                    return;
                }
                UnAddressInfo unAddressInfo2 = new UnAddressInfo();
                unAddressInfo2.addressType = 4;
                UnAddressEnableHelper.this.onAddressInfoListener.onResult(unAddressInfo2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reqeustSingleCoverage(final UnAddressInfo unAddressInfo, final boolean z) {
        UnAddressSelectUtils.singleAddreessDeliveryArea(unAddressInfo, this.shopParam, new OnSingleAddressCoverageListener() { // from class: com.jingdong.common.ui.address.UnAddressEnableHelper.1
            @Override // com.jingdong.common.ui.address.listener.OnSingleAddressCoverageListener
            public void onResult(UnAddressInfo unAddressInfo2) {
                if (unAddressInfo2 != null && unAddressInfo2.isCoverage) {
                    UnAddressEnableHelper.this.onAddressInfoListener.onResult(unAddressInfo);
                } else if (!z) {
                    if (LoginUserBase.hasLogin()) {
                        UnAddressEnableHelper.this.requestMultCoverage();
                    } else {
                        UnAddressEnableHelper.this.getLocation();
                    }
                } else {
                    unAddressInfo.addressType = 4;
                    UnAddressEnableHelper.this.onAddressInfoListener.onResult(unAddressInfo);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestMultCoverage() {
        new UnAddressSelectHelper(!TextUtils.isEmpty(this.sku) ? 2 : 0).setOnAddressLoadListener(new UnAddressSelectView.OnAddressLoadListener() { // from class: com.jingdong.common.ui.address.UnAddressEnableHelper.2
            @Override // com.jingdong.common.ui.address.UnAddressSelectView.OnAddressLoadListener
            public void onComplete(boolean z, List<UnAddressInfo> list) {
                if (z) {
                    UnAddressSelectUtils.multAddreessDeliveryArea(list, UnAddressEnableHelper.this.shopParam, new OnAddressCoverageListener() { // from class: com.jingdong.common.ui.address.UnAddressEnableHelper.2.1
                        @Override // com.jingdong.common.ui.address.listener.OnAddressCoverageListener
                        public void onResult(List<UnAddressInfo> list2) {
                            if (list2 == null || list2.size() <= 0) {
                                UnAddressEnableHelper.this.getLocation();
                                return;
                            }
                            ArrayList arrayList = new ArrayList();
                            UnAddressInfo locAddressInfo = UnAddressSelectUtils.getLocAddressInfo(UnAddressEnableHelper.this.sceneId);
                            for (int i2 = 0; i2 < list2.size(); i2++) {
                                UnAddressInfo unAddressInfo = list2.get(i2);
                                unAddressInfo.addressType = 3;
                                if ((i2 == 0 && !unAddressInfo.isCoverage) || !unAddressInfo.isCoverage) {
                                    break;
                                }
                                if (locAddressInfo != null) {
                                    double d = locAddressInfo.lng;
                                    if (d > 0.0d) {
                                        double d2 = locAddressInfo.lat;
                                        if (d2 > 0.0d) {
                                            unAddressInfo.locationDistance = UnAddressSelectUtils.calculateDistance(unAddressInfo.lat, unAddressInfo.lng, d2, d);
                                            int distanceConfig = UnAddressSelectUtils.getDistanceConfig();
                                            if (UnAddressSelectUtils.getDistanceConfig() <= 0) {
                                                arrayList.add(unAddressInfo);
                                            } else if (unAddressInfo.locationDistance * 1000.0d <= distanceConfig) {
                                                arrayList.add(unAddressInfo);
                                            }
                                        }
                                    }
                                }
                                arrayList.add(unAddressInfo);
                                break;
                            }
                            if (arrayList.size() == 0) {
                                UnAddressEnableHelper.this.getLocation();
                            } else if (arrayList.size() == 1) {
                                UnAddressEnableHelper.this.onAddressInfoListener.onResult((UnAddressInfo) arrayList.get(0));
                            } else {
                                Collections.sort(arrayList, new Comparator<UnAddressInfo>() { // from class: com.jingdong.common.ui.address.UnAddressEnableHelper.2.1.1
                                    @Override // java.util.Comparator
                                    public int compare(UnAddressInfo unAddressInfo2, UnAddressInfo unAddressInfo3) {
                                        return unAddressInfo2.locationDistance > unAddressInfo3.locationDistance ? 0 : 1;
                                    }
                                });
                                UnAddressEnableHelper.this.onAddressInfoListener.onResult((UnAddressInfo) arrayList.get(0));
                            }
                        }
                    });
                } else {
                    UnAddressEnableHelper.this.getLocation();
                }
            }

            @Override // com.jingdong.common.ui.address.UnAddressSelectView.OnAddressLoadListener
            public void onStart() {
            }
        }).initData(this.sku, this.shopParam, 0);
    }

    public void getAddress() {
        UnAddressInfo addressCacheAddressInfo = UnAddressSelectUtils.getAddressCacheAddressInfo(this.sceneId);
        if (addressCacheAddressInfo != null && UnAddressSelectUtils.lngLatIsEnable(addressCacheAddressInfo.lng, addressCacheAddressInfo.lat)) {
            reqeustSingleCoverage(addressCacheAddressInfo, false);
        } else if (LoginUserBase.hasLogin()) {
            requestMultCoverage();
        } else {
            getLocation();
        }
    }

    public UnAddressEnableHelper(@NonNull ShopParam shopParam, String str, String str2, @NonNull OnAddressInfoListener onAddressInfoListener) {
        this.sceneId = "locService";
        this.onAddressInfoListener = onAddressInfoListener;
        this.shopParam = shopParam;
        this.sku = str;
        this.sceneId = str2;
    }
}
