package com.example.internettelevision

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Space
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.internettelevision.ChannelGuide.URLs
import com.example.internettelevision.ChannelGuide.channelNames
import java.io.Serializable


/**
 * A simple [Fragment] subclass.
 * Use the [ChannelsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */



var channelMap: MutableMap<String, String> = HashMap()

class ChannelsFragment : Fragment() {


/*
    var channelNames = listOf("France24","MLB","The Blaze","Weather","ABC","CBS",  "Free Speech", "Travel", "SECDN", "Daytona", "HouseTV","ROKU","Orange County", "Seminole County",
        "University of Texas", "Temple University","University of California")

    var URLs = listOf("https://nmxlive.akamaized.net/hls/live/529965/Live_1/index.m3u8",
    "https://videos.rtva.ad/live/rtva/playlist.m3u8",
    "https://buzzrota-web.amagi.tv/playlist480.m3u8",
    "https://bloomberg.com/media-manifest/streams/us.m3u8",
    "https://content.uplynk.com/channel/3324f2467c414329b3b0cc5cd987b6be.m3u8",
    "https://reuters-reutersnow-1-eu.rakuten.wurl.tv/playlist.m3u8",
    "https://cbsnews.akamaized.net/hls/live/2020607/cbsnlineup_8/master.m3u8",

    "https://e46fa4a445464f8a9150fa7d510eb85c.mediatailor.us-west-2.amazonaws.com/v1/master/2d2d0d97b0e548f025b2598a69b55bf30337aa0e/npp/N55LT0GGXOUT6SC3GJN7/hls3/now_-1m_15s/m.m3u8",


    "https://30a-tv.com/darcizzle.m3u8",
    "https://30a-tv.com/feeds/vidaa/golf.m3u8",
    "https://30a-tv.com/feeds/xodglobal/30atv.m3u8",
    "https://30a-tv.com/feeds/vidaa/luxelife.m3u8",
    "https://30a-tv.com/music.m3u8",
    "https://30a-tv.com/feeds/ceftech/30atvmusic.m3u8",
    "https://30a-tv.com/sidewalks.m3u8",
    "https://30a-tv.com/feeds/masters/30atv.m3u8",
    "https://30a-tv.com/beachy.m3u8"
    )

*/

    var channelNames = ChannelGuide.channelNames

    var URLs = ChannelGuide.URLs








    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        instance = this

        var name = ""
        var url = ""


        var channelList =  arrayListOf<TVChannel>()



        for  (i in 0 .. (channelNames.size - 1)) {

            name = channelNames[i]
            url = URLs[i]

            channelMap.put(name, url)

            channelList.add(TVChannel(name, url))
            channelList[i].setName(name)
        }



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_channels, container, false)


    }


    companion object
    {
        private var instance : ChannelsFragment? = null
        public fun getInstance() : ChannelsFragment
        {
            return instance!!
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        val bundle = Bundle()
        var name = ""
        var url = ""




        var channelList =  arrayListOf<TVChannel>()



        for  (i in 0 .. (channelNames.size - 1)) {

            name = channelNames[i]
            url = URLs[i]

            channelMap.put(name, url)

            channelList.add(TVChannel(name, url))
            channelList[i].setName(name)
        }


        var space: Space
        for (i in 0..(channelList.size - 1))
        {
            var button1 = inflate(MainActivity.getInstance(), R.layout.button, null) as Button

            var text = channelList[i].getName()
            button1.setText(text)

            var handler = ButtonHandler()
            button1.setOnClickListener(handler)

            var linearLayout =
                MainActivity.getInstance().findViewById<LinearLayout>(R.id.linearLayout)

            linearLayout.addView(button1)

            var space = Space(MainActivity.getInstance())
            space.minimumHeight = 15

            linearLayout.addView(space)
        }

    }



}

class ButtonHandler : View.OnClickListener
{
    override fun onClick(view: View)
    {

        val b = view as Button
        val bundle = Bundle()

        val buttonText: String = b.getText().toString()

        val url = channelMap.get(buttonText).toString()

        var channel = TVChannel(buttonText, url)


        bundle.putSerializable("TVChannel", channel)

        var navController =
            Navigation.findNavController(ChannelsFragment.getInstance().requireView())
        //Transition to TV Fragment and send null Bundle
        navController.navigate(R.id.channelsToTV, bundle)
    }
}