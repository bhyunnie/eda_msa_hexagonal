package study.bhyunnie.book.domain.model.event

class ItemReturned(
	idName: IDName,
	item: Item,
	point: Long
):ItemRented(
	idName, item, point
) {

}