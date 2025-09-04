package com.billquote.controller;

@RestController
@RequestMapping("/api/health")
public class HealthController {
  @GetMapping public String ok() { return "OK"; }
}
