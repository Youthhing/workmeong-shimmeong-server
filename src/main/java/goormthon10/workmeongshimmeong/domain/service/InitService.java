package goormthon10.workmeongshimmeong.domain.service;


import goormthon10.workmeongshimmeong.domain.entity.ImageEntity;
import goormthon10.workmeongshimmeong.domain.entity.Member;
import goormthon10.workmeongshimmeong.domain.entity.Program;
import goormthon10.workmeongshimmeong.domain.enums.MemberType;
import goormthon10.workmeongshimmeong.domain.repository.ImageRepository;
import goormthon10.workmeongshimmeong.domain.repository.ProgramRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class InitService {

    private final ProgramRepository programRepository;
    private final ImageRepository imageRepository;

    public void initData() {
        List<Program> programs = new ArrayList<>();
        Member findMember = Member.builder().type(MemberType.HOST)
                .email("boysoeng@naver.com")
                .description("2021년부터 요가 중인 강사 베르데 입니다.")
                .build();

        Program prog1 = Program.builder()
                .name("오름뷰 요가 원데이 클래스")
                .description("빈야사/하타 요가를 기본으 그날 수강생의 컨디션, 실력에 맞게 진행한다.")
                .price(10000L)
                .startDateTime("5월 24일 (금) 오후 6시")
                .member(findMember)
                .build();

        Member findMember1 = Member.builder().type(MemberType.HOST)
                .email("youth@naver.com")
                .description("21년째 수영 중인 강사 베르데 입니다.")
                .build();

        Program prog2 = Program.builder()
                .name("체험 스쿠버 다이빙")
                .description("체험 스쿠버 다이밍을 진행한다.")
                .price(10000L)
                .startDateTime("5월 26일 (일) 오전 11시")
                .member(findMember)
                .build();

        programRepository.save(prog1);
        programRepository.save(prog2);

        imageRepository.save(ImageEntity.of(
                "https://workmeong.s3.ap-northeast-2.amazonaws.com/program/dc7290ba-1749-452c-951c-c862e45846ccRectangle+22500.jpg",
                0,
                prog1));

        imageRepository.save(ImageEntity.of(
                "https://workmeong.s3.ap-northeast-2.amazonaws.com/program/5b148d56-960f-4dc8-8241-26a6effacf90main.png",
                1,
                prog2));
        programRepository.saveAll(programs);
    }
}
