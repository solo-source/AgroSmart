package com.example.myapplicationsem2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationsem2.databinding.ActivityCropInfoBinding
import com.example.myapplicationsem2.databinding.ItemCropBinding

class CropInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCropInfoBinding
    private val cropList = generateCropList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCropInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up back button click listener
        binding.backButton.setOnClickListener {
            onBackPressed()
        }

        // Set up RecyclerView
        binding.cropListRecyclerView.adapter = CropListAdapter(cropList)
    }

    private fun generateCropList(): List<Crop> {
        // Generate sample crop data
        // Replace this with your actual crop data
        val crops = mutableListOf<Crop>()
        crops.add(
            Crop(
                "Apples",
                "What is Lorem Ipsum?\n" +
                        "\n" +
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                R.drawable.appleimg
            )
        )
        crops.add(
            Crop(
                "Corn",
                "What is Lorem Ipsum?\n" +
                        "\n" +
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                R.drawable.cornimg
            )
        )
        crops.add(
            Crop(
                "Tomato",
                "What is Lorem Ipsum?\n" +
                        "\n" +
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                R.drawable.tomato
            )
        )
        // Add more crops as needed
        crops.add(
            Crop(
                "Onion",
                "What is Lorem Ipsum?\n" +
                        "\n" +
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                R.drawable.onions
            )
        )
        crops.add(
            Crop(
                "Alphanso Mango",
                "What is Lorem Ipsum?\n" +
                        "\n" +
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                R.drawable.alphonsomango
            )
        )
        crops.add(
            Crop(
                "Wheat",
                "What is Lorem Ipsum?\n" +
                        "\n" +
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                R.drawable.wheat
            )
        )
        crops.add(
            Crop(
                "Eggplant",
                "What is Lorem Ipsum?\n" +
                        "\n" +
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                R.drawable.eggplant
            )
        )
        crops.add(
            Crop(
                "Okra",
                "What is Lorem Ipsum?\n" +
                        "\n" +
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                R.drawable.okra
            )
        )
        crops.add(
            Crop(
                "Cabbage",
                "What is Lorem Ipsum?\n" +
                        "\n" +
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                R.drawable.cabbage
            )
        )
        crops.add(
            Crop(
                "Radish",
                "What is Lorem Ipsum?\n" +
                        "\n" +
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                R.drawable.radish
            )
        )
        crops.add(
            Crop(
                "Bajra",
                "What is Lorem Ipsum?\n" +
                        "\n" +
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                R.drawable.bajra
            )
        )
        crops.add(
            Crop(
                "Crop 12",
                "What is Lorem Ipsum?\n" +
                        "\n" +
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                R.drawable.defaultcrop128
            )
        )
        return crops
    }

    data class Crop(val name: String, val description: String, val imageResId: Int)

    inner class CropListAdapter(private val crops: List<Crop>) :
        RecyclerView.Adapter<CropListAdapter.CropViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CropViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemCropBinding.inflate(inflater, parent, false)
            return CropViewHolder(itemBinding)
        }

        override fun onBindViewHolder(holder: CropViewHolder, position: Int) {
            val crop = crops[position]
            holder.bind(crop)
        }

        override fun getItemCount(): Int {
            return crops.size
        }

        inner class CropViewHolder(private val itemBinding: ItemCropBinding) :
            RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {

            init {
                itemBinding.root.setOnClickListener(this)
            }

            fun bind(crop: Crop) {
                itemBinding.cropImage.setImageResource(crop.imageResId)
                itemBinding.cropName.text = crop.name
                itemBinding.cropDescription.text = crop.description
                // Set visibility of detailed information initially to GONE
                itemBinding.detailedInfo.visibility = View.GONE
            }

            override fun onClick(view: View?) {
                // Toggle visibility of detailed information on item click
                val isVisible = itemBinding.detailedInfo.visibility == View.VISIBLE
                itemBinding.detailedInfo.visibility = if (isVisible) View.GONE else View.VISIBLE
            }
        }
    }
}
