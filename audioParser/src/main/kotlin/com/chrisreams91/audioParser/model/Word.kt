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

  private val userId: String = "",

  private val audioRecordingId: String = "",

  private val creationTime: Date = Date.from(Instant.now())
)