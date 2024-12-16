package study.bhyunnie.member.application.input

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import study.bhyunnie.member.application.output.MemberOutputPort
import study.bhyunnie.member.application.usecase.SavePointUsecase
import study.bhyunnie.member.domain.model.vo.IDName
import study.bhyunnie.member.framework.web.dto.MemberOutputDto

@Service
@Transactional
class SavePointInputPort(
    private val memberOutputPort: MemberOutputPort
): SavePointUsecase {
    override fun savePoint(idName: IDName, point: Long): MemberOutputDto {
        val loadMember = memberOutputPort.loadMemberByIdName(idName)
        loadMember.savePoint(point)
        return MemberOutputDto.mapToDto(loadMember)
    }
}