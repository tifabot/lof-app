/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.wearable.composeadvanced.presentation.ui.dialog

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.ScalingLazyListAnchorType
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.OutlinedChip
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.dialog.Alert
import androidx.wear.compose.material.dialog.Dialog
import com.example.android.wearable.composeadvanced.R
import com.google.android.horologist.compose.material.Button
import com.google.android.horologist.compose.material.Confirmation

@Composable
fun Dialogs(
    modifier: Modifier = Modifier
) {
    var confirmationShowDialog by remember { mutableStateOf(false) }
    var confirmationStatus by remember { mutableStateOf("") }
    var alertShowDialog by remember { mutableStateOf(false) }
    var alertStatus by remember { mutableStateOf("") }
    val dialogDismissed = stringResource(R.string.dialog_dismissed)
    val dialogTimedOut = stringResource(R.string.dialog_timed_out)
    val dialogNo = stringResource(R.string.confirmation_dialog_no)
    val dialogYes = stringResource(R.string.alert_dialog_yes)

    ScalingLazyColumn(
        modifier = modifier.fillMaxSize(),
        anchorType = ScalingLazyListAnchorType.ItemStart
    ) {
        item {
            OutlinedChip(
                onClick = {
                    confirmationStatus = ""
                    confirmationShowDialog = true
                },
                label = { Text(stringResource(R.string.confirmation_dialog_label)) },
                secondaryLabel = { Text(confirmationStatus) },
                modifier = Modifier.fillMaxWidth()
            )
        }
        item {
            OutlinedChip(
                onClick = {
                    alertStatus = ""
                    alertShowDialog = true
                },
                label = { Text(stringResource(R.string.alert_dialog_label)) },
                secondaryLabel = { Text(alertStatus) },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
    Dialog(
        showDialog = confirmationShowDialog,
        onDismissRequest = {
            if (confirmationStatus.isEmpty()) confirmationStatus = dialogDismissed
            confirmationShowDialog = false
        }
    ) {
        Confirmation(
            onTimeout = {
                confirmationStatus = dialogTimedOut
                confirmationShowDialog = false
            },
            icon = {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = stringResource(R.string.confirmation_dialog_tick),
                    modifier = Modifier.size(48.dp)
                )
            }
        ) {
            Text(
                text = stringResource(R.string.confirmation_dialog_success),
                textAlign = TextAlign.Center
            )
        }
    }
    val scrollState = rememberScalingLazyListState()

    Dialog(
        showDialog = alertShowDialog,
        onDismissRequest = {
            if (alertStatus.isEmpty()) alertStatus = dialogDismissed
            alertShowDialog = false
        },
        scrollState = scrollState
    ) {
        Alert(
            title = {
                Text(
                    text = stringResource(R.string.confirmation_dialog_power_off),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onBackground
                )
            },
            negativeButton = {
                Button(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = dialogNo,
                    onClick = {
                        alertStatus = dialogNo
                        alertShowDialog = false
                    },
                    colors = ButtonDefaults.secondaryButtonColors()
                )
            },
            positiveButton = {
                Button(
                    imageVector = Icons.Filled.Check,
                    contentDescription = dialogYes,
                    onClick = {
                        alertStatus = dialogYes
                        alertShowDialog = false
                    },
                    colors = ButtonDefaults.primaryButtonColors()
                )
            },
            scrollState = scrollState
        ) {
            Text(
                text = stringResource(R.string.dialog_sure),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onBackground
            )
        }
    }
}
