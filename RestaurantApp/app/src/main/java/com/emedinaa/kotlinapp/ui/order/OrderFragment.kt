package com.emedinaa.kotlinapp.ui.order


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.emedinaa.kotlinapp.R
import com.emedinaa.kotlinapp.data.DataInjector
import com.emedinaa.kotlinapp.data.socket.SocketConstant
import com.emedinaa.kotlinapp.data.socket.SocketManager
import com.emedinaa.kotlinapp.data.socket.SocketMapper
import com.emedinaa.kotlinapp.model.Cart
import com.emedinaa.kotlinapp.model.OrderViewFooter
import com.emedinaa.kotlinapp.model.OrderViewHeader
import com.emedinaa.kotlinapp.model.OrderViewType
import com.emedinaa.kotlinapp.order.OrderAdapter
import com.emedinaa.kotlinapp.ui.DashboardViewModel
import com.emedinaa.kotlinapp.utils.SimpleDialog
import io.socket.client.Ack
import io.socket.client.Socket
import kotlinx.android.synthetic.main.fragment_order.*
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OrderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OrderFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var orderAdapter: OrderAdapter

    private lateinit var socketManager: SocketManager
    private  var socket:Socket?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        socketManager= DataInjector.provideSocketManager()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        socket= socketManager.socket()

        activity?.let {
            dashboardViewModel= ViewModelProviders.of(it).get(DashboardViewModel::class.java)
            dashboardViewModel?.orderAction.observe(this,object :Observer<Boolean>{
                override fun onChanged(t: Boolean?) {
                    if(t!=null && t){
                        clearCart()
                    }
                }
            })
        }


        recyclerViewOrder.layoutManager = LinearLayoutManager(activity)
        orderAdapter= OrderAdapter(emptyList())
        recyclerViewOrder.adapter= orderAdapter


        textOrder.setOnClickListener {
            if (!Cart.isEmpty()) {
                sendOrder()
            } else {
                //cart empty
                Toast.makeText(getContext(), "Carrito vacío", Toast.LENGTH_SHORT).show()
            }
        }

        renderOrder()
    }

    private fun renderOrder() {
        if (Cart.getItems() == null) return

        val total = Cart.total()
        val tmp = mutableListOf<OrderViewType>()
        tmp.add(OrderViewHeader())
        tmp.addAll(Cart.getItems())
        tmp.add(OrderViewFooter(total))

        orderAdapter.update(tmp)
    }

    private fun sendOrder() {
        if(socketManager.isConnected()){
            val jsonObject= Cart.makeOrder()
            socket?.emit(SocketConstant.EMIT_ORDER, jsonObject,object:Ack{
                override fun call(vararg args: Any?) {
                    val data = args[0] as JSONObject
                    val socketResponse = SocketMapper().convert(data)
                    Log.v("CONSOLE", "sendOrder $socketResponse")
                    if (socketResponse.success) {
                        activity?.runOnUiThread(object : Runnable {
                            override fun run() {
                                onSuccess()
                            }
                        })
                    } else {
                        activity?.runOnUiThread { }
                    }
                }
            })
        }
    }

    private fun onSuccess() {
        Toast.makeText(
            context, "Se envió el pedido correctamente",
            Toast.LENGTH_LONG
        ).show()

        activity?.let {
            val bundle = Bundle()
            bundle.putString("TITLE", "MyRestaurant")
            bundle.putString("MESSAGE", "Orden enviada correctamente")
            val simpleDialog = SimpleDialog()
            simpleDialog.arguments=bundle
            simpleDialog.show(it.supportFragmentManager, "SimpleDialog")
        }
    }

    private fun clearCart(){
        Cart.clear()
        renderOrder()
        dashboardViewModel.orderAction.postValue(false)
        dashboardViewModel.cartNotification.postValue(0)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OrderFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OrderFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
