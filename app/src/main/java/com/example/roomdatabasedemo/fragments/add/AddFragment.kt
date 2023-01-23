package com.example.roomdatabasedemo.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomdatabasedemo.R
import com.example.roomdatabasedemo.model.User
import com.example.roomdatabasedemo.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*


class AddFragment : Fragment() {
    private lateinit var mUserViewModel : UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_add,container,false)
        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        view.add_btn.setOnClickListener{
           insertDataToDatabase()
       }

        return view
    }

    private fun insertDataToDatabase() {
        val firstName = addFirstName_et.text.toString()
        val lastName = addLastName_et.text.toString()
        val age = addAge_et.text

      if(inputCheck(firstName,lastName,age)){
          val user = User(0,firstName,lastName,Integer.parseInt(age.toString()))
          mUserViewModel.addUser(user)
          Toast.makeText(requireContext(),"successfully added",Toast.LENGTH_LONG).show()
          findNavController().navigate(R.id.action_addFragment_to_listFragment)
      }else{
          Toast.makeText(requireContext(),"please fill out all fields.",Toast.LENGTH_LONG).show()
      }


    }
    private fun inputCheck(firstName: String , lastName: String ,age: Editable):Boolean{
       return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }
}