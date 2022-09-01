package mob.dau.memehunter.ui

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import mob.dau.memehunter.databinding.FragmentInitialBinding

class InitialFragment : Fragment() {
    private val viewModel: MemesViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentInitialBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = this@InitialFragment.viewModel

        binding.memesRecyclerView.adapter = MemesGridAdapter()

        return binding.root
    }
}