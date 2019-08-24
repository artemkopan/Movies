package com.movie.data.system

import android.content.Context
import com.movie.data.R
import com.movie.domain.system.KeysProvider

class KeysProviderImpl(private val context: Context) : KeysProvider {

    override val apiKey: String
        get() = context.getString(R.string.api_key)

}