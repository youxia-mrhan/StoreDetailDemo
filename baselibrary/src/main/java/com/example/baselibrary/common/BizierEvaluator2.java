package com.example.baselibrary.common;

import android.animation.TypeEvaluator;
import android.graphics.Point;

/**
 * 贝塞尔曲线（二阶抛物线）
 * controllPoint 是中间的转折点
 * startValue 是起始的位置
 * endValue 是结束的位置
 */
public class BizierEvaluator2 implements TypeEvaluator<Point> {

    private Point controllPoint;

    public BizierEvaluator2(Point controllPoint) {
        this.controllPoint = controllPoint;
    }

    @Override
    public Point evaluate(float t, Point startValue, Point endValue) {
        int x = (int) ((1 - t) * (1 - t) * startValue.x + 2 * t * (1 - t) * controllPoint.x + t * t * endValue.x);
        int y = (int) ((1 - t) * (1 - t) * startValue.y + 2 * t * (1 - t) * controllPoint.y + t * t * endValue.y);
        return new Point(x, y);
    }
}