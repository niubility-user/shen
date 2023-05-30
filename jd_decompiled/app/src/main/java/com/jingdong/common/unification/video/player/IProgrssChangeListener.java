package com.jingdong.common.unification.video.player;

import tv.danmaku.ijk.media.widget.uniform.inter.IJDProgressChangeListener;

/* loaded from: classes6.dex */
public interface IProgrssChangeListener extends IJDProgressChangeListener {
    @Override // tv.danmaku.ijk.media.widget.uniform.inter.IJDProgressChangeListener
    void onProgressChange(int i2, int i3);

    @Override // tv.danmaku.ijk.media.widget.uniform.inter.IJDProgressChangeListener
    void onProgressPointSelect(int i2);
}
