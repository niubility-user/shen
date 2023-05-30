package com.jd.lib.productdetail.tradein.selectdevice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.tradein.R;
import com.jd.lib.productdetail.tradein.selectdevice.TradeInSelectDeviceData;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.jdsdk.utils.FontsUtil;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes16.dex */
public class TradeInSelectDeviceDeviceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public a a;
    public TradeInSelectDeviceData.Data.devicesInfo.HeaderTitleTwo b;

    /* renamed from: c  reason: collision with root package name */
    public ArrayList<TradeInSelectDeviceData.Data.devicesInfo.OwnedDevice> f5499c;
    public ArrayList<TradeInSelectDeviceData.Data.InquiriesInfo.OldProductInquiries> d;

    /* renamed from: e  reason: collision with root package name */
    public boolean f5500e = false;

    /* loaded from: classes16.dex */
    public interface a {
    }

    /* loaded from: classes16.dex */
    public class b extends RecyclerView.ViewHolder {
        public TextView a;
        public TextView b;

        public b(@NonNull View view) {
            super(view);
            this.a = (TextView) view.findViewById(R.id.tradein_select_device_device_item_num);
            this.b = (TextView) view.findViewById(R.id.tradein_select_device_device_item_title);
            FontsUtil.changeTextFont(this.a);
        }
    }

    /* loaded from: classes16.dex */
    public class c extends RecyclerView.ViewHolder {
        public TextView a;

        public c(@NonNull TradeInSelectDeviceDeviceAdapter tradeInSelectDeviceDeviceAdapter, View view) {
            super(view);
            this.a = (TextView) view.findViewById(R.id.tradein_select_device_device_item_footer_text);
        }
    }

    /* loaded from: classes16.dex */
    public class d extends RecyclerView.ViewHolder {
        public TextView a;
        public SimpleDraweeView b;

        /* renamed from: c  reason: collision with root package name */
        public LinearLayout f5502c;

        public d(@NonNull View view) {
            super(view);
            this.b = (SimpleDraweeView) view.findViewById(R.id.tradein_olddevices_device_header_icon);
            this.a = (TextView) view.findViewById(R.id.tradein_olddevices_device_header_title);
            this.f5502c = (LinearLayout) view.findViewById(R.id.tradein_olddevices_device_container);
        }
    }

    public TradeInSelectDeviceDeviceAdapter(ArrayList<TradeInSelectDeviceData.Data.InquiriesInfo.OldProductInquiries> arrayList, TradeInSelectDeviceData.Data.devicesInfo devicesinfo) {
        this.d = arrayList;
        if (devicesinfo != null) {
            this.b = devicesinfo.headerTitleTwo;
            this.f5499c = devicesinfo.deviceInfoList;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        ArrayList<TradeInSelectDeviceData.Data.InquiriesInfo.OldProductInquiries> arrayList = this.d;
        int i2 = 0;
        int size = arrayList != null ? arrayList.size() : 0;
        ArrayList<TradeInSelectDeviceData.Data.devicesInfo.OwnedDevice> arrayList2 = this.f5499c;
        if (arrayList2 != null && arrayList2.size() > 0) {
            i2 = 1;
        }
        return size + i2 + (this.f5500e ? 1 : 0);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i2) {
        ArrayList<TradeInSelectDeviceData.Data.devicesInfo.OwnedDevice> arrayList = this.f5499c;
        if (arrayList != null && arrayList.size() > 0) {
            if (i2 == 0) {
                return 1;
            }
            if (i2 <= this.d.size()) {
                return 2;
            }
            if (this.f5500e) {
                return 3;
            }
        } else if (i2 < this.d.size()) {
            return 2;
        } else {
            if (this.f5500e) {
                return 3;
            }
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i2) {
        int i3 = 0;
        if (viewHolder instanceof b) {
            b bVar = (b) viewHolder;
            ArrayList<TradeInSelectDeviceData.Data.devicesInfo.OwnedDevice> arrayList = TradeInSelectDeviceDeviceAdapter.this.f5499c;
            if (arrayList != null && arrayList.size() > 0) {
                i3 = 1;
            }
            int i4 = i2 - i3;
            TradeInSelectDeviceData.Data.InquiriesInfo.OldProductInquiries oldProductInquiries = TradeInSelectDeviceDeviceAdapter.this.d.get(i4);
            bVar.a.setText((i4 + 1) + "");
            if (oldProductInquiries != null) {
                bVar.b.setText(oldProductInquiries.inquiryName);
            }
            if (i4 == 0) {
                bVar.a.setBackgroundResource(R.drawable.tradein_select_device_device_no_1);
            } else if (i4 == 1) {
                bVar.a.setBackgroundResource(R.drawable.tradein_select_device_device_no_2);
            } else if (i4 != 2) {
                bVar.a.setBackgroundResource(R.drawable.tradein_select_device_device_no_other);
            } else {
                bVar.a.setBackgroundResource(R.drawable.tradein_select_device_device_no_3);
            }
            bVar.itemView.setOnClickListener(new com.jd.lib.productdetail.tradein.g.a(bVar, oldProductInquiries));
        } else if (viewHolder instanceof d) {
            d dVar = (d) viewHolder;
            TradeInSelectDeviceData.Data.devicesInfo.HeaderTitleTwo headerTitleTwo = this.b;
            ArrayList<TradeInSelectDeviceData.Data.devicesInfo.OwnedDevice> arrayList2 = this.f5499c;
            if (headerTitleTwo != null) {
                dVar.a.setText(headerTitleTwo.text);
                JDImageUtils.displayImage(headerTitleTwo.icon, dVar.b);
            }
            dVar.f5502c.removeAllViews();
            Iterator<TradeInSelectDeviceData.Data.devicesInfo.OwnedDevice> it = arrayList2.iterator();
            while (it.hasNext()) {
                TradeInSelectDeviceData.Data.devicesInfo.OwnedDevice next = it.next();
                View inflate = LayoutInflater.from(dVar.f5502c.getContext()).inflate(R.layout.tradein_old_device_device_item, (ViewGroup) dVar.f5502c, false);
                ((TextView) inflate.findViewById(R.id.tradein_olddevices_device_title)).setText(next.deviceName);
                ((TextView) inflate.findViewById(R.id.tradein_olddevices_device_sub_title)).setText(next.purchasedYears);
                JDDisplayImageOptions createSimple = JDDisplayImageOptions.createSimple();
                createSimple.setRoundingParams(new RoundingParams().setCornersRadius(PDUtils.dip2px(3.0f)));
                JDImageUtils.displayImage(next.devicePicUrl, (ImageView) inflate.findViewById(R.id.tradein_olddevices_device_icon), createSimple);
                inflate.setOnClickListener(new com.jd.lib.productdetail.tradein.g.b(dVar, next));
                dVar.f5502c.addView(inflate);
            }
        } else if (viewHolder instanceof c) {
            c cVar = (c) viewHolder;
            if (this.f5500e) {
                cVar.a.setText("\u52a0\u8f7d\u4e2d...");
            } else {
                cVar.a.setText((CharSequence) null);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        if (i2 == 2) {
            return new b(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tradein_select_device_device_item, viewGroup, false));
        }
        if (i2 == 1) {
            return new d(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tradein_old_device_owned_device, viewGroup, false));
        }
        if (i2 == 3) {
            return new c(this, LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tradein_select_device_device_item_footer, viewGroup, false));
        }
        return null;
    }
}
