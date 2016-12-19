/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.github.tony.foolhttp.core.requests

import com.github.tony.foolhttp.core.Method
import com.github.tony.foolhttp.core.Request
import java.net.URI
import java.net.URISyntaxException
import java.net.URL

/**
 * Created by sanyinchen on 16/4/21.
 */
class Encoding() : RequestConvertible {
    lateinit var encodeMethod: Method;
    lateinit var encodePath: String;
    var encodeParms: List<Pair<String, Any?>>? = null;
    override val request by lazy { encoder(encodeMethod, encodePath, encodeParms) }

    var encoder: (Method, String, List<Pair<String, Any?>>?) -> Request = { method, path, parmas ->
        var modifiedPath = path;
        if (encodeParmeters(method)) {

            var extraUrl = modefieParmsUrl(parmas);
            if (extraUrl.isNotEmpty()) {
                extraUrl = if (modifiedPath.last() == '?') {
                    '?' + extraUrl
                } else {
                    extraUrl
                }
            }
            modifiedPath += extraUrl;
        }

        Request().apply {
            this.httpMothd = method;
            this.path = modifiedPath;
            this.url = createUrl(modifiedPath);
        }
    }

    private fun createUrl(path: String): URL {
        var url = URL(path);
        val uri = try {
            url.toURI()
        } catch(e: URISyntaxException) {
            URI(url.protocol, url.userInfo, url.host, url.port, url.path, url.query, url.ref)
        }
        return URL(uri.toASCIIString());
    }

    private fun encodeParmeters(method: Method): Boolean {
        when (method) {
            Method.GET -> return true;
            else ->
                return false;
        }
    }

    private fun modefieParmsUrl(parms: List<Pair<String, Any?>>?): String {
        return parms?.let {
            parms.filterNotNull().mapTo(arrayListOf<String>(), { "${it.first}=${it.second}" }).joinToString("&");
        } ?: ""

    }
}