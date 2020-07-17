package com.chrisreams91.audioParser.service

import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest

@Component
class Header(private val httpServletRequest: HttpServletRequest) {


  fun getUserId(): String {
    return try {
      httpServletRequest.getHeader(userId)
    } catch (exception: Exception) {
      return "aint got no username header"
    }
  }


  companion object {
    internal const val userId = "user-id"
  }
}

