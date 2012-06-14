package android.opengl.cts;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class OpenGLES20NativeActivityOne extends Activity {

    public static final String EXTRA_VIEW_TYPE = "viewType";
    public static final String EXTRA_VIEW_INDEX = "viewIndex";

    int mValue;

    OpenGLES20View view;
    GL2Renderer mRenderer;
    int mRendererType;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

        int viewType = getIntent().getIntExtra(EXTRA_VIEW_TYPE, -1);
        int viewIndex = getIntent().getIntExtra(EXTRA_VIEW_INDEX, -1);

        view = new OpenGLES20View(this, viewType, viewIndex);
        setContentView(view);
    }

    @Override
    protected void onPause() {
        super.onPause();
        view.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(view != null) {
            view.onResume();
        }
    }

    class OpenGLES20View extends GLSurfaceView {
        public OpenGLES20View(Context context, int category, int testCase) {
            super(context);
            setEGLContextClientVersion(2);
            mRenderer = new GL2Renderer(category, testCase);
            setRenderer(mRenderer);
        }

        @Override
        public void setEGLContextClientVersion(int version) {
            super.setEGLContextClientVersion(version);
        }

        public GL2Renderer getRenderer() {
            return mRenderer;
        }
    }
}
class GL2Renderer implements GLSurfaceView.Renderer {
    private String TAG = "GL2Renderer";
    private int mCategory = -1;
    private int mTestCase = -1;
    int mAttachShaderError = -1;
    int mShaderCount = -1;

    public GL2Renderer(int category, int testcase) {
        this.mCategory = category;
        this.mTestCase = testcase;
    }

    public void onDrawFrame(GL10 gl) {

    }

    public void onSurfaceChanged(GL10 gl, int width, int height) {
        Log.i(TAG ,"onSurfaceCreated");
        GL2JniLibOne.init(mCategory, mTestCase, width, height);
        this.mAttachShaderError = GL2JniLibOne.getAttachShaderError();
        Log.i(TAG,"error:" + mAttachShaderError);
        this.mShaderCount = GL2JniLibOne.getAttachedShaderCount();
        Log.i(TAG,"ShaderCount:" + mShaderCount);
    }

    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

    }
}