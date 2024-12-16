package study.bhyunnie.member.application.usecase

import study.bhyunnie.member.framework.web.dto.MemberOutputDto

interface InquiryMemberUsecase {
    fun getMember(memberNo:Long): MemberOutputDto
}