package com.example.studyapp

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BusinessFragment : Fragment() {
    private lateinit var wordList: MutableList<String>
    private lateinit var adapter: WordPagerAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_business, container, false)

        wordList = mutableListOf("会議", "報告", "残業", "取引") // Business
        adapter = WordPagerAdapter(wordList)

        viewPager = view.findViewById(R.id.viewPagerWords)
        viewPager.adapter = adapter

        view.findViewById<FloatingActionButton>(R.id.fab_add).setOnClickListener {
            showAddWordDialog()
        }

        return view
    }

    private fun showAddWordDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_word, null)
        val editText = dialogView.findViewById<EditText>(R.id.editTextWord)
        val addButton = dialogView.findViewById<TextView>(R.id.buttonAdd)

        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("단어나 문장 추가")
            .setView(dialogView)
            .create()

        addButton.setOnClickListener {
            val text = editText.text.toString().trim()
            if (text.isNotEmpty()) {
                wordList.add(text)
                adapter.notifyItemInserted(wordList.size - 1)
                viewPager.setCurrentItem(wordList.size - 1, true)
                dialog.dismiss()
            } else {
                editText.error = "내용을 입력해주세요"
            }
        }

        dialog.show()
    }
}
