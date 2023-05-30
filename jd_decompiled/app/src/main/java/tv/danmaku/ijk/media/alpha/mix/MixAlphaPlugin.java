package tv.danmaku.ijk.media.alpha.mix;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MotionEvent;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONObject;
import tv.danmaku.ijk.media.alpha.AlphaConfig;
import tv.danmaku.ijk.media.alpha.IAlphaPlugin;
import tv.danmaku.ijk.media.alpha.IFetchResource;
import tv.danmaku.ijk.media.alpha.OnResourceClickListener;
import tv.danmaku.ijk.media.alpha.mix.Src;
import tv.danmaku.ijk.media.alpha.player.AlphaPlayer;
import tv.danmaku.ijk.media.alpha.util.BitmapUtil;

/* loaded from: classes11.dex */
public class MixAlphaPlugin implements IAlphaPlugin {
    private static final String TAG = "MixAlphaPlugin";
    private FrameAll frameAll;
    private MixRender mixRender;
    private AlphaPlayer player;
    private OnResourceClickListener resourceClickListener;
    private IFetchResource resourceRequest;
    private SrcMap srcMap;
    private int curFrameIndex = -1;
    private int resultCbCount = 0;
    private boolean autoTxtColorFill = true;
    private final Object lock = new Object();
    private boolean forceStopLock = false;
    private MixTouch mixTouch = new MixTouch(this);

    /* renamed from: tv.danmaku.ijk.media.alpha.mix.MixAlphaPlugin$1 */
    /* loaded from: classes11.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$tv$danmaku$ijk$media$alpha$mix$Src$SrcType;

        static {
            int[] iArr = new int[Src.SrcType.values().length];
            $SwitchMap$tv$danmaku$ijk$media$alpha$mix$Src$SrcType = iArr;
            try {
                iArr[Src.SrcType.IMG.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$tv$danmaku$ijk$media$alpha$mix$Src$SrcType[Src.SrcType.TXT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public MixAlphaPlugin(AlphaPlayer alphaPlayer) {
        this.player = alphaPlayer;
    }

    /* renamed from: a */
    public /* synthetic */ void b(Resource resource) {
        this.resourceClickListener.OnClick(resource);
    }

    private boolean createBitmap() {
        SrcMap srcMap = this.srcMap;
        if (srcMap == null || srcMap.getMap() == null) {
            return false;
        }
        for (Src src : this.srcMap.getMap().values()) {
            if (src.getSrcType() == Src.SrcType.TXT) {
                src.setBitmap(BitmapUtil.createTxtBitmap(src));
            }
        }
        return true;
    }

