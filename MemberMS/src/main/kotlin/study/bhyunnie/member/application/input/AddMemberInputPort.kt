package study.bhyunnie.member.application.input

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import study.bhyunnie.member.application.output.MemberOutputPort
import study.bhyunnie.member.application.usecase.AddMemberUsecase
import study.bhyunnie.member.domain.model.Member
import study.bhyunnie.member.domain.model.vo.Email
import study.bhyunnie.member.domain.model.vo.IDName
import study.bhyunnie.member.domain.model.vo.Password
import study.bhyunnie.member.framework.web.dto.MemberInfoDto
import study.bhyunnie.member.framework.web.dto.MemberOutputDto

@Service
@Transactional
class AddMemberInputPort(
    private val memberOutputPort: MemberOutputPort
): AddMemberUsecase {
    override fun addMember(memberInfoDto: MemberInfoDto): MemberOutputDto {
        val idName = IDName(memberInfoDto.id, memberInfoDto.name)
        val pwd = Password(memberInfoDto.password, memberInfoDto.password)
        val email = Email(memberInfoDto.email)
        val addedMember = Member.registerMember(idName, pwd, email)
        val savedMember = memberOutputPort.saveMember(addedMember)
        return MemberOutputDto.mapToDto(savedMember)
    }

}