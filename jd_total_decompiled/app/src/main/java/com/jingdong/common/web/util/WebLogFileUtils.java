package com.jingdong.common.web.util;

import android.text.TextUtils;
import com.jingdong.common.permission.PermissionHelper;
import com.jingdong.sdk.log.Log;
import com.jingdong.sdk.oklog.OKLog;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.Thread;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes12.dex */
public class WebLogFileUtils {
    private static final int MAX_APPEND_LOG_LENGTH = 500000;
    private static final long MAX_LOG_FILE = 2097152;
    private static final int MAX_NEW_LOG_COUNT = Integer.MAX_VALUE;
    private static String TAG = "WebLogFileUtils";
    private static volatile Thread writeLogThread;
    private static volatile AtomicBoolean threadInited = new AtomicBoolean(false);
    private static volatile boolean threadRunning = false;
    private static volatile LinkedBlockingQueue<String> appendLogQue = new LinkedBlockingQueue<>(Integer.MAX_VALUE);

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class WriteLogThread extends Thread {
        private File logFile;
        private OutputStream outputStream;

        public WriteLogThread() {
            super("t_X5_writelog");
            this.outputStream = null;
            this.logFile = null;
        }

        private void getLogToWrite() {
            String str = null;
            while (true) {
                try {
                    str = (String) WebLogFileUtils.appendLogQue.take();
                } catch (Exception e2) {
                    e2.printStackTrace();
                    Log.e(WebLogFileUtils.TAG, "write-log thread awakes by exception", e2);
                }
                if (str != null && !"".equals(str)) {
                    break;
                }
            }
            StringBuilder sb = new StringBuilder(str);
            for (int i2 = 0; sb.length() < WebLogFileUtils.MAX_APPEND_LOG_LENGTH && i2 < WebLogFileUtils.appendLogQue.size(); i2++) {
                String str2 = (String) WebLogFileUtils.appendLogQue.poll();
                if (TextUtils.isEmpty(str2)) {
                    break;
                }
                sb.append(str2);
            }
            if (sb.length() > 0) {
                writeToDisk(sb.toString());
            }
        }

        /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
            jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:24:0x0080
            	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:81)
            	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:47)
            	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
            */
        private synchronized void writeFile(java.lang.String r6) {
            /*
                r5 = this;
                monitor-enter(r5)
                java.io.File r0 = r5.logFile     // Catch: java.lang.Throwable -> Lba
                if (r0 != 0) goto L7
                monitor-exit(r5)
                return
            L7:
                java.io.File r0 = r0.getParentFile()     // Catch: java.lang.Throwable -> L84
                r0.mkdirs()     // Catch: java.lang.Throwable -> L84
                java.io.File r0 = r5.logFile     // Catch: java.lang.Throwable -> L84
                boolean r0 = r0.isFile()     // Catch: java.lang.Throwable -> L84
                if (r0 == 0) goto L35
                java.io.File r0 = r5.logFile     // Catch: java.lang.Throwable -> L84
                boolean r0 = r0.exists()     // Catch: java.lang.Throwable -> L84
                if (r0 == 0) goto L35
                java.io.File r0 = r5.logFile     // Catch: java.lang.Throwable -> L84
                long r0 = r0.length()     // Catch: java.lang.Throwable -> L84
                r2 = 2097152(0x200000, double:1.0361308E-317)
                int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                if (r4 <= 0) goto L35
                java.io.File r0 = r5.logFile     // Catch: java.lang.Throwable -> L84
                r0.delete()     // Catch: java.lang.Throwable -> L84
                java.io.File r0 = r5.logFile     // Catch: java.lang.Throwable -> L84
                r0.createNewFile()     // Catch: java.lang.Throwable -> L84
            L35:
                java.io.OutputStream r0 = r5.outputStream     // Catch: java.lang.Throwable -> L84
                if (r0 != 0) goto L66
                java.lang.String r0 = com.jingdong.common.web.util.WebLogFileUtils.access$000()     // Catch: java.lang.Throwable -> L84
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L84
                r1.<init>()     // Catch: java.lang.Throwable -> L84
                java.lang.String r2 = "Open stream for X5 log file. Location = "
                r1.append(r2)     // Catch: java.lang.Throwable -> L84
                java.io.File r2 = r5.logFile     // Catch: java.lang.Throwable -> L84
                java.lang.String r2 = r2.getAbsolutePath()     // Catch: java.lang.Throwable -> L84
                r1.append(r2)     // Catch: java.lang.Throwable -> L84
                java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L84
                com.jingdong.sdk.log.Log.d(r0, r1)     // Catch: java.lang.Throwable -> L84
                java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L84
                java.io.File r1 = r5.logFile     // Catch: java.lang.Throwable -> L84
                r2 = 1
                r0.<init>(r1, r2)     // Catch: java.lang.Throwable -> L84
                java.io.BufferedOutputStream r1 = new java.io.BufferedOutputStream     // Catch: java.lang.Throwable -> L84
                r1.<init>(r0)     // Catch: java.lang.Throwable -> L84
                r5.outputStream = r1     // Catch: java.lang.Throwable -> L84
            L66:
                java.io.OutputStream r0 = r5.outputStream     // Catch: java.lang.Throwable -> L84
                byte[] r6 = r6.getBytes()     // Catch: java.lang.Throwable -> L84
                r0.write(r6)     // Catch: java.lang.Throwable -> L84
                java.io.OutputStream r6 = r5.outputStream     // Catch: java.lang.Throwable -> Lba
                if (r6 == 0) goto La2
                r6.flush()     // Catch: java.lang.Throwable -> L77
                goto La2
            L77:
                r6 = move-exception
                java.lang.String r0 = com.jingdong.common.web.util.WebLogFileUtils.access$000()     // Catch: java.lang.Throwable -> Lba
                java.lang.String r1 = r6.getMessage()     // Catch: java.lang.Throwable -> Lba
            L80:
                com.jingdong.sdk.log.Log.e(r0, r1, r6)     // Catch: java.lang.Throwable -> Lba
                goto La2
            L84:
                r6 = move-exception
                java.lang.String r0 = com.jingdong.common.web.util.WebLogFileUtils.access$000()     // Catch: java.lang.Throwable -> La4
                java.lang.String r1 = r6.getMessage()     // Catch: java.lang.Throwable -> La4
                com.jingdong.sdk.log.Log.e(r0, r1, r6)     // Catch: java.lang.Throwable -> La4
                java.io.OutputStream r6 = r5.outputStream     // Catch: java.lang.Throwable -> Lba
                if (r6 == 0) goto La2
                r6.flush()     // Catch: java.lang.Throwable -> L98
                goto La2
            L98:
                r6 = move-exception
                java.lang.String r0 = com.jingdong.common.web.util.WebLogFileUtils.access$000()     // Catch: java.lang.Throwable -> Lba
                java.lang.String r1 = r6.getMessage()     // Catch: java.lang.Throwable -> Lba
                goto L80
            La2:
                monitor-exit(r5)
                return
            La4:
                r6 = move-exception
                java.io.OutputStream r0 = r5.outputStream     // Catch: java.lang.Throwable -> Lba
                if (r0 == 0) goto Lb9
                r0.flush()     // Catch: java.lang.Throwable -> Lad
                goto Lb9
            Lad:
                r0 = move-exception
                java.lang.String r1 = com.jingdong.common.web.util.WebLogFileUtils.access$000()     // Catch: java.lang.Throwable -> Lba
                java.lang.String r2 = r0.getMessage()     // Catch: java.lang.Throwable -> Lba
                com.jingdong.sdk.log.Log.e(r1, r2, r0)     // Catch: java.lang.Throwable -> Lba
            Lb9:
                throw r6     // Catch: java.lang.Throwable -> Lba
            Lba:
                r6 = move-exception
                monitor-exit(r5)
                goto Lbe
            Lbd:
                throw r6
            Lbe:
                goto Lbd
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.web.util.WebLogFileUtils.WriteLogThread.writeFile(java.lang.String):void");
        }

        private void writeToDisk(String str) {
            Log.e(WebLogFileUtils.TAG, "write log --> " + str);
            if (this.logFile == null) {
                try {
                    File externalFilesDir = PermissionHelper.getExternalFilesDir("webview");
                    String absolutePath = externalFilesDir != null ? externalFilesDir.getAbsolutePath() : null;
                    if (TextUtils.isEmpty(absolutePath)) {
                        Log.e(WebLogFileUtils.TAG, "Cannot write log to file, because files dir path is empty.");
                        return;
                    }
                    this.logFile = new File(absolutePath, "x5log.txt");
                } catch (Exception e2) {
                    Log.e(WebLogFileUtils.TAG, e2.getMessage(), e2);
                }
            }
            writeFile(str);
        }

        public void closeOutput() {
            try {
                OutputStream outputStream = this.outputStream;
                if (outputStream != null) {
                    outputStream.close();
                }
                this.outputStream = null;
                this.logFile = null;
            } catch (IOException e2) {
                Log.e(WebLogFileUtils.TAG, "Couldn't close output stream!", e2);
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            WebLogFileUtils.threadInited.set(true);
            boolean unused = WebLogFileUtils.threadRunning = true;
            while (WebLogFileUtils.threadRunning) {
                getLogToWrite();
            }
            boolean unused2 = WebLogFileUtils.threadRunning = false;
            WebLogFileUtils.threadInited.set(false);
            closeOutput();
        }
    }

    static /* synthetic */ String access$000() {
        return TAG;
    }

    public static void addLog(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (appendLogQue.remainingCapacity() <= 0) {
            String poll = appendLogQue.poll();
            if (OKLog.D) {
                String str2 = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("log queue is FULL! delete the oldest log = [");
                if (poll == null) {
                    poll = "";
                }
                sb.append(poll);
                sb.append("]");
                OKLog.d(str2, sb.toString());
            }
        }
        boolean offer = appendLogQue.offer(str);
        if (OKLog.D) {
            Log.d(TAG, "add log into queue, now size = " + appendLogQue.size());
            if (!offer) {
                Log.d(TAG, "fail to add log into queue, the queue is still full after trying to delete the oldest element, now size = " + appendLogQue.size());
            }
        }
        if (!offer || threadInited.getAndSet(true)) {
            return;
        }
        startToWrite();
    }

    private static void startToWrite() {
        try {
            if (writeLogThread == null || !writeLogThread.isAlive()) {
                Log.d(TAG, "create new IO thread to write log");
                writeLogThread = new WriteLogThread();
                writeLogThread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: com.jingdong.common.web.util.WebLogFileUtils.1
                    @Override // java.lang.Thread.UncaughtExceptionHandler
                    public void uncaughtException(Thread thread, Throwable th) {
                        Log.e(WebLogFileUtils.TAG, "write-log thread throws an exception", th);
                        boolean unused = WebLogFileUtils.threadRunning = false;
                        WebLogFileUtils.threadInited.set(false);
                    }
                });
                writeLogThread.start();
            }
        } catch (Exception e2) {
            if (writeLogThread != null) {
                try {
                    writeLogThread.interrupt();
                } catch (Exception e3) {
                    Log.e(TAG, e3.getMessage(), e3);
                }
            }
            threadInited.set(false);
            Log.e(TAG, e2.getMessage(), e2);
        }
    }
}
