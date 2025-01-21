package com.example.internettelevision

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.ImageView
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.appcompat.app.AlertDialog

class Handler : TextView.OnEditorActionListener, View.OnClickListener, DialogInterface.OnClickListener
{
    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean
    {
        println(v?.getText())
        var im = MainActivity.getInstance().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        im.hideSoftInputFromWindow(v?.getWindowToken(),0)
        return true
    }

    override fun onClick(view: View)
    {

        //CHECK THAT FIELDS ARE FILLED IN

        var g = ChannelGuide
        var nameEntry = MainActivity.getInstance().findViewById<EditText>(R.id.nameEntry)
        var urlEntry = MainActivity.getInstance().findViewById<EditText>(R.id.nameEntry)
        if (nameEntry.text != null && urlEntry.text != null ){

            //add channel to array I hope.

            g.addChannel(nameEntry.text.toString(), urlEntry.text.toString())


        }



    }

    override fun onClick(dialog: DialogInterface?, which: Int)
    {
        if (which == DialogInterface.BUTTON_NEGATIVE)
        {
            println("negative")
        }
        else if (which == DialogInterface.BUTTON_POSITIVE)
        {
            println("positive")
        }
    }


}

/**
 * A simple [Fragment] subclass.
 * Use the [ConfigFragment.newInstance] factory method to
 * create an instance of this fragment.
 */



class ConfigFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        instance = this



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var submit = MainActivity.getInstance().findViewById<Button>(R.id.submit)

        var handler = Handler()
        submit.setOnClickListener(handler)


        val dialogBuilder = AlertDialog.Builder(MainActivity.getInstance())

        dialogBuilder.setPositiveButton("OK",handler)
        dialogBuilder.setNegativeButton("Cancel", handler)
        //Create the actual alert box

        val alert1 = dialogBuilder.create()


        //Set the title of the alert box:

        alert1.setTitle("Channel created")


        //Show the alert box
        submit.setOnClickListener(){
            var g = ChannelGuide

            var nameEntry = MainActivity.getInstance().findViewById<EditText>(R.id.nameEntry)
            var urlEntry = MainActivity.getInstance().findViewById<EditText>(R.id.urlEntry)
            if (nameEntry.text != null && urlEntry.text != null ) {

                g.addChannel(nameEntry.text.toString(), urlEntry.text.toString())


                alert1.show()
            }
        }






    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_config, container, false)
    }



    companion object
    {
        private var instance : ConfigFragment? = null
        public fun getInstance() : ConfigFragment
        {
            return instance!!
        }
    }
}