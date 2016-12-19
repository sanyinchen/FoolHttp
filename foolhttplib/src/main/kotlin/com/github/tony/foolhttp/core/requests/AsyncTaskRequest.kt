/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.github.tony.foolhttp.core.requests

import com.github.tony.foolhttp.core.Request
import com.github.tony.foolhttp.core.Response

/**
 * Created by sanyinchen on 16/4/23.
 */
class AsyncTaskRequest(val task: TaskRequest) : TaskRequest(task.request) {


    override fun call(): Response? {
        val response = task.call().apply { dispatchCallback(this as Response) };
        // System.out.println("response:" + response.toString());
        return response;
    }


}