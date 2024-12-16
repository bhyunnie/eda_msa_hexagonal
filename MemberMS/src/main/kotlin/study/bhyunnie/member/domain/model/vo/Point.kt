package study.bhyunnie.member.domain.model.vo

data class Point(
	var pointValue: Long
) {
	fun addPoint(point:Long):Long {
		this.pointValue += point
		return this.pointValue
	}

	fun removePoint(point:Long):Long {
		if (point > this.pointValue) {
			throw RuntimeException("기존 보유 Point 보다 적어 실행할 수 없습니다")
		}
		this.pointValue -= point
		return this.pointValue
	}

	companion object {
		fun createPoint(): Point {
			return Point(0L)
		}

		fun sample():Point {
			return Point(10L)
		}
	}
}