package com.chrisreams91.audioParser

import org.springframework.stereotype.Service
import java.io.File
import java.util.concurrent.TimeUnit


@Service
class AudioParserService {

  fun parseAudio(): List<String> {
    val words = mutableListOf<String>()

    // need to figure out the right path to deepspeech and if i can just use bytes or need a file
    val process = ProcessBuilder("echo", "path/to/the/file").start()

    process.inputStream.reader(Charsets.UTF_8).use {
      it.readText().split(" ").forEach { word ->
        words.add(word)
      }
    }

    process.waitFor(10, TimeUnit.SECONDS)
    return words
  }




}