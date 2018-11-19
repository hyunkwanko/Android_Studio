package com.example.hyeongwan.code3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));
    }

    private static class MyGraphicView extends View {
        public MyGraphicView(Context context){
            super(context);
        }
        @Override
        protected void onDraw(Canvas canvas){
            super.onDraw(canvas);
            Bitmap picture = BitmapFactory.decodeResource(getResources(),
                    R.drawable.pica);

            int cenX = this.getWidth() / 2;
            int cenY = this.getHeight() / 2;
            int picX = (this.getWidth() - picture.getWidth()) / 2; // 화면 중앙에 출력
            int picY = (this.getHeight() - picture.getHeight()) / 2; // 화면 중앙에 출력

//            canvas.scale(1, 1, cenX, cenY); // 크기 조절
//            canvas.skew(0.8f, 0.8f); // 비스듬하게 왜곡
//            canvas.rotate(45, cenX, cenY); // 회전
//            canvas.translate(-150, 200); // 이동

            Paint paint = new Paint();
            BlurMaskFilter bMask;
            bMask = new BlurMaskFilter(10, BlurMaskFilter.Blur.NORMAL); // NORMAL, INNER, OUTER, SOLID
            paint.setMaskFilter(bMask);
            canvas.drawBitmap(picture, picX, picY, paint);
            picture.recycle();

//            Paint paint = new Paint();
//            paint.setColor(Color.GRAY);
//            EmbossMaskFilter eMask;
//            eMask = new EmbossMaskFilter(new float[] { 10, 3, 3 }, 0.5f, 5, 10);
//            paint.setMaskFilter(eMask);
//            canvas.drawCircle(cenX, cenY, 150, paint);
        }
    }
}






















