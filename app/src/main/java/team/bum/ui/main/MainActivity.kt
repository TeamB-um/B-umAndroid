package team.bum.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import retrofit2.Call
import team.bum.R
import team.bum.api.data.ResponseCategory
import team.bum.api.retrofit.ServiceCreator
import team.bum.databinding.ActivityMainBinding
import team.bum.ui.main.archive.ArchiveFragment
import team.bum.ui.main.collection.CollectionFragment
import team.bum.ui.main.collection.list.CollectionListFragment
import team.bum.ui.main.home.HomeFragment
import team.bum.ui.main.home.drop.HomeDropFragment
import team.bum.ui.main.home.writing.HomeWritingFragment
import team.bum.ui.main.setting.SettingFragment
import team.bum.ui.main.setting.bin.SettingBinFragment
import team.bum.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var backPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureBottomNav()
        getCategory()
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

    fun navigateHomeToWriting(paperId: Int) {
        replaceFragment(binding.fragmentContainer, HomeWritingFragment.newInstance(paperId), true)
        slideDownBottomNav()
    }

    fun navigateWritingToDrop(categoryId: String, title: String, content: String, dropTo: Boolean) {
        replaceFragment(binding.fragmentContainer, HomeDropFragment.newInstance(categoryId, title, content, dropTo), true)
        slideUpBottomNav()
    }

    fun navigateSettingToManagement(){
        replaceFragment(binding.fragmentContainer, SettingBinFragment::class.java, true)
    }

    fun navigateCollectionToList(categoryName: String) {
        replaceFragment(binding.fragmentContainer, CollectionListFragment.newInstance(categoryName), true)
    }

    fun popHomeWriting() {
        popFragment(HomeWritingFragment::class.java)
        slideUpBottomNav()
    }

    fun popHomeDrop() {
        popFragment(HomeDropFragment::class.java)
        slideDownBottomNav()
    }

    fun popSetting() {
        popFragment(SettingBinFragment::class.java)
    }

    fun popCollection() {
        popFragment(CollectionListFragment::class.java)
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

    private fun getCategory() {
        val call: Call<ResponseCategory> = ServiceCreator.bumService.getCategory(
            MyApplication.mySharedPreferences.getValue("token", "")
        )
        call.enqueueUtil(
            onSuccess = { response ->
                response.data.category.forEach {
                    categoryMap[it.name] = it._id
                }
            }
        )
    }

    fun deleteBottomNav() {
        binding.bottomNavi.setInvisible()
    }

    fun showFinishToast() {
        if (System.currentTimeMillis() - backPressedTime < 2000) {
            finish()
            return
        }
        shortToast("'뒤로' 버튼을 한번 더 누르시면 앱이 종료됩니다.")
        backPressedTime = System.currentTimeMillis()
    }

    companion object {
        val categoryMap = mutableMapOf<String, String>()
    }
}