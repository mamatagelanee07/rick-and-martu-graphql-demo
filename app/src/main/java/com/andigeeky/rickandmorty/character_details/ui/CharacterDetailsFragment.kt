package com.andigeeky.rickandmorty.character_details.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.andigeeky.rickandmorty.character_details.di.character_details_module
import com.andigeeky.rickandmorty.databinding.FragmentCharacterDetailsBinding
import io.uniflow.android.livedata.onEvents
import io.uniflow.android.livedata.onStates
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class CharacterDetailsFragment : Fragment() {
    private val vm : CharacterDetailsViewModel by viewModel()
    private lateinit var binding : FragmentCharacterDetailsBinding
    val args by navArgs<CharacterDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailsBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadKoinModules(character_details_module)
        setupEvents()
        setupStates()
        setupUI()
        vm.loadCharacterDetails(args.characterId)
    }

    private fun setupUI() {

    }

    private fun setupStates() {
        onStates(vm){
            when(it){
                is CharacterDetailsState -> {
                    binding.characterImage.load(it.details.url)
                }
            }
        }
    }

    private fun setupEvents() {
        onEvents(vm){
            when(it){
                CharacterDetailsEvent.ShowLoadingCharacterDetails -> {
                    binding.contentCharacterDetails.loadingCharacterDetails.isVisible = true
                }
                CharacterDetailsEvent.HideLoadingCharacterDetails -> {
                    binding.contentCharacterDetails.loadingCharacterDetails.isVisible = false
                }
            }
        }
    }
}