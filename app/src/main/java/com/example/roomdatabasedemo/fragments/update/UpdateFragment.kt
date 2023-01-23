package com.example.roomdatabasedemo.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomdatabasedemo.R
import com.example.roomdatabasedemo.model.User
import com.example.roomdatabasedemo.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*



class UpdateFragment : Fragment() {
    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mUserViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        view.updateFirstName_et.setText(args.currenUser.firstName)
        view.updateLastName_et.setText(args.currenUser.lastName)
        view.updateAge_et.setText(args.currenUser.age.toString())
        view.update_btn.setOnClickListener {
       updateItem()
        }
          setHasOptionsMenu(true)

        return view
    }

    private fun updateItem() {
        val firstName = updateFirstName_et.text.toString()
        val lastName = updateLastName_et.text.toString()
        val age = Integer.parseInt(updateAge_et.text.toString())

        if (inputCheck(firstName, lastName, updateAge_et.text)){
        val updatedUser = User(args.currenUser.id, firstName, lastName,age)
        mUserViewModel.updateUser(updatedUser)
        Toast.makeText(requireContext(), "updated successfully ", Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.action_updateFragment2_to_listFragment)
    }
    else
    {
        Toast.makeText(requireContext(), "please fill ", Toast.LENGTH_LONG).show()
    }
}
    private fun inputCheck(firstName: String , lastName: String ,age: Editable):Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
       inflater.inflate(R.menu.delete_menu,menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }



    private fun deleteUser() {
       val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("yes"){_,_->
       mUserViewModel.deleteUser(args.currenUser)
            Toast.makeText(
            requireContext(),"Successfully deleted ${args.currenUser.firstName}",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment2_to_listFragment)
        }
        builder.setNegativeButton("No"){_,_-> }
        builder.setTitle("Delete ${args.currenUser.firstName}?")
        builder.setMessage("Are you sur you want to delete ${args.currenUser.firstName}")
        builder.create().show()
    }


}