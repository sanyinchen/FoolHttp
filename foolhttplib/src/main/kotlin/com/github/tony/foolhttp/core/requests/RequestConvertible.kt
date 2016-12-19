/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.github.tony.foolhttp.core.requests

import com.github.tony.foolhttp.core.Request

/**
 * Created by sanyinchen on 16/4/21.
 */
interface RequestConvertible {
    val request: Request;
}