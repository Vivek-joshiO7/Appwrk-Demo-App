package com.example.appwrkdemoapplication.ui.detial_screen

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appwrkdemoapplication.data.local.entity.TrainingModule
import com.example.appwrkdemoapplication.databinding.ActivityDetailsBinding
import com.example.appwrkdemoapplication.ui.common.TrainingViewModel
import com.example.appwrkdemoapplication.ui.home_screen.MainActivity

class DetailsActivity : AppCompatActivity() {
    val binding by lazy { ActivityDetailsBinding.inflate(layoutInflater) }
    private val viewModel: TrainingViewModel by viewModels()
    private var moduleId = -1
    private var currentModule: TrainingModule? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initObject()
        listeners()
        observers()
    }

    private fun observers() {
        viewModel.getModuleById(moduleId)
            .observe(this) { module ->
                currentModule = module

                with(binding) {
                    tvItemTitle.text = module.title
                    tvItemDescription.text = module.description
                    tvStatus.text =
                        if (module.isCompleted) "Current Status : Completed" else "Current Status : Pending"
                    btnToggleStatus.text =
                        if (module.isCompleted) "Mark as Pending"
                        else "Mark as Completed"
                }

            }
    }

    private fun listeners() {
        binding.tvBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {

                override fun handleOnBackPressed() {
                    startActivity(Intent(this@DetailsActivity, MainActivity::class.java))
                }
            })


        binding.btnToggleStatus.setOnClickListener {
            currentModule?.let { module ->
                val updatedModule = module.copy(
                    isCompleted = !module.isCompleted
                )
                viewModel.updateStatus(updatedModule)
                finish()
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }

    private fun initObject() {
        moduleId = intent.getIntExtra("MODULE_ID", -1)

    }
}