package com.example.fragmentapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.*

class MainActivity : AppCompatActivity(R.layout.main_activity) {

    class ListFragment : Fragment() {
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            super.onCreateView(inflater, container, savedInstanceState)
            val view = inflater.inflate(R.layout.fragment_list, container, false)

            val button1 = view?.findViewById<Button>(R.id.first_li)
            button1?.setOnClickListener { onClick(button1) }
            val button2 = view?.findViewById<Button>(R.id.second_li)
            button2?.setOnClickListener { onClick(button2) }
            val button3 = view?.findViewById<Button>(R.id.third_li)
            button3?.setOnClickListener { onClick(button3) }

            return view
        }

        private fun onClick(button: Button) {
            val args = Bundle()
            args.putCharSequence("text", button.text)
            parentFragmentManager.popBackStack()
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.item_container, BigListFragment::class.java, args)
                addToBackStack(null)
            }
        }
    }

    class ItemFragment : Fragment() {
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            super.onCreateView(inflater, container, savedInstanceState)
            val view = inflater.inflate(R.layout.fragment_item, container, false)

            val text = arguments?.getCharSequence("text")
            val textView = view?.findViewById<TextView>(R.id.text_view)

            if (text != null && textView != null) {
                textView.text = text
            }

            return view
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.commit  {
                setReorderingAllowed(true)
                add<ListFragment>(R.id.list_container)
            }
        }
    }

    override fun onBackPressed() {
        if (!resources.getBoolean(R.bool.smallScreenFlag)) {
            finish()
        }
        super.onBackPressed()
    }
}