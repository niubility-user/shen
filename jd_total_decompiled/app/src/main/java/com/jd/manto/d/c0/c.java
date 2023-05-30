package com.jd.manto.d.c0;

import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener;
import com.jingdong.manto.jsapi.refact.video.IVideoInterface;
import com.jingdong.manto.jsapi.refact.video.JsApiVideoPlayer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class c {
    private int a;
    private com.jd.manto.d.c0.b b;

    /* renamed from: c */
    private Map<Integer, com.jd.manto.d.c0.a> f6633c = new HashMap();
    public MantoLifecycleLisener d = new b();

    /* loaded from: classes17.dex */
    public class a implements IVideoInterface {
        final /* synthetic */ MantoCore a;

        a(MantoCore mantoCore) {
            c.this = r1;
            this.a = mantoCore;
        }

        @Override // com.jingdong.manto.jsapi.refact.video.IVideoInterface
        public void onEnd(int i2) {
            JSONObject jSONObject = new JSONObject();
            try {
                com.jd.manto.d.c0.a aVar = (com.jd.manto.d.c0.a) c.this.f6633c.get(Integer.valueOf(i2));
                if (aVar != null) {
                    jSONObject.put(JsApiVideoPlayer.VIDEO_PLAYER_ID, i2);
                    jSONObject.put("data", aVar.getData());
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            c.this.b.dispatchEvent(this.a, IVideoInterface.onVideoEndedEvent, jSONObject, c.this.a);
        }

        @Override // com.jingdong.manto.jsapi.refact.video.IVideoInterface
        public void onError(int i2, int i3, int i4) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("errMsg", i3);
                com.jd.manto.d.c0.a aVar = (com.jd.manto.d.c0.a) c.this.f6633c.get(Integer.valueOf(i2));
                if (aVar != null) {
                    jSONObject.put(JsApiVideoPlayer.VIDEO_PLAYER_ID, i2);
                    jSONObject.put("data", aVar.getData());
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            c.this.b.dispatchEvent(this.a, IVideoInterface.onVideoErrorEvent, jSONObject, c.this.a);
        }

        @Override // com.jingdong.manto.jsapi.refact.video.IVideoInterface
        public void onFullScreenChange(int i2, boolean z, String str) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("fullScreen", z);
                jSONObject.put("direction", str);
                com.jd.manto.d.c0.a aVar = (com.jd.manto.d.c0.a) c.this.f6633c.get(Integer.valueOf(i2));
                if (aVar != null) {
                    jSONObject.put(JsApiVideoPlayer.VIDEO_PLAYER_ID, i2);
                    jSONObject.put("data", aVar.getData());
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            c.this.b.dispatchEvent(this.a, IVideoInterface.onVideoClickFullScreenEvent, jSONObject, c.this.a);
        }

        @Override // com.jingdong.manto.jsapi.refact.video.IVideoInterface
        public void onLoadedData(int i2) {
            JSONObject jSONObject = new JSONObject();
            try {
                com.jd.manto.d.c0.a aVar = (com.jd.manto.d.c0.a) c.this.f6633c.get(Integer.valueOf(i2));
                if (aVar != null) {
                    jSONObject.put(JsApiVideoPlayer.VIDEO_PLAYER_ID, i2);
                    jSONObject.put("data", aVar.getData());
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            c.this.b.dispatchEvent(this.a, IVideoInterface.onVideoLoadedDataEvent, jSONObject, c.this.a);
        }

        @Override // com.jingdong.manto.jsapi.refact.video.IVideoInterface
        public void onLoadedMetaData(int i2) {
            JSONObject jSONObject = new JSONObject();
            try {
                com.jd.manto.d.c0.a aVar = (com.jd.manto.d.c0.a) c.this.f6633c.get(Integer.valueOf(i2));
                if (aVar != null) {
                    jSONObject.put(JsApiVideoPlayer.VIDEO_PLAYER_ID, i2);
                    jSONObject.put("data", aVar.getData());
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            c.this.b.dispatchEvent(this.a, IVideoInterface.onVideoLoadedMetaDataEvent, jSONObject, c.this.a);
        }

        @Override // com.jingdong.manto.jsapi.refact.video.IVideoInterface
        public void onLoadedStart(int i2) {
            JSONObject jSONObject = new JSONObject();
            try {
                com.jd.manto.d.c0.a aVar = (com.jd.manto.d.c0.a) c.this.f6633c.get(Integer.valueOf(i2));
                if (aVar != null) {
                    jSONObject.put(JsApiVideoPlayer.VIDEO_PLAYER_ID, i2);
                    jSONObject.put("data", aVar.getData());
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            c.this.b.dispatchEvent(this.a, IVideoInterface.onVideoLoadStartEvent, jSONObject, c.this.a);
        }

        @Override // com.jingdong.manto.jsapi.refact.video.IVideoInterface
        public void onPause(int i2) {
            JSONObject jSONObject = new JSONObject();
            try {
                com.jd.manto.d.c0.a aVar = (com.jd.manto.d.c0.a) c.this.f6633c.get(Integer.valueOf(i2));
                if (aVar != null) {
                    jSONObject.put(JsApiVideoPlayer.VIDEO_PLAYER_ID, i2);
                    jSONObject.put("data", aVar.getData());
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            c.this.b.dispatchEvent(this.a, IVideoInterface.onVideoPauseEvent, jSONObject, c.this.a);
        }

        @Override // com.jingdong.manto.jsapi.refact.video.IVideoInterface
        public void onPlay(int i2) {
            JSONObject jSONObject = new JSONObject();
            try {
                com.jd.manto.d.c0.a aVar = (com.jd.manto.d.c0.a) c.this.f6633c.get(Integer.valueOf(i2));
                if (aVar != null) {
                    jSONObject.put(JsApiVideoPlayer.VIDEO_PLAYER_ID, i2);
                    jSONObject.put("data", aVar.getData());
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            c.this.b.dispatchEvent(this.a, IVideoInterface.onVideoPlayEvent, jSONObject, c.this.a);
        }

        @Override // com.jingdong.manto.jsapi.refact.video.IVideoInterface
        public void onSeeked(int i2) {
            JSONObject jSONObject = new JSONObject();
            try {
                com.jd.manto.d.c0.a aVar = (com.jd.manto.d.c0.a) c.this.f6633c.get(Integer.valueOf(i2));
                if (aVar != null) {
                    jSONObject.put(JsApiVideoPlayer.VIDEO_PLAYER_ID, i2);
                    jSONObject.put("data", aVar.getData());
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            c.this.b.dispatchEvent(this.a, IVideoInterface.onVideoSeekedEvent, jSONObject, c.this.a);
        }

        @Override // com.jingdong.manto.jsapi.refact.video.IVideoInterface
        public void onSeeking(int i2) {
            JSONObject jSONObject = new JSONObject();
            try {
                com.jd.manto.d.c0.a aVar = (com.jd.manto.d.c0.a) c.this.f6633c.get(Integer.valueOf(i2));
                if (aVar != null) {
                    jSONObject.put(JsApiVideoPlayer.VIDEO_PLAYER_ID, i2);
                    jSONObject.put("data", aVar.getData());
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            c.this.b.dispatchEvent(this.a, IVideoInterface.onVideoSeekingEvent, jSONObject, c.this.a);
        }

        @Override // com.jingdong.manto.jsapi.refact.video.IVideoInterface
        public void onWaiting(int i2) {
            JSONObject jSONObject = new JSONObject();
            try {
                com.jd.manto.d.c0.a aVar = (com.jd.manto.d.c0.a) c.this.f6633c.get(Integer.valueOf(i2));
                if (aVar != null) {
                    jSONObject.put(JsApiVideoPlayer.VIDEO_PLAYER_ID, i2);
                    jSONObject.put("data", aVar.getData());
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            c.this.b.dispatchEvent(this.a, IVideoInterface.onVideoWaitingEvent, jSONObject, c.this.a);
        }

        @Override // com.jingdong.manto.jsapi.refact.video.IVideoInterface
        public void timeUpdate(int i2) {
            JSONObject jSONObject = new JSONObject();
            try {
                com.jd.manto.d.c0.a aVar = (com.jd.manto.d.c0.a) c.this.f6633c.get(Integer.valueOf(i2));
                if (aVar != null) {
                    jSONObject.put(JsApiVideoPlayer.VIDEO_PLAYER_ID, i2);
                    jSONObject.put("data", aVar.getData());
                    double l2 = aVar.l();
                    Double.isNaN(l2);
                    jSONObject.put("position", l2 / 1000.0d);
                    double videoDuration = aVar.getVideoDuration();
                    Double.isNaN(videoDuration);
                    jSONObject.put("duration", videoDuration / 1000.0d);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            c.this.b.dispatchEvent(this.a, IVideoInterface.onVideoTimeUpdateEvent, jSONObject, c.this.a);
        }
    }

    /* loaded from: classes17.dex */
    public class b implements MantoLifecycleLisener {
        b() {
            c.this = r1;
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener
        public void onBackground() {
            Iterator it = c.this.f6633c.values().iterator();
            while (it.hasNext()) {
                ((com.jd.manto.d.c0.a) it.next()).pauseIfPlaying();
            }
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener
        public void onDestroy() {
            Iterator it = c.this.f6633c.values().iterator();
            while (it.hasNext()) {
                ((com.jd.manto.d.c0.a) it.next()).destroy();
            }
            c.this.f6633c.clear();
            c.this.b.b(c.this.a);
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener
        public void onForeground() {
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener
        public void onPause() {
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener
        public void onReady() {
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener
        public boolean onRemove() {
            return false;
        }
    }

    public c(int i2, com.jd.manto.d.c0.b bVar) {
        this.a = i2;
        this.b = bVar;
    }

    public void d(MantoCore mantoCore, com.jd.manto.d.c0.a aVar, int i2) {
        this.f6633c.put(Integer.valueOf(i2), aVar);
        aVar.g(new a(mantoCore));
    }

    public void e(int i2) {
        if (this.f6633c.get(Integer.valueOf(i2)) != null) {
            this.f6633c.remove(Integer.valueOf(i2));
        }
    }
}
