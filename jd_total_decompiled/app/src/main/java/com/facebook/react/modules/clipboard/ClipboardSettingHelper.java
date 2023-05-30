package com.facebook.react.modules.clipboard;

/* loaded from: classes12.dex */
public class ClipboardSettingHelper {
    private static ClipboardSettingHelper mClipboardSettingHelper;
    private static ClipboardSettingListener mClipboardSettingListener;

    /* loaded from: classes12.dex */
    public interface ClipboardSettingListener {
        boolean isSupportClipboard();
    }

    public static ClipboardSettingHelper newInstance() {
        if (mClipboardSettingHelper == null) {
            mClipboardSettingHelper = new ClipboardSettingHelper();
        }
        return mClipboardSettingHelper;
    }

    public boolean isSupportClipboard() {
        ClipboardSettingListener clipboardSettingListener = mClipboardSettingListener;
        if (clipboardSettingListener != null) {
            return clipboardSettingListener.isSupportClipboard();
        }
        return true;
    }

    public void setClipboardSettingListener(ClipboardSettingListener clipboardSettingListener) {
        mClipboardSettingListener = clipboardSettingListener;
    }
}
