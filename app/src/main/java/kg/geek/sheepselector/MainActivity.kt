package kg.geek.sheepselector

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import kg.geek.sheepselector.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MainAdapter
    private var secondList = arrayListOf<Uri>()
    private lateinit var getContent: ActivityResultLauncher<String?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getData()
        btnOpenGallery()
        btnFavorites()
    }

    private fun getData() {

        getContent = registerForActivityResult(ActivityResultContracts.GetMultipleContents()) {
            if (it != null) {
                initRecycler(it as ArrayList<Uri>)
            }
        }
        getContent.launch("image/*")
    }

    private fun btnFavorites() {
        val intent = Intent(this, FavoritesActivity::class.java)
        binding.bntStartFavoritesActivity.setOnClickListener {
            intent.putExtra(IMAGE_KEY, secondList)
            startActivity(intent)
        }
    }

    private fun btnOpenGallery() {
        binding.btnGallery.setOnClickListener {
            getContent.launch("image/*")
        }
    }

    private fun initRecycler(list: ArrayList<Uri>) {
        adapter = MainAdapter(object : MainAdapter.OnItemClick {

            override fun onClick(image: Uri) {
                secondList.add(image)
                binding.cardView.visibility = viewShow()
                binding.tvImageCounter.text =
                    String.format("Выбрано ${secondList.size} фотографии")
            }

            override fun deleteClick(image: Uri) {
                secondList.remove(image)
                binding.cardView.visibility = viewShow()
                binding.tvImageCounter.text = String.format("Выбрано ${secondList.size} фотографии")
            }

        })
        adapter.setList(list)
        binding.rvMain.adapter = adapter
    }

    private fun viewShow(): Int {
        return if (secondList.size < 1) {
            0x00000004
        } else {
            0x00000000
        }
    }

    companion object {
        const val IMAGE_KEY = "images"

    }
}