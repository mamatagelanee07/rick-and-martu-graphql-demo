package com.andigeeky.rickandmorty.characters.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.andigeeky.rickandmorty.characters.di.characters_module
import com.andigeeky.rickandmorty.databinding.FragmentCharactersBinding
import io.uniflow.android.livedata.onEvents
import io.uniflow.android.livedata.onStates
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class CharactersFragment : Fragment() {
    private val vm : CharactersViewModel by viewModel()
    private val adapter = CharactersAdapter()
    private lateinit var binding : FragmentCharactersBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadKoinModules(characters_module)
        setupEvents()
        setupStates()
        setupUI()
        vm.loadCharacters()
    }

    private fun setupUI() {
        binding.listCharacters.adapter = adapter
    }

    private fun setupStates() {
        onStates(vm){
            when(it){
                is CharactersState -> {
                    adapter.submitList(it.characters)
                }
            }
        }
    }

    private fun setupEvents() {
        onEvents(vm){
            when(it){
                CharactersEvent.ShowLoadingCharacters -> {
                    binding.loading.isVisible = true
                }
                CharactersEvent.HideLoadingCharacters -> {
                    binding.loading.isVisible = false
                }
            }
        }
    }
}