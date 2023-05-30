package com.jd.lib.cashier.sdk.h.b;

import android.util.SparseArray;
import com.jd.lib.cashier.sdk.R;
import com.jingdong.common.unification.title.theme.ThemeTitleDataController;
import com.jingdong.common.widget.custom.livewidget.bean.StatusCode;

/* loaded from: classes14.dex */
public class r implements com.jd.lib.cashier.sdk.d.a.b.b {
    @Override // com.jd.lib.cashier.sdk.d.a.b.b
    public SparseArray<Integer> a() {
        SparseArray<Integer> sparseArray = new SparseArray<>();
        sparseArray.put(100000, Integer.valueOf(R.layout.lib_cashier_sdk_pay_title_floor));
        sparseArray.put(100005, Integer.valueOf(R.layout.lib_cashier_sdk_nothing_floor));
        sparseArray.put(200001, Integer.valueOf(R.layout.lib_cashier_sdk_pay_channel_grid_floor));
        sparseArray.put(200003, Integer.valueOf(R.layout.lib_cashier_sdk_pay_expand_floor));
        sparseArray.put(200002, Integer.valueOf(R.layout.lib_cashier_sdk_pay_group_title_floor));
        sparseArray.put(200004, Integer.valueOf(R.layout.lib_cashier_sdk_unable_jd_pay_channel_new_floor));
        sparseArray.put(200005, Integer.valueOf(R.layout.lib_cashier_sdk_pay_channel_grid_bt_plan_floor));
        sparseArray.put(200006, Integer.valueOf(R.layout.lib_cashier_sdk_pay_channel_list_new_floor));
        sparseArray.put(400003, Integer.valueOf(R.layout.lib_cashier_sdk_b_pay_channel_list_floor));
        sparseArray.put(400004, Integer.valueOf(R.layout.lib_cashier_sdk_b_pay_channel_binding_card_floor));
        sparseArray.put(400005, Integer.valueOf(R.layout.lib_cashier_sdk_b_pay_channel_bank_card_floor));
        sparseArray.put(400006, Integer.valueOf(R.layout.lib_cashier_sdk_b_pay_channel_more_floor));
        sparseArray.put(400007, Integer.valueOf(R.layout.lib_cashier_sdk_b_pay_channel_virtual_jd_pay_floor));
        sparseArray.put(400011, Integer.valueOf(R.layout.lib_cashier_sdk_b_pay_expand_floor));
        sparseArray.put(100002, Integer.valueOf(R.layout.lib_cashier_sdk_pay_split_line_floor));
        sparseArray.put(400009, Integer.valueOf(R.layout.lib_cashier_sdk_pay_top_corner_floor));
        sparseArray.put(400010, Integer.valueOf(R.layout.lib_cashier_sdk_pay_bottom_corner_floor));
        sparseArray.put(500000, Integer.valueOf(R.layout.lib_cashier_sdk_b_pay_dynamic_channel_list_floor));
        sparseArray.put(StatusCode.PLAY_ERROR_PREPARE, Integer.valueOf(R.layout.lib_cashier_sdk_b_pay_dynamic_bank_card_floor));
        sparseArray.put(500002, Integer.valueOf(R.layout.lib_cashier_sdk_b_pay_dynamic_binding_card_floor));
        sparseArray.put(StatusCode.PLAY_INFO_BUFFERING_START, Integer.valueOf(R.layout.lib_cashier_sdk_b_pay_dynamic_expand_floor));
        sparseArray.put(StatusCode.PLAY_INFO_BUFFERING_END, Integer.valueOf(R.layout.lib_cashier_sdk_b_pay_dynamic_virtual_jd_pay_floor));
        sparseArray.put(StatusCode.MEDIADATA_NETWORK_ERROR, Integer.valueOf(R.layout.lib_cashier_sdk_a_jd_pay_title_floor));
        sparseArray.put(ThemeTitleDataController.DELAY_TIME, Integer.valueOf(R.layout.lib_cashier_sdk_a_pay_channel_list_floor));
        sparseArray.put(600002, Integer.valueOf(R.layout.lib_cashier_sdk_a_jd_pay_more_floor));
        sparseArray.put(600001, Integer.valueOf(R.layout.lib_cashier_sdk_a_jd_pay_unable_channel_floor));
        sparseArray.put(StatusCode.MEDIADATA_INTERNAL_ERROR, Integer.valueOf(R.layout.lib_cashier_sdk_a_pay_expand_floor));
        sparseArray.put(StatusCode.MEDIADATA_AUTH_FAILED, Integer.valueOf(R.layout.lib_cashier_sdk_a_pay_group_title_floor));
        return sparseArray;
    }
}
