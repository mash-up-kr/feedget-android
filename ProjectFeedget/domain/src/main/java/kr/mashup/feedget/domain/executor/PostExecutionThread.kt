package kr.mashup.feedget.domain.executor

import io.reactivex.Scheduler

interface PostExecutionThread {
    val scheduler: Scheduler
}
