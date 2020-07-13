package com.chrisreams91.audioParser

import com.chrisreams91.audioParser.model.AudioRecording
import org.springframework.data.repository.CrudRepository


interface Repository: CrudRepository<AudioRecording, Long> {
}