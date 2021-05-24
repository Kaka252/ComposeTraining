package com.test.compose.utils

import com.test.compose.logic.DataHelper
import com.test.compose.logic.Repository

object ServiceLocator {

    /**
     * Provide the Repository instance that ViewModel should depend on.
     */
    fun provideRepository() = Repository(provideDataHelper())

    /**
     * Provide the DataHelper instance that Repository should depend on.
     */
    private fun provideDataHelper() = DataHelper()
}