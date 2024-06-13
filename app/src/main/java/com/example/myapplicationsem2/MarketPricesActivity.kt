package com.example.myapplicationsem2
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        prices.add(MarketPrice("Crop 1", "Description of Crop 1", R.drawable.defaultcrop128, "₹120", generateSampleData()))
        prices.add(MarketPrice("Crop 2", "Description of Crop 2", R.drawable.defaultcrop128, "₹115", generateSampleData()))
        prices.add(MarketPrice("Crop 3", "Description of Crop 3", R.drawable.defaultcrop128, "₹110", generateSampleData()))
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

    data class MarketPrice(val name: String, val description: String, val imageResId: Int, val currentPrice: String, val priceData: List<Entry>)

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
                itemBinding.cropImage.setImageResource(marketPrice.imageResId)
                itemBinding.cropName.text = marketPrice.name
                itemBinding.cropDescription.text = marketPrice.description
                itemBinding.currentPrice.text = marketPrice.currentPrice
                itemBinding.detailedInfo.visibility = View.GONE
                setupChart(itemBinding.priceChart, marketPrice.priceData)
            }

            override fun onClick(view: View?) {
                val isVisible = itemBinding.detailedInfo.visibility == View.VISIBLE
                itemBinding.detailedInfo.visibility = if (isVisible) View.GONE else View.VISIBLE
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
