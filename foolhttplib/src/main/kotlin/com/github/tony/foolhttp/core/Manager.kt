/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.github.tony.foolhttp.core

import com.github.tony.foolhttp.core.client.Client
import com.github.tony.foolhttp.core.client.HttpCilent
import com.github.tony.foolhttp.core.requests.Encoding
import com.github.tony.foolhttp.core.utilbox.Delegate
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


/**
 * Created by sanyinchen on 16/4/20.
 */
class Manager {
    var client: Client by Delegate<Client> { HttpCilent() }
    var basePath: String? = null;

    // var baseParmas: List<Pair<String, Any?>> = emptyList();

    var exector: ExecutorService by Delegate {

        Executors.newCachedThreadPool { runnable ->
            Thread {
                // System.out.println("instance exector!");
                Thread.currentThread().priority = Thread.NORM_PRIORITY;
                runnable.run();
            }
        }
    }

    fun request(method: Method, path: String, parms: List<Pair<String, Any?>>? = null): Request {
        // System.out.println("instance request!");
        val request = Encoding().apply {
            encodeMethod = method;
            encodePath = path;
            encodeParms = parms;

        }.request;
        request.executor = exector;

        return request;
    }

    companion object {
        var instance by Delegate { Manager() }
        // var instance:Manager=Manager()
    }

}