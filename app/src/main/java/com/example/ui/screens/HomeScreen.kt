package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.data.CourseTrackId
import com.example.data.FeatureItem
import com.example.data.TuitionRepository
import com.example.data.WhyChooseItem
import com.example.ui.SectionTarget
import com.example.ui.components.BrandPillBadge
import com.example.ui.components.InstitutionalFooter
import com.example.ui.components.SectionHeader
import com.example.ui.components.featureIconForKey
import com.example.ui.theme.AntiqueGold
import com.example.ui.theme.CrispWhite
import com.example.ui.theme.DeepNavy
import com.example.ui.theme.GoldLightTint
import com.example.ui.theme.MidnightNavy
import com.example.ui.theme.NavyContainer
import com.example.ui.theme.RoyalBlue
import com.example.ui.theme.RoyalBlueSoft
import com.example.ui.theme.WarmGold
import com.example.ui.theme.WhatsAppGreen

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    listState: LazyListState,
    isWideLayout: Boolean,
    onJumpToSection: (SectionTarget) -> Unit,
    onOpenCourseTrack: (CourseTrackId) -> Unit,
    onCallPrimary: () -> Unit,
    onCallSecondary: () -> Unit,
    onOpenWhatsApp: (String) -> Unit,
    onOpenMaps: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        state = listState,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier
            .fillMaxSize()
            .testTag("home_screen_list"),
    ) {
        // Item 0: Hero Banner Section
        item(key = "home_hero") {
            HeroBannerCard(
                onWhatsAppClick = { onOpenWhatsApp(TuitionRepository.DEFAULT_WHATSAPP_MESSAGE) },
                onCallClick = onCallPrimary,
                onExploreCoursesClick = { onJumpToSection(SectionTarget.COURSES_TOP) },
            )
        }

        // Item 1: About the Institution Section
        item(key = "home_about") {
            Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                SectionHeader(
                    kicker = stringResource(R.string.section_about_kicker),
                    title = stringResource(R.string.section_about_title),
                )
                Card(
                    shape = RoundedCornerShape(22.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                    ),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            BrandPillBadge(
                                text = TuitionRepository.CLASSES_COVERED,
                                icon = Icons.Filled.School,
                            )
                            BrandPillBadge(
                                text = "Mentor: ${TuitionRepository.TEACHER_NAME}",
                                icon = Icons.Filled.Person,
                                containerColor = RoyalBlueSoft,
                                contentColor = DeepNavy,
                                borderColor = RoyalBlue.copy(alpha = 0.3f),
                            )
                        }

                        TuitionRepository.aboutParagraphs.forEach { paragraph ->
                            Text(
                                text = paragraph,
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f),
                            )
                        }

                        Surface(
                            shape = RoundedCornerShape(14.dp),
                            color = GoldLightTint,
                            border = BorderStroke(1.dp, AntiqueGold.copy(alpha = 0.4f)),
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Row(
                                modifier = Modifier.padding(14.dp),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Box(
                                    modifier = Modifier
                                        .width(3.dp)
                                        .height(32.dp)
                                        .clip(RoundedCornerShape(2.dp))
                                        .background(AntiqueGold),
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = "“${TuitionRepository.TAGLINE}”",
                                    style = MaterialTheme.typography.headlineSmall.copy(
                                        fontStyle = FontStyle.Italic,
                                        color = DeepNavy,
                                    ),
                                )
                            }
                        }
                    }
                }
            }
        }

        // Item 2: Courses Quick Overview
        item(key = "home_courses_preview") {
            Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    SectionHeader(
                        kicker = stringResource(R.string.section_courses_kicker),
                        title = stringResource(R.string.section_courses_title),
                        modifier = Modifier.weight(1f),
                    )
                    TextButton(
                        onClick = { onJumpToSection(SectionTarget.COURSES_TOP) },
                        modifier = Modifier
                            .minimumInteractiveComponentSize()
                            .testTag("home_view_all_courses_button"),
                    ) {
                        Text(
                            text = "View All",
                            style = MaterialTheme.typography.labelLarge,
                            color = RoyalBlue,
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = RoyalBlue,
                        )
                    }
                }

                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    TuitionRepository.courseTracks.forEach { track ->
                        Card(
                            shape = RoundedCornerShape(18.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surface,
                            ),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(18.dp))
                                .clickable { onOpenCourseTrack(track.id) }
                                .testTag("home_course_card_${track.id.name.lowercase()}"),
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Surface(
                                    shape = RoundedCornerShape(14.dp),
                                    color = DeepNavy,
                                    modifier = Modifier.size(54.dp),
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Text(
                                            text = track.code,
                                            style = MaterialTheme.typography.headlineSmall.copy(
                                                color = WarmGold,
                                                fontWeight = FontWeight.Bold,
                                            ),
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.width(14.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = track.classRange,
                                        style = MaterialTheme.typography.labelMedium,
                                        color = RoyalBlue,
                                    )
                                    Text(
                                        text = track.title,
                                        style = MaterialTheme.typography.titleMedium,
                                        color = MaterialTheme.colorScheme.onSurface,
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = track.subtitle,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    )
                                }
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                    contentDescription = null,
                                    tint = AntiqueGold,
                                    modifier = Modifier.size(20.dp),
                                )
                            }
                        }
                    }
                }
            }
        }

        // Item 3: Special Features Section
        item(key = "home_special_features") {
            Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                SectionHeader(
                    kicker = stringResource(R.string.section_features_kicker),
                    title = stringResource(R.string.section_features_title),
                )

                val features = TuitionRepository.specialFeatures
                if (isWideLayout) {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        features.chunked(2).forEach { pair ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(12.dp),
                            ) {
                                pair.forEach { feature ->
                                    FeatureCard(
                                        item = feature,
                                        modifier = Modifier.weight(1f),
                                    )
                                }
                                if (pair.size == 1) {
                                    Spacer(modifier = Modifier.weight(1f))
                                }
                            }
                        }
                    }
                } else {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        features.forEach { feature ->
                            FeatureCard(item = feature, modifier = Modifier.fillMaxWidth())
                        }
                    }
                }
            }
        }

        // Item 4: Why Choose Us Section
        item(key = "home_why_choose_us") {
            Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                SectionHeader(
                    kicker = stringResource(R.string.section_why_us_kicker),
                    title = stringResource(R.string.section_why_us_title),
                )
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    TuitionRepository.whyChooseUsItems.forEach { item ->
                        WhyChooseCard(item = item)
                    }
                }
            }
        }

        // Item 5: Mentor & Location Quick Access Banner
        item(key = "home_mentor_and_location") {
            Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                Card(
                    shape = RoundedCornerShape(22.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Column {
                        Image(
                            painter = painterResource(id = R.drawable.img_teacher_mentor),
                            contentDescription = stringResource(R.string.cd_teacher_illustration),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(16f / 9f),
                        )
                        Column(
                            modifier = Modifier.padding(20.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            Text(
                                text = "LEAD EDUCATOR",
                                style = MaterialTheme.typography.labelSmall,
                                color = AntiqueGold,
                            )
                            Text(
                                text = TuitionRepository.TEACHER_NAME,
                                style = MaterialTheme.typography.headlineSmall,
                                color = MaterialTheme.colorScheme.onSurface,
                            )
                            Text(
                                text = "Dedicated English mentor guiding Classes V–XII with clear grammar fundamentals, literary insight, and attentive exam preparation.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                Button(
                                    onClick = { onJumpToSection(SectionTarget.MENTOR_TOP) },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = DeepNavy,
                                        contentColor = CrispWhite,
                                    ),
                                    shape = RoundedCornerShape(12.dp),
                                    modifier = Modifier
                                        .weight(1f)
                                        .minimumInteractiveComponentSize()
                                        .testTag("home_teacher_profile_button"),
                                ) {
                                    Text("Teacher Profile", style = MaterialTheme.typography.labelLarge)
                                }
                                OutlinedButton(
                                    onClick = onOpenMaps,
                                    shape = RoundedCornerShape(12.dp),
                                    modifier = Modifier
                                        .weight(1f)
                                        .minimumInteractiveComponentSize()
                                        .testTag("home_open_maps_button"),
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.LocationOn,
                                        contentDescription = null,
                                        modifier = Modifier.size(16.dp),
                                        tint = RoyalBlue,
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("Location", style = MaterialTheme.typography.labelLarge)
                                }
                            }
                        }
                    }
                }
            }
        }

        // Item 6: Institutional Footer
        item(key = "home_footer") {
            InstitutionalFooter(
                onJumpToSection = onJumpToSection,
                onCallPrimary = onCallPrimary,
                onCallSecondary = onCallSecondary,
                onOpenWhatsApp = { onOpenWhatsApp(TuitionRepository.DEFAULT_WHATSAPP_MESSAGE) },
                onOpenMaps = onOpenMaps,
            )
        }
    }
}

