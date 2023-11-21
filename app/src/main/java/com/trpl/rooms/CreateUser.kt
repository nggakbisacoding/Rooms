package com.trpl.rooms

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.trpl.rooms.databinding.ActivityCreateUserBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CreateUser : AppCompatActivity() {
    private lateinit var binding: ActivityCreateUserBinding
    private lateinit var database: UserDatabase

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCreateUserBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
            database = Room.databaseBuilder(
                applicationContext, UserDatabase::class.java, "user"
            ).build()
            with(binding) {
                saveButton.setOnClickListener {
                if (createName.text.toString().trim { it <= ' ' }.isNotEmpty()
                    && createEmail.text.toString().trim { it <= ' ' }.isNotEmpty()
                ) {
                    val name = createName.text.toString()
                    val phone = createEmail.text.toString()
                    val email = createEmail.text.toString()
                    val pass = createPass.text.toString()
                    UserObject.setData(name, phone, email, pass)
                    GlobalScope.launch {
                        database.dao().insertTask(UserModel(0, name, phone, email, pass))
                    }

                    val intent = Intent(this@CreateUser, MainActivity::class.java)
                    startActivity(intent)
                }
                }
            }
    }
}