package com.facebook.react.devsupport;

import android.util.JsonReader;
import android.util.Pair;
import com.facebook.react.bridge.NativeDeltaClient;
import com.jd.dynamic.base.DynamicPrepareFetcher;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedHashMap;
import javax.annotation.Nullable;
import okhttp3.Headers;
import okio.BufferedSource;

/* loaded from: classes12.dex */
public abstract class BundleDeltaClient {
    private static final String METRO_DELTA_ID_HEADER = "X-Metro-Delta-ID";
    @Nullable
    private String mRevisionId;

    /* renamed from: com.facebook.react.devsupport.BundleDeltaClient$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$devsupport$BundleDeltaClient$ClientType;

        static {
            int[] iArr = new int[ClientType.values().length];
            $SwitchMap$com$facebook$react$devsupport$BundleDeltaClient$ClientType = iArr;
            try {
                iArr[ClientType.DEV_SUPPORT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$react$devsupport$BundleDeltaClient$ClientType[ClientType.NATIVE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* loaded from: classes12.dex */
    public enum ClientType {
        NONE,
        DEV_SUPPORT,
        NATIVE
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static BundleDeltaClient create(ClientType clientType) {
        int i2 = AnonymousClass1.$SwitchMap$com$facebook$react$devsupport$BundleDeltaClient$ClientType[clientType.ordinal()];
        AnonymousClass1 anonymousClass1 = null;
        if (i2 != 1) {
            if (i2 != 2) {
                return null;
            }
            return new BundleDeltaNativeClient(anonymousClass1);
        }
        return new BundleDeltaJavaClient(anonymousClass1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isDeltaUrl(String str) {
        return str.indexOf(".delta?") != -1;
    }

    public abstract boolean canHandle(ClientType clientType);

    public final synchronized String extendUrlForDelta(String str) {
        if (this.mRevisionId != null) {
            str = str + "&revisionId=" + this.mRevisionId;
        }
        return str;
    }

    public synchronized Pair<Boolean, NativeDeltaClient> processDelta(Headers headers, BufferedSource bufferedSource, File file) throws IOException {
        this.mRevisionId = headers.get(METRO_DELTA_ID_HEADER);
        return processDelta(bufferedSource, file);
    }

    protected abstract Pair<Boolean, NativeDeltaClient> processDelta(BufferedSource bufferedSource, File file) throws IOException;

    public synchronized void reset() {
        this.mRevisionId = null;
    }

    /* loaded from: classes12.dex */
    private static class BundleDeltaJavaClient extends BundleDeltaClient {
        final LinkedHashMap<Number, byte[]> mModules;
        byte[] mPostCode;
        byte[] mPreCode;

        private BundleDeltaJavaClient() {
            this.mModules = new LinkedHashMap<>();
        }

        private static int removeModules(JsonReader jsonReader, LinkedHashMap<Number, byte[]> linkedHashMap) throws IOException {
            jsonReader.beginArray();
            int i2 = 0;
            while (jsonReader.hasNext()) {
                linkedHashMap.remove(Integer.valueOf(jsonReader.nextInt()));
                i2++;
            }
            jsonReader.endArray();
            return i2;
        }

        private static int setModules(JsonReader jsonReader, LinkedHashMap<Number, byte[]> linkedHashMap) throws IOException {
            jsonReader.beginArray();
            int i2 = 0;
            while (jsonReader.hasNext()) {
                jsonReader.beginArray();
                linkedHashMap.put(Integer.valueOf(jsonReader.nextInt()), jsonReader.nextString().getBytes());
                jsonReader.endArray();
                i2++;
            }
            jsonReader.endArray();
            return i2;
        }

        @Override // com.facebook.react.devsupport.BundleDeltaClient
        public boolean canHandle(ClientType clientType) {
            return clientType == ClientType.DEV_SUPPORT;
        }

        @Override // com.facebook.react.devsupport.BundleDeltaClient
        public synchronized Pair<Boolean, NativeDeltaClient> processDelta(BufferedSource bufferedSource, File file) throws IOException {
            int modules;
            JsonReader jsonReader = new JsonReader(new InputStreamReader(bufferedSource.inputStream()));
            jsonReader.beginObject();
            int i2 = 0;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (nextName.equals("pre")) {
                    this.mPreCode = jsonReader.nextString().getBytes();
                } else if (nextName.equals(IMantoServerRequester.POST)) {
                    this.mPostCode = jsonReader.nextString().getBytes();
                } else {
                    if (nextName.equals(DynamicPrepareFetcher.KEY_PREPARE_MODULES)) {
                        modules = setModules(jsonReader, this.mModules);
                    } else if (nextName.equals("added")) {
                        modules = setModules(jsonReader, this.mModules);
                    } else if (nextName.equals("modified")) {
                        modules = setModules(jsonReader, this.mModules);
                    } else if (nextName.equals("deleted")) {
                        modules = removeModules(jsonReader, this.mModules);
                    } else {
                        jsonReader.skipValue();
                    }
                    i2 += modules;
                }
            }
            jsonReader.endObject();
            jsonReader.close();
            if (i2 == 0) {
                return Pair.create(Boolean.FALSE, null);
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(this.mPreCode);
            fileOutputStream.write(10);
            Iterator<byte[]> it = this.mModules.values().iterator();
            while (it.hasNext()) {
                fileOutputStream.write(it.next());
                fileOutputStream.write(10);
            }
            fileOutputStream.write(this.mPostCode);
            fileOutputStream.write(10);
            fileOutputStream.flush();
            fileOutputStream.close();
            return Pair.create(Boolean.TRUE, null);
        }

        @Override // com.facebook.react.devsupport.BundleDeltaClient
        public synchronized void reset() {
            super.reset();
            this.mPreCode = null;
            this.mPostCode = null;
            this.mModules.clear();
        }

        /* synthetic */ BundleDeltaJavaClient(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    /* loaded from: classes12.dex */
    private static class BundleDeltaNativeClient extends BundleDeltaClient {
        private final NativeDeltaClient nativeClient;

        private BundleDeltaNativeClient() {
            this.nativeClient = new NativeDeltaClient();
        }

        @Override // com.facebook.react.devsupport.BundleDeltaClient
        public boolean canHandle(ClientType clientType) {
            return clientType == ClientType.NATIVE;
        }

        @Override // com.facebook.react.devsupport.BundleDeltaClient
        protected Pair<Boolean, NativeDeltaClient> processDelta(BufferedSource bufferedSource, File file) throws IOException {
            this.nativeClient.processDelta(bufferedSource);
            return Pair.create(Boolean.FALSE, this.nativeClient);
        }

        @Override // com.facebook.react.devsupport.BundleDeltaClient
        public void reset() {
            super.reset();
            this.nativeClient.reset();
        }

        /* synthetic */ BundleDeltaNativeClient(AnonymousClass1 anonymousClass1) {
            this();
        }
    }
}
