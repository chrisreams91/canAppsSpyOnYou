package com.chrisreams91.audioParser.model

import java.time.Instant
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "words")
data class Word(
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  var id: Long = 0,

  private val word: String = "",

  private val user_id: String = "",

  private val audio_recording_id: String,

  private val creation_time: Date = Date.from(Instant.now())
)