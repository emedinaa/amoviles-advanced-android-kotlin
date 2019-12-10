package com.emedinaa.kotlinapp.data.remote

import com.emedinaa.kotlinapp.model.Category
import com.emedinaa.kotlinapp.model.Dish
import com.emedinaa.kotlinapp.model.User

data class LogInRaw(val email:String?,val password:String?)

open class BaseResponse(val status:Int?,val message:String?){
    companion object {
        const val STATUS_CODE:Int=200
    }

    protected fun isSuccess():Boolean {
        return status == STATUS_CODE
    }
}

class LogInResponse(status:Int?, val data: User?, message:String?):
    BaseResponse(status,message)

class DishResponse(status:Int?, message:String?,val data:Dish?):
    BaseResponse(status,message)

class DishesResponse(status:Int?, message:String?,val data:List<Dish>?):
    BaseResponse(status,message)

class CategoryResponse(status:Int?, message:String?,val data:Category?):
    BaseResponse(status,message)

class CategoriesResponse(status:Int?, message:String?,val data:List<Category>?):
    BaseResponse(status,message)