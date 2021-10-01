package kg.geek.sheepselector

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.geek.sheepselector.databinding.ItemSheepBinding
import java.util.ArrayList

class MainAdapter(private var onClick: OnItemClick?) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var list = arrayListOf<Int>()

    fun setList(list: ArrayList<Int>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemSheepBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(private var binding: ItemSheepBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(get: Int) {
            binding.imageView.setImageResource(get)

            binding.imageView.setOnClickListener {
                if (binding.imageView.colorFilter == null) {
                    binding.imageView.setColorFilter(R.color.black)
                    onClick?.onClick(get)
                } else {
                    binding.imageView.colorFilter = null
                    onClick?.deleteClick(get)
                }
            }
        }
    }

    interface OnItemClick {
        fun onClick(image: Int)

        fun deleteClick(image: Int)
    }
}