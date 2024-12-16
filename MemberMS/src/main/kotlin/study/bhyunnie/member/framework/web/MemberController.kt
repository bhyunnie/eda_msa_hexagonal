package study.bhyunnie.member.framework.web

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import study.bhyunnie.member.application.usecase.AddMemberUsecase
import study.bhyunnie.member.application.usecase.InquiryMemberUsecase
import study.bhyunnie.member.application.usecase.SavePointUsecase
import study.bhyunnie.member.application.usecase.UsePointUsecase
import study.bhyunnie.member.framework.web.dto.MemberInfoDto
import study.bhyunnie.member.framework.web.dto.MemberOutputDto

@RestController
class MemberController(
    private val addMemberUsecase: AddMemberUsecase,
    private val inquiryMemberUsecase: InquiryMemberUsecase,
    private val savePointUsecase: SavePointUsecase,
    private val usePointUsecase: UsePointUsecase
) {
    @PostMapping("/api/member")
    fun addMember(
        @RequestBody memberInfoDto: MemberInfoDto
    ): ResponseEntity<MemberOutputDto> {
        val addedMember = addMemberUsecase.addMember(memberInfoDto)
        return ResponseEntity(addedMember, HttpStatus.CREATED)
    }

    @GetMapping("/api/member/{no}")
    fun getMember(
        @PathVariable no: Long
    ):ResponseEntity<MemberOutputDto> {
        return ResponseEntity(inquiryMemberUsecase.getMember(no), HttpStatus.OK)
    }
}