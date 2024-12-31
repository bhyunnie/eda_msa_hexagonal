package study.bhyunnie.member.domain.model

import jakarta.persistence.*
import study.bhyunnie.member.domain.model.vo.Authority
import study.bhyunnie.member.domain.model.vo.Email
import study.bhyunnie.member.domain.model.vo.IDName
import study.bhyunnie.member.domain.model.vo.Password
import study.bhyunnie.member.domain.model.vo.Point
import study.bhyunnie.member.domain.model.vo.UserRole

@Entity
class Member(
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	val memberNo: Long = 0,
	@Embedded
	val idName: IDName,
	@Embedded
	val password: Password,
	@Embedded
	val email: Email,
	@ElementCollection
	val authorities: MutableList<Authority> = mutableListOf(),
	@Embedded
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