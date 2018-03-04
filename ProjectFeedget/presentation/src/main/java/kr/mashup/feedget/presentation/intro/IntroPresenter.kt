package kr.mashup.feedget.presentation.intro

import io.reactivex.observers.DisposableSingleObserver
import kr.mashup.feedget.domain.interactor.usecases.IsLogined
import kr.mashup.feedget.domain.interactor.usecases.Register
import kr.mashup.feedget.entity.SignIn
import javax.inject.Inject

class IntroPresenterImpl @Inject constructor(
    override val view: IntroView,
    private val isLogined: IsLogined,
    private val register: Register
) : IntroPresenter {

    override fun initialize() {
        isLogined.execute(object : DisposableSingleObserver<Boolean>() {
            override fun onSuccess(t: Boolean) {
                if (t)
                    view.startMain()
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }

        })
    }

    override fun onFacebookCallbackSuccess() {
        view.requestLoginFB()
    }

    override fun onRequestLoginFBSuccess(name: String, email: String, token: String) {
        register.execute(object : DisposableSingleObserver<SignIn>() {
            override fun onSuccess(t: SignIn) {
                view.startMain()
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }
        }, Register.Params(
            name,
            name,
            email,
            token
        ))
    }
}
