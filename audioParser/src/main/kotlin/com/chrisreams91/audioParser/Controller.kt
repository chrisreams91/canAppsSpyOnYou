package com.chrisreams91.audioParser

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.InputStream


@RestController
@RequestMapping
class Controller(val audioParserService: Service) {

  @GetMapping("/")
  fun test(): ResponseEntity<String> {
    return ResponseEntity.ok().body("test")
  }

  @PostMapping("/")
  fun audio(@RequestParam(value = "audio") audioFile: MultipartFile) {
    audioParserService.parseAudio(audioFile)
    ResponseEntity.ok()
  }
  
}