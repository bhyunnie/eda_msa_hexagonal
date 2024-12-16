package study.bhyunnie.member.application.input

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import study.bhyunnie.member.application.output.MemberOutputPort
import study.bhyunnie.member.application.usecase.UsePointUsecase
import study.bhyunnie.member.domain.model.vo.IDName
import study.bhyunnie.member.framework.web.dto.MemberOutputDto

@Service
@Transactional
class UsePointInputPort(
    private val memberOutputPort: MemberOutputPort
): UsePointUsecase {
    override fun usePoint(idName: IDName, point: Long): MemberOutputDto {
        val loadMember = memberOutputPort.loadMemberByIdName(idName)
        loadMember.usePoint(point)
        return MemberOutputDto.mapToDto(loadMember)
    }

}