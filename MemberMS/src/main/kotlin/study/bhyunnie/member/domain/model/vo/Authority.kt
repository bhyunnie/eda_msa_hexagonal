package study.bhyunnie.member.domain.model.vo

data class Authority(
	val roleName: UserRole
) {
	companion object {
		fun sample():Authority {
			return Authority(UserRole.USER)
		}
	}
}