import androidx.work.WorkManager
import com.example.gigih_final2.domain.UseCase.ReportPeriodUseCase
import com.example.gigih_final2.domain.UseCase.ThemeDarkUseCase
import com.example.gigih_final2.presentation.viewmodel.SettingViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class SettingViewModelTest {

    private lateinit var viewModel: SettingViewModel
    private val themeDarkUseCase: ThemeDarkUseCase = mockk(relaxed = true)
    private val reportPeriodUseCase: ReportPeriodUseCase = mockk(relaxed = true)
    private val workManager: WorkManager = mockk(relaxed = true)  // Mocking WorkManager

    @Before
    fun setUp() {
        viewModel = SettingViewModel(themeDarkUseCase, reportPeriodUseCase, workManager)  // Passing the mocked WorkManager
    }

    @Test
    fun `activateDarkTheme calls enableDarkTheme`() {
        viewModel.activateDarkTheme()
        verify { themeDarkUseCase.enableDarkTheme() }
    }

    @Test
    fun `deactivateDarkTheme calls disableDarkTheme`() {
        viewModel.deactivateDarkTheme()
        verify { themeDarkUseCase.disableDarkTheme() }
    }

    @Test
    fun `isDarkModeActive gets dark theme status`() {
        every { themeDarkUseCase.isDarkThemeEnabled() } returns true
        assert(viewModel.isDarkModeActive)
    }


}
