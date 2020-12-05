package tech.jhavidit.redcarpetupassignment.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import tech.jhavidit.redcarpetupassignment.R
import tech.jhavidit.redcarpetupassignment.databinding.FragmentDetailsBinding
import tech.jhavidit.redcarpetupassignment.util.getPeriod
import tech.jhavidit.redcarpetupassignment.util.globalTimeDateFormat


class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = "Headline Details"
        navController = Navigation.findNavController(view)
        var author = "Publisher - " + arguments?.getString("author","Unknown")
        val description = arguments?.getString("description")
        val title = arguments?.getString("title")
        val source = arguments?.getString("source")
        val url = arguments?.getString("url")
        val photo = arguments?.getString("photo")
        var date = arguments?.getString("date")
        date = "Published : " + getPeriod(globalTimeDateFormat(date!!)!!)

        binding.articleHeading.text = title
        binding.author.text = author
        binding.date.text = date
        binding.summary.text = description
        Glide.with(requireContext()).load(photo)
            .into(binding.articlePhoto)


        binding.webView.setOnClickListener {
            val bundle = bundleOf("url" to url)
            it.findNavController()
                .navigate(R.id.action_detailsFragment_to_webFragment, bundle)
        }

    }

}