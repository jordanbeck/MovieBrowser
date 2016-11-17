package com.twentyfivesquares.moviebrowser.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.twentyfivesquares.moviebrowser.R;

public class StarRibbonView extends View {

    private Paint whitePaint;
    private Paint accentPaint;
    private Bitmap starBitmap;
    private int starTop;
    private int starLeft;

    public StarRibbonView(Context context) {
        this(context, null);
    }

    public StarRibbonView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StarRibbonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public StarRibbonView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        // Allocate all variables before drawing
        int color = getContext().getResources().getColor(R.color.colorAccent);
        accentPaint = new Paint();
        accentPaint.setColor(color);

        whitePaint = new Paint();
        whitePaint.setColor(Color.WHITE);

        // Get all dimens needed for drawing
        Resources res = getResources();
        starTop = res.getDimensionPixelSize(R.dimen.star_top);
        starLeft = res.getDimensionPixelSize(R.dimen.star_left);
        final int starSize = res.getDimensionPixelSize(R.dimen.star_size);
        // Because I'm not much of a designer, I had to resize this on the fly instead of generating
        // the asset to fit :) I attempted to use a vector drawable, but there appears to be a problem
        // when attempting to draw one on a canvas (the Paint color is ignored). So I had to stick
        // with the old fashion icons for this one.
        starBitmap = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(res, R.drawable.ic_star), starSize, starSize, false);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final int w = getWidth() / 2;

        // Create the path that will be the triangle
        Path path = new Path();
        path.moveTo( w, 0);
        path.lineTo( 2 * w , 0);
        path.lineTo( 2 * w , w);
        path.lineTo( w , 0);
        path.close();

        // Draw the yellow ribbon
        canvas.drawPath(path, accentPaint);
        // Draw the star on top
        canvas.drawBitmap(starBitmap, starLeft, starTop, whitePaint);
    }

}
