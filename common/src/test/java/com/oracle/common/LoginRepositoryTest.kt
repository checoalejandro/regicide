package com.oracle.common

import com.oracle.common.repository.LoginRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class LoginRepositoryTest {

    @Test
    fun unauthorized() {
        val result =
            runBlocking { LoginRepository.doReachabilityTest("https://api.github.com/user") }
        assertFalse(result)
    }

    @Test
    fun success() {
        val result =
            runBlocking { LoginRepository.doReachabilityTest("https://api.github.com/events") }
        assertTrue(result)
    }
}