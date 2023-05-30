package okio;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes11.dex */
public final class HashingSource extends ForwardingSource {
    private final Mac mac;
    private final MessageDigest messageDigest;

    private HashingSource(Source source, String str) {
        super(source);
        try {
            this.messageDigest = MessageDigest.getInstance(str);
            this.mac = null;
        } catch (NoSuchAlgorithmException unused) {
            throw new AssertionError();
        }
    }

    public static HashingSource hmacSha1(Source source, ByteString byteString) {
        return new HashingSource(source, byteString, "HmacSHA1");
    }

    public static HashingSource hmacSha256(Source source, ByteString byteString) {
        return new HashingSource(source, byteString, "HmacSHA256");
    }

    public static HashingSource md5(Source source) {
        return new HashingSource(source, MessageDigestAlgorithms.MD5);
    }

    public static HashingSource sha1(Source source) {
        return new HashingSource(source, MessageDigestAlgorithms.SHA_1);
    }

    public static HashingSource sha256(Source source) {
        return new HashingSource(source, MessageDigestAlgorithms.SHA_256);
    }

    public final ByteString hash() {
        MessageDigest messageDigest = this.messageDigest;
        return ByteString.of(messageDigest != null ? messageDigest.digest() : this.mac.doFinal());
    }

    @Override // okio.ForwardingSource, okio.Source
    public long read(Buffer buffer, long j2) throws IOException {
        long read = super.read(buffer, j2);
        if (read != -1) {
            long j3 = buffer.size;
            long j4 = j3 - read;
            Segment segment = buffer.head;
            while (j3 > j4) {
                segment = segment.prev;
                j3 -= segment.limit - segment.pos;
            }
            while (j3 < buffer.size) {
                int i2 = (int) ((segment.pos + j4) - j3);
                MessageDigest messageDigest = this.messageDigest;
                if (messageDigest != null) {
                    messageDigest.update(segment.data, i2, segment.limit - i2);
                } else {
                    this.mac.update(segment.data, i2, segment.limit - i2);
                }
                j4 = (segment.limit - segment.pos) + j3;
                segment = segment.next;
                j3 = j4;
            }
        }
        return read;
    }

    private HashingSource(Source source, ByteString byteString, String str) {
        super(source);
        try {
            Mac mac = Mac.getInstance(str);
            this.mac = mac;
            mac.init(new SecretKeySpec(byteString.toByteArray(), str));
            this.messageDigest = null;
        } catch (InvalidKeyException e2) {
            throw new IllegalArgumentException(e2);
        } catch (NoSuchAlgorithmException unused) {
            throw new AssertionError();
        }
    }
}
