package com.andigeeky.rickandmorty.characters.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.lifecycle.viewModelScope
import com.andigeeky.rickandmorty.characters.di.characters_module
import com.andigeeky.rickandmorty.databinding.FragmentCharactersBinding
import com.andigeeky.rickandmorty.utilities.scrollWithDebounce
import io.uniflow.android.livedata.onEvents
import io.uniflow.android.livedata.onStates
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class CharactersFragment : Fragment() {
    private val vm: CharactersViewModel by viewModel()
    private val adapter = CharactersAdapter {
        it.id?.id?.let { id ->
            findNavController().navigate(
                CharactersFragmentDirections.navigateToCharacterDetail(id)
            )
        }
    }
    private lateinit var binding: FragmentCharactersBinding

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

    @OptIn(FlowPreview::class)
    private fun setupUI() {
        binding.listCharacters.adapter = adapter
        binding.listCharacters.scrollWithDebounce()
            .conflate()
            .onEach { position ->
                if (position == adapter.itemCount - 1 && !binding.loadingNextPage.isVisible) {
                    vm.sendLoadNextPageEvent()
                }
            }
            .debounce(DEBOUNCE_SCROLL_MILLISECONDS)
            .launchIn(vm.viewModelScope)
    }

    private fun setupStates() {
        onStates(vm) {
            when (it) {
                is CharactersState -> adapter.submitList(it.characters.toList())
            }
        }
    }

    private fun setupEvents() {
        onEvents(vm) {
            when (it) {
                CharactersEvent.ShowLoadingCharacters -> {
                    binding.loading.isVisible = true
                }
                CharactersEvent.HideLoadingCharacters -> {
                    binding.loading.isVisible = false
                }
                is CharactersEvent.LoadNextCharactersPage -> vm.loadNextPageCharacters(
                    page = it.page
                )
                CharactersEvent.ShowLoadingNextPageCharacters -> {
                    binding.loadingNextPage.isVisible = true
                }
                CharactersEvent.HideLoadingNextPageCharacters -> {
                    binding.loadingNextPage.isVisible = false
                }
            }
        }
    }

    companion object{
        private const val DEBOUNCE_SCROLL_MILLISECONDS = 1000L
    }
}