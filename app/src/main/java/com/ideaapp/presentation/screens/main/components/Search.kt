package com.ideaapp.presentation.screens.main.components



import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.ideaapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(
    modifier: Modifier = Modifier,
    query: MutableState<String>,
) {
    var expended by remember {
        mutableStateOf(false)
    }

    SearchBar(
        query = query.value,
        onQueryChange = {
            query.value = it
        },
        onSearch = {
            query.value = it
        },
        active = false,
        onActiveChange = {},

        leadingIcon = {
            Icon(imageVector = Icons.Filled.Search, contentDescription = null)
        },
        placeholder = {
            Text(text = stringResource(id = R.string.app_name))
        },
        trailingIcon = {
            IconButton(onClick = {
                expended = true
            }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert, contentDescription = null
                )
            }
            DropdownMenu(
                expanded = expended,
                onDismissRequest = { expended = false },
                offset = DpOffset(x = 10.dp, y = 8.dp),
                modifier = modifier
                    .padding(6.dp)
                    .clip(MaterialTheme.shapes.small)

            ) {

                DropdownMenuItem(text = {
                    Text(
                        text = stringResource(id = R.string.secure_notes),
                        style = MaterialTheme.typography.titleMedium
                    )
                }, onClick = { }, trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_shield),
                        contentDescription = stringResource(id = R.string.secure_notes)
                    )
                })

                Divider(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )


                DropdownMenuItem(text = {
                    Text(
                        text = stringResource(id = R.string.trash),
                        style = MaterialTheme.typography.titleMedium
                    )
                }, onClick = { }, trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.delete),
                        contentDescription = stringResource(id = R.string.trash)
                    )
                })

                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )


                DropdownMenuItem(text = {
                    Text(
                        text = stringResource(id = R.string.settings),
                        style = MaterialTheme.typography.titleMedium
                    )
                }, onClick = { }, trailingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Settings,
                        contentDescription = stringResource(id = R.string.settings)
                    )
                })
            }

        },

        modifier = modifier
            .fillMaxWidth()
            .padding(6.dp),
    ) {

    }
}
