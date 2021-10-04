package kg.geek.sheepselector

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.geek.sheepselector.databinding.ItemSheepBinding

class MainAdapter(private var onClick: OnItemClick?) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var list = arrayListOf<Uri>()

    fun setList(list: ArrayList<Uri>) {
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

        fun onBind(get: Uri) {
            binding.imageView.setImageURI(get)

            binding.imageView.setOnClickListener {
                if (binding.imageView.colorFilter == null) {
                    binding.imageView.setColorFilter(R.color.black_60)
                    onClick?.onClick(get)
                    binding.cvSelectBg.visibility = View.VISIBLE
                    binding.icCheck.visibility = View.VISIBLE
                } else {
                    binding.imageView.colorFilter = null
                    onClick?.deleteClick(get)
                    binding.cvSelectBg.visibility = View.INVISIBLE
                    binding.icCheck.visibility = View.INVISIBLE
                }
            }
        }
    }

    interface OnItemClick {
        fun onClick(image: Uri)

        fun deleteClick(image: Uri)
    }
}