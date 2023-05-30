package com.jingdong.common.unification.jdbottomdialogview;

import android.view.View;
import android.widget.AbsListView;

/* loaded from: classes6.dex */
public interface IJDBottomDialogView {
    View.OnClickListener getBottomLeftBtClickListener();

    String getBottomLeftBtText();

    View.OnClickListener getBottomRightBtClickListener();

    String getBottomRightBtText();

    AbsListView getContentListView();
}
