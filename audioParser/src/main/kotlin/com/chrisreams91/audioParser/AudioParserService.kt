package com.chrisreams91.audioParser

import org.springframework.stereotype.Service
import java.io.File
import java.util.concurrent.TimeUnit


@Service
class AudioParserService {

  fun parseAudio() {
    val process = ProcessBuilder("pwd").start()
    process.inputStream.reader(Charsets.UTF_8).use {
      println(it.readText())
    }

    process.waitFor(10, TimeUnit.SECONDS)
  }

}