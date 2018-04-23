package ru.slybeaver.currentweatherdemo.ui.activity

import android.content.Intent
import android.os.Build
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import ru.slybeaver.currentweatherdemo.R
import ru.slybeaver.currentweatherdemo.presenter.Presenter
import ru.slybeaver.currentweatherdemo.ui.view.BaseView

/**
 * Created by psinetron on 23.04.2018.
 * http://slybeaver.ru
 */
abstract class BaseActivity : AppCompatActivity(), BaseView {

    protected abstract fun getPresenter(): Presenter?

    override fun onStop() {
        super.onStop()
        getPresenter()?.onStop()

    }

    fun openActivity(cls: Class<*>) {
        val intent = Intent(this, cls)
        startActivity(intent)
    }

    fun openActivity(cls: Class<*>, flags: Int) {
        val intent = Intent(this, cls)
        intent.flags = flags
        startActivity(intent)
    }


    override fun getPermission(permissionName: Array<String>, permissionRequestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this,
                    permissionName, permissionRequestCode)
        }
    }

    override fun noConnectionError(method: Runnable, retried: Boolean) {
        var LENGTH = Snackbar.LENGTH_INDEFINITE
        if (!retried) {
            LENGTH = Snackbar.LENGTH_SHORT
        }
        val mySnackbar = Snackbar.make((this
                .findViewById<View>(android.R.id.content) as ViewGroup).getChildAt(0),
                R.string.no_internet_connection, LENGTH)

        mySnackbar.setAction(R.string.retry_connection, { method.run() })
        mySnackbar.setActionTextColor(ContextCompat.getColor(this, R.color.colorAccent))
        mySnackbar.show()
    }

    override fun showLoading() {
        findViewById<ProgressBar>(R.id.mainProgressBar)?.let {
            it.visibility = View.VISIBLE
        }
    }

    override fun hideLoading() {
        findViewById<ProgressBar>(R.id.mainProgressBar)?.let {
            it.visibility = View.GONE
        }
    }

}