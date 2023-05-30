package com.jd.lib.cashier.sdk.f.b;

import android.util.SparseArray;
import com.jd.lib.cashier.sdk.R;

/* loaded from: classes14.dex */
public class d implements com.jd.lib.cashier.sdk.d.a.b.b {
    @Override // com.jd.lib.cashier.sdk.d.a.b.b
    public SparseArray<Integer> a() {
        SparseArray<Integer> sparseArray = new SparseArray<>();
        sparseArray.put(1000, Integer.valueOf(R.layout.lib_cashier_sdk_friend_pay_top_image_floor));
        sparseArray.put(1001, Integer.valueOf(R.layout.lib_cashier_sdk_friend_pay_price_info_floor));
        sparseArray.put(1006, Integer.valueOf(R.layout.lib_cashier_sdk_friend_pay_share_button_floor));
        sparseArray.put(1002, Integer.valueOf(R.layout.lib_cashier_sdk_friend_pay_action_info_floor));
        sparseArray.put(1003, Integer.valueOf(R.layout.lib_cashier_sdk_friend_pay_order_info_title_floor));
        sparseArray.put(1004, Integer.valueOf(R.layout.lib_cashier_sdk_friend_pay_order_info_floor));
        sparseArray.put(1005, Integer.valueOf(R.layout.lib_cashier_sdk_friend_pay_split_line_floor));
        sparseArray.put(1009, Integer.valueOf(R.layout.lib_cashier_sdk_friend_pay_thin_split_line_floor));
        sparseArray.put(1008, Integer.valueOf(R.layout.lib_cashier_sdk_friend_pay_shadow_split_line_floor));
        sparseArray.put(2000, Integer.valueOf(R.layout.lib_cashier_sdk_nothing_floor));
        sparseArray.put(10000, Integer.valueOf(R.layout.lib_cashier_sdk_friend_pay_dynamic_top_image_floor));
        sparseArray.put(10004, Integer.valueOf(R.layout.lib_cashier_sdk_friend_pay_dynamic_order_info_floor));
        return sparseArray;
    }
}
