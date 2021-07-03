package team.bum.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import team.bum.R
import team.bum.databinding.ActivityMainBinding
import team.bum.ui.main.archive.ArchiveFragment
import team.bum.ui.main.collection.CollectionFragment
import team.bum.ui.main.home.HomeFragment
import team.bum.ui.main.home.drop.HomeDropFragment
import team.bum.ui.main.home.writing.HomeWritingFragment
import team.bum.ui.main.setting.SettingFragment
import team.bum.util.popFragment
import team.bum.util.replaceFragment
import team.bum.util.setInvisible
import team.bum.util.setVisible

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureBottomNav()
    }

    private fun configureBottomNav() {
        binding.bottomNavi.apply {
            setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    menu.getItem(0).itemId -> replaceFragment(binding.fragmentContainer, HomeFragment::class.java, withAnim = false)
                    menu.getItem(1).itemId -> replaceFragment(binding.fragmentContainer, CollectionFragment::class.java, withAnim = false)
                    menu.getItem(2).itemId -> replaceFragment(binding.fragmentContainer, ArchiveFragment::class.java, withAnim = false)
                    menu.getItem(3).itemId -> replaceFragment(binding.fragmentContainer, SettingFragment::class.java, withAnim = false)
                }
                true
            }
            selectedItemId = menu.getItem(0).itemId
        }
    }

    fun navigateHomeToWriting() {
        replaceFragment(binding.fragmentContainer, HomeWritingFragment::class.java, true)
        slideDownBottomNav()
    }

    fun navigateWritingToDrop() {
        replaceFragment(binding.fragmentContainer, HomeDropFragment::class.java, true)
        slideUpBottomNav()
    }

    fun popHomeWriting() {
        popFragment(HomeWritingFragment::class.java)
        slideUpBottomNav()
    }

    fun popHomeDrop() {
        popFragment(HomeDropFragment::class.java)
        slideDownBottomNav()
    }

    private fun slideUpBottomNav() {
        val slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up)
        binding.bottomNavi.apply {
            startAnimation(slideUp)
            setVisible()
        }
    }

    private fun slideDownBottomNav() {
        val slideDown = AnimationUtils.loadAnimation(this, R.anim.slide_down)
        binding.bottomNavi.apply {
            startAnimation(slideDown)
            setInvisible()
        }
    }
}