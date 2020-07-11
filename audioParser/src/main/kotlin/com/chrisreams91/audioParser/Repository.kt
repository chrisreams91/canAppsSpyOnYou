package com.chrisreams91.audioParser

import com.chrisreams91.audioParser.model.Audio
import org.springframework.data.repository.CrudRepository


interface Repository: CrudRepository<Audio, String> {
}