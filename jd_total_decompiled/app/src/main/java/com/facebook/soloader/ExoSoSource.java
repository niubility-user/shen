package com.facebook.soloader;

import android.content.Context;
import com.facebook.soloader.UnpackingSoSource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;

/* loaded from: classes12.dex */
public final class ExoSoSource extends UnpackingSoSource {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public final class ExoUnpacker extends UnpackingSoSource.Unpacker {
        private final FileDso[] mDsos;

        /* loaded from: classes12.dex */
        private final class FileBackedInputDsoIterator extends UnpackingSoSource.InputDsoIterator {
            private int mCurrentDso;

            private FileBackedInputDsoIterator() {
            }

            @Override // com.facebook.soloader.UnpackingSoSource.InputDsoIterator
            public boolean hasNext() {
                return this.mCurrentDso < ExoUnpacker.this.mDsos.length;
            }

            @Override // com.facebook.soloader.UnpackingSoSource.InputDsoIterator
            public UnpackingSoSource.InputDso next() throws IOException {
                FileDso[] fileDsoArr = ExoUnpacker.this.mDsos;
                int i2 = this.mCurrentDso;
                this.mCurrentDso = i2 + 1;
                FileDso fileDso = fileDsoArr[i2];
                FileInputStream fileInputStream = new FileInputStream(fileDso.backingFile);
                try {
                    return new UnpackingSoSource.InputDso(fileDso, fileInputStream);
                } catch (Throwable th) {
                    fileInputStream.close();
                    throw th;
                }
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:30:0x00e4, code lost:
            throw new java.lang.RuntimeException("illegal line in exopackage metadata: [" + r11 + "]");
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        ExoUnpacker(UnpackingSoSource unpackingSoSource) throws IOException {
            boolean z;
            File file = new File("/data/local/tmp/exopackage/" + ExoSoSource.this.mContext.getPackageName() + "/native-libs/");
            ArrayList arrayList = new ArrayList();
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            String[] supportedAbis = SysUtil.getSupportedAbis();
            int length = supportedAbis.length;
            int i2 = 0;
            int i3 = 0;
            loop0: while (i3 < length) {
                String str = supportedAbis[i3];
                File file2 = new File(file, str);
                if (file2.isDirectory()) {
                    linkedHashSet.add(str);
                    File file3 = new File(file2, "metadata.txt");
                    if (file3.isFile()) {
                        FileReader fileReader = new FileReader(file3);
                        try {
                            BufferedReader bufferedReader = new BufferedReader(fileReader);
                            while (true) {
                                String readLine = bufferedReader.readLine();
                                if (readLine != null) {
                                    if (readLine.length() != 0) {
                                        int indexOf = readLine.indexOf(32);
                                        if (indexOf == -1) {
                                            break loop0;
                                        }
                                        String str2 = readLine.substring(i2, indexOf) + ".so";
                                        int size = arrayList.size();
                                        int i4 = 0;
                                        while (true) {
                                            if (i4 >= size) {
                                                z = false;
                                                break;
                                            } else if (((FileDso) arrayList.get(i4)).name.equals(str2)) {
                                                z = true;
                                                break;
                                            } else {
                                                i4++;
                                            }
                                        }
                                        if (!z) {
                                            String substring = readLine.substring(indexOf + 1);
                                            arrayList.add(new FileDso(str2, substring, new File(file2, substring)));
                                        }
                                        i2 = 0;
                                    }
                                } else {
                                    bufferedReader.close();
                                    fileReader.close();
                                    break;
                                }
                            }
                        } finally {
                        }
                    } else {
                        continue;
                    }
                }
                i3++;
                i2 = 0;
            }
            unpackingSoSource.setSoSourceAbis((String[]) linkedHashSet.toArray(new String[linkedHashSet.size()]));
            this.mDsos = (FileDso[]) arrayList.toArray(new FileDso[arrayList.size()]);
        }

        @Override // com.facebook.soloader.UnpackingSoSource.Unpacker
        protected UnpackingSoSource.DsoManifest getDsoManifest() throws IOException {
            return new UnpackingSoSource.DsoManifest(this.mDsos);
        }

        @Override // com.facebook.soloader.UnpackingSoSource.Unpacker
        protected UnpackingSoSource.InputDsoIterator openDsoIterator() throws IOException {
            return new FileBackedInputDsoIterator();
        }
    }

    /* loaded from: classes12.dex */
    private static final class FileDso extends UnpackingSoSource.Dso {
        final File backingFile;

        FileDso(String str, String str2, File file) {
            super(str, str2);
            this.backingFile = file;
        }
    }

    public ExoSoSource(Context context, String str) {
        super(context, str);
    }

    @Override // com.facebook.soloader.UnpackingSoSource
    protected UnpackingSoSource.Unpacker makeUnpacker() throws IOException {
        return new ExoUnpacker(this);
    }
}
