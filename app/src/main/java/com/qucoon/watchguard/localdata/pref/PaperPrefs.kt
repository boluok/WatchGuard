package com.qucoon.watchguard.localdata.pref

import android.app.Application
import android.content.Context
import androidx.lifecycle.LifecycleObserver
import io.paperdb.Paper
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class PaperPrefs: CoroutineScope, LifecycleObserver {
    // Coroutine's background job
    private val job = Job()
    // Define default thread for Coroutine as Main and add job
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job


    constructor(application: Application){
        Paper.init(application)
    }

    constructor(context: Context){
        Paper.init(context)
    }

    private val PHONENUMBER_KEY = "iusniuffwewfwnnwei"
    private val ISLOGGED = "iusnpofiuiuniernriniuffwewfwnnwei"
    private val UUID = "wjmlmwfmmwfmmwe"
    private val PUSHIDKEY = "WFIJWFMNNFEWWFWFN"

    private fun  getStringFromPaperPref(key:String):Deferred<String>{
        return async(Dispatchers.IO) {Paper.book().read(key, "") }
    }

    private fun  getBooleanFromPaperPref(key:String,default:Boolean):Deferred<Boolean>{
        return async(Dispatchers.IO) {Paper.book().read(key, default) }
    }

    private fun saveStringToPaperPref(key:String,value:String){
        launch {
            withContext(Dispatchers.IO){
                Paper.book().write(key, value)
            }
        }
    }
    private fun saveBooleanToPaperPref(key:String,value:Boolean){
        launch {
            withContext(Dispatchers.IO){
                Paper.book().write(key, value)
            }
        }
    }

    fun isUserLogedIn():Deferred<Boolean>{
        return getBooleanFromPaperPref(ISLOGGED,false)
    }

    fun setIsUserLogin(isUserLoggedIn:Boolean){
        saveBooleanToPaperPref(ISLOGGED,isUserLoggedIn)
    }

    fun setPhoneNumber(username: String){
       saveStringToPaperPref(PHONENUMBER_KEY,username)
    }

   fun getPhoneNumber():Deferred<String>{
       return getStringFromPaperPref(PHONENUMBER_KEY)
   }

    fun getDeviceUUID():Deferred<String>{
        return getStringFromPaperPref(UUID)
    }

    fun setDeviceUUID(uuid: String){
        saveStringToPaperPref(UUID,uuid)
    }

    fun getPushID():Deferred<String>{
        return getStringFromPaperPref(PUSHIDKEY)
    }

    fun setPushID(pushid: String){
        saveStringToPaperPref(PUSHIDKEY,pushid)
    }



}