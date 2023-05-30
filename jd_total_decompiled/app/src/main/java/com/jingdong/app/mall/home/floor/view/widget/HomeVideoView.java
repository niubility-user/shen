package com.jingdong.app.mall.home.floor.view.widget;

import android.content.Context;
import com.jingdong.common.recommend.RecommendMtaUtils;
import tv.danmaku.ijk.media.example.widget.media.IPlayerControl;
import tv.danmaku.ijk.media.example.widget.media.JDVideoView;
import tv.danmaku.ijk.media.ext.mta.bean.PlayerReportInfoEntity;

/* loaded from: classes4.dex */
public class HomeVideoView extends JDVideoView {
    public HomeVideoView(Context context, String str) {
        super(context);
        IPlayerControl.PlayerOptions playerOptions = new IPlayerControl.PlayerOptions(false);
        playerOptions.setIsRequestAudioFocus(false);
        playerOptions.setVolume(0.0f);
        playerOptions.setPlayerReportInfoEntity(new PlayerReportInfoEntity(getClass().getSimpleName(), RecommendMtaUtils.Home_PageId));
        playerOptions.setPlayTypeId(str);
        setPlayerOptions(playerOptions);
    }
}
