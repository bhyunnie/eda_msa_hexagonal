package study.bhyunnie.rental.domain.model.vo

import jakarta.persistence.Embeddable

@Embeddable
data class LateFee(
	val point:Long
) {
	fun addPoint(point: Long): LateFee {
		return LateFee(this.point + point)
	}

	fun removePoint(point: Long): LateFee {
		if(point > this.point) {
			throw RuntimeException("보유한 포인트보다 커서 삭제할 수 없습니다")
		}
		return LateFee(this.point - point)
	}

	companion object {
		fun createLateFee(): LateFee {
			return LateFee(0)
		}

		fun sample(): LateFee {
			return createLateFee()
		}

		@JvmStatic
		fun main(args: Array<String>) {
			println(sample())
		}
	}
}