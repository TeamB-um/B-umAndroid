package team.bum.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import team.bum.databinding.ActivityMainBinding
import team.bum.ui.main.archive.ArchiveFragment
import team.bum.ui.main.collection.CollectionFragment
import team.bum.ui.main.home.HomeFragment
import team.bum.ui.main.setting.SettingFragment
import team.bum.util.setInvisible
import team.bum.util.setVisible

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val homeFragment by lazy { HomeFragment() }
    private val collectionFragment by lazy { CollectionFragment() }
    private val archiveFragment by lazy { ArchiveFragment() }
    private val settingFragment by lazy { SettingFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureNavigation()
    }

    private fun configureNavigation() {
        binding.bottomNavi.apply {
            setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    menu.getItem(0).itemId -> replaceFragment(homeFragment)
                    menu.getItem(1).itemId -> replaceFragment(collectionFragment)
                    menu.getItem(2).itemId -> replaceFragment(archiveFragment)
                    menu.getItem(3).itemId -> replaceFragment(settingFragment)
                }
                true
            }
            selectedItemId = menu.getItem(0).itemId
        }
    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment).commit()
    }

    fun hideBottomNav() {
        binding.bottomNavi.setInvisible()
    }

    fun showBottomNav() {
        binding.bottomNavi.setVisible()
    }
}