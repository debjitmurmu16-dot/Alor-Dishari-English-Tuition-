package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.data.TuitionRepository
import com.example.ui.EnquiryFormState
import com.example.ui.SectionTarget
import com.example.ui.components.InstitutionalFooter
import com.example.ui.components.SectionHeader
import com.example.ui.theme.AntiqueGold
import com.example.ui.theme.CrispWhite
import com.example.ui.theme.DeepNavy
import com.example.ui.theme.MidnightNavy
import com.example.ui.theme.RoyalBlue
import com.example.ui.theme.RoyalBlueSoft
import com.example.ui.theme.WarmGold
import com.example.ui.theme.WhatsAppGreen

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ContactScreen(
    listState: LazyListState,
    formState: EnquiryFormState,
    onStudentNameChange: (String) -> Unit,
    onSelectClass: (String) -> Unit,
    onPhoneChange: (String) -> Unit,
    onNotesChange: (String) -> Unit,
    onSubmitEnquiry: () -> Unit,
    onCallPrimary: () -> Unit,
    onCallSecondary: () -> Unit,
    onQuickWhatsApp: () -> Unit,
    onOpenMaps: () -> Unit,
    onJumpToSection: (SectionTarget) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        state = listState,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(22.dp),
        modifier = modifier
            .fillMaxSize()
            .testTag("contact_screen_list"),
    ) {
        // Item 0: Direct Phone & WhatsApp Cards
        item(key = "contact_direct_strip") {
            Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                SectionHeader(
                    kicker = stringResource(R.string.section_contact_kicker),
                    title = stringResource(R.string.section_contact_title),
                    subtitle = "Speak directly with ${TuitionRepository.TEACHER_NAME} or send an instant WhatsApp admission enquiry.",
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    PhoneContactCard(
                        label = "Primary Helpline",
                        displayNumber = stringResource(R.string.phone_primary_display),
                        tag = "contact_call_primary_card",
                        onClick = onCallPrimary,
                        modifier = Modifier.weight(1f),
                    )
                    PhoneContactCard(
                        label = "Alternate Number",
                        displayNumber = stringResource(R.string.phone_secondary_display),
                        tag = "contact_call_secondary_card",
                        onClick = onCallSecondary,
                        modifier = Modifier.weight(1f),
                    )
                }

                Surface(
                    shape = RoundedCornerShape(18.dp),
                    color = WhatsAppGreen,
                    contentColor = CrispWhite,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(18.dp))
                        .clickable(onClick = onQuickWhatsApp)
                        .testTag("contact_quick_whatsapp_card"),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 18.dp, vertical = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(CrispWhite.copy(alpha = 0.2f)),
                            contentAlignment = Alignment.Center,
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.Chat,
                                contentDescription = stringResource(R.string.cd_whatsapp),
                                tint = CrispWhite,
                                modifier = Modifier.size(22.dp),
                            )
                        }
                        Spacer(modifier = Modifier.width(14.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Instant WhatsApp Enquiry",
                                style = MaterialTheme.typography.titleMedium,
                                color = CrispWhite,
                            )
                            Text(
                                text = "Chat on +91 81456 14076 with a pre-filled message",
                                style = MaterialTheme.typography.bodySmall,
                                color = CrispWhite.copy(alpha = 0.9f),
                            )
                        }
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.OpenInNew,
                            contentDescription = null,
                            tint = CrispWhite,
                            modifier = Modifier.size(20.dp),
                        )
                    }
                }
            }
        }

        // Item 1: Structured Admission Enquiry Form
        item(key = "contact_admission_form") {
            Card(
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp),
                ) {
                    Text(
                        text = stringResource(R.string.form_title),
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    Text(
                        text = stringResource(R.string.form_subtitle),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )

                    OutlinedTextField(
                        value = formState.studentName,
                        onValueChange = onStudentNameChange,
                        label = { Text(stringResource(R.string.form_student_name_label)) },
                        placeholder = { Text(stringResource(R.string.form_student_name_placeholder)) },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Person,
                                contentDescription = null,
                                tint = RoyalBlue,
                            )
                        },
                        isError = formState.nameError,
                        supportingText = if (formState.nameError) {
                            { Text(stringResource(R.string.form_error_name)) }
                        } else {
                            null
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Words,
                        ),
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("enquiry_student_name_input"),
                    )

                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(
                            text = stringResource(R.string.form_class_label),
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp),
                        ) {
                            TuitionRepository.classOptions.forEach { className ->
                                val selected = formState.selectedClass == className
                                FilterChip(
                                    selected = selected,
                                    onClick = { onSelectClass(className) },
                                    label = {
                                        Text(
                                            text = className,
                                            style = MaterialTheme.typography.labelMedium,
                                        )
                                    },
                                    colors = FilterChipDefaults.filterChipColors(
                                        selectedContainerColor = DeepNavy,
                                        selectedLabelColor = WarmGold,
                                    ),
                                    modifier = Modifier
                                        .minimumInteractiveComponentSize()
                                        .testTag("enquiry_class_chip_${className.replace(" ", "_").lowercase()}"),
                                )
                            }
                        }
                    }

                    OutlinedTextField(
                        value = formState.phoneNumber,
                        onValueChange = onPhoneChange,
                        label = { Text(stringResource(R.string.form_phone_label)) },
                        placeholder = { Text(stringResource(R.string.form_phone_placeholder)) },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Phone,
                                contentDescription = null,
                                tint = RoyalBlue,
                            )
                        },
                        isError = formState.phoneError,
                        supportingText = if (formState.phoneError) {
                            { Text(stringResource(R.string.form_error_phone)) }
                        } else {
                            null
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("enquiry_phone_input"),
                    )

                    OutlinedTextField(
                        value = formState.notes,
                        onValueChange = onNotesChange,
                        label = { Text(stringResource(R.string.form_notes_label)) },
                        placeholder = { Text(stringResource(R.string.form_notes_placeholder)) },
                        minLines = 3,
                        maxLines = 4,
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Sentences,
                        ),
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("enquiry_notes_input"),
                    )

                    Button(
                        onClick = onSubmitEnquiry,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = DeepNavy,
                            contentColor = WarmGold,
                        ),
                        shape = RoundedCornerShape(14.dp),
                        contentPadding = PaddingValues(vertical = 15.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .minimumInteractiveComponentSize()
                            .testTag("enquiry_submit_button"),
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Chat,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp),
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = stringResource(R.string.action_submit_enquiry),
                            style = MaterialTheme.typography.labelLarge,
                        )
                    }
                }
            }
        }

        // Item 2: Google Maps Location Card
        item(key = "contact_google_maps") {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                SectionHeader(
                    kicker = stringResource(R.string.section_maps_kicker),
                    title = stringResource(R.string.section_maps_title),
                    subtitle = "Tap below to open exact turn-by-turn directions to Alor Dishari English Tuition in Google Maps.",
                )

                GoogleMapsLocationCard(onOpenMaps = onOpenMaps)
            }
        }

        // Item 3: Footer
        item(key = "contact_footer") {
            InstitutionalFooter(
                onJumpToSection = onJumpToSection,
                onCallPrimary = onCallPrimary,
                onCallSecondary = onCallSecondary,
                onOpenWhatsApp = onQuickWhatsApp,
                onOpenMaps = onOpenMaps,
            )
        }
    }
}

