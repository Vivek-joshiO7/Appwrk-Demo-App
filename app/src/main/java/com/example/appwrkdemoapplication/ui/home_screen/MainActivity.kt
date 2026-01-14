package com.example.appwrkdemoapplication.ui.home_screen

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appwrkdemoapplication.R
import com.example.appwrkdemoapplication.data.local.entity.TrainingModule
import com.example.appwrkdemoapplication.databinding.ActivityMainBinding
import com.example.appwrkdemoapplication.ui.common.TrainingViewModel
import com.example.appwrkdemoapplication.ui.detial_screen.DetailsActivity
import com.example.appwrkdemoapplication.ui.home_screen.adapter.TrainingAdapter

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: TrainingViewModel by viewModels()
    private lateinit var trainingAdapter: TrainingAdapter

    private val moduleList = listOf(
        TrainingModule(
            title = "Kotlin Basics",
            description = "Learn Kotlin fundamentals"
        ),
        TrainingModule(
            title = "MVVM Architecture",
            description = "Understand MVVM in Android"
        ),
        TrainingModule(
            title = "Room Database",
            description = "Local data persistence with Room"
        )
    )


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
        /**this will add data only once to database*/
        viewModel.insertDefaultData(moduleList)


        with(binding) {
            observeAll()
            selectCard(cardAll)

            cardAll.setOnClickListener {
                observeAll()
                selectCard(cardAll)
            }

            cardCompleted.setOnClickListener {
                observeCompleted()
                selectCard(cardCompleted)
            }

            cardPending.setOnClickListener {
                observePending()
                selectCard(cardPending)
            }
        }

    }

    private fun observers() {
        viewModel.modules.observe(this) {
            trainingAdapter.submitList(it)
        }
    }

    private fun listeners() {
        trainingAdapter.onClick = {
            startActivity(
                Intent(this, DetailsActivity::class.java)
                    .putExtra("MODULE_ID", it.id)
            )
            finish()
        }
    }

    private fun initObject() {
        trainingAdapter = TrainingAdapter()
        binding.rvTrainingModules.adapter = trainingAdapter
    }

    private fun selectCard(selected: CardView) {
        val cards = listOf(binding.cardAll, binding.cardCompleted, binding.cardPending)

        cards.forEach {
            it.setCardBackgroundColor(getColor(R.color.white))
        }
        selected.setCardBackgroundColor(getColor(R.color.green_lite))
    }

    private fun observeAll() {
        viewModel.allModules.observe(this) {
            trainingAdapter.submitList(it)
        }
    }

    private fun observeCompleted() {
        viewModel.getCompleted().observe(this) {
            trainingAdapter.submitList(it)
        }
    }

    private fun observePending() {
        viewModel.getPending().observe(this) {
            trainingAdapter.submitList(it)
        }
    }
}