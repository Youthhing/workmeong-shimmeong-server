package goormthon10.workmeongshimmeong.api.controller;

import goormthon10.workmeongshimmeong.api.dto.TagInfosResponse;
import goormthon10.workmeongshimmeong.domain.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping
    public ResponseEntity<TagInfosResponse> findTags() {
        return ResponseEntity.ok(tagService.findTags());
    }
}
