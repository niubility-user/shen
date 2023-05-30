package com.jd.lib.cashier.sdk.f.b;

import android.view.View;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor;
import com.jd.lib.cashier.sdk.freindpay.floors.FriendPayDynamicPDInfoFloor;
import com.jd.lib.cashier.sdk.freindpay.floors.FriendPayDynamicTopFloor;
import com.jd.lib.cashier.sdk.freindpay.floors.FriendPayEmptyFloor;
import com.jd.lib.cashier.sdk.freindpay.floors.FriendPayOrderInfoFloor;
import com.jd.lib.cashier.sdk.freindpay.floors.FriendPayOrderTitleFloor;
import com.jd.lib.cashier.sdk.freindpay.floors.FriendPayPriceInfoFloor;
import com.jd.lib.cashier.sdk.freindpay.floors.FriendPayShadowSplitLineFloor;
import com.jd.lib.cashier.sdk.freindpay.floors.FriendPayShareActionFloor;
import com.jd.lib.cashier.sdk.freindpay.floors.FriendPayShareButtonFloor;
import com.jd.lib.cashier.sdk.freindpay.floors.FriendPaySplitLineFloor;
import com.jd.lib.cashier.sdk.freindpay.floors.FriendPayThinSplitLineFloor;
import com.jd.lib.cashier.sdk.freindpay.floors.FriendPayTopFloor;

/* loaded from: classes14.dex */
public class b implements com.jd.lib.cashier.sdk.d.a.b.a<com.jd.lib.cashier.sdk.f.c.a> {
    @Override // com.jd.lib.cashier.sdk.d.a.b.a
    /* renamed from: b  reason: merged with bridge method [inline-methods] */
    public AbstractFloor a(com.jd.lib.cashier.sdk.f.c.a aVar, int i2, View view) {
        if (i2 == R.id.lib_cashier_friend_pay_top_image_floor_root) {
            return new FriendPayTopFloor(view);
        }
        if (i2 == R.id.lib_cashier_friend_pay_price_info_floor_root) {
            return new FriendPayPriceInfoFloor(view);
        }
        if (i2 == R.id.lib_cashier_friend_pay_action_info_floor_root) {
            return new FriendPayShareActionFloor(view);
        }
        if (i2 == R.id.lib_cashier_friend_pay_share_button_floor_root) {
            return new FriendPayShareButtonFloor(aVar, view);
        }
        if (i2 == R.id.lib_cashier_friend_pay_order_info_title_floor_root) {
            return new FriendPayOrderTitleFloor(view);
        }
        if (i2 == R.id.lib_cashier_friend_pay_order_info_floor_root) {
            return new FriendPayOrderInfoFloor(view);
        }
        if (i2 == R.id.lib_cashier_friend_pay_split_line_root) {
            return new FriendPaySplitLineFloor(view);
        }
        if (i2 == R.id.lib_cashier_friend_pay_thin_split_line_root) {
            return new FriendPayThinSplitLineFloor(view);
        }
        if (i2 == R.id.lib_cashier_friend_pay_shadow_split_line_root) {
            return new FriendPayShadowSplitLineFloor(view);
        }
        if (i2 == R.id.lib_cashier_friend_pay_dynamic_top_image_floor_root) {
            return new FriendPayDynamicTopFloor(view);
        }
        if (i2 == R.id.lib_cashier_friend_pay_dynamic_order_info_floor_root) {
            return new FriendPayDynamicPDInfoFloor(view);
        }
        return new FriendPayEmptyFloor(view);
    }
}
