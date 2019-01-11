package com.verse.app.verseapp.components

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.support.annotation.ColorRes
import android.support.annotation.Dimension
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatImageView
import android.text.TextPaint
import android.util.AttributeSet
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.verse.app.verseapp.R
import com.verse.app.verseapp.commons.GlideApp
import com.verse.app.verseapp.commons.WindowsUtils


open class AvatarView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    /*
    * Path of them image to be clipped (to be shown)
    * */
    private lateinit var clipPath: Path

    /*
    * Used to set size and color of the member initials
    * */
    private lateinit var textPaint: TextPaint

    /*
    * Used as background of the initials with user specific color
    * */
    private lateinit var paint: Paint

    /*
    * To draw border
    */
    private lateinit var borderPaint: Paint

    /*
    * Shape to be drawn
    * */
    private var shape: Int = 0

    @ColorRes
    private var mBorderColor: Int = -1

    @Dimension
    private var mBorderWidth: Int = 0


    /*
    * We will set it as 2dp
    * */
    private var cornerRadius: Int = 0


    /*
    * Bounds of the canvas in float
    * Used to set bounds of member initial and background
    * */
    private lateinit var rectF: RectF


    init {

        getAttributes(attrs!!)
        initView()
    }


    private fun getAttributes(attrs: AttributeSet) {
        val a = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.AvatarView,
            0, 0
        )

        try {

            /*
            * Get the shape and set shape field accordingly
            * */
            shape = a.getInteger(R.styleable.AvatarView_avatar_shape, CIRCLE)
            mBorderColor = a.getResourceId(R.styleable.AvatarView_avatar_border_color, R.color.colorAccent)
            mBorderWidth = a.getDimensionPixelSize(R.styleable.AvatarView_avatar_border_width, 0)


        } finally {
            a.recycle()
        }
    }

    /*
    * Initialize fields
    * */
    private fun initView() {

        rectF = RectF()
        clipPath = Path()

        cornerRadius = mBorderWidth

        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = ContextCompat.getColor(context, R.color.backgroundColor)
        textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG)
        textPaint.textSize = 16f * resources.displayMetrics.scaledDensity
        textPaint.color = ContextCompat.getColor(context, mBorderColor)

        borderPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        borderPaint.style = Paint.Style.STROKE
        borderPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_OVER)
        borderPaint.color = ContextCompat.getColor(context, mBorderColor)
        borderPaint.strokeWidth = mBorderWidth.toFloat()


        setPadding(
            mBorderWidth + WindowsUtils.convertDpToPixel(1),
            mBorderWidth + WindowsUtils.convertDpToPixel(1),
            mBorderWidth + WindowsUtils.convertDpToPixel(1),
            mBorderWidth + WindowsUtils.convertDpToPixel(1)
        )
    }


    fun setPlaceHolder(text: String) {

        GlideApp.with(context)
            .load(getDrawable(text)).override(this.width, this.height)
            .apply(RequestOptions.circleCropTransform()).into(this)
    }

    @SuppressLint("CheckResult")
    fun setUrl(url: String?, nick: String) {

        if (url == null) {
            setPlaceHolder(nick)
        } else {
            GlideApp.with(context)
                .load(url).error(getDrawable(nick)).fitCenter().override(this.width, this.height)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .apply(RequestOptions.circleCropTransform()).into(this)
        }
    }


    /*
    * Create placeholder drawable
    * */
    private fun getDrawable(text: String): Drawable {
        return object : Drawable() {
            override fun draw(canvas: Canvas) {

                val sb = StringBuilder()
                for (s in text.split(" ")) {
                    sb.append(s[0])

                    if (sb.length == 2)
                        break
                }


                val centerX = bounds.exactCenterX()
                val centerY = bounds.exactCenterY()

                val textWidth: Float = textPaint.measureText(sb.toString().toUpperCase()) * 0.5f
                val textBaseLineHeight: Float = textPaint.fontMetrics.ascent * -0.4f

                when (shape) {
                    RECTANGLE -> {
                        canvas.drawRoundRect(rectF, cornerRadius.toFloat(), cornerRadius.toFloat(), paint)
                    }
                    CIRCLE -> {
                        canvas.drawCircle(
                            centerX,
                            centerY,
                            bounds.width() / 2F,
                            paint
                        )
                    }
                }


                canvas.drawText(
                    sb.toString().toUpperCase(),
                    centerX - textWidth,
                    centerY + textBaseLineHeight,
                    textPaint
                )

            }

            override fun setAlpha(alpha: Int) {

            }

            override fun setColorFilter(colorFilter: ColorFilter?) {

            }

            override fun getOpacity(): Int {
                return PixelFormat.UNKNOWN
            }
        }
    }

    /*
    * Set the canvas bounds here
    * */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val screenWidth = MeasureSpec.getSize(widthMeasureSpec)
        val screenHeight = MeasureSpec.getSize(heightMeasureSpec)
        rectF.set(0f, 0f, screenWidth.toFloat(), screenHeight.toFloat())
    }

    override fun onDraw(canvas: Canvas) {

        when (shape) {
            RECTANGLE -> {
                canvas.drawRoundRect(rectF, cornerRadius.toFloat(), cornerRadius.toFloat(), borderPaint)
                clipPath.addRoundRect(rectF, cornerRadius.toFloat(), cornerRadius.toFloat(), Path.Direction.CW)
            }
            CIRCLE -> {
                canvas.drawCircle(
                    rectF.centerX(),
                    rectF.centerY(),
                    rectF.height() / 2 - borderPaint.strokeWidth,
                    borderPaint
                )
                clipPath.addCircle(rectF.centerX(), rectF.centerY(), rectF.height() / 2, Path.Direction.CW)
            }
        }

        canvas.clipPath(clipPath)
        super.onDraw(canvas)
    }

    companion object {

        /*
    * Constants to define shape
    * */
        protected const val CIRCLE = 0
        protected const val RECTANGLE = 1
    }
}