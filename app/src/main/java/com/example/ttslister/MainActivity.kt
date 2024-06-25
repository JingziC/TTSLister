package com.example.ttslister

import android.media.MediaDrm.ErrorCodes
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private lateinit var tts: TextToSpeech
    private lateinit var enginesText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enginesText = findViewById(R.id.tts_engines_text)
        tts = TextToSpeech(this, this,"com.google.android.tts")
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val voices = tts.voices
            val engines = tts.engines
            enginesText.text = "Available TTS Engines: $engines \nAvailable TTS voices: $voices"
        // Engineinfo{name="com.samsung.SMT" in Samsung} and cannot found google tts. Hope it works in other OS
        }
        else {
            enginesText.text = "TTS Initialization failed!"
        }
    }

    override fun onDestroy() {
        if (tts.isSpeaking) tts.stop()
        tts.shutdown()
        super.onDestroy()
    }
}
