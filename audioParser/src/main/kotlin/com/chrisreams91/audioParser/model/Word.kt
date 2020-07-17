package com.chrisreams91.audioParser.model

import javax.persistence.*

@Entity
@Table(name = "words")
data class Word(
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  val id: Long = 0,

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "audio_id", nullable = false)
  var audio: Audio? = null,

  private val word: String = "",

  private val user_id: String = ""
)