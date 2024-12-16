package study.bhyunnie.member.application.output

import org.springframework.stereotype.Repository
import study.bhyunnie.member.domain.model.Member
import study.bhyunnie.member.domain.model.vo.IDName

@Repository
interface MemberOutputPort {
    fun loadMember(memberNo: Long): Member
    fun loadMemberByIdName(idName: IDName): Member
    fun saveMember(member: Member): Member
}