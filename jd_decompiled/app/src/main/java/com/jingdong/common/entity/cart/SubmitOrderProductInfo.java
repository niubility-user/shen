package com.jingdong.common.entity.cart;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public interface SubmitOrderProductInfo {
    public static final String KEY_ADDITIONALDATA_HASSELECTEDOTC = "hasSelectedOTC";

    Bundle getAdditionalDataBundle();

    HashMap<CartPackSummary, ArrayList<CartSkuSummary>> getCheckedCartPackMap();

    ArrayList<CartPackSummary> getCheckedStatisticsPackList();

    ArrayList<CartSkuSummary> getCheckedStatisticsSkuList();

    int getCheckedWareNum();

    String getEasyBuySkuId();

    String getUnJieSuan();

    JSONObject toCheckedCartStr();

    JSONObject toCheckedStatisticsStr();
}
