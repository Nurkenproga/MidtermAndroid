package com.example.dodopizza.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dodopizza.databinding.ItemPizzaBinding
import com.example.dodopizza.model.Pizza

class PizzaAdapter(
    private val onPizzaClick: (Pizza) -> Unit
    ): RecyclerView.Adapter<PizzaAdapter.ViewHolder>() {

    private val pizzaList: ArrayList<Pizza> = ArrayList()
    private var originalPizzaList: List<Pizza> = emptyList()


    fun setPizzaData(pizzas: MutableList<Pizza>) {
        originalPizzaList = pizzas.toList()
        pizzaList.clear()
        pizzaList.addAll(pizzas)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPizzaBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount() = pizzaList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(pizzaList[position])
    }

    fun search(query: String) {
        val filteredPizzaList = if (query.isBlank()) {
            originalPizzaList
        } else {
            originalPizzaList.filter { pizza ->
                pizza.name.contains(query, ignoreCase = true) || pizza.description.contains(query, ignoreCase = true)
            }
        }
        setPizzaData(filteredPizzaList.toMutableList())

    }


    inner class ViewHolder(
        private val binding: ItemPizzaBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(pizza: Pizza) {
            with(binding){
                pizzaImage.setImageResource(pizza.imageRes)
                pizzaName.text=pizza.name
                pizzaPrice.text=pizza.price
                pizzaDescription.text=pizza.description

                root.setOnClickListener{
                    onPizzaClick(pizza)
                }
            }


        }
    }


}