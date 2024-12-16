package study.bhyunnie.member.framework.web.dto

import study.bhyunnie.member.domain.model.Member

class MemberOutputDto(
    val id:String,
    val name:String,
    val password: String,
    val email:String,
    val point: Long,
) {
    companion object {
        fun mapToDto(member:Member): MemberOutputDto {
            return MemberOutputDto(
                id = member.idName.id,
                name = member.idName.name,
                password = member.password.presentPwd,
                email = member.email.address,
                point = member.point.pointValue
            )
        }
    }
}