package study.bhyunnie.member.framework.jpaadapter

import org.springframework.stereotype.Repository
import study.bhyunnie.member.application.output.MemberOutputPort
import study.bhyunnie.member.domain.model.Member
import study.bhyunnie.member.domain.model.vo.IDName

@Repository
class MemberJpaAdapter(
    private val memberRepository: MemberRepository
):MemberOutputPort {
    override fun loadMember(memberNo: Long): Member {
        return memberRepository.findById(memberNo).get()
    }

    override fun loadMemberByIdName(idName: IDName): Member {
        return memberRepository.findMemberByIdName(idName).get()
    }

    override fun saveMember(member: Member): Member {
        return memberRepository.save(member)
    }
}