package com.example.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Collections
import androidx.compose.material.icons.filled.ContactPhone
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.outlined.Collections
import androidx.compose.material.icons.outlined.ContactPhone
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.School
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.R
import com.example.data.TuitionRepository
import com.example.ui.components.AlorDishariTopBar
import com.example.ui.components.QuickNavigationSheet
import com.example.ui.screens.ContactScreen
import com.example.ui.screens.CoursesScreen
import com.example.ui.screens.GalleryScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.MentorScreen
import com.example.ui.theme.DeepNavy
import com.example.ui.theme.GoldLightTint
import com.example.ui.theme.RoyalBlue
import com.example.util.ExternalIntents
import kotlinx.coroutines.launch

private data class NavItemSpec(
    val tab: AppTab,
    val labelRes: Int,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val testTag: String,
)

private val PrimaryNavItems = listOf(
    NavItemSpec(AppTab.HOME, R.string.nav_home, Icons.Filled.Home, Icons.Outlined.Home, "nav_tab_home"),
    NavItemSpec(AppTab.COURSES, R.string.nav_courses, Icons.Filled.School, Icons.Outlined.School, "nav_tab_courses"),
    NavItemSpec(AppTab.MENTOR, R.string.nav_mentor, Icons.Filled.Person, Icons.Outlined.Person, "nav_tab_mentor"),
    NavItemSpec(AppTab.GALLERY, R.string.nav_gallery, Icons.Filled.Collections, Icons.Outlined.Collections, "nav_tab_gallery"),
    NavItemSpec(AppTab.CONTACT, R.string.nav_contact, Icons.Filled.ContactPhone, Icons.Outlined.ContactPhone, "nav_tab_contact"),
)

