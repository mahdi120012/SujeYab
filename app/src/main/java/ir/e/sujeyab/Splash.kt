package ir.e.sujeyab

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import ir.e.sujeyab.pishkhan.Pishkhan

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.setStatusBarColor(Color.parseColor("#FFFFFF"))
        }

 /*       animation_view.playAnimation();
        //animation_view.cancelAnimation();
        animation_view.setRepeatCount(3);// for 3 loops
        val animationSpec = remember { LottieAnimationSpec.RawRes(R.raw.loading) }
        // You can control isPlaying/progress/repeat/etc. with this.
        val animationState = rememberLottieAnimationState(autoPlay = true, repeatCount = Integer.MAX_VALUE)

        LottieAnimation(
                animationSpec,
                animationState,
                modifier = Modifier.preferredSize(100.dp)
        )*/


        Handler().postDelayed(Runnable {
            val intent = Intent(this, Pishkhan::class.java)
            this.startActivity(intent)
            this.finish()
        }, 1000)}}


