package goormthon10.workmeongshimmeong.api.controller;

import goormthon10.workmeongshimmeong.api.dto.AddImagesRequest;
import goormthon10.workmeongshimmeong.api.dto.ChatLinkResponse;
import goormthon10.workmeongshimmeong.api.dto.DateResponse;
import goormthon10.workmeongshimmeong.api.dto.EnrollProgramRequest;
import goormthon10.workmeongshimmeong.api.dto.EnrollProgramResponse;
import goormthon10.workmeongshimmeong.api.dto.ProgramInfoResponse;
import goormthon10.workmeongshimmeong.api.dto.ProgramInfosResponse;
import goormthon10.workmeongshimmeong.common.error.ImageException;
import goormthon10.workmeongshimmeong.domain.service.ProgramService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Program", description = "프로그램 등록 관련 API 입니다.")
@RestController
@RequestMapping("/api/programs")
@RequiredArgsConstructor
public class ProgramController {

    private final ProgramService programService;

    //    @ApiResponse(description = "프로그램 추가 API")
    @PostMapping(value = "/add", consumes = "multipart/form-data")
    public ResponseEntity<EnrollProgramResponse> enrollSpace(@ModelAttribute EnrollProgramRequest request)
            throws ImageException {
        return ResponseEntity.status(HttpStatus.CREATED).body(programService.enrollSpace(request));
    }

    @ApiResponse(description = "프로그램 상세 정보 조회 API")
    @GetMapping("/{program-id}")
    public ResponseEntity<ProgramInfoResponse> getProgramInfo(@PathVariable("program-id") Long id) {
        return ResponseEntity.ok(programService.findProgramInfo(id));
    }

    @ApiResponse(description = "프로그램 전체 정보 조회 API")
    @GetMapping
    public ResponseEntity<ProgramInfosResponse> getProgramInfos() {
        return ResponseEntity.ok().body(programService.findPrograms());
    }

    @GetMapping("/date/{program-id}")
    public ResponseEntity<DateResponse> getAvailableReservationDate(@PathVariable("program-id") Long id) {
        return ResponseEntity.ok().body(programService.getAvailableDate(id));
    }

    @ApiResponse(description = "프로그램 오픈 채팅방 링크 조회 API")
    @GetMapping("/{program-id}/chat-link")
    public ResponseEntity<ChatLinkResponse> getChatLink(@PathVariable("program-id") Long id) {
        return ResponseEntity.ok().body(programService.findChatLink(id));
    }

    @ApiResponse(description = "특정 프로그램에 사진을 추가한다.")
    @PostMapping(value = "/{program-id}/images", consumes = "multipart/form-data")
    public ResponseEntity<Void> enrollAdditionalImages(@PathVariable("program-id") Long id,
                                                       @ModelAttribute AddImagesRequest request) throws ImageException {
        programService.addImages(request, id);
        return ResponseEntity.noContent().build();
    }

}
