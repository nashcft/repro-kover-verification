package com.github.nashcft.repro.kover

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalTestApi
@RunWith(AndroidJUnit4::class)
class GreetingTest {
    @Test
    fun `my first test`() = runComposeUiTest {
        setContent {
            Greeting(name = "World")
        }

        onNode(hasText("Hello World!")).assertExists()
    }
}