@Composable
private fun PhoneContactCard(
    label: String,
    displayNumber: String,
    tag: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        modifier = modifier
            .clip(RoundedCornerShape(18.dp))
            .clickable(onClick = onClick)
            .testTag(tag),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(RoyalBlueSoft),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = Icons.Filled.Call,
                    contentDescription = null,
                    tint = RoyalBlue,
                    modifier = Modifier.size(20.dp),
                )
            }
            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelSmall,
                    color = AntiqueGold,
                )
                Text(
                    text = displayNumber,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold,
                    ),
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
    }
}

@Composable
private fun GoogleMapsLocationCard(
    onOpenMaps: () -> Unit,
) {
    Surface(
        shape = RoundedCornerShape(22.dp),
        color = DeepNavy,
        border = BorderStroke(1.dp, WarmGold.copy(alpha = 0.45f)),
        shadowElevation = 4.dp,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column {
            // Stylized Vector Map Graphic using Canvas
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(172.dp)
                    .clickable(onClick = onOpenMaps)
                    .testTag("google_maps_preview_card"),
                contentAlignment = Alignment.Center,
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawRect(
                        brush = Brush.linearGradient(
                            colors = listOf(DeepNavy, Color(0xFF142D5C), MidnightNavy),
                        ),
                    )
                    val gridColor = Color.White.copy(alpha = 0.08f)
                    val stepX = size.width / 8f
                    val stepY = size.height / 5f
                    for (i in 1..7) {
                        drawLine(
                            color = gridColor,
                            start = Offset(stepX * i, 0f),
                            end = Offset(stepX * i, size.height),
                            strokeWidth = 1.5f,
                        )
                    }
                    for (j in 1..4) {
                        drawLine(
                            color = gridColor,
                            start = Offset(0f, stepY * j),
                            end = Offset(size.width, stepY * j),
                            strokeWidth = 1.5f,
                        )
                    }
                    drawLine(
                        color = WarmGold.copy(alpha = 0.45f),
                        start = Offset(size.width * 0.1f, size.height * 0.8f),
                        end = Offset(size.width * 0.5f, size.height * 0.48f),
                        strokeWidth = 5f,
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(14f, 10f), 0f),
                    )
                    drawLine(
                        color = RoyalBlue.copy(alpha = 0.7f),
                        start = Offset(size.width * 0.5f, size.height * 0.48f),
                        end = Offset(size.width * 0.9f, size.height * 0.22f),
                        strokeWidth = 5f,
                    )
                    drawCircle(
                        color = WarmGold.copy(alpha = 0.16f),
                        radius = size.minDimension * 0.28f,
                        center = Offset(size.width * 0.5f, size.height * 0.48f),
                    )
                    drawCircle(
                        color = WarmGold.copy(alpha = 0.30f),
                        radius = size.minDimension * 0.17f,
                        center = Offset(size.width * 0.5f, size.height * 0.48f),
                    )
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Surface(
                        shape = CircleShape,
                        color = WarmGold,
                        shadowElevation = 6.dp,
                        modifier = Modifier.size(54.dp),
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Filled.LocationOn,
                                contentDescription = stringResource(R.string.cd_google_maps),
                                tint = MidnightNavy,
                                modifier = Modifier.size(30.dp),
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Surface(
                        shape = RoundedCornerShape(50),
                        color = MidnightNavy.copy(alpha = 0.9f),
                        border = BorderStroke(1.dp, WarmGold.copy(alpha = 0.6f)),
                    ) {
                        Text(
                            text = "Alor Dishari English Tuition",
                            style = MaterialTheme.typography.labelMedium,
                            color = CrispWhite,
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                        )
                    }
                }
            }

            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Text(
                    text = TuitionRepository.INSTITUTION_NAME,
                    style = MaterialTheme.typography.titleMedium,
                    color = CrispWhite,
                )
                Text(
                    text = "Teacher: ${TuitionRepository.TEACHER_NAME} • ${TuitionRepository.CLASSES_COVERED}",
                    style = MaterialTheme.typography.bodySmall,
                    color = WarmGold,
                )
                Button(
                    onClick = onOpenMaps,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = WarmGold,
                        contentColor = MidnightNavy,
                    ),
                    shape = RoundedCornerShape(14.dp),
                    contentPadding = PaddingValues(vertical = 14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .minimumInteractiveComponentSize()
                        .testTag("contact_open_google_maps_button"),
                ) {
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(R.string.action_open_google_maps),
                        style = MaterialTheme.typography.labelLarge,
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.OpenInNew,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                    )
                }
            }
        }
    }
}
