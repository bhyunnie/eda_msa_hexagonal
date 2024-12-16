package study.bhyunnie.member.domain.model.vo

data class Email(
	val address: String
) {
	companion object {
		fun sample():Email {
			return Email("dev.bhyunnie@gmail.com")
		}
	}
}