package com.example.roomdatabasedemo.repository

import androidx.lifecycle.LiveData
import com.example.roomdatabasedemo.data.UserDae
import com.example.roomdatabasedemo.model.User

class UserRepository(private val userDae: UserDae) {
    val readAllData :LiveData<List<User>> = userDae.readAllData()

    suspend fun addUser(user: User)
    {
        userDae.addUser(user)
    }
    suspend fun updateUser(user: User)
    {
        userDae.updateUser(user)
    }
    suspend fun deleteUser(user: User){
        userDae.deleteUser(user)
    }
    suspend fun deleteAllUsers(){
        userDae.deleteAllUsers()
    }
    fun searchDatabase(searchQuery: String): LiveData<List<User>>
    {
       return userDae.searchDatabase(searchQuery)
    }
}