package com.example.myapplicationsem2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationsem2.databinding.ActivityMarketPricesBinding
import com.example.myapplicationsem2.databinding.ItemMarketPriceBinding
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class MarketPricesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMarketPricesBinding
    private val marketPrices = generateMarketPrices()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMarketPricesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up back button click listener
        binding.backButton.setOnClickListener {
            onBackPressed()
        }

        // Set up RecyclerView
        binding.marketPricesRecyclerView.adapter = MarketPricesAdapter(marketPrices)
    }

    private fun generateMarketPrices(): List<MarketPrice> {
        // Generate sample market price data
        val prices = mutableListOf<MarketPrice>()
        prices.add(
            MarketPrice(
                "Crop 1",
                "What is Lorem Ipsum?\n" +
                        "\n" +
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                R.drawable.defaultcrop128,
                "₹120",
                generateSampleData()
            )
        )
        prices.add(
            MarketPrice(
                "Crop 2",
                "What is Lorem Ipsum?\n" +
                        "\n" +
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                R.drawable.defaultcrop128,
                "₹115",
                generateSampleData()
            )
        )
        prices.add(
            MarketPrice(
                "Crop 3",
                "What is Lorem Ipsum?\n" +
                        "\n" +
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                R.drawable.defaultcrop128,
                "₹110",
                generateSampleData()
            )
        )
        prices.add(
            MarketPrice(
                "Crop 4",
                "What is Lorem Ipsum?\n" +
                        "\n" +
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                R.drawable.defaultcrop128,
                "₹120",
                generateSampleData()
            )
        )
        prices.add(
            MarketPrice(
                "Crop 5",
                "What is Lorem Ipsum?\n" +
                        "\n" +
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                R.drawable.defaultcrop128,
                "₹120",
                generateSampleData()
            )
        )
        prices.add(
            MarketPrice(
                "Crop 6",
                "What is Lorem Ipsum?\n" +
                        "\n" +
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                R.drawable.defaultcrop128,
                "₹120",
                generateSampleData()
            )
        )
        prices.add(
            MarketPrice(
                "Crop 7",
                "What is Lorem Ipsum?\n" +
                        "\n" +
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                R.drawable.defaultcrop128,
                "₹120",
                generateSampleData()
            )
        )
        prices.add(
            MarketPrice(
                "Crop 8",
                "What is Lorem Ipsum?\n" +
                        "\n" +
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                R.drawable.defaultcrop128,
                "₹120",
                generateSampleData()
            )
        )
        prices.add(
            MarketPrice(
                "Crop 9",
                "What is Lorem Ipsum?\n" +
                        "\n" +
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                R.drawable.defaultcrop128,
                "₹120",
                generateSampleData()
            )
        )
        prices.add(
            MarketPrice(
                "Crop 10",
                "What is Lorem Ipsum?\n" +
                        "\n" +
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                R.drawable.defaultcrop128,
                "₹120",
                generateSampleData()
            )
        )
        prices.add(
            MarketPrice(
                "Crop 11",
                "What is Lorem Ipsum?\n" +
                        "\n" +
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                R.drawable.defaultcrop128,
                "₹120",
                generateSampleData()
            )
        )
        prices.add(
            MarketPrice(
                "Crop 12",
                "What is Lorem Ipsum?\n" +
                        "\n" +
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                R.drawable.defaultcrop128,
                "₹120",
                generateSampleData()
            )
        )
        // Add more crops as needed
        return prices
    }

    private fun generateSampleData(): List<Entry> {
        val data = mutableListOf<Entry>()
        data.add(Entry(0f, 1f))
        data.add(Entry(1f, 2f))
        data.add(Entry(2f, 1.5f))
        data.add(Entry(3f, 2.5f))
        data.add(Entry(4f, 2f))
        return data
    }

    data class MarketPrice(
        val name: String,
        val description: String,
        val imageResId: Int,
        val currentPrice: String,
        val priceData: List<Entry>
    )

    inner class MarketPricesAdapter(private val marketPrices: List<MarketPrice>) :
        RecyclerView.Adapter<MarketPricesAdapter.MarketPriceViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketPriceViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemMarketPriceBinding.inflate(inflater, parent, false)
            return MarketPriceViewHolder(itemBinding)
        }

        override fun onBindViewHolder(holder: MarketPriceViewHolder, position: Int) {
            val marketPrice = marketPrices[position]
            holder.bind(marketPrice)
        }

        override fun getItemCount(): Int {
            return marketPrices.size
        }

        inner class MarketPriceViewHolder(private val itemBinding: ItemMarketPriceBinding) :
            RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {

            init {
                itemBinding.root.setOnClickListener(this)
            }

            fun bind(marketPrice: MarketPrice) {
                itemBinding.cropImageMPrice.setImageResource(marketPrice.imageResId)
                itemBinding.cropNameMPrice.text = marketPrice.name
                itemBinding.cropDescriptionMPrice.text = marketPrice.description
                itemBinding.currentPriceMPrice.text = marketPrice.currentPrice
                itemBinding.detailedInfoMprice.visibility = View.GONE
                setupChart(itemBinding.priceChart, marketPrice.priceData)
                //Toast.makeText(this@MarketPricesActivity, marketPrice.currentPrice, Toast.LENGTH_LONG).show()
            }

            override fun onClick(view: View?) {
                val isVisible = itemBinding.detailedInfoMprice.visibility == View.VISIBLE
                itemBinding.detailedInfoMprice.visibility = if (isVisible) View.GONE else View.VISIBLE
            }

            private fun setupChart(chart: LineChart, data: List<Entry>) {
                val lineDataSet = LineDataSet(data, "Price Trend")
                lineDataSet.color = resources.getColor(R.color.colorPrimary)
                lineDataSet.valueTextColor = resources.getColor(R.color.black)

                val lineData = LineData(lineDataSet)
                chart.data = lineData
                chart.invalidate() // refresh
            }
        }
    }
}
