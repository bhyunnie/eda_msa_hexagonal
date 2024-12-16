package study.bhyunnie.member.framework.jpaadapter

import org.springframework.data.jpa.repository.JpaRepository
import study.bhyunnie.member.domain.model.Member
import study.bhyunnie.member.domain.model.vo.IDName
import java.util.Optional

interface MemberRepository:JpaRepository<Member,Long> {
    fun findMemberByIdName(idName: IDName):Optional<Member>
}