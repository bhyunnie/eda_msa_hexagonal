package study.bhyunnie.rental.domain.model.vo

data class IDName(
	val id: String,
	val name: String,
) {
	companion object {
		fun sample(): IDName {
			return IDName(
				"bhyunnie",
				"an"
			)
		}

		@JvmStatic
		fun main(args: Array<String>) {
			println(sample())
		}
	}
}