package com.example.internettelevision

import android.graphics.Bitmap
import android.media.tv.TvView
import android.net.http.SslError
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.SslErrorHandler
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient

class Delegate : WebViewClient()
{
    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?)
    {
        super.onPageStarted(view, url, favicon)
        println("started")
    }
    override fun onPageFinished(view: WebView, url: String)
    {
        super.onPageFinished(view,url)
        println("finish")
    }
    override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError)
    {
        println(error.description )
    }
    override fun onReceivedHttpError(
        view: WebView, request: WebResourceRequest, errorResponse: WebResourceResponse
    )
    {
        println(errorResponse.data)
    }
    override fun onReceivedSslError(
        view: WebView, handler: SslErrorHandler,
        error: SslError
    )
    {
        println(error.primaryError)
    }
}

/**
 * A simple [Fragment] subclass.
 * Use the [TvFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TvFragment : Fragment() {

    private var channel : TVChannel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        //Get the bundle
        var arguments = this.getArguments()
        channel = arguments?.getSerializable("TVChannel") as TVChannel


        var webView = MainActivity.getInstance().findViewById<WebView>(R.id.webView)
        var delegate = Delegate()
//Add the settings:
        webView.webViewClient = delegate
//This will allow the tracing of links
        webView?.getSettings()?.setJavaScriptEnabled(true);
        webView?.getSettings()?.setJavaScriptCanOpenWindowsAutomatically(true);
        var url1 = channel!!.getURL()

        webView?.loadUrl(url1)


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv, container, false)
    }

    companion object
    {
        private var instance : TvFragment? = null
        public fun getInstance() : TvFragment
        {
            return instance!!
        }
    }
}