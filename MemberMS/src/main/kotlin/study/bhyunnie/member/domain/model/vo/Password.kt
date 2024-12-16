package study.bhyunnie.member.domain.model.vo

data class Password(
	val presentPwd: String,
	val pastPwd: String
) {
	companion object {
		fun sample():Password {
			return Password(
				presentPwd = "1234",
				pastPwd = "4321"
			)
		}
	}
}