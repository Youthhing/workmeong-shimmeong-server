package goormthon10.workmeongshimmeong.api.controller;

import goormthon10.workmeongshimmeong.api.dto.EnrollSpaceRequest;
import goormthon10.workmeongshimmeong.domain.service.SpaceService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Space", description = "장소 등록 관련 API 입니다.")
@RestController
@RequestMapping("/api/space")
@RequiredArgsConstructor
public class SpaceController {

    private final SpaceService spaceService;

    @ApiResponse(description = "장소 추가 API")
    @PostMapping("/add")
    public ResponseEntity<?> enrollSpace(@RequestBody EnrollSpaceRequest request) {
        return ResponseEntity.ok(spaceService.enrollSpace(request));
    }
}
