package kg.geek.sheepselector

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kg.geek.sheepselector.databinding.ActivityFavoritesBinding

class FavoritesActivity : AppCompatActivity() {

    private lateinit var adapter: MainAdapter
    private lateinit var binding: ActivityFavoritesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecycler()
    }

    private fun initRecycler() {
        adapter = MainAdapter(null)
        adapter.setList(intent.getParcelableArrayListExtra<Uri>(MainActivity.IMAGE_KEY) as ArrayList<Uri>)
        binding.rvFavorites.adapter = adapter
    }


}