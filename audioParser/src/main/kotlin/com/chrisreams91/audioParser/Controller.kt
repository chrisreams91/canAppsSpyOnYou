package com.chrisreams91.audioParser

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.io.File


@RestController
@RequestMapping
class Controller(val audioParserService: AudioParserService) {

  @GetMapping("/")
  fun test(): ResponseEntity<String> {
    val test = audioParserService.parseAudio()
    return ResponseEntity.ok().body("test")
  }

  @PostMapping("/audio")
  fun audio(@RequestBody file: File) {
    audioParserService.parseAudio()
    ResponseEntity.ok()
  }
  
}