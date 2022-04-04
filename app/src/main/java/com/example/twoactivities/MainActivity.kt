package com.example.twoactivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.twoactivities.databinding.ActivityMainBinding

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btnSend.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            val message = viewBinding.edtMain.text.toString()
            intent.putExtra(SecondActivity.EXTRA_MESSAGE, message)
            startActivityForResult(intent, TEXT_REQUEST)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == TEXT_REQUEST && resultCode == RESULT_OK) {
            val headline = getString(R.string.reply_message)
            val message = data?.getStringExtra(EXTRA_MESSAGE)
            Log.d("Main", message.toString())
            viewBinding.titleMessageMain.text = headline
            viewBinding.bodyMessageMain.text = message
        }
    }

    companion object {
        const val TEXT_REQUEST = 1
        const val EXTRA_MESSAGE = "Main_Activity"
    }
}