package study.bhyunnie.bestbook.domain.model

class Item(
	val no:Int,
	val title:String
) {
	companion object {
		fun sample():Item {
			return Item(
				no = 1,
				title = "도메인주도로 시작하는 마이크로 서비스 개발"
			)
		}
	}
}