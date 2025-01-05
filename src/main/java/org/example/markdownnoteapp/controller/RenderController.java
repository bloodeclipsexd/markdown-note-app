package org.example.markdownnoteapp.controller;

import org.example.markdownnoteapp.service.HtmlRenderService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/render")
public class RenderController {

    private final HtmlRenderService htmlRenderService;

    public RenderController(HtmlRenderService htmlRenderService) {
        this.htmlRenderService = htmlRenderService;
    }

    @GetMapping("/note")
    public ResponseEntity<String> render(@ModelAttribute("noteContent") String noteContent) {
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(htmlRenderService.render(noteContent));
    }
}
