package com.chrisreams91.audioParser.service

import org.springframework.stereotype.Service

@Service
class WordService {

  fun isValidWord(word: String): Boolean {
    println(word)

    return word.length > 2
  }
}