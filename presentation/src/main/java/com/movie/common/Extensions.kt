@file:Suppress("NOTHING_TO_INLINE")

package com.movie.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes


inline fun ViewGroup.inflate(@LayoutRes l: Int): View =
    LayoutInflater.from(context).inflate(l, this, false)
