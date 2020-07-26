package com.chrisreams91.audioParser.service

import com.chrisreams91.audioParser.model.Word
import com.chrisreams91.audioParser.repository.WordRepository
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*


@Service
class Service(private val deepSpeechService: DeepSpeechService,
              private val headerService: HeaderService,
              private val wordService: WordService,
              private val wordRepository: WordRepository) {

  private val projectRootDirectory = System.getProperty("user.dir")

  fun saveAudioFile(file: MultipartFile): Path {
    val path = Paths.get("${projectRootDirectory}/temp-audio.wav")
    Files.write(path, file.bytes)
    return path
  }

  fun parseAudio(file: MultipartFile): List<String> {
    // passing in path here every time because may rename file based on who sent to prevent over lap
    val filePath = saveAudioFile(file)

    val words = deepSpeechService.transcribe(filePath)
    val userId = headerService.getUserId()
    val audioRecordingId = UUID.randomUUID().toString()

    words.map {
      if (wordService.isValidWord(it)) {
        val word = Word(word = it, userId = userId, audioRecordingId = audioRecordingId)
        wordRepository.save(word)
      }
    }

    return words
  }

  fun getUsersWords(): List<Word> {
    val userId = headerService.getUserId()
    val words = wordRepository.findByUserId(userId)
    println(words)
    return words
  }
}