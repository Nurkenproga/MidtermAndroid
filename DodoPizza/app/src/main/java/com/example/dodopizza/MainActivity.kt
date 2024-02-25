package com.example.dodopizza

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.dodopizza.adapter.PizzaAdapter
import com.example.dodopizza.databinding.ActivityMainBinding
import com.example.dodopizza.model.Pizza
import com.example.dodopizza.model.PizzaDataSource


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var adapter: PizzaAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        fun handlePizzaClick(pizza: Pizza) {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra(SecondActivity.KEY_PIZZA_NAME, pizza.name)
            intent.putExtra(SecondActivity.KEY_PIZZA_SIZE, pizza.size)
            intent.putExtra(SecondActivity.KEY_PIZZA_DESCRIPTION, pizza.description)
            intent.putExtra(SecondActivity.KEY_PIZZA_IMAGE, pizza.imageRes)
            startActivity(intent)
        }

        PizzaAdapter(
            onPizzaClick = {
                handlePizzaClick(it)
            }
        )

        adapter = PizzaAdapter(
            onPizzaClick = {
                handlePizzaClick(it)
            }
        )


        binding.recyclerView.adapter = adapter

        adapter?.setPizzaData(PizzaDataSource.pizzaList)

        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter!!.search(s.toString()) // Вызываем метод поиска адаптера при изменении текста
            }
        })

    }
}