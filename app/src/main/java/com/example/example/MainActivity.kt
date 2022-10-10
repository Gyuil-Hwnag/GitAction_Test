package com.example.example

import android.content.res.Configuration
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

//    private val stringState: MutableSharedFlow<String> = MutableSharedFlow(1, 1)

//    private val stringStateFlow: Flow<String> = flow<String> {
//        for(i in 0..1000) {
//            emit(i.toString())
//            delay(500)
//        }
//    }.stateIn(
//        scope = lifecycleScope,
//        started = SharingStarted.WhileSubscribed(1000),
//        initialValue = ""
//    )
//
//    private val stringFlow: Flow<String> = flow<String> {
//        for(i in 0..1000) {
//            emit(i.toString())
//            delay(500)
//        }
//    }.shareIn(
//        scope = lifecycleScope,
//        started = SharingStarted.WhileSubscribed(1000)
//    )

    private val flowTest: Flow<String> = flow {
        for(i in 0..1000) {
            emit(i.toString())
            delay(100)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val res: Resources = Resources.getSystem()
        val config: Configuration = res.configuration
        onConfigurationChanged(config)

        lifecycleScope.launchWhenStarted {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                flowTest.collect {
                    println(it)
                }
            }
        }

//        lifecycleScope.launchWhenStarted {
//            flowTest.collect {
//                println(it)
//            }
//            flowTest.collectIndexed { index, value ->
//                Log.d("flow collectIndexed :", "$index $value")
//            }
//
//            launch {
//                flowTest.collectLatest {
//                    Log.d("flow collectLastest : ", it)
//                    delay(1000)
//                }
//            }
//
//            launch {
//                flowTest.collect {
//                    Log.d("flow collect : ", it)
//                    delay(1000)
//                }
//            }
//        }
    }
}

