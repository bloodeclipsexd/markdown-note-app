package org.example.markdownnoteapp.service;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Service;

@Service
public class HtmlRenderService {

    public String render(String content) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(content);

        HtmlRenderer renderer = HtmlRenderer.builder().build();

        return renderer.render(document);
    }
}
