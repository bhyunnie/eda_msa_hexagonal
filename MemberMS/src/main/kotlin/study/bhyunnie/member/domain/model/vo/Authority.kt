package study.bhyunnie.member.domain.model.vo

import jakarta.persistence.Embeddable

@Embeddable
data class Authority(
	val roleName: UserRole
) {
	companion object {
		fun sample():Authority {
			return Authority(UserRole.USER)
		}
	}
}