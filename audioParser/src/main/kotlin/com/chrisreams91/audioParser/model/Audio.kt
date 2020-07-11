package com.chrisreams91.audioParser.model

import java.time.Instant
import java.util.*

data class Audio(
  private val words: List<String> = emptyList(),
  private val timestamp: Date = Date.from(Instant.now())
)