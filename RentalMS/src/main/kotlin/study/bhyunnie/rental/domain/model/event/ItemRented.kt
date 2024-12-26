package study.bhyunnie.rental.domain.model.event

import study.bhyunnie.rental.domain.model.vo.IDName
import study.bhyunnie.rental.domain.model.vo.Item
import java.io.Serializable

open class ItemRented(
	val idName: IDName,
	val item: Item,
	val point: Long
):Serializable {}