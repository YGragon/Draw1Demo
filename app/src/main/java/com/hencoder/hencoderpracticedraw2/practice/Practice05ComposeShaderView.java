package com.hencoder.hencoderpracticedraw2.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ComposeShader;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw2.R;

public class Practice05ComposeShaderView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public Practice05ComposeShaderView(Context context) {
        super(context);
    }

    public Practice05ComposeShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice05ComposeShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        setLayerType(LAYER_TYPE_SOFTWARE, null); // 硬件加速下 ComposeShader 不能使用两个同类型的 Shader

        // 用 Paint.setShader(shader) 设置一个 ComposeShader
        // Shader 1: BitmapShader 图片：R.drawable.batman
        // Shader 2: BitmapShader 图片：R.drawable.batman_logo
        // 第一个 Shader：头像的 Bitmap
        Bitmap batmanBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.batman) ;
        Shader batmanShader = new BitmapShader(batmanBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP) ;
        // 第二个 Shader：从上到下的线性渐变（由透明到黑色）
        Bitmap batmanLogoBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.batman_logo) ;
        Shader batmanLogoShader = new BitmapShader(batmanLogoBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP) ;
        // ComposeShader：结合两个 Shader
        /**
         * ComposeShader() 在硬件加速下是不支持两个相同类型的 Shader 的，所以这里也需要关闭硬件加速才能看到效果
         * ComposeShader(Shader shaderA, Shader shaderB, PorterDuff.Mode mode)
         * shaderA, shaderB：两个相继使用的 Shader
         * mode: 两个 Shader 的叠加模式，即 shaderA 和 shaderB 应该怎样共同绘制。它的类型是 PorterDuff.Mode 。
         * PorterDuff.Mode 是指两个图像共同绘制时的颜色策略，具体可以谷歌。
         */
        Shader shader = new ComposeShader(batmanShader,batmanLogoShader, PorterDuff.Mode.SRC_OVER) ;
        paint.setShader(shader) ;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(200, 200, 200, paint);
    }
}
