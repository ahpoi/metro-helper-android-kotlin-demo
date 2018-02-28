package com.ahpoi.metrohelper

import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.ahpoi.metrohelper.base.Presenter
import com.ahpoi.metrohelper.base.View
import com.ahpoi.metrohelper.manager.ApiManager
import com.ahpoi.metrohelper.model.AppUser
import com.ahpoi.metrohelper.model.AuthResponse
import kotlinx.android.synthetic.main.activity_login.*
import org.androidannotations.annotations.*

interface LoginActivityView : View {

    fun onLoginSuccess(appUser: AppUser)

    fun onLoginFailure(message: String)

}

@EBean
class LoginPresenter : Presenter {

    @Bean(ApiManager::class)
    lateinit var apiManager: ApiManager

    private lateinit var view: LoginActivityView

    override fun initView(view: View) {
        this.view = view as LoginActivityView
    }

    fun login(email: String, password: String) {
        val response = apiManager.auth(email, password)
        return when (response) {
            is AuthResponse.AuthResponseSuccess -> view.onLoginSuccess(AppUser.fromAuthResponse(response))
            is AuthResponse.AuthResponseFailure -> view.onLoginFailure(response.errorMessage)
        }
    }

}

@EActivity(R.layout.activity_login)
class LoginActivity : AppCompatActivity(), LoginActivityView {

    @Bean
    lateinit var presenter: LoginPresenter //LateInit, as we know its not going to be null, but initialised later

    @AfterViews
    fun init() {
        this.presenter.initView(this)
    }

    @Click(R.id.sign_in_button)
    fun onLogin() {
        val email = email.text.toString() //Accessing XML properties using KotlinAndroidExtensions
        val password = password.text.toString()
        this.presenter.login(email, password)
    }

    override fun onLoginSuccess(appUser: AppUser) {
        MainActivity_.intent(this).user(appUser).start()
    }

    override fun onLoginFailure(message: String) {
        //Using String templates
        Snackbar.make(rootLayout, "Something went wrong: $message", Snackbar.LENGTH_LONG).show()
    }

}
