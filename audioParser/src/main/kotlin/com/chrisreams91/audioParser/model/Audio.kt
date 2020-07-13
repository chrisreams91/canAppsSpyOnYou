package com.chrisreams91.audioParser.model

import com.vladmihalcea.hibernate.type.json.JsonBinaryType
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import java.time.Instant
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "words")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType::class)
data class Audio(

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  val id : Long = 0,

  @Type(type = "jsonb")
  @Column(columnDefinition = "jsonb")
  private val words: List<String> = emptyList(),

  private val creation_time: Date = Date.from(Instant.now())
)