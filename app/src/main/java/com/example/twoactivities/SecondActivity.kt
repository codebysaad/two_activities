package com.example.twoactivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.twoactivities.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val message = intent.getStringExtra(EXTRA_MESSAGE)
        binding.bodyMessageSecond.text = message

        binding.btnReply.setOnClickListener {
            val intent = Intent()
            val replyMessage = binding.edtSecond.text.toString()
            Log.d("ReplyMessage", replyMessage)
            intent.putExtra(MainActivity.EXTRA_MESSAGE, replyMessage)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    companion object {
        const val EXTRA_MESSAGE = "Second_Activity"
    }
}