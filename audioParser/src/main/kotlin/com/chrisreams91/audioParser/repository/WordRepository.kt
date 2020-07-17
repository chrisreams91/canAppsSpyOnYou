package com.chrisreams91.audioParser.repository

import com.chrisreams91.audioParser.model.Word
import org.springframework.data.repository.CrudRepository

interface WordRepository : CrudRepository<Word, Long> {
}