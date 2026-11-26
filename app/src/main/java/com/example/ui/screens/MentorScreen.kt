package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Call
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
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.data.TuitionRepository
import com.example.ui.SectionTarget
import com.example.ui.components.BrandPillBadge
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MentorScreen(
    listState: LazyListState,
    onCallPrimary: () -> Unit,
    onCallSecondary: () -> Unit,
    onOpenWhatsApp: (String) -> Unit,
    onJumpToSection: (SectionTarget) -> Unit,
    onOpenMaps: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        state = listState,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = modifier
            .fillMaxSize()
            .testTag("mentor_screen_list"),
    ) {
        item(key = "mentor_header") {
            SectionHeader(
                kicker = stringResource(R.string.section_mentor_kicker),
                title = stringResource(R.string.section_mentor_title),
            )
        }

        item(key = "mentor_hero_card") {
            Surface(
                shape = RoundedCornerShape(24.dp),
                color = DeepNavy,
                border = BorderStroke(1.dp, WarmGold.copy(alpha = 0.4f)),
                shadowElevation = 5.dp,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column {
                    Image(
                        painter = painterResource(id = R.drawable.img_teacher_mentor),
                        contentDescription = stringResource(R.string.cd_teacher_illustration),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(4f / 3f),
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Brush.verticalGradient(listOf(DeepNavy, MidnightNavy)))
                            .padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        Text(
                            text = stringResource(R.string.teacher_role).uppercase(),
                            style = MaterialTheme.typography.labelSmall,
                            color = WarmGold,
                        )
                        Text(
                            text = TuitionRepository.TEACHER_NAME,
                            style = MaterialTheme.typography.displaySmall.copy(
                                color = CrispWhite,
                                fontWeight = FontWeight.Bold,
                            ),
                        )
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            BrandPillBadge(
                                text = TuitionRepository.CLASSES_COVERED,
                                icon = Icons.Filled.School,
                                containerColor = GoldLightTint,
                                contentColor = DeepNavy,
                            )
                            BrandPillBadge(
                                text = "English Language & Literature",
                                icon = Icons.AutoMirrored.Filled.MenuBook,
                                containerColor = RoyalBlueSoft,
                                contentColor = DeepNavy,
                                borderColor = RoyalBlue.copy(alpha = 0.35f),
                            )
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Dedicated to helping students from Class V to Class XII master English with clarity, confidence, and disciplined study habits at Alor Dishari English Tuition.",
                            style = MaterialTheme.typography.bodyLarge,
                            color = CrispWhite.copy(alpha = 0.9f),
                        )
                    }
                }
            }
        }

        item(key = "mentor_philosophy") {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = GoldLightTint),
                border = BorderStroke(1.dp, AntiqueGold.copy(alpha = 0.45f)),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    Text(
                        text = "TEACHING PHILOSOPHY",
                        style = MaterialTheme.typography.labelSmall,
                        color = AntiqueGold,
                    )
                    Text(
                        text = "“${TuitionRepository.TAGLINE}”",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            color = DeepNavy,
                            fontStyle = FontStyle.Italic,
                        ),
                    )
                    Text(
                        text = "True command of English grows when students understand why a sentence works, connect thoughtfully with literature, and practice expressing their own ideas clearly on paper.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = DeepNavy.copy(alpha = 0.85f),
                    )
                }
            }
        }

        item(key = "mentor_focus_areas") {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                SectionHeader(
                    kicker = "INSTRUCTIONAL APPROACH",
                    title = "How Every Student Is Guided",
                )
                TuitionRepository.mentorFocusItems.forEachIndexed { index, focus ->
                    Card(
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                        ),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.Top,
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(DeepNavy),
                                contentAlignment = Alignment.Center,
                            ) {
                                Text(
                                    text = "0${index + 1}",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = WarmGold,
                                )
                            }
                            Spacer(modifier = Modifier.width(14.dp))
                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Text(
                                    text = focus.title,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.onSurface,
                                )
                                Text(
                                    text = focus.detail,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                )
                            }
                        }
                    }
                }
            }
        }

        item(key = "mentor_direct_contact") {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = null,
                            tint = RoyalBlue,
                            modifier = Modifier.size(22.dp),
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Connect with ${TuitionRepository.TEACHER_NAME}",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                    }
                    Text(
                        text = "Reach out directly by phone or WhatsApp for batch schedules, syllabus guidance, and admission enquiries.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )

                    Button(
                        onClick = {
                            onOpenWhatsApp(
                                "Hello Bhajahari Sir, I would like to connect regarding English tuition classes at Alor Dishari English Tuition.",
                            )
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = WhatsAppGreen,
                            contentColor = CrispWhite,
                        ),
                        shape = RoundedCornerShape(14.dp),
                        contentPadding = PaddingValues(vertical = 13.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .minimumInteractiveComponentSize()
                            .testTag("mentor_whatsapp_button"),
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Chat,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp),
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Message on WhatsApp",
                            style = MaterialTheme.typography.labelLarge,
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        OutlinedButton(
                            onClick = onCallPrimary,
                            shape = RoundedCornerShape(14.dp),
                            modifier = Modifier
                                .weight(1f)
                                .minimumInteractiveComponentSize()
                                .testTag("mentor_call_primary_button"),
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Call,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp),
                                tint = RoyalBlue,
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = TuitionRepository.PHONE_PRIMARY,
                                style = MaterialTheme.typography.labelMedium,
                            )
                        }

                        OutlinedButton(
                            onClick = onCallSecondary,
                            shape = RoundedCornerShape(14.dp),
                            modifier = Modifier
                                .weight(1f)
                                .minimumInteractiveComponentSize()
                                .testTag("mentor_call_secondary_button"),
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Call,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp),
                                tint = RoyalBlue,
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = TuitionRepository.PHONE_SECONDARY,
                                style = MaterialTheme.typography.labelMedium,
                            )
                        }
                    }
                }
            }
        }

        item(key = "mentor_footer") {
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
