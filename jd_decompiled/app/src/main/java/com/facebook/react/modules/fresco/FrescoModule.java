package com.facebook.react.modules.fresco;

import android.content.Context;
import androidx.annotation.Nullable;
import com.facebook.common.logging.FLog;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.common.ModuleDataCleaner;
import com.facebook.react.modules.network.CookieJarContainer;
import com.facebook.react.modules.network.ForwardingCookieHandler;
import com.facebook.react.modules.network.OkHttpClientProvider;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;

@ReactModule(name = FrescoModule.NAME, needsEagerInit = true)
/* loaded from: classes12.dex */
public class FrescoModule extends ReactContextBaseJavaModule implements ModuleDataCleaner.Cleanable, LifecycleEventListener {
    public static final String NAME = "FrescoModule";
    private static boolean sHasBeenInitialized;
    private final boolean mClearOnDestroy;
    @Nullable
    private ImagePipelineConfig mConfig;

    public FrescoModule(ReactApplicationContext reactApplicationContext) {
        this(reactApplicationContext, true, null);
    }

    private static ImagePipelineConfig getDefaultConfig(ReactContext reactContext) {
        return getDefaultConfigBuilder(reactContext).build();
    }

    public static ImagePipelineConfig.Builder getDefaultConfigBuilder(ReactContext reactContext) {
        HashSet hashSet = new HashSet();
        hashSet.add(new SystraceRequestListener());
        OkHttpClient createClient = OkHttpClientProvider.createClient();
        ((CookieJarContainer) createClient.cookieJar()).setCookieJar(new JavaNetCookieJar(new ForwardingCookieHandler(reactContext)));
        return OkHttpImagePipelineConfigFactory.newBuilder(reactContext.getApplicationContext(), createClient).setNetworkFetcher(new ReactOkHttpNetworkFetcher(createClient)).setDownsampleEnabled(false).setRequestListeners(hashSet);
    }

    public static boolean hasBeenInitialized() {
        return sHasBeenInitialized;
    }

    @Override // com.facebook.react.modules.common.ModuleDataCleaner.Cleanable
    public void clearSensitiveData() {
        Fresco.getImagePipeline().clearCaches();
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void initialize() {
        super.initialize();
        getReactApplicationContext().addLifecycleEventListener(this);
        if (!hasBeenInitialized()) {
            if (this.mConfig == null) {
                this.mConfig = getDefaultConfig(getReactApplicationContext());
            }
            Context applicationContext = getReactApplicationContext().getApplicationContext();
            if (!Fresco.hasBeenInitialized()) {
                try {
                    FLog.e(JDReactConstant.ASSERT_DIR, "init method 3 ");
                    Fresco.class.getMethod("initialize", Context.class, ImagePipelineConfig.class, Boolean.TYPE).invoke(null, applicationContext, this.mConfig, Boolean.FALSE);
                } catch (IllegalAccessException unused) {
                    FLog.e(JDReactConstant.ASSERT_DIR, "IllegalAccessException 3 ");
                } catch (NoSuchMethodException unused2) {
                    FLog.e(JDReactConstant.ASSERT_DIR, "NoSuchMethodException 3 ");
                    try {
                        FLog.e(JDReactConstant.ASSERT_DIR, "init method 2 ");
                        Fresco.class.getMethod("initialize", Context.class, ImagePipelineConfig.class).invoke(null, applicationContext, this.mConfig);
                    } catch (IllegalAccessException unused3) {
                        FLog.e(JDReactConstant.ASSERT_DIR, "IllegalAccessException 2 ");
                    } catch (NoSuchMethodException unused4) {
                        FLog.e(JDReactConstant.ASSERT_DIR, "NoSuchMethodException 2 ");
                    } catch (InvocationTargetException unused5) {
                        FLog.e(JDReactConstant.ASSERT_DIR, "InvocationTargetException 2 ");
                    }
                } catch (InvocationTargetException unused6) {
                    FLog.e(JDReactConstant.ASSERT_DIR, "InvocationTargetException 3 ");
                }
            }
            sHasBeenInitialized = true;
        } else if (this.mConfig != null) {
            FLog.w(ReactConstants.TAG, "Fresco has already been initialized with a different config. The new Fresco configuration will be ignored!");
        }
        this.mConfig = null;
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        if (hasBeenInitialized() && this.mClearOnDestroy) {
            Fresco.getImagePipeline().clearMemoryCaches();
        }
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
    }

    public FrescoModule(ReactApplicationContext reactApplicationContext, boolean z) {
        this(reactApplicationContext, z, null);
    }

    public FrescoModule(ReactApplicationContext reactApplicationContext, boolean z, @Nullable ImagePipelineConfig imagePipelineConfig) {
        super(reactApplicationContext);
        this.mClearOnDestroy = z;
        this.mConfig = imagePipelineConfig;
    }
}
