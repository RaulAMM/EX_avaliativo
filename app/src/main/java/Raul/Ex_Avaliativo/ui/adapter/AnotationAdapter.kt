package Raul.Ex_Avaliativo.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import Raul.Ex_Avaliativo.R
import Raul.Ex_Avaliativo.data.model.Anotation
import Raul.Ex_Avaliativo.databinding.AnotationItemBinding
import Raul.Ex_Avaliativo.ui.listeners.AnotationItemClickListener

class AnotationAdapter(private val listener: AnotationItemClickListener) :
    RecyclerView.Adapter<AnotationAdapter.ViewHolder>() {
    // Dataset manipulada pelo adapter
    private var dataset: List<Anotation> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.anotation_item, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val anotation = dataset[position]
        val colorId = if (anotation.isHighPriority()) {
            R.color.red
        } else {
            R.color.green
        }
        val color = ContextCompat.getColor(holder.binding.root.context, colorId)
        holder.binding.textTitle.text = anotation.title
        holder.binding.imagePriority.setColorFilter(color)
        holder.binding.imageDone.setOnClickListener {
            listener.clickDone(position)
        }
        holder.binding.viewAnotation.setOnLongClickListener {
            listener.clickOpen(position)
            true
        }
    }
    override fun getItemCount(): Int {
        return dataset.size
    }
    /**
     * Recebe uma nova fonte de dados e notifica o adapter
     * que houve atualização nos dados.
     */
    fun submitDataset(data: List<Anotation>) {
        dataset = data
        this.notifyDataSetChanged()
    }
    fun getDatasetItem(position: Int): Anotation {
        return dataset[position]
    }
    // Inner class
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: AnotationItemBinding = AnotationItemBinding.bind(view)
    }
}
