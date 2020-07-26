package com.chrisreams91.audioParser.controller

import com.chrisreams91.audioParser.model.Word
import com.chrisreams91.audioParser.service.Service
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping
class Controller(val audioParserService: Service) {

  @GetMapping("/words")
  fun test(): List<Word> {
    return audioParserService.getUsersWords()
  }

  @PostMapping("/audioRecording")
  @ResponseStatus(value = HttpStatus.CREATED)
  fun audio(@RequestParam(required = false) audio: MultipartFile): List<String> {
    return audioParserService.parseAudio(audio)
  }

}