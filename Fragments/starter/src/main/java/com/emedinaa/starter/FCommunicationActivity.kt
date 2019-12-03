package com.emedinaa.starter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager

import com.emedinaa.starter.R
import com.emedinaa.starter.fragments.ShowMessageFragment
import com.emedinaa.starter.fragments.WriteMessageFragment
import com.emedinaa.starter.listeners.MessageListener

class FCommunicationActivity : AppCompatActivity(), MessageListener {
//class FCommunicationActivity : AppCompatActivity() {

    private lateinit var fragmentManager: FragmentManager
    private var  writeMessageFragment: WriteMessageFragment?=null
    private var  showMessageFragment: ShowMessageFragment?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fcommunication)

        fragmentManager = supportFragmentManager

       if (fragmentManager.findFragmentById(R.id.fragmentWriteMessage) is WriteMessageFragment) {
            writeMessageFragment= fragmentManager.findFragmentById(R.id.fragmentWriteMessage) as WriteMessageFragment
        }

        if (fragmentManager.findFragmentById(R.id.fragmentShowMessage) is ShowMessageFragment) {
            showMessageFragment= fragmentManager.findFragmentById(R.id.fragmentShowMessage) as ShowMessageFragment
        }
    }

   /* override fun recibiryEnviardesdeFragment(message: String?) {
        showMessageFragment?.mostrarMensaje(message)
    }*/

    override fun recibiryEnviardesdeFragment(message: String?) {
        showMessageFragment?.showMessage(message)
    }
}
