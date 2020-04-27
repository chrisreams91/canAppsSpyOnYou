package com.chrisreams91.audioParser

import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit


@Service
class Service {

  fun parseAudio(file: MultipartFile): List<String> {
    val words = mutableListOf<String>()

    val processBuilders = mutableListOf(
      ProcessBuilder("deepspeech", "--model", "deepspeech-0.7.0-models.pbmm", "--audio", "audio/2830-3980-0043.wav")
        .directory(File("/Users/tcxr3")),
      ProcessBuilder("cat")
    )

    val processes: List<Process> = ProcessBuilder.startPipeline(processBuilders)
    val first = processes.first()
    val last = processes.last()

    first.waitFor(10, TimeUnit.SECONDS)
    last.waitFor(10, TimeUnit.SECONDS)

    // deepspeech --model deepspeech-0.7.0-models.pbmm --audio audio/2830-3980-0043.wav | cat
    last.inputStream.reader(Charsets.UTF_8).use {
      it.readText().split(" ").forEach { word ->
        words.add(word)
      }
    }

    return words
  }




}