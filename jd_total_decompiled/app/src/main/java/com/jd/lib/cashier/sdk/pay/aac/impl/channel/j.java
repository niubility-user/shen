package com.jd.lib.cashier.sdk.pay.aac.impl.channel;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.cashier.app.jdlibcutter.protocol.utils.DpiUtil;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.pay.recyclerview.CustomDrawOrderRecyclerView;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class j extends AbsCashierPayItemDecorationProxy {

    /* renamed from: c  reason: collision with root package name */
    private int f3707c;
    private boolean d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f3708e;

    /* renamed from: f  reason: collision with root package name */
    private CustomDrawOrderRecyclerView f3709f;

    public j(@Nullable CustomDrawOrderRecyclerView customDrawOrderRecyclerView) {
        super(customDrawOrderRecyclerView);
        this.f3709f = customDrawOrderRecyclerView;
        this.f3707c = 1;
    }

    @Override // com.jd.lib.cashier.sdk.pay.aac.impl.channel.AbsCashierPayItemDecorationProxy
    public void c(@NotNull Rect rect, @NotNull View view, @NotNull RecyclerView recyclerView) {
        if (view.getId() == R.id.lib_cashier_b_pay_channel_top_corner_floor_root) {
            rect.left = DpiUtil.dip2px(recyclerView.getContext(), 10.0f);
            rect.right = DpiUtil.dip2px(recyclerView.getContext(), 10.0f);
            rect.bottom = DpiUtil.dip2px(recyclerView.getContext(), -12.0f);
        } else if (view.getId() == R.id.lib_cashier_b_pay_channel_bottom_corner_floor_root) {
            rect.top = DpiUtil.dip2px(recyclerView.getContext(), -11.5f);
            rect.left = DpiUtil.dip2px(recyclerView.getContext(), 10.0f);
            rect.right = DpiUtil.dip2px(recyclerView.getContext(), 10.0f);
        } else if (view.getId() == R.id.lib_cashier_b_pay_channel_more_floor_root) {
            rect.right = DpiUtil.dip2px(recyclerView.getContext(), 10.0f);
        } else if (view.getId() != R.id.lib_cashier_b_pay_channel_bank_card_floor_root && view.getId() != R.id.lib_cashier_b_pay_dynamic_bank_card_floor_root) {
            if (view.getId() == R.id.lib_cashier_b_pay_channel_binding_card_floor_root) {
                if (this.d) {
                    if (this.f3707c % 2 != 0) {
                        rect.left = DpiUtil.dip2px(recyclerView.getContext(), 10.0f);
                    } else {
                        rect.left = DpiUtil.dip2px(recyclerView.getContext(), 0.0f);
                        rect.right = DpiUtil.dip2px(recyclerView.getContext(), 0.0f);
                    }
                } else if (this.f3707c % 2 != 0) {
                    rect.left = DpiUtil.dip2px(recyclerView.getContext(), 10.0f);
                } else {
                    rect.right = DpiUtil.dip2px(recyclerView.getContext(), 10.0f);
                }
                this.f3707c++;
                return;
            }
            rect.left = DpiUtil.dip2px(recyclerView.getContext(), 10.0f);
            rect.right = DpiUtil.dip2px(recyclerView.getContext(), 10.0f);
        } else {
            if (!this.f3708e) {
                rect.bottom = 0;
            } else {
                rect.bottom = DpiUtil.dip2px(recyclerView.getContext(), 11.0f);
            }
            rect.left = DpiUtil.dip2px(recyclerView.getContext(), 10.0f);
            rect.right = DpiUtil.dip2px(recyclerView.getContext(), 10.0f);
        }
    }

    @Override // com.jd.lib.cashier.sdk.pay.aac.impl.channel.AbsCashierPayItemDecorationProxy
    public void e(@Nullable List<? extends com.jd.lib.cashier.sdk.d.a.e.a> list) {
        if (list != null) {
            if (list == null || list.isEmpty()) {
                return;
            }
            this.f3707c = 1;
            this.d = false;
            this.f3708e = false;
            CustomDrawOrderRecyclerView customDrawOrderRecyclerView = this.f3709f;
            if (customDrawOrderRecyclerView != null) {
                customDrawOrderRecyclerView.f4172g = -1;
            }
            if (customDrawOrderRecyclerView != null) {
                customDrawOrderRecyclerView.f4173h = -1;
            }
            int size = list.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    break;
                } else if (list.get(i2).getItemType() == 400006) {
                    this.d = true;
                    break;
                } else {
                    i2++;
                }
            }
            int size2 = list.size();
            for (int i3 = 0; i3 < size2; i3++) {
                if (list.get(i3).getItemType() == 400009) {
                    CustomDrawOrderRecyclerView customDrawOrderRecyclerView2 = this.f3709f;
                    if (customDrawOrderRecyclerView2 == null || customDrawOrderRecyclerView2.f4172g != -1) {
                        if (customDrawOrderRecyclerView2 != null && customDrawOrderRecyclerView2.f4173h == -1 && customDrawOrderRecyclerView2 != null) {
                            customDrawOrderRecyclerView2.f4173h = i3;
                        }
                    } else if (customDrawOrderRecyclerView2 != null) {
                        customDrawOrderRecyclerView2.f4172g = i3;
                    }
                }
                CustomDrawOrderRecyclerView customDrawOrderRecyclerView3 = this.f3709f;
                if ((customDrawOrderRecyclerView3 == null || customDrawOrderRecyclerView3.f4172g != -1) && (customDrawOrderRecyclerView3 == null || customDrawOrderRecyclerView3.f4173h != -1)) {
                    break;
                }
            }
            int size3 = list.size();
            for (int i4 = 0; i4 < size3; i4++) {
                if (list.get(i4).getItemType() == 400010) {
                    int i5 = i4 - 1;
                    if (list.get(i5).getItemType() == 400005 || list.get(i5).getItemType() == 500001) {
                        this.f3708e = true;
                        return;
                    }
                }
            }
        }
    }
}
