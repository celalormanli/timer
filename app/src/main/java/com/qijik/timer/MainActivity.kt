package com.qijik.timer

import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

//TODO:Normal timer, 2 veya 3 lü timer ve pomodoro seçenekleri eklenecek
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var timer1:TextView=findViewById(R.id.timer1)
        var startValueMinute=1//TODO: Kullanıcıdan alınacak bu değerler
        var startValueSecond=15
        var totalStartValue=startValueMinute*60000+startValueSecond*1000
        val timer = object: CountDownTimer(totalStartValue.toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                startValueSecond--
                if(startValueSecond<0)
                {
                    startValueMinute--
                    startValueSecond=59
                }
                if (startValueSecond<10) {
                    timer1.text = startValueMinute.toString() + ":0" + startValueSecond.toString()
                }
                if (startValueMinute<10) {
                    timer1.text = "0"+startValueMinute.toString() + ":" + startValueSecond.toString()
                }
                if (startValueSecond<10 && startValueMinute<10) {
                    timer1.text = "0"+startValueMinute.toString() + ":0" + startValueSecond.toString()
                }
                if (startValueSecond>=10 && startValueMinute>=10) {
                    timer1.text = startValueMinute.toString() + ":" + startValueSecond.toString()
                }

            }

            override fun onFinish() {
                startValueSecond--
                timer1.text="0"+startValueMinute.toString()+":0"+startValueSecond.toString()
                val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)//TODO: Müzik Sesi değiştirmer işlemi yapılacak
                val r = RingtoneManager.getRingtone(applicationContext,notification)
                r.play()

            }
        }
        timer.start()
   }
}

