package kz.cgn.flickrclient.domain

import io.reactivex.Scheduler

interface PostExecutionThread {

    fun ui(): Scheduler

    fun io(): Scheduler

    fun computation(): Scheduler

}