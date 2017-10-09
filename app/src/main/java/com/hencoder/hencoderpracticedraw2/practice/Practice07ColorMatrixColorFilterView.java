package com.hencoder.hencoderpracticedraw2.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw2.R;

public class Practice07ColorMatrixColorFilterView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    private Paint mPaint1;

    public Practice07ColorMatrixColorFilterView(Context context) {
        super(context);
    }

    public Practice07ColorMatrixColorFilterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice07ColorMatrixColorFilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.batman);

        // 使用 setColorFilter() 设置一个 ColorMatrixColorFilter
        // 用 ColorMatrixColorFilter.setSaturation() 把饱和度去掉
        /**
         * 计算过程
         *      R’ = a*R + b*G + c*B + d*A + e;
         *      G’ = f*R + g*G + h*B + i*A + j;
         *      B’ = k*R + l*G + m*B + n*A + o;
         *      A’ = p*R + q*G + r*B + s*A + t;
         */
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0f);
        ColorFilter colorFilter = new ColorMatrixColorFilter(cm) ;
        mPaint1 = new Paint();
        mPaint1.setColorFilter(colorFilter) ;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(bitmap, 0, 0, paint);     // 没有去掉饱和度的图片
        canvas.drawBitmap(bitmap, 400, 0, mPaint1); // 去掉饱和度的图片（脸部颜色变了）
    }
}
