@file:Suppress("NOTHING_TO_INLINE")

package utils

import kotlin.math.*

class Point2D {
    var x: Double
    var y: Double

    inline val intX: Int
        get() = x.toInt()

    inline val intY: Int
        get() = y.toInt()

    val roundX: Int
        get() = round(x).toInt()

    val roundY: Int
        get() = round(y).toInt()

    val fx: Float
        get() = x.toFloat()

    val fy: Float
        get() = y.toFloat()

    constructor(x: Int, y: Int, `val`: Double) {
        this.x = x.toDouble()
        this.y = y.toDouble()
    }

    override fun toString(): String {
        return "x=" + x.f() +
                ", y=" + y.f()
    }

    constructor(x: Double, y: Double) {
        this.x = x
        this.y = y
    }

    internal constructor(x: Float, y: Float) {
        this.x = x.toDouble()
        this.y = y.toDouble()
    }

    fun getDistanceTo(x: Double, y: Double): Double {
        return getDistance(this.x, this.y, x, y)
    }

    /*   public static double getDistance(double x1, double y1, double x2, double y2) {
           double dx = x1 - x2;
           double dy = y1 - y2;
           return util.FastMath.hypot(dx, dy);
       }
   */
    fun getDistanceTo(point: Point2D): Double {
        return getDistanceTo(point.x, point.y)
    }


    inline fun plus(x: Double, y: Double): Point2D {
        this.x += x
        this.y += y
        return this
    }

    constructor() {
        x = 0.0
        y = 0.0
    }

    constructor(v: Point2D) {
        this.x = v.x
        this.y = v.y
    }

    constructor(angle: Double) {
        this.x = cos(angle)
        this.y = sin(angle)
    }

    constructor(x: Int, y: Int) {
        this.x = x.toDouble()
        this.y = y.toDouble()
    }

    fun copy(): Point2D {
        return Point2D(this)
    }

    operator fun minus(v: Point2D): Point2D {
        return Point2D(x - v.x, y - v.y)
    }

    inline fun minus(dx: Double, dy: Double): Point2D {
        x -= dx
        y -= dy
        return this
    }

    inline fun mul(f: Double): Point2D {
        return Point2D(x * f, y * f)
    }

    inline fun length(): Double {
        //        return hypot(x, y);
        return hypot(x, y)
    }

    inline fun distance(v: Point2D): Double {

        //        return hypot(x - v.x, y - v.y);
        return hypot(x - v.x, y - v.y)
    }

    inline fun sqDistance(v: Point2D): Double {
        val tx = x - v.x
        val ty = y - v.y
        return tx * tx + ty * ty
    }

    inline fun sqDistance(x: Double, y: Double): Double {
        val tx = this.x - x
        val ty = this.y - y
        return tx * tx + ty * ty
    }

    inline fun squareLength(): Double {
        return x * x + y * y
    }

    inline fun reverse(): Point2D {
        return Point2D(-x, -y)
    }

    inline fun normalize(): Point2D {
        val length = this.length()
        return if (length == 0.0) {
            Point2D(0.0, 0.0)
        } else {
            Point2D(x / length, y / length)
        }
    }

    inline fun length(length: Double): Point2D {
        val currentLength = this.length()
        return if (currentLength == 0.0) {
            this
        } else {
            this.mul(length / currentLength)
        }
    }

    inline fun leftPerpendicular(): Point2D {
        return Point2D(y, -x)
    }

    inline fun rightPerpendicular(): Point2D {
        return Point2D(-y, x)
    }

    inline fun dotProduct(vector: Point2D): Double {
        return x * vector.x + y * vector.y
    }

    inline fun angle(): Float {
        //return Math.atan2(y, x);
        return atan2(y.toFloat(), x.toFloat())
    }

    inline fun nearlyEqual(potentialIntersectionPoint: Point2D, epsilon: Double): Boolean {
        return abs(x - potentialIntersectionPoint.x) < epsilon && abs(y - potentialIntersectionPoint.y) < epsilon
    }

    inline fun rotate(angle: Point2D): Point2D {
        val newX = angle.x * x - angle.y * y
        val newY = angle.y * x + angle.x * y
        return Point2D(newX, newY)
    }

    inline fun rotateBack(angle: Point2D): Point2D {
        val newX = angle.x * x + angle.y * y
        val newY = angle.x * y - angle.y * x
        return Point2D(newX, newY)
    }

    inline operator fun div(f: Double): Point2D {
        return Point2D(x / f, y / f)
    }


    inline operator fun plus(point: Point2D): Point2D {
        return plus(point.x, point.y)
    }

    inline fun rotate(angle: Double): Point2D {

        val x1 = (this.x * cos(angle) - this.y * sin(angle)).toFloat()

        val y1 = (this.x * sin(angle) + this.y * cos(angle)).toFloat()

        return Point2D(x1.toDouble(), y1.toDouble())
    }


    inline fun dirTo(pos: Point2D): Direction {
        return when {
            pos.x - 1 == x -> Direction.RIGHT
            pos.x + 1 == x -> Direction.LEFT
            pos.y + 1 == y -> Direction.DOWN
            pos.y - 1 == y -> Direction.UP
            else -> {
                Log.myLog("unable to find dir for $this $pos")
                Direction.UP
            }
        }
    }

    inline fun applyDir(direction: Direction): Point2D {
        return this.plus(getPointByDir(direction))
    }

    inline fun abs(): Point2D {
        if (x < 0) {
            x *= -1
        }
        if (y < 0) {
            y *= -1
        }

        return this
    }

    inline fun max(): Double {
        return max(x, y)
    }

    fun noRoundCopy(): Point2D {
        return Point2D(intX, intY)
    }

    operator fun component1(): Double {
        return x
    }

    operator fun component2(): Double {
        return y
    }
    companion object {
        inline fun getPointByDir(direction: Direction): Point2D {
            return when (direction) {
                Direction.LEFT -> LEFT
                Direction.UP -> UP
                Direction.RIGHT -> RIGHT
                Direction.DOWN -> DOWN
            }
        }


        fun angle(x: Double, y: Double): Float {
            return atan2(y.toFloat(), x.toFloat())
        }


        fun getDistance(x1: Double, y1: Double, x2: Double, y2: Double): Double {
            val dx = x1 - x2
            val dy = y1 - y2
            return sqrt(dx * dx + dy * dy)
        }

        fun vector(fromX: Double, fromY: Double, toX: Double, toY: Double): Point2D {
            return Point2D(toX - fromX, toY - fromY)
        }


        val STUB: Point2D = Point2D(0, 0)
        val ZERO = Point2D(0, 0)
        val UP = Point2D(0, 1)
        val RIGHT = Point2D(1, 0)
        val DOWN = Point2D(0, -1)
        val LEFT = Point2D(-1, 0)
    }
}
