package com.example.ui

import androidx.lifecycle.ViewModel
import com.example.data.CourseTrackId
import com.example.data.GalleryItem
import com.example.data.TuitionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

enum class AppTab(val route: String) {
    HOME("home"),
    COURSES("courses"),
    MENTOR("mentor"),
    GALLERY("gallery"),
    CONTACT("contact"),
}

enum class SectionTarget(val tab: AppTab, val itemIndex: Int) {
    HOME_TOP(AppTab.HOME, 0),
    ABOUT(AppTab.HOME, 1),
    FEATURES(AppTab.HOME, 3),
    WHY_CHOOSE_US(AppTab.HOME, 4),
    COURSES_TOP(AppTab.COURSES, 0),
    MENTOR_TOP(AppTab.MENTOR, 0),
    GALLERY_TOP(AppTab.GALLERY, 0),
    CONTACT_FORM(AppTab.CONTACT, 1),
    GOOGLE_MAPS(AppTab.CONTACT, 2),
}

data class EnquiryFormState(
    val studentName: String = "",
    val selectedClass: String = "Class IX",
    val phoneNumber: String = "",
    val notes: String = "",
    val nameError: Boolean = false,
    val phoneError: Boolean = false,
)

data class ScrollRequest(
    val target: SectionTarget,
    val nonce: Long = System.nanoTime(),
)

data class AlorDishariUiState(
    val activeTab: AppTab = AppTab.HOME,
    val selectedCourseFilter: CourseTrackId = CourseTrackId.ALL,
    val expandedCourseIds: Set<CourseTrackId> = setOf(
        CourseTrackId.V_VIII,
        CourseTrackId.IX_X,
        CourseTrackId.XI_XII,
    ),
    val selectedGalleryItem: GalleryItem? = null,
    val isNavigationMenuOpen: Boolean = false,
    val enquiryForm: EnquiryFormState = EnquiryFormState(),
    val pendingScroll: ScrollRequest? = null,
)

class AlorDishariViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AlorDishariUiState())
    val uiState: StateFlow<AlorDishariUiState> = _uiState.asStateFlow()

    fun selectTab(tab: AppTab) {
        _uiState.update {
            it.copy(
                activeTab = tab,
                isNavigationMenuOpen = false,
            )
        }
    }

    fun jumpToSection(target: SectionTarget) {
        _uiState.update {
            it.copy(
                activeTab = target.tab,
                isNavigationMenuOpen = false,
                pendingScroll = ScrollRequest(target = target),
            )
        }
    }

    fun consumeScrollRequest() {
        _uiState.update { it.copy(pendingScroll = null) }
    }

    fun openCoursesForTrack(trackId: CourseTrackId) {
        _uiState.update {
            it.copy(
                activeTab = AppTab.COURSES,
                selectedCourseFilter = trackId,
                expandedCourseIds = it.expandedCourseIds + trackId,
                isNavigationMenuOpen = false,
                pendingScroll = ScrollRequest(SectionTarget.COURSES_TOP),
            )
        }
    }

    fun selectCourseFilter(filter: CourseTrackId) {
        _uiState.update { it.copy(selectedCourseFilter = filter) }
    }

    fun toggleCourseExpanded(trackId: CourseTrackId) {
        _uiState.update { current ->
            val updated = if (trackId in current.expandedCourseIds) {
                current.expandedCourseIds - trackId
            } else {
                current.expandedCourseIds + trackId
            }
            current.copy(expandedCourseIds = updated)
        }
    }

    fun openGalleryPreview(item: GalleryItem) {
        _uiState.update { it.copy(selectedGalleryItem = item) }
    }

    fun closeGalleryPreview() {
        _uiState.update { it.copy(selectedGalleryItem = null) }
    }

    fun setNavigationMenuOpen(open: Boolean) {
        _uiState.update { it.copy(isNavigationMenuOpen = open) }
    }

    fun updateStudentName(value: String) {
        _uiState.update {
            it.copy(
                enquiryForm = it.enquiryForm.copy(
                    studentName = value,
                    nameError = false,
                ),
            )
        }
    }

    fun updateSelectedClass(value: String) {
        _uiState.update {
            it.copy(enquiryForm = it.enquiryForm.copy(selectedClass = value))
        }
    }

    fun updatePhoneNumber(value: String) {
        val cleaned = value.filter { it.isDigit() || it == '+' || it == ' ' || it == '-' }.take(15)
        _uiState.update {
            it.copy(
                enquiryForm = it.enquiryForm.copy(
                    phoneNumber = cleaned,
                    phoneError = false,
                ),
            )
        }
    }

    fun updateNotes(value: String) {
        _uiState.update {
            it.copy(enquiryForm = it.enquiryForm.copy(notes = value.take(280)))
        }
    }

    fun validateAndBuildEnquiryMessage(): String? {
        val form = _uiState.value.enquiryForm
        val digitsOnly = form.phoneNumber.filter { it.isDigit() }
        val isNameValid = form.studentName.trim().length >= 2
        val isPhoneValid = digitsOnly.length >= 10

        if (!isNameValid || !isPhoneValid) {
            _uiState.update {
                it.copy(
                    enquiryForm = form.copy(
                        nameError = !isNameValid,
                        phoneError = !isPhoneValid,
                    ),
                )
            }
            return null
        }

        return TuitionRepository.buildEnquiryWhatsAppMessage(
            studentName = form.studentName,
            studentClass = form.selectedClass,
            phone = form.phoneNumber,
            notes = form.notes,
        )
    }
}
