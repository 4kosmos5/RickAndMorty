package com.semyon.rickandmorty.ui.screen.episode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.semyon.rickandmorty.databinding.FragmentEpisodeBinding
import com.semyon.rickandmorty.ui.adapters.CharacterAdapter
import com.semyon.rickandmorty.ui.screen.character.CharacterViewModel
import com.semyon.rickandmorty.R
import com.semyon.rickandmorty.data.model.CharacterModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class EpisodeFragment : Fragment(), CharacterAdapter.Listener {

    private val viewModel by viewModel<CharacterViewModel>()
    private lateinit var characterAdapter: CharacterAdapter
    private lateinit var binding: FragmentEpisodeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEpisodeBinding.inflate(inflater, container, false)
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
        findNavController().navigate(R.id.action_episodeFragment_to_infoCharacterFragment, bundle)
    }
}