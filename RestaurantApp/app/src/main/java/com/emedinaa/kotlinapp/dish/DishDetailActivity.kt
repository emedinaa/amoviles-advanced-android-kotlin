package com.emedinaa.kotlinapp.dish

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.emedinaa.kotlinapp.R

class DishDetailActivity : AppCompatActivity(),NumberPickerDialog.NumberPickerListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dish_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onDialogCancel(obj: Any) {

    }

    override fun onDialogOk(amount: Int) {
        /*if(supportFragmentManager.findFragmentById(R.id.fragmentDishDetail) is DishDetailFragment){
            dishDetailFragment= supportFragmentManager.findFragmentById(R.id.fragmentDishDetail) as DishDetailFragment
        }*/

        var dishDetailFragment= supportFragmentManager.findFragmentById(R.id.fragmentDishDetail) as? DishDetailFragment
        dishDetailFragment?.addDishToCard(amount)
    }
}
