package com.umc.umc_10th.domain.mission.exception.code;

import com.umc.umc_10th.global.apiPayLoad.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    MISSION_IN_PROGRESS_LIST_SUCCESS(HttpStatus.OK, "MISSION200_01", "진행 중인 미션 목록 조회에 성공하였습니다."),
    MISSION_COMPLETED_LIST_SUCCESS(HttpStatus.OK, "MISSION200_02", "완료된 미션 목록 조회에 성공하였습니다."),
    MISSION_SUCCESS_UPDATE(HttpStatus.OK, "MISSION200_03", "미션 성공 처리가 완료되었습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
