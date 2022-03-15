package com.example.oceangrsmithassessment.ui.view

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.oceangrsmithassessment.R
import com.example.oceangrsmithassessment.databinding.FragmentListBinding
import com.example.oceangrsmithassessment.ui.adapter.FishWatchListAdapter
import com.example.oceangrsmithassessment.util.ConnectivityLiveData
import com.example.oceangrsmithassessment.util.NetworkReceiver
import com.example.oceangrsmithassessment.util.NetworkReceiver.Companion.mobileConnected
import com.example.oceangrsmithassessment.util.NetworkReceiver.Companion.refreshDisplay
import com.example.oceangrsmithassessment.util.NetworkReceiver.Companion.wifiConnected
import com.example.oceangrsmithassessment.util.Resource
import com.example.oceangrsmithassessment.util.getFishWatchList
import com.example.oceangrsmithassessment.viewmodel.FishWatchListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment(R.layout.fragment_list) {
    private lateinit var fishWatchListAdapter: FishWatchListAdapter
    private val viewModel: FishWatchListViewModel by activityViewModels()
    private var fishWatchList = getFishWatchList()
    private lateinit var binding: FragmentListBinding

    // The BroadcastReceiver that tracks network connectivity changes.
    private lateinit var receiver: NetworkReceiver
    private lateinit var connectivityLiveData: ConnectivityLiveData


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)

        fishWatchListAdapter = FishWatchListAdapter()

        viewModel.loadPaginatedFishWatchList()
        viewModel.fishWatchList.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    response.data?.let { data ->
                        binding.progressBar.visibility = View.INVISIBLE
                        binding.recyclerView.adapter = fishWatchListAdapter
                        val fishList = data.toMutableList()
                        fishWatchListAdapter.differ.submitList(fishList)
                    }

                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    Toast.makeText(
                        requireContext(),
                        "An Error Occurred: $response",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        /*Setup Search functionality*/
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.searchFishWatch(it)}
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { viewModel.searchFishWatch(it) }
                return false
            }
        })

        /*Setup Rv Swipe to Refresh*/
        binding.swipeRefresh.setOnRefreshListener {
            updateConnectedFlags()
            if (refreshDisplay) {
                viewModel.loadPaginatedFishWatchList()
                loadPage()
            }
            binding.swipeRefresh.isRefreshing = false
        }

    }

    /*Unregister Network Receiver to Prevent Memory Leak*/
    override fun onDestroy() {
        super.onDestroy()
        /*Unregisters BroadcastReceiver when app is destroyed.*/
        activity?.unregisterReceiver(receiver)
    }

    /*Function to display fishWatch list if network connection available*/
    private fun loadPage() {
        if (wifiConnected || mobileConnected) {
            binding.recyclerView.visibility = View.VISIBLE
            binding.statusButton.visibility = View.INVISIBLE
            viewModel.fishWatchList.observe(
                viewLifecycleOwner,
                Observer {

                }
            )
        }
    }

    private fun updateConnectedFlags() {
        val connMgr = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeInfo: NetworkInfo? = connMgr.activeNetworkInfo
        if (activeInfo?.isConnected == true) {
            binding.recyclerView.visibility = View.VISIBLE
            binding.statusButton.visibility = View.INVISIBLE
            wifiConnected = activeInfo.type == ConnectivityManager.TYPE_WIFI
            mobileConnected = activeInfo.type == ConnectivityManager.TYPE_MOBILE
        } else {
            wifiConnected = false
            mobileConnected = false
            binding.recyclerView.visibility = View.INVISIBLE
            binding.statusButton.visibility = View.VISIBLE
            hideProgressBar()
        }

    }
    private fun hideProgressBar() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

}
