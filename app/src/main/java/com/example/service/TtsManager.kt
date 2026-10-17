package com.example.service

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import com.example.data.model.Language
import java.util.Locale

class TtsManager(context: Context) : TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = TextToSpeech(context.applicationContext, this)
    private var isInitialized = false

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            isInitialized = true
            tts?.language = Locale.ENGLISH
        } else {
            Log.e("TtsManager", "TTS initialization failed: $status")
        }
    }

    fun speak(text: String, language: Language = Language.ENGLISH) {
        if (!isInitialized || tts == null) return

        val locale = when (language) {
            Language.ENGLISH -> Locale.ENGLISH
            Language.HINDI -> Locale("hi", "IN")
            Language.KANNADA -> Locale("kn", "IN")
        }

        val available = tts?.isLanguageAvailable(locale)
        if (available == TextToSpeech.LANG_AVAILABLE || available == TextToSpeech.LANG_COUNTRY_AVAILABLE) {
            tts?.language = locale
        } else {
            tts?.language = Locale.ENGLISH
        }

        // Clean out markdown asterisks and URLs for spoken audio
        val cleanText = text
            .replace("*", "")
            .replace("#", "")
            .replace("https://", "")
            .replace("http://", "")

        tts?.speak(cleanText, TextToSpeech.QUEUE_FLUSH, null, "scamshield_tts_id")
    }

    fun stop() {
        tts?.stop()
    }

    fun shutdown() {
        tts?.stop()
        tts?.shutdown()
        tts = null
    }
}
