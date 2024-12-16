package study.bhyunnie.member.domain.model

import study.bhyunnie.member.domain.model.vo.Authority
import study.bhyunnie.member.domain.model.vo.Email
import study.bhyunnie.member.domain.model.vo.IDName
import study.bhyunnie.member.domain.model.vo.Password
import study.bhyunnie.member.domain.model.vo.Point
import study.bhyunnie.member.domain.model.vo.UserRole

class Member(
	val memberNo: Long = 0,
	val idName: IDName,
	val password: Password,
	val email: Email,
	val authorities: ArrayList<Authority> = arrayListOf(),
	val point: Point
) {
	companion object {
		fun registerMember(idName: IDName, pwd:Password, email: Email): Member {
			val member = Member(
				idName = idName,
				password = pwd,
				email = email,
				point = Point.createPoint(),
			)
			member.addAuthority(Authority(UserRole.USER))
			return member
		}
	}

	fun addAuthority(authority: Authority) {
		this.authorities.add(authority)
	}

	fun savePoint(point:Long) {
		this.point.addPoint(point)
	}

	fun usePoint(point: Long) {
		this.point.removePoint(point)
	}

	fun login(idName: IDName, password: Password):Member {
		return this
	}

	fun logout(idName: IDName) {

	}
}