package com.emedinaa.kotlinapp.dish


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import kotlinx.android.synthetic.main.fragment_dish_detail.*
import com.emedinaa.kotlinapp.model.Dish
import android.text.style.RelativeSizeSpan
import android.text.Spanned
import android.text.style.SuperscriptSpan
import android.text.SpannableString
import com.emedinaa.kotlinapp.model.Cart
import com.emedinaa.kotlinapp.utils.Logger
import com.emedinaa.kotlinapp.utils.PicassoImageLoader


/**
 * A simple [Fragment] subclass.
 */
class DishDetailFragment : Fragment() {

    private var dish:Dish?=null
    private var imageLoader = PicassoImageLoader()
   // private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkExtras()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(com.emedinaa.kotlinapp.R.layout.fragment_dish_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        populate()

        /*activity?.let {
            dashboardViewModel= ViewModelProviders.of(it).get(DashboardViewModel::class.java)
        }*/
        //events
        buttonOrder.setOnClickListener {
            dish?.let { itDish ->
                //addDishToCard(itDish)
                showDialog()
            }
        }

        buttonFavorites.setOnClickListener{

        }

        /*if (PreferencesHelper.session(activity) == null) {
            buttonOrder.setVisibility(View.GONE)
        }*/
    }

    private fun populate(){
        dish?.let {
            val price = SpannableString("S/." + it.price)
            price.setSpan(SuperscriptSpan(), 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            price.setSpan(RelativeSizeSpan(0.5f), 0, 3, 0) // set size

            textViewTitle.text= it.name
            textViewDesc.text= it.desc
            textViewPrice.text = price

            imageLoader.load("https://diplomado-restaurant-backend.herokuapp.com/images/platos/${it.photo}",imageViewDish)
            setCustomTitle(it.name)
        }
    }

    private fun showDialog(){
        val numberPickerDialog = NumberPickerDialog()
        activity?.let {
            numberPickerDialog.show(it.supportFragmentManager, "numberPickerDialog")
        }
    }

    private fun checkExtras(){
        activity?.intent?.extras?.let {
            if(it.containsKey("DISH") && it.getSerializable("DISH") is Dish){
                dish= it.getSerializable("DISH") as Dish
            }
        }
    }

    private fun setCustomTitle(title:String){
        activity?.title=title
    }

    fun addDishToCard(amount: Int) {
        //Log.v("CONSOLE", "amount $amount")
        Logger.d("amount $amount")
        if(amount>0){
            val item = Cart.CardMapper().convert(dish)
            item.amount=amount
            Cart.addItem(item)

            Logger.d("item $item")
           // dashboardViewModel.cartNotification.postValue(amount)
        }
    }

}
