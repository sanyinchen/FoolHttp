/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.github.tony.foolhttp.core.client

import com.github.tony.foolhttp.core.Request
import com.github.tony.foolhttp.core.Response
import java.net.HttpURLConnection
import java.net.URLConnection
import java.util.zip.GZIPInputStream

/**
 * Created by sanyinchen on 16/4/21.
 */
class HttpCilent : Client {
    override fun executeRequest(request: Request): Response {
        System.out.println("executeRequest----");
        var response = Response();
        val connection = establishConnection(request) as HttpURLConnection;

        connection.apply {
            connectTimeout = request.timeOutLimt;
            readTimeout = request.timeOutLimt;
            doInput = true;
            requestMethod = request.httpMothd.valueMethod

        }

        var result = response.apply {
            // httpContentLength = connection.contentLengthLong.toLong();
            //  System.out.println("httpContentLength: " + httpContentLength)
            val cotentEncoding = connection.contentEncoding ?: "utf-8";
            httpEncode = cotentEncoding;
            val dataStream = if (connection.errorStream != null) {
                connection.errorStream;
            } else {
                connection.inputStream;
            }
            try {
                httpStateCode = connection.responseCode;
                System.out.println("fool:httpStateCode：" + httpStateCode);
                httpResponseMessage = connection.responseMessage;
                System.out.println("fool:httpResponseMessage：" + httpResponseMessage);
                // why shut down
                //httpContentLength = connection.contentLengthLong;
                //System.out.println("fool:httpContentLength：" + connection.contentLengthLong);

            } catch (exception: Exception) {
                exception.printStackTrace()
                System.out.println("fool:" + exception);
            }


            if (dataStream != null) {
                data = if (cotentEncoding.compareTo("gzip", true) == 0) {
                    GZIPInputStream(dataStream).readBytes()
                } else {
                    dataStream.readBytes();
                }
            }
        }
        releaseConnection(connection);
        return result;
    }

    private fun establishConnection(request: Request): URLConnection {
        System.out.println("url: " + request.url);
        return request.url.openConnection() as HttpURLConnection
    }

    private fun releaseConnection(connection: HttpURLConnection) {
        connection?.disconnect();
    }
}