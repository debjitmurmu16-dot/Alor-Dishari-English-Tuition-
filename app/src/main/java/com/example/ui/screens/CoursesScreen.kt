package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.data.CourseTrack
import com.example.data.CourseTrackId
import com.example.data.TuitionRepository
import com.example.ui.SectionTarget
import com.example.ui.components.InstitutionalFooter
import com.example.ui.components.SectionHeader
import com.example.ui.theme.AntiqueGold
import com.example.ui.theme.CrispWhite
import com.example.ui.theme.DeepNavy
import com.example.ui.theme.GoldLightTint
import com.example.ui.theme.MidnightNavy
import com.example.ui.theme.RoyalBlue
import com.example.ui.theme.RoyalBlueSoft
import com.example.ui.theme.WarmGold
import com.example.ui.theme.WhatsAppGreen

@Composable
fun CoursesScreen(
    listState: LazyListState,
    selectedFilter: CourseTrackId,
    expandedIds: Set<CourseTrackId>,
    onSelectFilter: (CourseTrackId) -> Unit,
    onToggleExpanded: (CourseTrackId) -> Unit,
    onEnquireTrack: (String) -> Unit,
    onJumpToSection: (SectionTarget) -> Unit,
    onCallPrimary: () -> Unit,
    onCallSecondary: () -> Unit,
    onOpenMaps: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val visibleTracks = if (selectedFilter == CourseTrackId.ALL) {
        TuitionRepository.courseTracks
    } else {
        TuitionRepository.courseTracks.filter { it.id == selectedFilter }
    }

    LazyColumn(
        state = listState,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = modifier
            .fillMaxSize()
            .testTag("courses_screen_list"),
    ) {
        item(key = "courses_header") {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                SectionHeader(
                    kicker = stringResource(R.string.section_courses_kicker),
                    title = stringResource(R.string.section_courses_title),
                    subtitle = "Carefully structured batches from Class V through Class XII designed to develop lasting grammar accuracy, literary depth, and exam confidence.",
                )

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    items(CourseTrackId.entries) { filter ->
                        val selected = filter == selectedFilter
                        FilterChip(
                            selected = selected,
                            onClick = { onSelectFilter(filter) },
                            label = {
                                Text(
                                    text = filter.label,
                                    style = MaterialTheme.typography.labelMedium,
                                )
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = DeepNavy,
                                selectedLabelColor = WarmGold,
                            ),
                            border = FilterChipDefaults.filterChipBorder(
                                enabled = true,
                                selected = selected,
                                borderColor = MaterialTheme.colorScheme.outline,
                                selectedBorderColor = WarmGold,
                            ),
                            modifier = Modifier
                                .minimumInteractiveComponentSize()
                                .testTag("course_filter_${filter.name.lowercase()}"),
                        )
                    }
                }
            }
        }

        items(visibleTracks, key = { it.id.name }) { track ->
            CourseTrackCard(
                track = track,
                isExpanded = track.id in expandedIds,
                onToggleExpanded = { onToggleExpanded(track.id) },
                onEnquireClick = { onEnquireTrack(track.whatsappPrefill) },
            )
        }

        item(key = "courses_academic_features") {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                SectionHeader(
                    kicker = stringResource(R.string.section_features_kicker),
                    title = "Included in Every Batch",
                )
                TuitionRepository.specialFeatures.forEach { feature ->
                    FeatureCard(item = feature, modifier = Modifier.fillMaxWidth())
                }
            }
        }

        item(key = "courses_footer") {
            InstitutionalFooter(
                onJumpToSection = onJumpToSection,
                onCallPrimary = onCallPrimary,
                onCallSecondary = onCallSecondary,
                onOpenWhatsApp = { onEnquireTrack(TuitionRepository.DEFAULT_WHATSAPP_MESSAGE) },
                onOpenMaps = onOpenMaps,
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun CourseTrackCard(
    track: CourseTrack,
    isExpanded: Boolean,
    onToggleExpanded: () -> Unit,
    onEnquireClick: () -> Unit,
) {
    Card(
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        modifier = Modifier
            .fillMaxWidth()
            .testTag("course_track_card_${track.id.name.lowercase()}"),
    ) {
        Column {
            // Prestigious Navy & Gold Header Band
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.horizontalGradient(
                            listOf(DeepNavy, MidnightNavy),
                        ),
                    )
                    .clickable(onClick = onToggleExpanded)
                    .padding(18.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = WarmGold,
                        modifier = Modifier.size(44.dp),
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = track.code,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    color = MidnightNavy,
                                    fontWeight = FontWeight.Bold,
                                ),
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(14.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = track.classRange.uppercase(),
                            style = MaterialTheme.typography.labelSmall,
                            color = WarmGold,
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = track.title,
                            style = MaterialTheme.typography.headlineSmall.copy(
                                color = CrispWhite,
                            ),
                        )
                        Text(
                            text = track.subtitle,
                            style = MaterialTheme.typography.bodySmall,
                            color = CrispWhite.copy(alpha = 0.8f),
                        )
                    }
                    Icon(
                        imageVector = if (isExpanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                        contentDescription = if (isExpanded) "Collapse details" else "Expand details",
                        tint = WarmGold,
                        modifier = Modifier.size(26.dp),
                    )
                }
            }

            Column(
                modifier = Modifier.padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp),
            ) {
                Text(
                    text = track.summary,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f),
                )

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    track.keyFocusAreas.forEach { tag ->
                        Surface(
                            shape = RoundedCornerShape(50),
                            color = RoyalBlueSoft,
                            border = BorderStroke(1.dp, RoyalBlue.copy(alpha = 0.25f)),
                        ) {
                            Text(
                                text = tag,
                                style = MaterialTheme.typography.labelMedium,
                                color = DeepNavy,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                            )
                        }
                    }
                }

                AnimatedVisibility(
                    visible = isExpanded,
                    enter = fadeIn() + expandVertically(),
                    exit = fadeOut() + shrinkVertically(),
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                        Text(
                            text = "KEY LEARNING MODULES",
                            style = MaterialTheme.typography.labelSmall,
                            color = AntiqueGold,
                        )
                        track.syllabusHighlights.forEach { bullet ->
                            Row(
                                verticalAlignment = Alignment.Top,
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                            ) {
                                Box(
                                    modifier = Modifier
                                        .padding(top = 3.dp)
                                        .size(18.dp)
                                        .clip(CircleShape)
                                        .background(GoldLightTint),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.CheckCircle,
                                        contentDescription = null,
                                        tint = AntiqueGold,
                                        modifier = Modifier.size(16.dp),
                                    )
                                }
                                Text(
                                    text = bullet,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.weight(1f),
                                )
                            }
                        }
                    }
                }

                Button(
                    onClick = onEnquireClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = WhatsAppGreen,
                        contentColor = CrispWhite,
                    ),
                    shape = RoundedCornerShape(14.dp),
                    contentPadding = PaddingValues(vertical = 13.dp, horizontal = 16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .minimumInteractiveComponentSize()
                        .testTag("enquire_course_${track.id.name.lowercase()}_button"),
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Chat,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${stringResource(R.string.action_enquire_track)} (${track.classRange})",
                        style = MaterialTheme.typography.labelLarge,
                    )
                }
            }
        }
    }
}
