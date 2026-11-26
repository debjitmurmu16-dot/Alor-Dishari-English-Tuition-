package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ZoomIn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.R
import com.example.data.GalleryItem
import com.example.data.TuitionRepository
import com.example.ui.SectionTarget
import com.example.ui.components.InstitutionalFooter
import com.example.ui.components.SectionHeader
import com.example.ui.theme.CrispWhite
import com.example.ui.theme.DeepNavy
import com.example.ui.theme.MidnightNavy
import com.example.ui.theme.WarmGold

@Composable
fun GalleryScreen(
    listState: LazyListState,
    isWideLayout: Boolean,
    selectedItem: GalleryItem?,
    onSelectItem: (GalleryItem) -> Unit,
    onDismissPreview: () -> Unit,
    onJumpToSection: (SectionTarget) -> Unit,
    onCallPrimary: () -> Unit,
    onCallSecondary: () -> Unit,
    onOpenWhatsApp: (String) -> Unit,
    onOpenMaps: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val items = TuitionRepository.galleryItems

    LazyColumn(
        state = listState,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = modifier
            .fillMaxSize()
            .testTag("gallery_screen_list"),
    ) {
        item(key = "gallery_header") {
            SectionHeader(
                kicker = stringResource(R.string.section_gallery_kicker),
                title = stringResource(R.string.section_gallery_title),
                subtitle = "Tap any artwork to view full-screen details of our academic culture and mentorship approach.",
            )
        }

        if (isWideLayout) {
            item(key = "gallery_grid_wide") {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    items.chunked(2).forEach { pair ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                        ) {
                            pair.forEach { galleryItem ->
                                GalleryCard(
                                    item = galleryItem,
                                    onClick = { onSelectItem(galleryItem) },
                                    modifier = Modifier.weight(1f),
                                )
                            }
                            if (pair.size == 1) {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
            }
        } else {
            items(items.size, key = { items[it].id }) { index ->
                val galleryItem = items[index]
                GalleryCard(
                    item = galleryItem,
                    onClick = { onSelectItem(galleryItem) },
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }

        item(key = "gallery_footer") {
            InstitutionalFooter(
                onJumpToSection = onJumpToSection,
                onCallPrimary = onCallPrimary,
                onCallSecondary = onCallSecondary,
                onOpenWhatsApp = { onOpenWhatsApp(TuitionRepository.DEFAULT_WHATSAPP_MESSAGE) },
                onOpenMaps = onOpenMaps,
            )
        }
    }

    if (selectedItem != null) {
        GalleryLightboxDialog(
            item = selectedItem,
            onDismiss = onDismissPreview,
        )
    }
}

@Composable
private fun GalleryCard(
    item: GalleryItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        modifier = modifier
            .clip(RoundedCornerShape(22.dp))
            .clickable(onClick = onClick)
            .testTag("gallery_card_${item.id}"),
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(4f / 3f),
            ) {
                Image(
                    painter = painterResource(id = item.imageRes),
                    contentDescription = item.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                listOf(
                                    MidnightNavy.copy(alpha = 0.05f),
                                    MidnightNavy.copy(alpha = 0.45f),
                                ),
                            ),
                        ),
                )
                Surface(
                    shape = RoundedCornerShape(50),
                    color = DeepNavy.copy(alpha = 0.88f),
                    border = BorderStroke(1.dp, WarmGold.copy(alpha = 0.65f)),
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(12.dp),
                ) {
                    Text(
                        text = item.category.uppercase(),
                        style = MaterialTheme.typography.labelSmall,
                        color = WarmGold,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                    )
                }

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(12.dp)
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(DeepNavy.copy(alpha = 0.85f)),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = Icons.Filled.ZoomIn,
                        contentDescription = "Preview ${item.title}",
                        tint = WarmGold,
                        modifier = Modifier.size(18.dp),
                    )
                }
            }

            Column(
                modifier = Modifier.padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    text = item.caption,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

@Composable
private fun GalleryLightboxDialog(
    item: GalleryItem,
    onDismiss: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Surface(
            shape = RoundedCornerShape(24.dp),
            color = DeepNavy,
            border = BorderStroke(1.dp, WarmGold.copy(alpha = 0.55f)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        ) {
            Column {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Image(
                        painter = painterResource(id = item.imageRes),
                        contentDescription = item.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(4f / 3f),
                    )
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(10.dp)
                            .clip(CircleShape)
                            .background(MidnightNavy.copy(alpha = 0.8f))
                            .minimumInteractiveComponentSize()
                            .testTag("gallery_lightbox_close_button"),
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = stringResource(R.string.cd_close_dialog),
                            tint = CrispWhite,
                        )
                    }
                }
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Text(
                        text = item.category.uppercase(),
                        style = MaterialTheme.typography.labelSmall,
                        color = WarmGold,
                    )
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.headlineSmall,
                        color = CrispWhite,
                    )
                    Text(
                        text = item.caption,
                        style = MaterialTheme.typography.bodyMedium,
                        color = CrispWhite.copy(alpha = 0.88f),
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}
