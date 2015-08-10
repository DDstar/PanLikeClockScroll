package com.example.testmodel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class PanLikeClock extends View {

	private Paint mPaint;
	private float degree;
	private boolean isTouch;

	public PanLikeClock(Context context, AttributeSet attrs) {
		super(context, attrs);
		mPaint = new Paint();
		mPaint.setColor(Color.BLACK);
		mPaint.setStrokeWidth(5);
	}
	float xOld =0.0f; 
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x = event.getX();
		if (xOld == 0.0f) {
			xOld = x;
		}else {
			if (xOld > x) {
				Log.e("onTouchEvent", "左");
				degree+=30;
			}else {
				degree-= 30;
				Log.e("onTouchEvent", "右");
			}
			invalidate();
		}
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
//			Log.e("onTouchEvent", "ACTION_DOWN");
			isTouch = true;
			break;
		case MotionEvent.ACTION_UP:
			isTouch = true;
			xOld = 0.0f;
			Log.e("onTouchEvent", "ACTION_UP");
			break;
		case MotionEvent.ACTION_MOVE:
			isTouch = false;
			Log.e("onTouchEvent", "ACTION_MOVE");
			break;

		default:
			break;
		}
		return true;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		canvas.drawCircle(250, 300, 200, mPaint);

		mPaint.setColor(Color.WHITE);
		canvas.drawCircle(250, 300, 180, mPaint);

		mPaint.setColor(Color.BLACK);
		canvas.drawCircle(250, 300, 10, mPaint);
		
		mPaint.setStrokeWidth(20);
		canvas.rotate(degree,200,300);
		canvas.drawLine(250, 300, 200, 450, mPaint);
		canvas.restore();
		drawNums(canvas);
		
	}

	private void drawNums(Canvas canvas) {
		Paint paint = new Paint();
		paint.setTextSize(40);
		paint.setColor(Color.RED);
		canvas.drawText("12", 210, 160, paint);
	}

}
