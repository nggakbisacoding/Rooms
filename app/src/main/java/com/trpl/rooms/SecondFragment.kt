package com.trpl.rooms

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.room.Room
import com.trpl.rooms.databinding.FragmentSecondBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private lateinit var database: UserDatabase

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createDB()
        val pos = requireArguments().getInt("pos")
        val data = UserObject.getData(pos)

        val s = _binding!!.createName.text
        val a = _binding!!.createPhone.text
        val b = _binding!!.createEmail.text

        _binding?.updateButton?.setOnClickListener {
            val name = _binding!!.createName.text.toString()
            val phone = _binding!!.createPhone.text.toString()
            val email = _binding!!.createEmail.text.toString()
            val pass = _binding!!.createPass.text.toString()
            UserObject.updateData(pos, name, phone, email, pass)
            GlobalScope.launch {
                database.dao().updateTask(UserModel(pos, name, phone, email, pass))
            }
            myIntent()
        }
        _binding?.deleteButton?.setOnClickListener {
            UserObject.deleteData(pos)
            GlobalScope.launch {
                database.dao().deleteTask(
                    UserModel(
                        pos + 1,
                        _binding!!.createName.text.toString(),
                        _binding!!.createPhone.text.toString(),
                        _binding!!.createName.text.toString(),
                        _binding!!.createPass.text.toString()
                    )
                )
            }
            myIntent()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun createDB() {
        database = Room.databaseBuilder(
            requireContext(), UserDatabase::class.java, "user"
        ).build()
    }

    private fun myIntent() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
    }
}