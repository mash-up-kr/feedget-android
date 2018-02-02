package kr.mashup.feedget.ui

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import kr.mashup.feedget.domain.executor.PostExecutionThread
import javax.inject.Inject

class UiThread @Inject internal constructor() : PostExecutionThread {

    override val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()

}