@Composable
fun AlorDishariApp(
    viewModel: AlorDishariViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val unableToOpenText = stringResource(R.string.toast_unable_open_link)

    val homeListState = rememberLazyListState()
    val coursesListState = rememberLazyListState()
    val mentorListState = rememberLazyListState()
    val galleryListState = rememberLazyListState()
    val contactListState = rememberLazyListState()

    val showLaunchError: () -> Unit = {
        scope.launch {
            snackbarHostState.showSnackbar(unableToOpenText)
        }
    }

    val callPrimary: () -> Unit = {
        if (!ExternalIntents.dialPhoneNumber(context, TuitionRepository.PHONE_PRIMARY)) {
            showLaunchError()
        }
    }

    val callSecondary: () -> Unit = {
        if (!ExternalIntents.dialPhoneNumber(context, TuitionRepository.PHONE_SECONDARY)) {
            showLaunchError()
        }
    }

    val openWhatsApp: (String) -> Unit = { message ->
        if (!ExternalIntents.openWhatsAppEnquiry(context, message)) {
            showLaunchError()
        }
    }

    val openMaps: () -> Unit = {
        if (!ExternalIntents.openGoogleMapsLocation(context)) {
            showLaunchError()
        }
    }

    // Back button behavior for state-switched navigation & overlays
    BackHandler(enabled = uiState.selectedGalleryItem != null) {
        viewModel.closeGalleryPreview()
    }
    BackHandler(enabled = uiState.selectedGalleryItem == null && uiState.activeTab != AppTab.HOME) {
        viewModel.selectTab(AppTab.HOME)
    }

    // Smooth scroll to targeted section when jumping from menu, footer, or hero buttons
    LaunchedEffect(uiState.pendingScroll) {
        val request = uiState.pendingScroll ?: return@LaunchedEffect
        val targetState = when (request.target.tab) {
            AppTab.HOME -> homeListState
            AppTab.COURSES -> coursesListState
            AppTab.MENTOR -> mentorListState
            AppTab.GALLERY -> galleryListState
            AppTab.CONTACT -> contactListState
        }
        targetState.animateScrollToItem(request.target.itemIndex)
        viewModel.consumeScrollRequest()
    }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val useNavigationRail = maxWidth >= 600.dp
        val isWideContent = maxWidth >= 600.dp

        Scaffold(
            containerColor = MaterialTheme.colorScheme.background,
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
            topBar = {
                AlorDishariTopBar(
                    onOpenMenu = { viewModel.setNavigationMenuOpen(true) },
                    onQuickCall = callPrimary,
                    onQuickWhatsApp = { openWhatsApp(TuitionRepository.DEFAULT_WHATSAPP_MESSAGE) },
                    onBrandClick = { viewModel.jumpToSection(SectionTarget.HOME_TOP) },
                )
            },
            bottomBar = {
                if (!useNavigationRail) {
                    NavigationBar(
                        containerColor = MaterialTheme.colorScheme.surface,
                        contentColor = MaterialTheme.colorScheme.onSurface,
                        windowInsets = WindowInsets.navigationBars,
                        tonalElevation = 6.dp,
                    ) {
                        PrimaryNavItems.forEach { item ->
                            val selected = uiState.activeTab == item.tab
                            NavigationBarItem(
                                selected = selected,
                                onClick = { viewModel.selectTab(item.tab) },
                                icon = {
                                    Icon(
                                        imageVector = if (selected) item.selectedIcon else item.unselectedIcon,
                                        contentDescription = stringResource(item.labelRes),
                                    )
                                },
                                label = {
                                    Text(
                                        text = stringResource(item.labelRes),
                                        style = MaterialTheme.typography.labelSmall,
                                        maxLines = 1,
                                    )
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = DeepNavy,
                                    selectedTextColor = DeepNavy,
                                    indicatorColor = GoldLightTint,
                                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                ),
                                modifier = Modifier.testTag(item.testTag),
                            )
                        }
                    }
                }
            },
        ) { innerPadding ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            ) {
                if (useNavigationRail) {
                    NavigationRail(
                        containerColor = MaterialTheme.colorScheme.surface,
                        contentColor = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .fillMaxHeight()
                            .navigationBarsPadding(),
                    ) {
                        Spacer(modifier = Modifier.weight(0.2f))
                        PrimaryNavItems.forEach { item ->
                            val selected = uiState.activeTab == item.tab
                            NavigationRailItem(
                                selected = selected,
                                onClick = { viewModel.selectTab(item.tab) },
                                icon = {
                                    Icon(
                                        imageVector = if (selected) item.selectedIcon else item.unselectedIcon,
                                        contentDescription = stringResource(item.labelRes),
                                    )
                                },
                                label = {
                                    Text(
                                        text = stringResource(item.labelRes),
                                        style = MaterialTheme.typography.labelSmall,
                                    )
                                },
                                colors = NavigationRailItemDefaults.colors(
                                    selectedIconColor = DeepNavy,
                                    selectedTextColor = RoyalBlue,
                                    indicatorColor = GoldLightTint,
                                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                ),
                                modifier = Modifier.testTag("${item.testTag}_rail"),
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .then(if (useNavigationRail) Modifier.navigationBarsPadding() else Modifier),
                    contentAlignment = Alignment.TopCenter,
                ) {
                    Box(
                        modifier = Modifier
                            .widthIn(max = 840.dp)
                            .fillMaxSize(),
                    ) {
                        Crossfade(
                            targetState = uiState.activeTab,
                            animationSpec = tween(durationMillis = 220),
                            label = "alor_dishari_tab_transition",
                        ) { tab ->
                            when (tab) {
                                AppTab.HOME -> HomeScreen(
                                    listState = homeListState,
                                    isWideLayout = isWideContent,
                                    onJumpToSection = viewModel::jumpToSection,
                                    onOpenCourseTrack = viewModel::openCoursesForTrack,
                                    onCallPrimary = callPrimary,
                                    onCallSecondary = callSecondary,
                                    onOpenWhatsApp = openWhatsApp,
                                    onOpenMaps = openMaps,
                                )

                                AppTab.COURSES -> CoursesScreen(
                                    listState = coursesListState,
                                    selectedFilter = uiState.selectedCourseFilter,
                                    expandedIds = uiState.expandedCourseIds,
                                    onSelectFilter = viewModel::selectCourseFilter,
                                    onToggleExpanded = viewModel::toggleCourseExpanded,
                                    onEnquireTrack = openWhatsApp,
                                    onJumpToSection = viewModel::jumpToSection,
                                    onCallPrimary = callPrimary,
                                    onCallSecondary = callSecondary,
                                    onOpenMaps = openMaps,
                                )

                                AppTab.MENTOR -> MentorScreen(
                                    listState = mentorListState,
                                    onCallPrimary = callPrimary,
                                    onCallSecondary = callSecondary,
                                    onOpenWhatsApp = openWhatsApp,
                                    onJumpToSection = viewModel::jumpToSection,
                                    onOpenMaps = openMaps,
                                )

                                AppTab.GALLERY -> GalleryScreen(
                                    listState = galleryListState,
                                    isWideLayout = isWideContent,
                                    selectedItem = uiState.selectedGalleryItem,
                                    onSelectItem = viewModel::openGalleryPreview,
                                    onDismissPreview = viewModel::closeGalleryPreview,
                                    onJumpToSection = viewModel::jumpToSection,
                                    onCallPrimary = callPrimary,
                                    onCallSecondary = callSecondary,
                                    onOpenWhatsApp = openWhatsApp,
                                    onOpenMaps = openMaps,
                                )

                                AppTab.CONTACT -> ContactScreen(
                                    listState = contactListState,
                                    formState = uiState.enquiryForm,
                                    onStudentNameChange = viewModel::updateStudentName,
                                    onSelectClass = viewModel::updateSelectedClass,
                                    onPhoneChange = viewModel::updatePhoneNumber,
                                    onNotesChange = viewModel::updateNotes,
                                    onSubmitEnquiry = {
                                        val msg = viewModel.validateAndBuildEnquiryMessage()
                                        if (msg != null) {
                                            openWhatsApp(msg)
                                        }
                                    },
                                    onCallPrimary = callPrimary,
                                    onCallSecondary = callSecondary,
                                    onQuickWhatsApp = {
                                        openWhatsApp(TuitionRepository.DEFAULT_WHATSAPP_MESSAGE)
                                    },
                                    onOpenMaps = openMaps,
                                    onJumpToSection = viewModel::jumpToSection,
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    if (uiState.isNavigationMenuOpen) {
        QuickNavigationSheet(
            onDismiss = { viewModel.setNavigationMenuOpen(false) },
            onJumpToSection = viewModel::jumpToSection,
            onCallPrimary = callPrimary,
            onOpenWhatsApp = { openWhatsApp(TuitionRepository.DEFAULT_WHATSAPP_MESSAGE) },
            onOpenMaps = openMaps,
        )
    }
}
