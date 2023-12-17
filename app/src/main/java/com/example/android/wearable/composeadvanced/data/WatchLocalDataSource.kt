/*
 * Copyright 2021 The Android Open Source Project
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
package com.example.android.wearable.composeadvanced.data

/**
 * Simple Data source for a list of fake watch models.
 */
class WatchLocalDataSource {
    val watches = listOf(
        WatchModel(
            modelId = 100001,
            name = "Alice",
            description = "200ft away N"
        ),
        WatchModel(
            modelId = 100002,
            name = "Bob",
            description = "400ft away NW"
        ),
        WatchModel(
            modelId = 100003,
            name = "Clark",
            description = "450ft away NE"
        )
    )
}
