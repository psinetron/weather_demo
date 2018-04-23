package ru.slybeaver.currentweatherdemo.presenter

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ru.slybeaver.currentweatherdemo.ui.view.BaseView

/**
 * Created by psinetron on 23.04.2018.
 * http://slybeaver.ru
 */
abstract class BasePresenter:Presenter {

    private var compositeDisposable = CompositeDisposable()

    override fun onStop() {
        compositeDisposable.clear()
    }

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    fun removeDisposable(disposable: Disposable) {
        compositeDisposable.remove(disposable)
    }

    protected abstract fun getView(): BaseView

    fun showLoadingState() {
        getView().showLoading()
    }

    fun hideLoadingState() {
        getView().hideLoading()
    }


}