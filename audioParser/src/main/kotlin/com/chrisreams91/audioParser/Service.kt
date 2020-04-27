package com.chrisreams91.audioParser

import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.concurrent.TimeUnit


@Service
class Service {

  private val projectRootDirectory = System.getProperty("user.dir")

  fun saveAudioFile(file: MultipartFile): Path {
    val path = Paths.get("${projectRootDirectory}/src/main/kotlin/com/chrisreams91/audioParser/Audio/temp.wav")
    Files.write(path, file.bytes)
    return path
  }

  fun parseAudio(file: MultipartFile): List<String> {
    val words = mutableListOf<String>()
    val filePath = saveAudioFile(file)

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