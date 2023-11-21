package com.trpl.rooms

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.room.Room
import com.trpl.rooms.databinding.FragmentFirstBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private lateinit var database: UserDatabase

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createDB()
        _binding?.deleteAll?.setOnClickListener {
            UserObject.deleteAll()
            GlobalScope.launch {
                database.dao().deleteAll()
            }
            getItem()
        }
        _binding?.recyclerView?.setOnItemClickListener { _, _, arg2, _ ->
            val ints = Intent(requireContext(), MainActivity::class.java)
            ints.putExtra("pos", arg2)
            startActivity(ints)
        }
        getItem()
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

    private fun getItem() {
        val obs = UserObject.getAllData()
        val mview = _binding!!.recyclerView
        val arrayadapter: ArrayAdapter<List<User>> = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, listOf(obs))
        mview.adapter = arrayadapter
    }
}