package com.example.ui.components

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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Collections
import androidx.compose.material.icons.filled.ContactPhone
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.data.TuitionRepository
import com.example.ui.SectionTarget
import com.example.ui.theme.AntiqueGold
import com.example.ui.theme.CrispWhite
import com.example.ui.theme.DeepNavy
import com.example.ui.theme.GoldLightTint
import com.example.ui.theme.MidnightNavy
import com.example.ui.theme.NavyContainer
import com.example.ui.theme.RoyalBlue
import com.example.ui.theme.WarmGold
import com.example.ui.theme.WhatsAppGreen

@Composable
fun AlorDishariTopBar(
    onOpenMenu: () -> Unit,
    onQuickCall: () -> Unit,
    onQuickWhatsApp: () -> Unit,
    onBrandClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        color = DeepNavy,
        contentColor = CrispWhite,
        shadowElevation = 4.dp,
        modifier = modifier.fillMaxWidth(),
    ) {
        Column(modifier = Modifier.statusBarsPadding()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .clickable(onClick = onBrandClick)
                        .padding(horizontal = 4.dp, vertical = 4.dp)
                        .testTag("top_bar_brand"),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(1.dp, WarmGold.copy(alpha = 0.65f)),
                        color = MidnightNavy,
                        modifier = Modifier.size(38.dp),
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_app_icon),
                            contentDescription = stringResource(R.string.cd_brand_emblem),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(38.dp),
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "ALOR DISHARI",
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = CrispWhite,
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                        Text(
                            text = "English Tuition • Classes V–XII",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = WarmGold,
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }

                IconButton(
                    onClick = onQuickCall,
                    modifier = Modifier
                        .minimumInteractiveComponentSize()
                        .testTag("top_bar_call_button"),
                ) {
                    Icon(
                        imageVector = Icons.Filled.Call,
                        contentDescription = stringResource(R.string.cd_call_primary),
                        tint = CrispWhite,
                    )
                }

                IconButton(
                    onClick = onQuickWhatsApp,
                    modifier = Modifier
                        .minimumInteractiveComponentSize()
                        .testTag("top_bar_whatsapp_button"),
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Chat,
                        contentDescription = stringResource(R.string.cd_whatsapp),
                        tint = WarmGold,
                    )
                }

                IconButton(
                    onClick = onOpenMenu,
                    modifier = Modifier
                        .minimumInteractiveComponentSize()
                        .testTag("top_bar_menu_button"),
                ) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = stringResource(R.string.action_open_menu),
                        tint = CrispWhite,
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(
                        Brush.horizontalGradient(
                            listOf(
                                AntiqueGold.copy(alpha = 0.2f),
                                WarmGold,
                                AntiqueGold.copy(alpha = 0.2f),
                            ),
                        ),
                    ),
            )
        }
    }
}

@Composable
fun SectionHeader(
    kicker: String,
    title: String,
    subtitle: String? = null,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Box(
                modifier = Modifier
                    .width(24.dp)
                    .height(2.5.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(AntiqueGold),
            )
            Text(
                text = kicker,
                style = MaterialTheme.typography.labelSmall,
                color = AntiqueGold,
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )
        if (!subtitle.isNullOrBlank()) {
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

@Composable
fun BrandPillBadge(
    text: String,
    icon: ImageVector? = null,
    containerColor: Color = GoldLightTint,
    contentColor: Color = DeepNavy,
    borderColor: Color = AntiqueGold.copy(alpha = 0.45f),
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = RoundedCornerShape(50),
        color = containerColor,
        contentColor = contentColor,
        border = BorderStroke(1.dp, borderColor),
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(15.dp),
                    tint = contentColor,
                )
            }
            Text(
                text = text,
                style = MaterialTheme.typography.labelMedium,
                color = contentColor,
            )
        }
    }
}

