import com.example.gigih_final2.domain.Entity.Entities
import com.example.gigih_final2.domain.UseCase.GetDisasterReportsUseCase
import com.example.gigih_final2.presentation.viewmodel.ReportViewModel
import com.example.gigih_final2.utils.ResultState
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ReportViewModelTest {

    private lateinit var viewModel: ReportViewModel
    private val getDisasterReportsUseCase: GetDisasterReportsUseCase = mockk(relaxed = true)

    @Before
    fun setUp() {
        coEvery { getDisasterReportsUseCase.getAllReportData(any(), any()) } returns flowOf(ResultState.Success(listOf()))
        viewModel = ReportViewModel(getDisasterReportsUseCase)
    }

    @Test
    fun `callApi sets reports LiveData`() = runBlockingTest {
        val expectedValue = ResultState.Success(listOf<Entities>())
        viewModel.callApi(null, null)
        val actualValue = viewModel.reports.value
        Assert.assertEquals(expectedValue, actualValue)
    }

    @Test
    fun `callApi is called during initialization`() = runBlockingTest {
        verify { getDisasterReportsUseCase.getAllReportData(null, null) }
    }


}
