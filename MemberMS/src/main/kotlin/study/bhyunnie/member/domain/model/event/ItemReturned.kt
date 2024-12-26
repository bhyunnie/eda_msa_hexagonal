package study.bhyunnie.member.domain.model.event

import study.bhyunnie.member.domain.model.vo.IDName

class ItemReturned(
	idName: IDName,
	item: Item,
	point: Long
):ItemRented(
	idName, item, point
) {

}