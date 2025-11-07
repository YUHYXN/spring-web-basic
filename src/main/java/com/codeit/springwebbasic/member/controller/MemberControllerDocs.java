package com.codeit.springwebbasic.member.controller;

import com.codeit.springwebbasic.common.dto.ApiResponse;
import com.codeit.springwebbasic.member.dto.request.MemberCreateRequestDto;
import com.codeit.springwebbasic.member.dto.response.MemberResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jdk.jfr.Description;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "사용자 관리(Member Controller)", description = "회원 가입, 조회 API")
public interface MemberControllerDocs {

    @Operation(
            summary = "회원 가입 API",
            description = """  
                    새로운 사용자를 등록합니다.
                    
                    ## 요청 데이터
                    - 이름, 이메일, 전화번호 정보가 피룡합니다.
                    - 모든 정보는 필수 값 입니다.
                    - 이메일은 중복될 수 없습니다.
                    
                    ## 응답
                    - 성공 시 저장된 회원 정보가 반환됩니다.
                    """
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201",
                    description = "회원 가입 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class),
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                "code": "SUCCESS",
                                                "message": "요청에 성공했습니다.",
                                                "data": {
                                                    "id": 1,
                                                    "name": "강군필",
                                                    "email": "swordmaster@naver.com",
                                                    "phone": "010-1234-4321",
                                                    "grade": "BRONZE",
                                                    "joinedAt": "2025-11-07T13:13:57.673201"
                                                }
                                            }
                                            
                                            """
                            )
                    )

            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "회원 가입 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class),
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "code": "ILLEGAL_ARGS",
                                              "message": "이미 등록된 이메일 입니다: swordmaster@naver.com",
                                              "data": null
                                          
                                            }
                                            
                                            """
                            )
                    )
            )
    })

    ResponseEntity<ApiResponse<MemberResponseDto>> createMember(
            @Valid @RequestBody MemberCreateRequestDto requestDto);

    ResponseEntity<ApiResponse<MemberResponseDto>> getMember(@PathVariable Long id);

    ResponseEntity<List<MemberResponseDto>> getMembers(
            @Parameter(description = "회원 조회(이름)",required = false)
            @RequestParam(required = false) String name);

}
