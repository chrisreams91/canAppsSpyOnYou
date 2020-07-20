package com.chrisreams91.audioParser.service

import org.springframework.stereotype.Service

@Service
class WordService {

  fun isValidWord(word: String): Boolean {

    // add more robust validation of words
    return word.length > 2
  }
}