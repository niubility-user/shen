package com.jingdong.common.utils.asyncinflate;

import com.jingdong.jdsdk.constant.CartConstant;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/jingdong/common/utils/asyncinflate/PersonalOnInflateFinishedCallback;", "", "Lcom/jingdong/common/utils/asyncinflate/PersonalAsyncInflateItem;", CartConstant.KEY_VENDOR_ITEM, "", "onInflateFinished", "(Lcom/jingdong/common/utils/asyncinflate/PersonalAsyncInflateItem;)V", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public interface PersonalOnInflateFinishedCallback {
    void onInflateFinished(@NotNull PersonalAsyncInflateItem item);
}
