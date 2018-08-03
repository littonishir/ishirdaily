package com.litton.ishirdaily.bean


/**
 * Created by littonishir on 2018/8/3.
 */
@Poko
data class Person(val name:String,val age:Int){
    fun sayHello(other:String):String{
        return "Hello $other"
    }
}