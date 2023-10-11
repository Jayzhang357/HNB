package com.wl.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class OutlineTextView extends AppCompatTextView {

    private Paint textPaint;
    private Paint outlinePaint;
    private int outlineColor;

    public OutlineTextView(Context context) {
        super(context);
        init();
    }

    public OutlineTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        textPaint = getPaint();
        outlinePaint = new Paint();
        outlinePaint.setColor(outlineColor); // 设置描边颜色
        outlinePaint.setStyle(Paint.Style.STROKE);
        outlinePaint.setStrokeWidth(5); // 设置描边宽度
        outlinePaint.setAntiAlias(true);
        outlineColor = Color.BLACK; // 设置描边颜色，默认为黑色
    }

    public void setOutlineColor(int color) {
        outlineColor = color;
        outlinePaint.setColor(outlineColor);
        invalidate(); // 重新绘制
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 在文本的周围绘制描边
        String text = getText().toString();
        canvas.drawText(text, 0, text.length(), 0, getTextSize(), outlinePaint);
        super.onDraw(canvas);
    }
}
