package com.chrisreams91.audioParser.controller

import com.chrisreams91.audioParser.service.Service
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping
class Controller(val audioParserService: Service) {

  @GetMapping("/words")
  fun test(): ResponseEntity<String> {
    val words = audioParserService.getUsersWords()
    return ResponseEntity.ok().body("test")
  }

  @PostMapping("/audioRecording")
  fun audio(@RequestParam(value = "audio") audioFile: MultipartFile) {
    val words = audioParserService.parseAudio(audioFile)
    ResponseEntity.ok(words)
  }

}