@Composable
private fun HeroBannerCard(
    onWhatsAppClick: () -> Unit,
    onCallClick: () -> Unit,
    onExploreCoursesClick: () -> Unit,
) {
    Surface(
        shape = RoundedCornerShape(26.dp),
        color = DeepNavy,
        border = BorderStroke(1.dp, WarmGold.copy(alpha = 0.45f)),
        shadowElevation = 6.dp,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_hero_banner),
                    contentDescription = stringResource(R.string.cd_hero_banner),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    MidnightNavy.copy(alpha = 0.15f),
                                    DeepNavy.copy(alpha = 0.55f),
                                    DeepNavy,
                                ),
                            ),
                        ),
                )
                Surface(
                    shape = RoundedCornerShape(50),
                    color = MidnightNavy.copy(alpha = 0.86f),
                    border = BorderStroke(1.dp, WarmGold.copy(alpha = 0.7f)),
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(14.dp),
                ) {
                    Text(
                        text = "CLASSES V – XII • ENGLISH TUITION",
                        style = MaterialTheme.typography.labelSmall,
                        color = WarmGold,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            listOf(DeepNavy, MidnightNavy),
                        ),
                    )
                    .padding(horizontal = 20.dp, vertical = 20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Text(
                    text = TuitionRepository.INSTITUTION_NAME,
                    style = MaterialTheme.typography.displaySmall.copy(
                        color = CrispWhite,
                        fontWeight = FontWeight.Bold,
                    ),
                )

                Text(
                    text = TuitionRepository.TAGLINE,
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = WarmGold,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Medium,
                    ),
                )

                Text(
                    text = "Structured English coaching for Classes V–XII by ${TuitionRepository.TEACHER_NAME}, focusing on crystal-clear grammar, literature depth, and confident exam writing.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = CrispWhite.copy(alpha = 0.86f),
                )

                Spacer(modifier = Modifier.height(4.dp))

                Button(
                    onClick = onWhatsAppClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = WhatsAppGreen,
                        contentColor = CrispWhite,
                    ),
                    shape = RoundedCornerShape(14.dp),
                    contentPadding = PaddingValues(vertical = 14.dp, horizontal = 18.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .minimumInteractiveComponentSize()
                        .testTag("hero_whatsapp_enquiry_button"),
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Chat,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(R.string.action_whatsapp_enquiry),
                        style = MaterialTheme.typography.labelLarge,
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    Button(
                        onClick = onCallClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = WarmGold,
                            contentColor = MidnightNavy,
                        ),
                        shape = RoundedCornerShape(14.dp),
                        contentPadding = PaddingValues(vertical = 13.dp, horizontal = 12.dp),
                        modifier = Modifier
                            .weight(1f)
                            .minimumInteractiveComponentSize()
                            .testTag("hero_call_now_button"),
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Call,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp),
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = stringResource(R.string.action_call_now),
                            style = MaterialTheme.typography.labelLarge,
                        )
                    }

                    OutlinedButton(
                        onClick = onExploreCoursesClick,
                        shape = RoundedCornerShape(14.dp),
                        border = BorderStroke(1.dp, CrispWhite.copy(alpha = 0.4f)),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = CrispWhite),
                        contentPadding = PaddingValues(vertical = 13.dp, horizontal = 12.dp),
                        modifier = Modifier
                            .weight(1f)
                            .minimumInteractiveComponentSize()
                            .testTag("hero_explore_courses_button"),
                    ) {
                        Text(
                            text = stringResource(R.string.action_explore_courses),
                            style = MaterialTheme.typography.labelLarge,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FeatureCard(
    item: FeatureItem,
    modifier: Modifier = Modifier,
) {
    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top,
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(RoyalBlueSoft),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = featureIconForKey(item.iconKey),
                    contentDescription = null,
                    tint = RoyalBlue,
                    modifier = Modifier.size(22.dp),
                )
            }
            Spacer(modifier = Modifier.width(14.dp))
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

@Composable
fun WhyChooseCard(
    item: WhyChooseItem,
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = RoundedCornerShape(18.dp),
        color = DeepNavy,
        contentColor = CrispWhite,
        border = BorderStroke(1.dp, WarmGold.copy(alpha = 0.3f)),
        modifier = modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalAlignment = Alignment.Top,
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(NavyContainer),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = item.number,
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = WarmGold,
                        fontWeight = FontWeight.Bold,
                    ),
                )
            }
            Spacer(modifier = Modifier.width(14.dp))
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = CrispWhite,
                )
                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFFD6E0F0),
                )
            }
        }
    }
}
