package com.ending0421.multirepo.impl

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ending0421.multirepo.data.BannerApiData
import org.koin.androidx.compose.koinViewModel

@Composable
fun SpecificScreen(
    viewModel: SpecificViewModel = koinViewModel<SpecificViewModel>(), modifier: Modifier = Modifier
) {
    val dataState by viewModel.data.collectAsState()
    val specificErrorState by viewModel.specificError.collectAsState()
    when (dataState) {
        is MResult.Loading -> {
            Box(
                contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }

        is MResult.Success -> {
            val data = (dataState as MResult.Success<BannerApiData>).data
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = data.toString(), // Replace with appropriate data rendering
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        is MResult.Error -> {
            // Handle specific errors if needed
            val error = (dataState as MResult.Error<*>).error
            when (error) {
                is SpecificErrorType.SpecificBusinessError -> {

                }

                is SpecificErrorType.UnknownSpecificError -> {

                }
            }
            Box(
                contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = error.message ?: "An error occurred",
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        null -> {
            // Initial state
        }
    }

    when (specificErrorState) {
        is SpecificErrorType.SpecificBusinessError -> {
            val error = specificErrorState as SpecificErrorType.SpecificBusinessError
            Box(
                contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = error.message ?: "Specific business error occurred",
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        is SpecificErrorType.UnknownSpecificError -> {
            Box(
                contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "Unknown specific error occurred",
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        null -> {
            // Initial state
        }
//        }
    }

    LaunchedEffect(Unit) {
        viewModel.fetchData()
    }
}