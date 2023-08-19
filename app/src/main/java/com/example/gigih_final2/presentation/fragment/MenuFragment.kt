package com.example.gigih_final2.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.gigih_final2.R
import com.example.gigih_final2.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    private val menuIcons = arrayOf(R.drawable.baseline_settings_36, R.drawable.baseline_info_48)
    private val menuTitles = arrayOf("Settings", "About")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuList: MutableList<Map<String, Any>> = mutableListOf()
        for (i in menuTitles.indices) {
            val menuItem = mapOf(
                "menuTitle" to menuTitles[i],
                "menuIcon" to menuIcons[i]
            )
            menuList.add(menuItem)
        }

        val from = arrayOf("menuTitle", "menuIcon")
        val to = intArrayOf(R.id.tvMenuName, R.id.imgMenuItem)

        val adapter = SimpleAdapter(context, menuList, R.layout.item_menu, from, to)
        binding.listViewMenu.adapter = adapter

        binding.listViewMenu.setOnItemClickListener { parent, view, position, id ->
            when (menuTitles[position]) {
                "Settings" -> {
                    findNavController().navigate(R.id.settingFragment)
                }

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
