package com.example.coink.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
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
        val textView = TextView(context)
        textView.text = documentType.description
        return textView
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return getView(position, convertView, parent)
    }
}
