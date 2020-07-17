package com.chrisreams91.audioParser.service

import com.chrisreams91.audioParser.Repository
import com.chrisreams91.audioParser.model.AudioRecording
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.time.Instant
import java.util.*
import java.util.concurrent.TimeUnit


@Service
class Service(private val deepSpeech: DeepSpeech,
              private val repository: Repository,
              private val header: Header) {

  private val projectRootDirectory = System.getProperty("user.dir")

  fun saveAudioFile(file: MultipartFile): Path {
    val path = Paths.get("${projectRootDirectory}/temp-audio.wav")
    Files.write(path, file.bytes)
    return path
  }

  fun parseAudio(file: MultipartFile) {
    // passing in path here every time because may rename file based on who sent to prevent over lap
    val filePath = saveAudioFile(file)

    val words = deepSpeech.transcribe(filePath)

    val userId = header.getUserId()
    val audio = AudioRecording(words = words, creation_time = Date.from(Instant.now()), user_id = userId)
    repository.save(audio)
  }

}