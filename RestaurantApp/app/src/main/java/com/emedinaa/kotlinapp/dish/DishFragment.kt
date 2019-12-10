package com.emedinaa.kotlinapp.dish


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import com.emedinaa.kotlinapp.R
import com.emedinaa.kotlinapp.data.DataCallback
import com.emedinaa.kotlinapp.data.DataInjector
import com.emedinaa.kotlinapp.model.Dish
import com.emedinaa.kotlinapp.utils.PicassoImageLoader
import com.emedinaa.kotlinapp.utils.RecyclerClickListener
import com.emedinaa.kotlinapp.utils.RecyclerTouchListener
import kotlinx.android.synthetic.main.fragment_dish.*
import kotlinx.android.synthetic.main.fragment_dish.swipeRefresh
import kotlinx.android.synthetic.main.fragment_home.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DishFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DishFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var dishList:List<Dish> = emptyList()
    private lateinit var adapter:DishAdapter
    private var repository: DishesRepository = DataInjector.instance().provideDishesRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dish, container, false)
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DishFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DishFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerViewDish.layoutManager= LinearLayoutManager(activity)
        recyclerViewDish.setHasFixedSize(true)

        adapter= DishAdapter(dishList,PicassoImageLoader())
        recyclerViewDish.adapter= adapter

        swipeRefresh.setOnRefreshListener {
            retrieveDishes()
        }

        context?.let {
            recyclerViewDish.addOnItemTouchListener(RecyclerTouchListener(it,recyclerViewDish,
                object :RecyclerClickListener{
                    override fun onClick(view: View, position: Int) {
                        val dish:Dish= dishList[position]
                        goToDetail(dish)
                    }

                    override fun onLongClick(view: View, position: Int) {

                    }
                }))
        }

        retrieveDishes()
    }

    private fun goToDetail(dish:Dish){
        val intent= Intent(activity,DishDetailActivity::class.java)
        val bundle= Bundle()
        bundle.putSerializable("DISH",dish)
        intent.putExtras(bundle)

        startActivity(intent)
    }

    private fun showErrorMessage(message:String){
        Toast.makeText(activity,message, Toast.LENGTH_SHORT).show()
    }

    private fun retrieveDishes(){
        /*
         {
              "imagen": "",
              "_id": "5b6dfd6ea56c974f54fa09a2",
              "categoria_id": {
                "_id": "5b65cbffd4bb18056bc107b0",
                "nombre": "Plato de fondo",
                "descripcion": "Segunda categoria"
              },
              "nombre": "Lomo Saltado",
              "precio": 21
            },
            {
              "imagen": "",
              "_id": "5b6e047ba284ba50ee72fb57",
              "categoria_id": {
                "_id": "5b65cbffd4bb18056bc107b0",
                "nombre": "Plato de fondo",
                "descripcion": "Segunda categoria"
              },
              "nombre": "Lomo Saltado3",
              "precio": 23
            }
         */

        //mock data
        /*val mockData = mutableListOf<Dish>()
        mockData.add(Dish("100","1","Lomo Saltado","20.0","Segunda categoria",""))
        mockData.add(Dish("101","1","Arroz Chaufa","15.0","Segunda categoria",""))
        dishList= mockData.toList()
        adapter.update(dishList)*/
        repository.retrieveDishes(object :DataCallback<List<Dish>>{
            override fun onError(errorMessage: String, exception: Exception) {
                swipeRefresh.isRefreshing=false
                showErrorMessage(errorMessage)
            }

            override fun onSuccess(data: List<Dish>) {
                dishList= data
                adapter.update(dishList)

                swipeRefresh.isRefreshing=false
            }
        })
    }
}
