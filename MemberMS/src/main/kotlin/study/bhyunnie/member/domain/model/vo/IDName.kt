package study.bhyunnie.member.domain.model.vo

import jakarta.persistence.Embeddable

@Embeddable
data class IDName(
	val id: String,
	val name: String
) {
	companion object {
		fun sample(): IDName {
			return IDName("honeyb", "bhyunnie")
		}

		@JvmStatic
		fun main(args: Array<String>) {
			println(sample())
		}
	}
}