package tv.danmaku.ijk.media.ext.tarns;

import android.app.Activity;
import android.app.ActivityOptions;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.RequiresApi;
import androidx.core.view.ViewCompat;
import tv.danmaku.ijk.media.example.widget.media.JDVideoView;
import tv.danmaku.ijk.media.ext.config.PlayerConfigLoader;

/* loaded from: classes11.dex */
public class JDVideoTransPlugin {
    private static final String TAG = "JDVideoTransPlugin";
    private static final int TRANS_DUR = 400;
    private static final JDVideoTransPlugin mInstance = new JDVideoTransPlugin();
    private final boolean enableShareAnim;
    private VideoCoverLayer enterCover;
    private VideoCoverLayer exitCover;
    public boolean inEnterAnim;
    public boolean inExitAnim;
    private boolean isExit = false;
    private JDVideoView jdVideoView;
    private JDVideoTransListener mEnterListener;
    private JDVideoTransListener mExitListener;
    private Bitmap mVideoShot;
    private TransitionSet transAnimSet;

    /* loaded from: classes11.dex */
    public interface JDVideoTransListener {
        void onAnimEnd();

        void onAnimStart();
    }

    private JDVideoTransPlugin() {
        boolean z = PlayerConfigLoader.getInstance().enableShare;
        this.enableShareAnim = z;
        if (Build.VERSION.SDK_INT < 21 || !z) {
            return;
        }
        this.transAnimSet = generateAnimSet();
        this.transAnimSet.addListener((Transition.TransitionListener) new TransListenerAdapter() { // from class: tv.danmaku.ijk.media.ext.tarns.JDVideoTransPlugin.1
            @Override // tv.danmaku.ijk.media.ext.tarns.TransListenerAdapter, android.transition.Transition.TransitionListener
            public void onTransitionEnd(Transition transition) {
                super.onTransitionEnd(transition);
                try {
                    if (!JDVideoTransPlugin.this.isExit || JDVideoTransPlugin.this.mEnterListener == null) {
                        if (JDVideoTransPlugin.this.mExitListener != null) {
                            JDVideoTransPlugin.this.mExitListener.onAnimEnd();
                            JDVideoTransPlugin.this.inExitAnim = false;
                        }
                    } else {
                        JDVideoTransPlugin.this.mEnterListener.onAnimEnd();
                        if (JDVideoTransPlugin.this.jdVideoView != null) {
                            JDVideoTransPlugin.this.jdVideoView.setActive(true);
                        }
                        JDVideoTransPlugin.this.inEnterAnim = false;
                    }
                    if (JDVideoTransPlugin.this.jdVideoView == null || !(JDVideoTransPlugin.this.jdVideoView.mRenderView instanceof TextureView)) {
                        return;
                    }
                    JDVideoTransPlugin jDVideoTransPlugin = JDVideoTransPlugin.this;
                    jDVideoTransPlugin.mVideoShot = ((TextureView) jDVideoTransPlugin.jdVideoView.mRenderView).getBitmap();
                    if (JDVideoTransPlugin.this.exitCover != null && JDVideoTransPlugin.this.mVideoShot != null) {
                        JDVideoTransPlugin.this.exitCover.setImageBitmap(JDVideoTransPlugin.this.mVideoShot);
                    }
                    if (JDVideoTransPlugin.this.enterCover == null || JDVideoTransPlugin.this.mVideoShot == null) {
                        return;
                    }
                    JDVideoTransPlugin.this.enterCover.setImageBitmap(JDVideoTransPlugin.this.mVideoShot);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            @Override // tv.danmaku.ijk.media.ext.tarns.TransListenerAdapter, android.transition.Transition.TransitionListener
            public void onTransitionStart(Transition transition) {
                super.onTransitionStart(transition);
                if (!JDVideoTransPlugin.this.isExit || JDVideoTransPlugin.this.mEnterListener == null) {
                    if (JDVideoTransPlugin.this.mExitListener != null) {
                        JDVideoTransPlugin.this.mExitListener.onAnimStart();
                        JDVideoTransPlugin.this.inExitAnim = true;
                        return;
                    }
                    return;
                }
                JDVideoTransPlugin.this.mEnterListener.onAnimStart();
                JDVideoTransPlugin.this.inEnterAnim = true;
            }
        });
    }

    @RequiresApi(api = 21)
    private TransitionSet generateAnimSet() {
        TransitionSet transitionSet = new TransitionSet();
        Fade fade = new Fade();
        fade.setDuration(400L);
        transitionSet.addTransition(fade);
        ChangeBounds changeBounds = new ChangeBounds();
        changeBounds.setDuration(400L);
        transitionSet.addTransition(changeBounds);
        ChangeImageTransform changeImageTransform = new ChangeImageTransform();
        changeBounds.setDuration(400L);
        transitionSet.addTransition(changeImageTransform);
        return transitionSet;
    }

    public static JDVideoTransPlugin getInstance() {
        return mInstance;
    }

    private static JDVideoView traversalVideoView(ViewGroup viewGroup) {
        JDVideoView traversalVideoView;
        for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if (childAt instanceof JDVideoView) {
                return (JDVideoView) childAt;
            }
            if ((childAt instanceof ViewGroup) && (traversalVideoView = traversalVideoView((ViewGroup) childAt)) != null) {
                return traversalVideoView;
            }
        }
        return null;
    }

    public Bundle makeVideoSceneEnterAnim(Activity activity, ViewGroup viewGroup, JDVideoTransListener jDVideoTransListener) {
        if (activity == null || viewGroup == null || !this.enableShareAnim || Build.VERSION.SDK_INT < 21) {
            return null;
        }
        this.mEnterListener = jDVideoTransListener;
        if (viewGroup instanceof JDVideoView) {
            this.jdVideoView = (JDVideoView) viewGroup;
        } else {
            this.jdVideoView = traversalVideoView(viewGroup);
        }
        if (this.jdVideoView != null) {
            if (this.enterCover == null) {
                this.enterCover = new VideoCoverLayer(viewGroup.getContext());
            }
            this.jdVideoView.setActive(false);
            this.mVideoShot = this.enterCover.setVideoView(this.jdVideoView, null);
        }
        this.isExit = false;
        activity.getWindow().setSharedElementEnterTransition(this.transAnimSet);
        return ActivityOptions.makeSceneTransitionAnimation(activity, viewGroup, "videoShare").toBundle();
    }

    public void makeVideoSceneExitAnim(JDVideoView jDVideoView) {
        makeVideoSceneExitAnim(jDVideoView, false);
    }

    public void releaseCoverLayer() {
        if (Build.VERSION.SDK_INT < 21 || !this.enableShareAnim) {
            return;
        }
        VideoCoverLayer videoCoverLayer = this.exitCover;
        if (videoCoverLayer != null) {
            if (videoCoverLayer.getParent() instanceof ViewGroup) {
                ((ViewGroup) this.exitCover.getParent()).removeView(this.exitCover);
            }
            this.exitCover = null;
        }
        VideoCoverLayer videoCoverLayer2 = this.enterCover;
        if (videoCoverLayer2 != null) {
            if (videoCoverLayer2.getParent() instanceof ViewGroup) {
                ((ViewGroup) this.enterCover.getParent()).removeView(this.enterCover);
            }
            this.enterCover = null;
        }
        Bitmap bitmap = this.mVideoShot;
        if (bitmap != null) {
            bitmap.recycle();
            this.mVideoShot = null;
        }
        this.mEnterListener = null;
        this.jdVideoView = null;
    }

    public void setupVideoSceneEnterAnim(Activity activity, ViewGroup viewGroup, JDVideoTransListener jDVideoTransListener) {
        JDVideoView traversalVideoView;
        if (activity == null || viewGroup == null || !this.enableShareAnim || Build.VERSION.SDK_INT < 21) {
            return;
        }
        this.mExitListener = jDVideoTransListener;
        if (viewGroup instanceof JDVideoView) {
            traversalVideoView = (JDVideoView) viewGroup;
        } else {
            traversalVideoView = traversalVideoView(viewGroup);
        }
        if (traversalVideoView != null) {
            if (this.exitCover == null) {
                this.exitCover = new VideoCoverLayer(viewGroup.getContext());
            }
            this.mVideoShot = this.exitCover.setVideoView(traversalVideoView, this.mVideoShot);
        }
        ViewCompat.setTransitionName(viewGroup, "videoShare");
        activity.getWindow().setSharedElementEnterTransition(this.transAnimSet);
    }

    public void makeVideoSceneExitAnim(JDVideoView jDVideoView, boolean z) {
        VideoCoverLayer videoCoverLayer;
        if (this.enableShareAnim) {
            JDVideoView jDVideoView2 = this.jdVideoView;
            if (jDVideoView2 != null) {
                jDVideoView2.setActive(true);
            }
            if (z && (videoCoverLayer = this.exitCover) != null) {
                if (videoCoverLayer.getParent() instanceof ViewGroup) {
                    ((ViewGroup) this.exitCover.getParent()).removeView(this.exitCover);
                }
                this.exitCover = null;
            }
            this.jdVideoView = jDVideoView;
            this.isExit = true;
        }
    }
}
