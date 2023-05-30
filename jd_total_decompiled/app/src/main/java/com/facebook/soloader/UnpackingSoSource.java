package com.facebook.soloader;

import android.content.Context;
import android.os.Parcel;
import android.os.StrictMode;
import java.io.Closeable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public abstract class UnpackingSoSource extends DirectorySoSource {
    private static final String DEPS_FILE_NAME = "dso_deps";
    private static final String LOCK_FILE_NAME = "dso_lock";
    private static final String MANIFEST_FILE_NAME = "dso_manifest";
    private static final byte MANIFEST_VERSION = 1;
    private static final byte STATE_CLEAN = 1;
    private static final byte STATE_DIRTY = 0;
    private static final String STATE_FILE_NAME = "dso_state";
    private static final String TAG = "fb-UnpackingSoSource";
    @Nullable
    private String[] mAbis;
    protected final Context mContext;
    @Nullable
    protected String mCorruptedLib;
    private final Map<String, Object> mLibsBeingLoaded;

    /* loaded from: classes12.dex */
    public static class Dso {
        public final String hash;
        public final String name;

        public Dso(String str, String str2) {
            this.name = str;
            this.hash = str2;
        }
    }

    /* loaded from: classes12.dex */
    public static final class DsoManifest {
        public final Dso[] dsos;

        public DsoManifest(Dso[] dsoArr) {
            this.dsos = dsoArr;
        }

        static final DsoManifest read(DataInput dataInput) throws IOException {
            if (dataInput.readByte() == 1) {
                int readInt = dataInput.readInt();
                if (readInt >= 0) {
                    Dso[] dsoArr = new Dso[readInt];
                    for (int i2 = 0; i2 < readInt; i2++) {
                        dsoArr[i2] = new Dso(dataInput.readUTF(), dataInput.readUTF());
                    }
                    return new DsoManifest(dsoArr);
                }
                throw new RuntimeException("illegal number of shared libraries");
            }
            throw new RuntimeException("wrong dso manifest version");
        }

        public final void write(DataOutput dataOutput) throws IOException {
            dataOutput.writeByte(1);
            dataOutput.writeInt(this.dsos.length);
            int i2 = 0;
            while (true) {
                Dso[] dsoArr = this.dsos;
                if (i2 >= dsoArr.length) {
                    return;
                }
                dataOutput.writeUTF(dsoArr[i2].name);
                dataOutput.writeUTF(this.dsos[i2].hash);
                i2++;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes12.dex */
    public static final class InputDso implements Closeable {
        public final InputStream content;
        public final Dso dso;

        public InputDso(Dso dso, InputStream inputStream) {
            this.dso = dso;
            this.content = inputStream;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.content.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes12.dex */
    public static abstract class InputDsoIterator implements Closeable {
        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }

        public abstract boolean hasNext();

        public abstract InputDso next() throws IOException;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes12.dex */
    public static abstract class Unpacker implements Closeable {
        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }

        protected abstract DsoManifest getDsoManifest() throws IOException;

        protected abstract InputDsoIterator openDsoIterator() throws IOException;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public UnpackingSoSource(Context context, String str) {
        super(getSoStorePath(context, str), 1);
        this.mLibsBeingLoaded = new HashMap();
        this.mContext = context;
    }

    private void deleteUnmentionedFiles(Dso[] dsoArr) throws IOException {
        String[] list = this.soDirectory.list();
        if (list != null) {
            for (String str : list) {
                if (!str.equals(STATE_FILE_NAME) && !str.equals(LOCK_FILE_NAME) && !str.equals(DEPS_FILE_NAME) && !str.equals(MANIFEST_FILE_NAME)) {
                    boolean z = false;
                    for (int i2 = 0; !z && i2 < dsoArr.length; i2++) {
                        if (dsoArr[i2].name.equals(str)) {
                            z = true;
                        }
                    }
                    if (!z) {
                        File file = new File(this.soDirectory, str);
                        String str2 = "deleting unaccounted-for file " + file;
                        SysUtil.dumbDeleteRecursive(file);
                    }
                }
            }
            return;
        }
        throw new IOException("unable to list directory " + this.soDirectory);
    }

    private void extractDso(InputDso inputDso, byte[] bArr) throws IOException {
        RandomAccessFile randomAccessFile;
        String str = "extracting DSO " + inputDso.dso.name;
        if (this.soDirectory.setWritable(true, true)) {
            File file = new File(this.soDirectory, inputDso.dso.name);
            try {
                randomAccessFile = new RandomAccessFile(file, "rw");
            } catch (IOException unused) {
                String str2 = "error overwriting " + file + " trying to delete and start over";
                SysUtil.dumbDeleteRecursive(file);
                randomAccessFile = new RandomAccessFile(file, "rw");
            }
            try {
                try {
                    int available = inputDso.content.available();
                    if (available > 1) {
                        SysUtil.fallocateIfSupported(randomAccessFile.getFD(), available);
                    }
                    SysUtil.copyBytes(randomAccessFile, inputDso.content, Integer.MAX_VALUE, bArr);
                    randomAccessFile.setLength(randomAccessFile.getFilePointer());
                    if (file.setExecutable(true, false)) {
                        return;
                    }
                    throw new IOException("cannot make file executable: " + file);
                } finally {
                    randomAccessFile.close();
                }
            } catch (IOException e2) {
                SysUtil.dumbDeleteRecursive(file);
                throw e2;
            }
        }
        throw new IOException("cannot make directory writable for us: " + this.soDirectory);
    }

    private Object getLibraryLock(String str) {
        Object obj;
        synchronized (this.mLibsBeingLoaded) {
            obj = this.mLibsBeingLoaded.get(str);
            if (obj == null) {
                obj = new Object();
                this.mLibsBeingLoaded.put(str, obj);
            }
        }
        return obj;
    }

    public static File getSoStorePath(Context context, String str) {
        return new File(context.getApplicationInfo().dataDir + "/" + str);
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x008a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x008b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private boolean refreshLocked(final FileLocker fileLocker, int i2, final byte[] bArr) throws IOException {
        byte b;
        Unpacker makeUnpacker;
        DsoManifest dsoManifest;
        InputDsoIterator openDsoIterator;
        DsoManifest dsoManifest2;
        final File file = new File(this.soDirectory, STATE_FILE_NAME);
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        try {
            b = randomAccessFile.readByte();
        } catch (EOFException unused) {
        } catch (Throwable th) {
        }
        if (b != 1) {
            String str = "dso store " + this.soDirectory + " regeneration interrupted: wiping clean";
            b = 0;
        }
        randomAccessFile.close();
        final File file2 = new File(this.soDirectory, DEPS_FILE_NAME);
        randomAccessFile = new RandomAccessFile(file2, "rw");
        try {
            int length = (int) randomAccessFile.length();
            byte[] bArr2 = new byte[length];
            if (randomAccessFile.read(bArr2) != length) {
                b = 0;
            }
            if (!Arrays.equals(bArr2, bArr)) {
                b = 0;
            }
            try {
                if (b != 0 && (i2 & 2) == 0) {
                    dsoManifest2 = null;
                    randomAccessFile.close();
                    if (dsoManifest2 != null) {
                        return false;
                    }
                    final DsoManifest dsoManifest3 = dsoManifest2;
                    Runnable runnable = new Runnable() { // from class: com.facebook.soloader.UnpackingSoSource.1
                        /* JADX WARN: Finally extract failed */
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                RandomAccessFile randomAccessFile2 = new RandomAccessFile(file2, "rw");
                                try {
                                    randomAccessFile2.write(bArr);
                                    randomAccessFile2.setLength(randomAccessFile2.getFilePointer());
                                    randomAccessFile2.close();
                                    randomAccessFile2 = new RandomAccessFile(new File(UnpackingSoSource.this.soDirectory, UnpackingSoSource.MANIFEST_FILE_NAME), "rw");
                                    try {
                                        dsoManifest3.write(randomAccessFile2);
                                        randomAccessFile2.close();
                                        SysUtil.fsyncRecursive(UnpackingSoSource.this.soDirectory);
                                        UnpackingSoSource.writeState(file, (byte) 1);
                                        String str2 = "releasing dso store lock for " + UnpackingSoSource.this.soDirectory + " (from syncer thread)";
                                        fileLocker.close();
                                    } finally {
                                    }
                                } finally {
                                    try {
                                        throw th;
                                    } catch (Throwable th2) {
                                        try {
                                            randomAccessFile2.close();
                                        } catch (Throwable unused2) {
                                        }
                                    }
                                }
                            } catch (IOException e2) {
                                throw new RuntimeException(e2);
                            }
                        }
                    };
                    if ((i2 & 1) != 0) {
                        new Thread(runnable, "SoSync:" + this.soDirectory.getName()).start();
                    } else {
                        runnable.run();
                    }
                    return true;
                }
                regenerate(b, dsoManifest, openDsoIterator);
                if (openDsoIterator != null) {
                    openDsoIterator.close();
                }
                if (makeUnpacker != null) {
                    makeUnpacker.close();
                }
                dsoManifest2 = dsoManifest;
                randomAccessFile.close();
                if (dsoManifest2 != null) {
                }
            } finally {
            }
            writeState(file, (byte) 0);
            makeUnpacker = makeUnpacker();
            dsoManifest = makeUnpacker.getDsoManifest();
            openDsoIterator = makeUnpacker.openDsoIterator();
        } finally {
            try {
                throw th;
            } finally {
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0037 A[Catch: all -> 0x0030, TRY_ENTER, TryCatch #6 {all -> 0x0030, blocks: (B:4:0x002b, B:11:0x0037, B:12:0x003e, B:13:0x0048, B:15:0x004e, B:37:0x008f, B:18:0x0056, B:20:0x005b, B:22:0x0069, B:25:0x007a, B:29:0x0081), top: B:52:0x002b }] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x004e A[Catch: all -> 0x0030, TRY_LEAVE, TryCatch #6 {all -> 0x0030, blocks: (B:4:0x002b, B:11:0x0037, B:12:0x003e, B:13:0x0048, B:15:0x004e, B:37:0x008f, B:18:0x0056, B:20:0x005b, B:22:0x0069, B:25:0x007a, B:29:0x0081), top: B:52:0x002b }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void regenerate(byte b, DsoManifest dsoManifest, InputDsoIterator inputDsoIterator) throws IOException {
        DsoManifest read;
        String str = "regenerating DSO store " + getClass().getName();
        RandomAccessFile randomAccessFile = new RandomAccessFile(new File(this.soDirectory, MANIFEST_FILE_NAME), "rw");
        if (b == 1) {
            try {
                try {
                    read = DsoManifest.read(randomAccessFile);
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        try {
                            randomAccessFile.close();
                        } catch (Throwable unused) {
                        }
                        throw th2;
                    }
                }
            } catch (Exception unused2) {
            }
            if (read == null) {
                read = new DsoManifest(new Dso[0]);
            }
            deleteUnmentionedFiles(dsoManifest.dsos);
            byte[] bArr = new byte[32768];
            while (inputDsoIterator.hasNext()) {
                InputDso next = inputDsoIterator.next();
                boolean z = true;
                int i2 = 0;
                while (z) {
                    Dso[] dsoArr = read.dsos;
                    if (i2 >= dsoArr.length) {
                        break;
                    }
                    if (dsoArr[i2].name.equals(next.dso.name) && read.dsos[i2].hash.equals(next.dso.hash)) {
                        z = false;
                    }
                    i2++;
                }
                if (z) {
                    extractDso(next, bArr);
                }
                if (next != null) {
                    next.close();
                }
            }
            randomAccessFile.close();
            String str2 = "Finished regenerating DSO store " + getClass().getName();
        }
        read = null;
        if (read == null) {
        }
        deleteUnmentionedFiles(dsoManifest.dsos);
        byte[] bArr2 = new byte[32768];
        while (inputDsoIterator.hasNext()) {
        }
        randomAccessFile.close();
        String str22 = "Finished regenerating DSO store " + getClass().getName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void writeState(File file, byte b) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        try {
            randomAccessFile.seek(0L);
            randomAccessFile.write(b);
            randomAccessFile.setLength(randomAccessFile.getFilePointer());
            randomAccessFile.getFD().sync();
        } catch (Throwable th) {
            try {
                throw th;
            } finally {
                try {
                    randomAccessFile.close();
                } catch (Throwable unused) {
                }
            }
        }
    }

    protected byte[] getDepsBlock() throws IOException {
        Parcel obtain = Parcel.obtain();
        Unpacker makeUnpacker = makeUnpacker();
        try {
            Dso[] dsoArr = makeUnpacker.getDsoManifest().dsos;
            obtain.writeByte((byte) 1);
            obtain.writeInt(dsoArr.length);
            for (int i2 = 0; i2 < dsoArr.length; i2++) {
                obtain.writeString(dsoArr[i2].name);
                obtain.writeString(dsoArr[i2].hash);
            }
            if (makeUnpacker != null) {
                makeUnpacker.close();
            }
            byte[] marshall = obtain.marshall();
            obtain.recycle();
            return marshall;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (makeUnpacker != null) {
                    try {
                        makeUnpacker.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th2;
            }
        }
    }

    @Override // com.facebook.soloader.SoSource
    public String[] getSoSourceAbis() {
        String[] strArr = this.mAbis;
        return strArr == null ? super.getSoSourceAbis() : strArr;
    }

    @Override // com.facebook.soloader.DirectorySoSource, com.facebook.soloader.SoSource
    public int loadLibrary(String str, int i2, StrictMode.ThreadPolicy threadPolicy) throws IOException {
        int loadLibraryFrom;
        synchronized (getLibraryLock(str)) {
            loadLibraryFrom = loadLibraryFrom(str, i2, this.soDirectory, threadPolicy);
        }
        return loadLibraryFrom;
    }

    protected abstract Unpacker makeUnpacker() throws IOException;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.soloader.SoSource
    public void prepare(int i2) throws IOException {
        SysUtil.mkdirOrThrow(this.soDirectory);
        FileLocker lock = FileLocker.lock(new File(this.soDirectory, LOCK_FILE_NAME));
        try {
            String str = "locked dso store " + this.soDirectory;
            if (refreshLocked(lock, i2, getDepsBlock())) {
                lock = null;
            } else {
                String str2 = "dso store is up-to-date: " + this.soDirectory;
            }
        } finally {
            if (lock != null) {
                String str3 = "releasing dso store lock for " + this.soDirectory;
                lock.close();
            } else {
                String str4 = "not releasing dso store lock for " + this.soDirectory + " (syncer thread started)";
            }
        }
    }

    public void setSoSourceAbis(String[] strArr) {
        this.mAbis = strArr;
    }

    protected UnpackingSoSource(Context context, File file) {
        super(file, 1);
        this.mLibsBeingLoaded = new HashMap();
        this.mContext = context;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized void prepare(String str) throws IOException {
        synchronized (getLibraryLock(str)) {
            this.mCorruptedLib = str;
            prepare(2);
        }
    }
}
