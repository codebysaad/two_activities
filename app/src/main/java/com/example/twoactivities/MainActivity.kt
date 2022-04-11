package com.example.twoactivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.twoactivities.databinding.ActivityMainBinding

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ")

        if (savedInstanceState != null){
            val isVisible = savedInstanceState.getBoolean("visible")

            if (isVisible) {
                viewBinding.apply {
                    titleMessageMain.visibility = View.VISIBLE
                    bodyMessageMain.visibility = View.VISIBLE
                    bodyMessageMain.text = savedInstanceState.getString("reply_text")
                }
            }
        }

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btnSend.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            val message = viewBinding.edtMain.text.toString()
            intent.putExtra(SecondActivity.EXTRA_MESSAGE, message)
            startActivityForResult(intent, TEXT_REQUEST)
        }

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if(viewBinding.titleMessageMain.visibility == View.VISIBLE) {
            outState.putBoolean("visible", true)
            outState.putString("reply_text", viewBinding.bodyMessageMain.text.toString())
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == TEXT_REQUEST && resultCode == RESULT_OK) {
            val headline = getString(R.string.reply_message)
            val message = data?.getStringExtra(EXTRA_MESSAGE)
            Log.d("Main", message.toString())
            viewBinding.titleMessageMain.apply {
                text = headline
                visibility = View.VISIBLE
            }
            viewBinding.bodyMessageMain.text = message
        }
    }

    companion object {
        const val TEXT_REQUEST = 1
        const val EXTRA_MESSAGE = "Main_Activity"
        const val TAG = "MainActivity"
    }
}