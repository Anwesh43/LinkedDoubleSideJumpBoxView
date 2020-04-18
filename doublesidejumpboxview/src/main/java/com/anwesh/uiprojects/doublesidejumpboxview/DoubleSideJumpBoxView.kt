package com.anwesh.uiprojects.doublesidejumpboxview

/**
 * Created by anweshmishra on 19/04/20.
 */

import android.view.View
import android.view.MotionEvent
import android.app.Activity
import android.content.Context
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Canvas
import android.graphics.Color

val nodes : Int = 5
val delay : Long = 20
val scGap : Float = 0.02f
val strokeFactor : Int = 90
val sizeFactor : Float = 2.9f
val foreColor : Int = Color.parseColor("#2196F3")
val backColor : Int = Color.parseColor("#BDBDBD")
val rFactor : Float = 5.6f

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.sinify() : Float = Math.sin(this * Math.PI).toFloat()

fun Canvas.drawDoubleSideJumpBox(scale : Float, size : Float, w : Float, paint : Paint) {
    val r : Float = size / rFactor
    val sf : Float = scale.sinify()
    val sc1 : Float = sf.divideScale(0, 1)
    val sc2 : Float = sf.divideScale(1, 2)
    save()
    translate((w / 2 - size) * sc1, 0f)
    drawRect(RectF(0f, -size / 2, size, size / 2), paint)
    save()
    translate(size + r + (w / 2 - 2 * r) * sc2, 0f)
    drawCircle(0f, 0f, r, paint)
    restore()
    restore()
}

fun Canvas.drawDSJBNode(i : Int , scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    val gap : Float = h / (nodes + 1)
    val size : Float = gap / sizeFactor
    paint.color = foreColor
    paint.strokeCap = Paint.Cap.ROUND
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    save()
    translate(0f, gap * (i + 1))
    drawDoubleSideJumpBox(scale, size, w, paint)
    restore()
}

class DoubleSideJumpBoxView(ctx : Context) : View(ctx) {

    override fun onDraw(canvas : Canvas) {

    }

    override fun onTouchEvent(event : MotionEvent) : Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }
}