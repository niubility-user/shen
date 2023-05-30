package com.jingdong.common.messagepop.template;

import android.content.Context;
import android.view.View;
import com.jingdong.common.messagepop.MessagePopModel;

/* loaded from: classes5.dex */
public interface IPopView {
    View initPopView(Context context, MessagePopModel messagePopModel);

    View initPopView(Context context, MessagePopModel messagePopModel, View.OnClickListener onClickListener);
}
