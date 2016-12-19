/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.github.tony.foolhttp.core

import com.github.tony.foolhttp.core.requests.AsyncTaskRequest
import com.github.tony.foolhttp.core.requests.TaskRequest
import com.github.tony.foolhttp.core.utilbox.Delegate
import java.net.URL
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Future
import kotlin.properties.Delegates

/**
 * Created by sanyinchen on 16/4/20.
 */
class Request {
    enum class RequestType {
        REQUEST,
        DOWNLOAD
    }

    var timeOutLimt = 15000;
    var type: RequestType = RequestType.REQUEST;
    lateinit var httpMothd: Method;
    lateinit var path: String;
    lateinit var url: URL;

    var taskFuture: Future<out Any>? = null;
    val taskRequest: TaskRequest by Delegate {
        when (type) {
            RequestType.REQUEST -> AsyncTaskRequest(TaskRequest(this));
            else -> AsyncTaskRequest(TaskRequest(this));
        }
    }
    lateinit var executor: ExecutorService;

    fun handler(successCallBack: (response: Response) -> Unit, failCallBack: (response: Response, request: Request) ->
    Unit): Request {
        taskRequest?.apply {
            this.successCallBack = successCallBack;
            this.failCallBack = failCallBack;
        }

        return this;
    }

    fun submit(callable: Callable<out Any>) {

        taskFuture = executor?.submit(callable)
    }

    fun cancel() {
        taskFuture?.cancel(true);
    }

    fun executeRequest() {
        submit(taskRequest);
    }

}