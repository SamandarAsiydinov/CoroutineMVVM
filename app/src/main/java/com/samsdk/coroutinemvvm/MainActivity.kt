package com.samsdk.coroutinemvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.ViewModelProvider
import com.samsdk.coroutinemvvm.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var imageView: AppCompatImageView
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

    }

    private fun initViews() {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        imageView = findViewById(R.id.imageview)

        viewModel.getImageObserver().observe(this) { bitmap ->
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap)
            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.makeApiCall("4")
    }
}