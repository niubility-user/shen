package com.jd.lib.productdetail.mainimage.old;

import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import androidx.viewpager2.widget.ViewPager2;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMainPictureDpgPops;
import com.jd.lib.productdetail.mainimage.old.PdMPartsDpgLayerTitleAdapter;

/* loaded from: classes15.dex */
public class c0 implements View.OnClickListener {

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ PdMPartsDpgLayerTitleAdapter.PDDPpgTitleHolder f5144g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ PdMPartsDpgLayerTitleAdapter f5145h;

    public c0(PdMPartsDpgLayerTitleAdapter pdMPartsDpgLayerTitleAdapter, PdMPartsDpgLayerTitleAdapter.PDDPpgTitleHolder pDDPpgTitleHolder) {
        this.f5145h = pdMPartsDpgLayerTitleAdapter;
        this.f5144g = pDDPpgTitleHolder;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        PdMPartsDpgLayerTitleAdapter.a aVar = this.f5145h.f5069f;
        if (aVar != null) {
            PdMPartsDpgLayerTitleAdapter.PDDPpgTitleHolder pDDPpgTitleHolder = this.f5144g;
            View view2 = pDDPpgTitleHolder.itemView;
            int adapterPosition = pDDPpgTitleHolder.getAdapterPosition();
            a0 a0Var = (a0) aVar;
            ViewPager2 viewPager2 = a0Var.b.f5044j;
            if (viewPager2 != null && viewPager2.getCurrentItem() != adapterPosition) {
                JDJSONObject jDJSONObject = new JDJSONObject();
                if (TextUtils.equals(((WareBusinessMainPictureDpgPops) a0Var.a.get(adapterPosition)).type, "vrDPG")) {
                    jDJSONObject.put("tabid", (Object) "2");
                } else if (TextUtils.equals(((WareBusinessMainPictureDpgPops) a0Var.a.get(adapterPosition)).type, WareBusinessMagicHeadPicInfoEntity.FB_TOP_IMAGE_RECOMMEND)) {
                    jDJSONObject.put("tabid", (Object) "1");
                }
                a0Var.b.q.mtaClick("Productdetail_DapeiBuyToastTab", jDJSONObject.toJSONString());
            }
            ViewPager2 viewPager22 = a0Var.b.f5044j;
            if (viewPager22 != null) {
                viewPager22.setCurrentItem(adapterPosition, false);
            }
        }
        PdMPartsDpgLayerTitleAdapter pdMPartsDpgLayerTitleAdapter = this.f5145h;
        int adapterPosition2 = this.f5144g.getAdapterPosition();
        int i2 = pdMPartsDpgLayerTitleAdapter.b;
        if (i2 == adapterPosition2) {
            return;
        }
        pdMPartsDpgLayerTitleAdapter.notifyItemChanged(i2);
        pdMPartsDpgLayerTitleAdapter.b = adapterPosition2;
        pdMPartsDpgLayerTitleAdapter.notifyItemChanged(adapterPosition2);
        PdMPartsDpgLayerTitleAdapter.PDDPpgTitleHolder pDDPpgTitleHolder2 = (PdMPartsDpgLayerTitleAdapter.PDDPpgTitleHolder) pdMPartsDpgLayerTitleAdapter.f5070g.findViewHolderForAdapterPosition(adapterPosition2);
        if (pDDPpgTitleHolder2 != null) {
            View view3 = pDDPpgTitleHolder2.itemView;
            if (pdMPartsDpgLayerTitleAdapter.f5070g == null || view3 == null) {
                return;
            }
            int width = view3.getWidth();
            Rect rect = new Rect();
            view3.getLocalVisibleRect(rect);
            if (rect.width() < width) {
                int i3 = rect.left;
                if (i3 > 0) {
                    pdMPartsDpgLayerTitleAdapter.f5070g.scrollBy(-i3, 0);
                    return;
                } else if (i3 == 0) {
                    pdMPartsDpgLayerTitleAdapter.f5070g.scrollBy(width - rect.width(), 0);
                    return;
                } else {
                    return;
                }
            }
            return;
        }
        pdMPartsDpgLayerTitleAdapter.f5070g.smoothScrollToPosition(0);
        pdMPartsDpgLayerTitleAdapter.f5070g.getLayoutManager().scrollToPosition(adapterPosition2);
    }
}
