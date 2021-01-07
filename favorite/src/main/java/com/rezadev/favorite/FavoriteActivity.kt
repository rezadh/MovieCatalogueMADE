package com.rezadev.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rezadev.favorite.databinding.ActivityFavoriteBinding
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        val sectionsPagerAdapter = SectionPagerFavoriteAdapter(this, supportFragmentManager)
        binding.viewPagerFavorite.adapter = sectionsPagerAdapter
        binding.tabsFavorite.setupWithViewPager(binding.viewPagerFavorite)
        supportActionBar?.title = getString(R.string.favorite)
        supportActionBar?.elevation = 0f
    }
}