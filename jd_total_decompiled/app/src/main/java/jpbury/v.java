package jpbury;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import jpbury.e;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;

/* loaded from: classes11.dex */
public class v {

    /* renamed from: c */
    private static final long f20147c = 10;
    private final ExecutorService a;
    private final OkHttpClient b;

    /* loaded from: classes11.dex */
    public static final class b {
        public static final v a = new v();

        private b() {
        }
    }

    /* loaded from: classes11.dex */
    public interface c {
        void a();

        void a(@Nullable e.a aVar);
    }

    /* loaded from: classes11.dex */
    public static final class d implements Callback {
        private final c a;

        public d(c cVar) {
            this.a = cVar;
        }

        @Override // okhttp3.Callback
        public void onFailure(Call call, IOException iOException) {
            this.a.a();
        }

        @Override // okhttp3.Callback
        public void onResponse(Call call, Response response) throws IOException {
            ResponseBody body;
            if (response == null || !response.isSuccessful() || (body = response.body()) == null) {
                this.a.a();
                return;
            }
            try {
                e eVar = (e) x.a().fromJson(body.string(), e.class);
                if (eVar == null) {
                    this.a.a(null);
                } else if (eVar.d()) {
                    this.a.a(eVar.b());
                } else {
                    this.a.a();
                }
            } catch (Throwable th) {
                this.a.a();
                th.printStackTrace();
            }
        }
    }

    private v() {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 3, 60L, timeUnit, new ArrayBlockingQueue(256), Util.threadFactory("OkHttp Dispatcher", false), new ThreadPoolExecutor.DiscardPolicy());
        this.a = threadPoolExecutor;
        this.b = new OkHttpClient.Builder().connectTimeout(f20147c, timeUnit).readTimeout(f20147c, timeUnit).writeTimeout(f20147c, timeUnit).dispatcher(new Dispatcher(threadPoolExecutor)).build();
    }

    public static v a() {
        return b.a;
    }

    public void a(@NonNull Request request, @NonNull c cVar) {
        try {
            this.b.newCall(request).enqueue(new d(cVar));
        } catch (OutOfMemoryError e2) {
            e2.printStackTrace();
            cVar.a();
        }
    }
}
