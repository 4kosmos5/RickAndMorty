package com.semyon.rickandmorty.ui.screen.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.semyon.rickandmorty.data.model.CharacterModel
import com.semyon.rickandmorty.databinding.FragmentInfoCharacterBinding

class InfoCharacterFragment : Fragment() {

    private lateinit var binding: FragmentInfoCharacterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoCharacterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val model = arguments?.getParcelable<CharacterModel>("character")

        binding.title.text = model?.name
    }


}