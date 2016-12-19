/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.github.tony.foolhttp.core

/**
 * Created by sanyinchen on 16/4/21.
 */
class Response {
    var httpStateCode = -1
    var httpResponseMessage = ""
    var httpEncode = "utf-8";

    //data
    var data = ByteArray(0);

    override fun toString(): String {
        return String(data);
    }
}