package ru.slybeaver.currentweatherdemo.ui.view

/**
 * Created by psinetron on 23.04.2018.
 * http://slybeaver.ru
 */
interface BaseView {

    fun getPermission(permissionName: Array<String>, permissionRequestCode: Int)

    fun noConnectionError(method: Runnable, retried: Boolean)

    fun showLoading()

    fun hideLoading()

}