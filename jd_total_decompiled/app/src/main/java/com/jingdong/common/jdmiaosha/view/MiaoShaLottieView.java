package com.jingdong.common.jdmiaosha.view;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.JsonReader;
import android.view.View;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.jdsdk.JdSdk;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0013\b\u0016\u0012\b\u0010#\u001a\u0004\u0018\u00010\"\u00a2\u0006\u0004\b$\u0010%B\u001d\b\u0016\u0012\b\u0010#\u001a\u0004\u0018\u00010\"\u0012\b\u0010'\u001a\u0004\u0018\u00010&\u00a2\u0006\u0004\b$\u0010(B%\b\u0016\u0012\b\u0010#\u001a\u0004\u0018\u00010\"\u0012\b\u0010'\u001a\u0004\u0018\u00010&\u0012\u0006\u0010*\u001a\u00020)\u00a2\u0006\u0004\b$\u0010+J!\u0010\u0006\u001a\u00020\u00052\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0019\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0002H\u0002\u00a2\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\r\u001a\u0004\u0018\u00010\u00022\b\u0010\f\u001a\u0004\u0018\u00010\u0002H\u0002\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0019\u0010\u0011\u001a\u00020\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0019\u0010\u0011\u001a\u00020\u00052\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013H\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0015J\u0017\u0010\u0016\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0018H\u0016\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u001cH\u0016\u00a2\u0006\u0004\b\u001e\u0010\u001fR\u0018\u0010 \u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b \u0010!\u00a8\u0006,"}, d2 = {"Lcom/jingdong/common/jdmiaosha/view/MiaoShaLottieView;", "Lcom/airbnb/lottie/LottieAnimationView;", "", "json", "key", "", "setLottieJson", "(Ljava/lang/String;Ljava/lang/String;)V", "lottieStr", "", "isValid", "(Ljava/lang/String;)Z", "name", "getAssetsString", "(Ljava/lang/String;)Ljava/lang/String;", "Ljava/io/InputStream;", "inputStream", "close", "(Ljava/io/InputStream;)V", "Ljava/io/Reader;", "reader", "(Ljava/io/Reader;)V", "initLiveLottie", "(Ljava/lang/String;)V", "Landroid/graphics/Canvas;", "canvas", "draw", "(Landroid/graphics/Canvas;)V", "Lcom/airbnb/lottie/LottieComposition;", "composition", "setComposition", "(Lcom/airbnb/lottie/LottieComposition;)V", "mLottieStr", "Ljava/lang/String;", "Landroid/content/Context;", AnnoConst.Constructor_Context, "<init>", "(Landroid/content/Context;)V", "Landroid/util/AttributeSet;", "attrs", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "", "defStyleAttr", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "AndroidJD-Lib_androidRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes5.dex */
public final class MiaoShaLottieView extends LottieAnimationView {
    private HashMap _$_findViewCache;
    private String mLottieStr;

    public MiaoShaLottieView(@Nullable Context context) {
        super(context);
    }

    private final void close(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Exception unused) {
            }
        }
    }

    /* JADX WARN: Type inference failed for: r4v0, types: [T, java.lang.String] */
    private final String getAssetsString(String name) {
        BufferedReader bufferedReader;
        InputStream inputStream;
        Reader reader;
        InputStream open;
        String str;
        InputStream inputStream2 = null;
        if (TextUtils.isEmpty(name)) {
            return null;
        }
        JdSdk jdSdk = JdSdk.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(jdSdk, "JdSdk.getInstance()");
        Context context = jdSdk.getApplicationContext();
        try {
            Intrinsics.checkExpressionValueIsNotNull(context, "context");
            Resources resources = context.getResources();
            Intrinsics.checkExpressionValueIsNotNull(resources, "context.resources");
            AssetManager assets = resources.getAssets();
            if (name == null) {
                Intrinsics.throwNpe();
            }
            open = assets.open(name);
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(open));
            } catch (Exception e2) {
                reader = null;
                inputStream = open;
                e = e2;
            } catch (Throwable th) {
                inputStream2 = open;
                th = th;
                bufferedReader = null;
            }
        } catch (Exception e3) {
            e = e3;
            inputStream = null;
            reader = null;
        } catch (Throwable th2) {
            th = th2;
            bufferedReader = null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            while (true) {
                ?? readLine = bufferedReader.readLine();
                objectRef.element = readLine;
                if (readLine != 0) {
                    String str2 = (String) readLine;
                    if (str2 != null) {
                        int length = str2.length() - 1;
                        int i2 = 0;
                        boolean z = false;
                        while (i2 <= length) {
                            boolean z2 = str2.charAt(!z ? i2 : length) <= ' ';
                            if (z) {
                                if (!z2) {
                                    break;
                                }
                                length--;
                            } else if (z2) {
                                i2++;
                            } else {
                                z = true;
                            }
                        }
                        str = str2.subSequence(i2, length + 1).toString();
                    } else {
                        str = null;
                    }
                    sb.append(str);
                } else {
                    close(open);
                    close(bufferedReader);
                    String sb2 = sb.toString();
                    close(open);
                    close(bufferedReader);
                    return sb2;
                }
            }
        } catch (Exception e4) {
            inputStream = open;
            e = e4;
            reader = bufferedReader;
            try {
                e.printStackTrace();
                close(inputStream);
                close(reader);
                return null;
            } catch (Throwable th3) {
                th = th3;
                inputStream2 = inputStream;
                bufferedReader = reader;
                close(inputStream2);
                close(bufferedReader);
                throw th;
            }
        } catch (Throwable th4) {
            inputStream2 = open;
            th = th4;
            close(inputStream2);
            close(bufferedReader);
            throw th;
        }
    }

    private final boolean isValid(String lottieStr) {
        try {
            if (TextUtils.isEmpty(lottieStr)) {
                return false;
            }
            JsonReader jsonReader = new JsonReader(new StringReader(lottieStr));
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                jsonReader.skipValue();
            }
            jsonReader.endObject();
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    private final void setLottieJson(String json, String key) {
        setAnimationFromJson(json, key);
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i2) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i2));
        if (view == null) {
            View findViewById = findViewById(i2);
            this._$_findViewCache.put(Integer.valueOf(i2), findViewById);
            return findViewById;
        }
        return view;
    }

    @Override // android.view.View
    public void draw(@NotNull Canvas canvas) {
        try {
            super.draw(canvas);
        } catch (Exception unused) {
        }
    }

    public final void initLiveLottie(@Nullable String lottieStr) {
        try {
            setImageAssetsFolder("assets/");
        } catch (Exception unused) {
        }
        if (TextUtils.isEmpty(this.mLottieStr)) {
            String assetsString = getAssetsString("lottie/" + lottieStr);
            this.mLottieStr = assetsString;
            if (isValid(assetsString)) {
                setLottieJson(this.mLottieStr, "MIAOSHA_LOTTIE");
            } else {
                setVisibility(8);
            }
            setRepeatCount(-1);
        }
    }

    @Override // com.airbnb.lottie.LottieAnimationView
    public void setComposition(@NotNull LottieComposition composition) {
        try {
            super.setComposition(composition);
        } catch (Exception unused) {
        }
    }

    public MiaoShaLottieView(@Nullable Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private final void close(Reader reader) {
        if (reader != null) {
            try {
                reader.close();
            } catch (Exception unused) {
            }
        }
    }

    public MiaoShaLottieView(@Nullable Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
