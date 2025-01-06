import builder.TestContextBuilder
import com.intellij.driver.sdk.openFile
import com.intellij.driver.sdk.ui.components.editor
import com.intellij.driver.sdk.ui.components.ideFrame
import com.intellij.driver.sdk.ui.components.searchEverywherePopup
import com.intellij.driver.sdk.ui.components.terminalToolWindow
import com.intellij.driver.sdk.waitForIndicators
import com.intellij.ide.starter.driver.engine.runIdeWithDriver
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.time.Duration.Companion.minutes

class AssignmentTest {

    @ParameterizedTest
    @CsvSource(
        "IC, skrabec/LeetCodeTDD, main, 2024.3",
        "WS, skrabec/LeetCodeTDD, main, 2024.3"
    )
    fun exampleParametrizedTest(
        ide: String,
        repo: String,
        branch: String,
        ideVersion: String
    ) {
        TestContextBuilder()
            .withIde(ide)
            .withRepository(repo)
            .withBranch(branch)
            .withIDEVersion(ideVersion)
            .build()


            .runIdeWithDriver().useDriverAndCloseIde {
                waitForIndicators(5.minutes)
                openFile("pom.xml")

                ideFrame {
                    editor {
                        keyboard {
                            enterText("THIS IS A TEST")
                        }
                    }

                    keyboard {
                        hotKey(18, 123)
                    }
                    terminalToolWindow {
                        keyboard {
                            enterText("git --help")
                            enter()
                        }
                    }

                    keyboard {
                        hotKey(17, 16, 70)
                    }

                    searchEverywherePopup {
                        keyboard {
                            enterText("THIS IS A TEST")
                        }
                    }
                }
            }
    }
}