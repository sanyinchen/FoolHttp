/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.github.tony.foolhttp

import com.github.tony.foolhttp.core.Manager
import com.github.tony.foolhttp.core.Method
import com.github.tony.foolhttp.core.Request
import com.github.tony.foolhttp.core.Response

/**
 * Created by sanyinchen on 16/4/20.
 */
class FoolHttp {
    companion object {
        @JvmOverloads
        fun get(path: String, parameters: List<Pair<String, Any?>>? = null, successCallBack: (response: Response) ->
        Unit = null!!, failCallBack: (response: Response, request: Request) ->
        Unit = null!!) {
            Manager.instance.request(Method.GET, path, parameters).handler(successCallBack, failCallBack).executeRequest();
        }
    }
}