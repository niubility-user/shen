package com.facebook.react.views.image;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public class ImageLoadEvent extends Event<ImageLoadEvent> {
    public static final int ON_ANIMATION_END = 7;
    public static final int ON_ANIMATION_GET_DURATION = 9;
    public static final int ON_ANIMATION_REPEAT = 8;
    public static final int ON_ANIMATION_START = 6;
    public static final int ON_ERROR = 1;
    public static final int ON_LOAD = 2;
    public static final int ON_LOAD_END = 3;
    public static final int ON_LOAD_START = 4;
    public static final int ON_PROGRESS = 5;
    private final long mDurationMs;
    private final int mEventType;
    private final int mHeight;
    @Nullable
    private final String mImageError;
    @Nullable
    private final String mImageUri;
    private final int mWidth;

    public ImageLoadEvent(int i2, int i3) {
        this(i2, i3, null);
    }

    public static String eventNameForType(int i2) {
        switch (i2) {
            case 1:
                return "topError";
            case 2:
                return "topLoad";
            case 3:
                return "topLoadEnd";
            case 4:
                return "topLoadStart";
            case 5:
                return "topProgress";
            case 6:
                return "onAnimationStart";
            case 7:
                return "onAnimationEnd";
            case 8:
                return "onAnimationRepeat";
            case 9:
                return "onAnimationGetDuration";
            default:
                throw new IllegalStateException("Invalid image event: " + Integer.toString(i2));
        }
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        WritableMap createMap;
        int i2;
        if (this.mImageUri != null || (i2 = this.mEventType) == 2 || i2 == 1) {
            createMap = Arguments.createMap();
            String str = this.mImageUri;
            if (str != null) {
                createMap.putString("uri", str);
            }
            int i3 = this.mEventType;
            if (i3 == 2) {
                WritableMap createMap2 = Arguments.createMap();
                createMap2.putDouble("width", this.mWidth);
                createMap2.putDouble("height", this.mHeight);
                String str2 = this.mImageUri;
                if (str2 != null) {
                    createMap2.putString("url", str2);
                }
                createMap.putMap("source", createMap2);
            } else if (i3 == 1) {
                createMap.putString("error", this.mImageError);
            } else if (i3 == 9) {
                createMap.putString("durationMs", String.valueOf(this.mDurationMs));
            }
        } else {
            createMap = null;
        }
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), createMap);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public short getCoalescingKey() {
        return (short) this.mEventType;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return eventNameForType(this.mEventType);
    }

    public ImageLoadEvent(int i2, int i3, boolean z, String str) {
        this(i2, i3, (String) null, 0, 0, str);
    }

    public ImageLoadEvent(int i2, int i3, String str) {
        this(i2, i3, str, 0, 0, (String) null);
    }

    public ImageLoadEvent(int i2, int i3, @Nullable String str, int i4, int i5) {
        this(i2, i3, str, i4, i5, (String) null);
    }

    public ImageLoadEvent(int i2, int i3, @Nullable String str, int i4, int i5, long j2) {
        this(i2, i3, str, i4, i5, null, j2);
    }

    public ImageLoadEvent(int i2, int i3, @Nullable String str, int i4, int i5, @Nullable String str2) {
        this(i2, i3, str, i4, i5, str2, 0L);
    }

    public ImageLoadEvent(int i2, int i3, @Nullable String str, int i4, int i5, @Nullable String str2, long j2) {
        super(i2);
        this.mEventType = i3;
        this.mImageUri = str;
        this.mWidth = i4;
        this.mHeight = i5;
        this.mImageError = str2;
        this.mDurationMs = j2;
    }
}
