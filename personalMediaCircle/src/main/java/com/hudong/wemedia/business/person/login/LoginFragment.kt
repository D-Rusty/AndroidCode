package com.hudong.wemedia.business.person.login

import android.view.View
import com.hudong.wemedia.BaseFragment
import com.hudong.wemedia.R
import com.hudong.wemedia.basiccomponent.bean.LoginUser

/**
 * 作者: 方天文
 * 日期: 2017/4/12:上午8:25
 * 概况:
 */

class LoginFragment : BaseFragment(), LoginContract.View, ILoginCallBack {

    private var presenter: LoginContract.Presenter? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_login
    }


    override fun initViewsData() {
        setHeadVisibility(View.GONE)
        holdingActivity.hiddenBottom()
    }

    override fun setListener() {

    }


    public override fun setPresenter() {
        presenter = LoginPresenter(context, this)
    }


    override fun loginResult(loginUser: LoginUser) {

    }





//    @OnClick(R.id.tv_forget_pwd, R.id.btn_login, R.id.btn_register)
//    override fun onViewClicked(view: View) {
//        when (view.id) {
//            R.id.tv_forget_pwd//忘记密码
//            -> {
//            }
//            R.id.btn_login//点击登录
//            -> {
////                val hashMap1 = HashMap<String, String>()
////                hashMap1.put("action", "login")
////                hashMap1.put("usertel", etLoginPhone!!.text.toString())
////                hashMap1.put("password", etLoginPwd!!.text.toString())
////                presenter!!.login(hashMap1)
//            }
//            R.id.btn_register//点击注册
//            -> {
//            }
//        }//            case R.id.tv_wx_login://微信登录
//        //                break;
//    }

    override fun showError(msg: String) {

    }

}
