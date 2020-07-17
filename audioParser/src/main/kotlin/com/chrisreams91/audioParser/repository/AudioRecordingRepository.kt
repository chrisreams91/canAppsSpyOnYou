package com.chrisreams91.audioParser.repository

import com.chrisreams91.audioParser.model.Audio
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AudioRecordingRepository : CrudRepository<Audio, Long> {
}