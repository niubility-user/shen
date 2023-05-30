package com.jingdong.common.recommend;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.jd.mobile.image.JDImageLoader;
import com.jingdong.common.recommend.ui.homerecommend.HomeCardMaskView;

/* loaded from: classes6.dex */
public class RecommendMaskViewHelper {
    public void maskView(ViewGroup viewGroup, String str) {
        if (viewGroup == null) {
            return;
        }
        try {
            int i2 = R.id.recommend_home_card_mask_view;
            View findViewById = viewGroup.findViewById(i2);
            if (findViewById == null) {
                findViewById = new HomeCardMaskView(viewGroup.getContext());
                findViewById.setId(i2);
                viewGroup.addView(findViewById);
                ViewGroup.LayoutParams layoutParams = findViewById.getLayoutParams();
                layoutParams.width = -1;
                layoutParams.height = -1;
                findViewById.setLayoutParams(layoutParams);
            }
            if (TextUtils.isEmpty(str)) {
                return;
            }
            JDImageLoader.display(str, findViewById);
        } catch (Exception unused) {
        }
    }
}
