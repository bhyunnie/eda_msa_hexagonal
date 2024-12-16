package study.bhyunnie.member.application.usecase

import study.bhyunnie.member.domain.model.vo.IDName
import study.bhyunnie.member.framework.web.dto.MemberOutputDto

interface UsePointUsecase {
    fun usePoint(idName:IDName, point:Long): MemberOutputDto
}