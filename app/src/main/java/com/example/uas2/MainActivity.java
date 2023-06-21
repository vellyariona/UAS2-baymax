package com.example.uas2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView mImgView;
    Bitmap mBitmap;
    Canvas mCanvas;
    private int mColorBackground;
    Paint mCirclePaint = new Paint();
    Paint mHeadPaint = new Paint();
    Paint mStroke = new Paint();
    private ConstraintLayout mainLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImgView = findViewById(R.id.my_img_view);

        mCirclePaint.setColor(getResources().getColor(R.color.black));
        mHeadPaint.setColor(getResources().getColor(R.color.white));
        mStroke.setColor(getResources().getColor(R.color.black));

        mainLayout = findViewById(R.id.main_layout);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        int vWidth = mImgView.getWidth();
        int vHeight = mImgView.getHeight();

        mBitmap = Bitmap.createBitmap(vWidth, vHeight, Bitmap.Config.ARGB_8888);
        mImgView.setImageBitmap(mBitmap);
        mColorBackground = ResourcesCompat.getColor(getResources(), R.color.blue, null);
        mCanvas = new Canvas(mBitmap);
        mCanvas.drawColor(mColorBackground);

        mStroke.setStrokeWidth(20f);

        mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateBaymax(view);
                mColorBackground = ResourcesCompat.getColor(getResources(), R.color.blue, null);
                mCanvas = new Canvas(mBitmap);
                mCanvas.drawColor(mColorBackground);
                drawHead();
                drawRightEye();
                drawLeftEye();
                drawEyeConnector();
            }
        });
//        drawHead();
//        drawRightEye();
//        drawLeftEye();
//        drawEyeConnector();
    }

    private void drawHead() {
        int vWidth = mImgView.getWidth();
        int vHeight = mImgView.getHeight();

        mCanvas.save();
        mCanvas.scale(2, 1);
        mCanvas.drawOval((vWidth/2-100), (vHeight/2-500), (vWidth/10), (vHeight/2+50), mHeadPaint);
        mCanvas.restore();
    }

    private void drawRightEye() {
        int vWidth = mImgView.getWidth();
        mCanvas.drawCircle(vWidth/2+150, 800, 50, mCirclePaint);
    }

    private void drawLeftEye() {
        int vWidth = mImgView.getWidth();
        mCanvas.drawCircle(vWidth/2-150, 800, 50, mCirclePaint);
    }

    private void drawEyeConnector() {
        int vWidth = mImgView.getWidth();
        mCanvas.drawLine(vWidth/2-100, 800, vWidth/2+100,800, mStroke);
    }

//    untuk no.3
    public void animateBaymax(View view){
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(mImgView, "alpha", 0f, 1f);
        alphaAnimator.setDuration(1000);

        ObjectAnimator rotationAnimator = ObjectAnimator.ofFloat(mImgView, "rotationY", 180);
        rotationAnimator.setDuration(1000);

        ObjectAnimator alphaAnimator2 = ObjectAnimator.ofFloat(mImgView, "alpha", 1f, 0f);
        alphaAnimator2.setDuration(1000);
        alphaAnimator2.start();

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(alphaAnimator, rotationAnimator, alphaAnimator2);
        animatorSet.start();
    }
}