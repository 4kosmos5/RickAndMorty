package com.example.rickandmortysemyon.ui.screen.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortysemyon.R
import com.example.rickandmortysemyon.data.model.CharacterModel
import com.example.rickandmortysemyon.databinding.FragmentCharacterBinding
import com.example.rickandmortysemyon.ui.adapters.CharacterAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterFragment : Fragment(), CharacterAdapter.Listener {

    private val viewModel by viewModel<CharacterViewModel>()
    private lateinit var characterAdapter: CharacterAdapter
    private lateinit var binding: FragmentCharacterBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCharacterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*button.setOnClickListener {
            findNavController().navigate(R.id.action_first_screen)
        }
*/
        characterAdapter = CharacterAdapter(this)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = characterAdapter
        }

        lifecycleScope.launch {
            viewModel.characters.collectLatest {
                characterAdapter.submitData(it)
            }
        }
    }

    override fun openInfoFragment(model: CharacterModel) {
        val bundle = Bundle()
        bundle.putParcelable("character", model)
       findNavController().navigate(R.id.action_leaderboard_to_userProfile, bundle)
    }
}