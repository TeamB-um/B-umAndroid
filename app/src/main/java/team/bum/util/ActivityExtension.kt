package team.bum.util

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import team.bum.R

fun AppCompatActivity.replaceFragment(
    containerView: FragmentContainerView,
    clazz: Class<out Fragment>,
    addToBackStack: Boolean = false,
    withAnim: Boolean = true,
    color: Int = getColor(R.color.white)
) {
    val tagName = clazz.simpleName
    val exists = supportFragmentManager.findFragmentByTag(tagName)

    supportFragmentManager.commit {
        if (withAnim) {
            setCustomAnimations(
                R.anim.enter_from_right, R.anim.exit_to_left, R.anim.pop_enter_from_left, R.anim.pop_exit_to_right
            )
        }
        exists?.run {
            replace(containerView.id, exists)
        } ?: replace(
            containerView.id, clazz, null, tagName
        )
        if (addToBackStack) addToBackStack(tagName)
    }
    StatusBarUtil.changeColor(this, color)
}

fun AppCompatActivity.replaceFragment(
    containerView: FragmentContainerView, fragment: Fragment, addToBackStack: Boolean = false
) {
    val tagName = fragment::class.java.simpleName
    val exists = supportFragmentManager.findFragmentByTag(tagName)

    supportFragmentManager.commit {
        setCustomAnimations(
            R.anim.enter_from_right, R.anim.exit_to_left, R.anim.pop_enter_from_left, R.anim.pop_exit_to_right
        )
        exists?.run {
            replace(containerView.id, exists)
        } ?: replace(
            containerView.id, fragment, tagName
        )
        if (addToBackStack) addToBackStack(tagName)
    }
}

fun AppCompatActivity.popFragment(clazz: Class<out Fragment>) {
    val tagName = clazz.simpleName
    val exists = supportFragmentManager.findFragmentByTag(tagName)

    if (exists != null) {
        supportFragmentManager.popBackStack(tagName, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}