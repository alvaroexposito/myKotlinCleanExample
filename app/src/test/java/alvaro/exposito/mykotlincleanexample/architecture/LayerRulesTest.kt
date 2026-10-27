package alvaro.exposito.mykotlincleanexample.architecture

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.verify.assertFalse
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.jupiter.api.Test

/** Constitution Article II: `presentation → domain ← data`. */
class LayerRulesTest {
    private val base = "alvaro.exposito.mykotlincleanexample"
    private val mainFiles = Konsist.scopeFromProduction()

    @Test
    fun `AC-10 domain has no Android, data or presentation imports`() {
        mainFiles.files
            .filter { it.packagee?.name?.startsWith("$base.domain") == true }
            .assertFalse { file ->
                file.imports.any {
                    it.name.startsWith("android.") ||
                        it.name.startsWith("androidx.") ||
                        it.name.startsWith("$base.data") ||
                        it.name.startsWith("$base.presentation") ||
                        it.name == "$base.R"
                }
            }
    }

    @Test
    fun `AC-10 data does not depend on presentation`() {
        mainFiles.files
            .filter { it.packagee?.name?.startsWith("$base.data") == true }
            .assertFalse { file -> file.imports.any { it.name.startsWith("$base.presentation") } }
    }

    @Test
    fun `AC-10 presentation does not depend on data`() {
        mainFiles.files
            .filter { it.packagee?.name?.startsWith("$base.presentation") == true }
            .assertFalse { file -> file.imports.any { it.name.startsWith("$base.data") } }
    }

    @Test
    fun `AC-10 use cases follow VerbNounUseCase naming`() {
        mainFiles.classes()
            .filter { it.resideInPackage("$base.domain.usecase..") }
            .assertTrue { it.name.endsWith("UseCase") }
    }
}
