package com.jingdong.app.mall.nfc.f;

import android.net.Uri;
import android.nfc.NdefRecord;
import com.google.common.base.Preconditions;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.primitives.Bytes;
import com.tencent.smtt.sdk.WebView;
import java.nio.charset.Charset;
import java.util.Arrays;
import jd.wjlogin_sdk.util.ReplyCode;

/* loaded from: classes4.dex */
public class d implements a {
    private static final BiMap<Byte, String> b = ImmutableBiMap.builder().put((ImmutableBiMap.Builder) (byte) 0, (byte) "").put((ImmutableBiMap.Builder) (byte) 1, (byte) "http://www.").put((ImmutableBiMap.Builder) (byte) 2, (byte) "https://www.").put((ImmutableBiMap.Builder) (byte) 3, (byte) "http://").put((ImmutableBiMap.Builder) (byte) 4, (byte) "https://").put((ImmutableBiMap.Builder) (byte) 5, (byte) WebView.SCHEME_TEL).put((ImmutableBiMap.Builder) (byte) 6, (byte) WebView.SCHEME_MAILTO).put((ImmutableBiMap.Builder) (byte) 7, (byte) "ftp://anonymous:anonymous@").put((ImmutableBiMap.Builder) (byte) 8, (byte) "ftp://ftp.").put((ImmutableBiMap.Builder) (byte) 9, (byte) "ftps://").put((ImmutableBiMap.Builder) (byte) 10, (byte) "sftp://").put((ImmutableBiMap.Builder) (byte) 11, (byte) "smb://").put((ImmutableBiMap.Builder) (byte) 12, (byte) "nfs://").put((ImmutableBiMap.Builder) (byte) 13, (byte) "ftp://").put((ImmutableBiMap.Builder) (byte) 14, (byte) "dav://").put((ImmutableBiMap.Builder) (byte) 15, (byte) "news:").put((ImmutableBiMap.Builder) (byte) 16, (byte) "telnet://").put((ImmutableBiMap.Builder) (byte) 17, (byte) "imap:").put((ImmutableBiMap.Builder) (byte) 18, (byte) "rtsp://").put((ImmutableBiMap.Builder) (byte) 19, (byte) "urn:").put((ImmutableBiMap.Builder) (byte) 20, (byte) "pop:").put((ImmutableBiMap.Builder) (byte) 21, (byte) "sip:").put((ImmutableBiMap.Builder) (byte) 22, (byte) "sips:").put((ImmutableBiMap.Builder) (byte) 23, (byte) "tftp:").put((ImmutableBiMap.Builder) (byte) 24, (byte) "btspp://").put((ImmutableBiMap.Builder) (byte) 25, (byte) "btl2cap://").put((ImmutableBiMap.Builder) (byte) 26, (byte) "btgoep://").put((ImmutableBiMap.Builder) (byte) 27, (byte) "tcpobex://").put((ImmutableBiMap.Builder) (byte) 28, (byte) "irdaobex://").put((ImmutableBiMap.Builder) (byte) 29, (byte) "file://").put((ImmutableBiMap.Builder) (byte) 30, (byte) "urn:epc:id:").put((ImmutableBiMap.Builder) (byte) 31, (byte) "urn:epc:tag:").put((ImmutableBiMap.Builder) (byte) 32, (byte) "urn:epc:pat:").put((ImmutableBiMap.Builder) Byte.valueOf((byte) ReplyCode.reply0x21), (Byte) "urn:epc:raw:").put((ImmutableBiMap.Builder) Byte.valueOf((byte) ReplyCode.reply0x22), (Byte) "urn:epc:").put((ImmutableBiMap.Builder) Byte.valueOf((byte) ReplyCode.reply0x23), (Byte) "urn:nfc:").build();
    private final Uri a;

    private d(Uri uri) {
        this.a = (Uri) Preconditions.checkNotNull(uri);
    }

    public static boolean b(NdefRecord ndefRecord) {
        try {
            c(ndefRecord);
            return true;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    public static d c(NdefRecord ndefRecord) {
        short tnf = ndefRecord.getTnf();
        if (tnf == 1) {
            return e(ndefRecord);
        }
        if (tnf == 3) {
            return d(ndefRecord);
        }
        throw new IllegalArgumentException("Unknown TNF " + ((int) tnf));
    }

    private static d d(NdefRecord ndefRecord) {
        return new d(Uri.parse(new String(ndefRecord.getPayload(), Charset.forName("UTF-8"))));
    }

    private static d e(NdefRecord ndefRecord) {
        Preconditions.checkArgument(Arrays.equals(ndefRecord.getType(), NdefRecord.RTD_URI));
        byte[] payload = ndefRecord.getPayload();
        return new d(Uri.parse(new String(Bytes.concat(b.get(Byte.valueOf(payload[0])).getBytes(Charset.forName("UTF-8")), Arrays.copyOfRange(payload, 1, payload.length)), Charset.forName("UTF-8"))));
    }

    @Override // com.jingdong.app.mall.nfc.f.a
    public String a() {
        return this.a.toString();
    }
}