fun featureIconForKey(key: String): ImageVector = when (key) {
    "grammar" -> Icons.AutoMirrored.Filled.MenuBook
    "writing" -> Icons.Filled.EditNote
    "literature" -> Icons.Filled.School
    "assessment" -> Icons.Filled.Quiz
    "mentor" -> Icons.Filled.Person
    else -> Icons.Filled.Verified
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun InstitutionalFooter(
    onJumpToSection: (SectionTarget) -> Unit,
    onCallPrimary: () -> Unit,
    onCallSecondary: () -> Unit,
    onOpenWhatsApp: () -> Unit,
    onOpenMaps: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = RoundedCornerShape(24.dp),
        color = DeepNavy,
        contentColor = CrispWhite,
        border = BorderStroke(1.dp, WarmGold.copy(alpha = 0.35f)),
        modifier = modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        listOf(DeepNavy, MidnightNavy),
                    ),
                )
                .padding(20.dp),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, WarmGold.copy(alpha = 0.7f)),
                    color = MidnightNavy,
                    modifier = Modifier.size(44.dp),
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_app_icon),
                        contentDescription = stringResource(R.string.cd_brand_emblem),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(44.dp),
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = TuitionRepository.INSTITUTION_NAME,
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = CrispWhite,
                            fontWeight = FontWeight.Bold,
                        ),
                    )
                    Text(
                        text = TuitionRepository.TAGLINE,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = WarmGold,
                        ),
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(color = CrispWhite.copy(alpha = 0.14f))
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "QUICK SECTIONS",
                style = MaterialTheme.typography.labelSmall,
                color = WarmGold,
            )
            Spacer(modifier = Modifier.height(10.dp))

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                FooterLinkChip("Home", "footer_link_home") { onJumpToSection(SectionTarget.HOME_TOP) }
                FooterLinkChip("About", "footer_link_about") { onJumpToSection(SectionTarget.ABOUT) }
                FooterLinkChip("Teacher", "footer_link_mentor") { onJumpToSection(SectionTarget.MENTOR_TOP) }
                FooterLinkChip("Courses", "footer_link_courses") { onJumpToSection(SectionTarget.COURSES_TOP) }
                FooterLinkChip("Features", "footer_link_features") { onJumpToSection(SectionTarget.FEATURES) }
                FooterLinkChip("Why Us", "footer_link_why_us") { onJumpToSection(SectionTarget.WHY_CHOOSE_US) }
                FooterLinkChip("Gallery", "footer_link_gallery") { onJumpToSection(SectionTarget.GALLERY_TOP) }
                FooterLinkChip("Admissions", "footer_link_contact") { onJumpToSection(SectionTarget.CONTACT_FORM) }
                FooterLinkChip("Google Maps", "footer_link_maps") { onOpenMaps() }
            }

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "DIRECT CONTACT",
                style = MaterialTheme.typography.labelSmall,
                color = WarmGold,
            )
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                OutlinedButton(
                    onClick = onCallPrimary,
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, CrispWhite.copy(alpha = 0.35f)),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = CrispWhite),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 10.dp),
                    modifier = Modifier
                        .weight(1f)
                        .minimumInteractiveComponentSize()
                        .testTag("footer_call_primary_button"),
                ) {
                    Icon(
                        imageVector = Icons.Filled.Call,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = WarmGold,
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = TuitionRepository.PHONE_PRIMARY,
                        style = MaterialTheme.typography.labelMedium,
                        maxLines = 1,
                    )
                }

                OutlinedButton(
                    onClick = onCallSecondary,
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, CrispWhite.copy(alpha = 0.35f)),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = CrispWhite),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 10.dp),
                    modifier = Modifier
                        .weight(1f)
                        .minimumInteractiveComponentSize()
                        .testTag("footer_call_secondary_button"),
                ) {
                    Icon(
                        imageVector = Icons.Filled.Call,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = WarmGold,
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = TuitionRepository.PHONE_SECONDARY,
                        style = MaterialTheme.typography.labelMedium,
                        maxLines = 1,
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                Button(
                    onClick = onOpenWhatsApp,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = WhatsAppGreen,
                        contentColor = CrispWhite,
                    ),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 12.dp),
                    modifier = Modifier
                        .weight(1f)
                        .minimumInteractiveComponentSize()
                        .testTag("footer_whatsapp_button"),
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Chat,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "WhatsApp",
                        style = MaterialTheme.typography.labelLarge,
                    )
                }

                Button(
                    onClick = onOpenMaps,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = RoyalBlue,
                        contentColor = CrispWhite,
                    ),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 12.dp),
                    modifier = Modifier
                        .weight(1f)
                        .minimumInteractiveComponentSize()
                        .testTag("footer_maps_button"),
                ) {
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Directions",
                        style = MaterialTheme.typography.labelLarge,
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))
            HorizontalDivider(color = CrispWhite.copy(alpha = 0.14f))
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = stringResource(R.string.footer_rights),
                style = MaterialTheme.typography.bodySmall,
                color = CrispWhite.copy(alpha = 0.85f),
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = stringResource(R.string.footer_mentor_note),
                style = MaterialTheme.typography.bodySmall,
                color = WarmGold.copy(alpha = 0.9f),
            )
        }
    }
}

