package com.project.onepice.weatherapp.ui

import android.support.v7.graphics.drawable.DrawerArrowDrawable
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.project.onepice.weatherapp.MyApp
import com.project.onepice.weatherapp.R
import com.project.onepice.weatherapp.extensions.ctx
import com.project.onepice.weatherapp.extensions.slideEnter
import com.project.onepice.weatherapp.extensions.slideExit
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * 作者: 方天文
 * 日期: 2017/6/2:下午6:37
 * 概况:
 */
interface ToolbarManager {
    val toolbar: Toolbar//定义一个ToolBar
    var toolbarTitle: String //定义一个 toolbarTitle 并设置对应的set,get方法
        get() = toolbar.title.toString()
        set(value) {
            toolbar.title = value
        }

    /**
     * 初始化toolBar
     *    1. 设计 toolBar布局为菜单布局
     *    2. 设计toolbar菜单点击事件
     */
    fun initToolBar() {
        toolbar.inflateMenu(R.menu.menu_main)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_settings -> toolbar.ctx.startActivity<SettingsActivity>()//如果点中了设置图标则进入设置界面
                else -> MyApp.instance.toast("Unknown option") //否则给出提示 无法识别的操作
            }
            true //阻止事件继续传递下去
        }
    }

    /**
     * 按下菜单时候选项
     */
    fun enableHomeAsUp(up: () -> Unit) {
        toolbar.navigationIcon = createUpDrawable()
        toolbar.setNavigationOnClickListener { up() }
    }

    private fun createUpDrawable() = DrawerArrowDrawable(toolbar.ctx).apply {
        progress = 1f
        this
    }


    fun attachToScroll(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if (dy > 0) toolbar.slideExit() else toolbar.slideEnter()
            }
        })
    }
}