package tv.danmaku.ijk.media.example.widget.media;

import android.content.Context;
import com.jingdong.common.utils.LangUtils;
import java.lang.ref.WeakReference;

/* loaded from: classes11.dex */
public class FFmpegInvoke {
    private static boolean mIsLibLoaded;
    private static int mIsLog;
    private static WeakReference<OnExecListener> mOnExecListener;

    /* loaded from: classes11.dex */
    public interface OnExecListener {
        void onExecuted(int i2);
    }

    public static String buildCmd(String str, String str2, int i2, String str3) {
        return "ffmpeg -i " + str + " -r " + i2 + " -b:v " + str3 + " -b:a 44k " + str2;
    }

    public static void exec(String str, OnExecListener onExecListener) {
        exec(str.split(LangUtils.SINGLE_SPACE), onExecListener);
    }

    public static native int ffmpegcore(int i2, String[] strArr, int i3);

    public static boolean loadLibrariesOnce(Context context) {
        if (mIsLibLoaded && MediaPlayerHelper.isLoadJniOk) {
            return true;
        }
        MediaPlayerHelper.loadLibrariesOnceSafe(context);
        try {
            System.loadLibrary("ffmpeginvoke");
            mIsLibLoaded = true;
        } catch (Throwable th) {
            mIsLibLoaded = false;
            th.printStackTrace();
        }
        return mIsLibLoaded && MediaPlayerHelper.isLoadJniOk;
    }

    public static void onExecuted(int i2) {
        WeakReference<OnExecListener> weakReference = mOnExecListener;
        if (weakReference != null) {
            OnExecListener onExecListener = weakReference.get();
            if (onExecListener != null) {
                onExecListener.onExecuted(i2);
            }
            mOnExecListener = null;
        }
    }

    public static void setIsLog(int i2) {
        mIsLog = i2;
    }

    private static void exec(String[] strArr, OnExecListener onExecListener) {
        mOnExecListener = new WeakReference<>(onExecListener);
        ffmpegcore(strArr.length, strArr, mIsLog);
    }
}
