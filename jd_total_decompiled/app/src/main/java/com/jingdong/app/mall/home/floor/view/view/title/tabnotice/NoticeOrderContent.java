package com.jingdong.app.mall.home.floor.view.view.title.tabnotice;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.g;

/* loaded from: classes4.dex */
public class NoticeOrderContent extends LinearLayout {
    private final f mInfoSize;
    private final TextView mLeftText;
    private final TextView mRightText;
    private final f mTimeInfoSize;

    public NoticeOrderContent(Context context) {
        super(context);
        setOrientation(0);
        setGravity(16);
        g gVar = new g(context, false);
        gVar.d(16);
        gVar.l(-15066598);
        gVar.j(true);
        gVar.m(24);
        gVar.f(1);
        TextView a = gVar.a();
        this.mLeftText = a;
        f fVar = new f(-2, 48);
        this.mInfoSize = fVar;
        fVar.J(0, 0, 16, 0);
        addView(a, fVar.i(a));
        g gVar2 = new g(context, false);
        gVar2.d(16);
        gVar2.l(-16734163);
        gVar2.m(24);
        gVar2.f(1);
        TextView a2 = gVar2.a();
        this.mRightText = a2;
        f fVar2 = new f(-2, 48);
        this.mTimeInfoSize = fVar2;
        addView(a2, fVar2.i(a2));
        setPadding(d.d(118), 0, d.d(24), 0);
    }

    public void bindData() {
        setAlpha(0.0f);
        setPadding(d.d(118), 0, d.d(24), 0);
        g.o(this.mLeftText, 24);
        g.o(this.mRightText, 24);
        this.mLeftText.setTextColor(TitleTabNoticeInfo.getInstance().getColorLeft());
        this.mLeftText.setText(TitleTabNoticeInfo.getInstance().getLeftText());
        this.mRightText.setTextColor(TitleTabNoticeInfo.getInstance().getColorRight());
        this.mRightText.setText(TitleTabNoticeInfo.getInstance().getRightText());
    }

    @Override // android.view.View
    public void setAlpha(float f2) {
        super.setAlpha(f2);
    }
}
