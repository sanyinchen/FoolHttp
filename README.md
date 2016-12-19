# FoolHttp

###Note:  

This is a simple kotlin demo of http, and it's modefied by [Fuel](https://github.com/kittinunf/Fuel)  

###Demo of using:  


method: ```fun request(method: Method, path: String, parms: List<Pair<String, Any?>>? = null): Request```,you can find get demo from Manager.

Simple demo:  


	 test1.setOnClickListener { view ->

            FoolHttp.get("https://www.baidu.com", null, { response ->
                runOnUiThread {
                    codeText.text = "status:" + response.httpStateCode.toString();
                    webview.loadData(response.toString(), "text/html", response.httpEncode);

                }

            }, { response, request ->
                runOnUiThread {
                    codeText.text = "failed:" + response.httpStateCode.toString();

                }

            });

        }
     
        
 


![](https://github.com/sanyinchen/FoolHttp/blob/master/show.gif)

###Others 
If you are interesting in it.I draw this uml diagram in order to help understand it's structure.  
![](https://github.com/sanyinchen/FoolHttp/blob/master/foolhttp.png)