@Composable
private fun FooterLinkChip(
    label: String,
    tag: String,
    onClick: () -> Unit,
) {
    Surface(
        shape = RoundedCornerShape(50),
        color = NavyContainer,
        contentColor = CrispWhite,
        border = BorderStroke(1.dp, CrispWhite.copy(alpha = 0.16f)),
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .clickable(onClick = onClick)
            .testTag(tag),
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = CrispWhite.copy(alpha = 0.92f),
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp),
        )
    }
}

private data class MenuDestination(
    val title: String,
    val subtitle: String,
    val icon: ImageVector,
    val target: SectionTarget,
    val tag: String,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuickNavigationSheet(
    onDismiss: () -> Unit,
    onJumpToSection: (SectionTarget) -> Unit,
    onCallPrimary: () -> Unit,
    onOpenWhatsApp: () -> Unit,
    onOpenMaps: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val destinations = listOf(
        MenuDestination("Home", "Overview & tagline", Icons.Filled.Home, SectionTarget.HOME_TOP, "menu_home"),
        MenuDestination("About the Institution", "Mission & learning philosophy", Icons.Filled.Info, SectionTarget.ABOUT, "menu_about"),
        MenuDestination("Teacher Profile", "Bhajahari Gorai — Lead Educator", Icons.Filled.Person, SectionTarget.MENTOR_TOP, "menu_mentor"),
        MenuDestination("Courses (Class V–XII)", "V–VIII, IX–X, and XI–XII tracks", Icons.Filled.School, SectionTarget.COURSES_TOP, "menu_courses"),
        MenuDestination("Special Features", "Grammar, writing & test focus", Icons.Filled.Star, SectionTarget.FEATURES, "menu_features"),
        MenuDestination("Why Choose Us", "Our 4 academic commitments", Icons.Filled.CheckCircle, SectionTarget.WHY_CHOOSE_US, "menu_why_us"),
        MenuDestination("Gallery", "Study atmosphere & mentorship", Icons.Filled.Collections, SectionTarget.GALLERY_TOP, "menu_gallery"),
        MenuDestination("Contact & Admission Enquiry", "Phone numbers & WhatsApp form", Icons.Filled.ContactPhone, SectionTarget.CONTACT_FORM, "menu_contact"),
        MenuDestination("Google Maps Location", "Directions to the tuition center", Icons.Filled.LocationOn, SectionTarget.GOOGLE_MAPS, "menu_maps"),
    )

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
        shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
                .padding(bottom = 28.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column {
                    Text(
                        text = stringResource(R.string.menu_title),
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    Text(
                        text = stringResource(R.string.menu_subtitle),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
                IconButton(
                    onClick = onDismiss,
                    modifier = Modifier
                        .minimumInteractiveComponentSize()
                        .testTag("menu_close_button"),
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = stringResource(R.string.action_close),
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            destinations.forEach { item ->
                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.55f),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .clickable {
                            onDismiss()
                            onJumpToSection(item.target)
                        }
                        .testTag(item.tag),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 14.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Box(
                            modifier = Modifier
                                .size(38.dp)
                                .clip(CircleShape)
                                .background(DeepNavy),
                            contentAlignment = Alignment.Center,
                        ) {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = null,
                                tint = WarmGold,
                                modifier = Modifier.size(20.dp),
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = item.title,
                                style = MaterialTheme.typography.titleSmall,
                                color = MaterialTheme.colorScheme.onSurface,
                            )
                            Text(
                                text = item.subtitle,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            tint = RoyalBlue,
                            modifier = Modifier.size(18.dp),
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                Button(
                    onClick = {
                        onDismiss()
                        onOpenWhatsApp()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = WhatsAppGreen,
                        contentColor = CrispWhite,
                    ),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .weight(1f)
                        .minimumInteractiveComponentSize()
                        .testTag("menu_whatsapp_button"),
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Chat,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("WhatsApp", style = MaterialTheme.typography.labelLarge)
                }

                OutlinedButton(
                    onClick = {
                        onDismiss()
                        onCallPrimary()
                    },
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .weight(1f)
                        .minimumInteractiveComponentSize()
                        .testTag("menu_call_button"),
                ) {
                    Icon(
                        imageVector = Icons.Filled.Call,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Call Now", style = MaterialTheme.typography.labelLarge)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedButton(
                onClick = {
                    onDismiss()
                    onOpenMaps()
                },
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .minimumInteractiveComponentSize()
                    .testTag("menu_open_maps_direct_button"),
            ) {
                Icon(
                    imageVector = Icons.Filled.LocationOn,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                    tint = RoyalBlue,
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = stringResource(R.string.action_open_google_maps),
                    style = MaterialTheme.typography.labelLarge,
                )
            }
        }
    }
}
