package study.bhyunnie.member.application.input

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import study.bhyunnie.member.application.output.MemberOutputPort
import study.bhyunnie.member.application.usecase.InquiryMemberUsecase
import study.bhyunnie.member.framework.web.dto.MemberOutputDto

@Service
@Transactional
class InquiryMemberInputPort(
    private val memberOutputPort: MemberOutputPort
):InquiryMemberUsecase {
    override fun getMember(memberNo:Long):MemberOutputDto {
        val loadMember = memberOutputPort.loadMember(memberNo)
        return MemberOutputDto.mapToDto(loadMember)
    }
}