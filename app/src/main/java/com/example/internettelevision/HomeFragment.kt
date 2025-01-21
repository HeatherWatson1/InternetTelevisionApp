package com.example.internettelevision

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 * This fragment is used as the "home screen" accessed via home icon on bottom navigation
 * hosts a slideshow of three images, where each image appears 5 seconds ar a time
 *
 */

class SlideShow : Thread  //Error message occurs because Constructor not implemented yet
{
    private var noSlides = 0
    private var duration : Long = 0
    private var count : Int = 1



    constructor()
    {
        duration = 3
        noSlides = 2
    }

    override public fun run()
    {
        var count = 0
        var files = arrayOf("homeimage", "image1", "image2")
        while(true)
        {
            var handler = HandlerThread(files[count%files.size])
            MainActivity.getInstance().runOnUiThread(handler)
            Thread.sleep(duration*1000) //Delay
            count++

        }
    }


}

class HandlerThread : Runnable
{
    private var fn : String = ""
    private var caption : String = ""
    constructor(fn : String)
    {
        this.fn = fn
    }
    override fun run()
    {
        var imageView = MainActivity.getInstance().findViewById<ImageView>(R.id.imageView)


        var id = MainActivity.getInstance().resources.getIdentifier(fn, "drawable", 	 		                                  MainActivity.getInstance().packageName)
        imageView.setImageResource(id)
    }
}





class HomeFragment : Fragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        instance = this


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)


    }

    companion object
    {
        private var instance : HomeFragment? = null
        public fun getInstance() : HomeFragment
        {
            return instance!!
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


       //THERE IS A SLIDESHOW BUT IF I RUN IT THE APP CRASHES

       //var slideShow = SlideShow()
       //slideShow.start()

    }


}