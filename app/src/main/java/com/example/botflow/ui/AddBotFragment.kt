package com.example.botflow.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.botflow.R

class AddBotFragment : Fragment() {
    private lateinit var email: String
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        email = activity!!.intent.getStringExtra("email")!!
        return inflater.inflate(R.layout.add_bot_fragment, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //собрать обьект класса Bot на основе email(уже есть из интента) и имени (введет пользователь) и при нажатии... пока ничего не делать
        //если что макет еще не готов
    }
}