package com.chrisreams91.audioParser

import com.chrisreams91.audioParser.service.DeepSpeech
import com.chrisreams91.audioParser.service.Service
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping
class Controller(val audioParserService: Service, val deepSpeech: DeepSpeech) {

  @GetMapping("/")
  fun test(): ResponseEntity<String> {
    deepSpeech.createAndActivateVirtualEnv()
    return ResponseEntity.ok().body("test")
  }

  @PostMapping("/audioRecording")
  fun audio(@RequestParam(value = "audio") audioFile: MultipartFile) {
    audioParserService.parseAudio(audioFile)
    ResponseEntity.ok()
  }

}