package com.jingdong.app.mall.nfc.f;

import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

/* loaded from: classes4.dex */
public class b implements com.jingdong.app.mall.nfc.f.a {
    private static final byte[] b = {97, 99, 116};

    /* renamed from: c  reason: collision with root package name */
    private static final byte[] f11424c = {116};
    private final c a;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public enum a {
        UNKNOWN((byte) -1),
        DO_ACTION((byte) 0),
        SAVE_FOR_LATER((byte) 1),
        OPEN_FOR_EDITING((byte) 2);
        

        /* renamed from: g  reason: collision with root package name */
        private static final ImmutableMap<Byte, a> f11425g;
        private final byte mAction;

        static {
            ImmutableMap.Builder builder = ImmutableMap.builder();
            for (a aVar : values()) {
                builder.put(Byte.valueOf(aVar.b()), aVar);
            }
            f11425g = builder.build();
        }

        a(byte b) {
            this.mAction = b;
        }

        private byte b() {
            return this.mAction;
        }
    }

    private b(d dVar, c cVar, a aVar, String str) {
        d dVar2 = (d) Preconditions.checkNotNull(dVar);
        this.a = cVar;
        a aVar2 = (a) Preconditions.checkNotNull(aVar);
    }

    private static NdefRecord b(byte[] bArr, NdefRecord[] ndefRecordArr) {
        for (NdefRecord ndefRecord : ndefRecordArr) {
            if (Arrays.equals(bArr, ndefRecord.getType())) {
                return ndefRecord;
            }
        }
        return null;
    }

    private static <T> T c(Iterable<?> iterable, Class<T> cls) {
        Iterable filter = Iterables.filter(iterable, cls);
        if (Iterables.isEmpty(filter)) {
            return null;
        }
        return (T) Iterables.get(filter, 0);
    }

    public static boolean d(NdefRecord ndefRecord) {
        try {
            e(ndefRecord);
            return true;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    public static b e(NdefRecord ndefRecord) {
        Preconditions.checkArgument(ndefRecord.getTnf() == 1);
        Preconditions.checkArgument(Arrays.equals(ndefRecord.getType(), NdefRecord.RTD_SMART_POSTER));
        try {
            return f(new NdefMessage(ndefRecord.getPayload()).getRecords());
        } catch (FormatException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    public static b f(NdefRecord[] ndefRecordArr) {
        try {
            List<com.jingdong.app.mall.nfc.f.a> a2 = com.jingdong.app.mall.nfc.a.a(ndefRecordArr);
            return new b((d) Iterables.getOnlyElement(Iterables.filter(a2, d.class)), (c) c(a2, c.class), g(ndefRecordArr), h(ndefRecordArr));
        } catch (NoSuchElementException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    private static a g(NdefRecord[] ndefRecordArr) {
        NdefRecord b2 = b(b, ndefRecordArr);
        if (b2 == null) {
            return a.UNKNOWN;
        }
        byte b3 = b2.getPayload()[0];
        if (a.f11425g.containsKey(Byte.valueOf(b3))) {
            return (a) a.f11425g.get(Byte.valueOf(b3));
        }
        return a.UNKNOWN;
    }

    private static String h(NdefRecord[] ndefRecordArr) {
        NdefRecord b2 = b(f11424c, ndefRecordArr);
        if (b2 == null) {
            return null;
        }
        return new String(b2.getPayload(), Charsets.UTF_8);
    }

    @Override // com.jingdong.app.mall.nfc.f.a
    public String a() {
        c cVar = this.a;
        if (cVar != null) {
            return cVar.b();
        }
        return cVar.b();
    }
}
