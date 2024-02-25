package com.example.dodopizza

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import com.example.dodopizza.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    companion object {
        const val KEY_PIZZA_NAME = "KeyPizzaName"
        const val KEY_PIZZA_DESCRIPTION = "KeyPizzaDescription"
        const val KEY_PIZZA_SIZE = "KeyPizzaSize"
        const val KEY_PIZZA_IMAGE = "KeyPizzaImage"
    }

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val pizzaName = intent.getStringExtra(KEY_PIZZA_NAME)
        val pizzaSize = intent.getStringExtra(KEY_PIZZA_SIZE)
        val pizzaDescr = intent.getStringExtra(KEY_PIZZA_DESCRIPTION)
        val pizzaImage = intent.getIntExtra(KEY_PIZZA_IMAGE, -1)

        binding.name.text = pizzaName
        binding.size.text = pizzaSize
        binding.ingredients.text = pizzaDescr
        binding.inPizzaImage.setImageResource(pizzaImage)
    }
}