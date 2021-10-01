package kg.geek.sheepselector

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
        intent.getIntegerArrayListExtra(MainActivity.IMAGE_KEY)?.let { adapter.setList(it) }
        binding.rvFavorites.adapter = adapter
    }


}