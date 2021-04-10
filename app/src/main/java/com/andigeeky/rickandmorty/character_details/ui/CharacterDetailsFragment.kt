package com.andigeeky.rickandmorty.character_details.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.andigeeky.rickandmorty.R
import com.andigeeky.rickandmorty.character_details.di.character_details_module
import com.andigeeky.rickandmorty.character_details.model.CharacterDetails
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
                is CharacterDetailsState -> updateUI(it.details)
            }
        }
    }

    private fun updateUI(details : CharacterDetails) {
        binding.characterImage.load(details.url)
        binding.contentCharacterDetails.characterName.text =
            getString(R.string.character_details_name, details.name)
        binding.contentCharacterDetails.characterCurrentLocation.text =
            getString(R.string.character_details_current_location, details.current?.name.orEmpty())
        binding.contentCharacterDetails.characterOrigin.text =
            getString(R.string.character_details_origin_location, details.origin?.name.orEmpty())
        binding.contentCharacterDetails.characterGender.text =
            getString(R.string.character_details_gender, details.gender.key)
        binding.contentCharacterDetails.characterSpecies.text =
            getString(R.string.character_details_species, details.spices)
        binding.contentCharacterDetails.characterStatus.text =
            getString(R.string.character_details_status, details.status.key)
        binding.contentCharacterDetails.characterEpisodes.text =
            getString(R.string.character_details_episodes, details.episodes.size)
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