/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.github.tony.foolhttp.core.client

import com.github.tony.foolhttp.core.Request
import com.github.tony.foolhttp.core.Response


/**
 * Created by sanyinchen on 16/4/21.
 */
interface Client{
    fun executeRequest(request: Request): Response
}