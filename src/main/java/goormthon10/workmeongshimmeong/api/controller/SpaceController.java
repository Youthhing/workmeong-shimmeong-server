package goormthon10.workmeongshimmeong.api.controller;

import goormthon10.workmeongshimmeong.api.dto.EnrollSpaceRequest;
import goormthon10.workmeongshimmeong.api.dto.EnrollSpaceResponse;
import goormthon10.workmeongshimmeong.api.dto.SpaceInfoResponse;
import goormthon10.workmeongshimmeong.api.dto.SpaceInfosResponse;
import goormthon10.workmeongshimmeong.domain.service.SpaceService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Space", description = "장소 등록 관련 API 입니다.")
@RestController
@RequestMapping("/api/spaces")
@RequiredArgsConstructor
public class SpaceController {

    private final SpaceService spaceService;

    @ApiResponse(description = "장소 추가 API")
    @PostMapping("/add")
    public ResponseEntity<EnrollSpaceResponse> enrollSpace(@RequestBody EnrollSpaceRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(spaceService.enrollSpace(request));
    }

    @GetMapping("{space-id}")
    public ResponseEntity<SpaceInfoResponse> getSpaceInfo(@PathVariable("space-id") Long id) {
        return ResponseEntity.ok(spaceService.findSpaceInfo(id));
    }

    @GetMapping
    public ResponseEntity<SpaceInfosResponse> getSpaceInfos() {
        return ResponseEntity.ok().body(spaceService.findSpaces());
    }
}
