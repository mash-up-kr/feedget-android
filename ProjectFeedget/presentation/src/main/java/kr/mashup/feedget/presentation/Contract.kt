package kr.mashup.feedget.presentation

interface RootView{

}
interface RootPresenter{

}

interface View<out T : RootPresenter> : RootView{
    val presenter : T
}

interface Presenter<out T : RootView>: RootPresenter{
    val view : T
}
