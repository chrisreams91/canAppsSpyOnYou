package com.chrisreams91.audioParser.repository

import com.chrisreams91.audioParser.model.Word
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface WordRepository : CrudRepository<Word, Long> {

  fun findByUserId(userId: String): List<Word>
  
}