package com.jd.lib.flexcube.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import com.jd.lib.babel.ifloor.entity.BabelScope;
import com.jd.lib.babel.ifloor.ui.IFloorUI;
import com.jd.lib.babel.ifloor.utils.CommonServiceUtil;
import com.jd.lib.babel.servicekit.imagekit.ImageLoad;
import com.jd.lib.babel.servicekit.model.BaseEvent;
import com.jd.lib.babel.servicekit.model.MtaData;
import com.jd.lib.flexcube.FlexCube;
import com.jd.lib.flexcube.R;
import com.jd.lib.flexcube.iwidget.b.c;
import com.jd.lib.flexcube.iwidget.entity.material.ExposureInfo;
import com.jd.lib.flexcube.iwidget.entity.material.FlexVideoModel;
import com.jd.lib.flexcube.iwidget.ui.IKnowWidget;
import com.jd.lib.flexcube.iwidget.ui.IWidget;
import com.jd.lib.flexcube.iwidget.ui.IWidgetCommunication;
import com.jd.lib.flexcube.iwidget.ui.video.FlexCubeVideoService;
import com.jd.lib.flexcube.iwidget.ui.video.FlexPlayerStateListener;
import com.jd.lib.flexcube.iwidget.ui.video.IFlexPlayerStateListener;
import com.jd.lib.flexcube.widgets.b.e;
import com.jd.lib.flexcube.widgets.entity.VideoEntity;
import com.jd.lib.flexcube.widgets.entity.video.VideoConfig;
import com.jd.lib.flexcube.widgets.entity.video.VideoDataPath;
import com.jd.lib.flexcube.widgets.entity.video.VideoInfo;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class FlexVideoView extends FrameLayout implements IWidget<VideoEntity>, FlexPlayerStateListener, IKnowWidget<VideoEntity> {

    /* renamed from: g  reason: collision with root package name */
    private Context f4493g;

    /* renamed from: h  reason: collision with root package name */
    private VideoEntity f4494h;

    /* renamed from: i  reason: collision with root package name */
    private BabelScope f4495i;

    /* renamed from: j  reason: collision with root package name */
    private e f4496j;

    /* renamed from: k  reason: collision with root package name */
    private int f4497k;

    /* renamed from: l  reason: collision with root package name */
    private ImageView f4498l;

    /* renamed from: m  reason: collision with root package name */
    private ImageView f4499m;

    /* renamed from: n  reason: collision with root package name */
    private ImageView f4500n;
    private ImageView o;
    private View p;
    private float q;
    private Map<String, String> r;
    private ExposureInfo s;
    private JSONObject t;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (FlexVideoView.this.f() == -1.0f) {
                return;
            }
            if (FlexVideoView.this.f() == 0.0f) {
                FlexVideoView.this.f4500n.setImageResource(R.drawable.flexcube_voice_enable);
                FlexVideoView.this.r(1.0f);
                return;
            }
            FlexVideoView.this.f4500n.setImageResource(R.drawable.flexcube_voice_disable);
            FlexVideoView.this.r(0.0f);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            FlexVideoView.this.o();
        }
    }

    public FlexVideoView(@NonNull Context context) {
        super(context);
        this.f4493g = context;
        this.f4496j = new e(this);
        k();
    }

    private FlexVideoModel e() {
        String d = com.jd.lib.flexcube.widgets.b.b.d(this.r, this.f4494h.dataPath.videoUrl);
        if (c.c(d)) {
            return null;
        }
        FlexVideoModel flexVideoModel = new FlexVideoModel();
        flexVideoModel.setMute(true);
        flexVideoModel.setShowLoading(false);
        flexVideoModel.setBgColor(this.f4497k);
        flexVideoModel.setVideoUrl(d);
        flexVideoModel.setVideoFitType(this.f4494h.config.videoAutoFitType);
        flexVideoModel.setPlayerType(this.f4494h.config.playerType);
        flexVideoModel.setShowNonWifiTip(false);
        flexVideoModel.setShowErrorTip(false);
        if ("0".equals(this.f4494h.config.newWindowPlay)) {
            flexVideoModel.setNewWindowPlay(false);
        } else {
            flexVideoModel.setNewWindowPlay(true);
        }
        if ("1".equals(this.f4494h.config.wifiPlayRule)) {
            flexVideoModel.setWifiPoolPlay(true);
        } else {
            flexVideoModel.setWifiPoolPlay(false);
        }
        return flexVideoModel;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float f() {
        FlexCubeVideoService videoService = FlexCube.getInstance().getVideoService();
        if (videoService != null) {
            return videoService.getVolume(this.p);
        }
        return -1.0f;
    }

    private void i() {
        this.f4499m.setVisibility(4);
    }

    private void j() {
        if (getVisibility() == 0 && this.f4500n != null && m()) {
            this.f4500n.setVisibility(4);
        }
    }

    private void k() {
        ImageView newImageView = ImageLoad.newImageView(this.f4493g);
        this.f4498l = newImageView;
        newImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        addView(this.f4498l);
        ImageView newImageView2 = ImageLoad.newImageView(this.f4493g);
        this.o = newImageView2;
        newImageView2.setScaleType(ImageView.ScaleType.FIT_XY);
        addView(this.o, new FrameLayout.LayoutParams(-1, -1));
        ImageView newImageView3 = ImageLoad.newImageView(this.f4493g);
        this.f4499m = newImageView3;
        newImageView3.setScaleType(ImageView.ScaleType.FIT_XY);
        addView(this.f4499m);
    }

    private boolean l() {
        VideoConfig videoConfig;
        VideoInfo videoInfo;
        String str;
        VideoEntity videoEntity = this.f4494h;
        return (videoEntity == null || (videoConfig = videoEntity.config) == null || (videoInfo = videoConfig.videoInfo) == null || (str = videoInfo.showPlayButton) == null || !"1".equals(str)) ? false : true;
    }

    private boolean m() {
        VideoConfig videoConfig;
        VideoEntity videoEntity = this.f4494h;
        return (videoEntity == null || (videoConfig = videoEntity.config) == null || videoConfig.soundButton != 1) ? false : true;
    }

    private void n() {
        Map<String, String> map;
        ExposureInfo b2;
        try {
            if (this.f4495i == null || (map = this.r) == null || (b2 = com.jd.lib.flexcube.widgets.b.b.b(map, this.f4494h.dataPath.clickInfo)) == null || TextUtils.isEmpty(b2.eventId) || TextUtils.isEmpty(b2.srv)) {
                return;
            }
            JSONObject jSONObject = new JSONObject(b2.srv);
            this.s = b2;
            this.t = jSONObject;
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean o() {
        VideoEntity videoEntity;
        FlexCubeVideoService videoService = FlexCube.getInstance().getVideoService();
        if (videoService == null || getVisibility() != 0 || (videoEntity = this.f4494h) == null || videoEntity.config == null || videoEntity.dataPath == null) {
            return false;
        }
        View view = this.p;
        if (view == null || view.getParent() == null) {
            FlexVideoModel e2 = e();
            if (e2 == null) {
                return false;
            }
            View videoView = videoService.getVideoView(e2, this, this.f4493g);
            this.p = videoView;
            if (videoView == null) {
                return false;
            }
            this.p.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
            addView(this.p);
            i();
            bringChildToFront(this.o);
            x();
            return true;
        }
        return true;
    }

    private void p() {
        ViewGroup viewGroup;
        View view = this.p;
        if (view == null || (viewGroup = (ViewGroup) view.getParent()) == null) {
            return;
        }
        FlexCubeVideoService videoService = FlexCube.getInstance().getVideoService();
        if (videoService != null) {
            videoService.releasePlayer(this.p);
        }
        viewGroup.removeView(this.p);
        this.p = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r(float f2) {
        View view;
        FlexCubeVideoService videoService = FlexCube.getInstance().getVideoService();
        if (videoService == null || (view = this.p) == null) {
            return;
        }
        videoService.setVolume(view, f2);
    }

    private void s() {
        VideoConfig videoConfig;
        VideoEntity videoEntity = this.f4494h;
        if (videoEntity != null && (videoConfig = videoEntity.config) != null && !TextUtils.isEmpty(videoConfig.atmospherePicUrl)) {
            CommonServiceUtil.displayImage(this.f4494h.config.atmospherePicUrl, this.o);
            this.o.setVisibility(0);
            return;
        }
        this.o.setVisibility(4);
    }

    private void t() {
        if (getVisibility() == 0) {
            this.f4498l.setVisibility(0);
            if (l()) {
                this.f4499m.setVisibility(0);
            }
        }
    }

    private void u(boolean z) {
        if (l() && z) {
            this.f4499m.setVisibility(0);
            v();
            if (TextUtils.isEmpty(this.f4494h.config.videoInfo.playButtonUrl)) {
                this.f4499m.setImageResource(R.drawable.flex_video_play);
            } else {
                CommonServiceUtil.displayImage(this.f4494h.config.videoInfo.playButtonUrl, this.f4499m);
            }
            this.f4498l.setOnClickListener(new b());
            return;
        }
        this.f4498l.setOnClickListener(null);
        this.f4498l.setClickable(false);
        this.f4499m.setVisibility(8);
    }

    private void v() {
        int i2 = (int) (this.q * 133.0f);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i2, i2);
        String str = this.f4494h.config.videoInfo.playButtonPosition;
        if ("lp".equals(str)) {
            i2 = (int) (this.q * 108.0f);
            layoutParams.gravity = 51;
        } else if ("ld".equals(str)) {
            i2 = (int) (this.q * 108.0f);
            layoutParams.gravity = 83;
        } else if ("rp".equals(str)) {
            i2 = (int) (this.q * 108.0f);
            layoutParams.gravity = 53;
        } else if ("rd".equals(str)) {
            i2 = (int) (this.q * 108.0f);
            layoutParams.gravity = 85;
        } else {
            layoutParams.gravity = 17;
        }
        layoutParams.width = i2;
        layoutParams.height = i2;
        int i3 = (int) (this.q * 42.0f);
        layoutParams.leftMargin = i3;
        layoutParams.topMargin = i3;
        layoutParams.rightMargin = i3;
        layoutParams.bottomMargin = i3;
        this.f4499m.setLayoutParams(layoutParams);
    }

    private void x() {
        if (m()) {
            ImageView imageView = this.f4500n;
            if (imageView != null && imageView.getParent() != null) {
                if (f() == 0.0f) {
                    this.f4500n.setImageResource(R.drawable.flexcube_voice_disable);
                } else if (f() == 1.0f) {
                    this.f4500n.setImageResource(R.drawable.flexcube_voice_enable);
                }
                this.f4500n.setVisibility(0);
                bringChildToFront(this.f4500n);
                return;
            }
            ImageView newImageView = ImageLoad.newImageView(this.f4493g);
            this.f4500n = newImageView;
            newImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            addView(this.f4500n);
            int d = com.jd.lib.flexcube.iwidget.b.b.d(81, this.q);
            int d2 = com.jd.lib.flexcube.iwidget.b.b.d(30, this.q);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(d, d);
            layoutParams.leftMargin = (getLayoutParamsWidth() - d2) - d;
            layoutParams.topMargin = (getLayoutParamsHeight() - (d2 * 2)) - d;
            this.f4500n.setImageResource(R.drawable.flexcube_voice_disable);
            this.f4500n.setLayoutParams(layoutParams);
            this.f4500n.setOnClickListener(new a());
        }
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public void clear() {
        setVisibility(8);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        this.f4496j.b(canvas);
        super.draw(canvas);
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IKnowWidget
    /* renamed from: g  reason: merged with bridge method [inline-methods] */
    public VideoEntity getWidgetEntity() {
        return this.f4494h;
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public int getLayoutParamsHeight() {
        VideoEntity videoEntity = this.f4494h;
        if (videoEntity == null || videoEntity.getBaseConfig() == null) {
            return 0;
        }
        return this.f4494h.getBaseConfig().getH(this.q);
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public int getLayoutParamsWidth() {
        VideoEntity videoEntity = this.f4494h;
        if (videoEntity == null || videoEntity.getBaseConfig() == null) {
            return 0;
        }
        return this.f4494h.getBaseConfig().getW(this.q);
    }

    public boolean h() {
        return o();
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.video.FlexPlayerStateListener
    public void onCompletion() {
        t();
        j();
        BabelScope babelScope = this.f4495i;
        IFlexPlayerStateListener iFlexPlayerStateListener = babelScope != null ? (IFlexPlayerStateListener) babelScope.getService(IFlexPlayerStateListener.class) : null;
        if (iFlexPlayerStateListener != null) {
            iFlexPlayerStateListener.onCompletion(this.f4494h);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        p();
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.video.FlexPlayerStateListener
    public boolean onError(int i2, int i3) {
        BabelScope babelScope = this.f4495i;
        IFlexPlayerStateListener iFlexPlayerStateListener = babelScope != null ? (IFlexPlayerStateListener) babelScope.getService(IFlexPlayerStateListener.class) : null;
        if (iFlexPlayerStateListener != null) {
            iFlexPlayerStateListener.onError(i2, i3, this.f4494h);
            return false;
        }
        return false;
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.video.FlexPlayerStateListener
    public void onPlayState(int i2, int i3) {
        JSONObject jSONObject;
        int i4;
        if (i2 == 1) {
            try {
                ImageView imageView = this.f4498l;
                if (imageView != null) {
                    imageView.setVisibility(4);
                }
            } catch (Throwable unused) {
                return;
            }
        }
        if (this.s != null && (jSONObject = this.t) != null && this.f4495i != null && (i2 == 1 || i2 == 2 || i2 == 3)) {
            jSONObject.put("jud", "" + i3);
            if (i2 == 1) {
                this.t.put("clicktyp", "0");
            }
            if (i2 == 2) {
                this.t.put("clicktyp", "1");
            }
            if (i2 == 3) {
                this.t.put("clicktyp", "1");
            }
            FlexCubeVideoService videoService = FlexCube.getInstance().getVideoService();
            int i5 = 0;
            if (videoService != null) {
                int currentPosition = videoService.getCurrentPosition();
                i5 = videoService.getTotalDuration();
                i4 = currentPosition;
            } else {
                i4 = 0;
            }
            this.t.put("tssum", "" + (i5 / 1000));
            this.t.put("ts", "" + (i4 / 1000));
            this.s.srv = this.t.toString();
            Context context = this.f4493g;
            ExposureInfo exposureInfo = this.s;
            CommonServiceUtil.onClickMta(context, MtaData.Builder.from(exposureInfo.eventId, exposureInfo.srv).page(this.f4495i.getPageName(), this.f4495i.getPageParam()).build());
        }
        BabelScope babelScope = this.f4495i;
        IFlexPlayerStateListener iFlexPlayerStateListener = babelScope != null ? (IFlexPlayerStateListener) babelScope.getService(IFlexPlayerStateListener.class) : null;
        if (iFlexPlayerStateListener != null) {
            iFlexPlayerStateListener.onPlayState(i2, i3, this.f4494h);
        }
    }

    public void q() {
        Map<String, String> map;
        ExposureInfo b2;
        try {
            BabelScope babelScope = this.f4495i;
            if (babelScope == null || babelScope.iFloorUI == null || (map = this.r) == null || (b2 = com.jd.lib.flexcube.widgets.b.b.b(map, this.f4494h.dataPath.expoInfo)) == null || TextUtils.isEmpty(b2.eventId)) {
                return;
            }
            if (TextUtils.isEmpty(b2.srv)) {
                return;
            }
            this.f4495i.iFloorUI.sendExposureData(MtaData.Builder.from(b2.eventId, b2.srv).jsonParam(b2.srvData).split(true).build());
        } catch (Throwable unused) {
        }
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.video.FlexPlayerStateListener
    public void release() {
        IFloorUI iFloorUI;
        BabelScope babelScope = this.f4495i;
        if (babelScope != null && (iFloorUI = babelScope.iFloorUI) != null) {
            iFloorUI.sendEvent(new BaseEvent("releaseVideo"));
        }
        t();
        j();
        BabelScope babelScope2 = this.f4495i;
        IFlexPlayerStateListener iFlexPlayerStateListener = babelScope2 != null ? (IFlexPlayerStateListener) babelScope2.getService(IFlexPlayerStateListener.class) : null;
        if (iFlexPlayerStateListener != null) {
            iFlexPlayerStateListener.release(this.f4494h);
        }
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public void updateContent(@NonNull Map<String, String> map, IWidgetCommunication iWidgetCommunication) {
        VideoEntity videoEntity = this.f4494h;
        if (videoEntity != null && videoEntity.dataPath != null && videoEntity.config != null) {
            this.f4495i = iWidgetCommunication != null ? iWidgetCommunication.getBabelScope() : null;
            this.r = map;
            setVisibility(0);
            VideoDataPath videoDataPath = this.f4494h.dataPath;
            String d = com.jd.lib.flexcube.widgets.b.b.d(map, videoDataPath.pictureUrl);
            if (c.d(d)) {
                CommonServiceUtil.displayImage(d, this.f4498l);
            } else if (!"1".equals(this.f4494h.config.extern_nullPictureShow)) {
                clear();
            }
            if (c.d(com.jd.lib.flexcube.widgets.b.b.d(map, videoDataPath.videoUrl))) {
                u(true);
            } else {
                u(false);
            }
            q();
            n();
            return;
        }
        clear();
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    /* renamed from: w  reason: merged with bridge method [inline-methods] */
    public void updateStyle(@NonNull VideoEntity videoEntity, float f2) {
        this.f4494h = videoEntity;
        this.q = f2;
        if (videoEntity != null) {
            setVisibility(0);
            VideoConfig videoConfig = videoEntity.config;
            if (videoConfig != null) {
                this.f4496j.j(videoConfig.cfInfo, this.q, getLayoutParamsHeight() >> 1);
                if ("0".equals(videoConfig.imgAutoFitType)) {
                    this.f4498l.setScaleType(ImageView.ScaleType.CENTER_CROP);
                } else if ("1".equals(videoConfig.imgAutoFitType)) {
                    this.f4498l.setScaleType(ImageView.ScaleType.FIT_XY);
                } else if ("2".equals(videoConfig.imgAutoFitType)) {
                    this.f4498l.setScaleType(ImageView.ScaleType.FIT_CENTER);
                } else {
                    this.f4498l.setScaleType(ImageView.ScaleType.CENTER_CROP);
                }
                VideoInfo videoInfo = videoConfig.videoInfo;
                if (videoInfo != null) {
                    this.f4497k = com.jd.lib.flexcube.iwidget.b.a.a(videoInfo.bgColor, 0);
                }
                u(true);
                s();
                return;
            }
            clear();
            return;
        }
        clear();
    }
}
