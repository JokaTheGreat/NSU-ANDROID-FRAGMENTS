package com.example.fragmentapplication

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BigListFragment : Fragment() {
    class BigListViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textView: TextView
        val imageView: ImageView

        init {
            textView = view.findViewById(R.id.text_view)
            imageView = view.findViewById(R.id.image_view)
        }
    }

    class BigListAdapter(private val dataSet: Array<CharSequence>, private val image: Drawable?) : RecyclerView.Adapter<BigListViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BigListViewHolder {
            val viewHolder = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_item, parent, false)

            return BigListViewHolder(viewHolder)
        }

        override fun onBindViewHolder(holder: BigListViewHolder, position: Int) {
            holder.textView.text = dataSet[position]
            holder.imageView.setImageDrawable(image)
        }

        override fun getItemCount() = dataSet.size
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val dataSet = Array(100) { arguments?.getCharSequence("text") ?: "some" }

        val view = inflater.inflate(R.layout.fragment_big_list, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = BigListAdapter(dataSet, ResourcesCompat.getDrawable(resources, R.drawable.cat, null))

        return view
    }
}