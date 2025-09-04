package com.billquote.controller;

@RestController
@RequestMapping("/api/societes")
@RequiredArgsConstructor
public class SocieteController {
  private final SocieteService service;
  @PostMapping public ResponseEntity<SocieteDTO> create(@Valid @RequestBody SocieteDTO dto){...}
  @GetMapping("/{id}") public SocieteDTO get(@PathVariable Long id){...}
  @PutMapping("/{id}") public SocieteDTO update(@PathVariable Long id,@Valid @RequestBody SocieteDTO dto){...}
}
