package com.chrisreams91.audioParser.repository

import com.chrisreams91.audioParser.model.AudioRecording
import org.springframework.data.repository.CrudRepository


interface AudioRecordingRepository : CrudRepository<AudioRecording, Long> {
}