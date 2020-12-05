package tech.jhavidit.redcarpetupassignment.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import tech.jhavidit.redcarpetupassignment.R
import tech.jhavidit.redcarpetupassignment.adapter.NewsHeadlinesAdapter
import tech.jhavidit.redcarpetupassignment.databinding.FragmentHomeBinding
import tech.jhavidit.redcarpetupassignment.viewModel.NewsHeadlinesViewModel
import tech.jhavidit.redcarpetupassignment.viewModel.ViewModelFactory

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: NewsHeadlinesViewModel
    private lateinit var adapter: NewsHeadlinesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        activity?.actionBar?.title="Headlines"
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
activity?.actionBar?.title="headlines"

        val viewModelFactory = ViewModelFactory()
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(NewsHeadlinesViewModel::class.java)
        adapter = NewsHeadlinesAdapter(requireContext())
        getData()
    }

    private fun getData() {
        viewModel.getNewsHeadlines()
        binding.recyclerView.adapter = adapter
        viewModel.showProgress.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.progressBar.visibility = VISIBLE
                binding.recyclerView.visibility = GONE
            } else {
                binding.progressBar.visibility = GONE
                binding.recyclerView.visibility = VISIBLE
            }
        })
        viewModel.showNewsHeadlines.observe(viewLifecycleOwner, Observer {
            adapter.setMusicItem(it.articles!!)
        })
    }
}