package com.jd.lib.cashier.sdk.h.b;

import android.view.View;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor;
import com.jd.lib.cashier.sdk.pay.floors.CashierAJDPayMoreFloor;
import com.jd.lib.cashier.sdk.pay.floors.CashierAJDPayTitleFloor;
import com.jd.lib.cashier.sdk.pay.floors.CashierAPayChannelListFloor;
import com.jd.lib.cashier.sdk.pay.floors.CashierAPayExpandFloor;
import com.jd.lib.cashier.sdk.pay.floors.CashierAPayGroupTitleFloor;
import com.jd.lib.cashier.sdk.pay.floors.CashierAUnableJDPayFloor;
import com.jd.lib.cashier.sdk.pay.floors.CashierBPayChannelBankCardFloor;
import com.jd.lib.cashier.sdk.pay.floors.CashierBPayChannelBindCardMoreFloor;
import com.jd.lib.cashier.sdk.pay.floors.CashierBPayChannelBindingCardFloor;
import com.jd.lib.cashier.sdk.pay.floors.CashierBPayChannelBottomCornerFloor;
import com.jd.lib.cashier.sdk.pay.floors.CashierBPayChannelListFloor;
import com.jd.lib.cashier.sdk.pay.floors.CashierBPayChannelTopCornerFloor;
import com.jd.lib.cashier.sdk.pay.floors.CashierBPayChannelVirtualJDPayFloor;
import com.jd.lib.cashier.sdk.pay.floors.CashierBPayDynamicBankCardFloor;
import com.jd.lib.cashier.sdk.pay.floors.CashierBPayDynamicBindingCardFloor;
import com.jd.lib.cashier.sdk.pay.floors.CashierBPayDynamicChannelListFloor;
import com.jd.lib.cashier.sdk.pay.floors.CashierBPayDynamicExpandFloor;
import com.jd.lib.cashier.sdk.pay.floors.CashierBPayDynamicVirtualJDPayFloor;
import com.jd.lib.cashier.sdk.pay.floors.CashierBPayExpandFloor;
import com.jd.lib.cashier.sdk.pay.floors.CashierPayChannelGridFloor;
import com.jd.lib.cashier.sdk.pay.floors.CashierPayChannelListNewFloor;
import com.jd.lib.cashier.sdk.pay.floors.CashierPayChannelPlanFloor;
import com.jd.lib.cashier.sdk.pay.floors.CashierPayChannelSplitLineFloor;
import com.jd.lib.cashier.sdk.pay.floors.CashierPayEmptyFloor;
import com.jd.lib.cashier.sdk.pay.floors.CashierPayExpandFloor;
import com.jd.lib.cashier.sdk.pay.floors.CashierPayGroupTitleFloor;
import com.jd.lib.cashier.sdk.pay.floors.CashierPayTitleFloor;
import com.jd.lib.cashier.sdk.pay.floors.CashierUnableJDPaymentNewFloor;

/* loaded from: classes14.dex */
public class q implements com.jd.lib.cashier.sdk.d.a.b.a<com.jd.lib.cashier.sdk.h.d.a> {
    @Override // com.jd.lib.cashier.sdk.d.a.b.a
    /* renamed from: b */
    public AbstractFloor a(com.jd.lib.cashier.sdk.h.d.a aVar, int i2, View view) {
        if (i2 == R.id.lib_cashier_pay_title_floor_root) {
            return new CashierPayTitleFloor(view);
        }
        if (i2 == R.id.lib_cashier_pay_channel_grid_floor_root) {
            return new CashierPayChannelGridFloor(view);
        }
        if (i2 == R.id.lib_cashier_pay_group_title_floor_root) {
            return new CashierPayGroupTitleFloor(view);
        }
        if (i2 == R.id.lib_cashier_pay_expand_floor_root) {
            return new CashierPayExpandFloor(view);
        }
        if (i2 == R.id.lib_cashier_unable_pay_channel_new_floor_root) {
            return new CashierUnableJDPaymentNewFloor(view);
        }
        if (i2 == R.id.lib_cashier_pay_channel_grid_bt_plan_floor_root) {
            return new CashierPayChannelPlanFloor(view);
        }
        if (i2 == R.id.lib_cashier_pay_channel_list_new_floor_root) {
            return new CashierPayChannelListNewFloor(view);
        }
        if (i2 == R.id.lib_cashier_b_pay_channel_virtual_jd_pay_floor_root) {
            return new CashierBPayChannelVirtualJDPayFloor(view);
        }
        if (i2 == R.id.lib_cashier_b_pay_channel_binding_card_floor_root) {
            return new CashierBPayChannelBindingCardFloor(view);
        }
        if (i2 == R.id.lib_cashier_b_pay_channel_more_floor_root) {
            return new CashierBPayChannelBindCardMoreFloor(view);
        }
        if (i2 == R.id.lib_cashier_b_pay_channel_bank_card_floor_root) {
            return new CashierBPayChannelBankCardFloor(view);
        }
        if (i2 == R.id.lib_cashier_b_pay_channel_list_floor_root) {
            return new CashierBPayChannelListFloor(view);
        }
        if (i2 == R.id.lib_cashier_b_pay_expand_floor_root) {
            return new CashierBPayExpandFloor(view);
        }
        if (i2 == R.id.lib_cashier_pay_split_line_floor_root) {
            return new CashierPayChannelSplitLineFloor(view);
        }
        if (i2 == R.id.lib_cashier_b_pay_channel_top_corner_floor_root) {
            return new CashierBPayChannelTopCornerFloor(view);
        }
        if (i2 == R.id.lib_cashier_b_pay_channel_bottom_corner_floor_root) {
            return new CashierBPayChannelBottomCornerFloor(view);
        }
        if (i2 == R.id.lib_cashier_b_pay_dynamic_channel_list_floor_root) {
            return new CashierBPayDynamicChannelListFloor(view);
        }
        if (i2 == R.id.lib_cashier_b_pay_dynamic_bank_card_floor_root) {
            return new CashierBPayDynamicBankCardFloor(view);
        }
        if (i2 == R.id.lib_cashier_b_pay_dynamic_binding_card_floor_root) {
            return new CashierBPayDynamicBindingCardFloor(view);
        }
        if (i2 == R.id.lib_cashier_b_pay_dynamic_expand_floor_root) {
            return new CashierBPayDynamicExpandFloor(view);
        }
        if (i2 == R.id.lib_cashier_b_pay_dynamic_virtual_jd_pay_floor_root) {
            return new CashierBPayDynamicVirtualJDPayFloor(view);
        }
        if (i2 == R.id.lib_cashier_sdk_a_jd_pay_title_floor_root) {
            return new CashierAJDPayTitleFloor(view);
        }
        if (i2 == R.id.lib_cashier_sdk_a_pay_channel_list_floor_root) {
            return new CashierAPayChannelListFloor(view);
        }
        if (i2 == R.id.lib_cashier_sdk_a_jd_pay_unable_channel_root) {
            return new CashierAUnableJDPayFloor(view);
        }
        if (i2 == R.id.lib_cashier_sdk_a_jd_pay_more_floor_root) {
            return new CashierAJDPayMoreFloor(view);
        }
        if (i2 == R.id.lib_cashier_sdk_a_pay_group_title_floor_root) {
            return new CashierAPayGroupTitleFloor(view);
        }
        if (i2 == R.id.lib_cashier_sdk_a_pay_expand_floor_root) {
            return new CashierAPayExpandFloor(view);
        }
        return new CashierPayEmptyFloor(view);
    }
}
