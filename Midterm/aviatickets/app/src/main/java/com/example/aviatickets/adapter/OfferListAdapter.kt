package com.example.aviatickets.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.aviatickets.R
import com.example.aviatickets.databinding.ItemOfferBinding
import com.example.aviatickets.model.entity.Offer




class OfferDiffCallback(private val oldList: List<Offer>, private val newList: List<Offer>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}

class OfferListAdapter : RecyclerView.Adapter<OfferListAdapter.ViewHolder>() {

    private val items: ArrayList<Offer> = arrayListOf()


    fun setItems(offerList: List<Offer>) {
        val diffResult = DiffUtil.calculateDiff(OfferDiffCallback(items, offerList))
        items.clear()
        items.addAll(offerList)
        diffResult.dispatchUpdatesTo(this)

        /**
         * DOne DOne DONNE
         */
    }
    private fun calculateDiff(newList: List<Offer>): DiffUtil.DiffResult {
        val diffCallback = OfferDiffCallback(items, newList)
        return DiffUtil.calculateDiff(diffCallback)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemOfferBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(
        private val binding: ItemOfferBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val context = binding.root.context

        fun bind(offer: Offer) {
            val flight = offer.flight

            with(binding) {
                departureTime.text = flight.departureTimeInfo
                arrivalTime.text = flight.arrivalTimeInfo
                route.text = context.getString(
                    R.string.route_fmt,
                    flight.departureLocation.code,
                    flight.arrivalLocation.code
                )
                duration.text = context.getString(
                    R.string.time_fmt,
                    getTimeFormat(flight.duration).first.toString(),
                    getTimeFormat(flight.duration).second.toString()
                )
                direct.text = context.getString(R.string.direct)
                price.text = context.getString(R.string.price_fmt, offer.price.toString())
            }
            /**
            Glide.with(context)
                .load(getAirlineImageUrl(offer.airlineCode)) // Replace getAirlineImageUrl() with your method to get the airline image URL
                .placeholder(R.drawable.placeholder_image) // Placeholder image while loading
                .error(R.drawable.error_image) // Error image if loading fails
                .into(binding.airlineImageView)
            */
        }


        private fun getTimeFormat(minutes: Int): Pair<Int, Int> = Pair(
            first = minutes / 60,
            second = minutes % 60
        )
        }
        /**
        private fun getAirlineImageUrl(airlineCode: String): String {
            // You need to implement logic to fetch the image URL based on the airline code
            // Return the appropriate URL for each airline
            // For example:
            when (airlineCode) {
                "AirAstana" -> return "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRWt694m1U-8jmUCWOO-6C_HkhrX_0KMIL0RQWpEMZB9JggERgZOtiEHm94gpts4ZB6cbs&usqp=CAU"
                "BA" -> return "url_for_airline_BA"
                // Add more cases as needed
                else -> return "" // Return default or empty URL
            }
        }                      по крайнее мерее я старался
        */
        private class OfferDiffCallback(
            private val oldList: List<Offer>,
            private val newList: List<Offer>
        ) : DiffUtil.Callback() {

            override fun getOldListSize(): Int = oldList.size

            override fun getNewListSize(): Int = newList.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldList[oldItemPosition].id == newList[newItemPosition].id
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldList[oldItemPosition] == newList[newItemPosition]
            }

        }
    }


