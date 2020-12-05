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
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home.*
import tech.jhavidit.redcarpetupassignment.R
import tech.jhavidit.redcarpetupassignment.adapter.NewsHeadlinesAdapter
import tech.jhavidit.redcarpetupassignment.databinding.FragmentHomeBinding
import tech.jhavidit.redcarpetupassignment.util.InternetConnectivity
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
        activity?.actionBar?.title = "Headlines"
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.actionBar?.title = "headlines"


        val viewModelFactory = ViewModelFactory()
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(NewsHeadlinesViewModel::class.java)
        adapter = NewsHeadlinesAdapter(requireContext())
        getData()
        binding.swipeRefresh.setOnRefreshListener {
            getData()
        }
    }

    private fun getData() {
        if (!InternetConnectivity.isNetworkAvailable(requireContext())!!) {
            Snackbar.make(binding.coordinatorLayout, "Internet Unavailable", Snackbar.LENGTH_LONG)
                .show()
            binding.progressBar.setAnimation(R.raw.no_internet)
            binding.progressBar.visibility = VISIBLE

        }
        viewModel.getNewsHeadlines()
        binding.recyclerView.adapter = adapter
        viewModel.showProgress.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.progressBar.setAnimation(R.raw.loading)
                binding.progressBar.visibility = VISIBLE
                binding.recyclerView.visibility = GONE
            } else {
                swipe_refresh.isRefreshing = false
                binding.progressBar.visibility = GONE
                binding.recyclerView.visibility = VISIBLE
            }
        })
        viewModel.showNewsHeadlines.observe(viewLifecycleOwner, Observer {
            if (it.totalResults == 0) {
                Snackbar.make(binding.recyclerView, "No news Available", Snackbar.LENGTH_LONG)
                    .show()
                binding.progressBar.setAnimation(R.raw.empty_list)
                binding.progressBar.visibility = VISIBLE
            }
            adapter.setMusicItem(it.articles!!)

        })
    }
}