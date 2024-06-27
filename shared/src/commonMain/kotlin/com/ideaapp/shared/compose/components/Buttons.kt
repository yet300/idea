package com.ideaapp.shared.compose.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ideasapp.shared.generated.resources.Res
import ideasapp.shared.generated.resources.cancel
import org.jetbrains.compose.resources.stringResource


@Composable
fun BackButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    IconButton(modifier = modifier, onClick = onClick) {
        Icon(
            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
            contentDescription = stringResource(Res.string.cancel),
        )
    }
}

@Composable
fun TextComponentButton(
    modifier: Modifier = Modifier,
    content: String,
    secondText: String,
    onClick: (() -> Unit) = {},
) {
    Surface(
        modifier = modifier.clickable {
            onClick()
        },
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = modifier
                    .weight(1f)
                    .padding(12.dp)
            ) {
                Text(
                    text = content,
                    style = MaterialTheme.typography.headlineSmall,
                )

                Text(
                    text = secondText,
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        }
    }

}

@Composable
fun TextIconComponentTextField(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    icon: Any? = null,
    onValueChange: ((String) -> Unit),
) {
    Surface {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(12.dp),

            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            when (icon) {
                is ImageVector -> {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier = modifier
                            .size(24.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                is Painter -> {
                    Icon(
                        painter = icon,
                        contentDescription = null,
                        modifier = modifier
                            .size(24.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = modifier.padding(10.dp))

            Column(
                modifier = modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                CustomTextField(
                    value = value,
                    onValueChange = onValueChange,
                    labelText = label,
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )
            }
        }
    }

}

@Composable
fun IconComponentSwitcher(
    modifier: Modifier = Modifier,
    text: String,
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit),
    icon: ImageVector,
) {
    Surface {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = text,
                modifier = modifier
                    .size(24.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = modifier.padding(10.dp))

            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f)
            )

            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange
            )
        }
    }

}

@Composable
fun IconComponentButton(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
    icon: Any? = null,
    onClick: (() -> Unit) = {},
) {
    Surface(
        modifier = modifier.clickable {
            onClick()
        }
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            when (icon) {
                is ImageVector -> {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier = modifier
                            .size(24.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                is Painter -> {
                    Icon(
                        painter = icon,
                        contentDescription = null,
                        modifier = modifier
                            .size(24.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = modifier.padding(10.dp))

            Column(
                modifier = modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                content()
            }
        }
    }

}

@Composable
fun TextIconComponent(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit) = {},
    text: String,
    icon: ImageVector,
) {
    Surface(
        modifier = modifier.clickable {
            onClick()
        }
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = icon,
                contentDescription = text
            )
        }
    }
}

