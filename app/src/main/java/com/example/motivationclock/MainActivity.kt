package com.example.motivationclock

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.motivationclock.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val quotesArray: Array<String> by lazy {
        resources.getStringArray(R.array.quotes)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Настройка часов
        setupClock()

        // Настройка цитат
        setupQuotes()
    }

    private fun setupClock() {
        val handler = Handler(Looper.getMainLooper())
        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

        handler.post(object : Runnable {
            override fun run() {
                val currentTime = dateFormat.format(Date())
                binding.textViewClock.text = currentTime
                handler.postDelayed(this, 1000) // обновление каждую секунду
            }
        })
    }

    private fun setupQuotes() {
        val handler = Handler(Looper.getMainLooper())

        handler.post(object : Runnable {
            override fun run() {
                val randomIndex = (0 until quotesArray.size).random()
                binding.textViewQuote.text = quotesArray[randomIndex]
                handler.postDelayed(this, 25000) // обновление каждые 20 секунд
            }
        })
    }
}