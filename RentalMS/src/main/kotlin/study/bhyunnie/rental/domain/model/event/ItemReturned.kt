package study.bhyunnie.rental.domain.model.event

import study.bhyunnie.rental.domain.model.vo.IDName
import study.bhyunnie.rental.domain.model.vo.Item

class ItemReturned(
	idName: IDName,
	item: Item,
	point: Long
):ItemRented(
	idName, item, point
) {

}