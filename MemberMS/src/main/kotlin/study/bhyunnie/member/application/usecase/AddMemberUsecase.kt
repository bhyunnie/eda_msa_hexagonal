package study.bhyunnie.member.application.usecase

import study.bhyunnie.member.framework.web.dto.MemberInfoDto
import study.bhyunnie.member.framework.web.dto.MemberOutputDto


interface AddMemberUsecase {
    fun addMember(memberInfoDto: MemberInfoDto): MemberOutputDto
}