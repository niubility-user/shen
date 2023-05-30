package com.facebook.react.devsupport;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.SpannedString;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.R;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.devsupport.RedBoxHandler;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.devsupport.interfaces.StackFrame;
import javax.annotation.Nullable;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public class RedBoxDialog extends Dialog implements AdapterView.OnItemClickListener {
    private boolean isReporting;
    private Button mCopyToClipboardButton;
    private final DevSupportManager mDevSupportManager;
    private Button mDismissButton;
    private final DoubleTapReloadRecognizer mDoubleTapReloadRecognizer;
    @Nullable
    private View mLineSeparator;
    @Nullable
    private ProgressBar mLoadingIndicator;
    @Nullable
    private final RedBoxHandler mRedBoxHandler;
    private Button mReloadJsButton;
    @Nullable
    private Button mReportButton;
    private View.OnClickListener mReportButtonOnClickListener;
    private RedBoxHandler.ReportCompletedListener mReportCompletedListener;
    @Nullable
    private TextView mReportTextView;
    private ListView mStackView;

    /* loaded from: classes12.dex */
    private static class CopyToHostClipBoardTask extends AsyncTask<String, Void, Void> {
        private final DevSupportManager mDevSupportManager;

        private CopyToHostClipBoardTask(DevSupportManager devSupportManager) {
            this.mDevSupportManager = devSupportManager;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(String... strArr) {
            try {
                String uri = Uri.parse(this.mDevSupportManager.getSourceUrl()).buildUpon().path("/copy-to-clipboard").query(null).build().toString();
                for (String str : strArr) {
                    new OkHttpClient().newCall(new Request.Builder().url(uri).post(RequestBody.create((MediaType) null, str)).build()).execute();
                }
            } catch (Exception e2) {
                FLog.e(ReactConstants.TAG, "Could not copy to the host clipboard", e2);
            }
            return null;
        }
    }

    /* loaded from: classes12.dex */
    private static class OpenStackFrameTask extends AsyncTask<StackFrame, Void, Void> {
        private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        private final DevSupportManager mDevSupportManager;

        private static JSONObject stackFrameToJson(StackFrame stackFrame) {
            return new JSONObject(MapBuilder.of("file", stackFrame.getFile(), "methodName", stackFrame.getMethod(), StackTraceHelper.LINE_NUMBER_KEY, Integer.valueOf(stackFrame.getLine()), "column", Integer.valueOf(stackFrame.getColumn())));
        }

        private OpenStackFrameTask(DevSupportManager devSupportManager) {
            this.mDevSupportManager = devSupportManager;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(StackFrame... stackFrameArr) {
            try {
                String uri = Uri.parse(this.mDevSupportManager.getSourceUrl()).buildUpon().path("/open-stack-frame").query(null).build().toString();
                OkHttpClient okHttpClient = new OkHttpClient();
                for (StackFrame stackFrame : stackFrameArr) {
                    okHttpClient.newCall(new Request.Builder().url(uri).post(RequestBody.create(JSON, stackFrameToJson(stackFrame).toString())).build()).execute();
                }
            } catch (Exception e2) {
                FLog.e(ReactConstants.TAG, "Could not open stack frame", e2);
            }
            return null;
        }
    }

    /* loaded from: classes12.dex */
    private static class StackAdapter extends BaseAdapter {
        private static final int VIEW_TYPE_COUNT = 2;
        private static final int VIEW_TYPE_STACKFRAME = 1;
        private static final int VIEW_TYPE_TITLE = 0;
        private final StackFrame[] mStack;
        private final String mTitle;

        /* loaded from: classes12.dex */
        private static class FrameViewHolder {
            private final TextView mFileView;
            private final TextView mMethodView;

            private FrameViewHolder(View view) {
                this.mMethodView = (TextView) view.findViewById(R.id.rn_frame_method);
                this.mFileView = (TextView) view.findViewById(R.id.rn_frame_file);
            }
        }

        public StackAdapter(String str, StackFrame[] stackFrameArr) {
            this.mTitle = str;
            this.mStack = stackFrameArr;
        }

        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public boolean areAllItemsEnabled() {
            return false;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.mStack.length + 1;
        }

        @Override // android.widget.Adapter
        public Object getItem(int i2) {
            return i2 == 0 ? this.mTitle : this.mStack[i2 - 1];
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public int getItemViewType(int i2) {
            return i2 == 0 ? 0 : 1;
        }

        @Override // android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            TextView textView;
            if (i2 == 0) {
                if (view != null) {
                    textView = (TextView) view;
                } else {
                    textView = (TextView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.redbox_item_title, viewGroup, false);
                }
                textView.setText(this.mTitle);
                return textView;
            }
            if (view == null) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.redbox_item_frame, viewGroup, false);
                view.setTag(new FrameViewHolder(view));
            }
            StackFrame stackFrame = this.mStack[i2 - 1];
            FrameViewHolder frameViewHolder = (FrameViewHolder) view.getTag();
            frameViewHolder.mMethodView.setText(stackFrame.getMethod());
            frameViewHolder.mFileView.setText(StackTraceHelper.formatFrameSource(stackFrame));
            return view;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public int getViewTypeCount() {
            return 2;
        }

        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public boolean isEnabled(int i2) {
            return i2 > 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public RedBoxDialog(Context context, DevSupportManager devSupportManager, @Nullable RedBoxHandler redBoxHandler) {
        super(context, R.style.Theme_Catalyst_RedBox);
        this.isReporting = false;
        this.mReportCompletedListener = new RedBoxHandler.ReportCompletedListener() { // from class: com.facebook.react.devsupport.RedBoxDialog.1
            @Override // com.facebook.react.devsupport.RedBoxHandler.ReportCompletedListener
            public void onReportError(SpannedString spannedString) {
                RedBoxDialog.this.isReporting = false;
                ((Button) Assertions.assertNotNull(RedBoxDialog.this.mReportButton)).setEnabled(true);
                ((ProgressBar) Assertions.assertNotNull(RedBoxDialog.this.mLoadingIndicator)).setVisibility(8);
                ((TextView) Assertions.assertNotNull(RedBoxDialog.this.mReportTextView)).setText(spannedString);
            }

            @Override // com.facebook.react.devsupport.RedBoxHandler.ReportCompletedListener
            public void onReportSuccess(SpannedString spannedString) {
                RedBoxDialog.this.isReporting = false;
                ((Button) Assertions.assertNotNull(RedBoxDialog.this.mReportButton)).setEnabled(true);
                ((ProgressBar) Assertions.assertNotNull(RedBoxDialog.this.mLoadingIndicator)).setVisibility(8);
                ((TextView) Assertions.assertNotNull(RedBoxDialog.this.mReportTextView)).setText(spannedString);
            }
        };
        this.mReportButtonOnClickListener = new View.OnClickListener() { // from class: com.facebook.react.devsupport.RedBoxDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (RedBoxDialog.this.mRedBoxHandler == null || !RedBoxDialog.this.mRedBoxHandler.isReportEnabled() || RedBoxDialog.this.isReporting) {
                    return;
                }
                RedBoxDialog.this.isReporting = true;
                ((TextView) Assertions.assertNotNull(RedBoxDialog.this.mReportTextView)).setText("Reporting...");
                ((TextView) Assertions.assertNotNull(RedBoxDialog.this.mReportTextView)).setVisibility(0);
                ((ProgressBar) Assertions.assertNotNull(RedBoxDialog.this.mLoadingIndicator)).setVisibility(0);
                ((View) Assertions.assertNotNull(RedBoxDialog.this.mLineSeparator)).setVisibility(0);
                ((Button) Assertions.assertNotNull(RedBoxDialog.this.mReportButton)).setEnabled(false);
                RedBoxDialog.this.mRedBoxHandler.reportRedbox(view.getContext(), (String) Assertions.assertNotNull(RedBoxDialog.this.mDevSupportManager.getLastErrorTitle()), (StackFrame[]) Assertions.assertNotNull(RedBoxDialog.this.mDevSupportManager.getLastErrorStack()), RedBoxDialog.this.mDevSupportManager.getSourceUrl(), (RedBoxHandler.ReportCompletedListener) Assertions.assertNotNull(RedBoxDialog.this.mReportCompletedListener));
            }
        };
        requestWindowFeature(1);
        setContentView(R.layout.redbox_view);
        this.mDevSupportManager = devSupportManager;
        this.mDoubleTapReloadRecognizer = new DoubleTapReloadRecognizer();
        this.mRedBoxHandler = redBoxHandler;
        ListView listView = (ListView) findViewById(R.id.rn_redbox_stack);
        this.mStackView = listView;
        listView.setOnItemClickListener(this);
        Button button = (Button) findViewById(R.id.rn_redbox_reload_button);
        this.mReloadJsButton = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.facebook.react.devsupport.RedBoxDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RedBoxDialog.this.mDevSupportManager.handleReloadJS();
            }
        });
        Button button2 = (Button) findViewById(R.id.rn_redbox_dismiss_button);
        this.mDismissButton = button2;
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.facebook.react.devsupport.RedBoxDialog.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RedBoxDialog.this.dismiss();
            }
        });
        Button button3 = (Button) findViewById(R.id.rn_redbox_copy_button);
        this.mCopyToClipboardButton = button3;
        button3.setOnClickListener(new View.OnClickListener() { // from class: com.facebook.react.devsupport.RedBoxDialog.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String lastErrorTitle = RedBoxDialog.this.mDevSupportManager.getLastErrorTitle();
                StackFrame[] lastErrorStack = RedBoxDialog.this.mDevSupportManager.getLastErrorStack();
                Assertions.assertNotNull(lastErrorTitle);
                Assertions.assertNotNull(lastErrorStack);
                new CopyToHostClipBoardTask(RedBoxDialog.this.mDevSupportManager).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, StackTraceHelper.formatStackTrace(lastErrorTitle, lastErrorStack));
            }
        });
        if (redBoxHandler == null || !redBoxHandler.isReportEnabled()) {
            return;
        }
        this.mLoadingIndicator = (ProgressBar) findViewById(R.id.rn_redbox_loading_indicator);
        this.mLineSeparator = findViewById(R.id.rn_redbox_line_separator);
        TextView textView = (TextView) findViewById(R.id.rn_redbox_report_label);
        this.mReportTextView = textView;
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        this.mReportTextView.setHighlightColor(0);
        Button button4 = (Button) findViewById(R.id.rn_redbox_report_button);
        this.mReportButton = button4;
        button4.setOnClickListener(this.mReportButtonOnClickListener);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
        new OpenStackFrameTask(this.mDevSupportManager).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (StackFrame) this.mStackView.getAdapter().getItem(i2));
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (i2 == 82) {
            this.mDevSupportManager.showDevOptionsDialog();
            return true;
        }
        if (this.mDoubleTapReloadRecognizer.didDoubleTapR(i2, getCurrentFocus())) {
            this.mDevSupportManager.handleReloadJS();
        }
        return super.onKeyUp(i2, keyEvent);
    }

    public void resetReporting() {
        RedBoxHandler redBoxHandler = this.mRedBoxHandler;
        if (redBoxHandler == null || !redBoxHandler.isReportEnabled()) {
            return;
        }
        this.isReporting = false;
        ((TextView) Assertions.assertNotNull(this.mReportTextView)).setVisibility(8);
        ((ProgressBar) Assertions.assertNotNull(this.mLoadingIndicator)).setVisibility(8);
        ((View) Assertions.assertNotNull(this.mLineSeparator)).setVisibility(8);
        ((Button) Assertions.assertNotNull(this.mReportButton)).setVisibility(0);
        ((Button) Assertions.assertNotNull(this.mReportButton)).setEnabled(true);
    }

    public void setExceptionDetails(String str, StackFrame[] stackFrameArr) {
        this.mStackView.setAdapter((ListAdapter) new StackAdapter(str, stackFrameArr));
    }
}
