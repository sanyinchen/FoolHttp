/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.github.tony.foolhttp.core.requests

import com.github.tony.foolhttp.core.Manager
import com.github.tony.foolhttp.core.Request
import com.github.tony.foolhttp.core.Response
import java.util.concurrent.Callable

/**
 * Created by sanyinchen on 16/4/23.
 */
open class TaskRequest(val request: Request) : Callable<Response> {
    lateinit var successCallBack: (response: Response) -> Unit;
    lateinit var failCallBack: (response: Response, request: Request) -> Unit;
    var validator: (Response) -> Boolean = { response ->
        (200..299).contains(response.httpStateCode)
    }

    override fun call(): Response? {

        var response = Manager.instance.client.executeRequest(request);

        //System.out.println("response: " + response);
        return response;
    }

    open fun dispatchCallback(response: Response) {
        System.out.println("fool:response:" + response.httpStateCode);

        if (validator?.invoke(response)) {
            successCallBack(response);
        } else {
            failCallBack(response, request);
        }
    }


}