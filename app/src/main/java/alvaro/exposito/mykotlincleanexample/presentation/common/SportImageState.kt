package alvaro.exposito.mykotlincleanexample.presentation.common

import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver

/** Load state of a sport photo, exposed in semantics so UI tests can check it without the network. */
enum class SportImageState { Loading, Success, Error }

val SportImageStateKey = SemanticsPropertyKey<SportImageState>("SportImageState")

var SemanticsPropertyReceiver.sportImageState by SportImageStateKey
