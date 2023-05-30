package com.facebook.react.modules.blob;

import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.webkit.MimeTypeMap;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.network.NetworkingModule;
import com.facebook.react.modules.websocket.WebSocketModule;
import com.jingdong.common.apkcenter.ApkDownloadTable;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.ByteString;
import tv.danmaku.ijk.media.player.IMediaPlayer;

@ReactModule(name = BlobModule.NAME)
/* loaded from: classes12.dex */
public class BlobModule extends ReactContextBaseJavaModule {
    protected static final String NAME = "BlobModule";
    private final Map<String, byte[]> mBlobs;
    private final NetworkingModule.RequestBodyHandler mNetworkingRequestBodyHandler;
    private final NetworkingModule.ResponseHandler mNetworkingResponseHandler;
    private final NetworkingModule.UriHandler mNetworkingUriHandler;
    private final WebSocketModule.ContentHandler mWebSocketContentHandler;

    public BlobModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mBlobs = new HashMap();
        this.mWebSocketContentHandler = new WebSocketModule.ContentHandler() { // from class: com.facebook.react.modules.blob.BlobModule.1
            @Override // com.facebook.react.modules.websocket.WebSocketModule.ContentHandler
            public void onMessage(String str, WritableMap writableMap) {
                writableMap.putString("data", str);
            }

            @Override // com.facebook.react.modules.websocket.WebSocketModule.ContentHandler
            public void onMessage(ByteString byteString, WritableMap writableMap) {
                byte[] byteArray = byteString.toByteArray();
                WritableMap createMap = Arguments.createMap();
                createMap.putString("blobId", BlobModule.this.store(byteArray));
                createMap.putInt(IMediaPlayer.OnNativeInvokeListener.ARG_OFFSET, 0);
                createMap.putInt(ApkDownloadTable.FIELD_SIZE, byteArray.length);
                writableMap.putMap("data", createMap);
                writableMap.putString("type", "blob");
            }
        };
        this.mNetworkingUriHandler = new NetworkingModule.UriHandler() { // from class: com.facebook.react.modules.blob.BlobModule.2
            @Override // com.facebook.react.modules.network.NetworkingModule.UriHandler
            public WritableMap fetch(Uri uri) throws IOException {
                byte[] bytesFromUri = BlobModule.this.getBytesFromUri(uri);
                WritableMap createMap = Arguments.createMap();
                createMap.putString("blobId", BlobModule.this.store(bytesFromUri));
                createMap.putInt(IMediaPlayer.OnNativeInvokeListener.ARG_OFFSET, 0);
                createMap.putInt(ApkDownloadTable.FIELD_SIZE, bytesFromUri.length);
                createMap.putString("type", BlobModule.this.getMimeTypeFromUri(uri));
                createMap.putString("name", BlobModule.this.getNameFromUri(uri));
                createMap.putDouble("lastModified", BlobModule.this.getLastModifiedFromUri(uri));
                return createMap;
            }

            @Override // com.facebook.react.modules.network.NetworkingModule.UriHandler
            public boolean supports(Uri uri, String str) {
                String scheme = uri.getScheme();
                return !("http".equals(scheme) || "https".equals(scheme)) && "blob".equals(str);
            }
        };
        this.mNetworkingRequestBodyHandler = new NetworkingModule.RequestBodyHandler() { // from class: com.facebook.react.modules.blob.BlobModule.3
            @Override // com.facebook.react.modules.network.NetworkingModule.RequestBodyHandler
            public boolean supports(ReadableMap readableMap) {
                return readableMap.hasKey("blob");
            }

            @Override // com.facebook.react.modules.network.NetworkingModule.RequestBodyHandler
            public RequestBody toRequestBody(ReadableMap readableMap, String str) {
                if (readableMap.hasKey("type") && !readableMap.getString("type").isEmpty()) {
                    str = readableMap.getString("type");
                }
                if (str == null) {
                    str = "application/octet-stream";
                }
                ReadableMap map = readableMap.getMap("blob");
                return RequestBody.create(MediaType.parse(str), BlobModule.this.resolve(map.getString("blobId"), map.getInt(IMediaPlayer.OnNativeInvokeListener.ARG_OFFSET), map.getInt(ApkDownloadTable.FIELD_SIZE)));
            }
        };
        this.mNetworkingResponseHandler = new NetworkingModule.ResponseHandler() { // from class: com.facebook.react.modules.blob.BlobModule.4
            @Override // com.facebook.react.modules.network.NetworkingModule.ResponseHandler
            public boolean supports(String str) {
                return "blob".equals(str);
            }

            @Override // com.facebook.react.modules.network.NetworkingModule.ResponseHandler
            public WritableMap toResponseData(ResponseBody responseBody) throws IOException {
                byte[] bytes = responseBody.bytes();
                WritableMap createMap = Arguments.createMap();
                createMap.putString("blobId", BlobModule.this.store(bytes));
                createMap.putInt(IMediaPlayer.OnNativeInvokeListener.ARG_OFFSET, 0);
                createMap.putInt(ApkDownloadTable.FIELD_SIZE, bytes.length);
                return createMap;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] getBytesFromUri(Uri uri) throws IOException {
        InputStream openInputStream = getReactApplicationContext().getContentResolver().openInputStream(uri);
        if (openInputStream != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = openInputStream.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } else {
            throw new FileNotFoundException("File not found for " + uri);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long getLastModifiedFromUri(Uri uri) {
        if ("file".equals(uri.getScheme())) {
            return new File(uri.toString()).lastModified();
        }
        return 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getMimeTypeFromUri(Uri uri) {
        String fileExtensionFromUrl;
        String type = getReactApplicationContext().getContentResolver().getType(uri);
        if (type == null && (fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(uri.getPath())) != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl);
        }
        return type == null ? "" : type;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getNameFromUri(Uri uri) {
        if ("file".equals(uri.getScheme())) {
            return uri.getLastPathSegment();
        }
        Cursor query = getReactApplicationContext().getContentResolver().query(uri, new String[]{"_display_name"}, null, null, null);
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    return query.getString(0);
                }
            } finally {
                query.close();
            }
        }
        return uri.getLastPathSegment();
    }

    private WebSocketModule getWebSocketModule() {
        return (WebSocketModule) getReactApplicationContext().getNativeModule(WebSocketModule.class);
    }

    @ReactMethod
    public void addNetworkingHandler() {
        NetworkingModule networkingModule = (NetworkingModule) getReactApplicationContext().getNativeModule(NetworkingModule.class);
        networkingModule.addUriHandler(this.mNetworkingUriHandler);
        networkingModule.addRequestBodyHandler(this.mNetworkingRequestBodyHandler);
        networkingModule.addResponseHandler(this.mNetworkingResponseHandler);
    }

    @ReactMethod
    public void addWebSocketHandler(int i2) {
        getWebSocketModule().setContentHandler(i2, this.mWebSocketContentHandler);
    }

    @ReactMethod
    public void createFromParts(ReadableArray readableArray, String str) {
        ArrayList arrayList = new ArrayList(readableArray.size());
        int i2 = 0;
        for (int i3 = 0; i3 < readableArray.size(); i3++) {
            ReadableMap map = readableArray.getMap(i3);
            String string = map.getString("type");
            string.hashCode();
            if (string.equals("string")) {
                byte[] bytes = map.getString("data").getBytes(Charset.forName("UTF-8"));
                i2 += bytes.length;
                arrayList.add(i3, bytes);
            } else if (!string.equals("blob")) {
                throw new IllegalArgumentException("Invalid type for blob: " + map.getString("type"));
            } else {
                ReadableMap map2 = map.getMap("data");
                i2 += map2.getInt(ApkDownloadTable.FIELD_SIZE);
                arrayList.add(i3, resolve(map2));
            }
        }
        ByteBuffer allocate = ByteBuffer.allocate(i2);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            allocate.put((byte[]) it.next());
        }
        store(allocate.array(), str);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    @Nullable
    public Map<String, Object> getConstants() {
        Resources resources = getReactApplicationContext().getResources();
        int identifier = resources.getIdentifier("blob_provider_authority", "string", getReactApplicationContext().getPackageName());
        if (identifier == 0) {
            return null;
        }
        return MapBuilder.of("BLOB_URI_SCHEME", "content", "BLOB_URI_HOST", resources.getString(identifier));
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public void release(String str) {
        remove(str);
    }

    public void remove(String str) {
        this.mBlobs.remove(str);
    }

    @ReactMethod
    public void removeWebSocketHandler(int i2) {
        getWebSocketModule().setContentHandler(i2, null);
    }

    @Nullable
    public byte[] resolve(Uri uri) {
        String lastPathSegment = uri.getLastPathSegment();
        String queryParameter = uri.getQueryParameter(IMediaPlayer.OnNativeInvokeListener.ARG_OFFSET);
        int parseInt = queryParameter != null ? Integer.parseInt(queryParameter, 10) : 0;
        String queryParameter2 = uri.getQueryParameter(ApkDownloadTable.FIELD_SIZE);
        return resolve(lastPathSegment, parseInt, queryParameter2 != null ? Integer.parseInt(queryParameter2, 10) : -1);
    }

    @ReactMethod
    public void sendOverSocket(ReadableMap readableMap, int i2) {
        byte[] resolve = resolve(readableMap.getString("blobId"), readableMap.getInt(IMediaPlayer.OnNativeInvokeListener.ARG_OFFSET), readableMap.getInt(ApkDownloadTable.FIELD_SIZE));
        if (resolve != null) {
            getWebSocketModule().sendBinary(ByteString.of(resolve), i2);
        } else {
            getWebSocketModule().sendBinary((ByteString) null, i2);
        }
    }

    public String store(byte[] bArr) {
        String uuid = UUID.randomUUID().toString();
        store(bArr, uuid);
        return uuid;
    }

    public void store(byte[] bArr, String str) {
        this.mBlobs.put(str, bArr);
    }

    @Nullable
    public byte[] resolve(String str, int i2, int i3) {
        byte[] bArr = this.mBlobs.get(str);
        if (bArr == null) {
            return null;
        }
        if (i3 == -1) {
            i3 = bArr.length - i2;
        }
        return (i2 > 0 || i3 != bArr.length) ? Arrays.copyOfRange(bArr, i2, i3 + i2) : bArr;
    }

    @Nullable
    public byte[] resolve(ReadableMap readableMap) {
        return resolve(readableMap.getString("blobId"), readableMap.getInt(IMediaPlayer.OnNativeInvokeListener.ARG_OFFSET), readableMap.getInt(ApkDownloadTable.FIELD_SIZE));
    }
}
