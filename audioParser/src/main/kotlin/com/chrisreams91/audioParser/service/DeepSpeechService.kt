package com.chrisreams91.audioParser.service

import org.springframework.stereotype.Service
import java.io.File
import java.nio.file.Path
import java.util.concurrent.TimeUnit

@Service
class DeepSpeechService {


  fun createAndActivateVirtualEnv() {
    val createEnv = ProcessBuilder("virtualenv", "-zp", "python3", "\$HOME/tmp/deepspeech-venv/")
    val activateEnv = ProcessBuilder("source", "\$HOME/tmp/deepspeech-venv/bin/activate")

    createEnv.start().inputStream.reader(Charsets.UTF_8).use {
      println(it.readText())
    }

    activateEnv.start().inputStream.reader(Charsets.UTF_8).use {
      println(it.readText())
    }
  }

  fun installDeepSpeech() {
    val install = ProcessBuilder("pip3", "install", "deepspeech")
  }

  fun downloadModels() {
    val model = ProcessBuilder("curl", "-LO", "https://github.com/mozilla/DeepSpeech/releases/download/v0.7.4/deepspeech-0.7.4-models.pbmm")
  }

  fun transcribe(filePath: Path): MutableList<String> {
    val words = mutableListOf<String>()

    val processBuilders = mutableListOf(
      ProcessBuilder("deepspeech", "--model", "deepspeech-0.7.0-models.pbmm", "--audio", filePath.toString())
        .directory(File("/Users/tcxr3")),
      ProcessBuilder("cat")
    )

    val processes: List<Process> = ProcessBuilder.startPipeline(processBuilders)
    val first = processes.first()
    val last = processes.last()

    first.waitFor(10, TimeUnit.SECONDS)
    last.waitFor(10, TimeUnit.SECONDS)

    last.inputStream.reader(Charsets.UTF_8).use {
      it
        .readText()
        .replace("\n", "")
        .split(" ")
        .forEach { word ->
          words.add(word)
        }
    }

    return words
  }
}