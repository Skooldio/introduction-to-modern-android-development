@file:Suppress("RedundantSuspendModifier", "unused", "UNUSED_PARAMETER", "RemoveExplicitTypeArguments")

package com.skooldio.githubviewer.misc

import androidx.lifecycle.ViewModel

private class KotlinCoroutinesViewModel : ViewModel() {

    fun getDataA() {

    }

    suspend fun getDataB() {

    }
}

private object SkooldioDataSource {
    fun setup(
        onCompleted: (sessionId: String) -> Unit,
        onFailure: (exception: Exception) -> Unit,
    ) {
        // Do long duration process
        onCompleted("")
    }
}
