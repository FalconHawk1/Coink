package com.example.coink.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.coink.R
import com.example.coink.model.DocumentTypeResponse

class DocumentTypeAdapter(private val context: Context, private val documentTypes: List<DocumentTypeResponse>) : BaseAdapter() {

    override fun getCount(): Int {
        return documentTypes.size
    }

    override fun getItem(position: Int): Any {
        return documentTypes[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val documentType = getItem(position) as DocumentTypeResponse
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val customView = inflater.inflate(R.layout.spinner, parent, false) as TextView
        customView.text = documentType.description
        return customView
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val documentType = getItem(position) as DocumentTypeResponse
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val customView = inflater.inflate(R.layout.spinner_item, parent, false) as TextView
        customView.text = documentType.description
        return customView
    }
}
