package com.example.studyapp

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BusinessFragment : Fragment() {
    private lateinit var linearLayoutWords: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_business, container, false)
        linearLayoutWords = view.findViewById(R.id.linearLayoutWords)
        val fab: FloatingActionButton = view.findViewById(R.id.fab_add)
        fab.setOnClickListener {
            showAddWordDialog()
        }
        return view
    }

    private fun showAddWordDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_word, null)
        val editText = dialogView.findViewById<EditText>(R.id.editTextWord)
        val addButton = dialogView.findViewById<TextView>(R.id.buttonAdd) // TextView로 변경

        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("단어나 문장 추가")
            .setView(dialogView)
            .create()

        addButton.setOnClickListener {
            val text = editText.text.toString().trim()
            if (text.isNotEmpty()) {
                addWord(text)
                dialog.dismiss()
            } else {
                editText.error = "내용을 입력해주세요"
            }
        }

        dialog.show()
    }

    private fun addWord(word: String) {
        val textView = TextView(requireContext()).apply {
            text = word
            textSize = 18f
            setPadding(0, 8, 0, 8)
        }
        linearLayoutWords.addView(textView)
    }
}
