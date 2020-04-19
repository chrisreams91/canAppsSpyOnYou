package com.chrisreams91.audioParser

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping
class Controller(val audioParserService: AudioParserService) {


  @GetMapping("/")
  fun test(): ResponseEntity<String> {
    audioParserService.parseAudio()
    return ResponseEntity.ok().body("test")
  }
}