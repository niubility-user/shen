package com.jingdong.common.search.view;

import android.text.TextUtils;
import android.widget.LinearLayout;
import com.jd.framework.json.JDJSONObject;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes6.dex */
public class OriginPriceBean implements Serializable {
    public boolean isPriceNewStyle;
    public String jdPrice;
    public LinearLayout priceContainer;
    public JDJSONObject priceInfoMta;
    public int priceMaxWidth;
    public String priceRemoveZeroAndAddSignStatus;
    public List<PriceInfoBean> renewVersionPrice;
    public List<Integer> showPriceIdxes;
    public String uniqueIdToDeleted;
    public float zoomRatio;

    public boolean isNewPriceStatus() {
        return TextUtils.equals("1", this.priceRemoveZeroAndAddSignStatus);
    }
}
