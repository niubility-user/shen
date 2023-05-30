package tv.danmaku.ijk.media.alpha;

import java.nio.charset.Charset;
import org.apache.commons.codec.CharEncoding;
import org.json.JSONObject;
import tv.danmaku.ijk.media.alpha.file.IFileContainer;
import tv.danmaku.ijk.media.alpha.player.AlphaPlayer;
import tv.danmaku.ijk.media.utils.DebugLog;

/* loaded from: classes11.dex */
public class AlphaConfigManager {
    private static final String TAG = "AlphaConfigManager";
    public AlphaConfig config;
    public boolean isParsingConfig;
    private AlphaPlayer player;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static class BoxHeader {
        public int length;
        public long startIndex;
        public String type;

        private BoxHeader() {
        }
    }

    public AlphaConfigManager(AlphaPlayer alphaPlayer) {
        this.player = alphaPlayer;
    }

    private boolean parse(IFileContainer iFileContainer, int i2) throws Exception {
        BoxHeader parseBoxHeader;
        if (iFileContainer == null) {
            return false;
        }
        this.config = new AlphaConfig();
        iFileContainer.startRandomRead();
        byte[] bArr = new byte[8];
        long j2 = 0;
        BoxHeader boxHeader = null;
        while (true) {
            if (iFileContainer.read(bArr, 0, 8) != 8 || (parseBoxHeader = parseBoxHeader(bArr)) == null) {
                break;
            }
            DebugLog.e(TAG, "box header ---- type=" + parseBoxHeader.type + " ,size=" + parseBoxHeader.length + " ,start=" + parseBoxHeader.startIndex);
            if ("vapc".equals(parseBoxHeader.type)) {
                parseBoxHeader.startIndex = j2;
                boxHeader = parseBoxHeader;
                break;
            }
            int i3 = parseBoxHeader.length;
            j2 += i3;
            iFileContainer.skip(i3 - 8);
        }
        if (boxHeader == null) {
            AlphaConfig alphaConfig = this.config;
            alphaConfig.isDefaultConfig = true;
            alphaConfig.fps = i2;
            return true;
        }
        int i4 = boxHeader.length - 8;
        byte[] bArr2 = new byte[i4];
        iFileContainer.read(bArr2, 0, i4);
        iFileContainer.closeRandomRead();
        JSONObject jSONObject = new JSONObject(new String(bArr2, 0, i4, Charset.forName(CharEncoding.US_ASCII)));
        AlphaConfig alphaConfig2 = this.config;
        alphaConfig2.jsonConfig = jSONObject;
        Boolean parse = alphaConfig2.parse(jSONObject);
        if (i2 > 0) {
            this.config.fps = i2;
        }
        this.player.setFps(this.config.fps);
        return parse.booleanValue();
    }

    private BoxHeader parseBoxHeader(byte[] bArr) {
        if (bArr.length != 8) {
            return null;
        }
        BoxHeader boxHeader = new BoxHeader();
        boxHeader.length = 0 | ((bArr[0] & 255) << 24) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8) | (bArr[3] & 255);
        boxHeader.type = new String(bArr, 4, 4, Charset.forName(CharEncoding.US_ASCII));
        return boxHeader;
    }

    public int parseConfig(IFileContainer iFileContainer, int i2) {
        try {
            this.isParsingConfig = true;
            if (!parse(iFileContainer, i2)) {
                this.isParsingConfig = false;
                return 10005;
            }
            AlphaConfig alphaConfig = this.config;
            if (alphaConfig == null) {
                return 0;
            }
            int onConfigCreate = this.player.pluginManager.onConfigCreate(alphaConfig);
            this.isParsingConfig = false;
            return onConfigCreate;
        } catch (Throwable th) {
            DebugLog.e(TAG, "parseConfig error: " + th.getMessage());
            this.isParsingConfig = false;
            return 10005;
        }
    }
}
