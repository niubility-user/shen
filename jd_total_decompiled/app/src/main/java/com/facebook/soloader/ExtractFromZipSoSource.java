package com.facebook.soloader;

import android.content.Context;
import com.facebook.soloader.UnpackingSoSource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public class ExtractFromZipSoSource extends UnpackingSoSource {
    protected final File mZipFileName;
    protected final String mZipSearchPattern;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static final class ZipDso extends UnpackingSoSource.Dso implements Comparable {
        final int abiScore;
        final ZipEntry backingEntry;

        ZipDso(String str, ZipEntry zipEntry, int i2) {
            super(str, makePseudoHash(zipEntry));
            this.backingEntry = zipEntry;
            this.abiScore = i2;
        }

        private static String makePseudoHash(ZipEntry zipEntry) {
            return String.format("pseudo-zip-hash-1-%s-%s-%s-%s", zipEntry.getName(), Long.valueOf(zipEntry.getSize()), Long.valueOf(zipEntry.getCompressedSize()), Long.valueOf(zipEntry.getCrc()));
        }

        @Override // java.lang.Comparable
        public int compareTo(Object obj) {
            return this.name.compareTo(((ZipDso) obj).name);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes12.dex */
    public class ZipUnpacker extends UnpackingSoSource.Unpacker {
        @Nullable
        private ZipDso[] mDsos;
        private final UnpackingSoSource mSoSource;
        private final ZipFile mZipFile;

        /* loaded from: classes12.dex */
        private final class ZipBackedInputDsoIterator extends UnpackingSoSource.InputDsoIterator {
            private int mCurrentDso;

            private ZipBackedInputDsoIterator() {
            }

            @Override // com.facebook.soloader.UnpackingSoSource.InputDsoIterator
            public boolean hasNext() {
                ZipUnpacker.this.ensureDsos();
                return this.mCurrentDso < ZipUnpacker.this.mDsos.length;
            }

            @Override // com.facebook.soloader.UnpackingSoSource.InputDsoIterator
            public UnpackingSoSource.InputDso next() throws IOException {
                ZipUnpacker.this.ensureDsos();
                ZipDso[] zipDsoArr = ZipUnpacker.this.mDsos;
                int i2 = this.mCurrentDso;
                this.mCurrentDso = i2 + 1;
                ZipDso zipDso = zipDsoArr[i2];
                InputStream inputStream = ZipUnpacker.this.mZipFile.getInputStream(zipDso.backingEntry);
                try {
                    return new UnpackingSoSource.InputDso(zipDso, inputStream);
                } catch (Throwable th) {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    throw th;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public ZipUnpacker(UnpackingSoSource unpackingSoSource) throws IOException {
            this.mZipFile = new ZipFile(ExtractFromZipSoSource.this.mZipFileName);
            this.mSoSource = unpackingSoSource;
        }

        @Override // com.facebook.soloader.UnpackingSoSource.Unpacker, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.mZipFile.close();
        }

        final ZipDso[] ensureDsos() {
            if (this.mDsos == null) {
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                HashMap hashMap = new HashMap();
                Pattern compile = Pattern.compile(ExtractFromZipSoSource.this.mZipSearchPattern);
                String[] supportedAbis = SysUtil.getSupportedAbis();
                Enumeration<? extends ZipEntry> entries = this.mZipFile.entries();
                while (entries.hasMoreElements()) {
                    ZipEntry nextElement = entries.nextElement();
                    Matcher matcher = compile.matcher(nextElement.getName());
                    if (matcher.matches()) {
                        String group = matcher.group(1);
                        String group2 = matcher.group(2);
                        int findAbiScore = SysUtil.findAbiScore(supportedAbis, group);
                        if (findAbiScore >= 0) {
                            linkedHashSet.add(group);
                            ZipDso zipDso = (ZipDso) hashMap.get(group2);
                            if (zipDso == null || findAbiScore < zipDso.abiScore) {
                                hashMap.put(group2, new ZipDso(group2, nextElement, findAbiScore));
                            }
                        }
                    }
                }
                this.mSoSource.setSoSourceAbis((String[]) linkedHashSet.toArray(new String[linkedHashSet.size()]));
                ZipDso[] zipDsoArr = (ZipDso[]) hashMap.values().toArray(new ZipDso[hashMap.size()]);
                Arrays.sort(zipDsoArr);
                int i2 = 0;
                for (int i3 = 0; i3 < zipDsoArr.length; i3++) {
                    ZipDso zipDso2 = zipDsoArr[i3];
                    if (shouldExtract(zipDso2.backingEntry, zipDso2.name)) {
                        i2++;
                    } else {
                        zipDsoArr[i3] = null;
                    }
                }
                ZipDso[] zipDsoArr2 = new ZipDso[i2];
                int i4 = 0;
                for (ZipDso zipDso3 : zipDsoArr) {
                    if (zipDso3 != null) {
                        zipDsoArr2[i4] = zipDso3;
                        i4++;
                    }
                }
                this.mDsos = zipDsoArr2;
            }
            return this.mDsos;
        }

        @Override // com.facebook.soloader.UnpackingSoSource.Unpacker
        protected final UnpackingSoSource.DsoManifest getDsoManifest() throws IOException {
            return new UnpackingSoSource.DsoManifest(ensureDsos());
        }

        @Override // com.facebook.soloader.UnpackingSoSource.Unpacker
        protected final UnpackingSoSource.InputDsoIterator openDsoIterator() throws IOException {
            return new ZipBackedInputDsoIterator();
        }

        protected boolean shouldExtract(ZipEntry zipEntry, String str) {
            return true;
        }
    }

    public ExtractFromZipSoSource(Context context, String str, File file, String str2) {
        super(context, str);
        this.mZipFileName = file;
        this.mZipSearchPattern = str2;
    }

    @Override // com.facebook.soloader.UnpackingSoSource
    protected UnpackingSoSource.Unpacker makeUnpacker() throws IOException {
        return new ZipUnpacker(this);
    }
}
