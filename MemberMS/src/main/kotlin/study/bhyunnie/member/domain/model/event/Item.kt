package study.bhyunnie.member.domain.model.event

import jakarta.persistence.Embeddable

@Embeddable
data class Item(
	val no: Int,
	val title: String
) {
	companion object {
		fun sample(): Item {
			return Item(
				no = 55,
				title = "이펙티브 코틀린"
			)
		}

		@JvmStatic
		fun main(args: Array<String>) {
			println(sample())
		}
	}
}