package com.ending0421.multirepo.result

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ending0421.multirepo.base.CommonRepoError
import kotlinx.coroutines.flow.StateFlow

@Composable
fun CommonErrorDisplayView(
    commonError: StateFlow<CommonRepoError?>, content: @Composable () -> Unit
) {
    val errorState by commonError.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        content()
        if (errorState != null) {
            Surface(
                modifier = Modifier
                    .height(50.dp)
                    .background(Color(0x80000000))
                    .wrapContentSize(Alignment.Center), color = Color.Transparent
            ) {
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surface)
                        .wrapContentSize()
                ) {
                    Text(
                        text = errorState?.message ?: "An error occurred",
                        color = MaterialTheme.colorScheme.onSurface
                    )
//                    Button(onClick = { viewModel.clearCommonError() }) {
//                        Text("Dismiss")
//                    }
                }
            }
        }
    }
}