    private void destroy() {
        forceStopLockThread();
        AlphaConfig alphaConfig = this.player.configManager.config;
        if (alphaConfig == null || !alphaConfig.isMix || this.srcMap == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        SrcMap srcMap = this.srcMap;
        if (srcMap != null && srcMap.getMap() != null) {
            this.srcMap.getMap().values();
            for (Src src : this.srcMap.getMap().values()) {
                int i2 = AnonymousClass1.$SwitchMap$tv$danmaku$ijk$media$alpha$mix$Src$SrcType[src.getSrcType().ordinal()];
                if (i2 != 1) {
                    if (i2 == 2 && src.getBitmap() != null) {
                        src.getBitmap().recycle();
                    }
                } else {
                    arrayList.add(new Resource(src));
                }
            }
            this.srcMap.getMap().clear();
        }
        IFetchResource iFetchResource = this.resourceRequest;
        if (iFetchResource != null) {
            iFetchResource.releaseResource(arrayList);
        }
        FrameAll frameAll = this.frameAll;
        if (frameAll != null && frameAll.getMap() != null) {
            this.frameAll.getMap().clear();
        }
        this.curFrameIndex = -1;
    }

    private void fetchResourceSync() {
        synchronized (this.lock) {
            this.forceStopLock = false;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        int size = this.srcMap.getMap().size();
        String str = "load resource totalSrc = " + size;
        this.resultCbCount = 0;
        SrcMap srcMap = this.srcMap;
        if (srcMap != null && srcMap.getMap() != null) {
            this.srcMap.getMap().values();
            for (Src src : this.srcMap.getMap().values()) {
                if (src.getSrcType() == Src.SrcType.IMG) {
                    String str2 = "fetch image " + src.getSrcId();
                    Bitmap fetchImage = this.resourceRequest.fetchImage(new Resource(src));
                    if (fetchImage == null) {
                        src.setBitmap(BitmapUtil.crateEmptyBitmap());
                    } else {
                        src.setBitmap(fetchImage);
                    }
                    resultCall();
                } else if (src.getSrcType() == Src.SrcType.TXT) {
                    String fetchText = this.resourceRequest.fetchText(new Resource(src));
                    if (!TextUtils.isEmpty(fetchText)) {
                        src.setTxt(fetchText);
                    }
                    resultCall();
                }
            }
        }
        synchronized (this.lock) {
            while (this.resultCbCount < size && !this.forceStopLock) {
                try {
                    this.lock.wait();
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
            }
        }
        String str3 = "fetchResourceSync cost= " + (SystemClock.elapsedRealtime() - elapsedRealtime) + "ms";
    }

    private void forceStopLockThread() {
        synchronized (this.lock) {
            this.forceStopLock = true;
            this.lock.notifyAll();
        }
    }

    private void parseFrame(AlphaConfig alphaConfig) {
        JSONObject jSONObject;
        if (alphaConfig == null || (jSONObject = alphaConfig.jsonConfig) == null) {
            return;
        }
        this.frameAll = new FrameAll(jSONObject);
    }

    private void parseSrc(AlphaConfig alphaConfig) {
        JSONObject jSONObject;
        if (alphaConfig == null || (jSONObject = alphaConfig.jsonConfig) == null) {
            return;
        }
        this.srcMap = new SrcMap(jSONObject);
    }

    private void resultCall() {
        synchronized (this.lock) {
            this.resultCbCount++;
            this.lock.notifyAll();
        }
    }

    public int getCurFrameIndex() {
        return this.curFrameIndex;
    }

    public FrameAll getFrameAll() {
        return this.frameAll;
    }

    public AlphaPlayer getPlayer() {
        return this.player;
    }

    public OnResourceClickListener getResourceClickListener() {
        return this.resourceClickListener;
    }

    public IFetchResource getResourceRequest() {
        return this.resourceRequest;
    }

    public SrcMap getSrcMap() {
        return this.srcMap;
    }

    public boolean isAutoTxtColorFill() {
        return this.autoTxtColorFill;
    }

    @Override // tv.danmaku.ijk.media.alpha.IAlphaPlugin
    public int onConfigCreate(AlphaConfig alphaConfig) {
        SrcMap srcMap;
        if (alphaConfig.isMix && this.resourceRequest != null) {
            parseSrc(alphaConfig);
            parseFrame(alphaConfig);
            fetchResourceSync();
            if (!createBitmap() || (srcMap = this.srcMap) == null || srcMap.getMap() == null) {
                return 10006;
            }
            this.srcMap.getMap().values();
            for (Src src : this.srcMap.getMap().values()) {
                if (src.getBitmap() == null) {
                    String str = "missing src = " + src.getSrcId();
                    return 10006;
                } else if (src.getBitmap().getConfig() == Bitmap.Config.ALPHA_8) {
                    return 10006;
                }
            }
            return 0;
        }
        return 0;
    }

    @Override // tv.danmaku.ijk.media.alpha.IAlphaPlugin
    public void onDecoding(int i2) {
    }

    @Override // tv.danmaku.ijk.media.alpha.IAlphaPlugin
    public void onDestroy() {
        destroy();
    }

    @Override // tv.danmaku.ijk.media.alpha.IAlphaPlugin
    public boolean onDispatchTouchEvent(MotionEvent motionEvent) {
        AlphaConfig alphaConfig = this.player.configManager.config;
        if ((alphaConfig == null || alphaConfig.isMix) && this.resourceClickListener != null) {
            final Resource onTouchEvent = this.mixTouch.onTouchEvent(motionEvent);
            if (onTouchEvent != null) {
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: tv.danmaku.ijk.media.alpha.mix.b
                    {
                        MixAlphaPlugin.this = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        MixAlphaPlugin.this.b(onTouchEvent);
                    }
                });
                return true;
            }
            return true;
        }
        return false;
    }

    @Override // tv.danmaku.ijk.media.alpha.IAlphaPlugin
    public void onRelease() {
        destroy();
    }

    @Override // tv.danmaku.ijk.media.alpha.IAlphaPlugin
    public void onRenderCreate() {
        AlphaConfig alphaConfig = this.player.configManager.config;
        if (alphaConfig == null || !alphaConfig.isMix) {
            return;
        }
        MixRender mixRender = new MixRender(this);
        this.mixRender = mixRender;
        mixRender.init();
    }

    @Override // tv.danmaku.ijk.media.alpha.IAlphaPlugin
    public void onRendering(int i2) {
        ArrayList<Frame> list;
        AlphaConfig alphaConfig = this.player.configManager.config;
        if (alphaConfig == null || !alphaConfig.isMix) {
            return;
        }
        this.curFrameIndex = i2;
        FrameAll frameAll = this.frameAll;
        if (frameAll == null || frameAll.getMap() == null || this.frameAll.getMap().get(i2) == null || (list = this.frameAll.getMap().get(i2).getList()) == null) {
            return;
        }
        Iterator<Frame> it = list.iterator();
        while (it.hasNext()) {
            Frame next = it.next();
            Src src = this.srcMap.getMap().get(next.getSrcId());
            if (src != null) {
                this.mixRender.renderFrame(alphaConfig, next, src);
            }
        }
    }

    public void setResourceClickListener(OnResourceClickListener onResourceClickListener) {
        this.resourceClickListener = onResourceClickListener;
    }

    public void setResourceRequest(IFetchResource iFetchResource) {
        this.resourceRequest = iFetchResource;
    }
}
