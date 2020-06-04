package com.qijik.timer

import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import kotlin.system.exitProcess

//TODO:Normal timer, 2 veya 3 lü timer ve pomodoro seçenekleri eklenecek
class MainActivity : AppCompatActivity() {
    var isFullScreen:Boolean=false
    var isTimerRun=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setBackgroundDrawable(resources.getDrawable(R.drawable.main_background))
        var minute:TextView=findViewById(R.id.minute)
        var second:TextView=findViewById(R.id.second)
        var btnReset:Button=findViewById(R.id.btnReset)
        btnReset.visibility=View.GONE
        var btnStartStop:Button=findViewById(R.id.btnStartStop)
        var startValueMinute=0
        var startValueSecond=0
        val timer = object: CountDownTimer(9223372036854775807.toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                if(isTimerRun==true)
                {
                    startValueSecond++
                }
                if(startValueSecond==60)
                {
                    startValueMinute++
                    startValueSecond=0
                }
                if (startValueSecond<10) {
                    minute.text = startValueMinute.toString()
                    second.text = "0" + startValueSecond.toString()
                }
                if (startValueMinute<10) {
                    minute.text = "0"+startValueMinute.toString()
                    second.text = startValueSecond.toString()
                }
                if (startValueSecond<10 && startValueMinute<10) {
                    minute.text = "0"+startValueMinute.toString()
                    second.text = "0" + startValueSecond.toString()
                }
                if (startValueSecond>=10 && startValueMinute>=10) {
                    minute.text = startValueMinute.toString()
                    second.text = startValueSecond.toString()
                }
                btnReset.setOnClickListener {
                    isTimerRun=false
                    startValueMinute=0
                    startValueSecond=0
                    minute.text="00"
                    second.text="00"
                    btnStartStop.text=getString(R.string.start)
                    btnReset.visibility=View.GONE
                }
                btnStartStop.setOnClickListener{
                   if(isTimerRun==false) {
                       isTimerRun = true
                       btnReset.visibility=View.GONE
                       btnStartStop.text=getString(R.string.stop)
                   }
                   else{
                    isTimerRun=false
                       btnReset.visibility=View.VISIBLE
                       btnStartStop.text=getString(R.string.continuee)
                   }
                }
            }

            override fun onFinish() {

            }
        }
        timer.start()
   }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate( R.menu.menu, menu)
        return true
    }

    override fun onBackPressed() {
        if(isFullScreen==true)
        {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN

            )
            getSupportActionBar()?.show();
            isFullScreen=false
        }
        else{
            moveTaskToBack(true);
            exitProcess(-1)
            super.onBackPressed()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        if (id == R.id.full_screen) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
            getSupportActionBar()?.hide();
            isFullScreen=true
            return true
        }
        return super.onOptionsItemSelected(item)

    }
}

