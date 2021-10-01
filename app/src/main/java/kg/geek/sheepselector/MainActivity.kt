package kg.geek.sheepselector

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kg.geek.sheepselector.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MainAdapter
    private var list = arrayListOf<Int>()
    private var secondList = arrayListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createList()
        initRecycler()
        btnFavorites()
    }

    private fun btnFavorites() {
        val intent = Intent(this, FavoritesActivity::class.java)
        binding.btnStartSecondActivity.setOnClickListener {
            intent.putExtra("images", secondList)
            startActivity(intent)
        }
    }

    private fun createList() {
        for (i in 1..30){
            list.add(R.drawable.img_third_sheep)
            list.add(R.drawable.img)
            list.add(R.drawable.img_second)
        }
    }

    private fun initRecycler() {
        adapter = MainAdapter(object : MainAdapter.OnItemClick{

            override fun onClick(image: Int) {
                secondList.add(image)
            }

            override fun deleteClick(image: Int) {
                secondList.remove(image)
            }

        })
        adapter.setList(list)
        binding.rvMain.adapter = adapter
    }


}