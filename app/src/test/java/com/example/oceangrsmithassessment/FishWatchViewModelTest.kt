package com.example.oceangrsmithassessment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.oceangrsmithassessment.di.NetworkModule
import com.example.oceangrsmithassessment.viewmodel.FishWatchListViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class FishWatchViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var networkModule: NetworkModule

    private lateinit var fishWatchListViewModel: FishWatchListViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    suspend fun fetchRepositories_positiveResponse() {
    }

}