package com.example.httpexample

import android.os.AsyncTask
import android.util.Log
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class HttpRequestTask : AsyncTask<String, Int, String>() {
    override fun doInBackground(vararg params: String?): String? {
        println("Entered in doInBackground")
        val request_type = params[0]
        lateinit var httpUrlConnection: HttpURLConnection

        if (request_type == "GET") {
            try {
                val url: URL = URL("https://jsonplaceholder.typicode.com/posts/1")
                println("Created URL")
                httpUrlConnection = url.openConnection() as HttpURLConnection
                httpUrlConnection.setRequestMethod("GET")

                println("---------Response------------")

                Log.d(
                    TAG,
                    "MyHttpRequestTask doInBackground : " + httpUrlConnection.getResponseCode()
                );
                Log.d(
                    TAG,
                    "MyHttpRequestTask doInBackground : " + httpUrlConnection.getResponseMessage()
                );
                println("Response output")
                println(url.readText())

            } catch (e: Exception) {
                e.printStackTrace()
            }

        } else if (request_type == "POST") {
            try {
                val url: URL = URL("https://jsonplaceholder.typicode.com/posts")
                println("Created URL for POST")
                httpUrlConnection = url.openConnection() as HttpURLConnection
                httpUrlConnection.setRequestMethod("POST")
                httpUrlConnection.setRequestProperty("Content-Type", "application/json; utf-8")

                try {
                    httpUrlConnection.setDoOutput(true)
                    val jsonInputString = """{"title": "foo", "body": "bar", "userId": 2}"""

                    println("preparing to write data")
                    val dataOutputStream: DataOutputStream =
                        DataOutputStream(httpUrlConnection.outputStream)
                    println("output stream is ready")
                    dataOutputStream.writeBytes(jsonInputString)
                    dataOutputStream.flush();
                    dataOutputStream.close();
                    Log.d(
                        TAG,
                        "MyHttpRequestTask doInBackground : " + httpUrlConnection.getResponseCode()
                    );
                    Log.d(
                        TAG,
                        "MyHttpRequestTask doInBackground : " + httpUrlConnection.getResponseMessage()
                    );


                    BufferedReader(
                        InputStreamReader(httpUrlConnection.getInputStream(), "utf-8")
                    ).use { br ->
                        val response = StringBuilder()
                        var responseLine: String? = null
                        while (br.readLine().also { responseLine = it } != null) {
                            response.append(responseLine!!.trim { it <= ' ' })
                        }
                        println("Respose body")
                        println(response.toString())
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        } else if (request_type == "PUT") {
            try {
                val url: URL = URL("https://jsonplaceholder.typicode.com/posts/1")
                println("Created URL for PUT")
                httpUrlConnection = url.openConnection() as HttpURLConnection
                httpUrlConnection.setRequestMethod("PUT")
                httpUrlConnection.setRequestProperty(
                    "Content-Type",
                    "application/json; utf-8"
                );
                try {
                    httpUrlConnection.setDoOutput(true)
                    val jsonInputString = """{"id": 1,"title": "foo", "body": "bar", "userId": 1}"""
                    println("preparing to write data")
                    val dataOutputStream: DataOutputStream =
                        DataOutputStream(httpUrlConnection.outputStream)
                    println("output stream is ready")
                    dataOutputStream.writeBytes(jsonInputString)
                    dataOutputStream.flush();
                    dataOutputStream.close();
                    Log.d(
                        TAG,
                        "MyHttpRequestTask doInBackground : " + httpUrlConnection.getResponseCode()
                    );
                    Log.d(
                        TAG,
                        "MyHttpRequestTask doInBackground : " + httpUrlConnection.getResponseMessage()
                    );



                    BufferedReader(
                        InputStreamReader(httpUrlConnection.getInputStream(), "utf-8")
                    ).use { br ->
                        val response = StringBuilder()
                        var responseLine: String? = null
                        while (br.readLine().also { responseLine = it } != null) {
                            response.append(responseLine!!.trim { it <= ' ' })
                        }
                        println("Respose body")
                        println(response.toString())
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        } else if (request_type == "DELETE"){
            try {
                val url: URL = URL("https://jsonplaceholder.typicode.com/posts/1")
                println("Created DELETE URL")
                httpUrlConnection = url.openConnection() as HttpURLConnection
                httpUrlConnection.setRequestMethod("DELETE")

                println("---------Response------------")

                Log.d(
                    TAG,
                    "MyHttpRequestTask doInBackground : " + httpUrlConnection.getResponseCode()
                );
                Log.d(
                    TAG,
                    "MyHttpRequestTask doInBackground : " + httpUrlConnection.getResponseMessage()
                );


                BufferedReader(
                    InputStreamReader(httpUrlConnection.getInputStream(), "utf-8")
                ).use { br ->
                    val response = StringBuilder()
                    var responseLine: String? = null
                    while (br.readLine().also { responseLine = it } != null) {
                        response.append(responseLine!!.trim { it <= ' ' })
                    }
                    println("Respose body")
                    println(response.toString())
                }


            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        httpUrlConnection.disconnect()


        return null
    }

    companion object {
        private val TAG = "HTTP_TAG"

    }

}