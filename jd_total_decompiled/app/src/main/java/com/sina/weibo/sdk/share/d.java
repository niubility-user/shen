package com.sina.weibo.sdk.share;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.sina.weibo.sdk.api.VideoSourceObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.b.a;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import tv.danmaku.ijk.media.alpha.util.MediaUtil;

/* loaded from: classes9.dex */
public final class d extends AsyncTask<WeiboMultiMessage, Void, c> {
    private WeakReference<Context> B;
    private b C;

    public d(Context context, b bVar) {
        this.B = new WeakReference<>(context);
        this.C = bVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0096 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:109:0x005c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x012d A[Catch: all -> 0x0189, TryCatch #0 {all -> 0x0189, blocks: (B:16:0x002c, B:18:0x0030, B:20:0x0034, B:21:0x0036, B:23:0x003a, B:25:0x003e, B:27:0x0042, B:28:0x0046, B:30:0x004d, B:31:0x005c, B:33:0x0062, B:35:0x006a, B:37:0x0074, B:39:0x007f, B:41:0x0089, B:46:0x0096, B:48:0x009a, B:49:0x00a1, B:51:0x00ab, B:52:0x00b8, B:53:0x00bf, B:54:0x00c0, B:55:0x00c7, B:56:0x00c8, B:57:0x00cc, B:59:0x00d0, B:61:0x00d4, B:63:0x00de, B:65:0x00e9, B:74:0x011a, B:76:0x0120, B:81:0x012d, B:83:0x0131, B:84:0x0147, B:86:0x015e, B:87:0x0174, B:88:0x017b, B:68:0x00f8, B:70:0x0102, B:73:0x010a, B:89:0x017c, B:90:0x0183, B:91:0x0184), top: B:99:0x002c }] */
    @Override // android.os.AsyncTask
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public c doInBackground(WeiboMultiMessage... weiboMultiMessageArr) {
        WeiboMultiMessage weiboMultiMessage;
        Uri uri;
        boolean z;
        boolean z2;
        Context context = this.B.get();
        if (context == null || (weiboMultiMessage = weiboMultiMessageArr[0]) == null) {
            return null;
        }
        a.C0775a e2 = com.sina.weibo.sdk.b.a.e(context);
        String str = e2 != null ? e2.packageName : "";
        if (TextUtils.isEmpty(str)) {
            str = "com.sina.weibo";
        }
        c cVar = new c();
        try {
            if (weiboMultiMessage.imageObject != null && weiboMultiMessage.multiImageObject != null) {
                weiboMultiMessage.imageObject = null;
            }
            if (weiboMultiMessage.videoSourceObject != null && (weiboMultiMessage.imageObject != null || weiboMultiMessage.multiImageObject != null)) {
                weiboMultiMessage.imageObject = null;
                weiboMultiMessage.multiImageObject = null;
            }
            if (weiboMultiMessage.multiImageObject != null) {
                ArrayList<Uri> arrayList = new ArrayList<>();
                Iterator<Uri> it = weiboMultiMessage.multiImageObject.getImageList().iterator();
                while (it.hasNext()) {
                    Uri next = it.next();
                    if (next != null) {
                        String a = com.sina.weibo.sdk.b.b.a(context, next);
                        if (!TextUtils.isEmpty(a)) {
                            File file = new File(a);
                            if (!com.sina.weibo.sdk.b.b.c(file)) {
                                String b = com.sina.weibo.sdk.b.b.b(file);
                                if (!TextUtils.isEmpty(b) && b.startsWith("image/")) {
                                    z2 = true;
                                    if (!z2) {
                                        continue;
                                    } else if (Build.VERSION.SDK_INT >= 24) {
                                        arrayList.add(next);
                                        context.grantUriPermission(str, next, 1);
                                    } else {
                                        String a2 = a.a(context, next, 1);
                                        if (!TextUtils.isEmpty(a2)) {
                                            arrayList.add(Uri.fromFile(new File(a2)));
                                        } else {
                                            throw new IllegalArgumentException("image's path is null");
                                        }
                                    }
                                }
                            }
                            z2 = false;
                            if (!z2) {
                            }
                        } else {
                            throw new IllegalArgumentException("get image path is null");
                        }
                    }
                }
                weiboMultiMessage.multiImageObject.imageList = arrayList;
            }
            VideoSourceObject videoSourceObject = weiboMultiMessage.videoSourceObject;
            if (videoSourceObject != null && (uri = videoSourceObject.videoPath) != null) {
                String a3 = com.sina.weibo.sdk.b.b.a(context, uri);
                if (!TextUtils.isEmpty(a3)) {
                    File file2 = new File(a3);
                    if (!com.sina.weibo.sdk.b.b.c(file2)) {
                        String str2 = "*/*";
                        String name = file2.getName();
                        int lastIndexOf = name.lastIndexOf(OrderISVUtil.MONEY_DECIMAL);
                        if (lastIndexOf >= 0) {
                            String substring = name.substring(lastIndexOf);
                            if (!TextUtils.isEmpty(substring) || substring.length() >= 2) {
                                str2 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(substring.substring(1).toLowerCase());
                            }
                        }
                        if (!TextUtils.isEmpty(str2) && str2.startsWith(MediaUtil.TRACK_VIDEO)) {
                            z = true;
                            if (z) {
                                if (Build.VERSION.SDK_INT >= 24) {
                                    VideoSourceObject videoSourceObject2 = weiboMultiMessage.videoSourceObject;
                                    videoSourceObject2.videoPath = uri;
                                    videoSourceObject2.during = com.sina.weibo.sdk.b.b.e(com.sina.weibo.sdk.b.b.b(context, uri));
                                    context.grantUriPermission(str, weiboMultiMessage.videoSourceObject.videoPath, 1);
                                } else {
                                    String a4 = a.a(context, uri, 0);
                                    com.sina.weibo.sdk.b.c.a("WBShareTag", "prepare video resource and video'path is".concat(String.valueOf(a4)));
                                    if (!TextUtils.isEmpty(a4)) {
                                        weiboMultiMessage.videoSourceObject.videoPath = Uri.fromFile(new File(a4));
                                        weiboMultiMessage.videoSourceObject.during = com.sina.weibo.sdk.b.b.e(a4);
                                    } else {
                                        throw new IllegalArgumentException("video's path is null");
                                    }
                                }
                            }
                        }
                    }
                    z = false;
                    if (z) {
                    }
                } else {
                    throw new IllegalArgumentException("get video path is null");
                }
            }
            cVar.A = weiboMultiMessage;
            cVar.z = true;
        } catch (Throwable th) {
            cVar.z = false;
            String message = th.getMessage();
            if (TextUtils.isEmpty(message)) {
                message = th.toString();
            }
            cVar.errorMessage = message;
            com.sina.weibo.sdk.b.c.b("WBShareTag", "prepare resource error is :".concat(String.valueOf(message)));
        }
        return cVar;
    }

    @Override // android.os.AsyncTask
    protected final /* synthetic */ void onPostExecute(c cVar) {
        c cVar2 = cVar;
        super.onPostExecute(cVar2);
        b bVar = this.C;
        if (bVar != null) {
            bVar.a(cVar2);
        }
    }

    @Override // android.os.AsyncTask
    protected final void onPreExecute() {
        super.onPreExecute();
    }
}
