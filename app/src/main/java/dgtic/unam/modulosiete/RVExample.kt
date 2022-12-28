package dgtic.unam.modulosiete

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import dgtic.unam.modulosiete.databinding.ActivityRvexampleBinding


class RVExample : AppCompatActivity() {

    private lateinit var binding: ActivityRvexampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        binding=ActivityRvexampleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initItem()
    }

    private fun initItem(){
        val recyclerView = binding.data
        recyclerView.layoutManager= LinearLayoutManager(this)
        val adapter= AdapaterViewHolder(this, Source.dataList)
        recyclerView.adapter=adapter
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}